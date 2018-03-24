package com.skt.rmsbatch.riskmaster.tasklet;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.skt.rmsbatch.domain.InfHist;
import com.skt.rmsbatch.domain.RiskMasterInfo;
import com.skt.rmsbatch.propertiespojo.HiomsParams;
import com.skt.rmsbatch.propertiespojo.RiskMasterHiomsParams;
import com.skt.rmsbatch.responsepojo.Message;
import com.skt.rmsbatch.responsepojo.Record;
import com.skt.rmsbatch.responsepojo.Response;
import com.skt.rmsbatch.riskmaster.service.HiomsService;
import com.skt.rmsbatch.utils.DateUtils;

public class RiskMasterInfoInsertTasklet implements Tasklet {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RiskMasterInfoInsertTasklet.class);
	
	
	@Autowired
	private Environment env;
	
	@Autowired
	DateUtils dateUtils;
	
	@Autowired
	private HiomsService hiomsService;
	
	private HiomsParams hiomsParams;
	
	private String jobName;
	
	@Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) {
		
		this.jobName = chunkContext.getStepContext().getStepExecution().getJobExecution().getJobInstance().getJobName();
		
		LOGGER.info("{} | RiskMasterInfoInsertTasklet Start", jobName);
		
		hiomsParams = new RiskMasterHiomsParams(env, jobName);
		
        if(jobName.equals("riskMasterInfoJob")){
        	
        	Response response = readResponseByXml( hiomsParams.getRiskMasterInfoJobResourceUrl() );
            
        	InfHist infHist = convertResponseToIsHist( response );
            
            hiomsService.writeInfHist(infHist);
        	
        }else if(jobName.equals("riskCauseJob")){
        	
        	List<RiskMasterInfo> riskMngNumList = hiomsService.readRiskCauseIsNullList(hiomsParams.getPeriodDay());
        	
        	List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        	
        	for(RiskMasterInfo riskMasterInfo :riskMngNumList){
        		
        		Response response = readResponseByXml( hiomsParams.getRiskCauseJobResourceUrl(riskMasterInfo.getRiskMngNum()) );
        		
        		hiomsService.writeInfHist(convertResponseToInfHist(response));
        		
        		for( Map<String, Object> riskMngNumMap : convertResponseToRiskMngNumListMap( response )){
        			mapList.add( riskMngNumMap );
        		}
        		
        	}
        	
        	LOGGER.info(" 업데이트 될  riskCause 갯수 : {}", mapList.size());
        	
        	hiomsService.updateRiskCauseByRiskMngNum( mapList );
        	
        }else if(jobName.equals("reRiskMasterInfoJob")){
        	
        	hiomsService.readFailRiskMasterInfoList( hiomsParams.getPeriodDay() ).forEach(infHist->{
        		
        		Response response = readResponseByXml( hiomsParams.getReRiskMasterInfoJobResourceUrl( infHist.getExecutionDate() ) );
            	InfHist infHistDomain = convertResponseToIsHist( response );
                hiomsService.writeInfHist(infHistDomain);
        	});
        	
        }
        
        LOGGER.info("{} ------------------------------------ RiskMasterInfoInsertTasklet End", jobName);
        
		return RepeatStatus.FINISHED;
	}
	
	private Response readResponseByXml( String resourceUrl ){
		
		LOGGER.info("리소스 파일 Read");

        Response response = null;
        
        LOGGER.info("리소스 파일 경로 : {}", resourceUrl);
        
		try{
        	JAXBContext jaxbContext = null;
    		jaxbContext = JAXBContext.newInstance(Response.class);
    		
    		if(new File(resourceUrl).exists()){
                File fXmlFile = new File(resourceUrl);
                response =  (Response) jaxbContext.createUnmarshaller().unmarshal(fXmlFile);
    		}
            
        }catch(JAXBException e){
			e.printStackTrace();
		}
        
        return response;
	}
	
	private InfHist convertResponseToIsHist( Response response ){
		
		LOGGER.info("Read한 Xml 파일 Domain 객체로 변환");
		
		if(response == null){
			return null;
		}
		
    	List<Record> recordList = new ArrayList<Record>();
    	
    	if(response.getDataSet().getRecordSet().getRecord() != null){
    		recordList = response.getDataSet().getRecordSet().getRecord();
    	}
    	
    	InfHist infHist = convertResponseToInfHist(response);
    	
    	List<RiskMasterInfo> riskMasterInfoList = new ArrayList<RiskMasterInfo>();
    	
    	for(Record record :recordList){
    		
    		if(hiomsService.findByRiskMngNum(record.getRiskMngNum()) != 0){
    			continue;
    		}
    		
    		RiskMasterInfo riskMasterInfo = new RiskMasterInfo();
    		riskMasterInfo.setRiskMngNum(record.getRiskMngNum());
    		riskMasterInfo.setStartDate(dateUtils.stringToDate(record.getStartDate(), "yyyy-MM-dd HH:mm"));
    		riskMasterInfo.setEndDate(dateUtils.stringToDate(record.getEndDate(), "yyyy-MM-dd HH:mm"));
    		riskMasterInfo.setRiskTitle(record.getRiskTitle());
    		riskMasterInfo.setRiskContent(record.getRiskContent());
    		riskMasterInfo.setRiskCause(record.getRiskCause());
    		riskMasterInfo.setRiskLevel(record.getRiskLevel());
    		riskMasterInfo.setCallCnt(dateUtils.stringToInt(record.getCallCnt()));
    		riskMasterInfo.setRiskMeasure(record.getRiskMeasure());
    		riskMasterInfo.setRiskAddType("P");
    		riskMasterInfoList.add(riskMasterInfo);
    	}
    	
    	LOGGER.info("새로 저장될 riskMasterInfo 갯수 : {}", riskMasterInfoList.size());
    	
        infHist.setRiskMasterInfo(riskMasterInfoList);
        
        return infHist;
	}
	
	/**
	 * RiskCause Batch Convertor 
	 * @param response
	 * @return
	 */
	private List<Map<String, Object>> convertResponseToRiskMngNumListMap( Response response ){
		
		List<Map<String, Object>> riskMngNumList = new ArrayList<Map<String, Object>>();
		
		List<Record> recordList = new ArrayList<Record>();
		
		if(response.getDataSet().getRecordSet().getRecord() != null){
			recordList = response.getDataSet().getRecordSet().getRecord();
		}
		
		for(Record record:recordList){
			Map<String, Object> riskMngNumMap = new HashMap<String, Object>();
			riskMngNumMap.put("riskMngNum", record.getRiskMngNum());
			riskMngNumMap.put("riskCause", record.getRiskCause());
			riskMngNumList.add(riskMngNumMap);
		}
		
		return riskMngNumList;
	}
	
	private InfHist convertResponseToInfHist(Response response){
    	InfHist infHist = new InfHist();
    	if(response.getDataSet().getMessage() != null){
    		Message message = response.getDataSet().getMessage();
        	infHist.setResult(message.getResult());
        	infHist.setMessageId(message.getMessageId());
        	infHist.setMessageName(message.getMessageName());
        	infHist.setMessageReason(message.getMessageReason());
        	infHist.setMessageRemark(message.getMessageRemark());
        	infHist.setExecutionDate(new Date());
        	infHist.setJobName(jobName);
    	}
    	return infHist;
	}
	
}

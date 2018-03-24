package com.skt.rmsbatch.riskmaster.tasklet;

import java.io.File;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.skt.rmsbatch.domain.InfHist;
import com.skt.rmsbatch.domain.RiskMasterInfo;
import com.skt.rmsbatch.propertiespojo.HiomsParams;
import com.skt.rmsbatch.propertiespojo.RiskMasterHiomsParams;
import com.skt.rmsbatch.repositories.InfHistRepository;
import com.skt.rmsbatch.repositories.RiskMasterInfoRepository;
import com.skt.rmsbatch.responsepojo.Response;
import com.skt.rmsbatch.riskmaster.service.HiomsService;
import com.skt.rmsbatch.utils.DateUtils;

public class ResponseMakeXmlTasklet implements Tasklet {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RiskMasterInfoInsertTasklet.class);
	
	private HiomsParams hiomsParams;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private DateUtils dateUtils;
	
	@Autowired
	private HiomsService hiomsService;
	
	private String jobName;
	
	@Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) {
		
		this.jobName = chunkContext.getStepContext().getStepExecution().getJobExecution().getJobInstance().getJobName();
		
		LOGGER.info("{} | ResponseMakeXmlTasklet Tasklet Start", jobName);
		
		hiomsParams = new RiskMasterHiomsParams(env, jobName);
		
		if(jobName.equals("riskMasterInfoJob")){
		
			String requestParam = hiomsParams.getRiskMasterInfoParam();
			String filePathName = hiomsParams.getRiskMasterInfoJobResourceUrl();
			
			readResponseByHiomsMakeXmlFile(requestParam, filePathName);
        
		}else if(jobName.equals("riskCauseJob")){
        	
        	hiomsService.readRiskCauseIsNullList(hiomsParams.getPeriodDay()).forEach(riskMasterInfo->{
        		
        		String requestParam = hiomsParams.getRiskCauseParam(riskMasterInfo.getRiskMngNum());
        		String filePathName = hiomsParams.getRiskCauseJobResourceUrl(riskMasterInfo.getRiskMngNum());
        		
        		readResponseByHiomsMakeXmlFile(requestParam, filePathName);
        	});
        	
        }else if(jobName.equals("reRiskMasterInfoJob")){
        	
        	hiomsService.readFailRiskMasterInfoList( hiomsParams.getPeriodDay() ).forEach(infHist->{
        		
        		String requestParam = hiomsParams.getReRiskMasterInfoParam( infHist.getExecutionDate() );
        		String filePathName = hiomsParams.getReRiskMasterInfoJobResourceUrl(infHist.getExecutionDate());
        		
        		readResponseByHiomsMakeXmlFile(requestParam, filePathName);
        	});
        	
        }
		
		LOGGER.info("Make File 파일경로  : {}" , hiomsParams.getFilePath());
		LOGGER.info("Make File 파일명   : {}" , hiomsParams.getFileName());
		LOGGER.info("검색기간(Day) : {}" , hiomsParams.getPeriodDay());
		LOGGER.info("검색시작일(시작일 세팅 없을시 오늘 - 검색기간) : {}" , hiomsParams.getStartDate());
		LOGGER.info("검색종료일(종료일 세팅 없을시 오늘)  : {}" , hiomsParams.getEndDate());
		
		LOGGER.info("{} ------------------------------------ ResponseMakeXmlTasklet Tasklet End", jobName);
		
		return RepeatStatus.FINISHED;
	}
	
	private void readResponseByHiomsMakeXmlFile(String requestParam, String filePathName){
		
		LOGGER.info("Hioms 정보 Read");

        Response response = null;
        
        String resourceUrl = "";
        if(jobName.equals("riskMasterInfoJob")){
        	resourceUrl = "/risk_response_"+"20160810"+".xml";
        }else if(jobName.equals("riskCauseJob")){
        	resourceUrl = "/risk_cause_response_"+"20160810"+".xml";
        }
        
		try{
        	JAXBContext jaxbContext = null;
    		jaxbContext = JAXBContext.newInstance(Response.class);
    		
    		LOGGER.info("filePath : {}", hiomsParams.getFilePath());
    		
    		if(this.getClass().getResource(resourceUrl) != null){
    			
    			/*String filePath = hiomsParams.getFilePath();
    			URL resource = this.getClass().getResource(resourceUrl);
                File fXmlFile = new File(resource.toURI());
                response =  (Response) jaxbContext.createUnmarshaller().unmarshal(fXmlFile);*/
                
                //웹 서비스 access point 지정 
    	        String endpoint = "http://203.235.210.94:9280/omwebservice/services/OmWebserviceImpl?wsdl";
    	        
    	        // 원격 웹 서비스에 대한 Service 객체 생성후, Call 객체 생성 
    	        Service service = new Service();
    	        Call call = (Call) service.createCall();
    	        // 입출력 인자정보 및 웹 서비스 access point 를 Call 객체에 바인딩
    	        call.setTargetEndpointAddress( new URL(endpoint) );
    	        call.setOperationName( 
    	        		new QName("http://webservice.om.skcc.com", "getOmWebservice"));
    	        //call.addParameter("request", XMLType.XSD_STRING, ParameterMode.IN);
    	        //call.setReturnType(XMLType.XSD_STRING);
    	        
    	        // 입력 인자값을 가지고 웹 서비스 호출하여 실행결과 얻어옴
    	        String result = (String)call.invoke(new Object[] {new String( requestParam )}); 
    	        
    	        LOGGER.info("요청 XML {}", hiomsParams.getRiskMasterInfoParam());
    	        
    	        LOGGER.info("결과 XML {}", result);
    	        
    	        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
    	        response = (Response)unmarshaller.unmarshal(new StringReader(result));
    	        
                
                LOGGER.info("성공여부 {}", response.getDataSet().getMessage().getResult());
                LOGGER.info("파일경로 파일명  : {}" , filePathName);
                
                File file = new File(hiomsParams.getFilePath());
    			if(!file.exists()){
    				file.mkdirs();
    			}
    			
                Marshaller m = jaxbContext.createMarshaller();
    			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    			m.marshal(response, new File(filePathName));
    		}
        }catch(JAXBException e){
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}


class Jiguk	 {

	private String id;
	private String name;
	private String type;

	Jiguk(String junmun){
		this.id = junmun.substring(0, 2);
		this.name = junmun.substring(2, 4);
		this.type = junmun.substring(5, 6);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getJunsong() {
		return type;
	}

}

class Text {


	public void test (){
		List<Jiguk> jiguks = new ArrayList<>();


		Jiguk jinguk = new Jiguk("01021312031298391283");

		jinguk.getName();
		jinguk.setName("dfjekfjk");
		jinguk.getJunsong();


	}


}
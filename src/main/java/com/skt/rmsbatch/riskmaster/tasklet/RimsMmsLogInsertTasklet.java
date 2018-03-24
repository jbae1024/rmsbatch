package com.skt.rmsbatch.riskmaster.tasklet;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.skt.rmsbatch.domain.RimsMmsLog;
import com.skt.rmsbatch.domain.RiskMasterInfo;
import com.skt.rmsbatch.propertiespojo.HiomsParams;
import com.skt.rmsbatch.propertiespojo.RiskMasterHiomsParams;
import com.skt.rmsbatch.repositories.InfHistRepository;
import com.skt.rmsbatch.repositories.RimsMmsLogRepository;
import com.skt.rmsbatch.repositories.RiskMasterInfoRepository;
import com.skt.rmsbatch.responsepojo.Response;
import com.skt.rmsbatch.utils.DateUtils;

public class RimsMmsLogInsertTasklet implements Tasklet {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RiskMasterInfoInsertTasklet.class);
	
	@Autowired
	private Environment env;
	
	@Autowired
    private InfHistRepository infHistRepository;
	
	@Autowired
    private RimsMmsLogRepository rimsMmsLogRepository;
	
	@Autowired
	@Qualifier("entityManagerFactoryRims")
	private EntityManager entityManager;
	
	@Autowired
	private DateUtils dateUtils;
	
	private String jobName;
	
	@Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) {
		
		this.jobName = chunkContext.getStepContext().getStepExecution().getJobExecution().getJobInstance().getJobName();
		
		LOGGER.info("{} | RimsMmsLogInsertTasklet Start", jobName);
		
		List<RimsMmsLog> resultList = new ArrayList<RimsMmsLog>();
		
        String q = "SELECT d.actionTime, d.actionTitle, d.actionMemo, d.actor, count(d) "
						+ "FROM MmsHistory d "
						+ "WHERE d.actionTime between :starttime and :endtime "
						+ "GROUP BY d.actionMemo, d.actionTitle, d.actor "
						+ "ORDER BY MIN(d.actionTime)";
        
    	Query query = entityManager.createQuery(q);
    	
    	Date startTime = dateUtils.getMoveDateMidNight(-1);
    	Date endTime = dateUtils.getMoveDateMidNight(0);
    	
    	query.setParameter("starttime", startTime);
    	query.setParameter("endtime", endTime);
    	
    	List<Object[]> list = query.getResultList();
    	
    	for (Object[] row : list) {
    		
    		RimsMmsLog rimsMmsLog = new RimsMmsLog();
    		rimsMmsLog.setActionTime((Date)row[0]);
    		rimsMmsLog.setActionTitle((String)row[1]);
    		rimsMmsLog.setMmsContents((String)row[2]);
    		rimsMmsLog.setActor((String)row[3]);
    		rimsMmsLog.setSendTelCnt((long)row[4]);
    		resultList.add(rimsMmsLog);
    		
    	}
    	
    	rimsMmsLogRepository.save(resultList);
		
		LOGGER.info("{} ------------------------------------ RimsMmsLogInsertTasklet End", jobName);
		
		return RepeatStatus.FINISHED;
	}

}

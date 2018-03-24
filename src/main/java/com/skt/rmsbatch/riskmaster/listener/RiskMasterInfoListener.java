package com.skt.rmsbatch.riskmaster.listener;

import com.skt.rmsbatch.domain.InfHist;
import com.skt.rmsbatch.domain.RimsMmsLog;
import com.skt.rmsbatch.domain.RiskMasterInfo;
import com.skt.rmsbatch.repositories.InfHistRepository;
import com.skt.rmsbatch.repositories.RimsMmsLogRepository;
import com.skt.rmsbatch.repositories.RiskMasterInfoRepository;
import com.skt.rmsbatch.rims.domain.MmsHistory;
import com.skt.rmsbatch.riskmaster.service.HiomsService;
import com.skt.rmsbatch.riskmaster.service.HiomsServiceImpl;
import com.skt.rmsbatch.riskmaster.service.SkServiceImpl;
import com.skt.rmsbatch.utils.DateUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Created by Sanjay on 7/30/2016.
 */

public class RiskMasterInfoListener implements JobExecutionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(RiskMasterInfoListener.class);

    @Autowired
    private RiskMasterInfoRepository riskMasterInfoRepository;
    
    @Autowired
    private RimsMmsLogRepository rimsMmsLogRepository;
    
    @Autowired
    private DateUtils dateUtils;
    
    @Autowired
	@Qualifier("entityManagerFactoryRims")
	private EntityManager entityManager;
    
    
    @Override
    public void beforeJob(JobExecution jobExecution) {
    	
    }

    @Override
    public void afterJob(JobExecution jobExecution) {

//        String input = "sk";
//        HiomsService hs;
//
//        if(input.equals("sk")){
//            hs = new SkServiceImpl();
//        }else{
//            hs = new HiomsServiceImpl();
//        }

        LOGGER.info("RUNNING JobCompletion Check.");

//        System.out.println("쿼리테스트");;
//        
//        List<RimsMmsLog> resultList = new ArrayList<RimsMmsLog>();
//        String q = "SELECT d.actionTime, d.actionTitle, d.actionMemo, d.actor, count(d) "
//						+ "FROM MmsHistory d "
//						+ "WHERE d.actionTime between :starttime and :endtime "
//						+ "GROUP BY d.actionMemo, d.actionTitle, d.actor "
//						+ "ORDER BY MIN(d.actionTime)";
//    	Query query = entityManager.createQuery(q);
//    	Date startTime = dateUtils.getMovedDate(-1);
//    	Date endTime = dateUtils.getMovedDate(0);
//    	query.setParameter("starttime", startTime);
//    	query.setParameter("endtime", endTime);
//    	List<Object[]> list = query.getResultList();
//    	for (Object[] row : list) {
//    		RimsMmsLog rimsMmsLog = new RimsMmsLog();
//    		rimsMmsLog.setActionTime((Date)row[0]);
//    		rimsMmsLog.setActionTitle((String)row[1]);
//    		rimsMmsLog.setMmsContents((String)row[2]);
//    		rimsMmsLog.setActor((String)row[3]);
//    		rimsMmsLog.setSendTelCnt((long)row[4]);
//    		resultList.add(rimsMmsLog);
//    	}
//    	rimsMmsLogRepository.save(resultList);
    	
    	//list.forEach(mmsHistory->LOGGER.info("Title : {}", mmsHistory.getActionTitle()));
    	
    	
//    	System.out.println(dateUtils.getMoveDateMidNight(1));
//    	System.out.println("쿼리테스트");
        
//        List<RiskMasterInfo> riskMasterInfoInDB = new ArrayList<>();
//        
//        riskMasterInfoRepository.findAll().forEach(riskMasterInfoInDB::add);
//        
//        LOGGER.info("Movies stored in Database: {}", riskMasterInfoInDB.size());
//        
//        List<RiskMasterInfo> riskCauseNullInfoInDB = new ArrayList<>();
//        
//        riskMasterInfoRepository.findByRiskCauseAndStartDateBetween("", dateUtils.getMovedDate(-30), dateUtils.getMovedDate(0)).forEach(riskCauseNullInfoInDB::add);
//        
//        //riskMasterInfoRepository.updateRiskCauseByRiskMngNum("바꾼다.", "ER160729-000009");
//        
//        LOGGER.info("riskCauseNullInfoInDB1 : {}", riskCauseNullInfoInDB.size());
//        
//        riskMasterInfoInDB.forEach(riskMasterInfo -> LOGGER.info("Title - {}. Release Data - {}. Language - {}", riskMasterInfo.getRiskMngNum()));
        
//        System.exit(0);
    }
    
    private static void printResult(Object result) {
        if (result == null) {
          System.out.print("NULL");
        } else if (result instanceof Object[]) {
          Object[] row = (Object[]) result;
          System.out.print("[");
          for (int i = 0; i < row.length; i++) {
            printResult(row[i]);
          }
          System.out.print("]");
        } else if (result instanceof Long || result instanceof Double
            || result instanceof String) {
          System.out.print(result.getClass().getName() + ": " + result);
        } else {
          System.out.print(result);
        }
        System.out.println();
      }
    
}

package com.skt.rmsbatch.bootstrap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.skt.rmsbatch.domain.RiskMasterInfo;
import com.skt.rmsbatch.repositories.RiskMasterInfoRepository;
import com.skt.rmsbatch.utils.DateUtils;

import java.math.BigDecimal;
import java.util.Date;

@Component
public class RiskMasterInfoLoader implements ApplicationListener<ContextRefreshedEvent> {

    private RiskMasterInfoRepository riskMasterInfoRepository;

    private Logger log = Logger.getLogger(RiskMasterInfoLoader.class);

    @Autowired
	DateUtils dateUtils;
    
    @Autowired
    public void setProductRepository(RiskMasterInfoRepository riskMasterInfoRepository) {
        this.riskMasterInfoRepository = riskMasterInfoRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
    	
    	System.out.println("부트 리스너");
//    	for(int i=0; i < 10000; i++){
//    		RiskMasterInfo riskMasterInfo = new RiskMasterInfo();
//            riskMasterInfo.setRiskMngNum("ER160729-000"+i);
//            riskMasterInfo.setRiskAddType("P");
//            riskMasterInfo.setStartDate(dateUtils.getMovedDate(-2));
//            riskMasterInfo.setEndDate(dateUtils.getMovedDate(0));
//            riskMasterInfo.setRiskTitle("일부 판매점 스캔등록 메뉴 일부 선택불가 장애 발생 "+i);
//            riskMasterInfo.setRiskContent("일부 판매점 스캔등록 메뉴 일부 선택불가 장애 발생 "+i);
//            riskMasterInfo.setRiskLevel("3");
//            riskMasterInfo.setJobEffect("");
//            riskMasterInfo.setRiskCause("");
//            riskMasterInfo.setCallCnt(2);
//            riskMasterInfo.setRiskMeasure("");
//            riskMasterInfoRepository.save(riskMasterInfo);
//    	}
        
    }
}

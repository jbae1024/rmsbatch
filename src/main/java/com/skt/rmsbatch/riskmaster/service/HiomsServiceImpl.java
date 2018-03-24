package com.skt.rmsbatch.riskmaster.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skt.rmsbatch.domain.InfHist;
import com.skt.rmsbatch.domain.RiskMasterInfo;
import com.skt.rmsbatch.repositories.InfHistRepository;
import com.skt.rmsbatch.repositories.RiskMasterInfoRepository;
import com.skt.rmsbatch.riskmaster.tasklet.RiskMasterInfoInsertTasklet;
import com.skt.rmsbatch.utils.DateUtils;

@Service
public class HiomsServiceImpl implements HiomsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RiskMasterInfoInsertTasklet.class);
	
	@Autowired
    private InfHistRepository infHistRepository;
	
	@Autowired
    private RiskMasterInfoRepository riskMasterInfoRepository;
	
	@Autowired
	DateUtils dateUtils;
	
	/**
	 * 실패한 배치 다시 수집을 위한 read
	 * @return
	 */
	public List<InfHist> readFailRiskMasterInfoList (String periodDay ){
		return infHistRepository.findByJobNameAndResultNotAndExecutionDateBetween("riskMasterInfoJob", "OK", dateUtils.getMoveDateMidNight(-Integer.parseInt(periodDay)), dateUtils.getMovedDate(0));
	}
	
	/**
	 * 세부원이 없는 레코드 List
	 * @return
	 */
	public List<RiskMasterInfo> readRiskCauseIsNullList (String periodDay ){
		return riskMasterInfoRepository.findByRiskCauseAndStartDateBetween("", dateUtils.getMovedDate(-Integer.parseInt(periodDay)), dateUtils.getMovedDate(0));
	}
	
	/**
	 * 배치 결과 메세지 저장
	 */
	public void writeInfHist( InfHist infHist ){
		if(infHist != null){
			infHistRepository.save(infHist);
		}
	}
	
	/**
	 * 세부 원인 업데이트 
	 * @param riskMngNumList
	 */
	public void updateRiskCauseByRiskMngNum( List<Map<String, Object>> riskMngNumList ){
		for(Map<?, ?> riskMngNumMap : riskMngNumList ){
			if(riskMngNumMap.get("riskCause") == null){
				continue;
			}
			LOGGER.info("riskCause.get : {} | {}", riskMngNumMap.get("riskCause"), riskMngNumMap.get("riskMngNum"));
			riskMasterInfoRepository.updateRiskCauseByRiskMngNum((String)riskMngNumMap.get("riskCause"), (String)riskMngNumMap.get("riskMngNum"));
		}
	}
	
	/**
	 * 관리번호 존재 확인
	 * @param riskMngNum
	 * @return
	 */
	public int findByRiskMngNum(String riskMngNum){
		return riskMasterInfoRepository.findByRiskMngNum(riskMngNum).size();
	}
	
}

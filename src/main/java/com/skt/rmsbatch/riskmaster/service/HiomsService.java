package com.skt.rmsbatch.riskmaster.service;

import java.util.List;
import java.util.Map;

import com.skt.rmsbatch.domain.InfHist;
import com.skt.rmsbatch.domain.RiskMasterInfo;

public interface HiomsService {
	
	List<InfHist> readFailRiskMasterInfoList ( String periodDay );
	
	List<RiskMasterInfo> readRiskCauseIsNullList (String periodDay);
	
	void writeInfHist( InfHist infHist );
	
	void updateRiskCauseByRiskMngNum( List<Map<String, Object>> riskMngNumList );
	
	int findByRiskMngNum(String riskMngNum);
	
}

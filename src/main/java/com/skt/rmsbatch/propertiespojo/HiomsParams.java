package com.skt.rmsbatch.propertiespojo;

import java.util.Date;

public interface HiomsParams {
	
	String getSysKey();

	String getSvcId();

	String getDsCd();

	String getStartDate();

	String getPeriodDay();

	String getFilePath();

	String getFileName();

	String getEndDate();
	
	String getRiskMasterInfoParam();
	
	String getRiskCauseParam( String riskMngNum );
	
	String getReRiskMasterInfoParam( Date date );
	
	String getRiskMasterInfoJobResourceUrl();
	
	String getRiskCauseJobResourceUrl( String riskMngNum );
	
	String getReRiskMasterInfoJobResourceUrl( Date date );
	
}
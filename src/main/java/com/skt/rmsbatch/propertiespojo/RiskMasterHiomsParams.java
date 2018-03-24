package com.skt.rmsbatch.propertiespojo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.skt.rmsbatch.responsepojo.Response;
import com.skt.rmsbatch.utils.DateUtils;

public class RiskMasterHiomsParams implements HiomsParams, Serializable{
	
	private String sysKey;
	private String svcId;
	private String dsCd;
	private String startDate;
	private String periodDay;
	private String filePath;
	private String fileName;
	private String endDate;
	private String jobName;
	private String batchDateType;
	
	DateUtils dateUtils;
	
	public RiskMasterHiomsParams(){
	}
	
	public RiskMasterHiomsParams( Environment env, String jobName ){
		
		
		this.dateUtils = new DateUtils();
		
		this.jobName = jobName;
		
		this.sysKey = env.getProperty("himosbatch."+jobName+".sysKey");
		this.svcId = env.getProperty("himosbatch."+jobName+".svcId");
		this.dsCd = env.getProperty("himosbatch."+jobName+".dsCd");
		this.startDate = env.getProperty("himosbatch."+jobName+".startDate");
		this.periodDay = env.getProperty("himosbatch."+jobName+".periodDay");
		this.filePath = env.getProperty("himosbatch."+jobName+".filePath");
		this.fileName = env.getProperty("himosbatch."+jobName+".fileName");
		this.endDate = env.getProperty("himosbatch."+jobName+".endDate");
		this.batchDateType = env.getProperty("himosbatch."+jobName+".batchDateType", "yyyyMMdd");
		
		
	}

	public String getSysKey() {
		return sysKey;
	}

	public void setSysKey(String sysKey) {
		this.sysKey = sysKey;
	}

	public String getSvcId() {
		return svcId;
	}

	public void setSvcId(String svcId) {
		this.svcId = svcId;
	}

	public String getDsCd() {
		return dsCd;
	}

	public void setDsCd(String dsCd) {
		this.dsCd = dsCd;
	}

	public String getStartDate() {
		
		DateUtils dateUtils = new DateUtils();
		if(startDate == null || startDate.equals("")){
			startDate = dateUtils.getMovedDate(-Integer.parseInt(getPeriodDay()), batchDateType);
		}
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getPeriodDay() {
		if(periodDay == null || periodDay.equals("")){
			periodDay = "0";
		}
		return periodDay;
	}

	public void setPeriodDay(String periodDay) {
		this.periodDay = periodDay;
	}

	public String getFilePath() {
		if(filePath == null || filePath.equals("")){
			filePath = "/Users/jinhwancom/sts-bundle/workspace/rmsbatch/src/batchxml/";
		}
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
//		if(fileName == null || fileName.equals("")){
//			fileName = jobName;
//		}
		fileName = jobName;
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getEndDate() {
		
		if(endDate == null || endDate.equals("")){
			endDate = dateUtils.getMovedDate(0, batchDateType); 
		}
		
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public String getRiskMasterInfoParam(){
		
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
				"<request>"+
				"<dataSet>"+
				"	<fields>"+
				"		<sys_key><![CDATA["+getSysKey()+"]]></sys_key>"+
				"		<svc_id><![CDATA["+getSvcId()+"]]></svc_id>"+
				"		<ds_cd><![CDATA["+getDsCd()+"]]></ds_cd>"+
				"		<search_strt_date><![CDATA["+getStartDate()+"]]></search_strt_date>"+
				"		<search_end_date><![CDATA["+getEndDate()+"]]></search_end_date>"+
				"		<trbl_accp_no><![CDATA[E]]></trbl_accp_no>"+
				"	</fields>"+
				"</dataSet>"+
			    "</request>";
		
	}
	
	public String getRiskCauseParam(String riskMngNum){
		
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
				"<request>"+
				"<dataSet>"+
				"	<fields>"+
				"		<sys_key><![CDATA["+getSysKey()+"]]></sys_key>"+
				"		<svc_id><![CDATA["+getSvcId()+"]]></svc_id>"+
				"		<ds_cd><![CDATA["+getDsCd()+"]]></ds_cd>"+
				"		<search_strt_date><![CDATA["+getStartDate()+"]]></search_strt_date>"+
				"		<search_end_date><![CDATA["+getEndDate()+"]]></search_end_date>"+
				"		<trbl_accp_no><![CDATA["+riskMngNum+"]]></trbl_accp_no>"+
				"	</fields>"+
				"</dataSet>"+
			    "</request>";
	}
	
	public String getReRiskMasterInfoParam( Date date ){
		
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
				"<request>"+
				"<dataSet>"+
				"	<fields>"+
				"		<sys_key><![CDATA["+getSysKey()+"]]></sys_key>"+
				"		<svc_id><![CDATA["+getSvcId()+"]]></svc_id>"+
				"		<ds_cd><![CDATA["+getDsCd()+"]]></ds_cd>"+
				"		<search_strt_date><![CDATA["+dateUtils.getMovedDate(-7 , date ,"yyyyMMdd")+"]]></search_strt_date>"+
				"		<search_end_date><![CDATA["+dateUtils.getMovedDate(0 , date ,"yyyyMMdd")+"]]></search_end_date>"+
				"		<trbl_accp_no><![CDATA[E]]></trbl_accp_no>"+
				"	</fields>"+
				"</dataSet>"+
			    "</request>";
		
	}
	
	public String getRiskMasterInfoJobResourceUrl(){
		return getFilePath()+getFileName()+"_"+getStartDate()+"_"+getEndDate()+".xml";
	}
	
	public String getRiskCauseJobResourceUrl( String riskMngNum ){
		return getFilePath()+getFileName()+"_"+riskMngNum+".xml";
	}
	
	public String getReRiskMasterInfoJobResourceUrl( Date date ){
		
		String startDate = dateUtils.getMovedDate(-7 , date ,"yyyyMMdd");
		String endDate = dateUtils.getMovedDate(0 , date ,"yyyyMMdd");
		
		return getFilePath()+getFileName()+"_"+startDate+"_"+endDate+".xml";
	}
	
}
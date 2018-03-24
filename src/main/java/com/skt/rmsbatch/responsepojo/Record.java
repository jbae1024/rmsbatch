package com.skt.rmsbatch.responsepojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "record")
@XmlAccessorType(XmlAccessType.FIELD)
public class Record {
	
	@XmlElement(name = "trbl_accp_no")
	private String riskMngNum;
	
	@XmlElement(name = "real_trbl_ocrn_dtm")
	private String startDate;
	
	@XmlElement(name = "real_trbl_sltn_dtm")
	private String endDate;
	
	@XmlElement(name = "trbl_titl_cntn")
	private String riskTitle;
	
	@XmlElement(name = "trbl_detl_cntn")
	private String riskContent;
	
	@XmlElement(name = "secr_ds_cd")
	private String riskLevel;
	
	@XmlElement(name = "trbl_ifnc_scp_cntn")
	private String jobEffect;
	
	@XmlElement(name = "trbl_prtc_caus_cntn")
	private String riskCause;
	
	@XmlElement(name = "trbl_accp_no_cnt")
	private String callCnt;
	
	@XmlElement(name = "trbl_msr_bdwn")
	private String riskMeasure;
    
	
	public String getRiskMngNum() {
		return riskMngNum;
	}
	public void setRiskMngNum(String riskMngNum) {
		this.riskMngNum = riskMngNum;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getRiskTitle() {
		return riskTitle;
	}
	public void setRiskTitle(String riskTitle) {
		this.riskTitle = riskTitle;
	}
	public String getRiskContent() {
		return riskContent;
	}
	public void setRiskContent(String riskContent) {
		this.riskContent = riskContent;
	}
	public String getRiskLevel() {
		return riskLevel;
	}
	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}
	public String getJobEffect() {
		return jobEffect;
	}
	public void setJobEffect(String jobEffect) {
		this.jobEffect = jobEffect;
	}
	public String getRiskCause() {
		return riskCause;
	}
	public void setRiskCause(String riskCause) {
		this.riskCause = riskCause;
	}
	public String getCallCnt() {
		return callCnt;
	}
	public void setCallCnt(String callCnt) {
		this.callCnt = callCnt;
	}
	public String getRiskMeasure() {
		return riskMeasure;
	}
	public void setRiskMeasure(String riskMeasure) {
		this.riskMeasure = riskMeasure;
	}
	@Override
	public String toString() {
		return "Record [riskMngNum=" + riskMngNum + ", startDate=" + startDate + ", endDate=" + endDate + ", riskTitle="
				+ riskTitle + ", riskContent=" + riskContent + ", riskLevel=" + riskLevel + ", jobEffect=" + jobEffect
				+ ", riskCause=" + riskCause + ", callCnt=" + callCnt + ", riskMeasure=" + riskMeasure + "]";
	}
}

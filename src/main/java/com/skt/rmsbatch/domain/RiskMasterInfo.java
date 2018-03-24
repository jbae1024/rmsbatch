package com.skt.rmsbatch.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class RiskMasterInfo extends AbstractBaseEntity implements Serializable{
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
    private long id;
	
	@Id
	@Column(name = "risk_mng_num")
	private String riskMngNum;
	
	private String riskAddType;
	
	@Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date startDate;
	
	@Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date endDate;
	
	private String riskTitle;
	
	private String riskContent;
	
	private String riskLevel;
	
	private String jobEffect;
	
	private String riskCause;
	
	private int callCnt;
	
	private String riskMeasure;
	
	public RiskMasterInfo(){}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRiskMngNum() {
		return riskMngNum;
	}

	public void setRiskMngNum(String riskMngNum) {
		this.riskMngNum = riskMngNum;
	}

	public String getRiskAddType() {
		return riskAddType;
	}

	public void setRiskAddType(String riskAddType) {
		this.riskAddType = riskAddType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
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

	public int getCallCnt() {
		return callCnt;
	}

	public void setCallCnt(int callCnt) {
		this.callCnt = callCnt;
	}

	public String getRiskMeasure() {
		return riskMeasure;
	}

	public void setRiskMeasure(String riskMeasure) {
		this.riskMeasure = riskMeasure;
	}
	
}

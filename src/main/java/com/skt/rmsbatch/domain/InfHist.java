package com.skt.rmsbatch.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class InfHist {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
    private long id;
	
	private String result;
	
	private String messageId;
	
	private String messageName;
	
	private String messageReason;
	
	private String messageRemark;
	
	@Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date executionDate;
	
	private String jobName;
    
	public InfHist(){
	}
	
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "infhist_riskmasterinfo",
    joinColumns = @JoinColumn(name = "infhist_id"),
    inverseJoinColumns = @JoinColumn(name = "risk_mng_num"))
	List<RiskMasterInfo> riskMasterInfo = new ArrayList<RiskMasterInfo>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getMessageName() {
		return messageName;
	}

	public void setMessageName(String messageName) {
		this.messageName = messageName;
	}

	public String getMessageReason() {
		return messageReason;
	}

	public void setMessageReason(String messageReason) {
		this.messageReason = messageReason;
	}

	public String getMessageRemark() {
		return messageRemark;
	}

	public void setMessageRemark(String messageRemark) {
		this.messageRemark = messageRemark;
	}
	
	public Date getExecutionDate() {
		return executionDate;
	}

	public void setExecutionDate(Date executionDate) {
		this.executionDate = executionDate;
	}
	
	public List<RiskMasterInfo> getRiskMasterInfo() {
		return riskMasterInfo;
	}

	public void setRiskMasterInfo(List<RiskMasterInfo> riskMasterInfo) {
		this.riskMasterInfo = riskMasterInfo;
	}
	
}

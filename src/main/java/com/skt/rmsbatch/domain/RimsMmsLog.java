package com.skt.rmsbatch.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class RimsMmsLog {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="mms_mng_num")
    private long mmsMngNum;
	
	@Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date actionTime;
	
	private String actionTitle;
	
	private String mmsContents;
	
	private String actor;
	
	private long sendTelCnt;

	public long getMmsMngNum() {
		return mmsMngNum;
	}

	public void setMmsMngNum(long mmsMngNum) {
		this.mmsMngNum = mmsMngNum;
	}

	public Date getActionTime() {
		return actionTime;
	}

	public void setActionTime(Date actionTime) {
		this.actionTime = actionTime;
	}

	public String getActionTitle() {
		return actionTitle;
	}

	public void setActionTitle(String actionTitle) {
		this.actionTitle = actionTitle;
	}

	public String getMmsContents() {
		return mmsContents;
	}

	public void setMmsContents(String mmsContents) {
		this.mmsContents = mmsContents;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public long getSendTelCnt() {
		return sendTelCnt;
	}

	public void setSendTelCnt(long sendTelCnt) {
		this.sendTelCnt = sendTelCnt;
	}
    
}

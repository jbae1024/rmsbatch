package com.skt.rmsbatch.rims.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="mms_history")
public class MmsHistory {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="mms_mng_num")
    private long mmsMngNum;
	
	@Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@Column(name="action_time")
	private Date actionTime;
	
	@Column(name="action_title")
	private String actionTitle;
	
	@Column(name="action_memo")
	private String actionMemo;
	
	@Column(name="actor")
	private String actor;
	
	@Column(name="send_tel")
	private int sendTel;

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

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public int getSendTel() {
		return sendTel;
	}

	public void setSendTel(int sendTel) {
		this.sendTel = sendTel;
	}

	public String getActionMemo() {
		return actionMemo;
	}

	public void setActionMemo(String actionMemo) {
		this.actionMemo = actionMemo;
	}
	
}

package com.skt.rmsbatch.responsepojo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "message")
public class Message {
	private String result;
	private String messageId;
	private String messageName;
	private String messageReason;
	private String messageRemark;
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
	
}

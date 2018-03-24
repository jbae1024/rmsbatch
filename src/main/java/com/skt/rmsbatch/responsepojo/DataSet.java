package com.skt.rmsbatch.responsepojo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "dataSet")
public class DataSet {
	
	private Message message;
	
	private RecordSet recordSet;
	
	public Message getMessage() {
		return message;
	}
	
	public void setMessage(Message message) {
		this.message = message;
	}
	
	public RecordSet getRecordSet() {
		return recordSet;
	}
	
	public void setRecordSet(RecordSet recordSet) {
		this.recordSet = recordSet;
	}
}

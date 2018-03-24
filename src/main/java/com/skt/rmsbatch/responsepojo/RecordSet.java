package com.skt.rmsbatch.responsepojo;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "recordSet")
public class RecordSet {
	
	private List<Record> record;

	public List<Record> getRecord() {
		return record;
	}

	public void setRecord(List<Record> record) {
		this.record = record;
	}
	
}

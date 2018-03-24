package com.skt.rmsbatch.responsepojo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "response")
public class Response {
 
	private DataSet dataSet;
	
	public DataSet getDataSet() {
		return dataSet;
	}

	public void setDataSet(DataSet dataSet) {
		this.dataSet = dataSet;
	}
	
}

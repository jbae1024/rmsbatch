package com.skt.rmsbatch.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.jboss.logging.annotations.Property;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@XmlRootElement(name = "fields")
public class Fields {
	
	private String sysKey;
	private String sysId;
	private String dsCd;
	private String searchDate;
	private String trblAccpNo;
	
	@XmlElement(name = "sys_key")
	public String getSysKey() {
		return sysKey;
	}
	
	public void setSysKey(String sysKey) {
		this.sysKey = sysKey;
	}
	@XmlElement(name = "sys_id")
	public String getSysId() {
		return sysId;
	}
	public void setSysId(String sysId) {
		this.sysId = sysId;
	}
	@XmlElement(name = "ds_cd")
	public String getDsCd() {
		return dsCd;
	}
	public void setDsCd(String dsCd) {
		this.dsCd = dsCd;
	}
	@XmlElement(name = "search_date")
	public String getSearchDate() {
		return searchDate;
	}
	public void setSearchDate(String searchDate) {
		this.searchDate = searchDate;
	}
	@XmlElement(name = "trbl_accp_no")
	public String getTrblAccpNo() {
		return trblAccpNo;
	}
	public void setTrblAccpNo(String trblAccpNo) {
		this.trblAccpNo = trblAccpNo;
	}
	
}

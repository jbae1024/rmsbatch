package com.skt.rmsbatch.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 공통 컬럼을 위한 상속 Entity
 * @author jinhwancom
 *
 */
@MappedSuperclass
public abstract class AbstractBaseEntity implements Serializable{
	
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "rgst_date", nullable = true)
    private Date rgstDate;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "updt_date", nullable = true)
    private Date updtDate;

    @Column(name = "rgst_id", nullable = true)
    private String rgstId;
    
    @Column(name = "updt_id", nullable = true)
    private String updtId;
    
    @PrePersist
    protected void onCreate() {
    	updtId = rgstId = "batch";
    	updtDate = rgstDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
    	updtId = "batch";
    	updtDate = new Date();
    }

	public Date getRgstDate() {
		
		return rgstDate;
	}

	public void setRgstDate(Date rgstDate) {
		this.rgstDate = rgstDate;
	}

	public Date getUpdtDate() {
		return updtDate;
	}

	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

	public String getRgstId() {
		return rgstId;
	}

	public void setRgstId(String rgstId) {
		this.rgstId = rgstId;
	}

	public String getUpdtId() {
		return updtId;
	}

	public void setUpdtId(String updtId) {
		this.updtId = updtId;
	}
    
}

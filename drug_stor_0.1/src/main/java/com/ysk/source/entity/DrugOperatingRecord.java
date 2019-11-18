package com.ysk.source.entity;

/**
 * 药品操作记录表--实体类
 * 
 * @author admin
 *
 */
public class DrugOperatingRecord {

	private String drugOperatingId;

	private String operatingUserId;

	private String operatingRecord;

	private String operatingTime;

	public String getDrugOperatingId() {
		return drugOperatingId;
	}

	public void setDrugOperatingId(String drugOperatingId) {
		this.drugOperatingId = drugOperatingId;
	}

	public String getOperatingUserId() {
		return operatingUserId;
	}

	public void setOperatingUserId(String operatingUserId) {
		this.operatingUserId = operatingUserId;
	}

	public String getOperatingRecord() {
		return operatingRecord;
	}

	public void setOperatingRecord(String operatingRecord) {
		this.operatingRecord = operatingRecord;
	}

	public String getOperatingTime() {
		return operatingTime;
	}

	public void setOperatingTime(String operatingTime) {
		this.operatingTime = operatingTime;
	}
}
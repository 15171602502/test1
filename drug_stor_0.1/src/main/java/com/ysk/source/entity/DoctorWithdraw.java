package com.ysk.source.entity;

/**
 * 医生提现记录表--实体类
 * 
 * @author admin
 *
 */
public class DoctorWithdraw {

	private String recordId;

	private String doctorId;

	private Integer withdrawalAmount;

	private String withdrawalTime;

	private Integer withdrawalType;

	private String accountName;

	private String backupData;

	private String backupText;

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public Integer getWithdrawalAmount() {
		return withdrawalAmount;
	}

	public void setWithdrawalAmount(Integer withdrawalAmount) {
		this.withdrawalAmount = withdrawalAmount;
	}

	public String getWithdrawalTime() {
		return withdrawalTime;
	}

	public void setWithdrawalTime(String withdrawalTime) {
		this.withdrawalTime = withdrawalTime;
	}

	public Integer getWithdrawalType() {
		return withdrawalType;
	}

	public void setWithdrawalType(Integer withdrawalType) {
		this.withdrawalType = withdrawalType;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getBackupData() {
		return backupData;
	}

	public void setBackupData(String backupData) {
		this.backupData = backupData;
	}

	public String getBackupText() {
		return backupText;
	}

	public void setBackupText(String backupText) {
		this.backupText = backupText;
	}
}
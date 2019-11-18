package com.ysk.source.entity;

import java.util.List;

/**
 * 订单表--实体类
 * 
 * @author admin
 *
 */
public class Order {

	private String orderId;

	private String userId;

	private Integer orderState;

	private String submitTime;

	private String finishTime;

	private Integer totalMoney;

	private String prescription;

	private Integer userDlt;

	private String backupData;

	private String backupText;
	// 购买商品列表
	private List<OrderDetails> drugList;
	// 药店信息
	private PharmacyInformation pharmacyInfo;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getOrderState() {
		return orderState;
	}

	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}

	public String getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}

	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

	public Integer getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Integer totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getPrescription() {
		return prescription;
	}

	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}

	public Integer getUserDlt() {
		return userDlt;
	}

	public void setUserDlt(Integer userDlt) {
		this.userDlt = userDlt;
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

	public List<OrderDetails> getDrugList() {
		return drugList;
	}

	public void setDrugList(List<OrderDetails> drugList) {
		this.drugList = drugList;
	}

	public PharmacyInformation getPharmacyInfo() {
		return pharmacyInfo;
	}

	public void setPharmacyInfo(PharmacyInformation pharmacyInfo) {
		this.pharmacyInfo = pharmacyInfo;
	}

}
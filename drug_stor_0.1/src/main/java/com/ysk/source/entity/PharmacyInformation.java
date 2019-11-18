package com.ysk.source.entity;

import java.util.List;

/**
 * 药店信息表--实体类
 * 
 * @author admin
 *
 */
public class PharmacyInformation {

	private String pharmacyId;

	private String pharmacyName;

	private String backgroundPicture;

	private String pharmacyIntroduce;

	private String contactMan;

	private String contactMobile;

	private String pharmacyAddress;

	private String province;

	private String city;

	private String area;

	private Float longitude;

	private Float latitude;

	private String addTime;

	private String account;

	private String passWord;

	private String businessHours;
	// 用户关注状态：0未关注，1关注
	private Integer attentionState = 0;

	private List<DrugInformation> drugList;

	private String backupData;

	private String backupText;

	public String getPharmacyId() {
		return pharmacyId;
	}

	public void setPharmacyId(String pharmacyId) {
		this.pharmacyId = pharmacyId;
	}

	public String getPharmacyName() {
		return pharmacyName;
	}

	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}

	public String getBackgroundPicture() {
		return backgroundPicture;
	}

	public void setBackgroundPicture(String backgroundPicture) {
		this.backgroundPicture = backgroundPicture;
	}

	public String getPharmacyIntroduce() {
		return pharmacyIntroduce;
	}

	public void setPharmacyIntroduce(String pharmacyIntroduce) {
		this.pharmacyIntroduce = pharmacyIntroduce;
	}

	public String getContactMan() {
		return contactMan;
	}

	public void setContactMan(String contactMan) {
		this.contactMan = contactMan;
	}

	public String getContactMobile() {
		return contactMobile;
	}

	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}

	public String getPharmacyAddress() {
		return pharmacyAddress;
	}

	public void setPharmacyAddress(String pharmacyAddress) {
		this.pharmacyAddress = pharmacyAddress;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
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

	public String getBusinessHours() {
		return businessHours;
	}

	public void setBusinessHours(String businessHours) {
		this.businessHours = businessHours;
	}

	public Integer getAttentionState() {
		return attentionState;
	}

	public void setAttentionState(Integer attentionState) {
		this.attentionState = attentionState;
	}

	public List<DrugInformation> getDrugList() {
		return drugList;
	}

	public void setDrugList(List<DrugInformation> drugList) {
		this.drugList = drugList;
	}

}
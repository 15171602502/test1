package com.ysk.source.entity;

/**
 * 用户信息表--实体类
 * 
 * @author admin
 *
 */
public class UserInfo {

	private String userId;

	private String phone;

	private String nickName;

	private Integer sex;

	private String birthday;

	private String headPortrait;

	private Integer userType;

	private String invitationCode;

	private String referrer;

	private String addTime;

	private String alipay;

	private String backupData;

	private String backupText;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getHeadPortrait() {
		return headPortrait;
	}

	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getInvitationCode() {
		return invitationCode;
	}

	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}

	public String getReferrer() {
		return referrer;
	}

	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getAlipay() {
		return alipay;
	}

	public void setAlipay(String alipay) {
		this.alipay = alipay;
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
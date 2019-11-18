package com.ysk.source.entity;

/**
 * 登录日志表--实体类
 * 
 * @author admin
 *
 */
public class LoginLog {

	private String logId;

	private String userId;

	private String loginTime;

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
}
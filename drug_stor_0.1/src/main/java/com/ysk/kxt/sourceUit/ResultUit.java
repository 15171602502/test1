package com.ysk.kxt.sourceUit;

public class ResultUit {
	private String resultCode;
	private String resultMsg;
	private Object pageData;

	public ResultUit() {
	}

	public ResultUit(String resultCode, String resultMsg) {
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
	}

	public ResultUit(String resultCode, String resultMsg, Object pageData) {
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
		this.pageData = pageData;
	}

	/**
	 * 默认的成功
	 * 
	 * @return
	 */
	public ResultUit success() {
		return new ResultUit(Status.SUCCESS.resultCode, Status.SUCCESS.resultMsg);
	}

	/**
	 * 默认的失败
	 * 
	 * @return
	 */
	public ResultUit error() {
		return new ResultUit(Status.FAIL.resultCode, Status.FAIL.resultMsg);
	}

	/**
	 * 成功 + 返回的成功信息
	 * 
	 * @param data
	 * @return
	 */
	public ResultUit sussess(Object data) {
		return new ResultUit(Status.SUCCESS.resultCode, Status.SUCCESS.resultMsg, data);
	}

	public enum Status {
		SUCCESS("10000", "请求成功"), FAIL("10010", "请求失败");

		private String resultCode;
		private String resultMsg;

		Status(String resultCode, String resultMsg) {
			this.resultCode = resultCode;
			this.resultMsg = resultMsg;
		}
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public Object getPageData() {
		return pageData;
	}

	public void setPageData(Object pageData) {
		this.pageData = pageData;
	}
	
	
	
	

}

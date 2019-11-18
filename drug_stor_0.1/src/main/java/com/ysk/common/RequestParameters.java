package com.ysk.common;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ysk.kxt.util.string.CharTools;


public class RequestParameters {
	
	private HttpServletRequest request;
	
	private Map<String, Object> parameters = new HashMap<String, Object>();
	
	public RequestParameters(HttpServletRequest request) {
		this.request = request;
		init();
	}
	
	public void init() {
		Enumeration<String> en = request.getParameterNames();
		String name;
		String value;
		
		while (en.hasMoreElements()) {
			name = en.nextElement();
			value = getValues(request.getParameterValues(name));
			//转义查询参数
			parameters.put(name, value);
		}
	}
	
	public String getValues(String[] tmp) {
		if (tmp == null) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < tmp.length; i++) {
			if (i == tmp.length - 1)
				sb.append(tmp[i].toString());
			else {
				sb.append(tmp[i].toString() + ",");
			}
		}
		/*return CharTools.Utf8URLdecode(sb.toString()); */
		return CharTools.Utf8URLdecode(sb.toString());
	}
	
	public Map<String, Object> getParameters() {
		return parameters;
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}
	
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
}

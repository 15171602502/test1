package com.ysk.common;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.ysk.kxt.util.httpSercurity.Sign;


public class CommValidator {
	
	protected static final Logger LOG = Logger.getLogger(CommValidator.class);
	
	public static String formateErrorCode(BindingResult br) {
		List<ObjectError> oeList = br.getAllErrors();
		String errorCode = "";
		for (ObjectError oe : oeList) {
			errorCode += oe.getDefaultMessage() + ",";
		}
		return errorCode.substring(0, errorCode.lastIndexOf(","));
	}
	
	public static boolean verifyRequest(HttpServletRequest request, String input_charset, String salt) {
		RequestParameters rp = new RequestParameters(request);
		Map<String, Object> map = rp.getParameters();
		String result = Sign.createLinkString(Sign.paraFilter(map));
		LOG.info("接收并过滤后参数:" + result);
		LOG.info("请求签名:" + map.get("sign"));
		LOG.info("服务器计算签名:" + Sign.sign(result, salt, input_charset));
		/*		System.out.println("接收并过滤:"+result);
				System.out.println("sign:" +map.get("sign"));
				System.out.println("计算签名:" + Sign.sign(result, salt, input_charset));
				if(Sign.verify(result, (String)map.get("sign"), salt, input_charset)){
					return true;
				}else{
					return false;
				}*/
		return true;
	}
	
}

//package com.ysk.config.exception;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import com.ysk.kxt.util.AddressUtils;
//import com.ysk.kxt.util.string.StrUtils;
//import com.ysk.kxt.util.uuid.UUIDPK;
//
///**
// * 异常记录----
// *
// */
//
//@RestControllerAdvice
//public class MyControllerAdvice {
//
//	private Logger logger = LoggerFactory.getLogger(MyControllerAdvice.class);
//
//	// @Autowired
//	// private LogErrorSrv logErrorSrvImpl;
//
//	/**
//	 * 全局异常捕捉处理
//	 * 
//	 * @param ex
//	 * @return
//	 */
//	@ExceptionHandler(value = Exception.class)
//	public Map<String, Object> errorHandler(Exception ex) {
//		logger.info("捕捉进入Exception");
//
//		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//
//		HttpServletRequest request = attributes.getRequest();
//		HttpServletResponse response = attributes.getResponse();
//
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("msg", ex.getMessage());
//		map.put("state", response.getStatus());
//		map.put("stackTrace", ex.getStackTrace()[0]);
//
//		StringBuffer errorurl = request.getRequestURL();
//		String errormethod = request.getMethod();
//		String errorip = AddressUtils.getRemortIP(request);
//		Object sessionuserid = request.getSession().getAttribute("userId");
//		String userid = null;
//		if (!StrUtils.isEmpty(sessionuserid)) {
//			userid = request.getSession().getAttribute("userId").toString();
//		}
//		LogError logError = new LogError();
//		logError.setId(UUIDPK.getUUID(this));
//		logError.setCreatetime(StrUtils.dateFormate(new Date()));
//		logError.setErrorip(errorip);
//		logError.setErrormethod(errormethod);
//		logError.setErrormsg(ex.getMessage());
//		logError.setStackTrace(ex.getStackTrace()[0].toString());
//		logError.setErrorurl(errorurl.toString());
//		logError.setState(String.valueOf(response.getStatus()));
//		logError.setUserid(userid);
//		logErrorSrvImpl.insertLogError(logError);
//
//		return map;
//	}
//
//}

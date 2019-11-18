//package com.ysk.config.aspectLog;
//
//import java.util.Arrays;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
///**
// * @Description $
// * @author DengJin
// * @date 2017年12月19日
// *
// */
//@Aspect
//@Component
//public class AspectLog {
//	
//	private Logger logger = LoggerFactory.getLogger(AspectLog.class);
//	
//	@Pointcut("@annotation(com.ysk.config.aspectLog.Log)")
//	private void cut() {
//	}
//	
//	@Before("cut()")
//	public void advice(JoinPoint point) {
//		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//		
//		HttpServletRequest request = attributes.getRequest();
//		
//		//URL
//		logger.warn("URL={}", request.getRequestURL());
//		
//		//Method
//		logger.warn("Method={}", request.getMethod());
//		
//		//IP
//		logger.warn("IP={}", request.getLocalPort());
//		System.out.println("@Before：参数为：" + Arrays.toString(point.getArgs()));
//		
//	}
//}

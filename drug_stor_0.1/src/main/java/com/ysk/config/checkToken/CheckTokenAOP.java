//package com.ysk.config.checkToken;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.stereotype.Component;
//
///**
// * @Description $
// * @author DengJin
// * @date 2018年3月19日
// *
// */
//@Aspect
//@Component
//public class CheckTokenAOP {
//	
//	@Autowired
//	private StringRedisTemplate redisTemplate;
//	
//	private static Logger logger = LoggerFactory.getLogger(CheckTokenAOP.class);
//	
//	@Pointcut("@annotation(com.ysk.config.checkToken.CheckToken)")
//	private void cut() {
//		logger.info("-----------------------定义checkToken的切面");
//		
//	}
//	
//	@Before(value = "cut()")
//	private void advice(JoinPoint joinPoint) throws Throwable {
//		Object[] args = joinPoint.getArgs();
//		String token = String.valueOf(args[args.length - 1]);
//		
//		Boolean isMember = redisTemplate.opsForSet().isMember("token", token);
//		logger.info("------------------token:" + token + ",检查结果" + isMember);
//		
//		if (isMember) {
//			
//		} else {
//			Map<String, Object> map = new HashMap<>();
//			map.put("error", "该用户未登录");
//			throw new RuntimeException("该用户未登录");
//		}
//		
//	}
//	
//}

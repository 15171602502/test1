package com.ysk.config.servlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import com.alibaba.druid.support.http.StatViewServlet;

@WebServlet(urlPatterns = "/druid/*", //通过哪个url访问
		initParams = { @WebInitParam(name = "loginUsername", value = "root"), //用户名
				@WebInitParam(name = "loginPassword", value = "root"), //密码
				@WebInitParam(name = "resetEnable", value = "true"),//是否可以重置
		// @WebInitParam(name = "allow",value = "127.0.0.1"),//白名单
		// @WebInitParam(name = "deny",value = "192.168.1.1")//黑名单
		})
public class DruidStatViewServlet extends StatViewServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
package com.ysk.config.servlet;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import com.alibaba.druid.support.http.WebStatFilter;

@WebFilter(filterName = "druidStatFilter", //过滤器名称
		urlPatterns = "/*", //需要拦截的url
		initParams = { //filter初始化信息
				//需要忽略的资源
				@WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,"
						+ "*.bmp,*.png,*.css,*.ico,/druid/*")/*,
																@WebInitParam(name = "sessionStatEnable", value = "true"),
																@WebInitParam(name = "profileEnable", value = "true")*/ })
public class DruidStatFilter extends WebStatFilter {
}
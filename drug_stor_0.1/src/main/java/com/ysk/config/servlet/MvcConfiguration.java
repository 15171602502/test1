package com.ysk.config.servlet;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfiguration extends WebMvcConfigurerAdapter {

	// private String
	// mImagesPath=UploadConstant.RESOURCES_URL+UploadConstant.IMAGES_USER;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/image/");
		registry.addResourceHandler("/user/images/**").addResourceLocations("file:C://gene/user/images/");

		registry.addResourceHandler("/gl/**").addResourceLocations("classpath:/static/gl/");
		registry.addResourceHandler("/pb/**").addResourceLocations("classpath:/static/pb/");
		registry.addResourceHandler("/mb/**").addResourceLocations("classpath:/static/mb/");
		registry.addResourceHandler("/zz/**").addResourceLocations("classpath:/static/zz/");
		registry.addResourceHandler("/gh/**").addResourceLocations("classpath:/static/gh/");
		super.addResourceHandlers(registry);
	}

}

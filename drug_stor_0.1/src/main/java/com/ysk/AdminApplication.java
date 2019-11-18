package com.ysk;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.ysk.kxt.util.SpringUtils;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ServletComponentScan
@EnableAsync
@EnableScheduling
@EnableAutoConfiguration
@Import(value = { SpringUtils.class })
public class AdminApplication extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AdminApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}
	
	@Bean
	public HttpMessageConverters fastJsonHttpMessageConverters() { //
		// 创建封装对象
		FastJsonHttpMessageConverter fastJsonConverter = new FastJsonHttpMessageConverter();
		
		// 创建配置文件对象
		FastJsonConfig config = new FastJsonConfig();
		config.setSerializerFeatures(SerializerFeature.PrettyFormat, // 格式化
				SerializerFeature.WriteMapNullValue, // value为空时，key也显示
				SerializerFeature.WriteNullStringAsEmpty, // 将string类型的null，修改成“”
				SerializerFeature.WriteNullListAsEmpty // list 长度为空时显示[]
		);
		fastJsonConverter.setFastJsonConfig(config);
		
		// 防止中文乱码
		List<MediaType> fastMediaType = new ArrayList<>();
		fastMediaType.add(MediaType.APPLICATION_JSON_UTF8);
		fastJsonConverter.setSupportedMediaTypes(fastMediaType);
		
		return new HttpMessageConverters(fastJsonConverter);
	}
	
}

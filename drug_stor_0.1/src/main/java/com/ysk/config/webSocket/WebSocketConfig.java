package com.ysk.config.webSocket;

import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

//@Component
public class WebSocketConfig implements WebSocketConfigurer {
	
	/* (non-Javadoc)
	 * @param registry
	 * @see org.springframework.web.socket.config.annotation.WebSocketConfigurer#registerWebSocketHandlers(org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry)
	 */
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		//指定counterHandler处理路径为/counter 的长连接请求
		registry.addHandler(counterHandler(), "/counter")
				//添加WebSocket握手请求的拦截器.
				.addInterceptors(new CounterHandler.CountHandshakeInterceptor());
	}
	
	@Bean
	public CounterHandler counterHandler() {
		return new CounterHandler();
	}
}

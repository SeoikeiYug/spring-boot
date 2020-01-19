package com.genius.spring.boot.security.config;

import com.genius.spring.boot.security.common.Status;
import com.genius.spring.boot.security.util.ResponseUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * @类名 SecurityHandlerConfig
 * @描述 Security 结果处理配置
 * @作者 shuaiqi
 * @创建日期 2020/1/19 10:21
 * @版本号 1.0
 * @参考地址
 **/
@Configuration
public class SecurityHandlerConfig {

	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return ((request, response, accessDeniedException) -> ResponseUtil.renderJson(response, Status.ACCESS_DENIED, null));
	}

}
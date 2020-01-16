package com.genius.spring.boot.session.config;

import com.genius.spring.boot.session.interceptor.SessionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @类名 WebMvcConfig
 * @描述 WebMvc 配置类
 * @作者 shuaiqi
 * @创建日期 2020/1/16 15:20
 * @版本号 1.0
 * @参考地址
 **/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	private SessionInterceptor sessionInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		final InterceptorRegistration interceptorRegistration = registry.addInterceptor(sessionInterceptor);
		//排除不需要拦截的路径
		interceptorRegistration.excludePathPatterns("/page/login");
		interceptorRegistration.excludePathPatterns("/page/doLogin");
		interceptorRegistration.excludePathPatterns("/error");

		//需要拦截的路径
		interceptorRegistration.addPathPatterns("/**");
	}
}
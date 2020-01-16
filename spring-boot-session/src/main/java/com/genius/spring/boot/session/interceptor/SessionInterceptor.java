package com.genius.spring.boot.session.interceptor;

import com.genius.spring.boot.session.common.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.io.IOException;

/**
 * @类名 SessionInterceptor
 * @描述 校验Session的拦截器
 * @作者 shuaiqi
 * @创建日期 2020/1/16 15:24
 * @版本号 1.0
 * @参考地址
 **/
@Component
public class SessionInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
		final HttpSession session = request.getSession();
		if (session.getAttribute(Constants.SESSION_KEY) != null) {
			return true;
		}
		//跳转到登录页
		String url = "/page/login?redirect=true";
		response.sendRedirect(request.getContextPath() + url);
		return false;
	}

}
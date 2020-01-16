package com.genius.spring.boot.security.util;

import cn.hutool.core.util.ObjectUtil;
import com.genius.spring.boot.security.common.Constants;
import com.genius.spring.boot.security.vo.UserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @类名 SecurityUtil
 * @描述 Spring Security工具类
 * @作者 shuaiqi
 * @创建日期 2020/1/16 14:30
 * @版本号 1.0
 * @参考地址
 **/
public class SecurityUtil {

	/**
	 * 获取当前登录用户用户名
	 *
	 * @return 当前登录用户用户名
	 */
	public static String getCurrentUsername() {
		UserPrincipal currentUser = getCurrentUser();
		return ObjectUtil.isNull(currentUser) ? Constants.ANONYMOUS_NAME : currentUser.getUsername();
	}

	/**
	 * 获取当前登录用户信息
	 *
	 * @return 当前登录用户信息，匿名登录时，为null
	 */
	public static UserPrincipal getCurrentUser() {
		Object userInfo = SecurityContextHolder.getContext()
				.getAuthentication()
				.getPrincipal();
		if (userInfo instanceof UserDetails) {
			return (UserPrincipal) userInfo;
		}
		return null;
	}

}
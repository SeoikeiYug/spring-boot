package com.genius.spring.boot.security.config;

import cn.hutool.core.util.StrUtil;
import com.genius.spring.boot.security.common.Constants;
import com.genius.spring.boot.security.common.Status;
import com.genius.spring.boot.security.dao.PermissionDao;
import com.genius.spring.boot.security.dao.RoleDao;
import com.genius.spring.boot.security.expection.SecurityException;
import com.genius.spring.boot.security.model.Permission;
import com.genius.spring.boot.security.model.Role;
import com.genius.spring.boot.security.vo.UserPrincipal;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @类名 RbacAuthorityService
 * @描述 TODO
 * @作者 shuaiqi
 * @创建日期 2020/1/17 10:43
 * @版本号 1.0
 * @参考地址
 **/
@Component
public class RbacAuthorityService {

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private PermissionDao permissionDao;

	@Autowired
	private RequestMappingHandlerMapping mapping;

	public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
		checkRequest(request);

		final Object userInfo = authentication.getPrincipal();
		boolean hasPermission = false;

		if (userInfo instanceof UserDetails) {
			UserPrincipal principal = (UserPrincipal) userInfo;
			final Long userId = principal.getId();

			final List<Role> roles = roleDao.selectByUserId(userId);
			final List<Long> roleIds = roles.stream().map(Role::getId).collect(Collectors.toList());
			final List<Permission> permissions = permissionDao.selectByRoleIdList(roleIds);

			//获取资源，前后端分离，所有过滤页面权限，只保留按钮权限
			final List<Permission> btnPerms = permissions.stream()
					//过滤页面权限
					.filter(permission -> Objects.equals(permission.getType(), Constants.BUTTON))
					//过滤 URL 为空
					.filter(permission -> StrUtil.isNotBlank(permission.getUrl()))
					//过滤method为空
					.filter(permission -> StrUtil.isNotBlank(permission.getMethod()))
					.collect(Collectors.toList());

			for (Permission btnPerm : btnPerms) {
				final AntPathRequestMatcher antPathRequestMatcher = new AntPathRequestMatcher(btnPerm.getUrl(), btnPerm.getMethod());
				if (antPathRequestMatcher.matches(request)) {
					hasPermission = true;
					break;
				}
			}

			return hasPermission;
		} else {
			return false;
		}
	}

	/**
	 * 校验请求是否存在
	 * @param request 请求
	 */
	private void checkRequest(HttpServletRequest request) {
		//获取当前request的方法
		final String currentMethod = request.getMethod();
		final Multimap<String, String> urlMapping = allUrlMapping();

		for (String uri : urlMapping.keySet()) {
			// 通过 AntPathRequestMatcher 匹配 url
			// 可以通过 2 种方式创建 AntPathRequestMatcher
			// 1：new AntPathRequestMatcher(uri,method) 这种方式可以直接判断方法是否匹配，因为这里我们把 方法不匹配 自定义抛出，所以，我们使用第2种方式创建
			// 2：new AntPathRequestMatcher(uri) 这种方式不校验请求方法，只校验请求路径
			AntPathRequestMatcher antPathMatcher = new AntPathRequestMatcher(uri);
			if (antPathMatcher.matches(request)) {
				if (!urlMapping.get(uri).contains(currentMethod)) {
					throw new SecurityException(Status.HTTP_BAD_METHOD);
				} else {
					return;
				}
			}
		}
		throw new SecurityException(Status.REQUEST_NOT_FOUND);
	}

	private Multimap<String, String> allUrlMapping() {
		final Multimap<String, String> urlMapping = ArrayListMultimap.create();

		//获取url与类和方法的对应信息
		final Map<RequestMappingInfo, HandlerMethod> handlerMethods = mapping.getHandlerMethods();

		handlerMethods.forEach((k, v) -> {
			//获取当前key下的获取所有URL
			final Set<String> url = k.getPatternsCondition().getPatterns();
			final RequestMethodsRequestCondition method = k.getMethodsCondition();

			//为每个URL添加所有的请求方法
			url.forEach(s -> urlMapping.putAll(s, method.getMethods().stream().map(Enum::toString).collect(Collectors.toList())));
		});

		return urlMapping;
	}

}
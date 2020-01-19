package com.genius.spring.boot.security.config;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.genius.spring.boot.security.common.Status;
import com.genius.spring.boot.security.service.CustomUserDetailsService;
import com.genius.spring.boot.security.util.JwtUtil;
import com.genius.spring.boot.security.util.ResponseUtil;
import com.google.common.collect.Sets;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;

/**
 * @类名 JwtAuthenticationFilter
 * @描述 JwtAuthenticationFilter
 * @作者 shuaiqi
 * @创建日期 2020/1/16 23:12
 * @版本号 1.0
 * @参考地址
 **/
@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private CustomConfig customConfig;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		if (checkIgnores(request)) {
			filterChain.doFilter(request, response);
			return;
		}

		final String jwt = jwtUtil.getJwtFromRequest(request);

		if (StrUtil.isNotBlank(jwt)) {
			final String username = jwtUtil.getUsernameFromJWT(jwt);
			final UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
			final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			filterChain.doFilter(request, response);
		} else {
			ResponseUtil.renderJson(response, Status.UNAUTHORIZED, null);
		}
	}

	/**
	 * 请求是否不需要进行权限拦截
	 *
	 * @param request 当前请求
	 * @return true - 忽略，false - 不忽略
	 */
	private boolean checkIgnores(HttpServletRequest request) {
		String method = request.getMethod();

		HttpMethod httpMethod = HttpMethod.resolve(method);
		if (ObjectUtil.isNull(httpMethod)) {
			httpMethod = HttpMethod.GET;
		}

		Set<String> ignores = Sets.newHashSet();

		switch (httpMethod) {
			case GET:
				ignores.addAll(customConfig.getIgnores().getGet());
				break;
			case PUT:
				ignores.addAll(customConfig.getIgnores().getPut());
				break;
			case HEAD:
				ignores.addAll(customConfig.getIgnores().getHead());
				break;
			case POST:
				ignores.addAll(customConfig.getIgnores().getPost());
				break;
			case PATCH:
				ignores.addAll(customConfig.getIgnores().getPatch());
				break;
			case TRACE:
				ignores.addAll(customConfig.getIgnores().getTrace());
				break;
			case DELETE:
				ignores.addAll(customConfig.getIgnores()
						.getDelete());
				break;
			case OPTIONS:
				ignores.addAll(customConfig.getIgnores().getOptions());
				break;
			default:
				break;
		}

		ignores.addAll(customConfig.getIgnores().getPattern());

		if (CollUtil.isNotEmpty(ignores)) {
			for (String ignore : ignores) {
				AntPathRequestMatcher matcher = new AntPathRequestMatcher(ignore, method);
				if (matcher.matches(request)) {
					return true;
				}
			}
		}

		return false;
	}
}
package com.genius.spring.boot.log.aop.aspect;

import cn.hutool.json.JSONUtil;
import eu.bitwalker.useragentutils.UserAgent;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Map;
import java.util.Objects;

/**
 * @类名 AopLog
 * @描述 使用 aop 切面记录请求日志信息
 * @作者 shuaiqi
 * @创建日期 2020/1/13 9:53
 * @版本号 1.0
 * @参考地址
 **/
@Aspect
@Component
@Slf4j
public class AopLog {

	private static final String START_TIME = "request-start";

	/**
	 * 切入点
	 */
	@Pointcut("execution(public * com.genius.spring.boot.log.aop.controller.*Controller.*(..))")
	public void log(){}

	/**
	 * 前置操作
	 */
	@Before("log()")
	public void beforeLog(JoinPoint joinPoint) {
		final ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		final HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();

		log.info("【请求 URL】: {}", request.getRequestURL());
		log.info("【请求 IP】: {}", request.getRemoteAddr());
		log.info("【请求类名】: {}, 【请求方法名】: {}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());

		final Map<String, String[]> parameterMap = request.getParameterMap();
		log.info("【请求参数】： {},", JSONUtil.toJsonStr(parameterMap));
		final long start = System.currentTimeMillis();
		request.setAttribute(START_TIME, start);
	}

	/**
	 * 环绕操作
	 *
	 * @param point 切入点
	 * @return 原方法返回值
	 * @throws Throwable 异常信息
	 */
	@Around("log()")
	public Object aroundLog(ProceedingJoinPoint point) throws Throwable {
		Object result = point.proceed();
		log.info("【返回值】：{}", JSONUtil.toJsonStr(result));
		return result;
	}

	/**
	 * 后置操作
	 */
	@AfterReturning("log()")
	public void afterReturning() {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();

		Long start = (Long) request.getAttribute(START_TIME);
		Long end = System.currentTimeMillis();
		log.info("【请求耗时】：{}毫秒", end - start);

		String header = request.getHeader("User-Agent");
		UserAgent userAgent = UserAgent.parseUserAgentString(header);
		log.info("【浏览器类型】：{}，【操作系统】：{}，【原始User-Agent】：{}", userAgent.getBrowser().toString(), userAgent.getOperatingSystem().toString(), header);
	}

}
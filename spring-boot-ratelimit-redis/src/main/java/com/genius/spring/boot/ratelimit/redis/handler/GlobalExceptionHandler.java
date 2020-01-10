package com.genius.spring.boot.ratelimit.redis.handler;

import cn.hutool.core.lang.Dict;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @类名 GlobalExceptionHandler
 * @描述 TODO
 * @作者 shuaiqi
 * @创建日期 2020/1/10 16:37
 * @版本号 1.0
 * @参考地址
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	public Dict handler(RuntimeException ex) {
		return Dict.create().set("msg", ex.getMessage());
	}

}
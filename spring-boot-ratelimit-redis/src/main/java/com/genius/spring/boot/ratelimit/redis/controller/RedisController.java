package com.genius.spring.boot.ratelimit.redis.controller;

import cn.hutool.core.lang.Dict;
import com.genius.spring.boot.ratelimit.redis.annotation.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @类名 RedisController
 * @描述 RedisController
 * @作者 shuaiqi
 * @创建日期 2020/1/10 16:05
 * @版本号 1.0
 * @参考地址
 **/
@Slf4j
@RestController
public class RedisController {

	@RateLimiter(value = 5)
	@GetMapping("/redis1")
	public Dict redis1() {
		log.info("【redis1】被执行了");
		return Dict.create().set("msg", "hello world").set("description", "别想一直看到我，不信你快速刷新看看");
	}

	@GetMapping("/redis2")
	public Dict redis2() {
		log.info("【redis2】被执行了。。。。。");
		return Dict.create().set("msg", "hello,world!").set("description", "这个一直能请求");
	}

	@RateLimiter(value = 2, key = "测试自定义key")
	@GetMapping("/redis3")
	public Dict redis3() {
		log.info("【redis3】被执行了。。。。。");
		return Dict.create().set("msg", "hello,world!").set("description", "别想一直看到我，不信你快速刷新看看~");
	}

}
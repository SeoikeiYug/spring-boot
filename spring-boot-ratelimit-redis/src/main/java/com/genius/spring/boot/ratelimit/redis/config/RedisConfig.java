package com.genius.spring.boot.ratelimit.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

/**
 * @类名 RedisConfig
 * @描述 RedisConfig
 * @作者 shuaiqi
 * @创建日期 2020/1/10 16:33
 * @版本号 1.0
 * @参考地址
 **/
@Configuration
public class RedisConfig {

	@Bean
	public RedisScript<Long> limitRedisScript() {
		final DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
		redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("scripts/redis/limit.lua")));
		redisScript.setResultType(Long.class);
		return redisScript;
	}

}
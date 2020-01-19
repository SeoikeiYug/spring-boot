package com.genius.spring.boot.security.config;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @类名 IdConfig
 * @描述 雪花主键生成器
 * @作者 shuaiqi
 * @创建日期 2020/1/19 9:38
 * @版本号 1.0
 * @参考地址
 **/
@Configuration
public class IdConfig {

	/**
	 * 雪花生成器
	 */
	@Bean
	public Snowflake snowflake() {
		return IdUtil.createSnowflake(1, 1);
	}

}
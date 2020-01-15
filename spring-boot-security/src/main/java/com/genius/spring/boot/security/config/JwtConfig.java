package com.genius.spring.boot.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @类名 JwtConfig
 * @描述 JWT配置
 * @作者 shuaiqi
 * @创建日期 2020/1/13 11:59
 * @版本号 1.0
 * @参考地址
 **/
@ConfigurationProperties(prefix = "jwt.config")
@Data
public class JwtConfig {
	/**
	 * jwt 加密key，默认值：genius
	 */
	private String key = "genius";

	/**
	 * jwt 过期时间，默认值：600000{@code 10分钟}
	 */
	private Long ttl = 600000L;

	/**
	 * 开启 记住我 之后jwt过期时间，默认值：604800000{@code 7天}
	 */
	private Long remember = 604800000L;
}
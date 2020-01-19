package com.genius.spring.boot.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @类名 CustomConfig
 * @描述 自定义配置
 * @作者 shuaiqi
 * @创建日期 2020/1/16 14:36
 * @版本号 1.0
 * @参考地址
 **/
@ConfigurationProperties(prefix = "custom.config")
@Data
public class CustomConfig {

	private IgnoreConfig ignores;

}
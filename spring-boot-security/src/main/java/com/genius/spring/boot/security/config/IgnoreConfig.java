package com.genius.spring.boot.security.config;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * @类名 IgnoreConfig
 * @描述 忽略配置
 * @作者 shuaiqi
 * @创建日期 2020/1/16 22:57
 * @版本号 1.0
 * @参考地址
 **/
@Data
public class IgnoreConfig {

	/**
	 * 需要忽略的 URL 格式，不考虑请求方法
	 */
	private List<String> pattern = Lists.newArrayList();

	/**
	 * 需要忽略的 GET 请求
	 */
	private List<String> get = Lists.newArrayList();

	/**
	 * 需要忽略的 POST 请求
	 */
	private List<String> post = Lists.newArrayList();

	/**
	 * 需要忽略的 DELETE 请求
	 */
	private List<String> delete = Lists.newArrayList();

	/**
	 * 需要忽略的 PUT 请求
	 */
	private List<String> put = Lists.newArrayList();

	/**
	 * 需要忽略的 HEAD 请求
	 */
	private List<String> head = Lists.newArrayList();

	/**
	 * 需要忽略的 PATCH 请求
	 */
	private List<String> patch = Lists.newArrayList();

	/**
	 * 需要忽略的 OPTIONS 请求
	 */
	private List<String> options = Lists.newArrayList();

	/**
	 * 需要忽略的 TRACE 请求
	 */
	private List<String> trace = Lists.newArrayList();

}
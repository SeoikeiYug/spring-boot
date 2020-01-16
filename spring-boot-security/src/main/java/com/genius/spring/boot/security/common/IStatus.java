package com.genius.spring.boot.security.common;

/**
 * Rest API 错误码接口
 */
public interface IStatus {

	/**
	 * 状态码
	 *
	 * @return 状态码
	 */
	Integer getCode();

	/**
	 * 返回信息
	 *
	 * @return 返回信息
	 */
	String getMessage();

}

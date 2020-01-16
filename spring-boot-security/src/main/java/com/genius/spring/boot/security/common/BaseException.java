package com.genius.spring.boot.security.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @类名 BaseException
 * @描述 异常基类
 * @作者 shuaiqi
 * @创建日期 2020/1/16 11:53
 * @版本号 1.0
 * @参考地址
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseException extends RuntimeException {

	private Integer code;
	private String message;
	private Object data;

	public BaseException(Status status) {
		super(status.getMessage());
		this.code = status.getCode();
		this.message = status.getMessage();
	}

	public BaseException(Status status, Object data) {
		this(status);
		this.data = data;
	}

	public BaseException(Integer code, String message) {
		super(message);
		this.code = code;
		this.message = message;
	}

	public BaseException(Integer code, String message, Object data) {
		this(code, message);
		this.data = data;
	}
}
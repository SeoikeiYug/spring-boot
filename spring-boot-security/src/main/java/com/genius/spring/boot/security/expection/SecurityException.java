package com.genius.spring.boot.security.expection;

import com.genius.spring.boot.security.common.BaseException;
import com.genius.spring.boot.security.common.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @类名 SecurityException
 * @描述 TODO
 * @作者 shuaiqi
 * @创建日期 2020/1/16 11:52
 * @版本号 1.0
 * @参考地址
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class SecurityException extends BaseException {

	public SecurityException(Status status) {
		super(status);
	}

	public SecurityException(Status status, Object data) {
		super(status, data);
	}

	public SecurityException(Integer code, String message) {
		super(code, message);
	}

	public SecurityException(Integer code, String message, Object data) {
		super(code, message, data);
	}
}
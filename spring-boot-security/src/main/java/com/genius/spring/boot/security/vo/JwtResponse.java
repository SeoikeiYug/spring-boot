package com.genius.spring.boot.security.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @类名 JwtResponse
 * @描述 JWT 响应返回
 * @作者 shuaiqi
 * @创建日期 2020/1/16 11:37
 * @版本号 1.0
 * @参考地址
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {

	/**
	 * token 字段
	 */
	private String token;

	/**
	 * token类型
	 */
	private String tokenType = "Bearer";

	public JwtResponse(String token) {
		this.token = token;
	}

}
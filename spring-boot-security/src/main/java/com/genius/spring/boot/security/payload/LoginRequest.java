package com.genius.spring.boot.security.payload;

import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @类名 LoginRequest
 * @描述 登录请求参数
 * @作者 shuaiqi
 * @创建日期 2020/1/16 14:16
 * @版本号 1.0
 * @参考地址
 **/
@Data
public class LoginRequest {

	/**
	 * 用户名或邮箱或手机号
	 */
	@NotBlank(message = "用户名不能为空")
	private String usernameOrEmailOrPhone;

	/**
	 * 密码
	 */
	@NotBlank(message = "密码不能为空")
	private String password;

	/**
	 * 记住我
	 */
	private Boolean rememberMe = false;

}
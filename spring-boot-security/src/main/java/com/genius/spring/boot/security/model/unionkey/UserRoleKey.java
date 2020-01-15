package com.genius.spring.boot.security.model.unionkey;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

/**
 * @类名 UserRoleKey
 * @描述 用户-角色联合主键
 * @作者 shuaiqi
 * @创建日期 2020/1/14 17:21
 * @版本号 1.0
 * @参考地址
 **/
@Data
@Embeddable
public class UserRoleKey implements Serializable {

	private static final long serialVersionUID = 5633412144183654743L;

	/**
	 * 用户Id
	 */
	@Column(name = "user_id")
	private Long userId;

	/**
	 * 角色Id
	 */
	@Column(name = "role_id")
	private Long roleId;
}
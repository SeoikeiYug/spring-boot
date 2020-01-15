package com.genius.spring.boot.security.model.unionkey;

import javax.persistence.Column;

import java.io.Serializable;

/**
 * @类名 RolePermissionKey
 * @描述 角色-权限联合主键
 * @作者 shuaiqi
 * @创建日期 2020/1/14 17:27
 * @版本号 1.0
 * @参考地址
 **/
public class RolePermissionKey implements Serializable {

	private static final long serialVersionUID = 6850974328279713855L;

	/**
	 * 角色id
	 */
	@Column(name = "role_id")
	private Long roleId;

	/**
	 * 权限id
	 */
	@Column(name = "permission_id")
	private Long permissionId;
}
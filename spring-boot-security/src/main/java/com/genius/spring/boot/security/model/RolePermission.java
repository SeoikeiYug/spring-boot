package com.genius.spring.boot.security.model;

import com.genius.spring.boot.security.model.unionkey.RolePermissionKey;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

/**
 * @类名 RolePermission
 * @描述 角色-权限
 * @作者 shuaiqi
 * @创建日期 2020/1/14 17:30
 * @版本号 1.0
 * @参考地址
 **/
@Data
@Entity
@Table(name = "sec_role_permission")
public class RolePermission {

	@EmbeddedId
	private RolePermissionKey id;

}
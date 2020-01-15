package com.genius.spring.boot.security.model;

import com.genius.spring.boot.security.model.unionkey.UserRoleKey;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

/**
 * @类名 UserRole
 * @描述 用户角色关联
 * @作者 shuaiqi
 * @创建日期 2020/1/14 17:48
 * @版本号 1.0
 * @参考地址
 **/
@Data
@Entity
@Table(name = "sec_user_role")
public class UserRole {

	/**
	 * 主键
	 */
	@EmbeddedId
	private UserRoleKey id;
}
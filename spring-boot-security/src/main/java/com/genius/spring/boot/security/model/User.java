package com.genius.spring.boot.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * @类名 User
 * @描述 用户
 * @作者 shuaiqi
 * @创建日期 2020/1/14 17:17
 * @版本号 1.0
 * @参考地址
 **/
@Data
@Entity
@Table(name = "sec_user")
public class User {
	/**
	 * 主键
	 */
	@Id
	private Long id;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 昵称
	 */
	private String nickname;

	/**
	 * 手机
	 */
	private String phone;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 生日
	 */
	private Long birthday;

	/**
	 * 性别，男-1，女-2
	 */
	private Integer sex;

	/**
	 * 状态，启用-1，禁用-0
	 */
	private Integer status;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Long createTime;

	/**
	 * 更新时间
	 */
	@Column(name = "update_time")
	private Long updateTime;
}
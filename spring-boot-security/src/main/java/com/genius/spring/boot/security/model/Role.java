package com.genius.spring.boot.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * @类名 Role
 * @描述 角色
 * @作者 shuaiqi
 * @创建日期 2020/1/14 17:28
 * @版本号 1.0
 * @参考地址
 **/
@Data
@Entity
@Table(name = "sec_role")
public class Role {

	/**
	 * 主键
	 */
	@Id
	private Long id;

	/**
	 * 角色名
	 */
	private String name;

	/**
	 * 描述
	 */
	private String description;

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
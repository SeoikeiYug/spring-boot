package com.genius.spring.boot.security.payload;

import lombok.Data;

/**
 * @类名 PageCondition
 * @描述 分页请求参数
 * @作者 shuaiqi
 * @创建日期 2020/1/16 14:17
 * @版本号 1.0
 * @参考地址
 **/
@Data
public class PageCondition {

	/**
	 * 当前页码
	 */
	private Integer currentPage;

	/**
	 * 每页条数
	 */
	private Integer pageSize;

}
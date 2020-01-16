package com.genius.spring.boot.security.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @类名 PageResult
 * @描述 通用分页参数返回
 * @作者 shuaiqi
 * @创建日期 2020/1/16 14:04
 * @版本号 1.0
 * @参考地址
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> implements Serializable {

	private static final long serialVersionUID = 3420391142991247367L;

	/**
	 * 当前页数据
	 */
	private List<T> rows;

	/**
	 * 总条数
	 */
	private Long total;

	public static <T> PageResult of(List<T> rows, Long total) {
		return new PageResult<>(rows, total);
	}

}
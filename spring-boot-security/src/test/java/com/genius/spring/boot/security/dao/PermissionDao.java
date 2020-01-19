package com.genius.spring.boot.security.dao;

import com.genius.spring.boot.security.SpringBootSecurityApplicationTests;
import com.genius.spring.boot.security.model.Permission;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @类名 PermissionDao
 * @描述 TODO
 * @作者 shuaiqi
 * @创建日期 2020/1/19 11:54
 * @版本号 1.0
 * @参考地址
 **/
@Slf4j
public class PermissionDao extends SpringBootSecurityApplicationTests {

	@Autowired
	private PermissionDao permissionDao;

	@Test
	public void selectByRoleIdListTest() {
		final List<Long> roleIds = new ArrayList<>();
		roleIds.add(1072806379208708096L);
		final List<Permission> permissions = permissionDao.selectByRoleIdList(roleIds);
		Assert.assertEquals(1, permissions.size());
		log.info("【1072806379208708096L 的资源】= {}", permissions);
	}

}
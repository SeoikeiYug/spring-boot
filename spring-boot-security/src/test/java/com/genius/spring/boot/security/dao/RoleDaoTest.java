package com.genius.spring.boot.security.dao;

import com.genius.spring.boot.security.SpringBootSecurityApplicationTests;
import com.genius.spring.boot.security.model.Role;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @类名 RolePermissionDaoTest
 * @描述 RolePermissionDaoTest
 * @作者 shuaiqi
 * @创建日期 2020/1/19 11:37
 * @版本号 1.0
 * @参考地址
 **/
@Slf4j
public class RoleDaoTest extends SpringBootSecurityApplicationTests {

	@Autowired
	private RoleDao roleDao;

	@Test
	public void selectByUserId() {
		final List<Role> roles = roleDao.selectByUserId(1072806377661009920L);
		Assert.assertEquals(1, roles.size());
		log.info("【1072806377661009920 的权限】= {}", roles);
	}

}
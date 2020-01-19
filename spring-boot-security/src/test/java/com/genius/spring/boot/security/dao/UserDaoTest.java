package com.genius.spring.boot.security.dao;

import com.genius.spring.boot.security.SpringBootSecurityApplicationTests;
import com.genius.spring.boot.security.model.User;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @类名 UserDaoTest
 * @描述 UserDao 测试
 * @作者 shuaiqi
 * @创建日期 2020/1/19 11:02
 * @版本号 1.0
 * @参考地址
 **/
@Slf4j
public class UserDaoTest extends SpringBootSecurityApplicationTests {

	@Autowired
	private UserDao userDao;

	/**
	 * 根据用户名称获取
	 */
	@Test
	public void findByUsernameIn() {
		final List<String> usernameList = Lists.newArrayList("admin", "user");
		final List<User> userList = userDao.findByUsernameIn(usernameList);
		Assert.assertEquals(2, userList.size());
		log.info("【userList】 = {}", userList);
	}

	@Test
	public void findByUsernameOrEmailOrPhone() {
		String fuzzyQuery = "admin@xkcoding.com";
		final Optional<User> optional = userDao.findByUsernameOrEmailOrPhone(fuzzyQuery, fuzzyQuery, fuzzyQuery);
		if (optional.isPresent()) {
			final User user = optional.get();
			Assert.assertEquals("admin@xkcoding.com", user.getEmail());
			log.info("【user email】= {}", user.getEmail());
		} else {
			Assert.fail("无查询数据");
		}
	}

}
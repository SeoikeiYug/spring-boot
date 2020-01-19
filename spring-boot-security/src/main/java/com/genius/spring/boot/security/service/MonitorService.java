package com.genius.spring.boot.security.service;

import cn.hutool.core.util.StrUtil;
import com.genius.spring.boot.security.common.Constants;
import com.genius.spring.boot.security.common.PageResult;
import com.genius.spring.boot.security.dao.UserDao;
import com.genius.spring.boot.security.model.User;
import com.genius.spring.boot.security.payload.PageCondition;
import com.genius.spring.boot.security.util.RedisUtil;
import com.genius.spring.boot.security.util.SecurityUtil;
import com.genius.spring.boot.security.vo.OnlineUser;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @类名 MonitorService
 * @描述 TODO
 * @作者 shuaiqi
 * @创建日期 2020/1/16 23:20
 * @版本号 1.0
 * @参考地址
 **/
@Slf4j
@Service
public class MonitorService {

	@Autowired
	private RedisUtil redisUtil;

	@Autowired
	private UserDao userDao;

	/**
	 * 在线用户分页列表
	 * @param pageCondition 分页参数
	 * @return 在线用户分页列表
	 */
	public PageResult<OnlineUser> onlineUser(PageCondition pageCondition) {
		final PageResult<String> keys = redisUtil.findKeysForPage(Constants.REDIS_JWT_KEY_PREFIX + Constants.SYMBOL_STAR, pageCondition.getCurrentPage(), pageCondition.getPageSize());
		final List<String> rows = keys.getRows();
		final Long total = keys.getTotal();

		//根据redis中键获取用户名列表
		final List<String> usernameList = rows.stream().map(s -> StrUtil.subAfter(s, Constants.REDIS_JWT_KEY_PREFIX, true)).collect(Collectors.toList());
		//根据用户名查询用户信息
		final List<User> userList = userDao.findByUsernameIn(usernameList);

		//封装在线用户
		final List<OnlineUser> onlineUserList = Lists.newArrayList();
		userList.forEach(user -> onlineUserList.add(OnlineUser.create(user)));
		return new PageResult<>(onlineUserList, total);
	}

	/**
	 * 踢出在线用户
	 * @param names 用户名列表
	 */
	public void kickOut(List<String> names) {
		final List<String> redisKeys = names.parallelStream().map(s -> Constants.REDIS_JWT_KEY_PREFIX + s).collect(Collectors.toList());
		redisUtil.delete(redisKeys);

		//获取当前用户名
		final String currentUsername = SecurityUtil.getCurrentUsername();
		names.parallelStream().forEach(name -> {
			// TODO: 通知被踢出的用户已被当前登录用户踢出，
			//  后期考虑使用 websocket 实现，具体伪代码实现如下。
			//  String message = "您已被用户【" + currentUsername + "】手动下线！";
			log.debug("用户【{}】被用户【{}】手动下线！", name, currentUsername);
		});
	}

}
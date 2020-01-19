package com.genius.spring.boot.security.service;

import com.genius.spring.boot.security.dao.PermissionDao;
import com.genius.spring.boot.security.dao.RoleDao;
import com.genius.spring.boot.security.dao.UserDao;
import com.genius.spring.boot.security.model.Permission;
import com.genius.spring.boot.security.model.Role;
import com.genius.spring.boot.security.model.User;
import com.genius.spring.boot.security.vo.UserPrincipal;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @类名 CustomUserDetailsService
 * @描述 TODO
 * @作者 shuaiqi
 * @创建日期 2020/1/16 23:14
 * @版本号 1.0
 * @参考地址
 **/
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private PermissionDao permissionDao;

	@Override
	public UserDetails loadUserByUsername(String usernameOrEmailOrPhone) throws UsernameNotFoundException {
		final User user = userDao.findByUsernameOrEmailOrPhone(usernameOrEmailOrPhone, usernameOrEmailOrPhone, usernameOrEmailOrPhone)
				.orElseThrow(() -> new UsernameNotFoundException("未找到用户信息 ： " + usernameOrEmailOrPhone));
		final List<Role> roles = roleDao.selectByUserId(user.getId());
		final List<Long> roleIds = roles.stream().map(Role::getId).collect(Collectors.toList());
		final List<Permission> permissions = permissionDao.selectByRoleIdList(roleIds);
		return UserPrincipal.create(user, roles, permissions);
	}
}
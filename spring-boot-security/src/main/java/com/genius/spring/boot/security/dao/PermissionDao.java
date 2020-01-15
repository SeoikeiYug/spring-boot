package com.genius.spring.boot.security.dao;

import com.genius.spring.boot.security.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @类名 PermissionDao
 * @描述 权限Dao
 * @作者 shuaiqi
 * @创建日期 2020/1/14 18:07
 * @版本号 1.0
 * @参考地址
 **/
public interface PermissionDao extends JpaRepository<Permission, Long>, JpaSpecificationExecutor<Permission> {

	/**
	 * 根绝角色列表查询权限列表
	 *
	 * @param ids 角色Id列表
	 * @return 权限列表
	 */
	@Query(value = "SELECT DISTINCT sec_permission.* FROM sec_permission,sec_role,sec_role_permission WHERE sec_role.id = sec_role_permission.role_id AND sec_permission.id = sec_role_permission.permission_id AND sec_role.id IN (:ids)", nativeQuery = true)
	List<Permission> selectByRoleIdList(@Param("ids") List<Long> ids);

}
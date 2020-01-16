package com.genius.spring.boot.security.dao;

import com.genius.spring.boot.security.model.RolePermission;
import com.genius.spring.boot.security.model.unionkey.RolePermissionKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RolePermissionDao extends JpaRepository<RolePermission, RolePermissionKey>, JpaSpecificationExecutor<RolePermission> {
}

package com.genius.spring.boot.security.dao;

import com.genius.spring.boot.security.model.UserRole;
import com.genius.spring.boot.security.model.unionkey.UserRoleKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRoleDao extends JpaRepository<UserRole, UserRoleKey>, JpaSpecificationExecutor<UserRole> {
}

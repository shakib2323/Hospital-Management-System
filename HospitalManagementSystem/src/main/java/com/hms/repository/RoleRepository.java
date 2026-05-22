package com.hms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.entity.Role;
import com.hms.enums.RoleType;

public interface RoleRepository extends JpaRepository<Role, Long>
{
	Optional<Role> findByRoleName(RoleType roleName);

    boolean existsByRoleName(RoleType roleName);
}

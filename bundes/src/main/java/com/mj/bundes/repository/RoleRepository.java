package com.mj.bundes.repository;

import org.springframework.data.repository.CrudRepository;

import com.mj.bundes.domain.security.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
	Role findByname(String name);
}

package com.mj.bundes.repository;

import org.springframework.data.repository.CrudRepository;

import com.mj.bundes.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
	
	User findByEmail(String email);
}

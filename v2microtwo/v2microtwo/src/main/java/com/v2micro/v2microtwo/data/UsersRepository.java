package com.v2micro.v2microtwo.data;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface UsersRepository extends CrudRepository<UserEntity, Long> {
	Optional<UserEntity> findByEmail(String email);
	UserEntity findByUserId(String userId);
	
}

package com.v2micro.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface UsersRepository extends CrudRepository<UserEntity, Long> {
	Optional<UserEntity> findByEmail(String email);
	UserEntity findByUserId(String userId);
	List<UserEntity> findAll();
	
}

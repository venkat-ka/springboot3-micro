package com.v2micro.v2microtwo.service;


import org.springframework.security.core.userdetails.UserDetailsService;

import com.v2micro.v2microtwo.shared.UserDto;

public interface UsersService extends UserDetailsService {
	UserDto createUser(UserDto userDetails);
	UserDto getUserDetailsByEmail(String email);
	UserDto getUserByUserId(String userId, String authorization);
	
}

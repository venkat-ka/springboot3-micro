package com.v2micro.v2microtwo.ui.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.v2micro.v2microtwo.service.UsersService;
import com.v2micro.v2microtwo.shared.UserDto;
import com.v2micro.v2microtwo.ui.model.CreateUserRequestModel;
import com.v2micro.v2microtwo.ui.model.CreateUserResponseModel;
import com.v2micro.v2microtwo.ui.model.UserResponseModel;

@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private Environment env;

	@Autowired
	UsersService usersService;

	ModelMapper modelMapper = new ModelMapper();

	@GetMapping("/status/check")
	public String status() {
		return "Working on port " + env.getProperty("local.server.port");
	}

	@GetMapping(path = "/byemail", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CreateUserResponseModel> getUserByEmail(@RequestParam("email") String email) {
		UserDetails usrDtl = usersService.loadUserByUsername(email);
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		CreateUserResponseModel returnValue = modelMapper.map(usrDtl, CreateUserResponseModel.class);
		return ResponseEntity.status(HttpStatus.OK).body(returnValue);
	}

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CreateUserResponseModel> createUser(@RequestBody CreateUserRequestModel userDetails) {

		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		UserDto userDto = modelMapper.map(userDetails, UserDto.class);

		UserDto createdUser = usersService.createUser(userDto);

		CreateUserResponseModel returnValue = modelMapper.map(createdUser, CreateUserResponseModel.class);

		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
	}

	@GetMapping(value = "/{userId}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasRole('ADMIN') or principal == #userId")
	// @PreAuthorize("principal == #userId")
	// @PostAuthorize("principal == returnObject.body.userId")
	public ResponseEntity<UserResponseModel> getUser(@PathVariable("userId") String userId,
			@RequestHeader("Authorization") String authorization) {

		UserDto userDto = usersService.getUserByUserId(userId, authorization);
		UserResponseModel returnValue = new ModelMapper().map(userDto, UserResponseModel.class);

		return ResponseEntity.status(HttpStatus.OK).body(returnValue);
	}
}

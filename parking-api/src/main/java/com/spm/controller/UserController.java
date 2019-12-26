package com.spm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spm.dto.UserDto;
import com.spm.exception.CustomExceptionHandler;
import com.spm.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/users")
@Api(value = "Users endpoint")
public class UserController extends CustomExceptionHandler{
	
	@Autowired
	UserService userService;
	
	@RequestMapping(path="/{username}", method = {RequestMethod.GET})
	@ApiOperation("This method support us can get user by username")
	public UserDto getUserByUsername(@PathVariable("username") String username) {
		return userService.getUserByUsername(username);
	}
	
}

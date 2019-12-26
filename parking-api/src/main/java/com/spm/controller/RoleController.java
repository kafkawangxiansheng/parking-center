package com.spm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spm.dto.UserRoleDto;
import com.spm.exception.CustomExceptionHandler;
import com.spm.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/roles")
@Api(value = "Users endpoint")
public class RoleController extends CustomExceptionHandler{
	
	@Autowired
	UserService userService;
	
	@RequestMapping(path="/get-roles/{userId}", method = {RequestMethod.GET})
	@ApiOperation("This method support us can get user's roles by username")
	public List<UserRoleDto> getUserRolesByUsername(@PathVariable("userId") Long userId) {
		return userService.getUserRolesByUserId(userId);
	}
	
}

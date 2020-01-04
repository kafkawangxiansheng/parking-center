package com.spm.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spm.common.enums.UserAttributeType;
import com.spm.dto.ResultObject;
import com.spm.dto.UserAttributeDto;
import com.spm.dto.UserDto;
import com.spm.dto.UserProjectDto;
import com.spm.dto.UserRoleDto;
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
	
	
	@RequestMapping(path="/get-attributes/{userId}/", method = {RequestMethod.GET})
	@ApiOperation("This method support us can get user by username")
	public ResultObject<List<UserAttributeDto>> getPropertiesByUsername(@PathVariable("userId") Long userId) {
		ResultObject<List<UserAttributeDto>> result = new ResultObject<>();
		List<UserAttributeDto> userAttributes = new ArrayList<>();
		
		List<UserRoleDto>  roles = userService.getUserRolesByUserId(userId);
		List<UserProjectDto> userProjects = userService.getUserProjectsByUserId(userId);
		roles.forEach(role -> {
			UserAttributeDto attribute  = new UserAttributeDto();
			attribute.setType(UserAttributeType.ROLE);
			attribute.setValue(role.getRole().getName());
			userAttributes.add(attribute);
		});
		userProjects.forEach(userProject -> {
			UserAttributeDto attribute  = new UserAttributeDto();
			attribute.setType(UserAttributeType.PROJECT);
			attribute.setValue(UserAttributeType.PROJECT.name()+"_"+userProject.getProject().getId());
			userAttributes.add(attribute);
		});
		
		result.setData(userAttributes);
		return result;
	}
	
}

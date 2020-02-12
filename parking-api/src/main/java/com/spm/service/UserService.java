package com.spm.service;

import java.util.List;

import com.spm.dto.UserDto;
import com.spm.dto.UserProjectDto;
import com.spm.dto.UserRoleDto;

public interface UserService {
	
	UserDto getUserByUsername(String username);
	
	List<UserRoleDto> getUserRolesByUserId(Long userId); 
	
	List<UserProjectDto> getUserProjectsByUserId(Long userId); 
	
}

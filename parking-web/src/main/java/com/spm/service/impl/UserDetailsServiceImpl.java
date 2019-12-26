package com.spm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spm.common.RestUtils;
import com.spm.common.URLConstants;
import com.spm.dto.UserDto;
import com.spm.dto.UserRoleDto;

@Service(value = "userService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		RestUtils<UserDto> restUtils = new RestUtils<UserDto>(UserDto.class);
		String getUserURL = URLConstants.URL_USER_BY_USERNAME.replace("{username}", userName);

		UserDto userEntity = restUtils.getUserByUsername(getUserURL);

		if (userEntity == null) {
			System.out.println("User not found! " + userName);
			throw new UsernameNotFoundException("User " + userName + " was not found in the database");
		}

		String getRolesByUserIdURL = URLConstants.URL_ROLES_BY_USER_ID.replace("{userId}",
				String.valueOf(userEntity.getId()));

		RestUtils<UserRoleDto> restUtilsForRoles = new RestUtils<>(UserRoleDto.class);

		// get user roles by user id
		List<UserRoleDto> userRoles = restUtilsForRoles.getUserRolesByUserId(getRolesByUserIdURL);
		
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		if (userRoles != null) {
			for (UserRoleDto userRole : userRoles) {
				// ROLE_USER, ROLE_ADMIN,..
				GrantedAuthority authority = new SimpleGrantedAuthority(userRole.getRole().getName());
				grantList.add(authority);
			}
		}

		UserDetails userDetails = (UserDetails) new User(userEntity.getUsername(), //
				userEntity.getPassword(), grantList);

		return userDetails;
	}

}

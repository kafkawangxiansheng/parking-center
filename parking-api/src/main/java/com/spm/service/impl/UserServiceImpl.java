package com.spm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spm.dto.UserDto;
import com.spm.dto.UserProjectDto;
import com.spm.dto.UserRoleDto;
import com.spm.entity.UserEntity;
import com.spm.entity.UserProjectEntity;
import com.spm.entity.UserRoleEntity;
import com.spm.repository.UserProjectRepository;
import com.spm.repository.UserRepository;
import com.spm.repository.UserRoleRepository;
import com.spm.service.UserService;


@Service
@Transactional
public class UserServiceImpl implements UserService{

	ModelMapper mapper;

	@PostConstruct
	public void init() {
		mapper = new ModelMapper();
	}
	@Autowired
    private UserRepository userRepository;
 
    @Autowired
    private UserRoleRepository userRoleRepository;
	
    @Autowired
    private UserProjectRepository  userProjectRepository;
    
    
	private List<UserDto> map(List<UserEntity> source) {

		ArrayList<UserDto> rtn = new ArrayList<>();
		source.stream().map((entity) -> {
			UserDto dto = new UserDto();
			mapper.map(entity, dto);
			return dto;
		}).forEachOrdered((dto) -> {
			rtn.add(dto);
		});
		return rtn;
	}
	
	private List<UserRoleDto> mapUserRoleDtos(List<UserRoleEntity> source) {

		ArrayList<UserRoleDto> rtn = new ArrayList<>();
		source.stream().map((entity) -> {
			UserRoleDto dto = new UserRoleDto();
			mapper.map(entity, dto);
			return dto;
		}).forEachOrdered((dto) -> {
			rtn.add(dto);
		});
		return rtn;
	}
	
	private List<UserProjectDto> mapUserProjectDtos(List<UserProjectEntity> source) {

		ArrayList<UserProjectDto> rtn = new ArrayList<>();
		source.stream().map((entity) -> {
			UserProjectDto dto = new UserProjectDto();
			mapper.map(entity, dto);
			return dto;
		}).forEachOrdered((dto) -> {
			rtn.add(dto);
		});
		return rtn;
	}
	
	private List<UserEntity> reMap(List<UserDto> source) {

		ArrayList<UserEntity> rtn = new ArrayList<>();
		source.stream().map((dto) -> {
			UserEntity entity = new UserEntity();
			mapper.map(dto, entity);
			return entity;
		}).forEachOrdered((entity) -> {
			rtn.add(entity);
		});
		return rtn;
	}

	@Override
	public UserDto getUserByUsername(String username) {
		UserEntity entity = userRepository.findByUsername(username);
		UserDto dto = new UserDto();
		
		mapper.map(entity, dto);
		
		return dto;
	}

	@Override
	public List<UserRoleDto> getUserRolesByUserId(Long userId) {
		List<UserRoleEntity> entities = userRoleRepository.findByUserId(userId);
		List<UserRoleDto> dtos = this.mapUserRoleDtos(entities);
		return dtos;
	}

	@Override
	public List<UserProjectDto> getUserProjectsByUserId(Long userId) {
		return this.mapUserProjectDtos(userProjectRepository.findByUserId(userId));
	}

}

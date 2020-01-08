package com.spm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spm.dto.SettingDto;
import com.spm.entity.SettingEntity;
import com.spm.repository.SettingRepository;
import com.spm.service.SettingService;

/**
 * Created by Vincent on 02/10/2018
 */

@Service
public class SettingServiceImpl implements SettingService {

	@Autowired
	private SettingRepository settingRepository;

	ModelMapper mapper;

	private List<SettingDto> map(List<SettingEntity> source) {

		ArrayList<SettingDto> rtn = new ArrayList<>();
		source.stream().map((entity) -> {
			SettingDto dto = new SettingDto();
			mapper.map(entity, dto);
			return dto;
		}).forEachOrdered((dto) -> {
			rtn.add(dto);
		});
		return rtn;
	}

	private List<SettingEntity> reMap(List<SettingDto> source) {

		ArrayList<SettingEntity> rtn = new ArrayList<>();
		source.stream().map((dto) -> {
			SettingEntity entity = new SettingEntity();
			mapper.map(dto, entity);
			return entity;
		}).forEachOrdered((entity) -> {
			rtn.add(entity);
		});
		return rtn;
	}

	@PostConstruct
	public void initialize() {
		mapper = new ModelMapper();
	}

	@Override
	public List<SettingDto> findAll() {
		List<SettingEntity> entities = settingRepository.findAll();
		return this.map(entities);
	}

	@Override
	public List<SettingDto> findAllByProjectId(int projectId) {
		List<SettingEntity> entities = settingRepository.findAllByProjectId(projectId);
		return this.map(entities);
	}

	@Override
	public List<SettingDto> findAllByAdminType(int adminType, int projectId) {
		List<SettingEntity> entities = settingRepository.findAllByAdminTypeAndProjectId(adminType, projectId);
		return this.map(entities);
	}

	@Override
	public SettingDto findOneById(long id) {
		SettingEntity entity = settingRepository.getOne(id);
		SettingDto dto = new SettingDto();
		mapper.map(entity, dto);
		return dto;
	}

	@Override
	public SettingDto create(SettingDto settingDto) {
		SettingEntity entity = new SettingEntity();
		mapper.map(settingDto, entity);
		settingRepository.save(entity);
		mapper.map(entity, settingDto);
		return settingDto;
	}

	@Override
	public void delete(long id) {
		settingRepository.deleteById(id);

	}

}

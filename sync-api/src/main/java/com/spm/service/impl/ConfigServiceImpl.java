package com.spm.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spm.dtos.ConfigDto;
import com.spm.entity.ConfigEntity;
import com.spm.repository.ConfigsRepository;
import com.spm.service.ConfigService;


@Service
@Transactional
public class ConfigServiceImpl implements ConfigService {

	ModelMapper mapper;

	@PostConstruct
	public void init() {
		mapper = new ModelMapper();
	}
	@Autowired
    private ConfigsRepository configRepository;
     
    
	private List<ConfigDto> map(List<ConfigEntity> source) {

		List<ConfigDto> rtn = new ArrayList<>();
		source.stream().map((entity) -> {
			ConfigDto dto = new ConfigDto();
			mapper.map(entity, dto);
			return dto;
		}).forEachOrdered((dto) -> {
			rtn.add(dto);
		});
		return rtn;
	}
	
	private List<ConfigEntity> reMap(List<ConfigDto> source) {

		List<ConfigEntity> rtn = new ArrayList<>();
		source.stream().map((dto) -> {
			ConfigEntity entity = new ConfigEntity();
			mapper.map(dto,entity);
			return entity;
		}).forEachOrdered((entity) -> {
			rtn.add(entity);
		});
		return rtn;
	}


	@Override
	public ConfigDto getById(long id) {
		ConfigDto dto = new ConfigDto();
		ConfigEntity entity = configRepository.getOne(id);
		if(entity == null) {
			return null;
		}
		mapper.map(entity, dto);
		return dto;
	}
	
	
	
	@Override
	public List<ConfigDto> findAllByProjectId(long projectId) {
		List<ConfigEntity> entities = configRepository.findAllByProjectId(projectId);
		List<ConfigDto> dtos = this.map(entities);
		return dtos;
	}
	
	@Override
	public ConfigDto findAllByProjectIdAndCode(long projectId, String code) {
		ConfigEntity entity = configRepository.findAllByProjectIdAndCode(projectId, code);
		if(entity == null) {
			return null;
		}
		ConfigDto dto = new ConfigDto();
		mapper.map(entity, dto);
		return dto;
	}


	@Override
	public void save(ConfigDto ConfigDto) {
		ConfigEntity entity = new ConfigEntity();
		mapper.map(ConfigDto, entity);
		entity.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
		configRepository.save(entity);
	}


	@Override
	public void delete(long id) {
		configRepository.deleteById(id);
	}


	@Override
	public void save(List<ConfigDto> configDtos) {
		List<ConfigEntity>  entities = this.reMap(configDtos);
		configRepository.saveAll(entities);
		
	}
	
	@Override
	public List<ConfigDto> syncAllByProjectId(long projectId) {
		List<ConfigEntity> entities = configRepository.syncAllByProjectId(projectId);
		//update all entities with last_sync and updated to current time
		List<ConfigDto> dtos = this.map(entities);
		entities.forEach(entity ->  {
			entity.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
			entity.setLastSync(Calendar.getInstance().getTimeInMillis());
		});
		configRepository.saveAll(entities);
		
		return dtos;
	}
		
	
}

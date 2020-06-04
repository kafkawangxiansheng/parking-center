package com.spm.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spm.dtos.FunctionDto;
import com.spm.entity.FunctionEntity;
import com.spm.repository.FunctionRepository;
import com.spm.service.FunctionService;

@Service
public class FunctionServiceImpl implements FunctionService {

	@Autowired
	private FunctionRepository functionRepository;

	ModelMapper mapper;

	private List<FunctionDto> map(List<FunctionEntity> source) {

		ArrayList<FunctionDto> rtn = new ArrayList<>();
		source.stream().map((entity) -> {
			FunctionDto dto = new FunctionDto();
			mapper.map(entity, dto);
			return dto;
		}).forEachOrdered((dto) -> {
			rtn.add(dto);
		});
		return rtn;
	}

	private List<FunctionEntity> reMap(List<FunctionDto> source) {

		ArrayList<FunctionEntity> rtn = new ArrayList<>();
		source.stream().map((dto) -> {
			FunctionEntity entity = new FunctionEntity();
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
	public FunctionDto save(FunctionDto functionDto) {

		FunctionEntity entity = new FunctionEntity();
		mapper.map(functionDto, entity);
		entity.setUpdated(Calendar.getInstance().getTimeInMillis());
		entity = functionRepository.save(entity);
		mapper.map(entity, functionDto);
		return functionDto;

	}

	@Override
	public List<FunctionDto> save(List<FunctionDto> functionDtos) {

		List<FunctionEntity> functionEntities = reMap(functionDtos);
		functionEntities = functionRepository.saveAll(functionEntities);
		functionDtos = map(functionEntities);
		return functionDtos;
	}

	@Override
	public FunctionDto findById(Long id) {
		FunctionEntity entity = functionRepository.getOne(id);
		FunctionDto functionDto = new FunctionDto();
		mapper.map(entity, functionDto);
		return functionDto;
	}

	@Override
	public List<FunctionDto> findByProjectId(Long projectId) {
		List<FunctionEntity> entities = functionRepository.findAllByProjectId(projectId);
		if (entities == null || entities.isEmpty()) {
			return null;
		}
		return this.map(entities);
	}
	
	@Override
	public FunctionDto findByProjectIdAndFunctionId(Long projectId, String functionId) {
		FunctionEntity entity = functionRepository.findAllByProjectIdAndFunctionId(projectId, functionId);
		if (entity == null) {
			return null;
		}
		FunctionDto functionDto = new FunctionDto();
		mapper.map(entity, functionDto);
		return functionDto;
	}

	@Override
	public void delete(Long id) {
		FunctionEntity entity = functionRepository.getOne(id);
		entity.setDeleted(1);
		entity.setUpdated(Calendar.getInstance().getTimeInMillis());
		functionRepository.save(entity);
	}

	@Override
	public List<FunctionDto> getListAll(Long projectId) {
		List<FunctionEntity> entities = functionRepository.findAllByProjectId(projectId);
		return this.map(entities);
	}


	@Override
	public FunctionDto update(FunctionDto functionDto) {
		FunctionEntity newEntity = new FunctionEntity();
		mapper.map(functionDto, newEntity);
		newEntity.setUpdated(Calendar.getInstance().getTimeInMillis());
		newEntity = functionRepository.save(newEntity);
		mapper.map(newEntity, functionDto);
		return functionDto;
		
	}

	@Override
	public List<FunctionDto> syncAllByProjectId(long projectId) {
		List<FunctionEntity> entities = functionRepository.syncAllByProjectId(projectId);
		// update all entities with last_sync and updated to current time
		List<FunctionDto> dtos = this.map(entities);
		entities.forEach(entity -> {
			entity.setUpdated(Calendar.getInstance().getTimeInMillis());
			entity.setLastSync(Calendar.getInstance().getTimeInMillis());
		});
		functionRepository.saveAll(entities);

		return dtos;
	}

}

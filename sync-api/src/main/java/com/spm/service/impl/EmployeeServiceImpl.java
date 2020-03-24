package com.spm.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spm.dtos.EmployeeDto;
import com.spm.entity.EmployeeEntity;
import com.spm.repository.EmployeeRepository;
import com.spm.service.EmployeeService;


@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService{

	ModelMapper mapper;

	@PostConstruct
	public void init() {
		mapper = new ModelMapper();
	}
	@Autowired
    private EmployeeRepository employeeRepository;
     
    
	private List<EmployeeDto> map(List<EmployeeEntity> source) {

		List<EmployeeDto> rtn = new ArrayList<>();
		source.stream().map((entity) -> {
			EmployeeDto dto = new EmployeeDto();
			mapper.map(entity, dto);
			return dto;
		}).forEachOrdered((dto) -> {
			rtn.add(dto);
		});
		return rtn;
	}
	
	private List<EmployeeEntity> reMap(List<EmployeeDto> source) {

		List<EmployeeEntity> rtn = new ArrayList<>();
		source.stream().map((dto) -> {
			EmployeeEntity entity = new EmployeeEntity();
			mapper.map(dto,entity);
			return entity;
		}).forEachOrdered((entity) -> {
			rtn.add(entity);
		});
		return rtn;
	}


	@Override
	public EmployeeDto getById(long id) {
		EmployeeDto dto = new EmployeeDto();
		EmployeeEntity entity = employeeRepository.getOne(id);
		if(entity == null) {
			return null;
		}
		mapper.map(entity, dto);
		return dto;
	}
	
	@Override
	public EmployeeDto getByEmployeeCode(String employeeCode) {
		EmployeeDto dto = new EmployeeDto();
		EmployeeEntity entity = employeeRepository.findByEmployeeCode(employeeCode);
		if(entity == null) {
			return null;
		}
		mapper.map(entity, dto);
		return dto;
	}


	
	@Override
	public List<EmployeeDto> findAllByProjectId(long projectId) {
		List<EmployeeEntity> entities = employeeRepository.findAllByProjectId(projectId);
		List<EmployeeDto> dtos = this.map(entities);
		return dtos;
	}


	@Override
	public void save(EmployeeDto employeeDto) {
		EmployeeEntity entity = new EmployeeEntity();
		mapper.map(employeeDto, entity);
		entity.setCreatedAt(Calendar.getInstance().getTime());
		entity.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
		employeeRepository.save(entity);
	}


	@Override
	public void delete(long id) {
		employeeRepository.deleteById(id);
	}


	@Override
	public void save(List<EmployeeDto> employeeDtos) {
		List<EmployeeEntity>  entities = this.reMap(employeeDtos);
		employeeRepository.saveAll(entities);
		
	}
	
	@Override
	public List<EmployeeDto> syncAllByProjectId(long projectId) {
		List<EmployeeEntity> entities = employeeRepository.syncAllByProjectId(projectId);
		//update all entities with last_sync and updated to current time
		List<EmployeeDto> dtos = this.map(entities);
		entities.forEach(entity ->  {
			entity.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
			entity.setLastSync(Calendar.getInstance().getTimeInMillis());
		});
		employeeRepository.saveAll(entities);
		
		return dtos;
	}
		
	
}

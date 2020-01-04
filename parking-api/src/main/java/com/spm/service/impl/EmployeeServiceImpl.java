package com.spm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spm.dto.EmployeeDto;
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


	@Override
	public EmployeeDto getById(long id) {
		EmployeeDto dto = new EmployeeDto();
		EmployeeEntity entity = employeeRepository.getOne(id);
		mapper.map(entity, dto);
		return dto;
	}


	@Override
	public List<EmployeeDto> findAll() {
		List<EmployeeEntity> entities = employeeRepository.findAll();
		List<EmployeeDto> dtos = this.map(entities);
		return dtos;
	}


	@Override
	public void save(EmployeeDto employeeDto) {
		EmployeeEntity entity = new EmployeeEntity();
		mapper.map(employeeDto, entity);
		employeeRepository.save(entity);
	}


	@Override
	public void delete(long id) {
		employeeRepository.deleteById(id);
	}
		
	
}

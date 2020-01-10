package com.spm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spm.dto.EmployeeDto;
import com.spm.dto.ResultObject;
import com.spm.entity.EmployeeEntity;
import com.spm.repository.EmployeeRepository;
import com.spm.search.form.EmployeeSearchForm;
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

		ArrayList<EmployeeEntity> rtn = new ArrayList<>();
		source.stream().map((dto) -> {
			EmployeeEntity entity = new EmployeeEntity();
			mapper.map(dto, entity);
			return entity;
		}).forEachOrdered((entity) -> {
			rtn.add(entity);
		});
		return rtn;
	}


	@Override
	public ResultObject<List<EmployeeDto>> getById(long id) {
		ResultObject<List<EmployeeDto>> result  = new ResultObject<>();
		EmployeeDto dto = new EmployeeDto();
		EmployeeEntity entity = employeeRepository.getOne(id);
		mapper.map(entity, dto);
		List<EmployeeDto>  listData = new ArrayList<>();
		listData.add(dto);
		result.setData(listData);
		return result;
	}


	@Override
	public ResultObject<List<EmployeeDto>> findAll(Pageable pageable, EmployeeSearchForm employeeSearchForm) {
		Page<EmployeeEntity> entities = employeeRepository.search(employeeSearchForm.getName(), employeeSearchForm.getPosition(), pageable);
		ResultObject<List<EmployeeDto>> resultObject = new ResultObject<>();
		resultObject.setData(this.map(entities.getContent()));
		resultObject.setTotalPages(entities.getTotalPages());
		return resultObject;
	}
	
	@Override
	public ResultObject<List<EmployeeDto>> findAllByProjectId(long projectId) {
		List<EmployeeEntity> entities = employeeRepository.findAll();
		List<EmployeeDto> dtos = this.map(entities);
		ResultObject<List<EmployeeDto>> result  = new ResultObject<>();
		result.setData(dtos);
		return result;
	}
	
	/**
	 * Created by thanhtuan 08/01/2020
	 */

	@Override
	public void delete(Long id) {
		employeeRepository.deleteById(id);
	}


	@Override
	public ResultObject<List<EmployeeDto>> findAll() {
		ResultObject<List<EmployeeDto>> resultObj = new ResultObject<>();
		List<EmployeeEntity> entities = employeeRepository.findAll();
		resultObj.setData(this.map(entities));
		return resultObj;
	}


	@Override
	public ResultObject<List<EmployeeDto>> findById(Long Id) {
		ResultObject<List<EmployeeDto>> resultObj = new ResultObject<>();
		EmployeeEntity entity = employeeRepository.findById(Id).get();
		List<EmployeeDto> listEmployeeDto = new ArrayList<EmployeeDto>();
		EmployeeDto employeeDto = new EmployeeDto();
		mapper.map(entity, employeeDto);
		listEmployeeDto.add(employeeDto);
		resultObj.setData(listEmployeeDto);
		return resultObj;
	}


	@Override
	public ResultObject<List<EmployeeDto>> save(EmployeeDto employeeDto) {
		ResultObject<List<EmployeeDto>> resultObj = new ResultObject<>();
		List<EmployeeDto> listEmployee = new ArrayList<>();
		EmployeeEntity entity = new EmployeeEntity();
		mapper.map(employeeDto, entity);
		entity = employeeRepository.save(entity);
		mapper.map(entity, employeeDto);
		listEmployee.add(employeeDto);
		resultObj.setData(listEmployee);
		return resultObj;
	}


	@Override
	public ResultObject<List<EmployeeDto>> save(List<EmployeeDto> listEmployeeDto) {
		ResultObject<List<EmployeeDto>> resultObj = new ResultObject<>();
		List<EmployeeEntity> listEntity = reMap(listEmployeeDto);
		listEntity = employeeRepository.saveAll(listEntity);
		resultObj.setData(map(listEntity));
		return resultObj;
	}
		
	
}

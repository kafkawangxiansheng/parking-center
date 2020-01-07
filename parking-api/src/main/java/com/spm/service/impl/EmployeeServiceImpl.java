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

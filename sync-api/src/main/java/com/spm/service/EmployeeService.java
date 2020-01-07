package com.spm.service;

import java.util.List;

import com.spm.dtos.EmployeeDto;

public interface EmployeeService {
	
	EmployeeDto getById(long id);
	
	List<EmployeeDto> findAllByProjectId(long projectId); 
	
	void save(EmployeeDto employeeDto);
	void delete(long id);
	
}

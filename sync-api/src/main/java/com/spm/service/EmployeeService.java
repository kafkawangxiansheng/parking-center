package com.spm.service;

import java.util.List;

import com.spm.dtos.EmployeeDto;

public interface EmployeeService {
	
	EmployeeDto getById(long id);
	
	EmployeeDto getByEmployeeCode(String employeeCode);
	
	List<EmployeeDto> findAllByProjectId(long projectId); 
	
	void save(EmployeeDto employeeDto);
	
	void save(List<EmployeeDto> employeeDtos);
	
	void delete(long id);
	
	List<EmployeeDto> syncAllByProjectId(long projectId);
	
}

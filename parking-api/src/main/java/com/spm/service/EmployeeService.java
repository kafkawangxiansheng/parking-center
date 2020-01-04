package com.spm.service;

import java.util.List;

import com.spm.dto.EmployeeDto;

public interface EmployeeService {
	
	EmployeeDto getById(long id);
	
	List<EmployeeDto> findAll(); 
	
	void save(EmployeeDto employeeDto);
	void delete(long id);
	
}

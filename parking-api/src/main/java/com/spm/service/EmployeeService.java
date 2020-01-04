package com.spm.service;

import java.util.List;

import com.spm.dto.EmployeeDto;
import com.spm.dto.ResultObject;

public interface EmployeeService {
	
	ResultObject<List<EmployeeDto>> getById(long id);
	
	ResultObject<List<EmployeeDto>> findAll(); 
	
	ResultObject<List<EmployeeDto>> findAllByProjectId(long projectId); 
	
	void save(EmployeeDto employeeDto);
	void delete(long id);
	
}

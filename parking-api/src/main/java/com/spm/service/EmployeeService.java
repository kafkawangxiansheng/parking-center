package com.spm.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.spm.dto.EmployeeDto;
import com.spm.dto.ResultObject;
import com.spm.search.form.EmployeeSearchForm;

public interface EmployeeService {
	
	ResultObject<List<EmployeeDto>> getById(long id);
	
	ResultObject<List<EmployeeDto>> findAll(Pageable pageable, EmployeeSearchForm employeeSearchForm); 
	
	ResultObject<List<EmployeeDto>> findAllByProjectId(long projectId); 
	
	void save(EmployeeDto employeeDto);
	void delete(long id);
	
}

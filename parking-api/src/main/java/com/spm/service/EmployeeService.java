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

	//	void save(EmployeeDto employeeDto);
//	void delete(Long id);
	
	/**
	 * Created by thanhtuan 08/01/2020
	 */
	
	void delete(Long id);
	
	ResultObject<List<EmployeeDto>> findAll();
	
	ResultObject<List<EmployeeDto>> findById(Long Id);
	
	ResultObject<List<EmployeeDto>> save(EmployeeDto employeeDto);
    
	ResultObject<List<EmployeeDto>> save(List<EmployeeDto> listEmployeeDto);
	
}

package com.spm.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.spm.dto.EmployeeDto;
import com.spm.dto.ResultObject;
import com.spm.search.form.EmployeeSearchForm;

public interface EmployeeService {
	ResultObject<List<EmployeeDto>> getAllEmployee(EmployeeSearchForm employeeSearchForm, Pageable pageable);
}

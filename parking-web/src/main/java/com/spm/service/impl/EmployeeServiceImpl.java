package com.spm.service.impl;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spm.common.RestUtils;
import com.spm.common.URLConstants;
import com.spm.dto.EmployeeDto;
import com.spm.dto.ResultObject;
import com.spm.search.form.EmployeeSearchForm;
import com.spm.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Override
	public ResultObject<List<EmployeeDto>> getAllEmployee(EmployeeSearchForm employeeSearchForm, Pageable pageable){
		RestUtils<EmployeeDto> restUtils = new RestUtils<>(EmployeeDto.class);
		ResultObject<List<EmployeeDto>> resultFromApi = new ResultObject<>();
		String finalURL = URLConstants.URL_GET_ALL_EMPLOYEE.replaceAll("::page", String.valueOf(pageable.getPageNumber()));
		finalURL = finalURL.replaceAll("::name", employeeSearchForm.getName()!= null ? employeeSearchForm.getName():"");
		finalURL = finalURL.replaceAll("::position", employeeSearchForm.getPosition()!= null ? employeeSearchForm.getPosition():"");
		resultFromApi = restUtils.get(finalURL);
		return resultFromApi;
	}
	
}

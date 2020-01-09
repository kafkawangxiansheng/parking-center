package com.spm.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.entity.StringEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
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

	@Override
	public EmployeeDto getEmployeeById(Long id) {
		ResultObject<List<EmployeeDto>> resultFromApi = new ResultObject<>();
		RestUtils<EmployeeDto> restUtils = new RestUtils<>(EmployeeDto.class);
		String finalURL = URLConstants.URL_GET_EMPLOYEE_BY_ID.replace("::id", String.valueOf(id));
		resultFromApi = restUtils.get(finalURL);
		return resultFromApi.getData().get(0);
	}

	@Override
	public EmployeeDto addEmployee(EmployeeDto employeeDto) {
		RestUtils<EmployeeDto> restUtils = new RestUtils<>(EmployeeDto.class);
		ResultObject<List<EmployeeDto>> resultFromApi = new ResultObject<>();
		Gson gson = new Gson();
		StringEntity stringEntity;
		try {
			stringEntity = new StringEntity(gson.toJson(employeeDto), "UTF-8");
			resultFromApi = restUtils.postJSON(URLConstants.URL_POST_ADD_EMPLOYEE, stringEntity);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return resultFromApi.getData().get(0);
	}

	@Override
	public void deleteEmployee(Long id) {
		RestUtils<EmployeeDto> restUtils = new RestUtils<>(EmployeeDto.class);
		String finalURL = URLConstants.URL_DELETE_EMPLOYEE.replace("::id", String.valueOf(id));
		
		try {
			restUtils.delete(finalURL);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public ResultObject<List<EmployeeDto>> getAll() {
		RestUtils<EmployeeDto> restUtils = new RestUtils<>(EmployeeDto.class);
		ResultObject<List<EmployeeDto>> resultFromApi = new ResultObject<>();
		String finalURL = URLConstants.URL_GET_ALL_EMPLOYEE;
		resultFromApi = restUtils.get(finalURL);
		return resultFromApi;
	}
	
}

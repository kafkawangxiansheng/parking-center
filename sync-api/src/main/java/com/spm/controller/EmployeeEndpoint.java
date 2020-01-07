package com.spm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spm.dtos.EmployeeDto;
import com.spm.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by Vincent on 02/10/2018
 */
@RestController
@RequestMapping("/employee")
@Api(value = "Employees Endpoint")
public class EmployeeEndpoint {

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "/", method = { RequestMethod.POST })
	@ApiOperation("Add new employee")
	public void addMember(@RequestBody EmployeeDto employeeDto) {
		employeeService.save(employeeDto);
	}

	@RequestMapping(value = "/", method = { RequestMethod.PUT })
	@ApiOperation("Update existing card")
	public void updateExistingCard(@RequestBody EmployeeDto employeeDto) {
		employeeService.save(employeeDto);
	}

	@RequestMapping(value = "", method = { RequestMethod.GET })
	@ApiOperation("get all employees")
	public @ResponseBody List<EmployeeDto> getAll(@RequestParam(name="projectId") Long projectId) {
		
		return employeeService.findAllByProjectId(projectId);
	}
	
	@RequestMapping(value="/get-by-id", method = {RequestMethod.GET})
	@ApiOperation("Get employee by id")
	public @ResponseBody EmployeeDto getCardById(@RequestParam(name="id") Long id) {
		return employeeService.getById(id);
	}

}

package com.spm.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@PostMapping(value = "/")
	@ApiOperation("Add new employee")
	public void addMember(@RequestBody EmployeeDto employeeDto) {
		employeeService.save(employeeDto);
	}

	@PutMapping(value = "/")
	@ApiOperation("Update existing card")
	public void updateExistingCard(@RequestBody EmployeeDto employeeDto) {
		employeeService.save(employeeDto);
	}

	@GetMapping(value = "")
	@ApiOperation("get all employees")
	public @ResponseBody List<EmployeeDto> getAll(@RequestParam(name="projectId") Long projectId) {
		
		return employeeService.findAllByProjectId(projectId);
	}
	
	@GetMapping(value="/get-by-id")
	@ApiOperation("Get employee by id")
	public @ResponseBody EmployeeDto getCardById(@RequestParam(name="id") Long id) {
		return employeeService.getById(id);
	}
	
	@PostMapping(value = "/batchinsert")
	@ApiOperation("Add Employee")
	public void batchinsert(@RequestBody List<EmployeeDto> employeeDtos) {
		employeeDtos.forEach(employeeDto -> {
			EmployeeDto existingEmployee  = employeeService.getByEmployeeCode(employeeDto.getEmployeeCode());
			if(existingEmployee != null) {
				employeeDto.setId(existingEmployee.getId());
			}
			employeeDto.setCreatedAt(Calendar.getInstance().getTime());
			employeeDto.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
		});
		employeeService.save(employeeDtos);
	}
	
	@GetMapping(value = "/batchsyncs")
	@ApiOperation("sync all employee")
	public @ResponseBody List<EmployeeDto> batchsyncs(@RequestParam(name="projectId") Long projectId) {
		return  employeeService.syncAllByProjectId(projectId);
	}

}

package com.spm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spm.dto.EmployeeDto;
import com.spm.dto.ResultObject;
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
	
	@GetMapping(value="/get-by-id")
	@ApiOperation("Get employee by id")
	public @ResponseBody ResultObject<List<EmployeeDto>> getCardById(@RequestParam(name="id") Long id) {
		return employeeService.getById(id);
	}
	
	//THANH TUAN
	
	@PostMapping(value = "/addEmployee")
	@ApiOperation("Add new EmployeeDto")
	public @ResponseBody ResultObject<List<EmployeeDto>> addEmployee(@RequestBody EmployeeDto employeeDto) {
		return employeeService.save(employeeDto);
	}
	
	@PostMapping(value = "/update")
	@ApiOperation("Update existing EmployeeDto")
	public @ResponseBody ResultObject<List<EmployeeDto>> updateExistingEmployee(@RequestBody EmployeeDto employeeDto) {
		return employeeService.update(employeeDto);
	}
	
	@GetMapping(value = "")
	@ApiOperation("Get all EmployeeDtos for list")
	public @ResponseBody ResultObject<List<EmployeeDto>> getAll() {
		return employeeService.findAll();
	}
	
	@GetMapping(value = "getEmployeeById")
	@ApiOperation("Get EmployeeDto")
	public @ResponseBody ResultObject<List<EmployeeDto>> getEmployeeById(@RequestParam(name="id") Long id) {
		return employeeService.findById(id);
	}
	
	@GetMapping(value = "getEmployeeByProjectId")
	@ApiOperation("Get Employee  by project id")
	public @ResponseBody ResultObject<List<EmployeeDto>> getEmployeeByProjectId(@RequestParam(name="projectId") Long projectId) {
		return employeeService.findAllByProjectId(projectId);
	}
	
	@DeleteMapping(path="/delete/{id}")
	@ApiOperation("This method support us can delete the specific EmployeeDto by id")
	public void delete(@PathVariable("id") Long id) {
		employeeService.delete(id);
	}
	
	@GetMapping(path="/getAllByProjectId")
	@ApiOperation("Get list Employee by id off Project")
	public ResultObject<List<EmployeeDto>> getAllByprojectId(@RequestParam(name="projectId", required=false) long projectId) {
		return employeeService.getAllByProjectId(projectId);
	}
	

}

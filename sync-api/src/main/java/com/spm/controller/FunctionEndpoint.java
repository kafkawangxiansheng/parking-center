package com.spm.controller;

import java.util.Calendar;
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

import com.spm.dtos.FunctionDto;
import com.spm.service.FunctionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/functions")
@Api(value = "Functions Endpoint", description = "The URL to handle vehicle endpoint")
public class FunctionEndpoint {
	
	@Autowired
	private FunctionService functionService;
	
	
	@PostMapping(value="/add")
	@ApiOperation("Add new funcntion")
	public FunctionDto addNewFunction(@RequestBody FunctionDto functionDto) {
		return functionService.save(functionDto);
	}
	
	@PostMapping(value="/update")
	@ApiOperation("Update existing function")
	public FunctionDto updateExistingFunctionn(@RequestBody FunctionDto functionDto) {
		return functionService.update(functionDto);
	}
	
	@GetMapping(value="/getById")
	@ApiOperation("Get function by id")
	public @ResponseBody FunctionDto getFunctionById(@RequestParam(name="functionId") Long functionId) {
		return functionService.findById(functionId);
	}
	
	@DeleteMapping(path="delete/{id}")
	@ApiOperation("This method support us can delete the specific functionn by id")
	public void delete(@PathVariable("id") Long id) {
		functionService.delete(id);
	}
	
	@GetMapping(value="getListAll")
	@ApiOperation("Get all functions")
	public @ResponseBody List<FunctionDto> getListAll(@RequestParam(name="projectId") Long projectId) {
		return functionService.getListAll(projectId);
	}
	
	@PostMapping(value = "/batchinsert")
	@ApiOperation("Add functions")
	public void batchinsert(@RequestBody List<FunctionDto> functionDtos) {
		functionDtos.forEach(functionDto -> {
			FunctionDto existingFunction  = functionService.findByProjectIdAndFunctionId(functionDto.getProjectId(), functionDto.getFunctionId());
			if(existingFunction != null) {
				functionDto.setId(existingFunction.getId());
			}
			functionDto.setUpdated(Calendar.getInstance().getTimeInMillis());
		});
		functionService.save(functionDtos);
	}
	
	@GetMapping(value = "/batchsyncs")
	@ApiOperation("sync all vehicle")
	public @ResponseBody List<FunctionDto> batchsyncs(@RequestParam(name="projectId") Long projectId) {
		return  functionService.syncAllByProjectId(projectId);
	}

}

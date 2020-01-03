package com.spm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spm.dto.ProjectsDto;
import com.spm.dto.ResultObject;
import com.spm.service.ProjectsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/project")
@Api(value = "Project Endpoint", description = "The URL to handle projects endpoint")
public class ProjectsEndpoint {

	@Autowired
	private ProjectsService projectsService ;
	
	@RequestMapping(value = "/addProject", method = {RequestMethod.POST})
	@ApiOperation("Add new project")
	public @ResponseBody ResultObject<List<ProjectsDto>> addProject(@RequestBody ProjectsDto projectsDto) {
		return projectsService.save(projectsDto);
	}
	
	@RequestMapping(value = "/addProject", method = {RequestMethod.PUT})
	@ApiOperation("Update existing project")
	public @ResponseBody ResultObject<List<ProjectsDto>> updateExistingCard(@RequestBody ProjectsDto projectsDto) {
		return projectsService.save(projectsDto);
	}
	
	@RequestMapping(value = "", method = {RequestMethod.GET})
	@ApiOperation("Get all projects")
	public @ResponseBody ResultObject<List<ProjectsDto>> getAll() {
		return projectsService.findAll();
	}
	
	
	
	@RequestMapping(value = "getProject", method = {RequestMethod.GET})
	@ApiOperation("Get Project")
	public @ResponseBody ResultObject<List<ProjectsDto>> getProjectById(@RequestParam(name="id") Long id) {
		return projectsService.findById(id);
	}
	
	@RequestMapping(path="/delete/{id}", method = {RequestMethod.DELETE})
	@ApiOperation("This method support us can delete the specific project by id")
	public void delete(@PathVariable("id") Long id) {
		projectsService.delete(id);
	}
}

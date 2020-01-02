package com.spm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

/**
 * Created by Sudo on 28/12/2019
 */
@RestController
@RequestMapping("/projects")
@Api(value = "Project Endpoint", description = "The URL to handle projects endpoint")
public class ProjectsEndpoint {

	@Autowired
	private ProjectsService projectsService ;
	
	@RequestMapping(value = "/add", method = {RequestMethod.POST})
	@ApiOperation("Add new project")
	public @ResponseBody ResultObject<List<ProjectsDto>> addNewCard(@RequestBody ProjectsDto projectsDto) {
		return projectsService.save(projectsDto);
	}
	
	@RequestMapping(value = "/update", method = {RequestMethod.PUT})
	@ApiOperation("Update existing project")
	public @ResponseBody ResultObject<List<ProjectsDto>> updateExistingCard(@RequestBody ProjectsDto projectsDto) {
		return projectsService.save(projectsDto);
	}
	
	@RequestMapping(value = "", method = {RequestMethod.GET})
	@ApiOperation("Get all projects")
	public @ResponseBody ResultObject<List<ProjectsDto>> getAll() {
		return projectsService.findAll();
	}
	
	
	
	@RequestMapping(value = "projectId", method = {RequestMethod.GET})
	@ApiOperation("Get all projects")
	public @ResponseBody ResultObject<List<ProjectsDto>> getProjectById(@RequestParam(name="projectId") Long projectId) {
		return projectsService.findById(projectId);
	}
}

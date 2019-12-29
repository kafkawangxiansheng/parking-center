package com.spm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spm.dto.ProjectsDto;
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
	public void addNewCard(@RequestBody ProjectsDto projectsDto) {
		projectsService.save(projectsDto);
	}
	
	@RequestMapping(value = "/update", method = {RequestMethod.PUT})
	@ApiOperation("Update existing project")
	public void updateExistingCard(@RequestBody ProjectsDto projectsDto) {
		projectsService.save(projectsDto);
	}
	
	@RequestMapping(value = "", method = {RequestMethod.GET})
	@ApiOperation("Orders batch syncs")
	public @ResponseBody List<ProjectsDto> getAll() {
		return projectsService.findAll();
	}
}

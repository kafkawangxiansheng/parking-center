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

import com.spm.dtos.ConfigDto;
import com.spm.service.ConfigService;
import com.spm.service.ConfigService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by Vincent on 02/10/2018
 */
@RestController
@RequestMapping("/config")
@Api(value = "config Endpoint")
public class ConfigEndpoint {

	@Autowired
	private ConfigService configService;

	@PostMapping(value = "/")
	@ApiOperation("Add new config")
	public void addMember(@RequestBody ConfigDto ConfigDto) {
		configService.save(ConfigDto);
	}

	@PutMapping(value = "/")
	@ApiOperation("Update existing config")
	public void updateExisting(@RequestBody ConfigDto ConfigDto) {
		configService.save(ConfigDto);
	}

	@GetMapping(value = "")
	@ApiOperation("get all config by the project id")
	public @ResponseBody List<ConfigDto> getAll(@RequestParam(name="projectId") Long projectId) {
		
		return configService.findAllByProjectId(projectId);
	}
	
	@GetMapping(value="/get-by-id")
	@ApiOperation("Get config by id")
	public @ResponseBody ConfigDto getById(@RequestParam(name="id") Long id) {
		return configService.getById(id);
	}
	
	@PostMapping(value = "/batchinsert")
	@ApiOperation("Add list of config")
	public void batchinsert(@RequestBody List<ConfigDto> ConfigDtos) {
		ConfigDtos.forEach(ConfigDto -> {
			ConfigDto existingConfig  = configService.findAllByProjectIdAndCode(ConfigDto.getProjectId(), ConfigDto.getCode());
			if(existingConfig != null) {
				ConfigDto.setId(existingConfig.getId());
			}
			ConfigDto.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
		});
		configService.save(ConfigDtos);
	}
	
	@GetMapping(value = "/batchsyncs")
	@ApiOperation("sync all config")
	public @ResponseBody List<ConfigDto> batchsyncs(@RequestParam(name="projectId") Long projectId) {
		return configService.syncAllByProjectId(projectId);
	}

}

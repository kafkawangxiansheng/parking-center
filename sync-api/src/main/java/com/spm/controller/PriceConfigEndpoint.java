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

import com.spm.dtos.PriceConfigDto;
import com.spm.service.PriceConfigService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by Vincent on 02/10/2018
 */
@RestController
@RequestMapping("/price-config")
@Api(value = "Price config Endpoint")
public class PriceConfigEndpoint {

	@Autowired
	private PriceConfigService priceConfigService;

	@PostMapping(value = "/")
	@ApiOperation("Add new price config")
	public void addMember(@RequestBody PriceConfigDto priceConfigDto) {
		priceConfigService.save(priceConfigDto);
	}

	@PutMapping(value = "/")
	@ApiOperation("Update existing price config")
	public void updateExisting(@RequestBody PriceConfigDto priceConfigDto) {
		priceConfigService.save(priceConfigDto);
	}

	@GetMapping(value = "")
	@ApiOperation("get all price config by the project id")
	public @ResponseBody List<PriceConfigDto> getAll(@RequestParam(name="projectId") Long projectId) {
		
		return priceConfigService.findAllByProjectId(projectId);
	}
	
	@GetMapping(value="/get-by-id")
	@ApiOperation("Get price config by id")
	public @ResponseBody PriceConfigDto getById(@RequestParam(name="id") Long id) {
		return priceConfigService.getById(id);
	}
	
	@PostMapping(value = "/batchinsert")
	@ApiOperation("Add list of price config")
	public void batchinsert(@RequestBody List<PriceConfigDto> priceConfigDtos) {
		priceConfigDtos.forEach(priceConfigDto -> {
			PriceConfigDto existingPriceConfig  = priceConfigService.findAllByProjectIdAndCode(priceConfigDto.getProjectId(), priceConfigDto.getCode());
			if(existingPriceConfig != null) {
				priceConfigDto.setId(existingPriceConfig.getId());
			}
			priceConfigDto.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
		});
		priceConfigService.save(priceConfigDtos);
	}
	
	@GetMapping(value = "/batchsyncs")
	@ApiOperation("sync all price config")
	public @ResponseBody List<PriceConfigDto> batchsyncs(@RequestParam(name="projectId") Long projectId) {
		return  priceConfigService.syncAllByProjectId(projectId);
	}

}

package com.spm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spm.dto.ResultObject;
import com.spm.dto.VehicleDto;
import com.spm.search.form.VehicleSearchForm;
import com.spm.service.VehicleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/vehicle")
@Api(value = "Vehicle Endpoint", description = "The URL to handle vehicle endpoint")
public class VehicleEndpoint {
	
	@Autowired
	private VehicleService vehicleService;
	
	@RequestMapping(value="", method = { RequestMethod.GET })
	@ApiOperation("Orders batch syncs")
	public @ResponseBody ResultObject<List<VehicleDto>> getAll(
			@RequestParam(name="projectId", required=false) String projectId){
		
		VehicleSearchForm vehicleSearchForm = new VehicleSearchForm();
		if(projectId != null && !projectId.isEmpty()) {
			vehicleSearchForm.setProjectId(projectId);
		}
		return vehicleService.findAll(vehicleSearchForm);
		
	}

}

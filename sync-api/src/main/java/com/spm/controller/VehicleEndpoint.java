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

import com.spm.dtos.VehicleDto;
import com.spm.service.VehicleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/vehicle")
@Api(value = "Vehicle Endpoint", description = "The URL to handle vehicle endpoint")
public class VehicleEndpoint {
	
	@Autowired
	private VehicleService vehicleService;
	
	
	@PostMapping(value="/add")
	@ApiOperation("Add new vehicle")
	public VehicleDto addNewVehicle(@RequestBody VehicleDto vehicleDto) {
		return vehicleService.save(vehicleDto);
	}
	
	@PostMapping(value="/update")
	@ApiOperation("Update existing vehicle")
	public VehicleDto updateExistingVehicle(@RequestBody VehicleDto vehicleDto) {
		return vehicleService.update(vehicleDto);
	}
	
	@GetMapping(value="/getById")
	@ApiOperation("Get VehicleId")
	public @ResponseBody VehicleDto getVehicleById(@RequestParam(name="vehicleId") Long vehicleId) {
		return vehicleService.findById(vehicleId);
	}
	
	@DeleteMapping(path="delete/{id}")
	@ApiOperation("This method support us can delete the specific vehicle by id")
	public void delete(@PathVariable("id") Long vehicleId) {
		vehicleService.delete(vehicleId);
	}
	
	@GetMapping(value="getListAll")
	@ApiOperation("Get all vehicle")
	public @ResponseBody List<VehicleDto> getListAll(@RequestParam(name="projectId") Long projectId) {
		return vehicleService.getListAll(projectId);
	}
	
	@PostMapping(value = "/batchinsert")
	@ApiOperation("Add vehicle")
	public void batchinsert(@RequestBody List<VehicleDto> vehicleDtos) {
		vehicleDtos.forEach(vehicleDto -> {
			VehicleDto existingVehicle  = vehicleService.findByVehicleIdAndProjectId(vehicleDto.getVehicleId(),  vehicleDto.getProjectId());
			if(existingVehicle != null) {
				vehicleDto.setId(existingVehicle.getId());
			}
			vehicleDto.setUpdated(Calendar.getInstance().getTimeInMillis());
		});
		vehicleService.save(vehicleDtos);
	}
	
	@GetMapping(value = "/batchsyncs")
	@ApiOperation("sync all vehicle")
	public @ResponseBody List<VehicleDto> batchsyncs(@RequestParam(name="projectId") Long projectId) {
		return  vehicleService.syncAllByProjectId(projectId);
	}

}

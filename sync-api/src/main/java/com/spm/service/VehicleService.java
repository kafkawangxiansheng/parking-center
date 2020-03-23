package com.spm.service;

import java.util.List;

import com.spm.dtos.VehicleDto;


public interface VehicleService {
	
	VehicleDto save(VehicleDto vehicleDto);
	
	List<VehicleDto> save(List<VehicleDto> vehicleDtos);
	
	VehicleDto findById(Long id);
	
	VehicleDto findByVehicleIdAndProjectId(int vehicleId,  Long projectId);
	
	void delete(Long vehicleId);
	
	List<VehicleDto> getListAll(Long projectId);
	
	VehicleDto update(VehicleDto vehicleDto);
	
	public List<VehicleDto> syncAllByProjectId(long projectId);
	
}

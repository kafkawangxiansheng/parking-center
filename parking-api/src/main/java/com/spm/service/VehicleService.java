package com.spm.service;

import java.util.List;

import com.spm.dto.ResultObject;
import com.spm.dto.VehicleDto;
import com.spm.search.form.VehicleSearchForm;

public interface VehicleService {
	
	VehicleDto save(VehicleDto vehicleDto);
	List<VehicleDto> save(List<VehicleDto> vehicleDtos);
	ResultObject<List<VehicleDto>> findAll(VehicleSearchForm vehicleSearchForm);
	ResultObject<List<VehicleDto>> findById(Long vehicleId);
	void delete(Long vehicleId);
	ResultObject<List<VehicleDto>> getListAll();
	

}

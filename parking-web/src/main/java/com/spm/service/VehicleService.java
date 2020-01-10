package com.spm.service;

import java.util.List;

import com.spm.dto.ResultObject;
import com.spm.dto.VehicleDto;
import com.spm.search.form.VehicleSearchForm;

public interface VehicleService {
	
	ResultObject<List<VehicleDto>> getAllVehicle(VehicleSearchForm vehicleSearchForm);

}

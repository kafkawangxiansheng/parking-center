package com.spm.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spm.common.RestUtils;
import com.spm.common.URLConstants;
import com.spm.dto.ResultObject;
import com.spm.dto.VehicleDto;
import com.spm.search.form.VehicleSearchForm;
import com.spm.service.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService{

	@Override
	public ResultObject<List<VehicleDto>> getAllVehicle(VehicleSearchForm vehicleSearchForm){
		RestUtils<VehicleDto> restUtils = new RestUtils<>(VehicleDto.class);
		ResultObject<List<VehicleDto>> resultFromApi = new ResultObject<>();
		String finalURL = URLConstants.URL_GET_ALL_VEHICLE;
		resultFromApi = restUtils.get(finalURL);
		return resultFromApi;
	}
}

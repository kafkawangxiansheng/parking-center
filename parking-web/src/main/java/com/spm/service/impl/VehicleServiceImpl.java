package com.spm.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.entity.StringEntity;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
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
		finalURL = finalURL.replaceAll("::projectId", vehicleSearchForm.getProjectId()!=null ? vehicleSearchForm.getProjectId():"");
		resultFromApi = restUtils.get(finalURL);
		return resultFromApi;
	}
	
	@Override
	public boolean addVehicle(VehicleDto vehicleDto) {
		RestUtils<VehicleDto> restUtils = new RestUtils<>(VehicleDto.class);
		ResultObject<List<VehicleDto>> resultFromApi = new ResultObject<>();
		Gson gson = new Gson();
		StringEntity stringEntity;
		try {
			stringEntity = new StringEntity(gson.toJson(vehicleDto), "UTF-8");
			resultFromApi = restUtils.postJSON(URLConstants.URL_ADD_VEHICLE, stringEntity);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(resultFromApi.getData() != null) {
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public VehicleDto getVehicleById(Long vehicleId){
		RestUtils<VehicleDto> restUtils = new RestUtils<>(VehicleDto.class);
		ResultObject<List<VehicleDto>> resultFromApi = new ResultObject<>();
		String finalURL = URLConstants.URL_GET_VEHICLE_BY_ID.replace("::vehicleId", String.valueOf(vehicleId));
		resultFromApi = restUtils.get(finalURL);
		return resultFromApi.getData().get(0);
	}
	
	@Override
	public void deleteVehicle(Long vehicleId) {
		RestUtils<VehicleDto> restUtils = new RestUtils<>(VehicleDto.class);
		String finalURL = URLConstants.URL_DELETE_VEHICLE.replace("::id", String.valueOf(vehicleId));
		try {
			restUtils.delete(finalURL);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ResultObject<List<VehicleDto>> getListAllVehicle() {
		RestUtils<VehicleDto> restUtils = new RestUtils<>(VehicleDto.class);
		ResultObject<List<VehicleDto>> resultFromApi = new ResultObject<>();
		String finalURL = URLConstants.URL_GET_LIST_ALL_VEHICLE;
		resultFromApi = restUtils.get(finalURL);
		return resultFromApi;
	}

	@Override
	public boolean updateVehicle(VehicleDto vehicleDto) {
		RestUtils<VehicleDto> restUtils = new RestUtils<>(VehicleDto.class);
		ResultObject<List<VehicleDto>> resultFromApi = new ResultObject<>();
		Gson gson = new Gson();
		StringEntity stringEntity;
		try {
			stringEntity = new StringEntity(gson.toJson(vehicleDto), "UTF-8");
			resultFromApi = restUtils.postJSON(URLConstants.URL_UPDATE_VEHICLE, stringEntity);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(resultFromApi.getData() != null) {
			return true;
		}else {
			return false;
		}
	}
	
}

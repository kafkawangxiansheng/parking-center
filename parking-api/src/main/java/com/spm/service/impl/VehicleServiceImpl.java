package com.spm.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spm.dto.ResultObject;
import com.spm.dto.VehicleDto;
import com.spm.entity.VehicleEntity;
import com.spm.repository.VehicleRepository;
import com.spm.search.form.VehicleSearchForm;
import com.spm.service.VehicleService;
import com.spm.service.cache.VehicleCache;

@Service
public class VehicleServiceImpl implements VehicleService{
	
	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Autowired
	private VehicleCache vehicleCache;
	
	ModelMapper mapper;

	private List<VehicleDto> map(List<VehicleEntity> source) {

		ArrayList<VehicleDto> rtn = new ArrayList<>();
		source.stream().map((entity) -> {
			VehicleDto dto = new VehicleDto();
			mapper.map(entity, dto);
			return dto;
		}).forEachOrdered((dto) -> {
			rtn.add(dto);
		});
		return rtn;
	}
	
	private List<VehicleEntity> reMap(List<VehicleDto> source) {

		ArrayList<VehicleEntity> rtn = new ArrayList<>();
		source.stream().map((dto) -> {
			VehicleEntity entity = new VehicleEntity();
			mapper.map(dto, entity);
			return entity;
		}).forEachOrdered((entity) -> {
			rtn.add(entity);
		});
		return rtn;
	}
	
	@PostConstruct
	public void initialize() {
		mapper = new ModelMapper();
		List<VehicleEntity> vehicles = vehicleRepository.findAll();
		vehicles.forEach(vehicle -> {
			vehicleCache.put(vehicle.getProject().getId()+"_"+vehicle.getType(), vehicle.getName());
		});
	}
	
	@Override
	public ResultObject<List<VehicleDto>> findAll(VehicleSearchForm vehicleSearchForm){
		List<VehicleEntity> entities = vehicleRepository.findAllByProjectIdAndDeleted(Long.valueOf(vehicleSearchForm.getProjectId()), false);
		ResultObject<List<VehicleDto>> resultObject = new ResultObject<>();
		resultObject.setData(this.map(entities));
		return resultObject;
	}
	
	@Override
	public ResultObject<List<VehicleDto>> save(VehicleDto vehicleDto) {
		ResultObject<List<VehicleDto>> resultObj = new ResultObject<>();
		List<VehicleDto> listObject = new ArrayList<>();
		
		boolean checkType = checkType(vehicleDto.getType(), vehicleDto.getProject().getId());
		if(checkType) {
			return null;
		}else {
		VehicleEntity entity = new VehicleEntity();
		mapper.map(vehicleDto, entity);
		entity.setUpdated(Calendar.getInstance().getTimeInMillis());
		entity = vehicleRepository.save(entity);
		mapper.map(entity, vehicleDto);
		listObject.add(vehicleDto);
		resultObj.setData(listObject);
		return resultObj;
		}
	}
	
	@Override
	public ResultObject<List<VehicleDto>> save(List<VehicleDto> vehicleDtos) {
		ResultObject<List<VehicleDto>> resultObj = new ResultObject<>();
		List<VehicleEntity> vehicleEntities = reMap(vehicleDtos);
		vehicleEntities = vehicleRepository.saveAll(vehicleEntities);
		resultObj.setData(map(vehicleEntities));
		return resultObj;
	}
	
	@Override
	public ResultObject<List<VehicleDto>> findById(Long vehicleId) {
		ResultObject<List<VehicleDto>> resultObject = new ResultObject<>();
		VehicleEntity entity = vehicleRepository.findById(vehicleId).get();
		List<VehicleDto> listVehicleDto = new ArrayList<VehicleDto>();
		VehicleDto vehicleDto = new VehicleDto();
		mapper.map(entity, vehicleDto);
		listVehicleDto.add(vehicleDto);
		resultObject.setData(listVehicleDto);
		return resultObject;
	}
	
	@Override
	public void delete(Long id) {
		VehicleEntity entity = vehicleRepository.findById(id).get();
		entity.setDeleted(true);
		entity.setUpdated(Calendar.getInstance().getTimeInMillis());
		vehicleRepository.save(entity);
	}

	@Override
	public ResultObject<List<VehicleDto>> getListAll() {
		List<VehicleEntity> entities = vehicleRepository.findAll();
		ResultObject<List<VehicleDto>> resultObject = new ResultObject<>();
		resultObject.setData(this.map(entities));
		return resultObject;
	}
	
	public boolean checkType(int type,  long  projectId) {
		VehicleEntity entites = vehicleRepository.findByTypeAndProjectId(type,projectId);
		if(entites == null) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public ResultObject<List<VehicleDto>> update(VehicleDto vehicleDto) {
		ResultObject<List<VehicleDto>> resultObj = new ResultObject<>();
		List<VehicleDto> listObject = new ArrayList<>();
		
		VehicleEntity newEntity = new VehicleEntity();
		VehicleDto newVehicleDto = new VehicleDto();
		VehicleEntity entites = vehicleRepository.findById(vehicleDto.getId()).get();
		if(entites.getType() == vehicleDto.getType()) {
			mapper.map(vehicleDto, newEntity);
			newEntity.setUpdated(Calendar.getInstance().getTimeInMillis());
			newEntity = vehicleRepository.save(newEntity);
			mapper.map(newEntity,newVehicleDto);
			listObject.add(vehicleDto);
			resultObj.setData(listObject);
			return resultObj;
		}else {
			return save(vehicleDto);
		}
	}

}

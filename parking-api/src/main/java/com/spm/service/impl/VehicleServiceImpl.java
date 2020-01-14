package com.spm.service.impl;

import java.util.ArrayList;
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

@Service
public class VehicleServiceImpl implements VehicleService{
	
	@Autowired
	private VehicleRepository vehicleRepository;
	
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
	}
	
	@Override
	public ResultObject<List<VehicleDto>> findAll(VehicleSearchForm vehicleSearchForm){
		List<VehicleEntity> entities = vehicleRepository.findAllByProjectId(Long.valueOf(vehicleSearchForm.getProjectId()));
		ResultObject<List<VehicleDto>> resultObject = new ResultObject<>();
		resultObject.setData(this.map(entities));
		return resultObject;
	}
	
	@Override
	public VehicleDto save(VehicleDto vehicleDto) {
		VehicleEntity entity = new VehicleEntity();
		mapper.map(vehicleDto, entity);
		entity = vehicleRepository.save(entity);
		mapper.map(entity, vehicleDto);
		return vehicleDto;
	}
	
	@Override
	public List<VehicleDto> save(List<VehicleDto> vehicleDtos) {
		List<VehicleEntity> vehicleEntities = reMap(vehicleDtos);
		vehicleEntities = vehicleRepository.saveAll(vehicleEntities);
		vehicleDtos = map(vehicleEntities);
		return vehicleDtos;
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
	public void delete(Long vehicleId) {
		vehicleRepository.deleteById(vehicleId);
	}

	@Override
	public ResultObject<List<VehicleDto>> getListAll() {
		List<VehicleEntity> entities = vehicleRepository.findAll();
		ResultObject<List<VehicleDto>> resultObject = new ResultObject<>();
		resultObject.setData(this.map(entities));
		return resultObject;
	}

}

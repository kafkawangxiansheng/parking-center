package com.spm.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spm.dtos.VehicleDto;
import com.spm.entity.VehicleEntity;
import com.spm.repository.VehicleRepository;
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
			vehicleCache.put(vehicle.getProjectId()+"_"+vehicle.getVehicleId(), vehicle.getName());
		});
	}
	
	
	
	@Override
	public VehicleDto save(VehicleDto vehicleDto) {
		
		boolean checkType = checkType(vehicleDto.getVehicleId(), vehicleDto.getProjectId());
		if(checkType) {
			return null;
		}else {
		VehicleEntity entity = new VehicleEntity();
		mapper.map(vehicleDto, entity);
		entity.setUpdated(Calendar.getInstance().getTimeInMillis());
		entity = vehicleRepository.save(entity);
		mapper.map(entity, vehicleDto);
		return vehicleDto;
		}
	}
	
	@Override
	public List<VehicleDto> save(List<VehicleDto> vehicleDtos) {
		
		List<VehicleEntity> vehicleEntities = reMap(vehicleDtos);
		vehicleEntities = vehicleRepository.saveAll(vehicleEntities);
		vehicleDtos = map(vehicleEntities);
		return vehicleDtos;
	}
	
	
	@Override
	public VehicleDto findById(Long id) {
		VehicleEntity entity = vehicleRepository.findById(id).get();
		VehicleDto vehicleDto = new VehicleDto();
		mapper.map(entity, vehicleDto);
		return vehicleDto;
	}
	
	@Override
	public VehicleDto findByVehicleIdAndProjectId(int vehicleId, Long projectId) {
		VehicleEntity entity = vehicleRepository.findByVehicleIdAndProjectId(vehicleId, projectId);
		if(entity == null) {
			return null;
		}
		VehicleDto vehicleDto = new VehicleDto();
		mapper.map(entity, vehicleDto);
		return vehicleDto;
	}
	
	@Override
	public void delete(Long id) {
		VehicleEntity entity = vehicleRepository.findById(id).get();
		entity.setDeleted(1);
		entity.setUpdated(Calendar.getInstance().getTimeInMillis());
		vehicleRepository.save(entity);
	}

	@Override
	public List<VehicleDto> getListAll(Long projectId) {
		List<VehicleEntity> entities = vehicleRepository.findAllByProjectId(projectId);
		return this.map(entities);
	}
	
	public boolean checkType(int type,  long  projectId) {
		VehicleEntity entites = vehicleRepository.findByVehicleIdAndProjectId(type,projectId);
		if(entites == null) {
			return false;
		}
		return true;
	}

	@Override
	public VehicleDto update(VehicleDto vehicleDto) {
		VehicleEntity newEntity = new VehicleEntity();
		VehicleDto newVehicleDto = new VehicleDto();
		VehicleEntity entites = vehicleRepository.findById(vehicleDto.getId()).get();
		if(entites.getVehicleId() == vehicleDto.getVehicleId()) {
			mapper.map(vehicleDto, newEntity);
			newEntity.setUpdated(Calendar.getInstance().getTimeInMillis());
			newEntity = vehicleRepository.save(newEntity);
			mapper.map(newEntity,newVehicleDto);
			return newVehicleDto;
		}else {
			return save(vehicleDto);
		}
	}
	
	@Override
	public List<VehicleDto> syncAllByProjectId(long projectId) {
		List<VehicleEntity> entities = vehicleRepository.syncAllByProjectId(projectId);
		//update all entities with last_sync and updated to current time
		List<VehicleDto> dtos = this.map(entities);
		entities.forEach(entity ->  {
			entity.setUpdated(Calendar.getInstance().getTimeInMillis());
			entity.setLastSync(Calendar.getInstance().getTimeInMillis());
		});
		vehicleRepository.saveAll(entities);
		
		return dtos;
	}

}

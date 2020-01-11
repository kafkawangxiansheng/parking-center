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

}

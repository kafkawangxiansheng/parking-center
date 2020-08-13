package com.spm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spm.dto.MonthlyCardLogDto;
import com.spm.dto.ResultObject;
import com.spm.entity.MonthlyCardLogEntity;
import com.spm.repository.MonthlyCardLogRepository;
import com.spm.service.MonthlyCardLogService;

/**
 * Created by Vincent on 02/10/2018
 */

@Service
public class MonthlyCardLogServiceImpl implements MonthlyCardLogService {

	@Autowired
	private MonthlyCardLogRepository monthlyCardLogRepository;

	
	ModelMapper mapper;

		
	private List<MonthlyCardLogDto> mapLog(List<MonthlyCardLogEntity> source) {

		ArrayList<MonthlyCardLogDto> rtn = new ArrayList<>();
		source.stream().map((entity) -> {
			MonthlyCardLogDto dto = new MonthlyCardLogDto();
			mapper.map(entity, dto);
			return dto;
		}).forEachOrdered((dto) -> {
			rtn.add(dto);
		});
		return rtn;
	}
	
	
	private List<MonthlyCardLogEntity> reMap(List<MonthlyCardLogDto> source) {

		ArrayList<MonthlyCardLogEntity> rtn = new ArrayList<>();
		source.stream().map((dto) -> {
			MonthlyCardLogEntity entity = new MonthlyCardLogEntity();
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
	public ResultObject<List<MonthlyCardLogDto>> getLogs(long projectId, Pageable pageable) {
		
		Page<MonthlyCardLogEntity> logEntities = monthlyCardLogRepository.findByProjectID(projectId, pageable);
		List<MonthlyCardLogDto> dtos = this.mapLog(logEntities.getContent());
		ResultObject<List<MonthlyCardLogDto>> result = new ResultObject<>();
		result.setData(dtos);
		result.setStatus(200);
		result.setTotalPages(logEntities.getTotalPages());
		result.setTotalRows(logEntities.getTotalElements());
		return result;
	}


	@Override
	public ResultObject<List<MonthlyCardLogDto>> save(MonthlyCardLogDto monthlyCardLogDto) {
		if(monthlyCardLogDto == null) {
			return null;
		}
		MonthlyCardLogEntity entity =  new MonthlyCardLogEntity();
		mapper.map(monthlyCardLogDto, entity);
		entity  =  monthlyCardLogRepository.save(entity);
		mapper.map(entity, monthlyCardLogDto);
		ResultObject<List<MonthlyCardLogDto>> result = new ResultObject<>();
		List<MonthlyCardLogDto> dtos = new ArrayList<>();
		dtos.add(monthlyCardLogDto);
		result.setData(dtos);
		return result;
	}


	@Override
	public ResultObject<List<MonthlyCardLogDto>> save(List<MonthlyCardLogDto> monthlyCardLogDtos) {
		if(monthlyCardLogDtos == null) {
			return new ResultObject<>();
		}
		List<MonthlyCardLogEntity> entities = this.reMap(monthlyCardLogDtos);
		entities = monthlyCardLogRepository.saveAll(entities);
		ResultObject<List<MonthlyCardLogDto>> result = new ResultObject<>();
		result.setData(this.mapLog(entities));
		return result;
	}
}

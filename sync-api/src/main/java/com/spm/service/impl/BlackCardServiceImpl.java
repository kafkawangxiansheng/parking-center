package com.spm.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spm.dtos.BlackCarDto;
import com.spm.entity.BlackCarEntity;
import com.spm.repository.BlackCarRepository;
import com.spm.service.BlackCarService;


@Service
@Transactional
public class BlackCardServiceImpl implements BlackCarService {

	ModelMapper mapper;

	@PostConstruct
	public void init() {
		mapper = new ModelMapper();
	}
	@Autowired
    private BlackCarRepository blackCarRepository;
     
    
	private List<BlackCarDto> map(List<BlackCarEntity> source) {

		List<BlackCarDto> rtn = new ArrayList<>();
		source.stream().map((entity) -> {
			BlackCarDto dto = new BlackCarDto();
			mapper.map(entity, dto);
			return dto;
		}).forEachOrdered((dto) -> {
			rtn.add(dto);
		});
		return rtn;
	}
	
	private List<BlackCarEntity> reMap(List<BlackCarDto> source) {

		List<BlackCarEntity> rtn = new ArrayList<>();
		source.stream().map((dto) -> {
			BlackCarEntity entity = new BlackCarEntity();
			mapper.map(dto,entity);
			return entity;
		}).forEachOrdered((entity) -> {
			rtn.add(entity);
		});
		return rtn;
	}


	@Override
	public BlackCarDto getById(long id) {
		BlackCarDto dto = new BlackCarDto();
		BlackCarEntity entity = blackCarRepository.getOne(id);
		if(entity == null) {
			return null;
		}
		mapper.map(entity, dto);
		return dto;
	}
	
	
	
	@Override
	public List<BlackCarDto> findAll() {
		List<BlackCarEntity> entities = blackCarRepository.findAll();
		List<BlackCarDto> dtos = this.map(entities);
		return dtos;
	}
	
	@Override
	public BlackCarDto findByDigit(String digit) {
		BlackCarEntity entity = blackCarRepository.findByDigit(digit);
		if(entity == null) {
			return null;
		}
		BlackCarDto dto = new BlackCarDto();
		mapper.map(entity, dto);
		return dto;
	}


	@Override
	public void save(BlackCarDto blackCarDto) {
		BlackCarEntity entity = new BlackCarEntity();
		mapper.map(blackCarDto, entity);
		entity.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
		blackCarRepository.save(entity);
	}


	@Override
	public void delete(long id) {
		blackCarRepository.deleteById(id);
	}


	@Override
	public void save(List<BlackCarDto> blackCarDtos) {
		List<BlackCarEntity>  entities = this.reMap(blackCarDtos);
		blackCarRepository.saveAll(entities);
		
	}
	
	@Override
	public List<BlackCarDto> syncAll() {
		List<BlackCarEntity> entities = blackCarRepository.syncAll();
		//update all entities with last_sync and updated to current time
		List<BlackCarDto> dtos = this.map(entities);
		entities.forEach(entity ->  {
			entity.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
			entity.setLastSync(Calendar.getInstance().getTimeInMillis());
		});
		blackCarRepository.saveAll(entities);
		
		return dtos;
	}
		
	
}

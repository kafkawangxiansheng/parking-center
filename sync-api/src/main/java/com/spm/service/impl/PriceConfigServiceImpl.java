package com.spm.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spm.dtos.PriceConfigDto;
import com.spm.entity.PriceConfigEntity;
import com.spm.repository.PriceConfigRepository;
import com.spm.service.PriceConfigService;


@Service
@Transactional
public class PriceConfigServiceImpl implements PriceConfigService{

	ModelMapper mapper;

	@PostConstruct
	public void init() {
		mapper = new ModelMapper();
	}
	@Autowired
    private PriceConfigRepository priceConfigRepository;
     
    
	private List<PriceConfigDto> map(List<PriceConfigEntity> source) {

		List<PriceConfigDto> rtn = new ArrayList<>();
		source.stream().map((entity) -> {
			PriceConfigDto dto = new PriceConfigDto();
			mapper.map(entity, dto);
			return dto;
		}).forEachOrdered((dto) -> {
			rtn.add(dto);
		});
		return rtn;
	}
	
	private List<PriceConfigEntity> reMap(List<PriceConfigDto> source) {

		List<PriceConfigEntity> rtn = new ArrayList<>();
		source.stream().map((dto) -> {
			PriceConfigEntity entity = new PriceConfigEntity();
			mapper.map(dto,entity);
			return entity;
		}).forEachOrdered((entity) -> {
			rtn.add(entity);
		});
		return rtn;
	}


	@Override
	public PriceConfigDto getById(long id) {
		PriceConfigDto dto = new PriceConfigDto();
		PriceConfigEntity entity = priceConfigRepository.getOne(id);
		if(entity == null) {
			return null;
		}
		mapper.map(entity, dto);
		return dto;
	}
	
	
	
	@Override
	public List<PriceConfigDto> findAllByProjectId(long projectId) {
		List<PriceConfigEntity> entities = priceConfigRepository.findAllByProjectId(projectId);
		List<PriceConfigDto> dtos = this.map(entities);
		return dtos;
	}
	
	@Override
	public PriceConfigDto findAllByProjectIdAndCode(long projectId, String code) {
		PriceConfigEntity entity = priceConfigRepository.findAllByProjectIdAndCode(projectId, code);
		if(entity == null) {
			return null;
		}
		PriceConfigDto dto = new PriceConfigDto();
		mapper.map(entity, dto);
		return dto;
	}


	@Override
	public void save(PriceConfigDto priceConfigDto) {
		PriceConfigEntity entity = new PriceConfigEntity();
		mapper.map(priceConfigDto, entity);
		entity.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
		priceConfigRepository.save(entity);
	}


	@Override
	public void delete(long id) {
		priceConfigRepository.deleteById(id);
	}


	@Override
	public void save(List<PriceConfigDto> priceConfigDtos) {
		List<PriceConfigEntity>  entities = this.reMap(priceConfigDtos);
		priceConfigRepository.saveAll(entities);
		
	}
	
	@Override
	public List<PriceConfigDto> syncAllByProjectId(long projectId) {
		List<PriceConfigEntity> entities = priceConfigRepository.syncAllByProjectId(projectId);
		//update all entities with last_sync and updated to current time
		List<PriceConfigDto> dtos = this.map(entities);
		entities.forEach(entity ->  {
			entity.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
			entity.setLastSync(Calendar.getInstance().getTimeInMillis());
		});
		priceConfigRepository.saveAll(entities);
		
		return dtos;
	}
		
	
}

package com.spm.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spm.dtos.CardsDto;
import com.spm.entity.CardsEntity;
import com.spm.repository.CardRepository;
import com.spm.service.CardsService;

/**
 * Created by Vincent on 02/10/2018
 */

@Service
public class CardsServiceImpl implements CardsService {

	@Autowired
	private CardRepository cardRepository;

	ModelMapper mapper;

	private List<CardsDto> map(List<CardsEntity> source) {

		ArrayList<CardsDto> rtn = new ArrayList<>();
		source.stream().map((entity) -> {
			CardsDto dto = new CardsDto();
			mapper.map(entity, dto);
			return dto;
		}).forEachOrdered((dto) -> {
			rtn.add(dto);
		});
		return rtn;
	}
	
	private List<CardsEntity> reMap(List<CardsDto> source) {

		ArrayList<CardsEntity> rtn = new ArrayList<>();
		source.stream().map((dto) -> {
			CardsEntity entity = new CardsEntity();
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
	public CardsDto save(CardsDto cardDto) {
		CardsEntity entity = new CardsEntity();
		mapper.map(cardDto, entity);
		entity.setCreated(Calendar.getInstance().getTimeInMillis());
		entity.setUpdated(Calendar.getInstance().getTimeInMillis());
		entity = cardRepository.save(entity);
		mapper.map(entity, cardDto);
		return cardDto;
	}

	@Override
	public List<CardsDto> findAll() {
		List<CardsEntity> entities = cardRepository.findAll();
		return this.map(entities);
	}

	@Override
	public List<CardsDto> save(List<CardsDto> cardsDtos) {
		List<CardsEntity> cardsEntities = reMap(cardsDtos);
		cardsEntities.forEach(entity ->  {
			entity.setCreated(Calendar.getInstance().getTimeInMillis());
			entity.setUpdated(Calendar.getInstance().getTimeInMillis());
			entity.setLastSync(Calendar.getInstance().getTimeInMillis()-10);
		});
		cardsEntities = cardRepository.saveAll(cardsEntities);
		cardsDtos = map(cardsEntities);
		return cardsDtos;
	}
	
	@Override
	public CardsDto findById(Long cardId) {
		CardsEntity entity = cardRepository.findById(cardId).get();
		CardsDto cardsDto = new CardsDto();
		mapper.map(entity, cardsDto);
		return cardsDto;
	}
	
	@Override
	public CardsDto findByCardCode(String cardCode) {
		CardsEntity entity = cardRepository.findByCode(cardCode);
		if(entity == null) {
			return null;
		}
		CardsDto cardsDto = new CardsDto();
		mapper.map(entity, cardsDto);
		return cardsDto;
	}

	
	@Override
	public List<CardsDto> syncAllByProjectId(long projectId) {
		List<CardsEntity> entities = cardRepository.syncAllByProjectId(projectId);
		//update all entities with last_sync and updated to current time
		List<CardsDto> dtos = this.map(entities);
		entities.forEach(entity ->  {
			entity.setUpdated(Calendar.getInstance().getTimeInMillis());
			entity.setLastSync(Calendar.getInstance().getTimeInMillis());
		});
		cardRepository.saveAll(entities);
		
		return dtos;
	}

}

package com.spm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spm.dto.CardsDto;
import com.spm.dto.ResultObject;
import com.spm.entity.CardsEntity;
import com.spm.repository.CardRepository;
import com.spm.search.form.CardSearchForm;
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
		entity = cardRepository.save(entity);
		mapper.map(entity, cardDto);
		return cardDto;
	}

	@Override
	public ResultObject<List<CardsDto>> findAll(Pageable pageable, CardSearchForm cardSearchForm) {
		Page<CardsEntity> entities = cardRepository.search(cardSearchForm.getCode(), cardSearchForm.getStt(), cardSearchForm.getVehicleId(), pageable);
		ResultObject<List<CardsDto>> resultObject = new ResultObject<>();
		resultObject.setData(this.map(entities.getContent()));
		resultObject.setTotalPages(entities.getTotalPages());
		return resultObject;
	}

	@Override
	public List<CardsDto> save(List<CardsDto> cardsDtos) {
		List<CardsEntity> cardsEntities = reMap(cardsDtos);
		cardsEntities = cardRepository.saveAll(cardsEntities);
		cardsDtos = map(cardsEntities);
		return cardsDtos;
	}
	
	@Override
	public ResultObject<List<CardsDto>> findById(Long cardId) {
		ResultObject<List<CardsDto>> resultObj = new ResultObject<>();
		CardsEntity entity = cardRepository.findById(cardId).get();
		List<CardsDto> listCardDto = new ArrayList<CardsDto>();
		CardsDto cardsDto = new CardsDto();
		mapper.map(entity, cardsDto);
		listCardDto.add(cardsDto);
		resultObj.setData(listCardDto);
		return resultObj;
	}
	
	@Override
	public ResultObject<List<CardsDto>> findAllDisabledCard(CardSearchForm cardSearchForm){
		List<CardsEntity> entities = cardRepository.findAllByDisable(Integer.valueOf(cardSearchForm.getDisable()));
		ResultObject<List<CardsDto>> resultObject = new ResultObject<>();
		resultObject.setData(this.map(entities));
		return resultObject;
	}


	@Override
	public boolean checkCardAndCardType(String code, int cardType) {
		CardsEntity cardEntity = cardRepository.findByCodeAndVehicleCardType(code, cardType);
		if(cardEntity != null) {
			return true;
		}else {
			return false;
		}
	}

}

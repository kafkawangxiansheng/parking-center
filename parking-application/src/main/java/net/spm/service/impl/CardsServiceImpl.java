package net.spm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.spm.dto.CardsDto;
import net.spm.jpa.entity.CardsEntity;
import net.spm.repository.CardRepository;
import net.spm.service.CardsService;

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
	public void delete(CardsDto cardsDto) {
		CardsEntity entity = new CardsEntity();
		mapper.map(cardsDto, entity);

	}


	@Override
	public List<CardsDto> findAll() {
		List<CardsEntity> entities = cardRepository.findAll();

		return this.map(entities);
	}

	@Override
	public List<CardsDto> save(List<CardsDto> cardsDtos) {
		List<CardsEntity> cardsEntities = reMap(cardsDtos);
		
		cardsEntities = cardRepository.save(cardsEntities);
		cardsDtos = map(cardsEntities);
		return cardsDtos;
	}

}

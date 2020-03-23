package com.spm.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
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
import com.spm.service.cache.VehicleCache;

/**
 * Created by Vincent on 02/10/2018
 */

@Service
public class CardsServiceImpl implements CardsService {

	@Autowired
	private CardRepository cardRepository;

	@Autowired
	private VehicleCache vehicleCache;
	
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
	public ResultObject<List<CardsDto>> addNewCard(CardsDto cardDto) {
		ResultObject<List<CardsDto>> resultObject = new ResultObject<>();
		List<CardsDto> listDto = new ArrayList<CardsDto>();
		CardsEntity checkCardCode = cardRepository.findByCode(cardDto.getCode());
		if(checkCardCode == null) {
			CardsEntity entities = new CardsEntity();
			mapper.map(cardDto, entities);
			entities.setUpdated(Calendar.getInstance().getTimeInMillis());
			entities.setCreated(Calendar.getInstance().getTimeInMillis());
			entities.setLastSync(0L);
			cardRepository.save(entities);
			mapper.map(entities, cardDto);
			listDto.add(cardDto);
			resultObject.setData(listDto);
			return resultObject;
		}else {
			return null;
		}
		
	}

	@Override
	public ResultObject<List<CardsDto>> findAll(Pageable pageable, CardSearchForm cardSearchForm) {
		Page<CardsEntity> entities = cardRepository.search(
				cardSearchForm.getCode(), cardSearchForm.getStt(),
				cardSearchForm.getVehicleId(),
				cardSearchForm.getProjectId(),
				pageable);
		ResultObject<List<CardsDto>> resultObject = new ResultObject<>();
		resultObject.setData(this.map(entities.getContent()));
		if(resultObject.getData()  != null && resultObject.getData().size()>0) {
			resultObject.getData().forEach(cardDto -> {
				String key = cardDto.getProjectId()+"_"+cardDto.getVehicleId();
				cardDto.setVehicleName(vehicleCache.get(key));
			});
		}
		resultObject.setTotalPages(entities.getTotalPages());
		return resultObject;
	}

	@Override
	public List<CardsDto> save(List<CardsDto> cardsDtos) {
		List<CardsEntity> cardsEntities = reMap(cardsDtos);
		cardsEntities.forEach(entity ->  {
			entity.setUpdated(Calendar.getInstance().getTimeInMillis());
			entity.setCreated(Calendar.getInstance().getTimeInMillis());
			entity.setLastSync(0L);
		});
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
		List<CardsEntity> entities = cardRepository.searchDisable(cardSearchForm.getCode(), 1);
		ResultObject<List<CardsDto>> resultObject = new ResultObject<>();
		resultObject.setData(this.map(entities));
		return resultObject;
	}


	@Override
	public boolean checkCardAndCardType(String code, int cardType) {
		CardsEntity entity =  cardRepository.findByCodeAndVehicleCardType(code, cardType);
		if(entity != null) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public void activebyId(int cardId) {
		CardsEntity entity = cardRepository.findById(Long.valueOf(cardId)).get();
		entity.setDisable(0);
//		entity.setUpdated(entity.getUpdated()+1);
		entity.setUpdated(Calendar.getInstance().getTimeInMillis());
		cardRepository.save(entity);
	}

	@Override
	public void delete(Long id) {
		CardsEntity entity = cardRepository.findById(id).get();
		entity.setDeleted(1);
		entity.setUpdated(Calendar.getInstance().getTimeInMillis());
		cardRepository.save(entity);
	}

	@Override
	public CardsDto checkCardAndCardTypeAndProjectId(String code, int cardType, long projectId) {
		CardsEntity entity =  cardRepository.findByCodeAndVehicleCardTypeAndProjectId(code, cardType, projectId);
		if(entity != null) {
			CardsDto cardDto = new CardsDto();
			 mapper.map(entity,cardDto);
			 return cardDto;
		}else {
			return null;
		}
	}

	@Override
	public ResultObject<List<CardsDto>> update(CardsDto cardDto) {
		ResultObject<List<CardsDto>> resultObject = new ResultObject<>();
		List<CardsDto> listDto = new ArrayList<CardsDto>();
		CardsEntity cardsEntity = cardRepository.findById(cardDto.getId()).get();
		if(cardsEntity.getCode().equals(cardDto.getCode())) {
			CardsEntity entities = new CardsEntity();
			mapper.map(cardDto, entities);
			entities.setUpdated(Calendar.getInstance().getTimeInMillis());
			cardRepository.save(entities);
			mapper.map(entities, cardDto);
			listDto.add(cardDto);
			resultObject.setData(listDto);
			return resultObject;
		}else {
			CardsEntity checkCardCode = cardRepository.findByCode(cardDto.getCode());
			if(checkCardCode == null) {
				CardsEntity entities = new CardsEntity();
				mapper.map(cardDto, entities);
				entities.setUpdated(Calendar.getInstance().getTimeInMillis());
				cardRepository.save(entities);
				mapper.map(entities, cardDto);
				listDto.add(cardDto);
				resultObject.setData(listDto);
				return resultObject;
			}else {
				return null;
			}
		}
	}

}

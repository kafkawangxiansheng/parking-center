package com.spm.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spm.dtos.MonthlyCardDto;
import com.spm.entity.MonthlyCardEntity;
import com.spm.entity.MonthlyCardLogEntity;
import com.spm.enums.LogType;
import com.spm.repository.MonthlyCardLogRepository;
import com.spm.repository.MonthlyCardRepository;
import com.spm.service.MonthlyCardService;

/**
 * Created by Vincent on 02/10/2018
 */

@Service
public class MonthlyCardServiceImpl implements MonthlyCardService {

	@Autowired
	private MonthlyCardRepository monthlyCardRepository;
	
	@Autowired
	private MonthlyCardLogRepository monthlyCardLogRepository;

	ModelMapper mapper;

	private List<MonthlyCardDto> map(List<MonthlyCardEntity> source) {

		ArrayList<MonthlyCardDto> rtn = new ArrayList<>();
		source.stream().map((entity) -> {
			MonthlyCardDto dto = new MonthlyCardDto();
			mapper.map(entity, dto);
			return dto;
		}).forEachOrdered((dto) -> {
			rtn.add(dto);
		});
		return rtn;
	}
	
	private List<MonthlyCardEntity> reMap(List<MonthlyCardDto> source) {

		ArrayList<MonthlyCardEntity> rtn = new ArrayList<>();
		source.stream().map((dto) -> {
			MonthlyCardEntity entity = new MonthlyCardEntity();
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
	public MonthlyCardDto findByCardCode(String cardCode) {
		MonthlyCardEntity entity = monthlyCardRepository.findByCardCode(cardCode);
		if(entity ==  null)  {
			return null;
		}
		MonthlyCardDto monthlyCardDto = new MonthlyCardDto();
		mapper.map(entity, monthlyCardDto);
		return monthlyCardDto;
	}
	

	@Override
	public void delete(MonthlyCardDto monthlyCardDto) {
		MonthlyCardEntity entity = new MonthlyCardEntity();
		mapper.map(monthlyCardDto, entity);
		insertMonthlyLog(entity, LogType.DElETE);
		monthlyCardRepository.delete(entity);

	}


	@Override
	public List<MonthlyCardDto> findAll() {
		List<MonthlyCardEntity> entities = monthlyCardRepository.findAll();
		return this.map(entities);
	}

	
	@Override
	public List<MonthlyCardDto> syncAllByProjectId(long projectId) {
		List<MonthlyCardEntity> entities = monthlyCardRepository.syncAllByProjectId(projectId);
		//update all entities with last_sync and updated to current time
		List<MonthlyCardDto> dtos = this.map(entities);
		entities.forEach(entity ->  {
			entity.setLastSync(Calendar.getInstance().getTimeInMillis());
		});
		monthlyCardRepository.saveAll(entities);
		
		return dtos;
	}

	@Override
	public MonthlyCardDto save(MonthlyCardDto monthlyCardDto) {
		MonthlyCardEntity entity = new MonthlyCardEntity();
		mapper.map(monthlyCardDto, entity);
		entity = monthlyCardRepository.save(entity);
		insertMonthlyLog(entity, LogType.CREATE);
		mapper.map(entity, monthlyCardDto);
		return monthlyCardDto;
	}
	
	@Override
	public List<MonthlyCardDto> save(List<MonthlyCardDto> monthlyCardDtos) {
		List<MonthlyCardEntity> monthlyCardsEntities = reMap(monthlyCardDtos);
		monthlyCardsEntities.forEach(entity ->  {
			entity.setUpdated(Calendar.getInstance().getTimeInMillis());
			entity.setLastSync(Calendar.getInstance().getTimeInMillis()-10);
		});
		monthlyCardsEntities = monthlyCardRepository.saveAll(monthlyCardsEntities);
		monthlyCardsEntities.forEach(monthlyCardsEntity -> {
			insertMonthlyLog(monthlyCardsEntity, LogType.CREATE);
		});
		monthlyCardDtos = map(monthlyCardsEntities);
		return monthlyCardDtos;
	}
	
	@Override
	public MonthlyCardDto update(MonthlyCardDto monthlyCardDto) {
		MonthlyCardEntity entity = new MonthlyCardEntity();
		mapper.map(monthlyCardDto, entity);
		entity = monthlyCardRepository.save(entity);
		insertMonthlyLog(entity, LogType.UPDATE);
		mapper.map(entity, monthlyCardDto);
		return monthlyCardDto;
	}

	@Override
	public List<MonthlyCardDto> update(List<MonthlyCardDto> monthlyCardDtos) {
		List<MonthlyCardEntity> monthlyCardsEntities = reMap(monthlyCardDtos);
		monthlyCardsEntities.forEach(entity ->  {
			entity.setUpdated(Calendar.getInstance().getTimeInMillis());
			entity.setLastSync(Calendar.getInstance().getTimeInMillis()-10);
		});
		monthlyCardsEntities = monthlyCardRepository.saveAll(monthlyCardsEntities);
		monthlyCardsEntities.forEach(monthlyCardsEntity -> {
			insertMonthlyLog(monthlyCardsEntity, LogType.UPDATE);
		});
		monthlyCardDtos = map(monthlyCardsEntities);
		return monthlyCardDtos;
	}

	private void insertMonthlyLog(MonthlyCardEntity monthlyCardsEntity, LogType logType) {
		MonthlyCardLogEntity logEntity = new MonthlyCardLogEntity();
		logEntity.setAccount(String.valueOf(monthlyCardsEntity.getAdminId()));
		logEntity.setAddress(monthlyCardsEntity.getAddress());
		logEntity.setCarKind(String.valueOf(monthlyCardsEntity.getVehicleId()));
		logEntity.setChargesAmount(String.valueOf(monthlyCardsEntity.getParkingFee()));
		logEntity.setCmnd(monthlyCardsEntity.getIdNumber());
		logEntity.setCompany(monthlyCardsEntity.getCompany());
		logEntity.setCustomerName(monthlyCardsEntity.getCustomerName());
		logEntity.setDigit(monthlyCardsEntity.getCarNumber());
		logEntity.setEmail(monthlyCardsEntity.getEmail());
		logEntity.setExpirationDate(monthlyCardsEntity.getEndDate());
		logEntity.setIdPart(String.valueOf(monthlyCardsEntity.getAreaId()));
		logEntity.setLogTypeID(logType.getId());
		logEntity.setNote(logType.name());
		logEntity.setProcessDate(Calendar.getInstance().getTime().getTime());
		logEntity.setProjectID(monthlyCardsEntity.getProject().getId());
		logEntity.setRegistrationDate(monthlyCardsEntity.getStartDate());
		logEntity.setStatus(1);
		logEntity.setTicketMonthID(String.valueOf(monthlyCardsEntity.getId()));
		logEntity.setTicketMonthIdentify(monthlyCardsEntity.getCardCode());
		monthlyCardLogRepository.save(logEntity);
	}
}

package com.spm.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spm.common.enums.LogType;
import com.spm.dto.MonthlyCardDto;
import com.spm.dto.ResultObject;
import com.spm.entity.MonthlyCardEntity;
import com.spm.entity.MonthlyCardLogEntity;
import com.spm.repository.MonthlyCardLogRepository;
import com.spm.repository.MonthlyCardRepository;
import com.spm.search.form.MonthlyCardSearchForm;
import com.spm.service.MonthlyCardService;
import com.spm.service.cache.CardCache;
import com.spm.service.cache.VehicleCache;

/**
 * Created by Vincent on 02/10/2018
 */

@Service
public class MonthlyCardServiceImpl implements MonthlyCardService {

	@Autowired
	private MonthlyCardRepository monthlyCardRepository;

	@Autowired
	private MonthlyCardLogRepository monthlyCardLogRepository;

	@Autowired
	private CardCache cardCache;

	@Autowired
	private VehicleCache vehicleCache;

	ModelMapper mapper;

	private List<MonthlyCardDto> map(List<MonthlyCardEntity> source) {

		ArrayList<MonthlyCardDto> rtn = new ArrayList<>();
		source.stream().map((entity) -> {
			MonthlyCardDto dto = new MonthlyCardDto();
			mapper.map(entity, dto);
			dto.setCardStt(cardCache.get(dto.getCardCode())!= null ? cardCache.get(dto.getCardCode()).getStt():"");
			dto.setVehicleName(vehicleCache.get(dto.getProject().getId() + "_" + dto.getVehicleId()));
			dto.setDateNumber(convertToNumberOfDays(dto.getEndDate()));
			return dto;
		}).forEachOrdered((dto) -> {
			rtn.add(dto);
		});
		return rtn;
	}

	private int convertToNumberOfDays(long dateInMiliSeconds) {

		long currentDate = Calendar.getInstance().getTimeInMillis();

		return (int) TimeUnit.DAYS.convert(dateInMiliSeconds - currentDate, TimeUnit.MILLISECONDS);
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
	public ResultObject<List<MonthlyCardDto>> save(MonthlyCardDto monthlyCardDto) {
		ResultObject<List<MonthlyCardDto>> resultObj = new ResultObject<>();
		List<MonthlyCardDto> listObject = new ArrayList<>();
		MonthlyCardEntity entity = new MonthlyCardEntity();
		mapper.map(monthlyCardDto, entity);
		entity.setUpdated(Calendar.getInstance().getTimeInMillis());
		entity = monthlyCardRepository.save(entity);
		mapper.map(entity ,monthlyCardDto );
		insertMonthlyLog(monthlyCardDto, LogType.CREATE);
		mapper.map(entity, monthlyCardDto);
		listObject.add(monthlyCardDto);
		resultObj.setData(listObject);
		return resultObj;
	}

	@Override
	public ResultObject<List<MonthlyCardDto>> save(List<MonthlyCardDto> listMonthlyCardDto) {
		ResultObject<List<MonthlyCardDto>> resultObj = new ResultObject<>();
		List<MonthlyCardEntity> monthlyCardEntities = reMap(listMonthlyCardDto);
		monthlyCardEntities.forEach(entity -> {
			entity.setUpdated(Calendar.getInstance().getTimeInMillis());
		});
		monthlyCardEntities = monthlyCardRepository.saveAll(monthlyCardEntities);
		listMonthlyCardDto.forEach(monthlyCardDto -> {
			
			insertMonthlyLog(monthlyCardDto, LogType.CREATE);
		});
		resultObj.setData(map(monthlyCardEntities));
		return resultObj;
	}

	@Override
	public ResultObject<List<MonthlyCardDto>> update(MonthlyCardDto monthlyCardDto) {
		ResultObject<List<MonthlyCardDto>> resultObj = new ResultObject<>();
		List<MonthlyCardDto> listObject = new ArrayList<>();
		MonthlyCardEntity entity = new MonthlyCardEntity();
		mapper.map(monthlyCardDto, entity);
		entity.setUpdated(Calendar.getInstance().getTimeInMillis());
		entity = monthlyCardRepository.save(entity);
		mapper.map(entity, monthlyCardDto);
		insertMonthlyLog(monthlyCardDto, LogType.UPDATE);
		listObject.add(monthlyCardDto);
		resultObj.setData(listObject);
		return resultObj;
	}

	@Override
	public ResultObject<List<MonthlyCardDto>> update(List<MonthlyCardDto> listMonthlyCardDto) {
		ResultObject<List<MonthlyCardDto>> resultObj = new ResultObject<>();
		List<MonthlyCardEntity> monthlyCardEntities = reMap(listMonthlyCardDto);
		monthlyCardEntities.forEach(entity -> {
			entity.setUpdated(Calendar.getInstance().getTimeInMillis());
		});
		monthlyCardEntities = monthlyCardRepository.saveAll(monthlyCardEntities);
		listMonthlyCardDto = map(monthlyCardEntities);
		
		listMonthlyCardDto.forEach(monthlyCardDto -> {
			insertMonthlyLog(monthlyCardDto, LogType.UPDATE);
		});
		resultObj.setData(map(monthlyCardEntities));
		return resultObj;
	}

	@Override
	public void delete(Long id, String username) {
		MonthlyCardEntity entity = monthlyCardRepository.findById(id).get();
		entity.setUpdated(Calendar.getInstance().getTimeInMillis());
		entity.setDeleted(1);
		MonthlyCardDto monthCardDto = new MonthlyCardDto();
		mapper.map(entity, monthCardDto);
		monthCardDto.setUsername(username);
		insertMonthlyLog(monthCardDto, LogType.DElETE);
		monthlyCardRepository.save(entity);
	}

	@Override
	public ResultObject<List<MonthlyCardDto>> findAll() {
		ResultObject<List<MonthlyCardDto>> resultObj = new ResultObject<>();
		List<MonthlyCardEntity> ListEntity = monthlyCardRepository.findAll();
		resultObj.setData(this.map(ListEntity));
		return resultObj;
	}

	@Override
	public ResultObject<List<MonthlyCardDto>> findById(Long id) {
		ResultObject<List<MonthlyCardDto>> resultObj = new ResultObject<>();
		MonthlyCardEntity entity = monthlyCardRepository.findById(id).get();
		List<MonthlyCardDto> listProjectDto = new ArrayList<MonthlyCardDto>();
		MonthlyCardDto monthlyCardDto = new MonthlyCardDto();
		mapper.map(entity, monthlyCardDto);
		listProjectDto.add(monthlyCardDto);
		resultObj.setData(listProjectDto);
		return resultObj;
	}

	@Override
	public MonthlyCardEntity findByCardCode(String cardCode) {
		return monthlyCardRepository.findByCardCode(cardCode);
	}

	@Override
	public ResultObject<List<MonthlyCardDto>> search(Pageable pageable, MonthlyCardSearchForm monthlyCardSearchForm) {

		Page<MonthlyCardEntity> entities = monthlyCardRepository.search(monthlyCardSearchForm.getCardCode(),
				monthlyCardSearchForm.getVehicleId(), monthlyCardSearchForm.getCustomerName(),
				monthlyCardSearchForm.getStatusDate(), Calendar.getInstance().getTimeInMillis(),
				monthlyCardSearchForm.getProjectId(), pageable);

		ResultObject<List<MonthlyCardDto>> resultObject = new ResultObject<>();
		resultObject.setData(this.map(entities.getContent()));
		resultObject.setTotalPages(entities.getTotalPages());
		resultObject.setTotalRows(entities.getTotalElements());
		return resultObject;
	}

	@Override
	public ResultObject<List<MonthlyCardDto>> renewalSearch(Pageable pageable,
			MonthlyCardSearchForm monthlyCardSearchForm) {
		
		Page<MonthlyCardEntity> entities = monthlyCardRepository.renewalSearch(monthlyCardSearchForm.getCardCode(),
				monthlyCardSearchForm.getCustomerName(), Calendar.getInstance().getTimeInMillis(), monthlyCardSearchForm.getProjectId(),
				pageable);

		ResultObject<List<MonthlyCardDto>> resultObject = new ResultObject<>();
		resultObject.setData(this.map(entities.getContent()));
		resultObject.setTotalPages(entities.getTotalPages());
		resultObject.setTotalRows(entities.getTotalElements());
		return resultObject;
	}

	@Override
	public ResultObject<List<MonthlyCardDto>> renewalFindOne(long id) {
		ResultObject<List<MonthlyCardDto>> resultObject = new ResultObject<>();
		List<MonthlyCardDto> listObject = new ArrayList<MonthlyCardDto>();
		MonthlyCardDto dto = new MonthlyCardDto();
		MonthlyCardEntity entities = monthlyCardRepository.findById(id).get();
		mapper.map(entities, dto);
		listObject.add(dto);
		resultObject.setData(listObject);
		return resultObject;
	}

	@Override
	public ResultObject<List<MonthlyCardDto>> renewalUpdate(MonthlyCardDto monthlyCardDto) {
		ResultObject<List<MonthlyCardDto>> resultObject = new ResultObject<>();
		List<MonthlyCardDto> listObject = new ArrayList<MonthlyCardDto>();
		MonthlyCardDto dto = new MonthlyCardDto();

		MonthlyCardEntity entities = monthlyCardRepository.findById(monthlyCardDto.getId()).get();
		entities.setStartDate(monthlyCardDto.getStartDate());
		entities.setEndDate(monthlyCardDto.getEndDate());
		entities.setUpdated(Calendar.getInstance().getTimeInMillis());
		monthlyCardRepository.save(entities);
		mapper.map(entities, dto);
		listObject.add(dto);
		resultObject.setData(listObject);
		return resultObject;
	}

	@Override
	public MonthlyCardDto getById(Long id) {
		MonthlyCardEntity entity = monthlyCardRepository.findById(id).get();
		MonthlyCardDto dto = new MonthlyCardDto();
		mapper.map(entity, dto);
		return dto;
	}

	private void insertMonthlyLog(MonthlyCardDto monthlyCardDto, LogType logType) {
		MonthlyCardLogEntity logEntity = new MonthlyCardLogEntity();
		logEntity.setAccount(monthlyCardDto.getUsername());
		logEntity.setAddress(monthlyCardDto.getAddress());
		logEntity.setCarKind(monthlyCardDto.getBrand());
		logEntity.setChargesAmount(String.valueOf(monthlyCardDto.getParkingFee()));
		logEntity.setCmnd(monthlyCardDto.getIdNumber());
		logEntity.setCompany(monthlyCardDto.getCompany());
		logEntity.setCustomerName(monthlyCardDto.getCustomerName());
		logEntity.setDigit(monthlyCardDto.getCarNumber());
		logEntity.setEmail(monthlyCardDto.getEmail());
		logEntity.setExpirationDate(monthlyCardDto.getEndDate());
		logEntity.setIdPart(String.valueOf(monthlyCardDto.getAreaId()));
		logEntity.setLogTypeID(logType.getId());
		logEntity.setNote(logType.name());
		logEntity.setProcessDate(Calendar.getInstance().getTime().getTime());
		logEntity.setProjectID(monthlyCardDto.getProject().getId());
		logEntity.setRegistrationDate(monthlyCardDto.getStartDate());
		logEntity.setStatus(1);
		logEntity.setTicketMonthID(String.valueOf(monthlyCardDto.getId()));
		logEntity.setTicketMonthIdentify(monthlyCardDto.getCardCode());
		monthlyCardLogRepository.save(logEntity);
	}
}

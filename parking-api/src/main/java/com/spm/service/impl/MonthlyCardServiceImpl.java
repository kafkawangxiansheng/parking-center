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

import com.spm.dto.MonthlyCardDto;
import com.spm.dto.ResultObject;
import com.spm.entity.MonthlyCardEntity;
import com.spm.repository.MonthlyCardRepository;
import com.spm.search.form.MonthlyCradSearchForm;
import com.spm.service.MonthlyCardService;

/**
 * Created by Vincent on 02/10/2018
 */

@Service
public class MonthlyCardServiceImpl implements MonthlyCardService {

	@Autowired
	private MonthlyCardRepository monthlyCardRepository;

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
	public ResultObject<List<MonthlyCardDto>> save(MonthlyCardDto monthlyCardDto) {
		ResultObject<List<MonthlyCardDto>> resultObj = new ResultObject<>();
		List<MonthlyCardDto> listObject = new ArrayList<>();
		MonthlyCardEntity entity = new MonthlyCardEntity();
		mapper.map(monthlyCardDto, entity);
		entity.setUpdated(Calendar.getInstance().getTimeInMillis());
		entity = monthlyCardRepository.save(entity);
		mapper.map(entity, monthlyCardDto);
		listObject.add(monthlyCardDto);
		resultObj.setData(listObject);
		return resultObj;
	}

	@Override
	public ResultObject<List<MonthlyCardDto>> save(List<MonthlyCardDto> listMonthlyCardDto) {
		ResultObject<List<MonthlyCardDto>> resultObj = new ResultObject<>();
		List<MonthlyCardEntity> projectsEntities = reMap(listMonthlyCardDto);
		projectsEntities.forEach(entity ->  {
			entity.setUpdated(Calendar.getInstance().getTimeInMillis());
		});
		projectsEntities = monthlyCardRepository.saveAll(projectsEntities);
		resultObj.setData(map(projectsEntities));
		return resultObj;
	}

	@Override
	public void delete(Long id) {
		monthlyCardRepository.deleteById(id);
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
	public ResultObject<List<MonthlyCardDto>> search(Pageable pageable,
			MonthlyCradSearchForm monthlyCradSearchForm) {
		
		Page<MonthlyCardEntity> entities = monthlyCardRepository.search(
				monthlyCradSearchForm.getCardCode(), 
				monthlyCradSearchForm.getVehicleId(),
				monthlyCradSearchForm.getCustomerName(),
//				monthlyCradSearchForm.getStatusDate(),
//				monthlyCradSearchForm.getNumberEndDate(),
				monthlyCradSearchForm.getProjectId(),
				pageable);
		
		ResultObject<List<MonthlyCardDto>> resultObject = new ResultObject<>();
		resultObject.setData(this.map(entities.getContent()));
		resultObject.setTotalPages(entities.getTotalPages());
		resultObject.setTotalRows(entities.getTotalElements());
		return resultObject;
	}

	@Override
	public ResultObject<List<MonthlyCardDto>> renewalSearch(MonthlyCradSearchForm monthlyCradSearchForm) {
		List<MonthlyCardEntity> entities = monthlyCardRepository.renewalSearch(
				monthlyCradSearchForm.getCardCode(), 
				monthlyCradSearchForm.getCustomerName());
		
		ResultObject<List<MonthlyCardDto>> resultObject = new ResultObject<>();
		resultObject.setData(this.map(entities));
		return resultObject;
	}

}

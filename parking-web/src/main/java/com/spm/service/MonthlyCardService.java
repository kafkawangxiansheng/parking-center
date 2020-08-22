package com.spm.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.spm.dto.MonthlyCardDto;
import com.spm.dto.ResultObject;
import com.spm.search.form.MonthlyCardSearchForm;

public interface MonthlyCardService {
	ResultObject<List<MonthlyCardDto>> getAllMonthlyCard();
	
	ResultObject<List<MonthlyCardDto>> getAllMonthlyCard(MonthlyCardSearchForm monthlyCardSearchForm, Pageable pageable);
	
	MonthlyCardDto getMonthlyCardById(Long monthlyCardId);
	
	boolean addMonthlyCard(MonthlyCardDto monthlyCardDto);
	
	void deleteMonthlyCard(Long id, String username);
	
	boolean updateMonthlyCard(MonthlyCardDto monthlyCardDto);
	
	ResultObject<List<MonthlyCardDto>> getRenewal(MonthlyCardSearchForm monthlyCardSearchForm , Pageable pageable);
	
	boolean revewalMonthlyCardUpdate(MonthlyCardDto monthlyCardDto);
}

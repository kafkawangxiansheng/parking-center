package com.spm.service;

import java.util.List;

import com.spm.dto.MonthlyCardDto;
import com.spm.dto.ResultObject;

public interface MonthlyCradService {
	ResultObject<List<MonthlyCardDto>> getAllMonthlyCard();
	
	MonthlyCardDto getMonthlyCardById(Long monthlyCardId);
	
	boolean addMonthlyCard(MonthlyCardDto monthlyCardDto);
	
	void deleteMonthlyCard(Long id);
	
	boolean updateMonthlyCard(MonthlyCardDto monthlyCardDto);
	
}

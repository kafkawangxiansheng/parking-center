package com.spm.service;

import java.util.List;

import com.spm.dtos.BlackCarDto;

public interface BlackCarService {
	
	BlackCarDto getById(long id);
	
	
	List<BlackCarDto> findAll(); 
	
	BlackCarDto findByDigit(String digit); 
	
	void save(BlackCarDto blackCarDto);
	
	void save(List<BlackCarDto> blackCarDtos);
	
	void delete(long id);
	
	List<BlackCarDto> syncAll();
	
}

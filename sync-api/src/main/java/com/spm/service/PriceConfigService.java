package com.spm.service;

import java.util.List;

import com.spm.dtos.PriceConfigDto;

public interface PriceConfigService {
	
	PriceConfigDto getById(long id);
	
	
	List<PriceConfigDto> findAllByProjectId(long projectId); 
	
	PriceConfigDto findAllByProjectIdAndCode(long projectId, String code); 
	
	void save(PriceConfigDto priceConfigDto);
	
	void save(List<PriceConfigDto> priceConfigDtos);
	
	void delete(long id);
	
	List<PriceConfigDto> syncAllByProjectId(long projectId);
	
}

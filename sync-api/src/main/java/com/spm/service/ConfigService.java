package com.spm.service;

import java.util.List;

import com.spm.dtos.ConfigDto;

public interface ConfigService {
	
	ConfigDto getById(long id);
	
	
	List<ConfigDto> findAllByProjectId(long projectId); 
	
	ConfigDto findAllByProjectIdAndCode(long projectId, String code); 
	
	void save(ConfigDto configDto);
	
	void save(List<ConfigDto> configDtos);
	
	void delete(long id);
	
	List<ConfigDto> syncAllByProjectId(long projectId);
	
}

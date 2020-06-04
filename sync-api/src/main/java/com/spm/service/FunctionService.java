package com.spm.service;

import java.util.List;

import com.spm.dtos.FunctionDto;


public interface FunctionService {
	
	FunctionDto save(FunctionDto functionDto);
	
	List<FunctionDto> save(List<FunctionDto> functionDtos);
	
	FunctionDto findById(Long id);
	
	List<FunctionDto> findByProjectId(Long projectId);
	
	FunctionDto findByProjectIdAndFunctionId(Long projectId, String functionId);
	
	void delete(Long id);
	
	List<FunctionDto> getListAll(Long projectId);
	
	FunctionDto update(FunctionDto functionDto);
	
	public List<FunctionDto> syncAllByProjectId(long projectId);
	
}

package com.spm.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.spm.dto.MonthlyCardLogDto;
import com.spm.dto.ResultObject;

/**
 * Created by Vincent 23/05/2018
 */
public interface MonthlyCardLogService {
    
	ResultObject<List<MonthlyCardLogDto>> getLogs(long projectId, Pageable pageable);
	
	ResultObject<List<MonthlyCardLogDto>> save(MonthlyCardLogDto monthlyCardLogDto);
	
	ResultObject<List<MonthlyCardLogDto>> save(List<MonthlyCardLogDto> monthlyCardLogDtos);
    
}
 
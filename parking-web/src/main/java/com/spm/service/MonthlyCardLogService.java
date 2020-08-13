package com.spm.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.spm.dto.MonthlyCardLogDto;
import com.spm.dto.ResultObject;

public interface MonthlyCardLogService {
	ResultObject<List<MonthlyCardLogDto>> getAllMonthlyCardLog(Long projectId, Pageable pageable);
}

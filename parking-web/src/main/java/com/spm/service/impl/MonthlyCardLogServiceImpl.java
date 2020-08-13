package com.spm.service.impl;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spm.common.RestUtils;
import com.spm.common.URLConstants;
import com.spm.dto.MonthlyCardLogDto;
import com.spm.dto.ResultObject;
import com.spm.service.MonthlyCardLogService;

@Service
public class MonthlyCardLogServiceImpl implements MonthlyCardLogService {

	@Override
	public ResultObject<List<MonthlyCardLogDto>> getAllMonthlyCardLog(Long projectId, Pageable pageable) {
		RestUtils<MonthlyCardLogDto> restUtils = new RestUtils<>(MonthlyCardLogDto.class);
		ResultObject<List<MonthlyCardLogDto>> resultFromApi = new ResultObject<>();
		String finalURL = URLConstants.URL_GET_ALL_MONTHLY_CARD_LOG.replaceAll("::page", String.valueOf(pageable.getPageNumber()));
		finalURL = finalURL.replaceAll("::projectId", String.valueOf(projectId));
		resultFromApi = restUtils.get(finalURL);
		return resultFromApi;
	}

}

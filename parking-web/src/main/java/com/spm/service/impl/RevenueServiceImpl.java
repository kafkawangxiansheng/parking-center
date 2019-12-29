package com.spm.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spm.common.RestUtils;
import com.spm.common.URLConstants;
import com.spm.dto.ResultObject;
import com.spm.dto.RevenueDto;
import com.spm.service.RevenueService;

@Service
public class RevenueServiceImpl implements RevenueService {

	@Override
	public ResultObject<List<RevenueDto>> getAllRevenue(int projectId) {
		String finalURL = URLConstants.URL_GET_ALL_REVENUE.replace(":projectId", String.valueOf(projectId));
		RestUtils<RevenueDto> restUtils = new RestUtils<>(RevenueDto.class);
		ResultObject<List<RevenueDto>> resultFromApi = new ResultObject<>();
		resultFromApi = restUtils.get(finalURL);
		return resultFromApi;
	}

}

package com.spm.service;

import java.util.List;

import com.spm.dto.ResultObject;
import com.spm.dto.RevenueDto;

public interface RevenueService {
	ResultObject<List<RevenueDto>> getAllRevenue(int projectId);
	
}

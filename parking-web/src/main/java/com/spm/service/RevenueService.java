package com.spm.service;

import java.util.List;

import com.spm.dto.ResultObject;
import com.spm.dto.RevenueDto;
import com.spm.search.form.RevenueSearchForm;

public interface RevenueService {
	ResultObject<List<RevenueDto>> getAllRevenue(RevenueSearchForm revenueSearchForm);
	
}

package com.spm.service.impl;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spm.common.RestUtils;
import com.spm.common.URLConstants;
import com.spm.common.util.DateUtil;
import com.spm.dto.ResultObject;
import com.spm.dto.RevenueDto;
import com.spm.search.form.RevenueSearchForm;
import com.spm.service.RevenueService;

@Service
public class RevenueServiceImpl implements RevenueService {

	@Override
	public ResultObject<List<RevenueDto>> getAllRevenue(RevenueSearchForm revenueSearchForm) {
		
		String finalURL = URLConstants.URL_GET_ALL_REVENUE.replace("::projectId", String.valueOf(revenueSearchForm.getProjectId()));
		
		String dateFromInLong = revenueSearchForm.getDateFrom()+" "+revenueSearchForm.getHourFrom()+":"+revenueSearchForm.getMinFrom()+":00";
		String dateToInLong = revenueSearchForm.getDateTo()+" "+revenueSearchForm.getHourTo()+":"+revenueSearchForm.getMinTo()+":59";
		
		if(revenueSearchForm.getDateFrom() != null && !revenueSearchForm.getDateFrom().isEmpty()) {
			try {
				dateFromInLong = String.valueOf(DateUtil.parseStringToMiliseconds(dateFromInLong));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		if(revenueSearchForm.getDateTo() != null && !revenueSearchForm.getDateTo().isEmpty()) {
			try {
				dateToInLong = String.valueOf(DateUtil.parseStringToMiliseconds(dateToInLong));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		if(dateFromInLong == null || dateFromInLong.isEmpty()) {
			try {
				dateFromInLong = String.valueOf(DateUtil.getBeginOfCurrentDate());
				dateToInLong = String.valueOf(DateUtil.getEndOfCurrentDate());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}  else  {
			if(dateToInLong == null || dateToInLong.isEmpty()) {
				try {
					dateToInLong = String.valueOf(DateUtil.parseStringToMiliseconds(revenueSearchForm.getDateFrom()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		
		finalURL  =  finalURL.replace("::employeeId", revenueSearchForm.getEmployeeId()!= null ? revenueSearchForm.getEmployeeId() : "");
		finalURL  =  finalURL.replace("::dateFrom", dateFromInLong != null ? dateFromInLong :  "");
		finalURL  =  finalURL.replace("::dateTo", dateToInLong != null ? dateToInLong : "");
		
		RestUtils<RevenueDto> restUtils = new RestUtils<>(RevenueDto.class);
		ResultObject<List<RevenueDto>> resultFromApi = new ResultObject<>();
		resultFromApi = restUtils.get(finalURL);
		return resultFromApi;
	}

}

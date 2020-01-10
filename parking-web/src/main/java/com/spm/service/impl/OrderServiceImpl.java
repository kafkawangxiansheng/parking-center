package com.spm.service.impl;

import java.text.ParseException;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spm.common.RestUtils;
import com.spm.common.URLConstants;
import com.spm.common.util.DateUtil;
import com.spm.dto.OrderDto;
import com.spm.dto.ResultObject;
import com.spm.search.form.OrderSearchForm;
import com.spm.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Override
	public ResultObject<List<OrderDto>> getAllOrder(OrderSearchForm orderSearhForm, Pageable pageable){
		
		String dateFromInLong = orderSearhForm.getDateFrom()+" "+orderSearhForm.getHourFrom()+":"+orderSearhForm.getMinFrom()+":00";
		String dateToInLong = orderSearhForm.getDateTo()+" "+orderSearhForm.getHourTo()+":"+orderSearhForm.getMinTo()+":59";
		
		if(orderSearhForm.getDateTo()  != null &&  !orderSearhForm.getDateTo().isEmpty()) {
			try {
				dateToInLong = String.valueOf(DateUtil.parseStringToMiliseconds(dateToInLong));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(orderSearhForm.getDateFrom()  != null &&  !orderSearhForm.getDateFrom().isEmpty()) {
			try {
				dateFromInLong = String.valueOf(DateUtil.parseStringToMiliseconds(dateFromInLong));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		RestUtils<OrderDto> restUtils = new RestUtils<>(OrderDto.class);
		ResultObject<List<OrderDto>> resultFromApi = new ResultObject<>();
		String finalURL = URLConstants.URL_GET_ALL_IN_OUT.replaceAll("::page", String.valueOf(pageable.getPageNumber()));
		finalURL = finalURL.replaceAll("::cardCode", orderSearhForm.getCardCode()!= null ? orderSearhForm.getCardCode():"");
		finalURL = finalURL.replaceAll("::isMonthlyCard", String.valueOf(orderSearhForm.getIsMonthlyCard()));
		finalURL = finalURL.replaceAll("::projectId", orderSearhForm.getProjectId()!= null ? orderSearhForm.getProjectId():"");
		finalURL = finalURL.replaceAll("::cardStt", orderSearhForm.getCardStt() != null ? orderSearhForm.getCardStt():"");
		finalURL = finalURL.replaceAll("::carNumber", orderSearhForm.getCarNumber() != null ? orderSearhForm.getCarNumber():"");
		finalURL = finalURL.replaceAll("::dateFrom", dateFromInLong != null ? dateFromInLong: "");
		finalURL = finalURL.replaceAll("::dateTo",  dateToInLong!= null ? dateToInLong : "");
		finalURL  =  finalURL.replace("::employeeId", orderSearhForm.getEmployeeId()!= null ? orderSearhForm.getEmployeeId() : "");
		
		resultFromApi = restUtils.get(finalURL);
		return resultFromApi;
	}
	@Override
	public ResultObject<List<OrderDto>> exportAllOrder(OrderSearchForm orderSearhForm, Pageable pageable){
		
		String dateToInLong = orderSearhForm.getDateTo();
		String dateFormInLong = orderSearhForm.getDateFrom();
		
		if(orderSearhForm.getDateTo()  != null &&  !orderSearhForm.getDateTo().isEmpty()) {
			try {
				dateToInLong = String.valueOf(DateUtil.parseStringToMiliseconds(dateToInLong+" 23:59:59"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(orderSearhForm.getDateFrom()  != null &&  !orderSearhForm.getDateFrom().isEmpty()) {
			try {
				dateFormInLong = String.valueOf(DateUtil.parseStringToMiliseconds(dateFormInLong+" 00:00:00"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		RestUtils<OrderDto> restUtils = new RestUtils<>(OrderDto.class);
		ResultObject<List<OrderDto>> resultFromApi = new ResultObject<>();
		String finalURL = URLConstants.URL_EXPORT_ALL_IN_OUT.replaceAll("::page", String.valueOf(pageable.getPageNumber()));
		finalURL = finalURL.replaceAll("::cardCode", orderSearhForm.getCardCode()!= null ? orderSearhForm.getCardCode():"");
		finalURL = finalURL.replaceAll("::isMonthlyCard", String.valueOf(orderSearhForm.getIsMonthlyCard()));
		finalURL = finalURL.replaceAll("::projectId", orderSearhForm.getProjectId()!= null ? orderSearhForm.getProjectId():"");
		finalURL = finalURL.replaceAll("::cardStt", orderSearhForm.getCardStt() != null ? orderSearhForm.getCardStt():"");
		finalURL = finalURL.replaceAll("::carNumber", orderSearhForm.getCarNumber() != null ? orderSearhForm.getCarNumber():"");
		finalURL = finalURL.replaceAll("::dateFrom", dateFormInLong != null ? dateFormInLong: "");
		finalURL = finalURL.replace("::employeeId", orderSearhForm.getEmployeeId()!= null ? orderSearhForm.getEmployeeId() : "");
		finalURL = finalURL.replaceAll("::dateTo",  dateToInLong!= null ? dateToInLong : "");
		resultFromApi = restUtils.get(finalURL);
		return resultFromApi;
	}

}

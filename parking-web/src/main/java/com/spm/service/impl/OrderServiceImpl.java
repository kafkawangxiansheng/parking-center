package com.spm.service.impl;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spm.common.RestUtils;
import com.spm.common.URLConstants;
import com.spm.dto.OrderDto;
import com.spm.dto.ResultObject;
import com.spm.search.form.OrderSearchForm;
import com.spm.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Override
	public ResultObject<List<OrderDto>> getAllOrder(OrderSearchForm orderSearhForm, Pageable pageable){
		RestUtils<OrderDto> restUtils = new RestUtils<>(OrderDto.class);
		ResultObject<List<OrderDto>> resultFromApi = new ResultObject<>();
		String finalURL = URLConstants.URL_GET_ALL_IN_OUT.replaceAll("::page", String.valueOf(pageable.getPageNumber()));
		finalURL = finalURL.replaceAll("::cardCode", orderSearhForm.getCardCode()!= null ? orderSearhForm.getCardCode():"");
		finalURL = finalURL.replaceAll("::cardStt", orderSearhForm.getCardStt() != null ? orderSearhForm.getCardStt():"");
		finalURL = finalURL.replaceAll("::carNumber", orderSearhForm.getCarNumber() != null ? orderSearhForm.getCarNumber():"");
		finalURL = finalURL.replaceAll("::dateFrom", orderSearhForm.getDateFrom() != null ? orderSearhForm.getDateFrom(): "");
		finalURL = finalURL.replaceAll("::dateTo", orderSearhForm.getDateTo() != null ? orderSearhForm.getDateTo(): "");
		resultFromApi = restUtils.get(finalURL);
		return resultFromApi;
	}

}

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
		
		String dateToInLong = orderSearhForm.getDateTo()+" 23:59:59";
		String dateFormInLong = orderSearhForm.getDateFrom()+" 00:00:00";
		
		if(dateToInLong  != null &&  !dateToInLong.isEmpty()) {
			try {
				dateToInLong = String.valueOf(DateUtil.parseStringToMiliseconds(dateToInLong));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(dateFormInLong  != null &&  !dateFormInLong.isEmpty()) {
			try {
				dateFormInLong = String.valueOf(DateUtil.parseStringToMiliseconds(dateFormInLong));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		RestUtils<OrderDto> restUtils = new RestUtils<>(OrderDto.class);
		ResultObject<List<OrderDto>> resultFromApi = new ResultObject<>();
		String finalURL = URLConstants.URL_GET_ALL_IN_OUT.replaceAll("::page", String.valueOf(pageable.getPageNumber()));
		finalURL = finalURL.replaceAll("::cardCode", orderSearhForm.getCardCode()!= null ? orderSearhForm.getCardCode():"");
		finalURL = finalURL.replaceAll("::cardStt", orderSearhForm.getCardStt() != null ? orderSearhForm.getCardStt():"");
		finalURL = finalURL.replaceAll("::carNumber", orderSearhForm.getCarNumber() != null ? orderSearhForm.getCarNumber():"");
		finalURL = finalURL.replaceAll("::dateFrom", dateFormInLong != null ? dateFormInLong: "");
		finalURL = finalURL.replaceAll("::dateTo",  dateToInLong!= null ? dateToInLong : "");
		resultFromApi = restUtils.get(finalURL);
		return resultFromApi;
	}

}

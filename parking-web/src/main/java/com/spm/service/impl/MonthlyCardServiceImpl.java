package com.spm.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.entity.StringEntity;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.spm.common.RestUtils;
import com.spm.common.URLConstants;
import com.spm.dto.MonthlyCardDto;
import com.spm.dto.ResultObject;
import com.spm.service.MonthlyCradService;

@Service
public class MonthlyCardServiceImpl implements MonthlyCradService{

	@Override
	public ResultObject<List<MonthlyCardDto>> getAllMonthlyCard() {
		RestUtils<MonthlyCardDto> restUtils = new RestUtils<>(MonthlyCardDto.class);
		ResultObject<List<MonthlyCardDto>> resultFromApi = new ResultObject<>();
		String finalURL = URLConstants.URL_GET_ALL_MONTHLY_CARD;
		resultFromApi = restUtils.get(finalURL);
		return resultFromApi;
	}

	@Override
	public MonthlyCardDto getMonthlyCardById(Long monthlyCardId) {
		ResultObject<List<MonthlyCardDto>> resultFromApi = new ResultObject<>();
		RestUtils<MonthlyCardDto> restUtils = new RestUtils<>(MonthlyCardDto.class);
		String finalURL = URLConstants.URL_GET_MONTHLY_CARD_BY_ID.replace("::id", String.valueOf(monthlyCardId));
		resultFromApi = restUtils.get(finalURL);
		return resultFromApi.getData().get(0);
	}

	@Override
	public MonthlyCardDto addMonthlyCard(MonthlyCardDto monthlyCardDto) {
		RestUtils<MonthlyCardDto> restUtils = new RestUtils<>(MonthlyCardDto.class);
		ResultObject<List<MonthlyCardDto>> resultFromApi = new ResultObject<>();
		Gson gson = new Gson();
		StringEntity stringEntity;
		try {
			stringEntity = new StringEntity(gson.toJson(monthlyCardDto), "UTF-8");
			resultFromApi = restUtils.postJSON(URLConstants.URL_POST_ADD_MONTHLY_CARD, stringEntity);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return resultFromApi.getData().get(0);
	}
	
	@Override
	public void deleteMonthlyCard(Long id) {
		RestUtils<MonthlyCardDto> restUtils = new RestUtils<>(MonthlyCardDto.class);
		String finalURL = URLConstants.URL_DELETE_MONTHLY_CARD.replace("::id", String.valueOf(id));
		
		try {
			restUtils.delete(finalURL);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}

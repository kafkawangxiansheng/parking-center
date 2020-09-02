package com.spm.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.entity.StringEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.spm.common.RestUtils;
import com.spm.common.URLConstants;
import com.spm.dto.MonthlyCardDto;
import com.spm.dto.OrderDto;
import com.spm.dto.ResultObject;
import com.spm.search.form.MonthlyCardSearchForm;
import com.spm.service.MonthlyCardService;

@Service
public class MonthlyCardServiceImpl implements MonthlyCardService{

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
	public boolean addMonthlyCard(MonthlyCardDto monthlyCardDto) {
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
		if(resultFromApi.getData() != null) {
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public void deleteMonthlyCard(Long id, String username) {
		RestUtils<MonthlyCardDto> restUtils = new RestUtils<>(MonthlyCardDto.class);
		String finalURL = URLConstants.URL_DELETE_MONTHLY_CARD.replace("::id", String.valueOf(id));
		finalURL = finalURL.replace("::username",username);
		try {
			restUtils.delete(finalURL);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean updateMonthlyCard(MonthlyCardDto monthlyCardDto) {
		RestUtils<MonthlyCardDto> restUtils = new RestUtils<>(MonthlyCardDto.class);
		ResultObject<List<MonthlyCardDto>> resultFromApi = new ResultObject<>();
		Gson gson = new Gson();
		StringEntity stringEntity;
		try {
			stringEntity = new StringEntity(gson.toJson(monthlyCardDto), "UTF-8");
			resultFromApi = restUtils.postJSON(URLConstants.URL_POST_UPDATE_MONTHLY_CARD, stringEntity);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(resultFromApi.getData() != null) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public ResultObject<List<MonthlyCardDto>> getAllMonthlyCard(MonthlyCardSearchForm monthlyCardSearchForm,
			Pageable pageable) {
		
		RestUtils<MonthlyCardDto> restUtils = new RestUtils<>(MonthlyCardDto.class);
		ResultObject<List<MonthlyCardDto>> resultFromApi = new ResultObject<>();
		String finalURL = URLConstants.URL_GET_ALL_MONTHLY_CARD_SEARCH_BY_PROJECT_ID.replaceAll("::page", String.valueOf(pageable.getPageNumber()));
		finalURL = finalURL.replaceAll("::cardCode", monthlyCardSearchForm.getCardCode()!= null ? monthlyCardSearchForm.getCardCode():"");
		finalURL = finalURL.replaceAll("::statusDate", String.valueOf(monthlyCardSearchForm.getStatusDate()));
		finalURL = finalURL.replaceAll("::vehicleId", (monthlyCardSearchForm.getVehicleId()!= null && !monthlyCardSearchForm.getVehicleId().equals("all")) ? monthlyCardSearchForm.getVehicleId():"");
		finalURL = finalURL.replaceAll("::numberEndDate", String.valueOf(monthlyCardSearchForm.getNumberEndDate()!= 0 ? monthlyCardSearchForm.getNumberEndDate():0));
		finalURL = finalURL.replaceAll("::customerName", URLEncoder.encode(monthlyCardSearchForm.getCustomerName() !=  null ? monthlyCardSearchForm.getCustomerName() : ""));
		finalURL = finalURL.replaceAll("::projectId", String.valueOf(monthlyCardSearchForm.getProjectId() !=  0 ? monthlyCardSearchForm.getProjectId() : ""));
		resultFromApi = restUtils.get(finalURL);
		return resultFromApi;
		
	}

	@Override
	public ResultObject<List<MonthlyCardDto>> getRenewal(MonthlyCardSearchForm monthlyCardSearchForm, Pageable pageable) {
		
		RestUtils<MonthlyCardDto> restUtils = new RestUtils<>(MonthlyCardDto.class);
		ResultObject<List<MonthlyCardDto>> resultFromApi = new ResultObject<>();
		String finalURL = URLConstants.URL_GET_MONTHLY_CARD_RENEWAL_SEARCH.replaceAll("::page", String.valueOf(pageable.getPageNumber()));
		finalURL = finalURL.replaceAll("::cardCode", monthlyCardSearchForm.getCardCode()!= null ? monthlyCardSearchForm.getCardCode():"");
		finalURL = finalURL.replaceAll("::statusDate", String.valueOf(monthlyCardSearchForm.getStatusDate()!= 0 ? monthlyCardSearchForm.getStatusDate():0));
		finalURL = finalURL.replaceAll("::vehicleId", (monthlyCardSearchForm.getVehicleId()!= null && !monthlyCardSearchForm.getVehicleId().equals("all")) ? monthlyCardSearchForm.getVehicleId():"");
		finalURL = finalURL.replaceAll("::numberEndDate", String.valueOf(monthlyCardSearchForm.getNumberEndDate()!= 0 ? monthlyCardSearchForm.getNumberEndDate():0));
		finalURL = finalURL.replaceAll("::customerName", URLEncoder.encode(monthlyCardSearchForm.getCustomerName() !=  null ? monthlyCardSearchForm.getCustomerName() : ""));
		finalURL = finalURL.replaceAll("::projectId", String.valueOf(monthlyCardSearchForm.getProjectId() !=  0 ? monthlyCardSearchForm.getProjectId() : "0"));
		resultFromApi = restUtils.get(finalURL);
		return resultFromApi;
	}

	@Override
	public boolean revewalMonthlyCardUpdate(MonthlyCardDto monthlyCardDto) {
		RestUtils<MonthlyCardDto> restUtils = new RestUtils<>(MonthlyCardDto.class);
		ResultObject<List<MonthlyCardDto>> resultFromApi = new ResultObject<>();
		Gson gson = new Gson();
		StringEntity stringEntity;
		try {
			stringEntity = new StringEntity(gson.toJson(monthlyCardDto), "UTF-8");
			resultFromApi = restUtils.postJSON(URLConstants.URL_POST_RENEWAL_MONTHLY_CARD_UPDATE, stringEntity);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(resultFromApi.getData() != null) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public void lockCard(String ids, String username) {
		RestUtils<OrderDto> restUtils = new RestUtils<>(OrderDto.class);
		String finalURL = URLConstants.URL_LOCK_MONTHLY_CARD.replace("::ids", ids);
		finalURL = finalURL.replace("::username", username);
		try {
			restUtils.post(finalURL, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}

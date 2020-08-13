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
import com.spm.dto.ResultObject;
import com.spm.search.form.MonthlyCradSearchForm;
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
	public void deleteMonthlyCard(Long id) {
		RestUtils<MonthlyCardDto> restUtils = new RestUtils<>(MonthlyCardDto.class);
		String finalURL = URLConstants.URL_DELETE_MONTHLY_CARD.replace("::id", String.valueOf(id));
		
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
	public ResultObject<List<MonthlyCardDto>> getAllMonthlyCard(MonthlyCradSearchForm monthlyCradSearchForm,
			Pageable pageable) {
		
		RestUtils<MonthlyCardDto> restUtils = new RestUtils<>(MonthlyCardDto.class);
		ResultObject<List<MonthlyCardDto>> resultFromApi = new ResultObject<>();
		String finalURL = URLConstants.URL_GET_ALL_MONTHLY_CARD_SEARCH_BY_PROJECT_ID.replaceAll("::page", String.valueOf(pageable.getPageNumber()));
		finalURL = finalURL.replaceAll("::cardCode", monthlyCradSearchForm.getCardCode()!= null ? monthlyCradSearchForm.getCardCode():"");
		finalURL = finalURL.replaceAll("::statusDate", String.valueOf(monthlyCradSearchForm.getStatusDate()));
		finalURL = finalURL.replaceAll("::vehicleId", (monthlyCradSearchForm.getVehicleId()!= null && !monthlyCradSearchForm.getVehicleId().equals("all")) ? monthlyCradSearchForm.getVehicleId():"");
		finalURL = finalURL.replaceAll("::numberEndDate", String.valueOf(monthlyCradSearchForm.getNumberEndDate()!= 0 ? monthlyCradSearchForm.getNumberEndDate():0));
		finalURL = finalURL.replaceAll("::customerName", URLEncoder.encode(monthlyCradSearchForm.getCustomerName() !=  null ? monthlyCradSearchForm.getCustomerName() : ""));
		finalURL = finalURL.replaceAll("::projectId", String.valueOf(monthlyCradSearchForm.getProjectId() !=  0 ? monthlyCradSearchForm.getProjectId() : ""));
		resultFromApi = restUtils.get(finalURL);
		return resultFromApi;
		
	}

	@Override
	public ResultObject<List<MonthlyCardDto>> getRenewal(MonthlyCradSearchForm monthlyCradSearchForm, Pageable pageable) {
		
		RestUtils<MonthlyCardDto> restUtils = new RestUtils<>(MonthlyCardDto.class);
		ResultObject<List<MonthlyCardDto>> resultFromApi = new ResultObject<>();
		String finalURL = URLConstants.URL_GET_MONTHLY_CARD_RENEWAL_SEARCH.replaceAll("::page", String.valueOf(pageable.getPageNumber()));
		finalURL = finalURL.replaceAll("::cardCode", monthlyCradSearchForm.getCardCode()!= null ? monthlyCradSearchForm.getCardCode():"");
		finalURL = finalURL.replaceAll("::statusDate", String.valueOf(monthlyCradSearchForm.getStatusDate()!= 0 ? monthlyCradSearchForm.getStatusDate():0));
		finalURL = finalURL.replaceAll("::vehicleId", (monthlyCradSearchForm.getVehicleId()!= null && !monthlyCradSearchForm.getVehicleId().equals("all")) ? monthlyCradSearchForm.getVehicleId():"");
		finalURL = finalURL.replaceAll("::numberEndDate", String.valueOf(monthlyCradSearchForm.getNumberEndDate()!= 0 ? monthlyCradSearchForm.getNumberEndDate():0));
		finalURL = finalURL.replaceAll("::customerName", monthlyCradSearchForm.getCustomerName() !=  null ? monthlyCradSearchForm.getCustomerName() : "");
		finalURL = finalURL.replaceAll("::projectId", String.valueOf(monthlyCradSearchForm.getProjectId() !=  0 ? monthlyCradSearchForm.getProjectId() : "0"));
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
	
}

package com.spm.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.entity.StringEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.spm.common.RestUtils;
import com.spm.common.URLConstants;
import com.spm.dto.CardsDto;
import com.spm.dto.ResultObject;
import com.spm.search.form.CardSearchForm;
import com.spm.service.CardService;

@Service
public class CardServiceImpl implements CardService{
	
	@Override
	public ResultObject<List<CardsDto>> getAllCard(CardSearchForm cardSearchForm, Pageable pageable){
		RestUtils<CardsDto> restUtils = new RestUtils<>(CardsDto.class);
		ResultObject<List<CardsDto>> resultFromApi = new ResultObject<>();
		String finalURL = URLConstants.URL_GET_ALL_CARD.replaceAll("::page", String.valueOf(pageable.getPageNumber()));
		finalURL = finalURL.replaceAll("::code", cardSearchForm.getCode()!= null ? cardSearchForm.getCode():"");
		finalURL = finalURL.replaceAll("::stt", cardSearchForm.getStt()!= null ? cardSearchForm.getStt():"");
		finalURL = finalURL.replaceAll("::vehicleId", cardSearchForm.getVehicleId()!= null ? cardSearchForm.getVehicleId():"");
		finalURL = finalURL.replaceAll("::projectId", String.valueOf(cardSearchForm.getProjectId() !=  0 ? cardSearchForm.getProjectId() : ""));
		resultFromApi = restUtils.get(finalURL);
		return resultFromApi;
	}
	
	@Override
	public ResultObject<List<CardsDto>> addCard(CardsDto cardsDto){
		RestUtils<CardsDto> restUtils = new RestUtils<>(CardsDto.class);
		ResultObject<List<CardsDto>> resultFromApi = new ResultObject<>();
		Gson gson = new Gson();
		
		StringEntity stringEntity;
		try {
			stringEntity = new StringEntity(gson.toJson(cardsDto), "UTF-8");
			resultFromApi = restUtils.postJSON(URLConstants.URL_ADD_CARD, stringEntity);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return resultFromApi;
	}
	
	@Override
	public CardsDto getCardById(Long cardId){
		RestUtils<CardsDto> restUtils = new RestUtils<>(CardsDto.class);
		ResultObject<List<CardsDto>> resultFromApi = new ResultObject<>();
		String finalURL = URLConstants.URL_GET_CARD_BY_ID.replace("::cardId", String.valueOf(cardId));
		resultFromApi = restUtils.get(finalURL);
		return resultFromApi.getData().get(0);
	}
	
	@Override
	public ResultObject<List<CardsDto>> getAllDisabledCard(CardSearchForm cardSearchForm) {
		RestUtils<CardsDto> restUtils = new RestUtils<>(CardsDto.class);
		ResultObject<List<CardsDto>> resultFromApi = new ResultObject<>();
		String finalURL = URLConstants.URL_GET_ALL_DISCARD;
		finalURL = finalURL.replaceAll("::disable", cardSearchForm.getDisable()!= null ? cardSearchForm.getDisable():"");
		finalURL = finalURL.replaceAll("::code", cardSearchForm.getCode()!= null ? cardSearchForm.getCode():"");
		resultFromApi = restUtils.get(finalURL);
		return resultFromApi;
	}

	@Override
	public void activeCard(int cardId) {
		RestUtils<CardsDto> restUtils = new RestUtils<>(CardsDto.class);
		String finalURL = URLConstants.URL_ACTIVE_CARD.replace("::cardId", String.valueOf(cardId));
		restUtils.get(finalURL);
	}

}

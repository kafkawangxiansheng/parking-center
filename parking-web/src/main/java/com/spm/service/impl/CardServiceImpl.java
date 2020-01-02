package com.spm.service.impl;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
		resultFromApi = restUtils.get(finalURL);
		return resultFromApi;
	}
}

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
import com.spm.dto.CompanyDto;
import com.spm.dto.ResultObject;
import com.spm.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService{

	@Override
	public ResultObject<List<CompanyDto>> getListCompanies() {
		RestUtils<CompanyDto> restUtils = new RestUtils<>(CompanyDto.class);
		ResultObject<List<CompanyDto>> resultFromApi = new ResultObject<>();
		String finalURL = URLConstants.URL_GET_LIST_COMPANIES;
		resultFromApi = restUtils.get(finalURL);
		return resultFromApi;
	}
	
	@Override
	public ResultObject<List<CompanyDto>> getAllCompanies() {
		RestUtils<CompanyDto> restUtils = new RestUtils<>(CompanyDto.class);
		ResultObject<List<CompanyDto>> resultFromApi = new ResultObject<>();
		String finalURL = URLConstants.URL_GET_ALL_COMPANIES;
		resultFromApi = restUtils.get(finalURL);
		return resultFromApi;
	}

	@Override
	public CompanyDto getCompanyById(Long companyId) {
		ResultObject<List<CompanyDto>> resultFromApi = new ResultObject<>();
		RestUtils<CompanyDto> restUtils = new RestUtils<>(CompanyDto.class);
		String finalURL = URLConstants.URL_GET_COMPANY_BY_ID.replace("::id", String.valueOf(companyId));
		resultFromApi = restUtils.get(finalURL);
		return resultFromApi.getData().get(0);
	}

	@Override
	public CompanyDto addCompany(CompanyDto companyDto) {
		RestUtils<CompanyDto> restUtils = new RestUtils<>(CompanyDto.class);
		ResultObject<List<CompanyDto>> resultFromApi = new ResultObject<>();
		Gson gson = new Gson();
		StringEntity stringEntity;
		try {
			stringEntity = new StringEntity(gson.toJson(companyDto), "UTF-8");
			resultFromApi = restUtils.postJSON(URLConstants.URL_POST_ADD_COMPANY, stringEntity);
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
	public void deleteCompany(Long id) {
		RestUtils<CompanyDto> restUtils = new RestUtils<>(CompanyDto.class);
		String finalURL = URLConstants.URL_DELETE_COMPANY.replace("::id", String.valueOf(id));
		
		try {
			restUtils.delete(finalURL);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

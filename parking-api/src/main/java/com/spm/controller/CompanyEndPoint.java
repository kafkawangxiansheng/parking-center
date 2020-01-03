package com.spm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spm.dto.CompanyDto;
import com.spm.dto.ResultObject;
import com.spm.service.CompanyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("company")
@Api(value = "Company Endpoint", description = "The URL to handle companies endpoint")
public class CompanyEndPoint {
	
	@Autowired
	private CompanyService companyService;
	
	@RequestMapping( value = "", method = {RequestMethod.GET})
	@ApiOperation("Get all Companies")
	public @ResponseBody ResultObject<List<CompanyDto>> getAll() {
		return companyService.findAll();
	}
	
	@RequestMapping( value = "listAllCompanies", method = {RequestMethod.GET})
	@ApiOperation("Get all Companies")
	public @ResponseBody ResultObject<List<CompanyDto>> getListAllCompanies() {
		return companyService.findAll();
	}

}

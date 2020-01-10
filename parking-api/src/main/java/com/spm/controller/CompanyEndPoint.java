package com.spm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spm.dto.CompanyDto;
import com.spm.dto.ResultObject;
import com.spm.service.CompanyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by thanhtuan on 06/01/2020
 */

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
	
	@RequestMapping(value = "addCompany", method = {RequestMethod.POST})
	@ApiOperation("Add new company")
	public @ResponseBody ResultObject<List<CompanyDto>> addCompany(@RequestBody CompanyDto companysDto) {
		return companyService.save(companysDto);
	}
	
	@RequestMapping(value = "addCompany", method = {RequestMethod.PUT})
	@ApiOperation("Update existing company")
	public @ResponseBody ResultObject<List<CompanyDto>> updateExistingCard(@RequestBody CompanyDto companysDto) {
		return companyService.save(companysDto);
	}
	
	@RequestMapping(value = "getCompanyById", method = {RequestMethod.GET})
	@ApiOperation("Get Company")
	public @ResponseBody ResultObject<List<CompanyDto>> getCompanyById(@RequestParam(name="id") Long id) {
		return companyService.findById(id);
	}
	
	@RequestMapping(path="delete/{id}", method = {RequestMethod.DELETE})
	@ApiOperation("This method support us can delete the specific company by id")
	public void delete(@PathVariable("id") Long id) {
		companyService.delete(id);
	}

}

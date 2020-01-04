package com.spm.service;

import java.util.List;

import com.spm.dto.CompanyDto;
import com.spm.dto.ResultObject;

public interface CompanyService {
	
	ResultObject<List<CompanyDto>> getListCompanies();
	
	ResultObject<List<CompanyDto>> getAllCompanies();
	
	CompanyDto getCompanyById(Long companyId);
	
	CompanyDto addCompany(CompanyDto companyDto);
	
	void deleteCompany(Long id);
	
}

package com.spm.service;

import java.util.List;

import com.spm.dto.CompanyDto;
import com.spm.dto.ResultObject;

public interface CompanyService {

	ResultObject<List<CompanyDto>> save(CompanyDto companyDto);
    
	ResultObject<List<CompanyDto>> save(List<CompanyDto> companyDto);

    void delete(Long id);
    
    ResultObject<List<CompanyDto>> findAll();
    
    ResultObject<List<CompanyDto>> findById(Long companyId);
    

}
 
package com.spm.service;

import java.util.List;

import com.spm.dto.MonthlyCardDto;
import com.spm.dto.ResultObject;

/**
 * Created by Vincent 23/05/2018
 */
public interface MonthlyCardService {
    
	ResultObject<List<MonthlyCardDto>> save(MonthlyCardDto monthlyCardDto);
    
	ResultObject<List<MonthlyCardDto>> save(List<MonthlyCardDto> listMonthlyCardDto);

    void delete(Long id);
    
    ResultObject<List<MonthlyCardDto>> findAll();
    
    ResultObject<List<MonthlyCardDto>> findById(Long id);

}
 
package com.spm.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.spm.dto.MonthlyCardDto;
import com.spm.dto.ResultObject;
import com.spm.entity.MonthlyCardEntity;
import com.spm.search.form.MonthlyCradSearchForm;

/**
 * Created by Vincent 23/05/2018
 */
public interface MonthlyCardService {
    
	ResultObject<List<MonthlyCardDto>> save(MonthlyCardDto monthlyCardDto);
    
	ResultObject<List<MonthlyCardDto>> save(List<MonthlyCardDto> listMonthlyCardDto);

    void delete(Long id);
    
    ResultObject<List<MonthlyCardDto>> findAll();
    
    ResultObject<List<MonthlyCardDto>> findById(Long id);
    
    MonthlyCardEntity findByCardCode(String cardCode);
    
    ResultObject<List<MonthlyCardDto>> search(Pageable pageable, MonthlyCradSearchForm  monthlyCradSearchForm);
    
    // renewal
    ResultObject<List<MonthlyCardDto>> renewalSearch(Pageable pageable, MonthlyCradSearchForm  monthlyCradSearchForm);
    
    ResultObject<List<MonthlyCardDto>> renewalFindOne(long id);
    
    ResultObject<List<MonthlyCardDto>> renewalUpdate(MonthlyCardDto monthlyCardDto);
    
    
}
 
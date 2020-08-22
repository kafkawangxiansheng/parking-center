package com.spm.service;

import java.util.List;

import com.spm.dtos.MonthlyCardDto;

/**
 * Created by Vincent 23/05/2018
 */
public interface MonthlyCardService {

    MonthlyCardDto save(MonthlyCardDto monthlyCardDto);
    
    MonthlyCardDto update(MonthlyCardDto monthlyCardDto);
    
    MonthlyCardDto findByCardCode(String cardCode);
    
    List<MonthlyCardDto> save(List<MonthlyCardDto> monthlyCardDtos);

    List<MonthlyCardDto> update(List<MonthlyCardDto> monthlyCardDtos);

    
    void delete(MonthlyCardDto monthlyCardDto);
    
    List<MonthlyCardDto> findAll();
    
    List<MonthlyCardDto> syncAllByProjectId(long projectId);

}
 
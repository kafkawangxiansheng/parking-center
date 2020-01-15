package com.spm.service;

import java.util.List;

import com.spm.dtos.CardsDto;

/**
 * Created by Vincent 23/05/2018
 */
public interface CardsService {

    CardsDto save(CardsDto cardsDto);
    
    List<CardsDto> save(List<CardsDto> cardsDtos);
    
    List<CardsDto> findAll();
    
    CardsDto findById(Long cardId);
    
    List<CardsDto> syncAllByProjectId(long projectId);

}
 
package com.spm.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.spm.dto.CardsDto;
import com.spm.dto.ResultObject;
import com.spm.search.form.CardSearchForm;

/**
 * Created by Vincent 23/05/2018
 */
public interface CardsService {

    CardsDto save(CardsDto cardsDto);
    
    List<CardsDto> save(List<CardsDto> cardsDtos);

    void delete(CardsDto cardsDto);
    
    ResultObject<List<CardsDto>> findAll(Pageable pageable, CardSearchForm cardSearchForm);

}
 
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

	ResultObject<List<CardsDto>> addNewCard(CardsDto cardsDto);
    
    List<CardsDto> save(List<CardsDto> cardsDtos);
    
    ResultObject<List<CardsDto>> findAll(Pageable pageable, CardSearchForm cardSearchForm);
    
    ResultObject<List<CardsDto>> findById(Long cardId);
    
    ResultObject<List<CardsDto>> findAllDisabledCard(CardSearchForm cardSearchForm);
    
    CardsDto checkCardAndCardType(String code, int cardType);
    
    void activebyId(int cardId);
    
    void delete(Long id);

}
 
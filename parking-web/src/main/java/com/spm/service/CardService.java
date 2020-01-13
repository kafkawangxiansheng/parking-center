package com.spm.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.spm.dto.CardsDto;
import com.spm.dto.ResultObject;
import com.spm.search.form.CardSearchForm;

public interface CardService {
	ResultObject<List<CardsDto>> getAllCard(CardSearchForm cardSearchForm, Pageable pageable);
	ResultObject<List<CardsDto>> addCard(CardsDto cardsDto);
	CardsDto getCardById(Long cardId);
	ResultObject<List<CardsDto>> getAllDisabledCard(CardSearchForm cardSearchForm);
}

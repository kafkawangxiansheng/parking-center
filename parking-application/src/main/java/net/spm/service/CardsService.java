package net.spm.service;

import java.util.List;

import net.spm.dto.CardsDto;

/**
 * Created by Vincent 23/05/2018
 */
public interface CardsService {

    CardsDto save(CardsDto cardsDto);
    
    List<CardsDto> save(List<CardsDto> cardsDtos);

    void delete(CardsDto cardsDto);
    
    List<CardsDto> findAll();

}
 
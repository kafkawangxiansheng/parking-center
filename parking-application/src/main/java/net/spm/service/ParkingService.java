package net.spm.service;

import java.util.List;

import net.spm.dto.OrdersDto;

/**
 * Created by Vincent 23/05/2018
 */
public interface ParkingService {

    OrdersDto save(OrdersDto parkingDto);
    
    List<OrdersDto> save(List<OrdersDto> ordersDtos);

    void delete(OrdersDto parkingDto);
    
    OrdersDto findByNumberPlate(String numberPlate);
    
    List<OrdersDto> findAll();

}
 
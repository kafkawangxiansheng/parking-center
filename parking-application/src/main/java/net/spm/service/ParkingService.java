package net.spm.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import net.spm.dto.OrdersDto;

/**
 * Created by Vincent 23/05/2018
 */
public interface ParkingService {

    OrdersDto save(OrdersDto parkingDto);
    
    List<OrdersDto> save(List<OrdersDto> ordersDtos);

    void delete(OrdersDto parkingDto);
    
    OrdersDto findByOrderId(Long orderId);
    
    List<OrdersDto> findAll(Pageable pageable);

}
 
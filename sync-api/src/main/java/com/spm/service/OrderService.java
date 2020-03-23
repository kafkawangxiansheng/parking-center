package com.spm.service;

import java.util.List;

import com.spm.dtos.OrderDto;

/**
 * Created by Vincent 23/05/2018
 */
public interface OrderService {

    OrderDto save(OrderDto parkingDto);
    
    List<OrderDto> save(List<OrderDto> ordersDtos);

    void delete(OrderDto parkingDto);
    
    OrderDto findByOrderIdAndProjectId(Long orderId, Long projectId);
    
    List<OrderDto>  findAll();

}
 
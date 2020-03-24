package com.spm.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.spm.dto.OrderDto;
import com.spm.dto.ResultObject;
import com.spm.search.form.OrderSearchForm;

/**
 * Created by Vincent 23/05/2018
 */
public interface OrderService {

    OrderDto save(OrderDto parkingDto);
    
    List<OrderDto> save(List<OrderDto> ordersDtos);

    void delete(OrderDto parkingDto);
    
    OrderDto findByOrderIdAndProjectId(Long orderId, Long projectId);
    
    ResultObject<List<OrderDto>> findAll(Pageable pageable, OrderSearchForm  orderSearchForm);

}
 
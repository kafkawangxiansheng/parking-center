package com.spm.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.spm.dto.OrderDto;
import com.spm.dto.ResultObject;
import com.spm.search.form.OrderSearchForm;

public interface OrderService {
	ResultObject<List<OrderDto>> getAllOrder(OrderSearchForm orderSearh, Pageable pageable);
	ResultObject<List<OrderDto>> getExistingCarInPart(OrderSearchForm orderSearh, Pageable pageable);
	ResultObject<List<OrderDto>> exportAllOrder(OrderSearchForm orderSearh, Pageable pageable);
	void saveLostCard(String ids);
	
}

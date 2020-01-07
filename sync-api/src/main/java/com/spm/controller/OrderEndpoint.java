package com.spm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spm.dtos.OrderDto;
import com.spm.service.OrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by Vincent on 02/10/2018
 */
@RestController
@RequestMapping("/orders")
@Api(value = "parking Endpoint", description = "The URL to handle parking endpoint")
public class OrderEndpoint {

	@Autowired
	private OrderService parkingService;
	
	
	
	@RequestMapping(value = "/batchinsert", method = {RequestMethod.POST})
	@ApiOperation("Orders batch syncs")
	public void batchSync(@RequestBody List<OrderDto> ordersDtos) {
		for(OrderDto orderDto : ordersDtos) {
			OrderDto existingDto = parkingService.findByOrderId(orderDto.getOrderId());
			if(existingDto != null && (existingDto.getCheckoutTime() == null || existingDto.getCheckoutTime()==  0)) {
				//just update existing record with new data
				orderDto.setId(existingDto.getId());
			}
			parkingService.save(orderDto);
		}
		
	}
	
	@RequestMapping(value = "", method = {RequestMethod.GET})
	@ApiOperation("Get all in/out logs")
	public @ResponseBody List<OrderDto> getAll() {
		
		return parkingService.findAll();
	}
	
}

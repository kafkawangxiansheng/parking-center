package com.spm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spm.constant.MessageConstants;
import com.spm.dtos.MonthlyCardDto;
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
	
	
	
	@PostMapping(value = "/batchinsert")
	@ApiOperation("Orders batch syncs")
	public String batchSync(@RequestBody List<OrderDto> ordersDtos) {
		for(OrderDto orderDto : ordersDtos) {
			OrderDto existingDto = parkingService.findByOrderIdAndProjectId(orderDto.getOrderId(), orderDto.getProjectId());
			if(existingDto != null) {
				//just update existing record with new data
				orderDto.setId(existingDto.getId());
			}
		}
		
		try {
			List<OrderDto>  dtos = parkingService.save(ordersDtos);
			if (dtos != null) {
				return MessageConstants.SUCCESS;
			}
		} catch (Exception e) {
			return MessageConstants.ERROR;
		}
		return MessageConstants.ERROR;
		
		
	}
	
	@RequestMapping(value = "", method = {RequestMethod.GET})
	@ApiOperation("Get all in/out logs")
	public @ResponseBody List<OrderDto> getAll() {
		
		return parkingService.findAll();
	}
	
}

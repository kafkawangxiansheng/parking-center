package net.spm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.spm.common.web.BaseEndpoint;
import net.spm.dto.OrdersDto;
import net.spm.service.ParkingService;

/**
 * Created by Vincent on 02/10/2018
 */
@RestController
@RequestMapping("/orders")
@Api(value = "parking Endpoint", description = "The URL to handle parking endpoint")
public class OrdersEndpoint extends BaseEndpoint {

	@Autowired
	private ParkingService parkingService;
	
	
	
	@RequestMapping(value = "/batchinsert", method = {RequestMethod.POST})
	@ApiOperation("Orders batch syncs")
	public void batchSync(@RequestBody List<OrdersDto> ordersDtos) {
		for(OrdersDto orderDto : ordersDtos) {
			OrdersDto existingDto = parkingService.findByOrderId(orderDto.getOrderId());
			if(existingDto != null && (existingDto.getCheckoutTime() == null || existingDto.getCheckoutTime()==  0)) {
				//just update existing record with new data
				orderDto.setId(existingDto.getId());
			}
			parkingService.save(orderDto);
		}
		
	}
	
	@RequestMapping(value = "", method = {RequestMethod.GET})
	@ApiOperation("Orders batch syncs")
	public @ResponseBody List<OrdersDto> getAll(@RequestParam(name="page", required  =  false, defaultValue="0") int page) {
		Pageable paging = new PageRequest(page, 100);
		return parkingService.findAll(paging);
	}
	
	
}

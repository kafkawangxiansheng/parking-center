package net.spm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.spm.common.web.BaseEndpoint;
import net.spm.service.RevenueService;

/**
 * Created by Vincent on 02/10/2018
 */
@RestController
@RequestMapping("/revenue")
@Api(value = "Revenue Endpoint", description = "The URL to handle revenue endpoint")
public class RevenueEndpoint extends BaseEndpoint {

	@Autowired
	private RevenueService revenueService;
	
	
	
	
	@RequestMapping(value = "", method = {RequestMethod.GET})
	@ApiOperation("get revenue for moto monthly")
	public @ResponseBody Map<String, Object> getRevenue() {
		return revenueService.getRevenues();
	}
	
	
}

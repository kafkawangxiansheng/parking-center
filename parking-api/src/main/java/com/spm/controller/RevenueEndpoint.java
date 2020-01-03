package com.spm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spm.dto.ResultObject;
import com.spm.dto.RevenueDto;
import com.spm.search.form.RevenueSearchForm;
import com.spm.service.RevenueService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by Vincent on 02/10/2018
 */
@RestController
@RequestMapping("/revenue")
@Api(value = "Revenue Endpoint", description = "The URL to handle revenue endpoint")
public class RevenueEndpoint {

	@Autowired
	private RevenueService revenueService;
	
	
	
	
	@RequestMapping(value = "", method = {RequestMethod.GET})
	@ApiOperation("get revenue for report")
	public @ResponseBody ResultObject<List<RevenueDto>> getRevenue(@RequestParam(name="projectId", required=false, defaultValue="1")int projectId,
			@RequestParam(name="employeeId", required=false)String employeeId,
			@RequestParam(name="dateFrom", required=false)String dateFrom,
			@RequestParam(name="dateTo", required=false)String dateTo) {
		
		RevenueSearchForm revenueSearchForm = new RevenueSearchForm();
		
		revenueSearchForm.setProjectId(projectId);
		if(employeeId != null && !employeeId.isEmpty()){
			revenueSearchForm.setEmployeeId(employeeId);
		}
		
		if(dateFrom != null && !dateFrom.isEmpty()){
			revenueSearchForm.setDateFrom(dateFrom);
		}
		if(dateTo != null && !dateTo.isEmpty()){
			revenueSearchForm.setDateTo(dateTo);
		}
		
		ResultObject<List<RevenueDto>> resutl = new ResultObject<>();
		resutl.setData(revenueService.getRevenues(revenueSearchForm));
		return resutl;
	}
	
	
}

package com.spm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spm.constants.PagingConstants;
import com.spm.dto.MonthlyCardLogDto;
import com.spm.dto.ResultObject;
import com.spm.service.MonthlyCardLogService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by Vincent on 02/10/2018
 */
@RestController
@RequestMapping("monthlyCardLog")
@Api(value = "Monthly card logs Endpoint", description = "The URL to handle monthly cards logs endpoint")
public class MonthCardLogEndpoint {

	@Autowired
	private MonthlyCardLogService monthCardLogService;

	@PostMapping(value = "/add")
	@ApiOperation("Add MonthlyCard Log")
	public ResultObject<List<MonthlyCardLogDto>> addMonthlyCardLog(@RequestBody MonthlyCardLogDto monthlyCardLogDto) {
		ResultObject<List<MonthlyCardLogDto>> resultObject = monthCardLogService.save(monthlyCardLogDto);
		return resultObject;
	}

	
	@GetMapping(value = "")
	@ApiOperation("Get all MonthlyCards")
	public @ResponseBody ResultObject<List<MonthlyCardLogDto>> getAll(@RequestParam(name = "projectId") long projectId, @RequestParam(name = "page") int page) {
		Pageable pageable = PageRequest.of(page, PagingConstants.ROWS_PER_PAGE);
		return monthCardLogService.getLogs(projectId, pageable);
	}
}

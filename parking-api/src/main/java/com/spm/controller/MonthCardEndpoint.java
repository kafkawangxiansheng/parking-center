package com.spm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spm.dto.MonthlyCardDto;
import com.spm.dto.ResultObject;
import com.spm.service.MonthlyCardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by Vincent on 02/10/2018
 */
@RestController
@RequestMapping("/monthlycards")
@Api(value = "Monthly cards Endpoint", description = "The URL to handle monthly cards endpoint")
public class MonthCardEndpoint{

	@Autowired
	private MonthlyCardService  monthCardsService;
	
	
	
	@RequestMapping(value = "/add", method = {RequestMethod.POST})
	@ApiOperation("Add new monthly card")
	public void addNewCard(@RequestBody MonthlyCardDto monthlyCardDto) {
		monthCardsService.save(monthlyCardDto);
	}
	
	@RequestMapping(value = "/update", method = {RequestMethod.PUT})
	@ApiOperation("Update existing monthly card")
	public void updateExistingCard(@RequestBody MonthlyCardDto monthlyCardDto) {
		monthCardsService.save(monthlyCardDto);
	}
	
	@RequestMapping(value = "", method = {RequestMethod.GET})
	@ApiOperation("Orders batch syncs")
	public @ResponseBody List<MonthlyCardDto> getAll() {
		return monthCardsService.findAll();
	}
	
	@RequestMapping(value = "getMonthlyCardById", method = {RequestMethod.GET})
	@ApiOperation("Get Monthly Card")
	public @ResponseBody ResultObject<List<MonthlyCardDto>> getProjectById(@RequestParam(name="id") Long id) {
		return monthCardsService.findById(id);
	}
	
}

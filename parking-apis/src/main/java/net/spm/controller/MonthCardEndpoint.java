package net.spm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.spm.common.web.BaseEndpoint;
import net.spm.dto.CardsDto;
import net.spm.dto.MonthlyCardDto;
import net.spm.service.CardsService;
import net.spm.service.MonthlyCardService;

/**
 * Created by Vincent on 02/10/2018
 */
@RestController
@RequestMapping("/monthlycards")
@Api(value = "Monthly cards Endpoint", description = "The URL to handle monthly cards endpoint")
public class MonthCardEndpoint extends BaseEndpoint {

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
	
	
}

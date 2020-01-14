package com.spm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spm.dto.MonthlyCardDto;
import com.spm.dto.ResultObject;
import com.spm.service.CardsService;
import com.spm.service.MonthlyCardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by Vincent on 02/10/2018
 */
@RestController
@RequestMapping("monthlyCard")
@Api(value = "Monthly cards Endpoint", description = "The URL to handle monthly cards endpoint")
public class MonthCardEndpoint{

	@Autowired
	private MonthlyCardService  monthCardsService;
	
	@Autowired
	private CardsService  cardsService;
	
	@RequestMapping(value = "/add", method = {RequestMethod.POST})
	@ApiOperation("Add MonthlyCard")
	public ResultObject<List<MonthlyCardDto>> addMonthlyCard(@RequestBody MonthlyCardDto monthlyCardDto) {
		ResultObject<List<MonthlyCardDto>> resultObject = new ResultObject<>();
		// type ve thang
		int cardType = 2;
		boolean resultCheck = cardsService.checkCardAndCardType(monthlyCardDto.getCardCode(),cardType );
		if(resultCheck) {
			// check card used on monthlyCard
			MonthlyCardDto monthlyCardDtoSource = monthCardsService.findByCardCode(monthlyCardDto.getCardCode());
			if(monthlyCardDtoSource == null) {
				resultObject =  monthCardsService.save(monthlyCardDto);
			}
		}else {
			resultObject = null;
		}
		return resultObject;
	}
	
	@RequestMapping(value = "/update", method = {RequestMethod.PUT})
	@ApiOperation("Update monthly card")
	public void updateMonthlyCard(@RequestBody MonthlyCardDto monthlyCardDto) {
		monthCardsService.save(monthlyCardDto);
	}
	
	@RequestMapping(value = "", method = {RequestMethod.GET})
	@ApiOperation("Get all MonthlyCards")
	public @ResponseBody ResultObject<List<MonthlyCardDto>> getAll() {
		return monthCardsService.findAll();
	}
	
	@RequestMapping(value = "getMonthlyCardById", method = {RequestMethod.GET})
	@ApiOperation("Get Monthly Card")
	public @ResponseBody ResultObject<List<MonthlyCardDto>> getMonthlyCardById(@RequestParam(name="id") Long id) {
		return monthCardsService.findById(id);
	}
	
	@RequestMapping(path="/delete/{id}", method = {RequestMethod.DELETE})
	@ApiOperation("This method support us can delete the specific monthCard by id")
	public void delete(@PathVariable("id") Long id) {
		monthCardsService.delete(id);
	}
	
}

package com.spm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spm.dtos.CardsDto;
import com.spm.service.CardsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by Vincent on 02/10/2018
 */
@RestController
@RequestMapping("/cards")
@Api(value = "Card Endpoint", description = "The URL to handle cards endpoint")
public class CardsEndpoint {

	@Autowired
	private CardsService cardsService;

	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	@ApiOperation("Add new card")
	public void addNewCard(@RequestBody CardsDto cardDto) {
		cardsService.save(cardDto);
	}

	@RequestMapping(value = "/batchinsert", method = { RequestMethod.POST })
	@ApiOperation("Add new card")
	public void batchinsert(@RequestBody List<CardsDto> cardDtos) {
		cardsService.save(cardDtos);
	}
	@RequestMapping(value = "/update", method = { RequestMethod.PUT })
	@ApiOperation("Update existing card")
	public void updateExistingCard(@RequestBody CardsDto cardDto) {
		cardsService.save(cardDto);
	}

	@RequestMapping(value = "", method = { RequestMethod.GET })
	@ApiOperation("Get All orders")
	public @ResponseBody List<CardsDto> getAll() {
		return cardsService.findAll();
	}
	
	@RequestMapping(value="/getById", method = {RequestMethod.GET})
	@ApiOperation("Get CardId")
	public @ResponseBody CardsDto getCardById(@RequestParam(name="cardId") Long cardId) {
		return cardsService.findById(cardId);
	}

}

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
import net.spm.service.CardsService;

/**
 * Created by Vincent on 02/10/2018
 */
@RestController
@RequestMapping("/cards")
@Api(value = "Card Endpoint", description = "The URL to handle cards endpoint")
public class CardsEndpoint extends BaseEndpoint {

	@Autowired
	private CardsService  cardsService;
	
	
	
	@RequestMapping(value = "/add", method = {RequestMethod.POST})
	@ApiOperation("Add new card")
	public void addNewCard(@RequestBody CardsDto cardDto) {
		cardsService.save(cardDto);
	}
	
	@RequestMapping(value = "/update", method = {RequestMethod.PUT})
	@ApiOperation("Update existing card")
	public void updateExistingCard(@RequestBody CardsDto cardDto) {
		cardsService.save(cardDto);
	}
	
	@RequestMapping(value = "", method = {RequestMethod.GET})
	@ApiOperation("Orders batch syncs")
	public @ResponseBody List<CardsDto> getAll() {
		return cardsService.findAll();
	}
	
	
}

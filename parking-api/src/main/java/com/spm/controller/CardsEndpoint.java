package com.spm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spm.constants.PagingConstants;
import com.spm.dto.CardsDto;
import com.spm.dto.ResultObject;
import com.spm.search.form.CardSearchForm;
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
	public ResultObject<List<CardsDto>> addNewCard(@RequestBody CardsDto cardDto) {
		return cardsService.addNewCard(cardDto);
	}

	@RequestMapping(value = "/update", method = { RequestMethod.POST })
	@ApiOperation("Update existing card")
	public ResultObject<List<CardsDto>> updateExistingCard(@RequestBody CardsDto cardDto) {
		return cardsService.update(cardDto);
	}

	@RequestMapping(value = "", method = { RequestMethod.GET })
	@ApiOperation("Orders batch syncs")
	public @ResponseBody ResultObject<List<CardsDto>> getAll(
			@RequestParam(name="page", required  =  false, defaultValue="0") int page,
			@RequestParam(name = "code", required = false) String code,
			@RequestParam(name = "stt", required = false) String stt,
			@RequestParam(name = "vehicleId", required = false) String vehicleId,
			@RequestParam(name = "projectId", required = false) long projectId) {
		Pageable paging = PageRequest.of(page, PagingConstants.ROWS_PER_PAGE);
		CardSearchForm cardSearchForm = new CardSearchForm();
		if(code != null && !code.isEmpty()) {
			cardSearchForm.setCode(code);
		}
		if(stt != null && !stt.isEmpty()) {
			cardSearchForm.setStt(stt);
		}
		if(vehicleId != null && !vehicleId.isEmpty()) {
			cardSearchForm.setVehicleId(vehicleId);
		}
		if(projectId != 0 ) {
			cardSearchForm.setProjectId(projectId);
		}
		return cardsService.findAll(paging, cardSearchForm);
	}
	
	@RequestMapping(value="/getById", method = {RequestMethod.GET})
	@ApiOperation("Get CardId")
	public @ResponseBody ResultObject<List<CardsDto>> getCardById(@RequestParam(name="cardId") Long cardId) {
		return cardsService.findById(cardId);
	}
	
	@RequestMapping(value="/disabledCard", method = {RequestMethod.GET})
	@ApiOperation("Show Disabled Card")
	public @ResponseBody ResultObject<List<CardsDto>> getAllDisabledCard(@RequestParam(name = "code", required = false) String code) {
		CardSearchForm cardSearchForm = new CardSearchForm();
		if(code != null && !code.isEmpty()) {
			cardSearchForm.setCode(code);
		}
		return cardsService.findAllDisabledCard(cardSearchForm);
	}
	
	@RequestMapping(value="/activeCard", method = { RequestMethod.GET })
	@ApiOperation("Active Card")
	public void activeCardById(@RequestParam(name="cardId") int cardId) {
		cardsService.activebyId(cardId);
	}
	
	@RequestMapping(path = "/delete/{id}", method = { RequestMethod.DELETE })
	@ApiOperation("This method support us can delete the specific Card by id")
	public void delete(@PathVariable("id") Long id) {
		cardsService.delete(id);
	}
	
}

package com.spm.controller;

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
import com.spm.dto.CardsDto;
import com.spm.dto.ResultObject;
import com.spm.search.form.CardSearchForm;
import com.spm.service.CardsService;

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

	@RequestMapping(value = "/update", method = { RequestMethod.PUT })
	@ApiOperation("Update existing card")
	public void updateExistingCard(@RequestBody CardsDto cardDto) {
		cardsService.save(cardDto);
	}

	@RequestMapping(value = "", method = { RequestMethod.GET })
	@ApiOperation("Orders batch syncs")
	public @ResponseBody ResultObject<List<CardsDto>> getAll(
			@RequestParam(name="page", required  =  false, defaultValue="0") int page,
			@RequestParam(name = "code", required = false) String code,
			@RequestParam(name = "stt", required = false) String stt,
			@RequestParam(name = "vehicleId", required = false) String vehicleId) {
		Pageable paging = PageRequest.of(page, 100);
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
		return cardsService.findAll(paging, cardSearchForm);
	}

}

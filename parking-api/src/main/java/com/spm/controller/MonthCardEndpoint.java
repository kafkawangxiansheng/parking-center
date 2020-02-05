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
import com.spm.dto.MonthlyCardDto;
import com.spm.dto.ResultObject;
import com.spm.entity.MonthlyCardEntity;
import com.spm.search.form.MonthlyCradSearchForm;
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
public class MonthCardEndpoint {

	@Autowired
	private MonthlyCardService monthCardsService;

	@Autowired
	private CardsService cardsService;

	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	@ApiOperation("Add MonthlyCard")
	public ResultObject<List<MonthlyCardDto>> addMonthlyCard(@RequestBody MonthlyCardDto monthlyCardDto) {
		ResultObject<List<MonthlyCardDto>> resultObject = new ResultObject<>();
		// type ve thang
		int cardType = 2;
		CardsDto cardsDto = checkCard(monthlyCardDto.getCardCode(), cardType);
		if (cardsDto != null) {
			// check card used on monthlyCard
			MonthlyCardEntity monthlyCardEntity = monthCardsService.findByCardCode(monthlyCardDto.getCardCode());
			if (monthlyCardEntity == null) {
				monthlyCardDto.setVehicleId(cardsDto.getVehicleId());
				resultObject = monthCardsService.save(monthlyCardDto);
			} else {
				resultObject = null;
			}
		} else {
			resultObject = null;
		}
		return resultObject;
	}

	@RequestMapping(value = "/update", method = { RequestMethod.POST })
	@ApiOperation("Update MonthlyCard")
	public ResultObject<List<MonthlyCardDto>> updateMonthlyCard(@RequestBody MonthlyCardDto monthlyCardDto) {
		ResultObject<List<MonthlyCardDto>> resultObject = new ResultObject<>();
		// type ve thang
		int cardType = 2;
		CardsDto cardsDto = checkCard(monthlyCardDto.getCardCode(), cardType);
		if (cardsDto != null) {
			// check card used on monthlyCard
			MonthlyCardEntity monthlyCardEntity = monthCardsService.findByCardCode(monthlyCardDto.getCardCode());
			if (monthlyCardEntity == null) {
				monthlyCardDto.setVehicleId(cardsDto.getVehicleId());
				resultObject = monthCardsService.save(monthlyCardDto);
			} else {
				resultObject = null;
			}
		} else {
			resultObject = null;
		}

		return resultObject;
	}

	@RequestMapping(value = "", method = { RequestMethod.GET })
	@ApiOperation("Get all MonthlyCards")
	public @ResponseBody ResultObject<List<MonthlyCardDto>> getAll() {
		return monthCardsService.findAll();
	}

	@RequestMapping(value = "getMonthlyCardById", method = { RequestMethod.GET })
	@ApiOperation("Get Monthly Card")
	public @ResponseBody ResultObject<List<MonthlyCardDto>> getMonthlyCardById(@RequestParam(name = "id") Long id) {
		return monthCardsService.findById(id);
	}

	@RequestMapping(path = "/delete/{id}", method = { RequestMethod.DELETE })
	@ApiOperation("This method support us can delete the specific monthCard by id")
	public void delete(@PathVariable("id") Long id) {
		monthCardsService.delete(id);
	}

	@RequestMapping(path = "/checkCard", method = { RequestMethod.GET })
	@ApiOperation("input : string code in Cards, int cardType int Vehicles,  return :  cardsDto")
	public CardsDto checkCard(@RequestParam(name = "cardcode") String code,
			@RequestParam(name = "cardType") int cardType) {
		CardsDto cardsDto = cardsService.checkCardAndCardType(code, cardType);
		return cardsDto;
	}
	
	@RequestMapping(value = "search", method = { RequestMethod.GET })
	@ApiOperation("Get all MonthlyCards by form search")
	public @ResponseBody ResultObject<List<MonthlyCardDto>> getAllBySearch(
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "cardCode", required = false) String cardCode,
			@RequestParam(name = "statusDate", required = false) int statusDate,
			@RequestParam(name = "vehicleId", required = false) String vehicleId,
			@RequestParam(name = "numberEndDate", required = false) String numberEndDate) {
		
		Pageable paging = PageRequest.of(page, PagingConstants.ROWS_PER_PAGE);
		
		MonthlyCradSearchForm monthlyCradSearchForm = new MonthlyCradSearchForm();
		if(cardCode != null && !cardCode.isEmpty() && cardCode != "all") {
			monthlyCradSearchForm.setCardCode(cardCode);
		}
		if(vehicleId != null && !vehicleId.isEmpty()) {
			monthlyCradSearchForm.setVehicleId(vehicleId);
		}
		if(numberEndDate != null && !numberEndDate.isEmpty()) {
			monthlyCradSearchForm.setNumberEndDate(numberEndDate);
		}
		//TODO:  search by status date
		// 0: all , 1: conhan, 2: hethan
		
		return monthCardsService.search(paging, monthlyCradSearchForm);
	}
	
}



package com.spm.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spm.constants.PagingConstants;
import com.spm.dto.CardsDto;
import com.spm.dto.MonthlyCardDto;
import com.spm.dto.ResultObject;
import com.spm.entity.MonthlyCardEntity;
import com.spm.search.form.MonthlyCardSearchForm;
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

	@PostMapping(value = "/add")
	@ApiOperation("Add MonthlyCard")
	public ResultObject<List<MonthlyCardDto>> addMonthlyCard(@RequestBody MonthlyCardDto monthlyCardDto) {
		ResultObject<List<MonthlyCardDto>> resultObject = new ResultObject<>();
		// type ve thang
		int cardType = 2;

		CardsDto cardsDto = checkCard(monthlyCardDto.getCardCode(), cardType, monthlyCardDto.getProject().getId());
		if (cardsDto != null) {
			// check card used on monthlyCard
			MonthlyCardEntity monthlyCardEntity = monthCardsService.findByCardCode(monthlyCardDto.getCardCode());
			if (monthlyCardEntity == null) {
				monthlyCardDto.setVehicleId(cardsDto.getVehicleId());
				monthlyCardDto.setUpdated(Calendar.getInstance().getTimeInMillis());
				monthlyCardDto.setCreated(Calendar.getInstance().getTimeInMillis());
				monthlyCardDto.setLastSync(0);
				resultObject = monthCardsService.save(monthlyCardDto);
			} else {
				resultObject = null;
			}
		} else {
			resultObject = null;
		}
		return resultObject;
	}

	@PostMapping(value = "/update")
	@ApiOperation("Update MonthlyCard")
	public ResultObject<List<MonthlyCardDto>> updateMonthlyCard(@RequestBody MonthlyCardDto monthlyCardDto) {
		ResultObject<List<MonthlyCardDto>> resultObject = new ResultObject<>();

		if (monthlyCardDto.getCardCode().equals(monthlyCardDto.getCardCode())) {
			monthlyCardDto.setUpdated(Calendar.getInstance().getTimeInMillis());
			resultObject = monthCardsService.update(monthlyCardDto);
		} else {
			// type ve thang
			int cardType = 2;
			CardsDto cardsDto = checkCard(monthlyCardDto.getCardCode(), cardType, monthlyCardDto.getProject().getId());
			if (cardsDto != null) {
				// check card used on monthlyCard
				MonthlyCardEntity checkCardCode = monthCardsService.findByCardCode(monthlyCardDto.getCardCode());
				if (checkCardCode == null) {
					monthlyCardDto.setVehicleId(cardsDto.getVehicleId());
					monthlyCardDto.setUpdated(Calendar.getInstance().getTimeInMillis());
					resultObject = monthCardsService.save(monthlyCardDto);
				} else {
					resultObject = null;
				}
			} else {
				resultObject = null;
			}
		}

		return resultObject;
	}

	@GetMapping(value = "")
	@ApiOperation("Get all MonthlyCards")
	public @ResponseBody ResultObject<List<MonthlyCardDto>> getAll() {
		return monthCardsService.findAll();
	}

	@GetMapping(value = "getMonthlyCardById")
	@ApiOperation("Get Monthly Card")
	public @ResponseBody ResultObject<List<MonthlyCardDto>> getMonthlyCardById(@RequestParam(name = "id") Long id) {
		return monthCardsService.findById(id);
	}

	@DeleteMapping(path = "/delete/{id}/{username}/")
	@ApiOperation("This method support us can delete the specific monthCard by id")
	public void delete(@PathVariable("id") Long id, @PathVariable("username") String username) {
		monthCardsService.delete(id, username);
	}
	
	@PostMapping(path = "/lock-card/{username}/")
	@ApiOperation("This method support us can delete the specific monthCard by id")
	public void lockCard(@PathVariable("username") String username, @RequestParam(name = "ids", required = true) String ids) {
		String[] idsArray  = ids.split(",");
		for(String id : idsArray) {
			MonthlyCardDto  cardDto = monthCardsService.getById(Long.valueOf(id));
			if(cardDto !=  null  && cardDto.getId() != null)  {
				cardDto.setDisabled(true);
				cardDto.setDeleted(true);
				cardDto.setUpdated(Calendar.getInstance().getTimeInMillis());
				monthCardsService.save(cardDto);
			}
		}
	}

	@GetMapping(path = "/checkCard")
	@ApiOperation("input : string code in Cards, int cardType int Vehicles,  return :  cardsDto")
	public CardsDto checkCard(@RequestParam(name = "cardcode") String code,
			@RequestParam(name = "cardType") int cardType, @RequestParam(name = "projectId") long projectId) {
		return cardsService.checkCardAndCardTypeAndProjectId(code, cardType, projectId);
	}

	@GetMapping(value = "/search")
	@ApiOperation("Get all MonthlyCards by form search")
	public @ResponseBody ResultObject<List<MonthlyCardDto>> getAllBySearch(
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "cardCode", required = false) String cardCode,
			@RequestParam(name = "statusDate", required = false) int statusDate,
			@RequestParam(name = "vehicleId", required = false) String vehicleId,
			@RequestParam(name = "numberEndDate", required = false) int numberEndDate,
			@RequestParam(name = "customerName", required = false) String customerName,
			@RequestParam(name = "projectId", required = false) long projectId) {

		Pageable paging = PageRequest.of(page, PagingConstants.ROWS_PER_PAGE);

		MonthlyCardSearchForm monthlyCardSearchForm = new MonthlyCardSearchForm();
		if (cardCode != null && !cardCode.isEmpty()) {
			monthlyCardSearchForm.setCardCode(cardCode);
		}
		if (vehicleId != null && !vehicleId.isEmpty()) {
			monthlyCardSearchForm.setVehicleId(vehicleId);
		}
		if (numberEndDate != 0) {
			monthlyCardSearchForm.setNumberEndDate(numberEndDate);
		}
		monthlyCardSearchForm.setStatusDate(statusDate);
		
		if (customerName != null && !customerName.isEmpty()) {
			monthlyCardSearchForm.setCustomerName(customerName);
		}
		if (projectId != 0) {
			monthlyCardSearchForm.setProjectId(projectId);
		}

		return monthCardsService.search(paging, monthlyCardSearchForm);
	}

	@GetMapping(value = "/renewal/search")
	@ApiOperation("Get all MonthlyCards by form search")
	public @ResponseBody ResultObject<List<MonthlyCardDto>> getRenewalSearch(
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "cardCode", required = false) String cardCode,
			@RequestParam(name = "customerName", required = false) String customerName,
			@RequestParam(name = "projectId", required = false) long projectId) {

		MonthlyCardSearchForm monthlyCardSearchForm = new MonthlyCardSearchForm();
		Pageable paging = PageRequest.of(page, PagingConstants.ROWS_PER_PAGE);

		if (cardCode != null && !cardCode.isEmpty()) {
			monthlyCardSearchForm.setCardCode(cardCode);
		}
		if (customerName != null && !customerName.isEmpty()) {
			monthlyCardSearchForm.setCustomerName(customerName);
		}
		if (projectId != 0) {
			monthlyCardSearchForm.setProjectId(projectId);
		}
		return monthCardsService.renewalSearch(paging, monthlyCardSearchForm);
	}

	// renewal
	@GetMapping(value = "/renewal/findOne")
	@ApiOperation("Get one MonthlyCards for renewal")
	public @ResponseBody ResultObject<List<MonthlyCardDto>> renewalFindOne(
			@RequestParam(name = "id", required = false) long id) {

		return monthCardsService.renewalFindOne(id);
	}

	// renewal update
	@PostMapping(value = "/revewal/update")
	@ApiOperation("Get one MonthlyCards for renewal update")
	public @ResponseBody ResultObject<List<MonthlyCardDto>> revewalUpdate(@RequestBody MonthlyCardDto monthlyCardDto) {

		return monthCardsService.renewalUpdate(monthlyCardDto);
	}

}

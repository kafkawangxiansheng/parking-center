package com.spm.controller;

import java.util.Calendar;
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

	@RequestMapping(value = "/update", method = { RequestMethod.POST })
	@ApiOperation("Update MonthlyCard")
	public ResultObject<List<MonthlyCardDto>> updateMonthlyCard(@RequestBody MonthlyCardDto monthlyCardDto) {
		ResultObject<List<MonthlyCardDto>> resultObject = new ResultObject<>();

		MonthlyCardEntity monthlyCardEntity = monthCardsService.getById(monthlyCardDto.getId());
		if (monthlyCardEntity.getCardCode().equals(monthlyCardDto.getCardCode())) {
			monthlyCardDto.setUpdated(Calendar.getInstance().getTimeInMillis());
			resultObject = monthCardsService.save(monthlyCardDto);
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
			@RequestParam(name = "cardType") int cardType, @RequestParam(name = "projectId") long projectId) {
		return cardsService.checkCardAndCardTypeAndProjectId(code, cardType, projectId);
	}

	@RequestMapping(value = "search", method = { RequestMethod.GET })
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

		MonthlyCradSearchForm monthlyCradSearchForm = new MonthlyCradSearchForm();
		if (cardCode != null && !cardCode.isEmpty()) {
			monthlyCradSearchForm.setCardCode(cardCode);
		}
		if (vehicleId != null && !vehicleId.isEmpty()) {
			monthlyCradSearchForm.setVehicleId(vehicleId);
		}
		if (numberEndDate != 0) {
			monthlyCradSearchForm.setNumberEndDate(numberEndDate);
		}
		if (statusDate != 0) {
			monthlyCradSearchForm.setStatusDate(statusDate);
		}
		if (customerName != null && !customerName.isEmpty()) {
			monthlyCradSearchForm.setCustomerName(customerName);
		}
		if (projectId != 0) {
			monthlyCradSearchForm.setProjectId(projectId);
		}

		return monthCardsService.search(paging, monthlyCradSearchForm);
	}

	@RequestMapping(value = "renewal/search", method = { RequestMethod.GET })
	@ApiOperation("Get all MonthlyCards by form search")
	public @ResponseBody ResultObject<List<MonthlyCardDto>> getRenewalSearch(
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "cardCode", required = false) String cardCode,
			@RequestParam(name = "customerName", required = false) String customerName,
			@RequestParam(name = "projectId", required = false) long projectId) {

		MonthlyCradSearchForm monthlyCradSearchForm = new MonthlyCradSearchForm();
		Pageable paging = PageRequest.of(page, PagingConstants.ROWS_PER_PAGE);

		if (cardCode != null && !cardCode.isEmpty()) {
			monthlyCradSearchForm.setCardCode(cardCode);
		}
		if (customerName != null && !customerName.isEmpty()) {
			monthlyCradSearchForm.setCustomerName(customerName);
		}
		if (projectId != 0) {
			monthlyCradSearchForm.setProjectId(projectId);
		}
		return monthCardsService.renewalSearch(paging, monthlyCradSearchForm);
	}

	// renewal
	@RequestMapping(value = "renewal/findOne", method = { RequestMethod.GET })
	@ApiOperation("Get one MonthlyCards for renewal")
	public @ResponseBody ResultObject<List<MonthlyCardDto>> renewalFindOne(
			@RequestParam(name = "id", required = false) long id) {

		return monthCardsService.renewalFindOne(id);
	}

	// renewal update
	@RequestMapping(value = "revewal/update", method = { RequestMethod.POST })
	@ApiOperation("Get one MonthlyCards for renewal update")
	public @ResponseBody ResultObject<List<MonthlyCardDto>> revewalUpdate(@RequestBody MonthlyCardDto monthlyCardDto) {

		return monthCardsService.renewalUpdate(monthlyCardDto);
	}

}

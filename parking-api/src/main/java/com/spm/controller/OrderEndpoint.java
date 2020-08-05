package com.spm.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spm.constants.PagingConstants;
import com.spm.dto.OrderDto;
import com.spm.dto.ResultObject;
import com.spm.search.form.OrderSearchForm;
import com.spm.service.OrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by Vincent on 02/10/2018
 */
@RestController
@RequestMapping("/orders")
@Api(value = "parking Endpoint")
public class OrderEndpoint {

	@Autowired
	private OrderService orderService;

	@PostMapping(value = "/batchinsert")
	@ApiOperation("Orders batch syncs")
	public void batchSync(@RequestBody List<OrderDto> ordersDtos) {
		for (OrderDto orderDto : ordersDtos) {
			OrderDto existingDto = orderService.findByOrderIdAndProjectId(orderDto.getOrderId(),
					orderDto.getProjectId());
			if (existingDto != null && (existingDto.getCheckoutTime() == null || existingDto.getCheckoutTime() == 0)) {
				// just update existing record with new data
				orderDto.setId(existingDto.getId());
			}
			orderService.save(orderDto);
		}

	}

	@GetMapping(value = "")
	@ApiOperation("Get all in/out logs")
	public @ResponseBody ResultObject<List<OrderDto>> getAll(
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "projectId", required = false) String projectId,
			@RequestParam(name = "cardCode", required = false) String cardCode,
			@RequestParam(name = "cardStt", required = false) String cardStt,
			@RequestParam(name = "dateFrom", required = false) String dateFrom,
			@RequestParam(name = "dateTo", required = false) String dateTo,
			@RequestParam(name = "carNumber", required = false) String carNumber,
			@RequestParam(name = "isMonthlyCard", required = false, defaultValue = "0") int isMonthlyCard,
			@RequestParam(name = "employeeId", required = false) String employeeId,
			@RequestParam(name = "employeeIdOut", required = false) String employeeIdOut) {
		Pageable paging = PageRequest.of(page, PagingConstants.ROWS_PER_PAGE);
		OrderSearchForm orderSearchForm = new OrderSearchForm();
		if (projectId != null && !projectId.isEmpty()) {
			orderSearchForm.setProjectId(projectId);
		}
		if (cardCode != null && !cardCode.isEmpty()) {
			try {
				orderSearchForm.setCardCode(URLDecoder.decode(cardCode, "UTF8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (cardStt != null && !cardStt.isEmpty()) {
			orderSearchForm.setCardStt(cardStt);
		}
		if (carNumber != null && !carNumber.isEmpty()) {
			orderSearchForm.setCarNumber(carNumber);
		}
		if (dateFrom != null && !dateFrom.isEmpty()) {
			orderSearchForm.setDateFrom(dateFrom);
		}
		if (dateTo != null && !dateTo.isEmpty()) {
			orderSearchForm.setDateTo(dateTo);
		}
		if (employeeId != null && !employeeId.isEmpty()) {
			orderSearchForm.setEmployeeId(employeeId);
		}
		if (employeeIdOut != null && !employeeIdOut.isEmpty()) {
			orderSearchForm.setEmployeeIdOut(employeeIdOut);
		}

		orderSearchForm.setIsMonthlyCard(isMonthlyCard);

		return orderService.findAll(paging, orderSearchForm);
	}

	@GetMapping(value = "/existing-in-part/")
	@ApiOperation("Get all existing car in part")
	public @ResponseBody ResultObject<List<OrderDto>> getAllExistingInPart(
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "projectId", required = false) String projectId) {
		Pageable paging = PageRequest.of(page, PagingConstants.ROWS_PER_PAGE);
		OrderSearchForm orderSearchForm = new OrderSearchForm();
		if (projectId != null && !projectId.isEmpty()) {
			orderSearchForm.setProjectId(projectId);
		}
		return orderService.findAllVehicleExistingInParking(paging, orderSearchForm);
	}

	@PostMapping(value = "/save-lost-card/")
	@ApiOperation("Save lost card")
	public @ResponseBody void saveLostCard(@RequestParam(name = "ids", required = false) String ids) {
		orderService.saveLostCard(ids);
	}

	@GetMapping(value = "/export")
	@ApiOperation("Export all in/out logs")
	public @ResponseBody ResultObject<List<OrderDto>> export(
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "projectId", required = false) String projectId,
			@RequestParam(name = "cardCode", required = false) String cardCode,
			@RequestParam(name = "cardStt", required = false) String cardStt,
			@RequestParam(name = "dateFrom", required = false) String dateFrom,
			@RequestParam(name = "dateTo", required = false) String dateTo,
			@RequestParam(name = "carNumber", required = false) String carNumber,
			@RequestParam(name = "isMonthlyCard", required = false, defaultValue = "0") int isMonthlyCard,
			@RequestParam(name = "employeeId", required = false) String employeeId) {

		Pageable paging = PageRequest.of(page, PagingConstants.MAX_ROWS_FOR_EXPORT);

		OrderSearchForm orderSearchForm = new OrderSearchForm();

		if (projectId != null && !projectId.isEmpty()) {
			orderSearchForm.setProjectId(projectId);
		}
		if (cardCode != null && !cardCode.isEmpty()) {
			orderSearchForm.setCardCode(cardCode);
		}
		if (cardStt != null && !cardStt.isEmpty()) {
			orderSearchForm.setCardStt(cardStt);
		}
		if (carNumber != null && !carNumber.isEmpty()) {
			orderSearchForm.setCarNumber(carNumber);
		}
		if (dateFrom != null && !dateFrom.isEmpty()) {
			orderSearchForm.setDateFrom(dateFrom);
		}
		if (dateTo != null && !dateTo.isEmpty()) {
			orderSearchForm.setDateTo(dateTo);
		}
		if (employeeId != null && !employeeId.isEmpty()) {
			orderSearchForm.setEmployeeId(employeeId);
		}
		orderSearchForm.setIsMonthlyCard(isMonthlyCard);

		return orderService.findAll(paging, orderSearchForm);
	}

}

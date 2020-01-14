package com.spm.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spm.common.util.DateUtil;
import com.spm.common.util.constant.SessionConstants;
import com.spm.constants.PagingConstants;
import com.spm.dto.EmployeeDto;
import com.spm.dto.OrderDto;
import com.spm.dto.ResultObject;
import com.spm.exception.UnauthorizedException;
import com.spm.export.InOutExport;
import com.spm.search.form.OrderSearchForm;
import com.spm.service.EmployeeService;
import com.spm.service.OrderService;

@Controller
@RequestMapping(path="/in-out-monthly-logs")
public class InOutMonthlyCardController {
	
	@Autowired
	OrderService  orderService;
	
	@Autowired
	EmployeeService employeeServie;
	
	@RequestMapping(value = "", method= {RequestMethod.GET})
	public String index(
			@RequestParam(name="page", required=false, defaultValue="0")int page,
			@RequestParam(name="employeeId", required=false)String employeeId,
			@RequestParam(name="cardCode", required  =  false) String cardCode,
			@RequestParam(name="cardStt", required  =  false) String cardStt,
			@RequestParam(name="dateFrom", required  =  false) String dateFrom,
			@RequestParam(name="hourFrom", required=false)String hourFrom,
			@RequestParam(name="minFrom", required=false)String minFrom,
			@RequestParam(name="dateTo", required  =  false) String dateTo,
			@RequestParam(name="hourTo", required=false)String hourTo,
			@RequestParam(name="minTo", required=false)String minTo,
			@RequestParam(name="carNumber", required  =  false) String carNumber,
			Model model,  HttpServletRequest request) throws UnauthorizedException, ParseException {
		
		List<String> projects = (List<String>)request.getSession().getAttribute(SessionConstants.PROJECT_SESSION_NAME);
		
		
		OrderSearchForm orderSearchForm = new OrderSearchForm();
		orderSearchForm.setEmployeeId(employeeId);
		orderSearchForm.setDateFrom(dateFrom);
		orderSearchForm.setHourFrom(hourFrom);
		orderSearchForm.setMinFrom(minFrom);
		orderSearchForm.setCardStt(cardStt);
		orderSearchForm.setCarNumber(carNumber);
		if(dateFrom ==  null || dateFrom.isEmpty()) {
			orderSearchForm.setDateFrom(DateUtil.getCurrentDateString());
			orderSearchForm.setHourFrom("00");
			orderSearchForm.setMinFrom("00");
		}
		
		orderSearchForm.setDateTo(dateTo);
		orderSearchForm.setHourTo(hourTo);
		orderSearchForm.setMinTo(minTo);
		if(dateTo == null || dateTo.isEmpty()) {
			orderSearchForm.setDateTo(orderSearchForm.getDateFrom());
			orderSearchForm.setHourTo("23");
			orderSearchForm.setMinTo("59");
		}
		orderSearchForm.setIsMonthlyCard(1);
		orderSearchForm.setProjectId(projects.get(0));
		
		if(dateFrom  == null || dateFrom.isEmpty()) {
			orderSearchForm.setDateFrom(DateUtil.getCurrentDateString());
		}
		if(dateTo  == null || dateTo.isEmpty()) {
			orderSearchForm.setDateTo(orderSearchForm.getDateFrom());
		}
		
		model.addAttribute("orderSearchForm", orderSearchForm);
		
		if(page > 0) {
			page = page - 1;
		}
		Pageable pageable = PageRequest.of(page, PagingConstants.ROWS_PER_PAGE);
		ResultObject<List<OrderDto>> result = orderService.getAllOrder(orderSearchForm,pageable);
		
		List<Integer>  totalPages = new ArrayList<Integer>();
		if( page <= 5) {
			for(int p = 1; p <= result.getTotalPages(); p ++) {
				totalPages.add(p);
				if(p  > 10) {
					break;
				}
			}
		} else {
			for(int p = page  -  5; p <= result.getTotalPages(); p ++) {
				totalPages.add(p);
				if(p  > (page + 5)) {
					break;
				}
			}
		}
		
		ResultObject<List<EmployeeDto>>  employees = employeeServie.getAllEmployeeByProjectId(Long.valueOf(projects.get(0)));
		
		model.addAttribute("employees", employees.getData());
		model.addAttribute("orders", result.getData());
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("maxPage", result.getTotalPages());
		model.addAttribute("totalRows", result.getTotalRows());
		model.addAttribute("currentPage", page+1);
		
        return "inOutMonthlyCardPage";
	}
	
	@RequestMapping(value = "/export", method= {RequestMethod.GET})
	public InOutExport export(
			@RequestParam(name="page", required=false, defaultValue="0")int page,
			@RequestParam(name="employeeId", required=false)String employeeId,
			@RequestParam(name="cardCode", required  =  false) String cardCode,
			@RequestParam(name="cardStt", required  =  false) String cardStt,
			@RequestParam(name="dateFrom", required  =  false) String dateFrom,
			@RequestParam(name="hourFrom", required=false)String hourFrom,
			@RequestParam(name="minFrom", required=false)String minFrom,
			@RequestParam(name="dateTo", required  =  false) String dateTo,
			@RequestParam(name="hourTo", required=false)String hourTo,
			@RequestParam(name="minTo", required=false)String minTo,
			@RequestParam(name="carNumber", required  =  false) String carNumber,
			Model model,  HttpServletRequest request) throws UnauthorizedException, ParseException {
		
		List<String> projects = (List<String>)request.getSession().getAttribute(SessionConstants.PROJECT_SESSION_NAME);
		
		OrderSearchForm orderSearchForm = new OrderSearchForm();
		orderSearchForm.setEmployeeId(employeeId);
		orderSearchForm.setDateFrom(dateFrom);
		orderSearchForm.setHourFrom(hourFrom);
		orderSearchForm.setMinFrom(minFrom);
		orderSearchForm.setCardStt(cardStt);
		orderSearchForm.setCarNumber(carNumber);
		if(dateFrom ==  null || dateFrom.isEmpty()) {
			orderSearchForm.setDateFrom(DateUtil.getCurrentDateString());
			orderSearchForm.setHourFrom("00");
			orderSearchForm.setMinFrom("00");
		}
		
		orderSearchForm.setDateTo(dateTo);
		orderSearchForm.setHourTo(hourTo);
		orderSearchForm.setMinTo(minTo);
		if(dateTo == null || dateTo.isEmpty()) {
			orderSearchForm.setDateTo(orderSearchForm.getDateFrom());
			orderSearchForm.setHourTo("23");
			orderSearchForm.setMinTo("59");
		}
		orderSearchForm.setIsMonthlyCard(1);
		orderSearchForm.setProjectId(projects.get(0));
		
		if(dateFrom  == null || dateFrom.isEmpty()) {
			orderSearchForm.setDateFrom(DateUtil.getCurrentDateString());
		}
		if(dateTo  == null || dateTo.isEmpty()) {
			orderSearchForm.setDateTo(orderSearchForm.getDateFrom());
		}
		
		
		if(page > 0) {
			page = page - 1;
		}
		Pageable pageable = PageRequest.of(page, PagingConstants.MAX_ROWS_FOR_EXPORT);
		ResultObject<List<OrderDto>> result = orderService.exportAllOrder(orderSearchForm,pageable);
		
		
		model.addAttribute("orders", result.getData());
		model.addAttribute("fileName", "in-out-monthly-logs.xls");
		
        return new InOutExport();
	}

}

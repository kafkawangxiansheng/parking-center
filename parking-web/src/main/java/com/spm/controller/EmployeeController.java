package com.spm.controller;

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

import com.spm.constants.PagingConstants;
import com.spm.dto.EmployeeDto;
import com.spm.dto.ResultObject;
import com.spm.exception.UnauthorizedException;
import com.spm.search.form.EmployeeSearchForm;
import com.spm.service.EmployeeService;

@Controller
@RequestMapping(path="/employees")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@RequestMapping(value = "", method = { RequestMethod.GET })
	public String index(
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "userName", required = false) String userName,
			@RequestParam(name = "pass", required = false) String pass,
			@RequestParam(name = "position", required = false) String position,
			@RequestParam(name = "sex", required = false) String sex,
			Model model, HttpServletRequest request) throws UnauthorizedException {

		EmployeeSearchForm employeeSearchForm = new EmployeeSearchForm();
		employeeSearchForm.setName(userName);
		employeeSearchForm.setPosition(position);
		
		model.addAttribute("employeeSearchForm", employeeSearchForm);
		
		if(page > 0) {
			page = page - 1;
		}
		Pageable pageable = PageRequest.of(page, PagingConstants.ROWS_PER_PAGE);
		
		ResultObject<List<EmployeeDto>> result = employeeService.getAllEmployee(employeeSearchForm, pageable);
		
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
		
		model.addAttribute("employees", result.getData());
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("maxPage", result.getTotalPages());
		model.addAttribute("currentPage", page+1);
		
		return "employeePage";
	}
	
	@RequestMapping(value="addNewEmployee", method = { RequestMethod.GET})
	public String showAddNewEmployeePage() {
		return "addNewEmployeePage";
	}
	
}

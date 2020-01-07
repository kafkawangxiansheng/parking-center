package com.spm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path="/employees")
public class EmployeeTypeController {
	
	@RequestMapping(value="type", method = { RequestMethod.GET})
	public String showEmployeeTypePage() {
		return "employeeTypePage";
	}
	
	@RequestMapping(value="addNewEmployeeType", method = { RequestMethod.GET})
	public String showAddNewEmployeePage() {
		return "addNewEmployeeTypePage";
	}

}

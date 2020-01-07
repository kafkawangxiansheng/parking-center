package com.spm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path="/in-out-logs")
public class InOutMonthlyCardController {
	
	@RequestMapping(value="monthlyCard", method= {RequestMethod.GET})
	public String showInOutMonthlyCardPage(){
		return "inOutMonthlyCardPage";
	}

}

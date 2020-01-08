package com.spm.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spm.exception.UnauthorizedException;

@Controller
@RequestMapping(path = "monthlycard")
public class MonthlyCardController {

	//index == monthlycardPage
	@RequestMapping(value = "", method = { RequestMethod.GET })
	public String index(Model model, HttpServletRequest request) throws UnauthorizedException {
		model.addAttribute("monthlycards","");
		return "monthlyCardPage";
	}
	
	@RequestMapping(value = "viewlog", method = { RequestMethod.GET })
	public String monthlyCardViewlog(Model model, HttpServletRequest request) throws UnauthorizedException {
		model.addAttribute("monthlycards","");
		return "monthlyCardViewLogPage";
	}
	
	@RequestMapping(value = "renewal", method = { RequestMethod.GET })
	public String monthlyCardRenewal(Model model, HttpServletRequest request) throws UnauthorizedException {
		model.addAttribute("monthlycards","");
		return "monthlyCardRenewalPage";
	}
	
	@RequestMapping(value = "disablelist", method = { RequestMethod.GET })
	public String monthlyCardDisablelist(Model model, HttpServletRequest request) throws UnauthorizedException {
		model.addAttribute("monthlycards","");
		return "monthlyCardDisableListPage";
	}
	
	@RequestMapping(value = "active", method = { RequestMethod.GET })
	public String monthlyCardActive(Model model, HttpServletRequest request) throws UnauthorizedException {
		model.addAttribute("monthlycards","");
		return "monthlyCardActivePage";
	}
	
	@RequestMapping(value = "newCard", method = { RequestMethod.GET })
	public String update(Model model, HttpServletRequest request) throws UnauthorizedException {
		model.addAttribute("monthlycards","");
		return "monthlyCardAddForm";
	}
	
	@RequestMapping(value = "addNewMonthlyCard", method = { RequestMethod.GET })
	public String showAddNewMonthlyCardPage() {
		return "addNewMonthlyCardPage";
	}
	
}

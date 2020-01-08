package com.spm.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spm.exception.UnauthorizedException;

@Controller
@RequestMapping(path = "setting")
public class SettingController {

	//index == monthlycardPage
	@RequestMapping(value = "", method = { RequestMethod.GET })
	public String index(Model model, HttpServletRequest request) throws UnauthorizedException {
		model.addAttribute("settings","");
		return "settingDisplayPage";
	}
	
	@RequestMapping(value = "display", method = { RequestMethod.GET })
	public String display(Model model, HttpServletRequest request) throws UnauthorizedException {
		model.addAttribute("settings","");
		return "settingDisplayPage";
	}
	
	@RequestMapping(value = "order", method = { RequestMethod.GET })
	public String monthlyCardViewlog(Model model, HttpServletRequest request) throws UnauthorizedException {
		model.addAttribute("settings","");
		return "settingOrderPage";
	}
	
	@RequestMapping(value = "permission", method = { RequestMethod.GET })
	public String monthlyCardRenewal(Model model, HttpServletRequest request) throws UnauthorizedException {
		model.addAttribute("settings","");
		return "settingPermissionPage";
	}
	
	@RequestMapping(value = "systemlog", method = { RequestMethod.GET })
	public String monthlyCardDisablelist(Model model, HttpServletRequest request) throws UnauthorizedException {
		model.addAttribute("settings","");
		return "settingSystemlogPage";
	}
	
}

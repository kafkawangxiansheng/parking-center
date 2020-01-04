package com.spm.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spm.exception.UnauthorizedException;

@Controller
@RequestMapping(path="company-project")
public class CompanyProjectController {
	
	@RequestMapping(value = "", method= {RequestMethod.GET})
	public String index( HttpServletRequest request) throws UnauthorizedException {
		return "companyProjectPage";
	}
}

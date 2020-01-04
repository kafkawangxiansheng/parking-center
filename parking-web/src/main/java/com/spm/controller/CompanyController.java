package com.spm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spm.dto.CompanyDto;
import com.spm.dto.ResultObject;
import com.spm.exception.UnauthorizedException;
import com.spm.service.CompanyService;

@Controller
@RequestMapping(path="company")
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;
	
	@RequestMapping(value = "", method= {RequestMethod.GET})
	public String index(Model model,  HttpServletRequest request) throws UnauthorizedException {
		ResultObject<List<CompanyDto>> companysMap = companyService.getAllCompanies();
		model.addAttribute("companies", companysMap.getData());
		return "companyPage";
	}
	
	@RequestMapping(value = "add-company", method= {RequestMethod.GET})
	public  String addProfile(Model model) throws UnauthorizedException{
		model.addAttribute("companyDto", new CompanyDto());
		return "addCompany";
	}
	
	@RequestMapping(value = "edit-company/{id}", method= {RequestMethod.GET})
	public  String editProfile(Model model, @PathVariable("id")Long id) throws UnauthorizedException{
		CompanyDto projecstDto = companyService.getCompanyById(id);
		model.addAttribute("companyDto", projecstDto);
		return "editCompany";
	}
	
	@RequestMapping(value = "add-company", method= {RequestMethod.POST,RequestMethod.PUT})
	public  String doAddProfile(Model model, @Valid @ModelAttribute("companyDto") CompanyDto projecstDto) throws UnauthorizedException{
		companyService.addCompany(projecstDto);
		return "redirect:/company";
	}
	
	@RequestMapping(value = "delete-company/{id}", method= {RequestMethod.GET})
	public  String deleteProfile(Model model, @PathVariable("id") Long id) throws UnauthorizedException{
		companyService.deleteCompany(id);
		return "redirect:/company";
	}
	
}

package com.spm.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.spm.common.util.DateUtil;
import com.spm.dto.CompanyDto;
import com.spm.dto.MonthlyCardDto;
import com.spm.dto.ProjectsDto;
import com.spm.dto.ResultObject;
import com.spm.dto.VehicleDto;
import com.spm.exception.UnauthorizedException;
import com.spm.service.CompanyService;
import com.spm.service.MonthlyCradService;
import com.spm.service.ProjectService;
import com.spm.service.VehicleService;

@Controller
@RequestMapping(path = "/monthlyCard")
public class MonthlyCardController {
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private MonthlyCradService monthlyCradService;
	
	@Autowired
	private CompanyService companyService;

	//index == monthlycardPage
	@RequestMapping(value = "", method = { RequestMethod.GET })
	public String index(Model model, HttpServletRequest request) throws UnauthorizedException {
		ResultObject<List<MonthlyCardDto>> monthlyCardMap = monthlyCradService.getAllMonthlyCard();
		model.addAttribute("listMonthlycard",monthlyCardMap.getData());
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
	
//	monthlyCard
	
	@RequestMapping(value = "add", method = { RequestMethod.GET })
	public String showAddNewMonthlyCardPage(Model model) throws UnauthorizedException {
		model.addAttribute("monthlyCardDto", new MonthlyCardDto());
		ResultObject<List<CompanyDto>> companyMap = companyService.getListCompanies();
		model.addAttribute("listCompany", companyMap.getData());
		ResultObject<List<ProjectsDto>> projectMap = projectService.getAllProjects();
		model.addAttribute("listProject", projectMap.getData());
		ResultObject<List<VehicleDto>> vehicleMap = vehicleService.getListAllVehicle();
		model.addAttribute("listVehicle", vehicleMap.getData());
		return "addMonthlyCardForm";
	}
	
	@RequestMapping(value = "edit/{id}", method= {RequestMethod.GET})
	public  String editMonthlyCard(Model model, @PathVariable("id")Long id) throws UnauthorizedException{
		MonthlyCardDto monthlyCardDto = monthlyCradService.getMonthlyCardById(id);
		
		//  convert long date to string date
		String startDateString = new SimpleDateFormat("dd/MM/yyyy").format(new Date(monthlyCardDto.getStartDate()));
		String endDateString = new SimpleDateFormat("dd/MM/yyyy").format(new Date(monthlyCardDto.getEndDate()));
		monthlyCardDto.setCardcode(monthlyCardDto.getCard().getCode());
		
		monthlyCardDto.setStartDateString(startDateString);
		monthlyCardDto.setEndDateString(endDateString);
		
		model.addAttribute("monthlyCardDto", monthlyCardDto);
		ResultObject<List<CompanyDto>> companyMap = companyService.getListCompanies();
		model.addAttribute("listCompany", companyMap.getData());
		ResultObject<List<ProjectsDto>> projectMap = projectService.getAllProjects();
		model.addAttribute("listProject", projectMap.getData());
		return "editMonthlyCardForm";
	}
	
	@RequestMapping(value = "/add", method= {RequestMethod.POST,RequestMethod.PUT})
	public  String doAddMonthlyCard(Model model, @Valid @ModelAttribute("monthlyCardDto") MonthlyCardDto monthlyCardDto) throws UnauthorizedException, ParseException{
		long startDateLong = DateUtil.getParseStringDateToLong(monthlyCardDto.getStartDateString());
		long endDateLong = DateUtil.getParseStringDateToLong(monthlyCardDto.getEndDateString());
		monthlyCardDto.setStartDate(startDateLong);
		monthlyCardDto.setEndDate(endDateLong);
		boolean addSuccess = monthlyCradService.addMonthlyCard(monthlyCardDto);
		if(addSuccess) {
			return "redirect:/monthlyCard";
		}else {
			String error = "Thẻ đã sử dụng hoặc chưa kích hoạt mã trên hệ thống!";
			model.addAttribute("errorMessage",error );
			
			String startDateString = new SimpleDateFormat("dd/MM/yyyy").format(new Date(monthlyCardDto.getStartDate()));
			String endDateString = new SimpleDateFormat("dd/MM/yyyy").format(new Date(monthlyCardDto.getEndDate()));
			monthlyCardDto.setStartDateString(startDateString);
			monthlyCardDto.setEndDateString(endDateString);
			
			model.addAttribute("monthlyCardDto", monthlyCardDto);
			ResultObject<List<CompanyDto>> companyMap = companyService.getListCompanies();
			model.addAttribute("listCompany", companyMap.getData());
			ResultObject<List<ProjectsDto>> projectMap = projectService.getAllProjects();
			model.addAttribute("listProject", projectMap.getData());
			return "addMonthlyCardForm";
		}
	}
	
	@RequestMapping(value = "/delete/{id}", method= {RequestMethod.GET})
	public  String deleteMonthlyCard(Model model, @PathVariable("id") Long id) throws UnauthorizedException{
		monthlyCradService.deleteMonthlyCard(id);
		return "redirect:/monthlyCard";
	}
	
}

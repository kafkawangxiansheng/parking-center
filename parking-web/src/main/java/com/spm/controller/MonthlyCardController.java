package com.spm.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spm.common.util.DateUtil;
import com.spm.common.util.constant.SessionConstants;
import com.spm.constants.PagingConstants;
import com.spm.dto.CompanyDto;
import com.spm.dto.MonthlyCardDto;
import com.spm.dto.ProjectsDto;
import com.spm.dto.ResultObject;
import com.spm.dto.VehicleDto;
import com.spm.exception.UnauthorizedException;
import com.spm.search.form.MonthlyCradSearchForm;
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
	
	public String getProjectId(HttpServletRequest request) {
		List<String> projects = (List<String>)request.getSession().getAttribute(SessionConstants.PROJECT_SESSION_NAME);
		return projects.get(0);
	}
	
	//index == monthlycardPage
	@RequestMapping(value = "", method = { RequestMethod.GET })
	public String index(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "cardCode", required = false) String cardCode,
			@RequestParam(name = "statusDate", required = false, defaultValue = "0") Integer statusDate,
			@RequestParam(name = "vehicleId", required = false) String vehicleId,
			@RequestParam(name = "numberEndDate", required = false, defaultValue = "0") Integer numberEndDate,
			@RequestParam(name = "customerName", required = false) String customerName,
			Model model, HttpServletRequest request) throws UnauthorizedException {
		
		MonthlyCradSearchForm monthlyCradSearchForm = new MonthlyCradSearchForm();
		monthlyCradSearchForm.setCardCode(cardCode);
		monthlyCradSearchForm.setVehicleId(vehicleId);
		monthlyCradSearchForm.setStatusDate(statusDate);
		monthlyCradSearchForm.setNumberEndDate(numberEndDate);
		monthlyCradSearchForm.setCustomerName(customerName);
		monthlyCradSearchForm.setProjectId(Long.parseLong(getProjectId(request)));
		if(page > 0) {
			page = page - 1;
		}
		Pageable pageable = PageRequest.of(page, PagingConstants.ROWS_PER_PAGE);
		ResultObject<List<MonthlyCardDto>> result = monthlyCradService.getAllMonthlyCard(monthlyCradSearchForm,pageable );
		
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
		
		ResultObject<List<VehicleDto>> listAllVehicle = vehicleService.getListAllVehicle();
		model.addAttribute("listMonthlycard",result);
		model.addAttribute("vehicles",listAllVehicle.getData());
		model.addAttribute("monthlyCradSearchForm",monthlyCradSearchForm);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalRows", result.getTotalRows());
		model.addAttribute("maxPage", result.getTotalPages());
		model.addAttribute("currentPage", page+1);
		model.addAttribute("currentDate", new Date().getTime());
		return "monthlyCardPage";
	}
	
	@RequestMapping(value = "viewlog", method = { RequestMethod.GET })
	public String monthlyCardViewlog(Model model, HttpServletRequest request) throws UnauthorizedException {
		model.addAttribute("monthlycards","");
		return "monthlyCardViewLogPage";
	}
	
	// renewaltMonthlyCard
	@RequestMapping(value = "renewal/{id}", method= {RequestMethod.GET})
	public  String renewaltMonthlyCard(Model model, @PathVariable("id")Long id) throws UnauthorizedException{
		MonthlyCardDto monthlyCardDto = monthlyCradService.getMonthlyCardById(id);
		return "monthlyCardRenewalForm";
		
	}
	// renewal
		@RequestMapping(value = "renewal", method = { RequestMethod.GET })
		public String monthlyCardRenewalSearch(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
				@RequestParam(name = "cardCode", required = false) String cardCode,
				@RequestParam(name = "customerName", required = false) String customerName,
				Model model, HttpServletRequest request) throws UnauthorizedException {
			
			ResultObject<List<MonthlyCardDto>> result = new ResultObject<List<MonthlyCardDto>>();
			MonthlyCradSearchForm monthlyCradSearchForm = new MonthlyCradSearchForm();
			if(page > 0) {
				page = page - 1;
			}
			Pageable pageable = PageRequest.of(page, PagingConstants.ROWS_PER_PAGE);
			List<Integer>  totalPages = new ArrayList<Integer>();
				monthlyCradSearchForm.setCardCode(cardCode);
				monthlyCradSearchForm.setCustomerName(customerName);
				monthlyCradSearchForm.setProjectId(Long.parseLong(getProjectId(request)));
				result = monthlyCradService.getRenewal(monthlyCradSearchForm, pageable);
				
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
			
			model.addAttribute("listMonthlycard",result.getData());
			model.addAttribute("monthlyCradSearchForm",monthlyCradSearchForm);
			model.addAttribute("totalPages", totalPages);
			model.addAttribute("totalRows", result.getTotalRows());
			model.addAttribute("maxPage", result.getTotalPages());
			model.addAttribute("currentPage", page+1);
			model.addAttribute("currentDate", new Date().getTime());
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
		monthlyCardDto.setStartDateString(startDateString);
		monthlyCardDto.setEndDateString(endDateString);
		
		model.addAttribute("monthlyCardDto", monthlyCardDto);
		ResultObject<List<CompanyDto>> companyMap = companyService.getListCompanies();
		model.addAttribute("listCompany", companyMap.getData());
		ResultObject<List<ProjectsDto>> projectMap = projectService.getAllProjects();
		model.addAttribute("listProject", projectMap.getData());
		return "editMonthlyCardForm";
	}
	
	@RequestMapping(value = "/add", method= {RequestMethod.POST})
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
			model = refeshSelectForm(model);
			return "addMonthlyCardForm";
		}
	}
	
	@RequestMapping(value = "/update", method= {RequestMethod.POST})
	public  String doUpdateMonthlyCard(Model model, @Valid @ModelAttribute("monthlyCardDto") MonthlyCardDto monthlyCardDto) throws UnauthorizedException, ParseException{
		long startDateLong = DateUtil.getParseStringDateToLong(monthlyCardDto.getStartDateString());
		long endDateLong = DateUtil.getParseStringDateToLong(monthlyCardDto.getEndDateString());
		monthlyCardDto.setStartDate(startDateLong);
		monthlyCardDto.setEndDate(endDateLong);
		boolean addSuccess = monthlyCradService.updateMonthlyCard(monthlyCardDto);
		if(addSuccess) {
			return "redirect:/monthlyCard";
		}else {
			String error = "Thẻ đã sử dụng hoặc chưa kích hoạt mã trên hệ thống!";
			model.addAttribute("errorMessage",error );
			
			String startDateString = new SimpleDateFormat("dd/MM/yyyy").format(new Date(monthlyCardDto.getStartDate()));
			String endDateString = new SimpleDateFormat("dd/MM/yyyy").format(new Date(monthlyCardDto.getEndDate()));
			monthlyCardDto.setStartDateString(startDateString);
			monthlyCardDto.setEndDateString(endDateString);
			model = refeshSelectForm(model);
			return "editMonthlyCardForm";
		}
	}
	
	@RequestMapping(value = "/delete/{id}", method= {RequestMethod.GET})
	public  String deleteMonthlyCard(Model model, @PathVariable("id") Long id) throws UnauthorizedException{
		monthlyCradService.deleteMonthlyCard(id);
		return "redirect:/monthlyCard";
	}
	
	//refeshSelectForm
	public  Model refeshSelectForm(Model model) throws UnauthorizedException{
		ResultObject<List<CompanyDto>> companyMap = companyService.getListCompanies();
		model.addAttribute("listCompany", companyMap.getData());
		ResultObject<List<ProjectsDto>> projectMap = projectService.getAllProjects();
		model.addAttribute("listProject", projectMap.getData());
		return model;
	}
	
}

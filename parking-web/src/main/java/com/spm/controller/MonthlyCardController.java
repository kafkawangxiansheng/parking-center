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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spm.common.util.DateUtil;
import com.spm.common.util.constant.SessionConstants;
import com.spm.constants.PagingConstants;
import com.spm.dto.CompanyDto;
import com.spm.dto.MonthlyCardDto;
import com.spm.dto.MonthlyCardLogDto;
import com.spm.dto.ProjectsDto;
import com.spm.dto.ResultObject;
import com.spm.dto.VehicleDto;
import com.spm.search.form.MonthlyCardSearchForm;
import com.spm.search.form.VehicleSearchForm;
import com.spm.service.CompanyService;
import com.spm.service.MonthlyCardLogService;
import com.spm.service.MonthlyCardService;
import com.spm.service.ProjectService;
import com.spm.service.VehicleService;

@Controller
@RequestMapping(path = "/monthlyCard")
public class MonthlyCardController {

	@Autowired
	private VehicleService vehicleService;

	@Autowired
	private MonthlyCardService monthlyCardService;

	@Autowired
	private MonthlyCardLogService monthlyCardLogService;

	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private ProjectService projectService;

	public String getProjectId(HttpServletRequest request) {
		List<String> projects = (List<String>) request.getSession().getAttribute(SessionConstants.PROJECT_SESSION_NAME);
		return projects.get(0);
	}
	
	private String getCurentUsername() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = principal.toString();
		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		} 
		return username;
	}

	@GetMapping(value = "")
	public String index(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "cardCode", required = false) String cardCode,
			@RequestParam(name = "statusDate", required = false, defaultValue = "0") Integer statusDate,
			@RequestParam(name = "vehicleId", required = false) String vehicleId,
			@RequestParam(name = "numberEndDate", required = false, defaultValue = "0") Integer numberEndDate,
			@RequestParam(name = "customerName", required = false) String customerName, Model model,
			HttpServletRequest request) {

		MonthlyCardSearchForm monthlyCardSearchForm = new MonthlyCardSearchForm();
		monthlyCardSearchForm.setCardCode(cardCode);
		monthlyCardSearchForm.setVehicleId(vehicleId);
		monthlyCardSearchForm.setStatusDate(statusDate);
		monthlyCardSearchForm.setNumberEndDate(numberEndDate);
		monthlyCardSearchForm.setCustomerName(customerName);
		monthlyCardSearchForm.setProjectId(Long.parseLong(getProjectId(request)));
		if (page > 0) {
			page = page - 1;
		}
		Pageable pageable = PageRequest.of(page, PagingConstants.ROWS_PER_PAGE);
		ResultObject<List<MonthlyCardDto>> result = monthlyCardService.getAllMonthlyCard(monthlyCardSearchForm, pageable);

		List<Integer> totalPages = new ArrayList<>();
		if (page <= 5) {
			for (int p = 1; p <= result.getTotalPages(); p++) {
				totalPages.add(p);
				if (p > 10) {
					break;
				}
			}
		} else {
			for (int p = page - 5; p <= result.getTotalPages(); p++) {
				totalPages.add(p);
				if (p > (page + 5)) {
					break;
				}
			}
		}

		VehicleSearchForm vehicleSearchForm = new VehicleSearchForm();
		vehicleSearchForm.setProjectId(getProjectId(request));
		ResultObject<List<MonthlyCardLogDto>> logs = monthlyCardLogService.getAllMonthlyCardLog(Long.valueOf(getProjectId(request)), pageable);
		model.addAttribute("logs", logs);

		ResultObject<List<VehicleDto>> listAllVehicle = vehicleService.getAllVehicle(vehicleSearchForm);
		model.addAttribute("listMonthlycard", result);
		model.addAttribute("vehicles", listAllVehicle.getData());
		model.addAttribute("monthlyCradSearchForm", monthlyCardSearchForm);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalRows", result.getTotalRows());
		model.addAttribute("maxPage", result.getTotalPages());
		model.addAttribute("currentPage", page + 1);
		model.addAttribute("currentDate", new Date().getTime());

		return "monthlyCardPage";

	}

	@GetMapping(value = "/renewal")
	public String monthlyCardRenewalSearch(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "cardCode", required = false) String cardCode,
			@RequestParam(name = "customerName", required = false) String customerName, Model model,
			HttpServletRequest request) {

		ResultObject<List<MonthlyCardDto>> result = new ResultObject<>();
		MonthlyCardSearchForm monthlyCardSearchForm = new MonthlyCardSearchForm();
		if (page > 0) {
			page = page - 1;
		}
		Pageable pageable = PageRequest.of(page, PagingConstants.ROWS_PER_PAGE);
		List<Integer> totalPages = new ArrayList<>();
		monthlyCardSearchForm.setCardCode(cardCode);
		monthlyCardSearchForm.setCustomerName(customerName);
		monthlyCardSearchForm.setProjectId(Long.parseLong(getProjectId(request)));
		result = monthlyCardService.getRenewal(monthlyCardSearchForm, pageable);

		if (page <= 5) {
			for (int p = 1; p <= result.getTotalPages(); p++) {
				totalPages.add(p);
				if (p > 10) {
					break;
				}
			}
		} else {
			for (int p = page - 5; p <= result.getTotalPages(); p++) {
				totalPages.add(p);
				if (p > (page + 5)) {
					break;
				}
			}
		}

		model.addAttribute("listMonthlycard", result.getData());
		model.addAttribute("monthlyCardSearchForm", monthlyCardSearchForm);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalRows", result.getTotalRows());
		model.addAttribute("maxPage", result.getTotalPages());
		model.addAttribute("currentPage", page + 1);
		model.addAttribute("currentDate", new Date().getTime());
		return "monthlyCardRenewalPage";
	}

	// renewaltMonthlyCard findOne
	@GetMapping(value = "renewal/findOne/{id}")
	@ResponseBody
	public MonthlyCardDto monthlyCardRenwalFindOne(@PathVariable("id") Long id){
		return monthlyCardService.getMonthlyCardById(id);
	}

	// renewaltMonthlyCard update
	@PutMapping(value = "renewal/update")
	public String monthlyCardRenwalUpdate(MonthlyCardDto monthlyCardDto) throws ParseException {
		long startDateLong = DateUtil.getParseStringDateToLong(monthlyCardDto.getStartDateString());
		long endDateLong = DateUtil.getParseStringDateToLong(monthlyCardDto.getEndDateString());
		monthlyCardDto.setStartDate(startDateLong);
		monthlyCardDto.setEndDate(endDateLong);
		monthlyCardDto.setUsername(getCurentUsername());
		monthlyCardService.revewalMonthlyCardUpdate(monthlyCardDto);
		return "redirect:/monthlyCard/renewal";
	}

	@GetMapping(value = "disablelist")
	public String monthlyCardDisablelist(Model model, HttpServletRequest request) {
		model.addAttribute("monthlycards", "");
		return "monthlyCardDisableListPage";
	}

	@GetMapping(value = "active")
	public String monthlyCardActive(Model model, HttpServletRequest request) {
		model.addAttribute("monthlycards", "");
		return "monthlyCardActivePage";
	}

	@GetMapping(value = "add")
	public String showAddNewMonthlyCardPage(Model model, HttpServletRequest request) {
		model.addAttribute("monthlyCardDto", new MonthlyCardDto());
		ResultObject<List<CompanyDto>> companyMap = companyService.getListCompanies();
		model.addAttribute("listCompany", companyMap.getData());
		ResultObject<List<ProjectsDto>> projectMap = projectService.getAllProjects();
		List<ProjectsDto> selectedProject = new ArrayList<>();
		projectMap.getData().forEach(project -> {
			if (getProjectId(request).equals(String.valueOf(project.getId()))) {
				selectedProject.add(project);
				return;
			}
		});
		model.addAttribute("listProject", selectedProject);
		ResultObject<List<VehicleDto>> vehicleMap = vehicleService.getListAllVehicle();
		model.addAttribute("listVehicle", vehicleMap.getData());
		return "addMonthlyCardForm";
	}

	@GetMapping(value = "edit/{id}")
	public String editMonthlyCard(Model model, @PathVariable("id") Long id, HttpServletRequest request) {
		MonthlyCardDto monthlyCardDto = monthlyCardService.getMonthlyCardById(id);

		// convert long date to string date
		String startDateString = new SimpleDateFormat("dd/MM/yyyy").format(new Date(monthlyCardDto.getStartDate()));
		String endDateString = new SimpleDateFormat("dd/MM/yyyy").format(new Date(monthlyCardDto.getEndDate()));
		monthlyCardDto.setStartDateString(startDateString);
		monthlyCardDto.setEndDateString(endDateString);

		model.addAttribute("monthlyCardDto", monthlyCardDto);
		ResultObject<List<CompanyDto>> companyMap = companyService.getListCompanies();
		model.addAttribute("listCompany", companyMap.getData());
		ResultObject<List<ProjectsDto>> projectMap = projectService.getAllProjects();
		List<ProjectsDto> selectedProject = new ArrayList<>();
		projectMap.getData().forEach(project -> {
			if (getProjectId(request).equals(String.valueOf(project.getId()))) {
				selectedProject.add(project);
				return;
			}
		});
		model.addAttribute("listProject", selectedProject);
		return "editMonthlyCardForm";
	}

	@PostMapping(value = "/add")
	public String doAddMonthlyCard(Model model, @Valid @ModelAttribute("monthlyCardDto") MonthlyCardDto monthlyCardDto)
			throws ParseException {
		long startDateLong = DateUtil.getParseStringDateToLong(monthlyCardDto.getStartDateString());
		long endDateLong = DateUtil.getParseStringDateToLong(monthlyCardDto.getEndDateString());
		monthlyCardDto.setStartDate(startDateLong);
		monthlyCardDto.setEndDate(endDateLong);
		monthlyCardDto.setUsername(getCurentUsername());
		boolean addSuccess = monthlyCardService.addMonthlyCard(monthlyCardDto);
		if (addSuccess) {
			return "redirect:/monthlyCard";
		} else {
			String error = "Thẻ đã sử dụng hoặc chưa kích hoạt mã trên hệ thống!";
			model.addAttribute("errorMessage", error);

			String startDateString = new SimpleDateFormat("dd/MM/yyyy").format(new Date(monthlyCardDto.getStartDate()));
			String endDateString = new SimpleDateFormat("dd/MM/yyyy").format(new Date(monthlyCardDto.getEndDate()));
			monthlyCardDto.setStartDateString(startDateString);
			monthlyCardDto.setEndDateString(endDateString);
			model = refeshSelectForm(model);
			return "addMonthlyCardForm";
		}
	}

	@PostMapping(value = "/update")
	public String doUpdateMonthlyCard(Model model, @Valid @ModelAttribute("monthlyCardDto") MonthlyCardDto monthlyCardDto)
			throws ParseException {
		long startDateLong = DateUtil.getParseStringDateToLong(monthlyCardDto.getStartDateString());
		long endDateLong = DateUtil.getParseStringDateToLong(monthlyCardDto.getEndDateString());
		monthlyCardDto.setStartDate(startDateLong);
		monthlyCardDto.setEndDate(endDateLong);
		monthlyCardDto.setUsername(getCurentUsername());
		boolean addSuccess = monthlyCardService.updateMonthlyCard(monthlyCardDto);
		if (addSuccess) {
			return "redirect:/monthlyCard";
		} else {
			String error = "Thẻ đã sử dụng hoặc chưa kích hoạt mã trên hệ thống!";
			model.addAttribute("errorMessage", error);

			String startDateString = new SimpleDateFormat("dd/MM/yyyy").format(new Date(monthlyCardDto.getStartDate()));
			String endDateString = new SimpleDateFormat("dd/MM/yyyy").format(new Date(monthlyCardDto.getEndDate()));
			monthlyCardDto.setStartDateString(startDateString);
			monthlyCardDto.setEndDateString(endDateString);
			monthlyCardDto.setUsername(getCurentUsername());
			model = refeshSelectForm(model);
			return "editMonthlyCardForm";
		}
	}

	@GetMapping(value = "/delete/{id}")
	public String deleteMonthlyCard(Model model, @PathVariable("id") Long id) {
		monthlyCardService.deleteMonthlyCard(id, getCurentUsername());
		return "redirect:/monthlyCard";
	}

	// refeshSelectForm
	public Model refeshSelectForm(Model model) {
		ResultObject<List<CompanyDto>> companyMap = companyService.getListCompanies();
		model.addAttribute("listCompany", companyMap.getData());
		ResultObject<List<ProjectsDto>> projectMap = projectService.getAllProjects();
		model.addAttribute("listProject", projectMap.getData());
		return model;
	}
	
	@RequestMapping(value = "/lock-card/", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody void saveLostCard(@RequestParam(name = "ids", required = false) String ids, Model model,
			HttpServletRequest request) {

		monthlyCardService.lockCard(ids, getCurentUsername());

	}

}

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.spm.exception.UnauthorizedException;
import com.spm.search.form.MonthlyCradSearchForm;
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
	private ProjectService projectService;
	
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private MonthlyCardService monthlyCardService;
	
	@Autowired
	private MonthlyCardLogService monthlyCardLogService;
	
	@Autowired
	private CompanyService companyService;
	
	public String getProjectId(HttpServletRequest request) {
		List<String> projects = (List<String>)request.getSession().getAttribute(SessionConstants.PROJECT_SESSION_NAME);
		return projects.get(0);
	}
	
	@GetMapping(value = "viewlog")
	public String monthlyCardViewlog(Model model, @RequestParam(name="page", defaultValue = "0",required = false)int page, HttpServletRequest request) {
		
		if(page >  0) {
			page =  page - 1;
		}
		Pageable pageable = PageRequest.of(page, PagingConstants.ROWS_PER_PAGE);
		
		ResultObject<List<MonthlyCardLogDto>>  logs = monthlyCardLogService.getAllMonthlyCardLog(Long.valueOf(getProjectId(request)), pageable);
		model.addAttribute("logs",logs);
		
		return "monthlyCardViewLogPage";
	}
	
	
}

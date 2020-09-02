package com.spm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spm.common.util.constant.SessionConstants;
import com.spm.constants.PagingConstants;
import com.spm.dto.MonthlyCardLogDto;
import com.spm.dto.ResultObject;
import com.spm.service.MonthlyCardLogService;

@Controller
@RequestMapping(path = "/monthlyCard")
public class MonthlyCardLogController {
	
	@Autowired
	private MonthlyCardLogService monthlyCardLogService;
	
	
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
		
		List<Integer> totalPages = new ArrayList<>();
		if (page <= 5) {
			for (int p = 1; p <= logs.getTotalPages(); p++) {
				totalPages.add(p);
				if (p > 10) {
					break;
				}
			}
		} else {
			for (int p = page - 5; p <= logs.getTotalPages(); p++) {
				totalPages.add(p);
				if (p > (page + 5)) {
					break;
				}
			}
		}
		
		model.addAttribute("logs",logs);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalRows", logs.getTotalRows());
		model.addAttribute("maxPage", logs.getTotalPages());
		model.addAttribute("currentPage", page + 1);
		
		return "monthlyCardViewLogPage";
	}
	
	
}

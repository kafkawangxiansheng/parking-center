package com.spm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spm.dto.ResultObject;
import com.spm.dto.RevenueDto;
import com.spm.service.RevenueService;

/**
 * Created by Vincent on 02/10/2018
 */
@Controller
@RequestMapping("/revenue")
public class RevenueController {

	@Autowired
	private RevenueService revenueService;
	
	
	
	
	@RequestMapping(value = "", method = {RequestMethod.GET})
	public String getRevenue(@RequestParam(name="projectId", required=false, defaultValue="1")int projectId,
			Model model,  HttpServletRequest request) {
		ResultObject<List<RevenueDto>> revenueMap = revenueService.getAllRevenue(projectId);
		model.addAttribute("revenues", revenueMap.getData());
		return "revenuePage";
	}
	
	
}

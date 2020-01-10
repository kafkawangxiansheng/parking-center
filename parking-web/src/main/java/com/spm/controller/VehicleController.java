package com.spm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spm.common.util.constant.SessionConstants;
import com.spm.dto.ResultObject;
import com.spm.dto.VehicleDto;
import com.spm.exception.UnauthorizedException;
import com.spm.search.form.VehicleSearchForm;
import com.spm.service.VehicleService;

@Controller
@RequestMapping(path="/cards")
public class VehicleController {
	
	@Autowired
	VehicleService vehicleService;
	
	@RequestMapping(value="vehicle", method = { RequestMethod.GET })
	public String index(Model model, HttpServletRequest request) throws UnauthorizedException {
		
		List<String> projects = (List<String>)request.getSession().getAttribute(SessionConstants.PROJECT_SESSION_NAME);
		VehicleSearchForm vehicleSearchForm = new VehicleSearchForm();
		vehicleSearchForm.setProjectId(projects.get(0));
		
		model.addAttribute("vehicleSearchForm", vehicleSearchForm);
		ResultObject<List<VehicleDto>> result = vehicleService.getAllVehicle(vehicleSearchForm);
		model.addAttribute("vehicles", result.getData());
		return "vehiclePage";
	}
	
	@RequestMapping(value="addNewVehicle", method = { RequestMethod.GET })
	public String showAddNewVehicle() {
		return "addNewVehiclePage";
	}

}

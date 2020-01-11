package com.spm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spm.common.util.constant.SessionConstants;
import com.spm.dto.ProjectsDto;
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
	public String showAddNewVehicle(Model model, HttpServletRequest request) {
		
		VehicleDto vehicle = new VehicleDto();
		List<String> projects = (List<String>)request.getSession().getAttribute(SessionConstants.PROJECT_SESSION_NAME);
		ProjectsDto project = new ProjectsDto();
		project.setId(Long.valueOf(projects.get(0)));
		vehicle.setProject(project);
		model.addAttribute("addVehicle", vehicle);
		return "addNewVehiclePage";
	}
	
	@RequestMapping(value="addNewVehicle", method = { RequestMethod.POST })
	public String addVehicle(Model model, @ModelAttribute("addVehicle") VehicleDto vehicleDto) throws UnauthorizedException {
		vehicleService.addVehicle(vehicleDto);
		return "redirect:/cards/vehicle";
	}
	
	@RequestMapping (value="editVehicle/{vehicleId}", method = { RequestMethod.GET})
	public String editVehicle(Model model, @PathVariable("vehicleId")Long vehicleId) throws UnauthorizedException{
		VehicleDto result = vehicleService.getVehicleById(vehicleId);
		model.addAttribute("editVehicle", result);
		return "editVehiclePage";
	}
	
	@RequestMapping(value = "deleteVehicle/{id}", method= {RequestMethod.GET})
	public  String deleteVehicle(Model model, @PathVariable("id") Long vehicleId) throws UnauthorizedException{
		vehicleService.deleteVehicle(vehicleId);
		return "redirect:/cards/vehicle";
	}

}

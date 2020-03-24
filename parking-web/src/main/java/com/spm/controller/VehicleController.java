package com.spm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spm.common.util.constant.SessionConstants;
import com.spm.dto.ResultObject;
import com.spm.dto.VehicleDto;
import com.spm.search.form.VehicleSearchForm;
import com.spm.service.VehicleService;

@Controller
@RequestMapping(path="/cards")
public class VehicleController {
	
	@Autowired
	VehicleService vehicleService;
	
	public String getProjectId(HttpServletRequest request) {
		List<String> projects = (List<String>)request.getSession().getAttribute(SessionConstants.PROJECT_SESSION_NAME);
		return projects.get(0);
	}
	
	@GetMapping(value="vehicle")
	public String index(Model model, HttpServletRequest request) {
		VehicleSearchForm vehicleSearchForm  = new VehicleSearchForm(); 
		
		String projectId = getProjectId(request);
		
		vehicleSearchForm.setProjectId(projectId);
		model.addAttribute("vehicleSearchForm", vehicleSearchForm);
		ResultObject<List<VehicleDto>> result = vehicleService.getAllVehicle(vehicleSearchForm);
		model.addAttribute("vehicles", result.getData());
		return "vehiclePage";
	}
	
	@GetMapping(value="addNewVehicle")
	public String showAddNewVehicle(Model model, HttpServletRequest request) {
		
		String projectId = getProjectId(request);
		
		VehicleDto vehicle = new VehicleDto();
		vehicle.setProjectId(Long.valueOf(projectId));
		model.addAttribute("addVehicle", vehicle);
		return "addNewVehicleForm";
	}
	
	@PostMapping(value="addNewVehicle")
	public String addVehicle(Model model, @ModelAttribute("addVehicle") VehicleDto vehicleDto) {
		
		
		boolean addSuccess = vehicleService.addVehicle(vehicleDto);
		if(addSuccess) {
			return "redirect:/cards/vehicle";
		}else {
			String error = "Loại Xe đã tồn tại, vui lòng nhập Loại Xe khác!";
			model.addAttribute("errorMessage",error );
			
			return "addNewVehicleForm";
		}
	}
	
	@GetMapping (value="editVehicle/{vehicleId}")
	public String editVehicle(Model model, @PathVariable("vehicleId")Long vehicleId) {
		VehicleDto result = vehicleService.getVehicleById(vehicleId);
		model.addAttribute("editVehicle", result);
		return "editVehicleForm";
	}
	
	@GetMapping(value = "deleteVehicle/{id}")
	public  String deleteVehicle(Model model, @PathVariable("id") Long vehicleId) {
		vehicleService.deleteVehicle(vehicleId);
		return "redirect:/cards/vehicle";
	}
	
	@PostMapping(value="updateVehicle")
	public String updateVehicle(Model model, @ModelAttribute("editVehicle") VehicleDto vehicleDto) {
		boolean addSuccess = vehicleService.updateVehicle(vehicleDto);
		if(addSuccess) {
			return "redirect:/cards/vehicle";
		}else {
			String error = "Loại Xe đã tồn tại, vui lòng nhập Loại Xe khác!";
			model.addAttribute("errorMessage",error );
			return "editVehicleForm";
		}
	}

}

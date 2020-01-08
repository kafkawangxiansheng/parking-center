package com.spm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path="/cards")
public class VehicleController {
	
	@RequestMapping(value="vehicle", method = { RequestMethod.GET })
	public String showVehiclePage() {
		return "vehiclePage";
	}
	
	@RequestMapping(value="addNewVehicle", method = { RequestMethod.GET })
	public String showAddNewVehicle() {
		return "addNewVehiclePage";
	}

}

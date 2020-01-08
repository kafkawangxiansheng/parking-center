package com.spm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/revenue")
public class RevenuePriceForMula3 {

	@RequestMapping(value="priceformula3")
	public String showRevenuePFM3(){
		return "revenuePriceForMula3";
	}
}

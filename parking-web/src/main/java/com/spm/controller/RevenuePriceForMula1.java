package com.spm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/revenue")
public class RevenuePriceForMula1 {

	@RequestMapping(value="priceformula1")
	public String showRevenuePFM1(){
		return "revenuePriceForMula1";
	}
}

package com.spm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/revenue")
public class RevenuePriceForMula2 {

	@RequestMapping(value="priceformula2")
	public String showRevenuePFM2(){
		return "revenuePriceForMula2";
	}
}

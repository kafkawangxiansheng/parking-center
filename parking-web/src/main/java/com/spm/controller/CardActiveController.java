package com.spm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path="/cards")
public class CardActiveController {
	
	@RequestMapping(value="card-active", method = { RequestMethod.GET })
	public String showCardActivePage() {
		return "cardActivePage";
	}

}

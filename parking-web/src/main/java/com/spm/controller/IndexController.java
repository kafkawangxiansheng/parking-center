package com.spm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spm.exception.CustomExceptionHandler;


@Controller
public class IndexController extends CustomExceptionHandler{
	
	private static final Logger logger = LogManager.getLogger(IndexController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest request, HttpServletResponse response) {
		
		return "redirect:/in-out-logs";
    
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
		logger.debug("Login");
        return "loginPage";
    }
	
	@RequestMapping(value = "/loginSuccess", method = RequestMethod.GET)
    public String loginPost(Model model, HttpServletRequest request, HttpServletResponse response) {
		
		return "redirect:/in-out-logs";
    
	}
 
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String destroySession(HttpServletRequest request) {
		
		request.getSession().invalidate();
		
		return "redirect:/login";
	}
}

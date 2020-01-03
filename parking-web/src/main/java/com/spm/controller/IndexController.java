package com.spm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spm.common.util.constant.SessionConstants;
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
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		List<GrantedAuthority> roles  = (List<GrantedAuthority>)authentication.getAuthorities();
		List<String> projectIds = new ArrayList<>();
		
		roles.forEach(role -> {
			if(role.getAuthority().startsWith("PROJECT_")) {
				String[] roleSplit  = role.getAuthority().split("_");
				projectIds.add(roleSplit[1]);
			}
		});
		request.getSession().setAttribute(SessionConstants.PROJECT_SESSION_NAME, projectIds);
		return "redirect:/in-out-logs";
    
	}
 
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String destroySession(HttpServletRequest request) {
		
		request.getSession().invalidate();
		
		return "redirect:/login";
	}
}

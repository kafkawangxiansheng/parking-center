package com.spm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spm.dto.CompanyDto;
import com.spm.dto.ProjectsDto;
import com.spm.dto.ResultObject;
import com.spm.exception.UnauthorizedException;
import com.spm.service.CompanyService;
import com.spm.service.ProjectService;

@Controller
@RequestMapping(path="/project")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private CompanyService companyService;
	
	@RequestMapping(value = "", method= {RequestMethod.GET})
	public String index(Model model,  HttpServletRequest request) throws UnauthorizedException {
		ResultObject<List<ProjectsDto>> projectsMap = projectService.getAllProjects();
		model.addAttribute("projects", projectsMap.getData());
		return "projectPage";
	}
	
	@RequestMapping(value = "/add-project", method= {RequestMethod.GET})
	public  String addProfile(Model model) throws UnauthorizedException{
		model.addAttribute("projectDto", new ProjectsDto());
		ResultObject<List<CompanyDto>> companyMap = companyService.getListCompanies();
		model.addAttribute("listCompanies", companyMap.getData());
		return "addProject";
	}
	
	@RequestMapping(value = "/edit-project/{id}", method= {RequestMethod.GET})
	public  String editProfile(Model model, @PathVariable("id")Long id) throws UnauthorizedException{
		ProjectsDto projecstDto = projectService.getProjectById(id);
		model.addAttribute("projectDto", projecstDto);
		ResultObject<List<CompanyDto>> companyMap = companyService.getListCompanies();
		model.addAttribute("listCompanies", companyMap.getData());
		return "editProject";
	}
	
	@RequestMapping(value = "/add-project", method= {RequestMethod.POST,RequestMethod.PUT})
	public  String doAddProfile(Model model, @Valid @ModelAttribute("projectDto") ProjectsDto projecstDto) throws UnauthorizedException{
		projectService.addProject(projecstDto);
		return "redirect:/project";
	}
	
	@RequestMapping(value = "/delete-project/{id}", method= {RequestMethod.GET})
	public  String deleteProfile(Model model, @PathVariable("id") Long id) throws UnauthorizedException{
		projectService.deleteProject(id);
		return "redirect:/project";
	}
	
}

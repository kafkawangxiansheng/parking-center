package com.spm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spm.dto.ProjectsDto;
import com.spm.dto.ResultObject;
import com.spm.exception.UnauthorizedException;
import com.spm.service.ProjectService;

@Controller
@RequestMapping(path="/project")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
//	@RequestMapping(value = "", method= {RequestMethod.GET})
//	public String index(
//			@RequestParam(name="page", required=false, defaultValue="0")int page,
//			Model model,  HttpServletRequest request) throws UnauthorizedException {
//			if(page > 0) {
//				page = page - 1;
//			}
//		Pageable pageable = PageRequest.of(page, PagingConstants.MAX_ROWS_CAN_DISPLAY);
//		ResultObject<List<ProjectsDto>> projectsMap = projectService.getAllProjects(pageable);
//		model.addAttribute("projects", projectsMap.getData());
//		return "revenuePage";
//	}
	
	@RequestMapping(value = "", method= {RequestMethod.GET})
	public String index(Model model,  HttpServletRequest request) throws UnauthorizedException {
		ResultObject<List<ProjectsDto>> projectsMap = projectService.getAllProjects();
		model.addAttribute("projects", projectsMap.getData());
		return "projectPage";
	}
	
	@RequestMapping(value = "projectId", method = {RequestMethod.GET})
	public String getProjectId(@RequestParam("projectId") Long projectId,Model model,  HttpServletRequest request) throws UnauthorizedException {
		ResultObject<List<ProjectsDto>> projectsMap = projectService.getProjectById(projectId);
		model.addAttribute("project", projectsMap.getData());
		return "editProjectPage";
	}
	
}

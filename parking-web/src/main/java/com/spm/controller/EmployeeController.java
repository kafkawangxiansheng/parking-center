package com.spm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spm.common.util.constant.SessionConstants;
import com.spm.dto.EmployeeDto;
import com.spm.dto.ProjectsDto;
import com.spm.dto.ResultObject;
import com.spm.service.EmployeeService;
import com.spm.service.ProjectService;

@Controller
@RequestMapping(path="/employees")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	ProjectService projectService;
	
	public String getProjectId(HttpServletRequest request) {
		List<String> projects = (List<String>)request.getSession().getAttribute(SessionConstants.PROJECT_SESSION_NAME);
		return projects.get(0);
	}
	
	@GetMapping(value = "")
	public String index(Model model,  HttpServletRequest request){
		ResultObject<List<EmployeeDto>> projectsMap = employeeService.getAllByProjectId(Long.parseLong(getProjectId(request)));
		model.addAttribute("listEmployee", projectsMap.getData());
		return "employeePage";
	}

	
	@GetMapping(value="addNewEmployee")
	public String showAddNewEmployeePage(Model model) {
		model.addAttribute("employeeDto", new EmployeeDto());
		ResultObject<List<ProjectsDto>> projectMap = projectService.getAllProjects();
		model.addAttribute("listProjectDto", projectMap.getData());	
		return "addEmployeeForm";
	}
	
	@PostMapping(value = "/addNewEmployee")
	public  String doAddNewEmployee(Model model, @Valid @ModelAttribute("employeeDto") EmployeeDto employeeDto) {
		boolean result = employeeService.addEmployee(employeeDto);
		if(result) {
			return "redirect:/employees";
		}else {
			String error = "Tên đăng nhập đã tồn tại!";
			model.addAttribute("errorMessage",error );
			ResultObject<List<ProjectsDto>> projectMap = projectService.getAllProjects();
			model.addAttribute("listProjectDto", projectMap.getData());	
			return "addEmployeeForm";
		}
		
	}
	
	@PutMapping(value = "/addNewEmployee")
	public  String doUpdateEmployee(Model model, @Valid @ModelAttribute("employeeDto") EmployeeDto employeeDto) {
		return  doAddNewEmployee(model, employeeDto);
		
	}
	
	@GetMapping(value = "/editEmployee/{id}")
	public  String editEmployee(Model model, @PathVariable("id")Long id) {
		EmployeeDto employeeDto = employeeService.getEmployeeById(id);
		model.addAttribute("employeeDto", employeeDto);
		ResultObject<List<ProjectsDto>> projectMap = projectService.getAllProjects();
		model.addAttribute("listProjectDto", projectMap.getData());	
		return "editEmployeeForm";
	}
	
	@PostMapping(value = "/update")
	public  String doUpate(Model model, @Valid @ModelAttribute("employeeDto") EmployeeDto employeeDto) {
		boolean result = employeeService.update(employeeDto);
		if(result) {
			return "redirect:/employees";
		}else {
			String error = "Tên đăng nhập đã tồn tại!";
			model.addAttribute("errorMessage",error );
			ResultObject<List<ProjectsDto>> projectMap = projectService.getAllProjects();
			model.addAttribute("listProjectDto", projectMap.getData());	
			return "editEmployeeForm";
		}
		
	}
	
	@GetMapping(value = "/delete/{id}")
	public  String deleteEmployee(Model model, @PathVariable("id") Long id){
		employeeService.deleteEmployee(id);
		return "redirect:/employees";
	}
}

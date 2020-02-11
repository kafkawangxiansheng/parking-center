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

import com.spm.common.util.constant.SessionConstants;
import com.spm.dto.EmployeeDto;
import com.spm.dto.ProjectsDto;
import com.spm.dto.ResultObject;
import com.spm.exception.UnauthorizedException;
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
	
//	@RequestMapping(value = "", method = { RequestMethod.GET })
//	public String index(
//			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
//			@RequestParam(name = "name", required = false) String name,
//			@RequestParam(name = "userName", required = false) String userName,
//			@RequestParam(name = "pass", required = false) String pass,
//			@RequestParam(name = "position", required = false) String position,
//			@RequestParam(name = "sex", required = false) String sex,
//			Model model, HttpServletRequest request) throws UnauthorizedException {
//
//		EmployeeSearchForm employeeSearchForm = new EmployeeSearchForm();
//		employeeSearchForm.setName(userName);
//		employeeSearchForm.setPosition(position);
//		
//		model.addAttribute("employeeSearchForm", employeeSearchForm);
//		
//		if(page > 0) {
//			page = page - 1;
//		}
//		Pageable pageable = PageRequest.of(page, PagingConstants.ROWS_PER_PAGE);
//		
//		ResultObject<List<EmployeeDto>> result = employeeService.getAllEmployee(employeeSearchForm, pageable);
//		
//		List<Integer>  totalPages = new ArrayList<Integer>();
//		if( page <= 5) {
//			for(int p = 1; p <= result.getTotalPages(); p ++) {
//				totalPages.add(p);
//				if(p  > 10) {
//					break;
//				}
//			}
//		} else {
//			for(int p = page  -  5; p <= result.getTotalPages(); p ++) {
//				totalPages.add(p);
//				if(p  > (page + 5)) {
//					break;
//				}
//			}
//		}
//		
//		model.addAttribute("employees", result.getData());
//		model.addAttribute("totalPages", totalPages);
//		model.addAttribute("maxPage", result.getTotalPages());
//		model.addAttribute("currentPage", page+1);
//		
//		return "employeePage";
//	}
	
	@RequestMapping(value = "", method= {RequestMethod.GET})
	public String index(Model model,  HttpServletRequest request) throws UnauthorizedException {
		ResultObject<List<EmployeeDto>> projectsMap = employeeService.getAllByProjectId(Long.parseLong(getProjectId(request)));
		model.addAttribute("listEmployee", projectsMap.getData());
		return "employeePage";
	}

	
	@RequestMapping(value="addNewEmployee", method = { RequestMethod.GET})
	public String showAddNewEmployeePage(Model model) {
		model.addAttribute("employeeDto", new EmployeeDto());
		ResultObject<List<ProjectsDto>> projectMap = projectService.getAllProjects();
		model.addAttribute("listProjectDto", projectMap.getData());	
		return "addEmployeeForm";
	}
	
	@RequestMapping(value = "/addNewEmployee", method= {RequestMethod.POST,RequestMethod.PUT})
	public  String doAddNewEmployee(Model model, @Valid @ModelAttribute("employeeDto") EmployeeDto employeeDto) throws UnauthorizedException{
		employeeService.addEmployee(employeeDto);
		return "redirect:/employees";
	}
	
	@RequestMapping(value = "/editEmployee/{id}", method= {RequestMethod.GET})
	public  String editEmployee(Model model, @PathVariable("id")Long id) throws UnauthorizedException{
		EmployeeDto employeeDto = employeeService.getEmployeeById(id);
		model.addAttribute("employeeDto", employeeDto);
		ResultObject<List<ProjectsDto>> projectMap = projectService.getAllProjects();
		model.addAttribute("listProjectDto", projectMap.getData());	
		return "editEmployeeForm";
	}
	
	@RequestMapping(value = "/delete/{id}", method= {RequestMethod.GET})
	public  String deleteEmployee(Model model, @PathVariable("id") Long id) throws UnauthorizedException{
		employeeService.deleteEmployee(id);
		return "redirect:/employees";
	}
}

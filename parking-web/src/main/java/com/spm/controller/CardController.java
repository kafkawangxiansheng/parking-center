package com.spm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spm.common.util.constant.SessionConstants;
import com.spm.constants.PagingConstants;
import com.spm.dto.CardsDto;
import com.spm.dto.ResultObject;
import com.spm.dto.VehicleDto;
import com.spm.exception.UnauthorizedException;
import com.spm.search.form.CardSearchForm;
import com.spm.search.form.VehicleSearchForm;
import com.spm.service.CardService;
import com.spm.service.VehicleService;

@Controller
@RequestMapping(path = "/cards")
public class CardController {

	@Autowired
	CardService cardService;
	
	@Autowired
	VehicleService vehicleService;
	
	public String getProjectId(HttpServletRequest request) {
		List<String> projects = (List<String>)request.getSession().getAttribute(SessionConstants.PROJECT_SESSION_NAME);
		return projects.get(0);
	}

	@RequestMapping(value = "", method = { RequestMethod.GET })
	public String index(
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "code", required = false) String code,
			@RequestParam(name = "stt", required = false) String stt,
			@RequestParam(name = "vehicleId", required = false) String vehicleId,
			Model model, HttpServletRequest request) throws UnauthorizedException {

		
		CardSearchForm cardSearchForm = new CardSearchForm();
		cardSearchForm.setCode(code);
		cardSearchForm.setStt(stt);
		cardSearchForm.setVehicleId(vehicleId);
		cardSearchForm.setProjectId(Long.parseLong(getProjectId(request)));
		model.addAttribute("cardSearchForm", cardSearchForm);
		
		if(page > 0) {
			page = page - 1;
		}
		Pageable pageable = PageRequest.of(page, PagingConstants.ROWS_PER_PAGE);
		
		ResultObject<List<CardsDto>> result = cardService.getAllCard(cardSearchForm, pageable);
		
		List<Integer>  totalPages = new ArrayList<Integer>();
		if( page <= 5) {
			for(int p = 1; p <= result.getTotalPages(); p ++) {
				totalPages.add(p);
				if(p  > 10) {
					break;
				}
			}
		} else {
			for(int p = page  -  5; p <= result.getTotalPages(); p ++) {
				totalPages.add(p);
				if(p  > (page + 5)) {
					break;
				}
			}
		}
		
		VehicleSearchForm vehicleSearchForm = new VehicleSearchForm();
		vehicleSearchForm.setProjectId(getProjectId(request));
		
		ResultObject<List<VehicleDto>> vehicles = vehicleService.getAllVehicle(vehicleSearchForm);
		model.addAttribute("cards", result.getData());
		model.addAttribute("vehicles", vehicles.getData());
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("maxPage", result.getTotalPages());
		model.addAttribute("currentPage", page+1);
		
		return "cardPage";
	}
	
	@RequestMapping (value="addNewCard", method = { RequestMethod.GET})
	public String showAddNewCardPage(Model model, HttpServletRequest request)throws UnauthorizedException{
		
		VehicleSearchForm vehicleSearchForm = new VehicleSearchForm();
		vehicleSearchForm.setProjectId(getProjectId(request));
		ResultObject<List<VehicleDto>> vehicles = vehicleService.getAllVehicle(vehicleSearchForm);
		
		model.addAttribute("addCard", new CardsDto());
		model.addAttribute("vehicles", vehicles.getData());
		return "addNewCardPage";
	}
	
	@RequestMapping (value="addNewCard", method= {RequestMethod.POST})
	public String addCard(Model model,HttpServletRequest request, @ModelAttribute("addCard") CardsDto cardsDto) throws UnauthorizedException{
		cardsDto.setProjectId(Long.parseLong(getProjectId(request)));
		cardService.addCard(cardsDto);
		return "redirect:/cards";
	}
	
	@RequestMapping (value="editCard/{cardId}", method = { RequestMethod.GET})
	public String editCard(Model model, HttpServletRequest request, @PathVariable("cardId")Long cardId) throws UnauthorizedException{
		List<String> projects = (List<String>)request.getSession().getAttribute(SessionConstants.PROJECT_SESSION_NAME);
		VehicleSearchForm vehicleSearchForm = new VehicleSearchForm();
		vehicleSearchForm.setProjectId(projects.get(0));
		ResultObject<List<VehicleDto>> vehicles = vehicleService.getAllVehicle(vehicleSearchForm);
		CardsDto result = cardService.getCardById(cardId);
		
		model.addAttribute("editCard", result);
		model.addAttribute("vehicles", vehicles.getData());
		return "editCardPage";
	}
	
	@RequestMapping(value="active-card", method = { RequestMethod.GET})
	public String getAllDisabledCard(
			@RequestParam(name = "disable", required = false, defaultValue = "1") String disable,
			@RequestParam(name = "code", required = false) String code,
			Model model, HttpServletRequest request) throws UnauthorizedException {
		CardSearchForm cardSearchForm = new CardSearchForm();
		cardSearchForm.setDisable(disable);
		cardSearchForm.setCode(code);
		model.addAttribute("cardSearchForm", cardSearchForm);
		ResultObject<List<CardsDto>> result = cardService.getAllDisabledCard(cardSearchForm);
		model.addAttribute("disabledCards", result.getData());
		return "activeCardPage";
	}
	
	@RequestMapping(value="/activeCard/{id}", method= {RequestMethod.GET})
	public String activeCard(Model model, @PathVariable("id") int cardId) throws UnauthorizedException{
		cardService.activeCard(cardId);
		return "redirect:/cards/active-card";
	}
	
	@RequestMapping(value = "/delete/{id}", method= {RequestMethod.GET})
	public  String deleteMonthlyCard(Model model, @PathVariable("id") Long id) throws UnauthorizedException{
		cardService.deleteCard(id);
		return "redirect:/cards";
	}
	
}

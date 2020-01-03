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

import com.spm.constants.PagingConstants;
import com.spm.dto.CardsDto;
import com.spm.dto.ResultObject;
import com.spm.exception.UnauthorizedException;
import com.spm.search.form.CardSearchForm;
import com.spm.service.CardService;

@Controller
@RequestMapping(path = "/cards")
public class CardController {

	@Autowired
	CardService cardService;

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
		
		model.addAttribute("cards", result.getData());
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("maxPage", result.getTotalPages());
		model.addAttribute("currentPage", page+1);
		
		return "cardPage";
	}
	
	@RequestMapping (value="addNewCard", method = { RequestMethod.GET})
	public String showAddNewCardPage(Model model)throws UnauthorizedException{
		model.addAttribute("addCard", new CardsDto());
		return "addNewCardPage";
	}
	
	@RequestMapping (value="addNewCard", method= {RequestMethod.POST})
	public String addCard(Model model, @ModelAttribute("addCard") CardsDto cardsDto) throws UnauthorizedException{
		cardService.addCard(cardsDto);
		return "redirect:/cards";
	}
	
	@RequestMapping (value="editCard/{cardId}", method = { RequestMethod.GET})
	public String editCard(Model model, @PathVariable("cardId")Long cardId) throws UnauthorizedException{
		CardsDto result = cardService.getCardById(cardId);
		model.addAttribute("editCard", result);
		return "cardEditPage";
	}
	
}

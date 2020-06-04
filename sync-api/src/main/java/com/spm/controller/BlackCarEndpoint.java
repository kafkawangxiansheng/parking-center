package com.spm.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spm.dtos.BlackCarDto;
import com.spm.service.BlackCarService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by Vincent on 02/10/2018
 */
@RestController
@RequestMapping("/back-car")
@Api(value = "black car Endpoint")
public class BlackCarEndpoint {

	@Autowired
	private BlackCarService blackCarService;

	@PostMapping(value = "/")
	@ApiOperation("Add new black car")
	public void addMember(@RequestBody BlackCarDto blackCarDto) {
		blackCarService.save(blackCarDto);
	}

	@PutMapping(value = "/")
	@ApiOperation("Update existing black car")
	public void updateExisting(@RequestBody BlackCarDto blackCarDto) {
		blackCarService.save(blackCarDto);
	}

	@GetMapping(value = "")
	@ApiOperation("get black car")
	public @ResponseBody List<BlackCarDto> getAll() {
		return blackCarService.findAll();
	}
	
	@GetMapping(value="/get-by-id")
	@ApiOperation("Get black car by id")
	public @ResponseBody BlackCarDto getById(@RequestParam(name="id") Long id) {
		return blackCarService.getById(id);
	}
	
	@GetMapping(value="/get-digit")
	@ApiOperation("Get black car digit")
	public @ResponseBody BlackCarDto getByDigit(@RequestParam(name="digit") String digit) {
		return blackCarService.findByDigit(digit);
	}
	
	@PostMapping(value = "/batchinsert")
	@ApiOperation("Add list of black car")
	public void batchinsert(@RequestBody List<BlackCarDto> blackCarDtos) {
		blackCarDtos.forEach(blackCarDto -> {
			BlackCarDto existingBlackCard  = blackCarService.findByDigit(blackCarDto.getDigit());
			if(existingBlackCard != null) {
				blackCarDto.setId(existingBlackCard.getId());
			}
			blackCarDto.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
		});
		blackCarService.save(blackCarDtos);
	}
	
	@GetMapping(value = "/batchsyncs")
	@ApiOperation("sync all black car")
	public @ResponseBody List<BlackCarDto> batchsyncs() {
		return  blackCarService.syncAll();
	}

}

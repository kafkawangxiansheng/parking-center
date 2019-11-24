package net.spm.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.spm.dto.MotoMonthlyRevenueDto;
import net.spm.repository.inhouse.parking.center.MotoMonthlyRevenueRepository;
import net.spm.service.RevenueService;

/**
 * Created by Vincent on 02/10/2018
 */

@Service
public class RevenueServiceImpl implements RevenueService {

	@Autowired
	private MotoMonthlyRevenueRepository motoMonthlyRevenueRepository;

	ModelMapper mapper;

	
	@PostConstruct
	public void initialize() {
		mapper = new ModelMapper();
	}


	@Override
	public Map<String, Object> getRevenues() {
		Map<String, Object> revenues = new HashMap<>();
		
		MotoMonthlyRevenueDto revenueDto = new MotoMonthlyRevenueDto();
		mapper.map(motoMonthlyRevenueRepository.findAll().get(0), revenueDto);
		revenues.put("motoMonthly", revenueDto);
		revenues.put("motoNormal", new MotoMonthlyRevenueDto());
		revenues.put("otoMonthly", new MotoMonthlyRevenueDto());
		revenues.put("otoNormal", new MotoMonthlyRevenueDto());
		return revenues;
	}

	

}

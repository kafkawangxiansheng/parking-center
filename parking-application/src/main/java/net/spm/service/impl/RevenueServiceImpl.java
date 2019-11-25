package net.spm.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.spm.common.util.constant.VehicleConstants;
import net.spm.dto.RevenueDto;
import net.spm.jpa.entity.RevenueEntity;
import net.spm.repository.RevenueRepository;
import net.spm.service.RevenueService;

/**
 * Created by Vincent on 02/10/2018
 */

@Service
public class RevenueServiceImpl implements RevenueService {

	@Autowired
	private RevenueRepository motoMonthlyRevenueRepository;

	ModelMapper mapper;

	
	@PostConstruct
	public void initialize() {
		mapper = new ModelMapper();
	}


	@Override
	public Map<String, Object> getRevenues() {
		Map<String, Object> revenues = new HashMap<>();
		
		RevenueDto revenueDto = new RevenueDto();
		RevenueEntity revenueEntity = motoMonthlyRevenueRepository.findByVehicleId(VehicleConstants.MOTO_MONTHLY_TYPE);
		if(revenueEntity != null) {
			mapper.map(revenueEntity, revenueDto);
		}
		revenues.put(VehicleConstants.MOTO_MONTHLY_KEY, revenueDto);
		
		revenueDto = new RevenueDto();
		revenueEntity = motoMonthlyRevenueRepository.findByVehicleId(VehicleConstants.MOTO_NORMAL_TYPE);
		if(revenueEntity != null) {
			mapper.map(revenueEntity, revenueDto);
		}
		revenues.put(VehicleConstants.MOTO_NORMAL_KEY, revenueDto);
		
		revenueDto = new RevenueDto();
		revenueEntity = motoMonthlyRevenueRepository.findByVehicleId(VehicleConstants.OTO_MONTHLY_TYPE);
		if(revenueEntity != null) {
			mapper.map(revenueEntity, revenueDto);
		}
		revenues.put(VehicleConstants.OTO_MONTHLY_KEY, revenueDto);
		
		revenueDto = new RevenueDto();
		revenueEntity = motoMonthlyRevenueRepository.findByVehicleId(VehicleConstants.OTO_NORMAL_TYPE);
		if(revenueEntity != null) {
			mapper.map(revenueEntity, revenueDto);
		}
		revenues.put(VehicleConstants.OTO_NORMAL_KEY, revenueDto);
		return revenues;
	}

	

}

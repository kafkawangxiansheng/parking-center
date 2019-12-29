package com.spm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spm.dto.RevenueDto;
import com.spm.entity.RevenueEntity;
import com.spm.entity.SettingEntity;
import com.spm.repository.RevenueRepository;
import com.spm.repository.SettingRepository;
import com.spm.service.RevenueService;

/**
 * Created by Vincent on 02/10/2018
 */

@Service
public class RevenueServiceImpl implements RevenueService {

	@Autowired
	private RevenueRepository motoMonthlyRevenueRepository;
	
	@Autowired
	private SettingRepository  settingRepository;

	ModelMapper mapper;

	
	@PostConstruct
	public void initialize() {
		mapper = new ModelMapper();
	}


	@Override
	public List<RevenueDto> getRevenues(int projectId) {
		
		List<RevenueDto> revenues = new ArrayList<>();
		List<SettingEntity> settings = settingRepository.findAllByProjectId(projectId);
		for(SettingEntity setting :  settings) {
			RevenueDto revenueDto = new RevenueDto();
			RevenueEntity revenueEntity = motoMonthlyRevenueRepository.findByVehicleId(setting.getVehicleId());
			if(revenueEntity != null) {
				mapper.map(revenueEntity, revenueDto);
			}
			revenueDto.setLabel(setting.getName());
			revenues.add(revenueDto);
			
		}
		
		return revenues;
	}

	

}

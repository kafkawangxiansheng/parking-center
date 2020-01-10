package com.spm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.util.VersionFSHierarchyMaker;
import com.spm.dto.RevenueDto;
import com.spm.entity.OrderEntity;
import com.spm.entity.RevenueEntity;
import com.spm.entity.SettingEntity;
import com.spm.entity.VehicleEntity;
import com.spm.repository.OrderRepository;
import com.spm.repository.SettingRepository;
import com.spm.repository.VehicleRepository;
import com.spm.search.form.RevenueSearchForm;
import com.spm.service.RevenueService;

/**
 * Created by Vincent on 02/10/2018
 */

@Service
public class RevenueServiceImpl implements RevenueService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private VehicleRepository  vehicleRepository;

	ModelMapper mapper;

	
	@PostConstruct
	public void initialize() {
		mapper = new ModelMapper();
	}


	@Override
	public List<RevenueDto> getRevenues(RevenueSearchForm revenueSearchForm) {
		
		List<RevenueDto> revenues = new ArrayList<>();
		List<VehicleEntity> vehicles = vehicleRepository.findAllByProjectId(revenueSearchForm.getProjectId());
		List<OrderEntity> orderEntities  = orderRepository.findAll(String.valueOf(revenueSearchForm.getProjectId()), revenueSearchForm.getEmployeeId(), revenueSearchForm.getDateFrom(), revenueSearchForm.getDateTo());
		
		for(VehicleEntity vehicle :  vehicles) {
			RevenueDto revenueDto = new RevenueDto();
			RevenueEntity revenueEntity = new RevenueEntity() ;
			revenueEntity.setVehicleId(vehicle.getCardType());
			orderEntities.forEach(order -> {
				if(order.getVehicleId() == vehicle.getCardType())  {
					revenueEntity.setTotalCheckin(((order.getCheckinTime()!=null && order.getCheckinTime() > 0)? 1 : 0) + revenueEntity.getTotalCheckin());
					revenueEntity.setTotalCheckout(((order.getCheckoutTime()!=null && order.getCheckoutTime() > 0)? 1 : 0) + revenueEntity.getTotalCheckout());
					revenueEntity.setTotalPrice(revenueEntity.getTotalPrice() + order.getTotalPrice());
				}
			});
			if(revenueEntity != null) {
				mapper.map(revenueEntity, revenueDto);
			}
			revenueDto.setLabel(vehicle.getName());
			revenues.add(revenueDto);
			
		}
		
		return revenues;
	}

	

}

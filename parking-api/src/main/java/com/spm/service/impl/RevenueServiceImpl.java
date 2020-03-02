package com.spm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spm.dto.RevenueDto;
import com.spm.entity.OrderEntity;
import com.spm.entity.RevenueEntity;
import com.spm.entity.VehicleEntity;
import com.spm.repository.OrderRepository;
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
		
		List<RevenueDto> normalRevenues = new ArrayList<>();
		List<RevenueDto> monthRevenues = new ArrayList<>();
		List<RevenueDto> totalRevenues = new ArrayList<>();
		
		List<VehicleEntity> vehicles = vehicleRepository.findAllByProjectId(revenueSearchForm.getProjectId());
		List<OrderEntity> orderEntitiesOut  = orderRepository.findAllVehicleOut(String.valueOf(revenueSearchForm.getProjectId()), revenueSearchForm.getEmployeeId(), revenueSearchForm.getDateFrom(), revenueSearchForm.getDateTo());
		List<OrderEntity> orderEntitiesIn  = orderRepository.findAllVehicleIn(String.valueOf(revenueSearchForm.getProjectId()), revenueSearchForm.getEmployeeId(), revenueSearchForm.getDateFrom(), revenueSearchForm.getDateTo());
		
		List<OrderEntity> orderEntitiesOutAll  = orderRepository.findAllVehicleOut(String.valueOf(revenueSearchForm.getProjectId()), revenueSearchForm.getEmployeeId(), null, null);
		List<OrderEntity> orderEntitiesInAll  = orderRepository.findAllVehicleIn(String.valueOf(revenueSearchForm.getProjectId()), revenueSearchForm.getEmployeeId(), null, null);
		
		RevenueDto normalRevenueTotalDto = new RevenueDto();
		normalRevenueTotalDto.setLabel("___ Tổng cộng xe thường");
		normalRevenueTotalDto.setCssClass("revenue-sub-total");
		RevenueDto monthlyRevenueTotalDto = new RevenueDto();
		monthlyRevenueTotalDto.setLabel("___ Tổng cộng xe tháng");
		monthlyRevenueTotalDto.setCssClass("revenue-sub-total");
		RevenueDto revenueTotalDto = new RevenueDto();
		revenueTotalDto.setLabel("Tổng cộng:");
		revenueTotalDto.setCssClass("revenue-total");
		
		for(VehicleEntity vehicle :  vehicles) {
			RevenueDto revenueDto = new RevenueDto();
			RevenueEntity revenueEntity = new RevenueEntity() ;
			revenueEntity.setVehicleId(vehicle.getType());
			orderEntitiesOut.forEach(order -> {
				if(order.getVehicleId() == vehicle.getType())  {
					revenueEntity.setTotalCheckout(((order.getCheckoutTime()!=null && order.getCheckoutTime() > 0)? 1 : 0) + revenueEntity.getTotalCheckout());
					revenueEntity.setTotalPrice(revenueEntity.getTotalPrice() + order.getTotalPrice());
				}
			});
			
			orderEntitiesIn.forEach(order -> {
				if(order.getVehicleId() == vehicle.getType())  {
					revenueEntity.setTotalCheckin(((order.getCheckinTime()!=null && order.getCheckinTime() > 0)? 1 : 0) + revenueEntity.getTotalCheckin());
				}
			});
			
			
			if(revenueEntity != null) {
				mapper.map(revenueEntity, revenueDto);
			}
			revenueDto.setLabel(vehicle.getName());
			revenueDto.setExistingVerhicle(orderRepository.findAllVehicleExistingInParking(String.valueOf(revenueSearchForm.getProjectId()), vehicle.getType()).size());
			if(vehicle.getCardType() == 1)  {
				normalRevenues.add(revenueDto);
				normalRevenueTotalDto.setTotalCheckin(normalRevenueTotalDto.getTotalCheckin() + revenueDto.getTotalCheckin());
				normalRevenueTotalDto.setTotalCheckout(normalRevenueTotalDto.getTotalCheckout() + revenueDto.getTotalCheckout());
				normalRevenueTotalDto.setExistingVerhicle(normalRevenueTotalDto.getExistingVerhicle() + revenueDto.getExistingVerhicle());
				normalRevenueTotalDto.setTotalPrice(normalRevenueTotalDto.getTotalPrice()+revenueDto.getTotalPrice());
			} else {
				monthRevenues.add(revenueDto);
				monthlyRevenueTotalDto.setTotalCheckin(monthlyRevenueTotalDto.getTotalCheckin()+revenueDto.getTotalCheckin());
				monthlyRevenueTotalDto.setTotalCheckout(monthlyRevenueTotalDto.getTotalCheckout()+revenueDto.getTotalCheckout());
				monthlyRevenueTotalDto.setExistingVerhicle(monthlyRevenueTotalDto.getExistingVerhicle() + revenueDto.getExistingVerhicle());
				monthlyRevenueTotalDto.setTotalPrice(monthlyRevenueTotalDto.getTotalPrice()+revenueDto.getTotalPrice());
			}
			
			
		}
		for(RevenueDto normalReveneu : normalRevenues) {
			totalRevenues.add(normalReveneu);
		}
		totalRevenues.add(normalRevenueTotalDto);
		for(RevenueDto monthlyReveneu : monthRevenues) {
			totalRevenues.add(monthlyReveneu);
		}
		totalRevenues.add(monthlyRevenueTotalDto);
		
		revenueTotalDto.setTotalCheckin(normalRevenueTotalDto.getTotalCheckin()  + monthlyRevenueTotalDto.getTotalCheckin());
		revenueTotalDto.setTotalCheckout(normalRevenueTotalDto.getTotalCheckout()  + monthlyRevenueTotalDto.getTotalCheckout());
		revenueTotalDto.setExistingVerhicle(normalRevenueTotalDto.getExistingVerhicle()  + monthlyRevenueTotalDto.getExistingVerhicle());
		revenueTotalDto.setTotalPrice(normalRevenueTotalDto.getTotalPrice()  + monthlyRevenueTotalDto.getTotalPrice());
		
		totalRevenues.add(revenueTotalDto);
		
		return totalRevenues;
	}

	

}

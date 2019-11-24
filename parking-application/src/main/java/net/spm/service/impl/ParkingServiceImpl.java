package net.spm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.spm.dto.OrdersDto;
import net.spm.jpa.entity.OrdersEntity;
import net.spm.repository.inhouse.parking.center.ParkingRepository;
import net.spm.service.ParkingService;

/**
 * Created by Vincent on 02/10/2018
 */

@Service
public class ParkingServiceImpl implements ParkingService {

	@Autowired
	private ParkingRepository parkingRepository;

	ModelMapper mapper;

	private List<OrdersDto> map(List<OrdersEntity> source) {

		ArrayList<OrdersDto> rtn = new ArrayList<>();
		source.stream().map((entity) -> {
			OrdersDto dto = new OrdersDto();
			mapper.map(entity, dto);
			return dto;
		}).forEachOrdered((dto) -> {
			rtn.add(dto);
		});
		return rtn;
	}
	
	private List<OrdersEntity> reMap(List<OrdersDto> source) {

		ArrayList<OrdersEntity> rtn = new ArrayList<>();
		source.stream().map((dto) -> {
			OrdersEntity entity = new OrdersEntity();
			mapper.map(dto, entity);
			return entity;
		}).forEachOrdered((entity) -> {
			rtn.add(entity);
		});
		return rtn;
	}

	@PostConstruct
	public void initialize() {
		mapper = new ModelMapper();
	}

	@Override
	public OrdersDto save(OrdersDto ordersDto) {
		OrdersEntity entity = new OrdersEntity();
		mapper.map(ordersDto, entity);
		entity = parkingRepository.save(entity);
		mapper.map(entity, ordersDto);
		return ordersDto;
	}
	
	@Override
	public List<OrdersDto> save(List<OrdersDto> ordersDtos) {
		List<OrdersEntity> ordersEntities = reMap(ordersDtos);
		
		ordersEntities = parkingRepository.save(ordersEntities);
		ordersDtos = map(ordersEntities);
		return ordersDtos;
	}

	@Override
	public void delete(OrdersDto parkingDto) {
		OrdersEntity entity = new OrdersEntity();
		mapper.map(parkingDto, entity);
		parkingRepository.delete(entity);

	}

	@Override
	public OrdersDto findByOrderId(Long orderId) {
		OrdersEntity entities = parkingRepository.findByOrderId(orderId);
		if (entities == null) {
			return null;
		}
		OrdersDto dto = new OrdersDto();
		mapper.map(entities, dto);
		return dto;
	}

	@Override
	public List<OrdersDto> findAll() {
		List<OrdersEntity> entities = parkingRepository.findAll();

		return this.map(entities);
	}

}

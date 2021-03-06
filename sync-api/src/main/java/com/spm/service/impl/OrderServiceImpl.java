package com.spm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spm.dtos.OrderDto;
import com.spm.entity.OrderEntity;
import com.spm.repository.OrderRepository;
import com.spm.service.OrderService;

/**
 * Created by Vincent on 02/10/2018
 */

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository parkingRepository;

	ModelMapper mapper;

	private List<OrderDto> map(List<OrderEntity> source) {

		ArrayList<OrderDto> rtn = new ArrayList<>();
		source.stream().map((entity) -> {
			OrderDto dto = new OrderDto();
			mapper.map(entity, dto);
			return dto;
		}).forEachOrdered((dto) -> {
			rtn.add(dto);
		});
		return rtn;
	}
	
	private List<OrderEntity> reMap(List<OrderDto> source) {

		ArrayList<OrderEntity> rtn = new ArrayList<>();
		source.stream().map((dto) -> {
			OrderEntity entity = new OrderEntity();
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
	public OrderDto save(OrderDto ordersDto) {
		OrderEntity entity = new OrderEntity();
		mapper.map(ordersDto, entity);
		entity = parkingRepository.save(entity);
		mapper.map(entity, ordersDto);
		return ordersDto;
	}
	
	@Override
	public List<OrderDto> save(List<OrderDto> ordersDtos) {
		List<OrderEntity> ordersEntities = reMap(ordersDtos);
		
		ordersEntities = parkingRepository.saveAll(ordersEntities);
		ordersDtos = map(ordersEntities);
		return ordersDtos;
	}

	@Override
	public void delete(OrderDto parkingDto) {
		OrderEntity entity = new OrderEntity();
		mapper.map(parkingDto, entity);
		parkingRepository.delete(entity);

	}

	@Override
	public OrderDto findByOrderIdAndProjectId(Long orderId, Long projectId) {
		OrderEntity entities = parkingRepository.findByOrderIdAndProjectId(orderId, projectId);
		if (entities == null) {
			return null;
		}
		OrderDto dto = new OrderDto();
		mapper.map(entities, dto);
		return dto;
	}

	@Override
	public List<OrderDto> findAll() {
		List<OrderEntity> entities = parkingRepository.findAll();
		return this.map(entities);
	}

}

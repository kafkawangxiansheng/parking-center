package com.spm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spm.dto.OrderDto;
import com.spm.dto.ResultObject;
import com.spm.entity.OrderEntity;
import com.spm.repository.OrderRepository;
import com.spm.search.form.OrderSearchForm;
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
	public OrderDto findByOrderId(Long orderId) {
		OrderEntity entities = parkingRepository.findByOrderId(orderId);
		if (entities == null) {
			return null;
		}
		OrderDto dto = new OrderDto();
		mapper.map(entities, dto);
		return dto;
	}

	@Override
	public ResultObject<List<OrderDto>> findAll(Pageable pageable, OrderSearchForm orderSearchForm) {
		Page<OrderEntity> entities = parkingRepository.search(orderSearchForm.getCardCode(), orderSearchForm.getCardStt(), 
				orderSearchForm.getCarNumber(), orderSearchForm.getDateFrom(), orderSearchForm.getDateTo(), pageable);
		ResultObject<List<OrderDto>> resultObject = new ResultObject<>();
		resultObject.setData(this.map(entities.getContent()));
		resultObject.setTotalPages(entities.getTotalPages());
		return resultObject;
	}

}

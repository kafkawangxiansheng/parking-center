package net.spm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.spm.jpa.entity.CustomerBalanceEntity;
import net.spm.repository.inhouse.parking.center.CustomerBalanceRepository;
import net.spm.service.CustomerBalanceService;

/**
 * Created by Vincent on 02/10/2018
 */
@Service
public class CustomerBalancerServiceImpl implements CustomerBalanceService {

	@Autowired
	CustomerBalanceRepository repository;

	@Override
	public CustomerBalanceEntity save(CustomerBalanceEntity customerBalance) {
		return repository.save(customerBalance);
	}

	@Override
	public CustomerBalanceEntity findById(long id) {
		return repository.findOne(id);
	}
	
	
	
}

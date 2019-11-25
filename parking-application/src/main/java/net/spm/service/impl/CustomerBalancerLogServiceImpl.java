package net.spm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.spm.jpa.entity.CustomerBalanceLogEntity;
import net.spm.repository.CustomerBalanceLogRepository;
import net.spm.service.CustomerBalanceLogService;

/**
 * Created by Vincent on 02/10/2018
 */
@Service
public class CustomerBalancerLogServiceImpl implements CustomerBalanceLogService {

	@Autowired
	CustomerBalanceLogRepository repository;
	
	@Override
	public CustomerBalanceLogEntity save(CustomerBalanceLogEntity customerBalanceLog) {
		return repository.save(customerBalanceLog);
	}

	@Override
	public CustomerBalanceLogEntity findByCustomerId(int cusId) {
		return repository.findByCustomerId(cusId);
	}

	
}

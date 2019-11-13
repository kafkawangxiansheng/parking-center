package net.spm.service;

import net.spm.jpa.entity.CustomerBalanceLogEntity;

/**
 * Created by Vincent 23/05/2018
 */
public interface CustomerBalanceLogService {

    CustomerBalanceLogEntity save(CustomerBalanceLogEntity customerBalanceLog);

    CustomerBalanceLogEntity findByCustomerId(int cusId);

}
 
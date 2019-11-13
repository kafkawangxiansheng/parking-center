package net.spm.service;

import net.spm.jpa.entity.CustomerBalanceEntity;

/**
 * Created by Vincent 23/05/2018
 */
public interface CustomerBalanceService {

    CustomerBalanceEntity save(CustomerBalanceEntity customerBalance);

    CustomerBalanceEntity findById(long id);

}
 
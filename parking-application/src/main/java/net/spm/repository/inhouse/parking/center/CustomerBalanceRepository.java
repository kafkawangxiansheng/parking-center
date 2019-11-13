package net.spm.repository.inhouse.parking.center;

import org.springframework.data.jpa.repository.JpaRepository;

import net.spm.jpa.entity.CustomerBalanceEntity;

/**
 * Created by Vincent 02/10/2018
 */

public interface CustomerBalanceRepository  extends JpaRepository<CustomerBalanceEntity, Long> {
	

}

package net.spm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.spm.jpa.entity.CustomerBalanceLogEntity;

/**
 * Created by Vincent 02/10/2018
 */

public interface CustomerBalanceLogRepository  extends JpaRepository<CustomerBalanceLogEntity, Long> {
	
	
	@Query(value="select * from customer_balance_log where cus_id = :cusId order by id DESC limit 0,1", nativeQuery=true)
	CustomerBalanceLogEntity findByCustomerId(@Param(value="cusId") int cusId);

}

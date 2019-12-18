package net.spm.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import net.spm.jpa.entity.OrdersEntity;

/**
 * Created by Vincent 02/10/2018
 */

public interface ParkingRepository  extends JpaRepository<OrdersEntity, Long> {
//	
//	@Query(value="select * from sdb1.tickets where number_plate = :numberPlate order by id DESC limit 0,1", nativeQuery=true)
//	OrdersEntity findByNumberPlate(@Param(value="numberPlate") String numberPlate);

	OrdersEntity findByOrderId(Long orderId);
	List<OrdersEntity> findAllByOrderByIdDesc(Pageable pageable);

}

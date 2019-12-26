package com.spm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spm.entity.OrderEntity;

/**
 * Created by Vincent 02/10/2018
 */

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

	OrderEntity findByOrderId(Long orderId);

	@Query(
			value = "SELECT * from orders  WHERE  (:cardCode is null OR card_code = :cardCode)  AND  (:cardStt is null OR card_stt = :cardStt)  AND (:carNumber is null OR car_number like %:carNumber%) AND ((:dateFrom is null OR created >= :dateFrom) && (:dateTo is null OR created <= :dateTo)) order by updated DESC", 
			countQuery = "SELECT * from orders  WHERE  (:cardCode is null OR card_code = :cardCode)  AND  (:cardStt is null OR card_stt = :cardStt)  AND (:carNumber is null OR car_number like %:carNumber%) AND ((:dateFrom is null OR created >= :dateFrom) && (:dateTo is null OR created <= :dateTo)) order by updated DESC", 
			nativeQuery = true
		)
	Page<OrderEntity> search(@Param(value = "cardCode") String cardCode, @Param(value = "cardStt") String cardStt,
			@Param(value = "carNumber") String carNumber, @Param(value = "dateFrom") String dateFrom,
			@Param(value = "dateTo") String dateTo, Pageable  pageable);

}

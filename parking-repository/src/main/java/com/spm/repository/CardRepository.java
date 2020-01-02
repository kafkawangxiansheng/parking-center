package com.spm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spm.entity.CardsEntity;

/**
 * Created by Vincent 02/10/2018
 */

public interface CardRepository  extends JpaRepository<CardsEntity, Long> {

	CardsEntity findByCode(Long code);

	@Query(
			value = "SELECT * from cards WHERE (:code is null OR code = :code) AND (:stt is null OR stt = :stt) AND (:vehicleId is null OR vehicle_id = :vehicleId) order by updated DESC",
			countQuery = "SELECT * from cards WHERE (:code is null OR code = :code) AND (:stt is null OR stt = :stt) AND (:vehicleId is null OR vehicle_id = :vehicleId) order by updated DESC",
			nativeQuery = true
		)
	Page<CardsEntity> search(@Param(value = "code") String code, @Param(value = "stt") String stt,
			@Param(value = "vehicleId") String vehicleId, Pageable  pageable);
}

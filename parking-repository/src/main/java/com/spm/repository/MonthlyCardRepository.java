package com.spm.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spm.entity.MonthlyCardEntity;

/**
 * Created by Vincent 02/10/2018
 */

public interface MonthlyCardRepository  extends JpaRepository<MonthlyCardEntity, Long> {

	MonthlyCardEntity findByCardCode(String cardCode);
	
	@Query(value = "SELECT * FROM monthly_cards where  project_id = :projectId AND last_sync < updated", nativeQuery = true)
	List<MonthlyCardEntity> syncAllByProjectId(@Param(value = "projectId") Long projectId);
	
	@Query(value = "SELECT * FROM monthly_cards WHERE  "
			+ "(:cardCode is null OR card_code = :cardCode) AND (:vehicleId is null OR vehicle_id = :vehicleId)", nativeQuery = true)
	Page<MonthlyCardEntity> search(
			@Param(value = "cardCode") String cardCode,
			@Param(value = "vehicleId") String vehicleId,
//			@Param(value = "numberEndDate") String numberEndDate,
			Pageable pageable);
}


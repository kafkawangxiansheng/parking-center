package com.spm.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spm.entity.MonthlyCardEntity;

/**
 * Created by Vincent 02/10/2018
 */
@Repository
@Transactional
public interface MonthlyCardRepository  extends JpaRepository<MonthlyCardEntity, Long> {

	MonthlyCardEntity findByCardCode(String cardCode);
	
	@Query(value = "SELECT * FROM monthly_cards where  project_id = :projectId AND last_sync < updated", nativeQuery = true)
	List<MonthlyCardEntity> syncAllByProjectId(@Param(value = "projectId") Long projectId);
	
	@Query(value = "SELECT * FROM monthly_cards WHERE  "
			+ "(:cardCode is null OR card_code = :cardCode) AND (:vehicleId is null OR vehicle_id = :vehicleId)"
			+ "AND (:customerName is null OR customer_name = :customerName) AND (project_id = :projectId) AND (deleted = 0)", nativeQuery = true)
	Page<MonthlyCardEntity> search(
			@Param(value = "cardCode") String cardCode,
			@Param(value = "vehicleId") String vehicleId,
			@Param(value = "customerName") String customerName,
//			@Param(value = "vehicleId") int statusDate,
//			@Param(value = "numberEndDate") int numberEndDate,
			@Param(value = "projectId") long projectId,
			Pageable pageable);
	
	@Query(value = "SELECT * FROM monthly_cards WHERE  "
			+ "(:cardCode is null OR card_code = :cardCode) AND (:customerName is null OR customer_name = :customerName) AND (end_date < :currentDate) AND (project_id = :projectId)", nativeQuery = true)
	List<MonthlyCardEntity> renewalSearch(
			@Param(value = "cardCode") String cardCode,
			@Param(value = "customerName") String customerName,
			@Param(value = "currentDate") long currentDate,
			@Param(value = "projectId") long projectId,
			Pageable pageable );
	
}


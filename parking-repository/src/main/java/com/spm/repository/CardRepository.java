package com.spm.repository;

import java.util.List;

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

//	List<CardsEntity> findAllByDisable(int disable);
//	
//	List<CardsEntity> findAllByCodeAndDisable(String code, int disable);
	
	@Query(
			value = "SELECT * from cards WHERE (:code is null OR code = :code) "
					+ "AND (:stt is null OR stt = :stt) "
					+ "AND (:vehicleId is null OR vehicle_id = :vehicleId) "
					+ "AND (project_id = :projectId) "
					+ "AND deleted = 0 order by updated DESC",
					nativeQuery = true
		)
	Page<CardsEntity> search(@Param(value = "code") String code, 
							@Param(value = "stt") String stt, 
							@Param(value = "vehicleId") String vehicleId, 
							@Param(value = "projectId") long projectId, 
							Pageable  pageable);

	@Query(
			value = "SELECT * from cards WHERE (:code is null OR code = :code) AND (:disable is null OR disable = :disable) order by updated DESC",
			countQuery = "SELECT * from cards WHERE (:code is null OR code = :code) AND (:disable is null OR disable = :disable) order by updated DESC",
			nativeQuery = true
			)
	List<CardsEntity> searchDisable(@Param(value = "code") String code, @Param(value = "disable") int disable);
	
	@Query(value = "SELECT c.* FROM cards as c INNER JOIN vehicles as v ON c.vehicle_id = v.id AND v.card_type = :cardType AND c.code = :code", nativeQuery = true)
	CardsEntity findByCodeAndVehicleCardType(@Param(value = "code") String code, @Param(value = "cardType") int cardType);
	
	@Query(value = "SELECT * FROM cards where  project_id = :projectId AND last_sync < updated", nativeQuery = true)
	List<CardsEntity> syncAllByProjectId(@Param(value = "projectId") Long projectId);
	
	CardsEntity findByCode(String cardCode);
	
	@Query(value = "SELECT c.* FROM cards as c INNER JOIN vehicles as v ON c.vehicle_id = v.id AND v.card_type = :cardType AND c.code = :code AND c.project_id = :projectId", nativeQuery = true)
	CardsEntity findByCodeAndVehicleCardTypeAndProjectId(@Param(value = "code") String code, @Param(value = "cardType") int cardType,  @Param(value = "projectId") long projectId);
}

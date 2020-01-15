package com.spm.repository;

import java.util.List;

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
}

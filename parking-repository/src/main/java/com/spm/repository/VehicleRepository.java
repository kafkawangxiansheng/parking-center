package com.spm.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spm.entity.VehicleEntity;

public interface VehicleRepository extends JpaRepository<VehicleEntity, Long>{
	
	@Query(
			value = "SELECT * form vehicles WHERE (project_id = :projectId) order by updated DESC",
			countQuery = "SELECT * form vehicles WHERE (project_id = :projectId) order by updated DESC",
			nativeQuery = true
		)
	List<VehicleEntity> findAll(@Param(value = "projectId") String projectId);
}

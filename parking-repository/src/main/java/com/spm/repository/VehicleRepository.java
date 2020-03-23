package com.spm.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spm.entity.VehicleEntity;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, Long>{
	
	List<VehicleEntity> findAllByProjectIdAndDeleted(long projectId, int delete);
	
	List<VehicleEntity> findAllByProjectId(long projectId);
	
	VehicleEntity findByVehicleIdAndProjectId(long type,  long projectId);
	
	VehicleEntity findById(long id);
	
	@Query(value = "SELECT * FROM vehicles where  project_id = :projectId AND (last_sync < updated or last_sync is null)", nativeQuery = true)
	List<VehicleEntity> syncAllByProjectId(@Param(value = "projectId") Long projectId);
	
}

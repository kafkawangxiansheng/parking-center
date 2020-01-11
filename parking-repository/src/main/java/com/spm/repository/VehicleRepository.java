package com.spm.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spm.entity.VehicleEntity;

public interface VehicleRepository extends JpaRepository<VehicleEntity, Long>{
	
	List<VehicleEntity> findAllByProjectId(long projectId);
}

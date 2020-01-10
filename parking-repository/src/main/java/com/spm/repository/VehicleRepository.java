package com.spm.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.spm.entity.VehicleEntity;

public interface VehicleRepository extends JpaRepository<VehicleEntity, Long>{
	
}

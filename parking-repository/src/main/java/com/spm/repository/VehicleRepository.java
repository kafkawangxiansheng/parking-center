package com.spm.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.spm.entity.VehicleEntity;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, Long>{
	
	List<VehicleEntity> findAllByProjectIdAndDeleted(long projectId, boolean delete);
	
	List<VehicleEntity> findAllByProjectId(long projectId);
	
	VehicleEntity findByTypeAndProjectId(int type,  long projectId);
	
	VehicleEntity findById(long id);
	
}

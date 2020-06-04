package com.spm.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spm.entity.FunctionEntity;

@Repository
public interface FunctionRepository extends JpaRepository<FunctionEntity, Long>{
	
	List<FunctionEntity> findAllByProjectIdAndDeleted(long projectId, int delete);
	
	List<FunctionEntity> findAllByProjectId(long projectId);
	FunctionEntity findAllByProjectIdAndFunctionId(long projectId, String functionId);
	
	FunctionEntity findById(long id);
	
	@Query(value = "SELECT * FROM functions where  project_id = :projectId AND (last_sync < updated or last_sync is null)", nativeQuery = true)
	List<FunctionEntity> syncAllByProjectId(@Param(value = "projectId") Long projectId);
	
}

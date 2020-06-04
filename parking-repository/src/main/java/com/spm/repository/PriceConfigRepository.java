package com.spm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spm.entity.PriceConfigEntity;

@Repository
@Transactional
public interface PriceConfigRepository extends JpaRepository<PriceConfigEntity, Long> {
	
	List<PriceConfigEntity> findAllByProjectId(long projectId);
	
	PriceConfigEntity findAllByProjectIdAndCode(long projectId, String code);
	
	
	List<PriceConfigEntity> findAllByProjectIdAndDeleted(long projectId, int deleted);
	
	
	@Query(value = "SELECT * FROM price_configs where  project_id = :projectId AND (last_sync < updated_at or last_sync is null)", nativeQuery = true)
	List<PriceConfigEntity> syncAllByProjectId(@Param(value = "projectId") Long projectId);
	
}

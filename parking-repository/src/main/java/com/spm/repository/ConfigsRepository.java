package com.spm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spm.entity.ConfigEntity;

@Repository
@Transactional
public interface ConfigsRepository extends JpaRepository<ConfigEntity, Long> {
	
	List<ConfigEntity> findAllByProjectId(long projectId);
	
	ConfigEntity findAllByProjectIdAndCode(long projectId, String code);
	
	
	List<ConfigEntity> findAllByProjectIdAndDeleted(long projectId, int deleted);
	
	
	@Query(value = "SELECT * FROM configs where  project_id = :projectId AND (last_sync < updated_at or last_sync is null)", nativeQuery = true)
	List<ConfigEntity> syncAllByProjectId(@Param(value = "projectId") Long projectId);
	
}

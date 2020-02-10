package com.spm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spm.entity.ProjectsEntity;

@Repository
@Transactional
public interface ProjectRepository  extends JpaRepository<ProjectsEntity, Long> {
	
	@EntityGraph(attributePaths = { "company"})
	List<ProjectsEntity> findAll();
	
}

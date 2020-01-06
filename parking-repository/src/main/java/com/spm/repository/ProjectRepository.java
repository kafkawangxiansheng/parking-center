package com.spm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.spm.entity.ProjectsEntity;

public interface ProjectRepository  extends JpaRepository<ProjectsEntity, Long> {
	
	@EntityGraph(attributePaths = { "company"})
	List<ProjectsEntity> findAll();

}

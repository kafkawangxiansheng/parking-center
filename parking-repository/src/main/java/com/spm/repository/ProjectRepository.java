package com.spm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spm.entity.ProjectsEntity;

/**
 * Created by sudo 28/12/2019
 */

public interface ProjectRepository  extends JpaRepository<ProjectsEntity, Long> {

}

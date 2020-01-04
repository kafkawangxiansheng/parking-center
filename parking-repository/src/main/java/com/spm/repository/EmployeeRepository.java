package com.spm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spm.entity.EmployeeEntity;

@Repository
@Transactional
public interface EmployeeRepository  extends JpaRepository<EmployeeEntity, Long> {
	List<EmployeeEntity> findAllByProjectId(long projectId);
}

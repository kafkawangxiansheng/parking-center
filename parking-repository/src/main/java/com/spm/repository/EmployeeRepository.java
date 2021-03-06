package com.spm.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spm.entity.EmployeeEntity;

@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
	
	List<EmployeeEntity> findAllByProjectId(long projectId);
	
	EmployeeEntity findByEmployeeCode(String employeeCode);
	
	
	List<EmployeeEntity> findAllByProjectIdAndDeleted(long projectId, int deleted);
	
	@Query(
			value = "SELECT * from employees WHERE (:name is null OR name = :name) AND (:userName is null OR userName = :userName) AND (:pass is null OR pass = :pass) AND (:position is null OR position = :position) AND (:sex is null OR sex = :sex)",
			countQuery = "SELECT * from employees WHERE (:name is null OR name = :name) AND (:userName is null OR userName = :userName) AND (:pass is null OR pass = :pass) AND (:position is null OR position = :position) AND (:sex is null OR sex = :sex)",
			nativeQuery = true
	)
	Page<EmployeeEntity> search(@Param(value = "name") String name, @Param(value = "position") String position, Pageable pageable);
	
	EmployeeEntity findByUsername(String username);
	
	@Query(value = "SELECT * FROM employees where  project_id = :projectId AND (last_sync < updated_at or last_sync is null)", nativeQuery = true)
	List<EmployeeEntity> syncAllByProjectId(@Param(value = "projectId") Long projectId);
	
}

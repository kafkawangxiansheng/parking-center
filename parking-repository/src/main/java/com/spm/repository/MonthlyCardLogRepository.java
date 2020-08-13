package com.spm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spm.entity.MonthlyCardLogEntity;

/**
 * Created by Vincent 02/10/2018
 */
@Repository
@Transactional
public interface MonthlyCardLogRepository  extends JpaRepository<MonthlyCardLogEntity, Long> {

	Page<MonthlyCardLogEntity> findByProjectID(Long projectId, Pageable pageable);
}


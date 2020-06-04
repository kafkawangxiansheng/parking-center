package com.spm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spm.entity.BlackCarEntity;

@Repository
@Transactional
public interface BlackCarRepository extends JpaRepository<BlackCarEntity, Long> {
	
	BlackCarEntity findByDigit(String digit);
	
	@Query(value = "SELECT * FROM black_car where  (last_sync < updated_at or last_sync is null)", nativeQuery = true)
	List<BlackCarEntity> syncAll();
	
}

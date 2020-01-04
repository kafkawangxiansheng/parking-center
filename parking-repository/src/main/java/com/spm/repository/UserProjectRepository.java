package com.spm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spm.entity.UserProjectEntity;

@Repository
@Transactional
public interface UserProjectRepository  extends JpaRepository<UserProjectEntity, Long> {
	List<UserProjectEntity> findByUserId(Long userId);
}

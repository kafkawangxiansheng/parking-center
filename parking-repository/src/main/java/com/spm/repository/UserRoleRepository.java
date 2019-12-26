package com.spm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spm.entity.UserRoleEntity;

@Repository
@Transactional
public interface UserRoleRepository  extends JpaRepository<UserRoleEntity, Long> {
	List<UserRoleEntity> findByUserId(Long userId);
}

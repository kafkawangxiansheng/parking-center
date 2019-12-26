package com.spm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spm.entity.UserEntity;

@Repository
@Transactional
public interface UserRepository  extends JpaRepository<UserEntity, Long> {
	UserEntity findByUsername(String username);
}

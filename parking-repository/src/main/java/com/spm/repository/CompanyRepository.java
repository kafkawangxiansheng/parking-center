package com.spm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spm.entity.CompanyEntity;

@Repository
@Transactional
public interface CompanyRepository  extends JpaRepository<CompanyEntity, Long> {

}

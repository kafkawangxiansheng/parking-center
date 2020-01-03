package com.spm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spm.entity.CompanyEntity;

public interface CompanyRepository  extends JpaRepository<CompanyEntity, Long> {

}

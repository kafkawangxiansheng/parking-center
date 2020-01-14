package com.spm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spm.entity.MonthlyCardEntity;

/**
 * Created by Vincent 02/10/2018
 */

public interface MonthlyCardRepository  extends JpaRepository<MonthlyCardEntity, Long> {

	MonthlyCardEntity findByCardCode(String cardCode);
}

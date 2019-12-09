package net.spm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.spm.jpa.entity.MonthlyCardEntity;

/**
 * Created by Vincent 02/10/2018
 */

public interface MonthlyCardRepository  extends JpaRepository<MonthlyCardEntity, Long> {

}

package com.spm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spm.entity.CardsEntity;

/**
 * Created by Vincent 02/10/2018
 */

public interface CardRepository  extends JpaRepository<CardsEntity, Long> {

}

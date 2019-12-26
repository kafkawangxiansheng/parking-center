package com.spm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spm.entity.RevenueEntity;

/**
 * Created by Vincent 02/10/2018
 */

public interface RevenueRepository  extends JpaRepository<RevenueEntity, String> {

	RevenueEntity findByVehicleId(Integer vehicleId);
}

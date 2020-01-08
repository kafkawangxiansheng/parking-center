package com.spm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spm.entity.SettingEntity;

/**
 * Created by Vincent 02/10/2018
 */

public interface SettingRepository  extends JpaRepository<SettingEntity, Long> {

	List<SettingEntity> findAllByProjectId(int projectId);
	List<SettingEntity> findAllByAdminTypeAndProjectId(int adminType,  int projectId);
	
}

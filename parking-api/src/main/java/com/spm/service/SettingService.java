package com.spm.service;

import java.util.List;

import com.spm.dto.SettingDto;

/**
 * Created by Vincent 23/05/2018
 */
public interface SettingService {

	List<SettingDto> findAll();

	List<SettingDto> findAllByProjectId(int projectId);

	List<SettingDto> findAllByAdminType(int adminType);

	SettingDto findOneById(long id);

	SettingDto create(SettingDto settingDto);

	void delete(long id);

}

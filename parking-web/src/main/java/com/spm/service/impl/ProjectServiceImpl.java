package com.spm.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spm.common.RestUtils;
import com.spm.common.URLConstants;
import com.spm.dto.ProjectsDto;
import com.spm.dto.ResultObject;
import com.spm.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService{

	@Override
	public ResultObject<List<ProjectsDto>> getAllProjects() {
		RestUtils<ProjectsDto> restUtils = new RestUtils<>(ProjectsDto.class);
		ResultObject<List<ProjectsDto>> resultFromApi = new ResultObject<>();
		String finalURL = URLConstants.URL_GET_ALL_PROJECT;
		resultFromApi = restUtils.get(finalURL);
		return resultFromApi;
	}

	@Override
	public ResultObject<List<ProjectsDto>> getProjectById(Long projectId) {
		ResultObject<List<ProjectsDto>> resultFromApi = new ResultObject<>();
		RestUtils<ProjectsDto> restUtils = new RestUtils<>(ProjectsDto.class);
		String finalURL = URLConstants.URL_GET_PROJECT_BY_ID.replace("::projectId", String.valueOf(projectId));
		resultFromApi = restUtils.get(finalURL);
		return resultFromApi;
	}

//	@Override
//	public ResultObject<ProjectsDto> getProjectsById(int projectId) {
//		String finalURL = URLConstants.URL_GET_ALL_REVENUE.replace(":projectId", String.valueOf(projectId));
//		RestUtils<ProjectsDto> restUtils = new RestUtils<>(ProjectsDto.class);
//		ResultObject<ProjectsDto> resultFromApi = new ResultObject<>();
//		resultFromApi = restUtils.get(finalURL);
//		return resultFromApi;
//	}

}

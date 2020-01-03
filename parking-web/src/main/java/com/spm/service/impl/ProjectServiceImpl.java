package com.spm.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.entity.StringEntity;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
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
	public ProjectsDto getProjectById(Long id) {
		ResultObject<List<ProjectsDto>> resultFromApi = new ResultObject<>();
		RestUtils<ProjectsDto> restUtils = new RestUtils<>(ProjectsDto.class);
		String finalURL = URLConstants.URL_GET_PROJECT_BY_ID.replace("::id", String.valueOf(id));
		resultFromApi = restUtils.get(finalURL);
		return resultFromApi.getData().get(0);
	}

	@Override
	public ProjectsDto addProject(ProjectsDto projectsDto) {
		RestUtils<ProjectsDto> restUtils = new RestUtils<>(ProjectsDto.class);
		ResultObject<List<ProjectsDto>> resultFromApi = new ResultObject<>();
		Gson gson = new Gson();
		StringEntity stringEntity;
		try {
			stringEntity = new StringEntity(gson.toJson(projectsDto), "UTF-8");
			resultFromApi = restUtils.postJSON(URLConstants.URL_POST_ADD_PROJECT, stringEntity);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return resultFromApi.getData().get(0);
	}
	
	@Override
	public void deleteProject(Long id) {
		RestUtils<ProjectsDto> restUtils = new RestUtils<>(ProjectsDto.class);
		String finalURL = URLConstants.URL_DELETE_PROJECT.replace("::id", String.valueOf(id));
		
		try {
			restUtils.delete(finalURL);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Integer> getProjectIdsByUsername(Long userId) {
		RestUtils<Integer> restUtils = new RestUtils<>(Integer.class);
		ResultObject<List<Integer>> resultFromAPI = new ResultObject<>();
		
		String finalURL = URLConstants.URL_GET_PROJECT_IDS_BY_USER_ID.replace("{userId}", String.valueOf(userId));
		resultFromAPI  = restUtils.get(finalURL);
		
		return resultFromAPI.getData();
	}

}

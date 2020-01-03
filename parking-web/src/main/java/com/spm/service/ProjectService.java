package com.spm.service;

import java.util.List;

import com.spm.dto.ProjectsDto;
import com.spm.dto.ResultObject;

public interface ProjectService {
	ResultObject<List<ProjectsDto>> getAllProjects();
	
	ProjectsDto getProjectById(Long projectId);
	
	ProjectsDto addProject(ProjectsDto projectsDto);
	
	void deleteProject(Long id);
	
	List<Integer> getProjectIdsByUsername(Long userId);
	
}

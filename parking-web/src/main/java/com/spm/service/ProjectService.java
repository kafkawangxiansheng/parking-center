package com.spm.service;

import java.util.List;

import com.spm.dto.ProjectsDto;
import com.spm.dto.ResultObject;

public interface ProjectService {
	ResultObject<List<ProjectsDto>> getAllProjects();
	
	ResultObject<List<ProjectsDto>> getProjectById(Long projectId);
	
}

package com.spm.service;

import java.util.List;

import com.spm.dto.ProjectsDto;
import com.spm.dto.ResultObject;

/**
 * Created by thanhtuan 04/01/2020
 */
public interface ProjectsService {

	ResultObject<List<ProjectsDto>> save(ProjectsDto projectsDto);
    
	ResultObject<List<ProjectsDto>> save(List<ProjectsDto> projectsDtos);

    void delete(Long id);
    
    ResultObject<List<ProjectsDto>> findAll();
    
    ResultObject<List<ProjectsDto>> findById(Long projectId);
    

}
 
package com.spm.service;

import java.util.List;

import com.spm.dto.ProjectsDto;
import com.spm.dto.ResultObject;

/**
 * Created by sudo 28/12/2019
 */
public interface ProjectsService {

	ResultObject<List<ProjectsDto>> save(ProjectsDto projectsDto);
    
	ResultObject<List<ProjectsDto>> save(List<ProjectsDto> projectsDtos);

    void delete(ProjectsDto projectsDto);
    
    ResultObject<List<ProjectsDto>> findAll();
    
    ResultObject<List<ProjectsDto>> findById(Long projectId);
    

}
 
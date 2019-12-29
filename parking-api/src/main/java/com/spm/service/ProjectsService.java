package com.spm.service;

import java.util.List;

import com.spm.dto.ProjectsDto;

/**
 * Created by sudo 28/12/2019
 */
public interface ProjectsService {

	ProjectsDto save(ProjectsDto projectsDto);
    
    List<ProjectsDto> save(List<ProjectsDto> projectsDtos);

    void delete(ProjectsDto projectsDto);
    
    List<ProjectsDto> findAll();

}
 
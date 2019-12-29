package com.spm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spm.dto.ProjectsDto;
import com.spm.entity.ProjectsEntity;
import com.spm.repository.ProjectRepository;
import com.spm.service.ProjectsService;

@Service
public class ProjectsServiceImpl implements ProjectsService{

	@Autowired
	private ProjectRepository projectRepository;

	ModelMapper mapper;
	
	private List<ProjectsDto> map(List<ProjectsEntity> source) {

		ArrayList<ProjectsDto> rtn = new ArrayList<>();
		source.stream().map((entity) -> {
			ProjectsDto dto = new ProjectsDto();
			mapper.map(entity, dto);
			return dto;
		}).forEachOrdered((dto) -> {
			rtn.add(dto);
		});
		return rtn;
	}
	
	private List<ProjectsEntity> reMap(List<ProjectsDto> source) {

		ArrayList<ProjectsEntity> rtn = new ArrayList<>();
		source.stream().map((dto) -> {
			ProjectsEntity entity = new ProjectsEntity();
			mapper.map(dto, entity);
			return entity;
		}).forEachOrdered((entity) -> {
			rtn.add(entity);
		});
		return rtn;
	}
	
	@PostConstruct
	public void initialize() {
		mapper = new ModelMapper();
	}
	
	@Override
	public ProjectsDto save(ProjectsDto projectsDto) {
		ProjectsEntity entity = new ProjectsEntity();
		mapper.map(projectsDto, entity);
		entity = projectRepository.save(entity);
		mapper.map(entity, projectsDto);
		return projectsDto;
	}

	@Override
	public List<ProjectsDto> save(List<ProjectsDto> projectsDtos) {
		List<ProjectsEntity> projectsEntities = reMap(projectsDtos);
		projectsEntities = projectRepository.saveAll(projectsEntities);
		projectsDtos = map(projectsEntities);
		return projectsDtos;
	}

	@Override
	public void delete(ProjectsDto projectsDto) {
		ProjectsEntity entity = new ProjectsEntity();
		mapper.map(projectsDto, entity);
	}

	@Override
	public List<ProjectsDto> findAll() {
		List<ProjectsEntity> entities = projectRepository.findAll();
		return this.map(entities);
	}

}

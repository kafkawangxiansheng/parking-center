package com.spm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spm.dto.ProjectsDto;
import com.spm.dto.ResultObject;
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
	
//	@PostConstruct
//	private void postConstruct() {
//	mapper.getConfiguration().setAmbiguityIgnored(true);
//		
//	}
	
	@Override
	public ResultObject<List<ProjectsDto>> save(ProjectsDto projectsDto) {
		ResultObject<List<ProjectsDto>> resultObj = new ResultObject<>();
		List<ProjectsDto> listObject = new ArrayList<>();
		ProjectsEntity entity = new ProjectsEntity();
		mapper.map(projectsDto, entity);
		entity = projectRepository.save(entity);
		mapper.map(entity, projectsDto);
		listObject.add(projectsDto);
		resultObj.setData(listObject);
		return resultObj;
	}

	@Override
	public ResultObject<List<ProjectsDto>> save(List<ProjectsDto> projectsDtos) {
		ResultObject<List<ProjectsDto>> resultObj = new ResultObject<>();
		List<ProjectsEntity> projectsEntities = reMap(projectsDtos);
		projectsEntities = projectRepository.saveAll(projectsEntities);
		resultObj.setData(map(projectsEntities));
		return resultObj;
	}

	@Override
	public void delete(Long id) {
		projectRepository.deleteById(id);
	}

	@Override
	public ResultObject<List<ProjectsDto>> findAll() {
		ResultObject<List<ProjectsDto>> resultObj = new ResultObject<>();
		List<ProjectsEntity> ListEntity = projectRepository.findAll();
		resultObj.setData(this.map(ListEntity));
		return resultObj;
	}

	@Override
	public ResultObject<List<ProjectsDto>> findById(Long projectId) {
		ResultObject<List<ProjectsDto>> resultObj = new ResultObject<>();
		ProjectsEntity entity = projectRepository.findById(projectId).get();
		List<ProjectsDto> listProjectDto = new ArrayList<ProjectsDto>();
		ProjectsDto projectsDto = new ProjectsDto();
		mapper.map(entity, projectsDto);
		listProjectDto.add(projectsDto);
		resultObj.setData(listProjectDto);
		return resultObj;
	}
}

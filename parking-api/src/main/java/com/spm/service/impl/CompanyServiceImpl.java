package com.spm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spm.dto.CompanyDto;
import com.spm.dto.ResultObject;
import com.spm.entity.CompanyEntity;
import com.spm.repository.CompanyRepository;
import com.spm.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService{

	@Autowired
	private CompanyRepository companyRepository;

	ModelMapper mapper;
	
	private List<CompanyDto> map(List<CompanyEntity> source) {

		ArrayList<CompanyDto> rtn = new ArrayList<>();
		source.stream().map((entity) -> {
			CompanyDto dto = new CompanyDto();
			mapper.map(entity, dto);
			return dto;
		}).forEachOrdered((dto) -> {
			rtn.add(dto);
		});
		return rtn;
	}
	
	private List<CompanyEntity> reMap(List<CompanyDto> source) {

		ArrayList<CompanyEntity> rtn = new ArrayList<>();
		source.stream().map((dto) -> {
			CompanyEntity entity = new CompanyEntity();
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
	public ResultObject<List<CompanyDto>> save(CompanyDto companyDto) {
		ResultObject<List<CompanyDto>> resultObj = new ResultObject<>();
		List<CompanyDto> listObject = new ArrayList<>();
		CompanyEntity entity = new CompanyEntity();
		mapper.map(companyDto, entity);
		entity = companyRepository.save(entity);
		mapper.map(entity, companyDto);
		listObject.add(companyDto);
		resultObj.setData(listObject);
		return resultObj;
	}

	@Override
	public ResultObject<List<CompanyDto>> save(List<CompanyDto> companyDtos) {
		ResultObject<List<CompanyDto>> resultObj = new ResultObject<>();
		List<CompanyEntity> projectsEntities = reMap(companyDtos);
		projectsEntities = companyRepository.saveAll(projectsEntities);
		resultObj.setData(map(projectsEntities));
		return resultObj;
	}

	@Override
	public void delete(Long id) {
		companyRepository.deleteById(id);
	}

	@Override
	public ResultObject<List<CompanyDto>> findAll() {
		ResultObject<List<CompanyDto>> resultObj = new ResultObject<>();
		List<CompanyEntity> entities = companyRepository.findAll();
		resultObj.setData(this.map(entities));
		return resultObj;
	}

	@Override
	public ResultObject<List<CompanyDto>> findById(Long projectId) {
		ResultObject<List<CompanyDto>> resultObj = new ResultObject<>();
		CompanyEntity entity = companyRepository.findById(projectId).get();
		List<CompanyDto> listProjectDto = new ArrayList<CompanyDto>();
		CompanyDto companyDto = new CompanyDto();
		mapper.map(entity, companyDto);
		listProjectDto.add(companyDto);
		resultObj.setData(listProjectDto);
		return resultObj;
	}
}

package com.spm.service;

import java.util.List;

import com.spm.dto.RevenueDto;

/**
 * Created by Vincent 23/05/2018
 */
public interface RevenueService {

	List<RevenueDto> getRevenues(int projectId);

}
 
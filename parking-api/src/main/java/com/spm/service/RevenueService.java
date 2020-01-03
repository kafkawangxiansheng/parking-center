package com.spm.service;

import java.util.List;

import com.spm.dto.RevenueDto;
import com.spm.search.form.RevenueSearchForm;

/**
 * Created by Vincent 23/05/2018
 */
public interface RevenueService {

	List<RevenueDto> getRevenues(RevenueSearchForm revenueSearchForm);

}
 
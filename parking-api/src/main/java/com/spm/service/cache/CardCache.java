package com.spm.service.cache;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.spm.entity.CardsEntity;

@Repository
public class CardCache extends HashMap<String, CardsEntity>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}

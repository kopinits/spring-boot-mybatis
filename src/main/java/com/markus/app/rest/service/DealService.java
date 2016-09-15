package com.markus.app.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.markus.app.rest.exception.BusinessException;
import com.markus.app.rest.exception.ExceptionCode;
import com.markus.app.rest.mapper.DealMapper;
import com.markus.app.rest.model.Deal;

@Service
public class DealService {
	@Autowired
	private DealMapper mapper;
	
	public Deal findById(Long id){
		return mapper.findById(id);
	}

	public List<Deal> findAll(){
		return mapper.findAll();
	}

	public Deal insert(Deal deal) throws BusinessException{
		boolean executed = (mapper.insert(deal) == 1);
		if (!executed){
			throw new BusinessException(ExceptionCode.VALIDATION_ERROR, Deal.class.getSimpleName(), deal.getName()); 
		}
		return findById(deal.getId());
	}
	
	public Deal update(Deal deal) throws BusinessException{
		boolean executed = (mapper.update(deal) == 1);
		if (!executed){
			throw new BusinessException(ExceptionCode.OPTIMISTIC_LOCK, Deal.class.getSimpleName(), deal.getName()); 
		}
		return findById(deal.getId());
	}
	

}

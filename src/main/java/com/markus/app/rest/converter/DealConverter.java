package com.markus.app.rest.converter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.markus.app.rest.dto.DealDTO;
import com.markus.app.rest.model.Deal;

@Component
public class DealConverter {
	
	private ModelMapper mapper = new ModelMapper();
	
	public Deal fromDTO(DealDTO dto){
		return mapper.map(dto, Deal.class);
	}

	public DealDTO toDTO(Deal deal){
		return mapper.map(deal, DealDTO.class);
	}

	public List<DealDTO> toDTO(List<Deal> list){
		List<DealDTO> results = new ArrayList<>();
		for (Deal deal : list){
			results.add(toDTO(deal));
		}
		return results;
	}
}

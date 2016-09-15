package com.markus.app.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.markus.app.rest.converter.DealConverter;
import com.markus.app.rest.dto.DealDTO;
import com.markus.app.rest.exception.BusinessException;
import com.markus.app.rest.service.DealService;

@RestController
@RequestMapping("/deal")
public class DealController {
	
	@Autowired
	private DealService service;

	@Autowired
	private DealConverter converter;

	@ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public DealDTO getById(@PathVariable("id") Long id) {
    	return converter.toDTO(service.findById(id));
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public List<DealDTO> listAll() {
        return converter.toDTO(service.findAll());
    }

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public DealDTO save(@RequestBody DealDTO deal) throws BusinessException {
    	return converter.toDTO(service.insert(converter.fromDTO(deal)));
    }

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public DealDTO update(@RequestBody DealDTO deal) throws BusinessException {
    	return converter.toDTO(service.update(converter.fromDTO(deal)));
    }
}

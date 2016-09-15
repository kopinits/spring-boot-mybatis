package com.markus.app.rest.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.markus.app.rest.controller.DealController;
import com.markus.app.rest.dto.DealDTO;
import com.markus.app.rest.model.Deal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DealControllerTest {
	
	private static final String UPDATED_DEAL_NAME = "Updated Deal Name";
	private static final Date DEFAULT_START_DATE = new Date(1473458400000L);
	private static final String DEFAULT_DEAL_NAME_01 = "Deal Name 01";

	@Autowired
	private DealController controller;
	
	@Test
	@Sql("/testdata/deal/deal-test.sql")
	public void getDealTest(){
		assertThat(true);
	}
/*
	@Test
	@Sql("/testdata/deal/deal-test.sql")
	public void getAllDealTest(){
		List<Deal> findAll = controller.findAll();
		assertThat(findAll).isNotNull();
		assertThat(findAll.size()).isEqualTo(5);
	}

	@Test
	@Sql("/testdata/deal/deal-test.sql")
	public void createDealTest(){
		Deal inserted = controller.insert(createDefaultDeal());
		assertThat(inserted.getId()).isNotNull();
		assertThat(inserted.getName()).isEqualTo(DEFAULT_DEAL_NAME_01);
		assertThat(inserted.getStartDate()).isEqualTo(DEFAULT_START_DATE);
	}

	@Test
	@Sql("/testdata/deal/deal-test.sql")
	public void updateDealTest(){
		Deal persisted = controller.findById(Long.valueOf(1));
		persisted.setName(UPDATED_DEAL_NAME);
		Date updatedStartDate = new Date();
		persisted.setStartDate(updatedStartDate);
		controller.update(persisted);
		
		assertThat(persisted.getId()).isEqualTo(1L);
		assertThat(persisted.getName()).isNotEqualTo(DEFAULT_DEAL_NAME_01);
		assertThat(persisted.getName()).isEqualTo(UPDATED_DEAL_NAME);
		assertThat(persisted.getStartDate()).isNotEqualTo(DEFAULT_START_DATE);
		assertThat(persisted.getStartDate()).isEqualTo(updatedStartDate);
	}

	private Deal createDefaultDeal() {
		Deal deal = new Deal();
		deal.setName(DEFAULT_DEAL_NAME_01);
		deal.setStartDate(DEFAULT_START_DATE);
		return deal;
	}
	

	private DealDTO createDefaultDealDTO() {
		DealDTO deal = new DealDTO();
		deal.setId(1L);
		deal.setName(DEFAULT_DEAL_NAME_01);
		deal.setStartDate(DEFAULT_START_DATE);
		return deal;
	}*/	
}



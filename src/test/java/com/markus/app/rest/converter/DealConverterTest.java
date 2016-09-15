package com.markus.app.rest.converter;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.markus.app.rest.converter.DealConverter;
import com.markus.app.rest.dto.DealDTO;
import com.markus.app.rest.model.Deal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DealConverterTest {
	
	@Autowired
	private DealConverter converter;
	
	@Test
	public void convertDealToDTOTest(){
		Deal deal = createDefaultDeal();
		DealDTO dto = converter.toDTO(deal);
		assertThat(dto.getId()).isEqualTo(deal.getId());
		assertThat(dto.getName()).isEqualTo(deal.getName());
		assertThat(dto.getStartDate()).isEqualTo(deal.getStartDate());
	}

	@Test
	public void convertDTOtoDealTest(){
		DealDTO dto = createDefaultDealDTO();
		Deal deal = converter.fromDTO(dto);
		assertThat(deal.getId()).isEqualTo(dto.getId());
		assertThat(deal.getName()).isEqualTo(dto.getName());
		assertThat(deal.getStartDate()).isEqualTo(dto.getStartDate());
	}

	@Test
	public void convertDealListToDTOListTest(){
		ArrayList<Deal> dealList = new ArrayList<>();
		dealList.add(createDefaultDeal());
		List<DealDTO> dtoList = converter.toDTO(dealList);
		assertThat(dealList.size()).isEqualTo(dtoList.size());
		assertThat(dtoList.get(0).getId()).isEqualTo(dealList.get(0).getId());
		assertThat(dtoList.get(0).getName()).isEqualTo(dealList.get(0).getName());
		assertThat(dtoList.get(0).getStartDate()).isEqualTo(dealList.get(0).getStartDate());
	}

	private Deal createDefaultDeal() {
		Deal deal = new Deal();
		deal.setId(1L);
		deal.setName("Dealk Name");
		deal.setStartDate(new Date(1489017600000L));
		return deal;
	}

	private DealDTO createDefaultDealDTO() {
		DealDTO deal = new DealDTO();
		deal.setId(1L);
		deal.setName("Dealk Name");
		deal.setStartDate(new Date(1489017600000L));
		return deal;
	}
}


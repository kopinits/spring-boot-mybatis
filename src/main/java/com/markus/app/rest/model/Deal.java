package com.markus.app.rest.model;

import java.util.Date;

public class Deal extends AbstractEntity{
	
	private String name;
	private Date startDate;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

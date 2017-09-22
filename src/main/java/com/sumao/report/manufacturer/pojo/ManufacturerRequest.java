package com.sumao.report.manufacturer.pojo;

import com.sumao.report.pojo.PaginationRequest;

public class ManufacturerRequest extends PaginationRequest{
	private String startDate;
	private String endDate;
	private String producer;
	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	
}

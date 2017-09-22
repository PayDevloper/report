package com.sumao.report.skudetail.pojo;

import com.sumao.report.pojo.PaginationRequest;

public class SkuDetailRequest extends PaginationRequest {

	private String	startDate;
	private String	endDate;
	private String	producer;
	private String	category;
	private String	use;



	public String getProducer() {
		return producer;
	}



	public void setProducer(String producer) {
		this.producer = producer;
	}



	public String getCategory() {
		return category;
	}



	public void setCategory(String category) {
		this.category = category;
	}



	public String getUse() {
		return use;
	}



	public void setUse(String use) {
		this.use = use;
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

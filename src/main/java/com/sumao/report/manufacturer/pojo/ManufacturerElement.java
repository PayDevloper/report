package com.sumao.report.manufacturer.pojo;


public class ManufacturerElement{
	private int  id;
	private String producer;
	private String sales;
	private String amount;
	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}
	public String getSales() {
		return sales;
	}
	public void setSales(String sales) {
		this.sales = sales;
	}
	public String getAmount() {
		return amount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	
}

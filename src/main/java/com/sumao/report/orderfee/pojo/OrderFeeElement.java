package com.sumao.report.orderfee.pojo;


public class OrderFeeElement{
	private String happenDate;
	private Double happenAmount;
	private Double currentAmount;
	private String happenType;
	private String orderId;
	private String isWarning;
	private int id ; 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHappenDate() {
		return happenDate;
	}
	public void setHappenDate(String happenDate) {
		this.happenDate = happenDate;
	}
	public Double getHappenAmount() {
		return happenAmount;
	}
	public void setHappenAmount(Double happenAmount) {
		this.happenAmount = happenAmount;
	}
	public Double getCurrentAmount() {
		return currentAmount;
	}
	public void setCurrentAmount(Double currentAmount) {
		this.currentAmount = currentAmount;
	}
	public String getHappenType() {
		return happenType;
	}
	public void setHappenType(String happenType) {
		this.happenType = happenType;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getIsWarning() {
		return isWarning;
	}
	public void setIsWarning(String isWarning) {
		this.isWarning = isWarning;
	}
	
	

}

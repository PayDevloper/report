package com.sumao.report.customersale.pojo;

public class CustomerSaleElement{
	private int id;
	private String customerCode;
	private String customerName;
	private String businessType;
	private String sales;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private String amount;
	private Integer ordersNumber;
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public Integer getOrdersNumber() {
		return ordersNumber;
	}
	public void setOrdersNumber(Integer ordersNumber) {
		this.ordersNumber = ordersNumber;
	}
	public String getSales() {
		return sales;
	}
	public void setSales(String sales) {
		this.sales = sales;
	}

	
}

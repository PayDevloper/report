package com.sumao.report.orderdetail.pojo;

import com.sumao.report.pojo.PaginationRequest;

public class OrderDetailRequest extends PaginationRequest {
	private String	startDate;
	private String	endDate;
	private String	orderNum;
	private String	orderType;
	private String	orderstate;
	private String	grade;
	private String	warehouse;
	private String	buyerName;
	private String	invoiceState;



	public String getOrderNum() {
		return orderNum;
	}



	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}



	public String getOrderType() {
		return orderType;
	}



	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}



	public String getGrade() {
		return grade;
	}



	public void setGrade(String grade) {
		this.grade = grade;
	}



	public String getWarehouse() {
		return warehouse;
	}



	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}



	public String getBuyerName() {
		return buyerName;
	}



	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}



	public String getInvoiceState() {
		return invoiceState;
	}



	public void setInvoiceState(String invoiceState) {
		this.invoiceState = invoiceState;
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



	public String getOrderstate() {
		return orderstate;
	}



	public void setOrderstate(String orderstate) {
		this.orderstate = orderstate;
	}

}

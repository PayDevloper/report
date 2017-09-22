package com.sumao.report.customersale.pojo;

import com.sumao.report.pojo.PaginationRequest;


public class CustomerSaleRequest extends PaginationRequest{
	
	private String startDate;
	private String endDate;
	private String customerCode;
	private String customerName;
	private String businessType;
	
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
	@Override
	public String toString() {
		return "CustomerSaleRequest [startDate=" + startDate + ", endDate="
				+ endDate + ", customerCode=" + customerCode
				+ ", customerName=" + customerName + ", businessType="
				+ businessType + "]";
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

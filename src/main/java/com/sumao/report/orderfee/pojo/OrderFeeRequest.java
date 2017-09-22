package com.sumao.report.orderfee.pojo;

import com.sumao.report.pojo.PaginationRequest;

public class OrderFeeRequest extends PaginationRequest {
	private String	startDate;
	private String	endDate;
	private String	happenType;
	private String	orderId;



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

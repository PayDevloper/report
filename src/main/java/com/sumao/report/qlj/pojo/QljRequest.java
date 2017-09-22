package com.sumao.report.qlj.pojo;

import com.sumao.report.pojo.PaginationRequest;

public class QljRequest extends PaginationRequest {
	private String	startDate;
	private String	endDate;
	private String	paymentState;
	private String	qlj;



	public String getPaymentState() {
		return paymentState;
	}



	public void setPaymentState(String paymentState) {
		this.paymentState = paymentState;
	}



	public String getQlj() {
		return qlj;
	}



	public void setQlj(String qlj) {
		this.qlj = qlj;
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

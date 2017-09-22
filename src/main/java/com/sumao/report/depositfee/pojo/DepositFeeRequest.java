package com.sumao.report.depositfee.pojo;

import com.sumao.report.pojo.PaginationRequest;

public class DepositFeeRequest extends PaginationRequest{
	private String paymentState;

	public String getPaymentState() {
		return paymentState;
	}

	public void setPaymentState(String paymentState) {
		this.paymentState = paymentState;
	}
	
}

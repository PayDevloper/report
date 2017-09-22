package com.sumao.report.summary.pojo;

import java.util.List;

import com.sumao.report.pojo.BaseResponse;

public class SummaryResponse extends BaseResponse {
	private SalesSummary			salesSummary;
	private SalesRank				salesRank;
	private OrderStateInfo			orderStateInfo;
	private CustomerQuantityInfo	customerQuantityInfo;
	private TopInfo					topInfo;

	private List<DailySalesSummary>	dailySalesSummary;


	public SalesSummary getSalesSummary() {
		return salesSummary;
	}



	public void setSalesSummary(SalesSummary salesSummary) {
		this.salesSummary = salesSummary;
	}



	public SalesRank getSalesRank() {
		return salesRank;
	}



	public void setSalesRank(SalesRank salesRank) {
		this.salesRank = salesRank;
	}



	public OrderStateInfo getOrderStateInfo() {
		return orderStateInfo;
	}



	public void setOrderStateInfo(OrderStateInfo orderStateInfo) {
		this.orderStateInfo = orderStateInfo;
	}



	public CustomerQuantityInfo getCustomerQuantityInfo() {
		return customerQuantityInfo;
	}



	public void setCustomerQuantityInfo(CustomerQuantityInfo customerQuantityInfo) {
		this.customerQuantityInfo = customerQuantityInfo;
	}



	public TopInfo getTopInfo() {
		return topInfo;
	}



	public void setTopInfo(TopInfo topInfo) {
		this.topInfo = topInfo;
	}



	public List<DailySalesSummary> getDailySalesSummary() {
		return dailySalesSummary;
	}


	public void setDailySalesSummary(List<DailySalesSummary> dailySalesSummary) {
		this.dailySalesSummary = dailySalesSummary;
	}

}

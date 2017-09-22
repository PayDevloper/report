package com.sumao.report.summary.pojo;

import java.util.List;

public class SalesRank {
	private List<TopInfo> hotProductTop;
	private List<TopInfo> customerTop;
	private List<TopInfo> producerTop;
	private List<TopInfo> hotSellRegionTop;
	
	public List<TopInfo> getHotProductTop() {
		return hotProductTop;
	}
	public void setHotProductTop(List<TopInfo> hotProductTop) {
		this.hotProductTop = hotProductTop;
	}
	public List<TopInfo> getCustomerTop() {
		return customerTop;
	}
	public void setCustomerTop(List<TopInfo> customerTop) {
		this.customerTop = customerTop;
	}
	public List<TopInfo> getProducerTop() {
		return producerTop;
	}
	public void setProducerTop(List<TopInfo> producerTop) {
		this.producerTop = producerTop;
	}
	public List<TopInfo> getHotSellRegionTop() {
		return hotSellRegionTop;
	}
	public void setHotSellRegionTop(List<TopInfo> hotSellRegionTop) {
		this.hotSellRegionTop = hotSellRegionTop;
	}
	
}

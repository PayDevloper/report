package com.sumao.report.summary.pojo;
/**订单状态的实体类
 * liutong
 * **/
public class OrderStateInfo {
	private String orderQuantity;//订单总数
	private String prepaidQuantity;//已支付订单总数
	private String tobepaidQuantity;//待支付订单总数
	private String cancelOrderQuantity;//取消订单总数
	public String getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(String orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	public String getPrepaidQuantity() {
		return prepaidQuantity;
	}
	public void setPrepaidQuantity(String prepaidQuantity) {
		this.prepaidQuantity = prepaidQuantity;
	}
	public String getTobepaidQuantity() {
		return tobepaidQuantity;
	}
	public void setTobepaidQuantity(String tobepaidQuantity) {
		this.tobepaidQuantity = tobepaidQuantity;
	}
	public String getCancelOrderQuantity() {
		return cancelOrderQuantity;
	}
	public void setCancelOrderQuantity(String cancelOrderQuantity) {
		this.cancelOrderQuantity = cancelOrderQuantity;
	}
	@Override
	public String toString() {
		return "OrderStateInfo [orderQuantity=" + orderQuantity + ", prepaidQuantity=" + prepaidQuantity
				+ ", tobepaidQuantity=" + tobepaidQuantity + ", cancelOrderQuantity=" + cancelOrderQuantity + "]";
	}


	
}

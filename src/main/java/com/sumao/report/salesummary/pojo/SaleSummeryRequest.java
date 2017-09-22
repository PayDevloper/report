package com.sumao.report.salesummary.pojo;

import com.sumao.report.pojo.PaginationRequest;
/**
 * 销售概览的参数实体类
 * @author liutong
 *
 */
public class SaleSummeryRequest extends PaginationRequest {

	private String	salercorp;//卖方公司
	private String	saleorganid;//销售组织编号
	private String	businessType;//企业类型
	private String	ordertype;//订单类型
	private String	orderstate;//订单状态
	private String	startDate;//开始时间
	private String	endDate;//结束时间
	public String getSalercorp() {
		return salercorp;
	}
	public void setSalercorp(String salercorp) {
		this.salercorp = salercorp;
	}
	public String getSaleorganid() {
		return saleorganid;
	}
	public void setSaleorganid(String saleorganid) {
		this.saleorganid = saleorganid;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getOrdertype() {
		return ordertype;
	}
	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}
	public String getOrderstate() {
		return orderstate;
	}
	public void setOrderstate(String orderstate) {
		this.orderstate = orderstate;
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
	@Override
	public String toString() {
		return "SaleSummeryRequest [salercorp=" + salercorp + ", saleorganid=" + saleorganid + ", businessType="
				+ businessType + ", ordertype=" + ordertype + ", orderstate=" + orderstate + ", startDate=" + startDate
				+ ", endDate=" + endDate + "]";
	}

}
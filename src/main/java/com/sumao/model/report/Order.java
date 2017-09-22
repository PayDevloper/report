package com.sumao.model.report;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
public class Order implements java.io.Serializable {

	private static final long serialVersionUID = -5090280456749526244L;

	// 交易类型
	private String dealType;

	// 订单号
	private String orderNo;

	// 产品名称
	private String productName;

	// 客户名称
	private String clientName;

	// 单价
	private String price;

	// 订单状态
	private String orderStatus;

	// 金额
	private String amount;

	// 订单最新修改时间
	private String lastModifiedDate;

	// 企业名称
	private String enterpriseName;

	// 企业部门
	private String enterpriseDepartment;

	// 省份
	private String province;

	// 城市
	private String city;

	// 区
	private String county;

	// 地址
	private String address;

	// 企业ID
	private String enterpriseId;

	public String getDealType() {
		return dealType;
	}

	public void setDealType(String dealType) {
		this.dealType = dealType;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getEnterpriseDepartment() {
		return enterpriseDepartment;
	}

	public void setEnterpriseDepartment(String enterpriseDepartment) {
		this.enterpriseDepartment = enterpriseDepartment;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	
}

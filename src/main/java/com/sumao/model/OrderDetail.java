package com.sumao.model;

/**
 * @author Liutong
 *  订单明细表的通用字段
 *         
 */
public class OrderDetail {
	private String orderid;
	private String ordertype;
	private String orderstate;
	private String totalsum;
	private String adjustreason;
	private String paymenttype;
	private String paymentremarks;
	private String checkstatus;
	private String enterpriseid;
	private String enterprisename;
	private String salerenterpriseid;
	private String salerenterprisename;
	private String submitteddate;
	private String orderer;
	private String salesman;
	private String Approvaler;
	private String adjustopinion;
	private String productid;
	private String category;
	private String gradenumber;
	private String warehouse;
	private String warehouseid;
	private String origprice;
	private String newprice;
	private String quantity;
	private String totalprice;
	private String carriage;
	/**
	 * 备用字段
	 * */
	private String col1;
	private String col2;
	private String col3;
	private String col4;
	private String col5;
	private String col6;
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
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
	public String getTotalsum() {
		return totalsum;
	}
	public void setTotalsum(String totalsum) {
		this.totalsum = totalsum;
	}
	public String getAdjustreason() {
		return adjustreason;
	}
	public void setAdjustreason(String adjustreason) {
		this.adjustreason = adjustreason;
	}
	public String getPaymenttype() {
		return paymenttype;
	}
	public void setPaymenttype(String paymenttype) {
		this.paymenttype = paymenttype;
	}
	public String getPaymentremarks() {
		return paymentremarks;
	}
	public void setPaymentremarks(String paymentremarks) {
		this.paymentremarks = paymentremarks;
	}
	public String getCheckstatus() {
		return checkstatus;
	}
	public void setCheckstatus(String checkstatus) {
		this.checkstatus = checkstatus;
	}
	public String getEnterpriseid() {
		return enterpriseid;
	}
	public void setEnterpriseid(String enterpriseid) {
		this.enterpriseid = enterpriseid;
	}
	public String getEnterprisename() {
		return enterprisename;
	}
	public void setEnterprisename(String enterprisename) {
		this.enterprisename = enterprisename;
	}
	public String getSalerenterpriseid() {
		return salerenterpriseid;
	}
	public void setSalerenterpriseid(String salerenterpriseid) {
		this.salerenterpriseid = salerenterpriseid;
	}
	public String getSalerenterprisename() {
		return salerenterprisename;
	}
	public void setSalerenterprisename(String salerenterprisename) {
		this.salerenterprisename = salerenterprisename;
	}
	public String getSubmitteddate() {
		return submitteddate;
	}
	public void setSubmitteddate(String submitteddate) {
		this.submitteddate = submitteddate;
	}
	public String getOrderer() {
		return orderer;
	}
	public void setOrderer(String orderer) {
		this.orderer = orderer;
	}
	public String getSalesman() {
		return salesman;
	}
	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}
	public String getApprovaler() {
		return Approvaler;
	}
	public void setApprovaler(String approvaler) {
		Approvaler = approvaler;
	}
	public String getAdjustopinion() {
		return adjustopinion;
	}
	public void setAdjustopinion(String adjustopinion) {
		this.adjustopinion = adjustopinion;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getGradenumber() {
		return gradenumber;
	}
	public void setGradenumber(String gradenumber) {
		this.gradenumber = gradenumber;
	}
	public String getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}
	public String getWarehouseid() {
		return warehouseid;
	}
	public void setWarehouseid(String warehouseid) {
		this.warehouseid = warehouseid;
	}
	public String getOrigprice() {
		return origprice;
	}
	public void setOrigprice(String origprice) {
		this.origprice = origprice;
	}
	public String getNewprice() {
		return newprice;
	}
	public void setNewprice(String newprice) {
		this.newprice = newprice;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(String totalprice) {
		this.totalprice = totalprice;
	}
	public String getCarriage() {
		return carriage;
	}
	public void setCarriage(String carriage) {
		this.carriage = carriage;
	}
	public String getCol1() {
		return col1;
	}
	public void setCol1(String col1) {
		this.col1 = col1;
	}
	public String getCol2() {
		return col2;
	}
	public void setCol2(String col2) {
		this.col2 = col2;
	}
	public String getCol3() {
		return col3;
	}
	public void setCol3(String col3) {
		this.col3 = col3;
	}
	public String getCol4() {
		return col4;
	}
	public void setCol4(String col4) {
		this.col4 = col4;
	}
	public String getCol5() {
		return col5;
	}
	public void setCol5(String col5) {
		this.col5 = col5;
	}
	public String getCol6() {
		return col6;
	}
	public void setCol6(String col6) {
		this.col6 = col6;
	}
	@Override
	public String toString() {
		return "OrderDetail [orderid=" + orderid + ", ordertype=" + ordertype + ", orderstate=" + orderstate
				+ ", totalsum=" + totalsum + ", adjustreason=" + adjustreason + ", paymenttype=" + paymenttype
				+ ", paymentremarks=" + paymentremarks + ", checkstatus=" + checkstatus + ", enterpriseid="
				+ enterpriseid + ", enterprisename=" + enterprisename + ", salerenterpriseid=" + salerenterpriseid
				+ ", salerenterprisename=" + salerenterprisename + ", submitteddate=" + submitteddate + ", orderer="
				+ orderer + ", salesman=" + salesman + ", Approvaler=" + Approvaler + ", adjustopinion=" + adjustopinion
				+ ", productid=" + productid + ", category=" + category + ", gradenumber=" + gradenumber
				+ ", warehouse=" + warehouse + ", warehouseid=" + warehouseid + ", origprice=" + origprice
				+ ", newprice=" + newprice + ", quantity=" + quantity + ", totalprice=" + totalprice + ", carriage="
				+ carriage + ", col1=" + col1 + ", col2=" + col2 + ", col3=" + col3 + ", col4=" + col4 + ", col5="
				+ col5 + ", col6=" + col6 + "]";
	}
	
}

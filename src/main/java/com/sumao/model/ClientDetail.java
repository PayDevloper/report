package com.sumao.model;

/**
 * @author Liutong
 *  客户明细表的通用字段
 *         
 */
public class ClientDetail {
	private String clientid;
	private String clientname;
	private String enterprisetype;
	private String quantity;
	private String totalprice;
	private String dealnum;
	/**
	 * 备用字段
	 * */
	private String col1;
	private String col2;
	private String col3;
	private String col4;
	private String col5;
	private String col6;
	public String getClientid() {
		return clientid;
	}
	public void setClientid(String clientid) {
		this.clientid = clientid;
	}
	public String getClientname() {
		return clientname;
	}
	public void setClientname(String clientname) {
		this.clientname = clientname;
	}
	public String getEnterprisetype() {
		return enterprisetype;
	}
	public void setEnterprisetype(String enterprisetype) {
		this.enterprisetype = enterprisetype;
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
	public String getDealnum() {
		return dealnum;
	}
	public void setDealnum(String dealnum) {
		this.dealnum = dealnum;
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
		return "ClientDetail [clientid=" + clientid + ", clientname=" + clientname + ", enterprisetype="
				+ enterprisetype + ", quantity=" + quantity + ", totalprice=" + totalprice + ", dealnum=" + dealnum
				+ ", col1=" + col1 + ", col2=" + col2 + ", col3=" + col3 + ", col4=" + col4 + ", col5=" + col5
				+ ", col6=" + col6 + "]";
	}
	
}

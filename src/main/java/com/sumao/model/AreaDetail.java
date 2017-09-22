package com.sumao.model;

/**
 * @author Liutong
 *  客户明细表的通用字段
 *         
 */
public class AreaDetail {
	private String province;
	private String city;
	private String producer;
	private String gradenumber;
	private String manufacturer;
	private String quantity;
	private String totalprice;
	private String averageprice;
	/**
	 * 备用字段
	 * */
	private String col1;
	private String col2;
	private String col3;
	private String col4;
	private String col5;
	private String col6;
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
	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}
	public String getGradenumber() {
		return gradenumber;
	}
	public void setGradenumber(String gradenumber) {
		this.gradenumber = gradenumber;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
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
	public String getAverageprice() {
		return averageprice;
	}
	public void setAverageprice(String averageprice) {
		this.averageprice = averageprice;
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
		return "AreaDetail [province=" + province + ", city=" + city + ", producer=" + producer + ", gradenumber="
				+ gradenumber + ", manufacturer=" + manufacturer + ", quantity=" + quantity + ", totalprice="
				+ totalprice + ", averageprice=" + averageprice + ", col1=" + col1 + ", col2=" + col2 + ", col3=" + col3
				+ ", col4=" + col4 + ", col5=" + col5 + ", col6=" + col6 + "]";
	}
	
}

package com.sumao.report.salesummary.pojo;

/**
 * @author Liutong
 *  销售概览实体类
 */
public class SaleSummeryElement {
	private String quantity;//销量
	private String totalprice;//金额
	/**
	 * 备用字段
	 * */
	private double remar1;
	private double remar2;
	private String remar3;
	private String remar4;
	private String remar5;
	private String remar6;
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
	public double getRemar1() {
		return remar1;
	}
	public void setRemar1(double remar1) {
		this.remar1 = remar1;
	}
	public double getRemar2() {
		return remar2;
	}
	public void setRemar2(double remar2) {
		this.remar2 = remar2;
	}
	public String getRemar3() {
		return remar3;
	}
	public void setRemar3(String remar3) {
		this.remar3 = remar3;
	}
	public String getRemar4() {
		return remar4;
	}
	public void setRemar4(String remar4) {
		this.remar4 = remar4;
	}
	public String getRemar5() {
		return remar5;
	}
	public void setRemar5(String remar5) {
		this.remar5 = remar5;
	}
	public String getRemar6() {
		return remar6;
	}
	public void setRemar6(String remar6) {
		this.remar6 = remar6;
	}
	@Override
	public String toString() {
		return "SaleSummeryElement [quantity=" + quantity + ", totalprice=" + totalprice + ", remar1=" + remar1
				+ ", remar2=" + remar2 + ", remar3=" + remar3 + ", remar4=" + remar4 + ", remar5=" + remar5
				+ ", remar6=" + remar6 + "]";
	}

	
}

package com.sumao.model;

/**
 * @author Liutong
 *  统计表的模型
 *         
 */
public class QuantityTop {
	/**序号*/
	private String serial;
	/**牌号*/
	private String gradeNumber;
	/**总销量*/
	private String totalQuantity;
	/**总金额*/
	private String totalPrice;
	/**平均*/
	private String average;
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public String getGradeNumber() {
		return gradeNumber;
	}
	public void setGradeNumber(String gradeNumber) {
		this.gradeNumber = gradeNumber;
	}
	public String getTotalQuantity() {
		return totalQuantity;
	}
	public void setTotalQuantity(String totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getAverage() {
		return average;
	}
	public void setAverage(String average) {
		this.average = average;
	}
	@Override
	public String toString() {
		return "QuantityTop [serial=" + serial + ", gradeNumber=" + gradeNumber + ", totalQuantity=" + totalQuantity
				+ ", totalPrice=" + totalPrice + ", average=" + average + "]";
	}
	
}

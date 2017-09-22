package com.sumao.model;

/**
 * @author Liutong
 *  统计表的模型
 *         
 */
public class ClientTop {
	/**序号*/
	private String serialc;
	/**客户名*/
	private String sort;
	/**总销量*/
	private String totalQuantityc;
	/**总金额*/
	private String totalPricec;
	/**平均*/
	private String averagec;
	public String getSerialc() {
		return serialc;
	}
	public void setSerialc(String serialc) {
		this.serialc = serialc;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getTotalQuantityc() {
		return totalQuantityc;
	}
	public void setTotalQuantityc(String totalQuantityc) {
		this.totalQuantityc = totalQuantityc;
	}
	public String getTotalPricec() {
		return totalPricec;
	}
	public void setTotalPricec(String totalPricec) {
		this.totalPricec = totalPricec;
	}
	public String getAveragec() {
		return averagec;
	}
	public void setAveragec(String averagec) {
		this.averagec = averagec;
	}
	@Override
	public String toString() {
		return "ClientTop [serialc=" + serialc + ", sort=" + sort + ", totalQuantityc=" + totalQuantityc
				+ ", totalPricec=" + totalPricec + ", averagec=" + averagec + "]";
	}
	
	
}

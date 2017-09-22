package com.sumao.report.skulocation.pojo;

import com.sumao.report.pojo.PaginationRequest;

public class SkuLocationRequest extends PaginationRequest {
	private String	startDate;
	private String	endDate;
	private String	grade;
	private String	province;
	private String	city;



	public String getGrade() {
		return grade;
	}



	public void setGrade(String grade) {
		this.grade = grade;
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

}

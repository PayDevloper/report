package com.sumao.report.summary.pojo;

import com.sumao.report.pojo.BaseRequest;

public class SummaryRequest extends BaseRequest {
	private String	startDate;
	private String	endDate;
	private String	grade;



	public String getGrade() {
		return grade;
	}



	public void setGrade(String grade) {
		this.grade = grade;
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
		return "SummaryRequest [startDate=" + startDate + ", endDate=" + endDate + ", grade=" + grade + "]";
	}

}

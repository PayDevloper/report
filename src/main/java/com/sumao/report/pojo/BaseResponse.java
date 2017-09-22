package com.sumao.report.pojo;

public class BaseResponse {
	private boolean	executionResult;
	private String	message;



	public boolean isExecutionResult() {
		return executionResult;
	}



	public void setExecutionResult(boolean executionResult) {
		this.executionResult = executionResult;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}
}

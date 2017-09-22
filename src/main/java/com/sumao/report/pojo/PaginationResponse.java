package com.sumao.report.pojo;

import java.util.List;

public class PaginationResponse<T> extends BaseResponse {
	private int		pageNum;
	private int		pageSize;
	private int		total;
	private List<T>	rows;



	public int getPageNum() {
		return pageNum;
	}



	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}



	public int getPageSize() {
		return pageSize;
	}



	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}



	public int getTotal() {
		return total;
	}



	public void setTotal(int total) {
		this.total = total;
	}



	public List<T> getRows() {
		return rows;
	}



	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}

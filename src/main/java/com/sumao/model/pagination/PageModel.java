package com.sumao.model.pagination;

//PageModel（包装当前页数和每页显示条数的pojo）
public class PageModel {
	private int pageNo = 1;// 当前页
	private int pageSize = 3;// 每页显示条数

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}

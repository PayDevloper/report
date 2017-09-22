package com.sumao.model.pagination;

import java.io.Serializable;
import java.util.List;

public class ReturnPageModel<T> implements Serializable {
	private static final long serialVersionUID = -234567743211L;
	private int count;// 总数
	private int end;// 结束的行数
	private long pages;// 页数
	private int pageSize;// 每页显示行数
	private List<T> list; // 返回数据
	private int start;// 开始行数
	private long currentPage;// 当前页

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public long getPages() {
		return pages;
	}

	public void setPages(long pages) {
		this.pages = pages;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public long getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(long currentPage) {
		this.currentPage = currentPage;
	}

}
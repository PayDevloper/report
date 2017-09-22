package com.sumao.model.pagination;

import java.util.Map;

//包装查询条件，查询实体名字，排序等的POJO）
public class QueryModel {
	private String tableName;// 实体名字

	private Map<String, Object> map;// 查询条件

	private String orderColumn;// 排序字段

	private String orderType;// 排序方式，比如：desc，asc

	private PageModel pageModel;// 包装了当前页码和每页显示条数

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public String getOrderColumn() {
		return orderColumn;
	}

	public void setOrderColumn(String orderColumn) {
		this.orderColumn = orderColumn;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public PageModel getPageModel() {
		return pageModel;
	}

	public void setPageModel(PageModel pageModel) {
		this.pageModel = pageModel;
	}

}

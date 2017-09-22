package com.sumao.model;

import java.util.List;
import com.sumao.model.ClientTop;
/**
 * 后台向前台返回JSON，用于easyui的datagrid
 * 
 * @author 陈小俊
 * 
 */
public class EasyuiDataGridJson implements java.io.Serializable {

	private static final long serialVersionUID = -6071424965676236737L;
	private Long total;// 总记录数
	private List rows;// 每行记录
	private List footer;
	
	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public List getFooter() {
		return footer;
	}

	public void setFooter(List footer) {
		this.footer = footer;
	}

	@Override
	public String toString() {
		return "EasyuiDataGridJson [total=" + total + ", rows=" + rows + ", footer=" + footer + "]";
	}


	
}

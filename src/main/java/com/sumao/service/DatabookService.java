package com.sumao.service;

import com.sumao.model.EasyuiDataGrid;
import com.sumao.model.EasyuiDataGridJson;
import com.sumao.model.Paysub;

public interface DatabookService {
	/** 执行sql语句，显示数据字典的数据总数，params是查询信息 */
	public int DataDicNum(Paysub payr);
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg, Paysub payr, int total);
	public boolean edit(com.sumao.model.Paysub payr);
	public boolean add(com.sumao.model.Paysub payr);
	public boolean del(String ids);

}
package com.sumao.service;

import java.util.List;

import com.sumao.model.EasyuiDataGrid;
import com.sumao.model.EasyuiDataGridJson;
import com.sumao.model.Paysub;

/**
 * 岗位管理Service1.0
 * 
 * @author liutong
 * 
 */
public interface AuthManageService {

	/**
	 * 数据表格
	 * 
	 * @param dg
	 * @param payr
	 * @param int
	 * @return
	 */
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg, Paysub payr, int total);

	/**
	 * 数据表格翻页查询
	 * 
	 * @param Pay_userorg
	 *            payr
	 * @return int
	 */
	public int findTotal(Paysub payr);

	/**
	 * 新建保存信息
	 * 
	 * @param Paysub
	 *            payr
	 * @return
	 */
	public boolean add(Paysub payr);

	/**
	 * 编辑保存信息
	 * 
	 * @param Paysub
	 *            payr
	 * @return
	 */
	public boolean edit(Paysub payr);

	/**
	 * 删除信息
	 * 
	 * @param ids
	 * @return
	 */
	public boolean del(String ids);

	/**
	 * 选择岗位查询
	 * 
	 * @param Pay_userorg
	 *            payr
	 * @return list
	 */
	public List<Paysub> findposition(Paysub basein);
}

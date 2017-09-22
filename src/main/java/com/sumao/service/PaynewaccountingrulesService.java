package com.sumao.service;

import com.sumao.model.EasyuiDataGrid;
import com.sumao.model.EasyuiDataGridJson;
import com.sumao.model.Paynewaccountingrules;

/**
 * 计费规则创建Service
 * @author heijj
 * 
 */
public interface PaynewaccountingrulesService  {

	/**
	 * 数据表格
	 * @param EasyuiDataGrid
	 * @param Paynewaccountingrules
	 * @param int 
	 * @author heijj
	 * @return EasyuiDataGridJson datagrid
	 */
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg, Paynewaccountingrules payr,int total);

	/**
	 * 新建保存信息
	 * @param Paynewaccountingrules payr
	 * @return
	 */
	public boolean add(Paynewaccountingrules payr);

	/**
	 * 编辑保存信息
	 * @param Paynewaccountingrules payr
	 * @return
	 */
	public boolean edit(Paynewaccountingrules payr);

	/**
	 * 删除信息
	 * @param ids
	 * @return
	 */
	public boolean del(String ids);
	
	/**
	 * 批量审核功能
	 * @param Paynewaccountingrules payr
	 * @return
	 */
	public boolean editaudit(Paynewaccountingrules payr);
	
	/**
	 * 根据组织机构ID，更新信息“计费配置状态”状态为"已创建"
	 * @param Paynewaccountingrules payr
	 * @return
	 */
	public boolean editNewAccountByID(Paynewaccountingrules payr);
	
	/**
	 * 数据表格翻页
	 * @param Paynewaccountingrules payr
	 * @return int
	 */
	public int findPayrTotal(Paynewaccountingrules payr);
	
	/**
	 * 根据销售组织ID值，得到计费规则创建数据
	 * @param id
	 * @return Paynewaccountingrules
	 */
	public Paynewaccountingrules selectByPrimaryKey(String id);

}

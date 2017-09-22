package com.sumao.service;

import com.sumao.model.EasyuiDataGrid;
import com.sumao.model.EasyuiDataGridJson;
import com.sumao.model.Paytotal;

/**
 * 总项管理Service
 * @author heijj
 * 
 */
public interface PayTotalService  {

	/**
	 * 总项表格
	 * @param dg
	 * @param Paytotal payr
	 * @return
	 */
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg, Paytotal payr,int total);

	/**
	 * 新建保存信息
	 * @param Paytotal payr
	 * @return
	 */
	public boolean add(Paytotal payr);

	/**
	 * 编辑保存信息
	 * @param Paytotal payr
	 * @return
	 */
	public boolean edit(Paytotal payr);

	/**
	 * 删除信息
	 * @param ids
	 * @return
	 */
	public boolean del(String ids);
	

	/**
	 * 删除子项信息
	 * @param ids
	 * @return
	 */
	public boolean delSubByPrimaryKey(String id);
	
	/**
	 * 删除保证金设定信息
	 * @param ids
	 * @return
	 */
	public boolean deBondByPrimaryKey(String id);
	
	/**
	 * 删除权利金设定信息
	 * @param ids
	 * @return
	 */
	public boolean delRightByPrimaryKey(String id);
	
	/**
	 * 批量审核功能
	 * @param ids
	 * @return
	 */
	public boolean editaudit(String ids);
	
	/**
	 * 数据表格翻页查询
	 * @param Pay_userorg payr
	 * @return int
	 */
	public int findTotal(Paytotal payr);
	
	/**
	 * 翻页查询
	 * @param Pay_userorg payr
	 * @return int
	 */
	public int findSelTotal(Paytotal payr);


}

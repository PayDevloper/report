package com.sumao.service;

import java.util.List;

import com.sumao.model.EasyuiDataGrid;
import com.sumao.model.EasyuiDataGridJson;
import com.sumao.model.Paysub;
import com.sumao.model.Pay_userorg;
import com.sumao.model.Payeditaccountingrules;

/**
 * 子项管理Service
 * 
 * @author heijj
 * 
 */
public interface PaySubService  {

	/**
	 * 数据表格
	 * @param dg
	 * @param payr
	 * @param int
	 * @return
	 */
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg, Paysub payr,int total);
	
	/**
	 * 数据选择权利金子项表格
	 * @param dg
	 * @param Paysub payr
	 * @param int
	 * @author heijj
	 * @return
	 */
	public EasyuiDataGridJson datagridsel(EasyuiDataGrid dg, Paysub payr,int total);
	
	/**
	 * 数据选择保证金子项表格
	 * @param dg
	 * @param Paysub payr
	 * @param int
	 * @author heijj
	 * @return
	 */
	public EasyuiDataGridJson datagridselbond(EasyuiDataGrid dg, Paysub payr,int total);
	
	/**
	 * 销售组织配置数据表格
	 * @param dg
	 * @param Pay_userorg payr
	 * @param int
	 * @author heijj
	 * @return
	 */
	public EasyuiDataGridJson datagriduserorg(EasyuiDataGrid dg, Pay_userorg payr,int total);

	/**
	 * 新建保存信息
	 * @param Paysub payr
	 * @return
	 */
	public boolean add(Paysub payr);

	/**
	 * 编辑保存信息
	 * @param Paysub payr
	 * @return
	 */
	public boolean edit(Paysub payr);

	/**
	 * 删除信息
	 * @param ids
	 * @return
	 */
	public boolean del(String ids);
	
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
	 * 数据销售组织配置翻页查询
	 * @param Pay_userorg payr
	 * @return int
	 */
	public int findPayrTotal(Pay_userorg payr);
	
	/**
	 * 数据选择权利金子项表格翻页查询
	 * @param Pay_userorg payr
	 * @return int
	 */
	public int findSelTotal(Paysub payr);
	
	/**
	 * 数据选择保证金子项表格翻页查询
	 * @param Pay_userorg payr
	 * @return int
	 */
	public int findSelbondTotal(Paysub payr);
	
	/**
	 * 数据表格翻页查询
	 * @param Pay_userorg payr
	 * @return int
	 */
	public int findTotal(Paysub payr);
	
	/**
	 * 根据销售组织id，查询是否已存在该计费规则
	 * @param mkid
	 * @return int
	 */
	public int getprovingmkid(String mkid);
	
	/**
	 * @param String
	 * 返回页面的json,返回值是卖方名称信息的json
	 * @return List<Pay_userorg>
	 */
	public List<Pay_userorg> showSeller();
	
	/**
	 * @param String
	 * 返回页面的json,返回值是销售组织信息的json
	 * @return List<Pay_userorg>
	 */
	public List<Pay_userorg> selSeller(String sellername);

}

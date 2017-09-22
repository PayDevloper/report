package com.sumao.service;

import java.util.List;

import com.sumao.model.ComTable;
import com.sumao.model.EasyuiDataGrid;
import com.sumao.model.EasyuiDataGridJson;
import com.sumao.model.Pay_rightgold;
import com.sumao.model.Pay_rightgoldcheck;
import com.sumao.model.Pay_userorg;

/**
 * 权利金设定Service
 * @author heijj
 * 
 */
public interface PayRightService  {

	/**
	 * 权利金设定
	 * @param EasyuiDataGrid
	 * @param Pay_rightgold
	 * @param int 
	 * @author heijj
	 * @return EasyuiDataGridJson datagrid
	 */
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg, Pay_rightgold payr,int total);

	/**
	 * 新建保存信息
	 * @param Pay_rightgold payr
	 * @return
	 */
	public boolean add(Pay_rightgold payr);

	/**
	 * 编辑保存信息
	 * @param Pay_rightgold payr
	 * @return
	 */
	public boolean edit(Pay_rightgold payr);

	/**
	 * 删除用户
	 * @param ids
	 * @return
	 */
	public boolean del(String ids);
	
	/**
	 * 批量审核功能
	 * @param Pay_rightgold
	 * @return
	 */
	public boolean editaudit(Pay_rightgold payr);
	
	/**
	 *更新权利金设定中是否缴费
	 *@param Pay_rightgold
	 *@author heijj
	 */
	public boolean editBondByID(Pay_rightgold payr);

	/**
	 * 根据权利金设定的ID值，得到数据，用于自动带入“费用支付”新建页面内容
	 * @param id
	 * @return Pay_rightgold
	 */
	public Pay_rightgold selectByPrimaryKey(String id);
	
	/**
	 * 根据销售组织ID，得到销售组织，卖方等信息
	 * @param marketingid
	 * @return
	 */
	public Pay_userorg getUserorgBy(String marketingid);
	
	/**
	 * 数据表格翻页
	 * @param Pay_rightgold payr
	 * @return int
	 */
	public int findPayrTotal(Pay_rightgold payr);
	
	/**
	 * 根据销售组织以及子项配置，验证是否已通过验证
	 * @param payr
	 * @return
	 */
	public List<Pay_rightgold> getproving(Pay_rightgold payr);
	
	/**
	 * 数据表格翻页
	 * @param Pay_rightgold payr
	 * @return int
	 */
	public int findPayrchTotal(Pay_rightgoldcheck payr);
	
	/**
	 * 卖家中心--权利金缴费查看列表展示
	 * @author heijj
	 * @param startPos
	 * @param pageSize
	 * @param payr
	 * @return
	 */
	public List<Pay_rightgoldcheck> showOrderByPage(int pageNum, int pageSize,Pay_rightgoldcheck payr);
	
	/**
	 * 权利金费用查看
	 * @param EasyuiDataGrid
	 * @param Pay_rightgold
	 * @param int 
	 * @author heijj
	 * @return EasyuiDataGridJson datagrid
	 */
	public EasyuiDataGridJson datagridch(EasyuiDataGrid dg, Pay_rightgoldcheck payr,int total);
	
	/**
	 * 得到权利金子项信息
	 * @return
	 */
	public List<Pay_rightgoldcheck> selectgoldsub();
	
	/**
	 * 数据表格翻页
	 * @param Pay_rightgold payr
	 * @return int
	 */
	public int findPayallchTotal(Pay_rightgoldcheck payr);
	
	/**
	 * 权利金全部费用查看
	 * @param EasyuiDataGrid
	 * @param Pay_rightgold
	 * @param int 
	 * @author heijj
	 * @return EasyuiDataGridJson datagrid
	 */
	public EasyuiDataGridJson datagridallch(EasyuiDataGrid dg, Pay_rightgoldcheck payr,int total);
	
	//==========================================================================================================
	/**
	 * 卖方中心接口调用
	 * @param payr
	 * @return
	 */
		public String getqueryQlj(Pay_rightgoldcheck payr);

}

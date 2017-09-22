package com.sumao.service;

import java.util.List;

import com.sumao.model.EasyuiDataGrid;
import com.sumao.model.EasyuiDataGridJson;
import com.sumao.model.Pay_rightgoldcheck;
import com.sumao.model.Paybond;

/**
 * 保证金设定Service
 * 
 * @author heijj
 * 
 */
public interface PaybondService  {

	/**
	 * 保证金设定表格
	 * @param EasyuiDataGrid
	 * @param Paybond
	 * @param int 
	 * @author heijj
	 * @return EasyuiDataGridJson datagrid
	 */
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg, Paybond payr,int total);
	
	/**
	 * 保证金设定历史记录表格
	 * @param EasyuiDataGrid
	 * @param Paybond
	 * @param int 
	 * @author heijj
	 * @return EasyuiDataGridJson datagrid
	 */
	public EasyuiDataGridJson datagridHis(EasyuiDataGrid dg, Paybond payr,int total);

	/**
	 * 新建保存信息
	 * @param Paybond payr
	 * @return
	 */
	public boolean add(Paybond payr);

	/**
	 * 编辑保存信息
	 * @param Paybond payr
	 * @return
	 */
	public boolean edit(Paybond payr);

	/**
	 * 删除信息
	 * @param ids
	 * @return
	 */
	public boolean del(String ids);
	
	/**
	 * 批量审核功能
	 * @param Paybond payr
	 * @return
	 */
	public boolean editaudit(Paybond payr);
	
	/**
	 * 根据设定信息编号，更新设定信息“是否缴费”状态，如果是保证金支付，则增加“现有保证金”数量
	 * @param Paybond payr
	 * @return
	 */
	public boolean editBondByID(Paybond payr);

	/**
	 * 根据保证金设定的ID值，得到数据，用于自动带入“费用支付”新建页面内容
	 * @param id
	 * @return Paybond
	 */
	public Paybond selectByPrimaryKey(String id);
	
	/**
	 * 根据保证金设定中的销售组织ID值，得到数据
	 * @param String Marketingid
	 * @return Paybond
	 */
	public Paybond selectByMarketingid(String id);


	/**
	 * 数据表格翻页
	 * @param payr
	 * @return int
	 */
	public int findPayrTotal(Paybond payr);
	
	/**
	 * 历史记录数据表格翻页
	 * @param payr
	 * @return int
	 */
	public int findPayrHisTotal(Paybond payr);
	

	/**
	 * 卖家中心--费用明细列表展示
	 * @author heijj
	 * @param startPos
	 * @param pageSize
	 * @param payr
	 * @return
	 */
	public List<Paybond> showOrderByPage(int pageNum, int pageSize,Paybond payr);
	
	
	/**
	 * 保证金设定编辑保存历史记录
	 * @param Paybond payr
	 * @return boolean
	 */
	public boolean editHistory(Paybond payr);
	
	/**
	 * 新建保存历史信息，用于卖家查看扣款明细
	 * @param Paybond payr
	 * @return
	 */
	public boolean addHistory(Paybond payr);
	
	/**
	 * 根据销售组织ID，得到保证金设定中的唯一编号，用于保证金设定历史记录的保存。
	 * @param Paybond payr
	 * @author heijj
	 * @return Paybond
	 */
	public Paybond findBondByMID(Paybond payr);
	

}

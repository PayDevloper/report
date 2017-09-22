package com.sumao.service;

import java.util.List;

import com.sumao.model.EasyuiDataGrid;
import com.sumao.model.EasyuiDataGridJson;
import com.sumao.model.Pay_rightgoldcheck;
import com.sumao.model.Paycostpay;

/**
 * 费用支付Service
 * 
 * @author heijj
 * 
 */
public interface PaycostpayService  {

	/**
	 * 费用支付表格
	 * @param EasyuiDataGrid
	 * @param Paycostpay
	 * @param int 
	 * @author heijj
	 * @return EasyuiDataGridJson datagrid
	 */
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg, Paycostpay payr,int total);

	/**
	 * 新建保存信息
	 * @param Paycostpay payr
	 * @return
	 */
	public boolean add(Paycostpay payr);

	/**
	 * 编辑保存信息
	 * @param Paycostpay payr
	 * @return
	 */
	public boolean edit(Paycostpay payr);

	/**
	 * 删除信息
	 * @param ids
	 * @return
	 */
	public boolean del(String ids);
	
	/**
	 * 批量审核（此方法暂未使用）
	 * @param ids
	 * @return
	 */
	public boolean editaudit(String ids);

	/**
	 * 根据费用支付数据的ID，得到相应信息
	 * @param String id
	 * @author heijj
	 * @return Paycostpay 
	 */
	public Paycostpay selectByPrimaryKey(String id);

	/**
	 * 数据表格翻页
	 * @param Paycostpay payr
	 * @return int
	 */
	public int findPayrTotal(Paycostpay payr);
	
	/**
	 * 卖家中心--保证金缴费查看数据表格翻页
	 * @param Paycostpay payr
	 * @return int
	 */
	public int findPayrcostTotal(Paycostpay payr);
	
	/**
	 * 卖家中心--保证金缴费查看列表展示
	 * @author heijj
	 * @param startPos
	 * @param pageSize
	 * @param payr
	 * @return
	 */
	public List<Paycostpay> showOrderByPage(int pageNum, int pageSize,Paycostpay payr);
	
	
	/**
	 * 卖家中心--保证金缴费查看表格
	 * @param EasyuiDataGrid
	 * @param Paycostpay
	 * @param int 
	 * @author heijj
	 * @return EasyuiDataGridJson datagrid
	 */
	public EasyuiDataGridJson datagridcost(EasyuiDataGrid dg, Paycostpay payr,int total);
	
	/**
	 * 数据表格翻页
	 * @param Paycostpay payr
	 * @return int
	 */
	public int findPayallchTotal(Paycostpay payr);
	
	/**
	 * 保证金全部费用查看
	 * @param EasyuiDataGrid
	 * @param Paycostpay
	 * @param int 
	 * @author heijj
	 * @return EasyuiDataGridJson datagrid
	 */
	public EasyuiDataGridJson datagridallch(EasyuiDataGrid dg, Paycostpay payr,int total);
	
	/**
	 * 得到保证金子项信息
	 * @return
	 */
	public List<Paycostpay> selectgoldsub();
	
	/**
	 * 根据是否为权利金以及缴费编号，查询是否已创建
	 * @param String
	 * @return int
	 */
	public int getprovingused(String paymentcode);

}

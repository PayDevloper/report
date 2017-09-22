package com.sumao.service;

import java.util.List;
import com.sumao.model.EasyuiDataGrid;
import com.sumao.model.EasyuiDataGridJson;
import com.sumao.model.Pay_userorg;
import com.sumao.model.Payeditaccountingrules;

/**
 * 计费规则管理Service
 * 
 * @author heijj
 * 
 */
public interface PayeditaccountingrulesService  {

	/**
	 * 数据表格
	 * @param dg
	 * @param Payeditaccountingrules payr
	 * @param int total
	 * @return
	 */
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg, Payeditaccountingrules payr,int total);

	/**
	 * 新建保存信息
	 * @param payr
	 * @return
	 */
	public boolean add(Payeditaccountingrules payr);

	/**
	 * 编辑保存信息
	 * @param payr
	 * @return
	 */
	public boolean edit(Payeditaccountingrules payr);

	/**
	 * 删除信息
	 * @param ids
	 * @return
	 */
	public boolean del(String ids);
	
	/**
	 * 批量审核功能
	 * @param payr
	 * @return
	 */
	public boolean editaudit(Payeditaccountingrules payr);
	

	/**
	 * 根据计费规则管理的ID值，得到数据
	 * @param id
	 * @return Payeditaccountingrules
	 */
	public Payeditaccountingrules selectByPrimaryKey(String id);

	/**
	 * 数据表格翻页
	 * @param payr
	 * @return int
	 */
	public int findPayrTotal(Payeditaccountingrules payr);

	/**
	 * 计费规则编辑保存历史记录
	 * @param Payeditaccountingrules payr
	 * @return boolean
	 */
	public boolean editHistory(Payeditaccountingrules payr);
	
	/**
	 * 查询符合输入参数的规则
	 * @param String
	 * @return int
	 */
	public List<Payeditaccountingrules> findrule(String buyerordernum,String buytime);
	
	/**
	 * 自动运行，查询符合输入参数的计费规则
	 * @param String tradingpatterns,String marketingid,String buytime
	 * @return List<Payeditaccountingrules>
	 */
	public List<Payeditaccountingrules> findsellerrule(String tradingpatterns,String marketingid,String buytime,String buyerordernum);
	
	
	/**
	 * 自动运行，查询符合输入参数的退费规则
	 * @param String buytime
	 * @return List<Payeditaccountingrules>
	 */
	public List<Payeditaccountingrules> findRefundrule(String buytime);
	
	/**
	 * 根据销售组织以及配置，验证是否时间交叉
	 * @param paye
	 * @return
	 */
	public int getprovingtime(Payeditaccountingrules paye);
	
	/**
	 * 根据销售组织以及配置，验证是否阈值交叉
	 * @param paye
	 * @return
	 */
	public int getprovingshold(Payeditaccountingrules paye);
	
	/**
	 * 计费规则查询数据表格
	 * @param dg
	 * @param Payeditaccountingrules payr
	 * @param int total
	 * @return
	 */
	public EasyuiDataGridJson datagridseach(EasyuiDataGrid dg, Payeditaccountingrules payr,int total);
	
	/**
	 * 计费规则查询数据表格翻页
	 * @param payr
	 * @return int
	 */
	public int findPayseachTotal(Payeditaccountingrules payr);
	
	/**
	 * 数据产品牌号翻页查询
	 * @param Payeditaccountingrules payr
	 * @return int
	 */
	public int findPaybrandTotal(Payeditaccountingrules payr);
	
	/**
	 * 产品牌号数据表格
	 * @param dg
	 * @param Pay_userorg payr
	 * @param int
	 * @author heijj
	 * @return
	 */
	public EasyuiDataGridJson datagridbrand(EasyuiDataGrid dg, Payeditaccountingrules payr,int total);
	
	/**
	 * 返回页面的json,返回值是牌号大类信息的json
	 * @return List<Payeditaccountingrules>
	 */
	public List<Payeditaccountingrules> findoneebs(String marketingid);
	
	/**
	 * @param String
	 * 返回页面的json,返回值是牌号中类信息的json
	 * @return List<Payeditaccountingrules>
	 */
	public List<Payeditaccountingrules> findtwoebs(String marketingid,String ebsname);
	
	/**
	 * @param String
	 * 返回页面的json,返回值是牌号小类信息的json
	 * @return List<Payeditaccountingrules>
	 */
	public List<Payeditaccountingrules> findthreeebs(String marketingid,String ebsname);
	
	/**
	 * @param String
	 * 返回页面的json,返回值是牌号信息的json
	 * @return List<Payeditaccountingrules>
	 */
	public List<Payeditaccountingrules> findgradeebs(String marketingid,String ebsname);

}

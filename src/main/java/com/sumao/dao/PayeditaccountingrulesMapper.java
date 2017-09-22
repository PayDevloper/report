package com.sumao.dao;

import java.util.List;
import java.util.Map;

import com.sumao.model.EasyuiDataGrid;
import com.sumao.model.EasyuiDataGridJson;
import com.sumao.model.Pay_userorg;
import com.sumao.model.Payeditaccountingrules;

/**
 * 计费规则管理DAO
 * 
 * @author heijj
 * 
 */
public interface PayeditaccountingrulesMapper {
	/**
	 * 删除信息
	 * @param ids
	 * @return
	 */
	public boolean deleteByPrimaryKey(String id);
	
	/**
	 * 批量审核功能
	 * @param payr
	 * @return
	 */
	public boolean editaudit(Payeditaccountingrules payr);

	/**
	 * 新建保存信息
	 * @param payr
	 * @return
	 */
	public boolean insert(Payeditaccountingrules payr);

	/**
	 * 根据计费规则管理的ID值，得到数据
	 * @param id
	 * @return Payeditaccountingrules
	 */
	public Payeditaccountingrules selectByPrimaryKey(String id);

	/**
	 * 编辑保存信息
	 * @param payr
	 * @return
	 */
	public boolean updateByPrimaryKey(Payeditaccountingrules payr);

	/**
	 * 根据条件查询信息
	 * @author heijj
	 * @param payr
	 * @return  List<Payeditaccountingrules>
	 */
	public List<Payeditaccountingrules> findPayrList(Payeditaccountingrules payr);
	
	/**
	 * 分页查询
	 * @param Payeditaccountingrules payr
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
	 * @param id
	 * @return List<Payeditaccountingrules>
	 */
	public List<Payeditaccountingrules> findrule(Map<String, Object> map);
	
	/**
	 * 自动运行，查询符合输入参数的计费规则
	 * @param Map
	 * @return List<Payeditaccountingrules>
	 */
	public List<Payeditaccountingrules> findsellerrule(Map<String, Object> map);
	
	/**
	 * 自动运行，查询符合输入参数的退费规则
	 * @param Map
	 * @return List<Payeditaccountingrules>
	 */
	public List<Payeditaccountingrules> findRefundrule(Map<String, Object> map);
	
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
	public List<Payeditaccountingrules> findPayseachList(Payeditaccountingrules payr);
	
	/**
	 * 计费规则查询数据表格翻页
	 * @param payr
	 * @return int
	 */
	public int findPayseachTotal(Payeditaccountingrules payr);
	
	/**
	 * 产品牌号翻页查询
	 * @author heijj
	 * @param Payeditaccountingrules payr
	 * @return  List<Payeditaccountingrules>
	 */
	public List<Payeditaccountingrules> findPayselbrandList(Payeditaccountingrules payr);
	
	/**
	 * 产品牌号翻页查询
	 * @param Payeditaccountingrules payr
	 * @return int
	 */
	public int findPaybrandTotal(Payeditaccountingrules payr);
	
	/**
	 * 返回页面的json,返回值是牌号大类信息的json
	 * @return List<Payeditaccountingrules>
	 */
	public List<Payeditaccountingrules> findoneebs(Map<String, Object> map);
	
	/**
	 * @param String
	 * 返回页面的json,返回值是牌号中类信息的json
	 * @return List<Payeditaccountingrules>
	 */
	public List<Payeditaccountingrules> findtwoebs(Map<String, Object> map);
	
	/**
	 * @param String
	 * 返回页面的json,返回值是牌号小类信息的json
	 * @return List<Payeditaccountingrules>
	 */
	public List<Payeditaccountingrules> findthreeebs(Map<String, Object> map);
	
	/**
	 * @param String
	 * 返回页面的json,返回值是牌号信息的json
	 * @return List<Payeditaccountingrules>
	 */
	public List<Payeditaccountingrules> findgradeebs(Map<String, Object> map);
}
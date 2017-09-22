package com.sumao.dao;

import com.sumao.model.Pay_rightgold;
import com.sumao.model.Pay_rightgoldcheck;
import java.util.List;
import com.sumao.model.Pay_userorg;

public interface Pay_rightgoldMapper {
	
	/**
	 * 删除用户
	 * @param ids
	 * @return
	 */
	public boolean deleteByPrimaryKey(String id);
	
	/**
	 * 批量审核功能
	 * @param ids
	 * @return
	 */
	public boolean editaudit(Pay_rightgold payr);
	
	/**
	 *更新权利金设定中是否缴费
	 *@param Pay_rightgold
	 *@author heijj
	 *@return boolean
	 */
	public boolean editBondByID(Pay_rightgold payr);
	
	/**
	 *根据销售组织ID得到卖方，销售组织信息
	 *@param String marketingid
	 *@return Pay_userorg
	 *@author heijj
	 */
	public Pay_userorg getUserorgBy(String marketingid);

	/**
	 * 新建保存信息
	 * @param Pay_rightgold payr
	 * @return
	 */
	public boolean insert(Pay_rightgold payr);

	/**
	 * 根据权利金设定数据的ID，查询设定信息，用于费用支付功能
	 * @param String id
	 * @author heijj
	 * @return Pay_rightgold 
	 */
	public Pay_rightgold selectByPrimaryKey(String id);

	/**
	 * 编辑保存信息
	 * @param Pay_rightgold payr
	 * @return
	 */
	public boolean updateByPrimaryKey(Pay_rightgold payr);

	/**
	 * 根据条件查询信息
	 * @author heijj
	 * @param payr
	 * @return  List<pay_rightgold>
	 */
	public List<Pay_rightgold> findPayrList(Pay_rightgold payr);

	/**
	 * 分页查询
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
	 * 分页查询
	 * @param Pay_rightgold payr
	 * @return int
	 */
	public int findPayrchTotal(Pay_rightgoldcheck payr);
	
	/**
	 * 根据条件查询信息
	 * @author heijj
	 * @param payr
	 * @return  List<Pay_rightgoldcheck>
	 */
	public List<Pay_rightgoldcheck> findPayrchList(Pay_rightgoldcheck payr);
	
	/**
	 * 得到权利金子项信息
	 * @return
	 */
	public List<Pay_rightgoldcheck> selectgoldsub();
	
	/**
	 * 分页查询
	 * @param Pay_rightgold payr
	 * @return int
	 */
	public int findPayallchTotal(Pay_rightgoldcheck payr);
	
	/**
	 * 根据条件查询信息
	 * @author heijj
	 * @param payr
	 * @return  List<Pay_rightgoldcheck>
	 */
	public List<Pay_rightgoldcheck> findPayallchList(Pay_rightgoldcheck payr);
	
}
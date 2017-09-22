package com.sumao.dao;

import java.util.List;

import com.sumao.model.Paybond;

/**
 * 保证金设定Dao
 * 
 * @author heijj
 * 
 */
public interface PaybondMapper {
	/**
	 * 删除信息
	 * @param ids
	 * @return
	 */
	public boolean deleteByPrimaryKey(String id);
	
	/**
	 * 批量审核功能
	 * @param Paybond payr
	 * @return
	 */
	public boolean editaudit(Paybond payr);
	
	/**
	 *更新保证金设定中是否缴费及现有保证金金额
	 *@param Paybond
	 *@author heijj
	 */
	public boolean editBondByID(Paybond payr);

	/**
	 * 新建保存信息
	 * @param Paybond payr
	 * @return
	 */
	public boolean insert(Paybond payr);

	/**
	 * 根据保证经设定数据的ID，查询设定信息，用于费用支付功能
	 * @param String id
	 * @author heijj
	 * @return Paybond 
	 */
	public Paybond selectByPrimaryKey(String id);
	
	/**
	 * 根据保证金设定中的销售组织ID值，得到数据
	 * @param String Marketingid
	 * @author heijj
	 * @return Paybond 
	 */
	public Paybond selectByMarketingid(String id);

	/**
	 * 编辑保存信息
	 * @param Paybond payr
	 * @return
	 */
	public boolean updateByPrimaryKey(Paybond payr);

	/**
	 * 根据条件查询信息,不分页
	 * @author heijj
	 * @param payr
	 * @return  List<Paybond>
	 */
	public List<Paybond> findPayrList(Paybond payr);
	
	/**
	 * 分页查询
	 * @param Paybond payr
	 * @return int
	 */
	public int findPayrTotal(Paybond payr);
	
	/**
	 * 保证金设定编辑保存历史记录
	 * @param Paybond payr
	 * @return boolean
	 */
	public boolean editHistory(Paybond payr);
	
	/**
	 * 新建保存历史信息，用于卖家查看扣款明细
	 * @param Paybond payr
	 * @return boolean
	 */
	public boolean insertHistory(Paybond payr);
	
	/**
	 * 根据销售组织ID，得到保证金设定中的唯一编号，用于保证金设定历史记录的保存。
	 * @param Paybond payr
	 * @author heijj
	 * @return Paybond
	 */
	public Paybond findBondByMID(Paybond payr);
	

	/**
	 * 根据条件查询历史记录信息,不分页
	 * @author heijj
	 * @param payr
	 * @return  List<Paybond>
	 */
	public List<Paybond> findPayrHisList(Paybond payr);
	
	/**
	 * 历史记录分页查询
	 * @param Paybond payr
	 * @return int
	 */
	public int findPayrHisTotal(Paybond payr);
	
}
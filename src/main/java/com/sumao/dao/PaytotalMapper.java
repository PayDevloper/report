package com.sumao.dao;

import java.util.List;

import com.sumao.model.Paysub;
import com.sumao.model.Paytotal;

/**
 * 总项管理DAO
 * @author heijj
 *
 */
public interface PaytotalMapper {
	
	/**
	 * 删除信息
	 * @param ids
	 * @return
	 */
	public boolean deleteByPrimaryKey(String id);
	
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
	public boolean editaudit(String id);

	/**
	 * 新建保存信息
	 * @param Paytotal payr
	 * @return
	 */
	public boolean insert(Paytotal payr);

	/**
	 * 根据ID，得到总项数据(暂未使用)
	 * @param id
	 * @return Paytotal
	 */
	public Paytotal selectByPrimaryKey(String id);

	/**
	 * 编辑保存信息
	 * @param Paytotal payr
	 * @return
	 */
	public boolean updateByPrimaryKey(Paytotal payr);

	/**
	 * 根据条件查询信息,不分页
	 * @author heijj
	 * @param payr
	 * @return  List<Paytotal>
	 */
	public List<Paytotal> findPayrList(Paytotal payr);
	
	/**
	 * 翻页查询
	 * @param Paytotal payr
	 * @return int
	 */
	public int findTotal(Paytotal payr);
	
	/**
	 * 翻页查询
	 * @param Paytotal payr
	 * @return int
	 */
	public int findSelTotal(Paytotal payr);
	
}
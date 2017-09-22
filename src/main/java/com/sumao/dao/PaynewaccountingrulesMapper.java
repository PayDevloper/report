package com.sumao.dao;

import java.util.List;

import com.sumao.model.Paynewaccountingrules;

/**
 * 计费规则创建DAO
 * @author heijj
 * 
 */
public interface PaynewaccountingrulesMapper {
	
	/**
	 * 删除信息
	 * @param ids
	 * @return
	 */
	public boolean deleteByPrimaryKey(String id);
	
	/**
	 * 批量审核功能
	 * @param Paynewaccountingrules payr
	 * @return
	 */
	public boolean editaudit(Paynewaccountingrules payr);
	
	/**
	 * 根据组织机构ID，更新信息“计费配置状态”状态为"已创建"
	 * @param Paynewaccountingrules payr
	 * @return
	 */
	public boolean editNewAccountByID(Paynewaccountingrules payr);

	/**
	 * 新建保存信息
	 * @param Paynewaccountingrules payr
	 * @return
	 */
	public boolean insert(Paynewaccountingrules payr);

	/**
	 * 根据数据的ID，得到相应信息
	 * @param id
	 * @return Paynewaccountingrules
	 */
	public Paynewaccountingrules selectByPrimaryKey(String id);

	/**
	 * 编辑保存信息
	 * @param Paynewaccountingrules payr
	 * @return
	 */
	public boolean updateByPrimaryKey(Paynewaccountingrules payr);

	/**
	 * 根据条件查询信息
	 * @author heijj
	 * @param payr
	 * @return  List<Paynewaccountingrules>
	 */
	public List<Paynewaccountingrules> findPayrList(Paynewaccountingrules payr);
	
	/**
	 * 分页查询
	 * @param Paybond payr
	 * @return int
	 */
	public int findPayrTotal(Paynewaccountingrules payr);
	
}
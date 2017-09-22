package com.sumao.dao;

import java.util.List;
import java.util.Map;

import com.sumao.model.Paysub;
import com.sumao.model.Pay_userorg;

/**
 * 子项管理Dao
 * @author heijj
 */
public interface PaysubMapper {
	
	/**
	 * 删除信息
	 * @param ids
	 * @return
	 */
	public boolean deleteByPrimaryKey(String id);
	
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
	 * @param Paysub payr
	 * @return
	 */
	public boolean insert(Paysub payr);

	/**
	 * 根据ID，得到子项信息(暂未使用)
	 * @param id
	 * @return
	 */
	public Paysub selectByPrimaryKey(String id);

	/**
	 * 编辑保存信息
	 * @param Paysub payr
	 * @return
	 */
	public boolean updateByPrimaryKey(Paysub payr);

	/**
	 * 根据条件查询信息
	 * @author heijj
	 * @param payr
	 * @return  List<Paysub>
	 */
	public List<Paysub> findPayrList(Paysub payr);
	
	/**
	 * 根据条件查询信息
	 * @author heijj
	 * @param payr
	 * @return  List<Paysub>
	 */
	public List<Paysub> findPayselList(Paysub payr);
	
	/**
	 * 根据条件查询信息
	 * @author heijj
	 * @param payr
	 * @return  List<Paysub>
	 */
	public List<Paysub> findPayselbondList(Paysub payr);
	
	/**
	 * 销售组织配置
	 * @author heijj
	 * @param Pay_userorg payr
	 * @return  List<Pay_userorg>
	 */
	public List<Pay_userorg> findUserorgList(Pay_userorg payr);
	
	/**
	 * 翻页查询
	 * @param Pay_userorg payr
	 * @return int
	 */
	public int findPayrTotal(Pay_userorg payr);
	
	/**
	 * 翻页查询
	 * @param Paysub payr
	 * @return int
	 */
	public int findSelTotal(Paysub payr);
	
	/**
	 * 翻页查询
	 * @param Paysub payr
	 * @return int
	 */
	public int findSelbondTotal(Paysub payr);
	
	/**
	 * 翻页查询
	 * @param Paysub payr
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
	public List<Pay_userorg> selSeller(Map<String, Object> map);
	
}
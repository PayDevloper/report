package com.sumao.dao;

import java.util.List;
import com.sumao.model.Paysub;

/**
 *权限分配管理Dao
 * @author liutong
 */
public interface AuthDistributeMapper {
	/**
	 * 根据条件查询权限信息+总数
	 * @author liutong
	 * @param payr
	 * @return  List<Paysub>
	 */
	public int disauthTotal(Paysub payr);
	public List<Paysub> finddisauthList(Paysub payr);
	/**
	 * 根据条件查询岗位信息+总数
	 * @author liutong
	 * @param payr
	 * @return  List<Paysub>
	 */
	public int findposTotal(Paysub payr);
	public List<Paysub> findpositionList(Paysub payr);
	/**
	 * 查询出权限的父类栏以及子类栏
	 * @param dg
	 * @param payr
	 * @param int
	 * @return
	 */
	public List<Paysub> findAuthsort();
	public List<Paysub> findSubauthort();
	/**
	 * 新建保存信息
	 * 
	 * @param Paysub
	 *            payr
	 * @return
	 */
	public boolean insert(Paysub payr);
	/**
	 * 编辑保存信息
	 * 
	 * @param Paysub
	 *            payr
	 * @return
	 */
	public boolean updateByPrimaryKey(Paysub payr);
	/**
	 * 删除信息
	 * 
	 * @param ids
	 * @return
	 */
	public boolean deleteByPrimaryKey(String ids);
	/**
	 * 查询出权限的的父与子链接url
	 * @param dg
	 * @param payr
	 * @param int
	 * @return
	 */
	public List<Paysub> findsourceid(String sql);
	public List<Paysub> findurl(String sql_url);
	/**
	 * 根据Id查询出该权限信息
	 * 
	 * @param authid
	 * @return
	 */
	public Paysub findinfoByid(String authid);
	/**
	 * 基本权限信息+总数
	 * @author liutong
	 * @param payr
	 * @return  List<Paysub>
	 */
	public int disbasicauthTotal();
	public List<Paysub> disbasicauthList();
	/**
	 * 编辑保存基本权限信息
	 * 
	 * @param Paysub
	 *            payr
	 * @return
	 */
	public boolean editbasic(String sql);
	/**
	 * 导航显示信息+总数
	 * @author liutong
	 * @param payr
	 * @return  List<Paysub>
	 */
	public int findnaviTotal(Paysub payr);
	public List<Paysub> findnaviList(Paysub payr);
	/**
	 * 新建一级导航保存信息
	 * 
	 * @param Paysub
	 *            payr
	 * @return
	 */
	public boolean insertnavi(Paysub payr);
	/**
	 * 编辑一级导航保存信息
	 * 
	 * @param Paysub
	 *            payr
	 * @return
	 */
	public boolean editnaviByKey(Paysub payr);
	/**
	 * 删除一级导航信息
	 * 
	 * @param Paysub
	 *            payr
	 * @return
	 */
	public boolean delnaviByKey(String ids);
	/**
	 * 导航显示信息+总数
	 * @author liutong
	 * @param payr
	 * @return  List<Paysub>
	 */
	public int findsubnaviTotal(Paysub payr);
	public List<Paysub> subnavidatagrid(Paysub payr);
	/**
	 * 新建一级导航保存信息
	 * 
	 * @param Paysub
	 *            payr
	 * @return
	 */
	public boolean insertsubnavi(Paysub payr);
	/**
	 * 编辑一级导航保存信息
	 * 
	 * @param Paysub
	 *            payr
	 * @return
	 */
	public boolean editsubnaviByKey(Paysub payr);
	/**
	 * 删除一级导航信息
	 * 
	 * @param Paysub
	 *            payr
	 * @return
	 */
	public boolean delsubnaviByKey(String ids);
	
}
package com.sumao.service;

import java.util.List;
import com.sumao.model.EasyuiDataGrid;
import com.sumao.model.EasyuiDataGridJson;
import com.sumao.model.Paysub;

/**
 * 导航管理Service1.0
 * 
 * @author liutong
 * 
 */
public interface NavigationService  {
	/**
	 * 数据表格翻页查询
	 * @param Pay_userorg payr
	 * @return int
	 */
	public List<Paysub> findAuthsort();
	public List<Paysub> findSubauthort(String[] userids);
	/**
	 * 导航维护数据表格翻页查询
	 * @param Pay_userorg payr
	 * @return int
	 */
	public int findnaviTotal(Paysub payr);
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg,Paysub payr, int total);
	/**
	 * 新建一级导航保存信息
	 * 
	 * @param Paysub
	 *            payr
	 * @return
	 */
	public boolean addnavi(Paysub payr);
	/**
	 * 编辑一级导航保存信息
	 * 
	 * @param Paysub
	 *            payr
	 * @return
	 */
	public boolean editnavi(com.sumao.model.Paysub payr);
	/**
	 * 删除一级导航信息
	 * 
	 * @param Paysub
	 *            payr
	 * @return
	 */
	public boolean delnavi(String ids);
	/**
	 * 二级导航维护数据表格翻页查询
	 * @param Pay_userorg payr
	 * @return int
	 */
	public int findsubnaviTotal(com.sumao.model.Paysub payr);
	public EasyuiDataGridJson subnavidatagrid(EasyuiDataGrid dg, com.sumao.model.Paysub payr, int total);
	/**
	 * 新建一级导航保存信息
	 * 
	 * @param Paysub
	 *            payr
	 * @return
	 */
	public boolean addsubnavi(com.sumao.model.Paysub payr);
	
	/**
	 * 编辑一级导航保存信息
	 * 
	 * @param Paysub
	 *            payr
	 * @return
	 */
	public boolean editsubnavi(com.sumao.model.Paysub payr);
	/**
	 * 删除一级导航信息
	 * 
	 * @param Paysub
	 *            payr
	 * @return
	 */
	public boolean delsubnavi(String string);

	}

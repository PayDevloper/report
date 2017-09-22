package com.sumao.service;

import java.util.List;

import com.sumao.model.EasyuiDataGrid;
import com.sumao.model.EasyuiDataGridJson;
import com.sumao.model.Paysub;

/**
 * 权限分配管理Service
 * 
 * @author liutong
 * 
 */
public interface AuthDistributeService {
	/**
	 * 数据表格翻页查询
	 * 
	 * @param Pay_userorg
	 *            payr
	 * @return int
	 */
	public int disauthTotal(Paysub payr);

	/**
	 * 数据表格
	 * 
	 * @param dg
	 * @param payr
	 * @param int
	 * @return
	 */
	public EasyuiDataGridJson disauthdatagrid(EasyuiDataGrid dg, Paysub payr, int total);

	/**
	 * 数据表格翻页查询
	 * 
	 * @param Pay_userorg
	 *            payr
	 * @return int
	 */
	public int findposTotal(com.sumao.model.Paysub payr);

	/**
	 * 数据表格
	 * 
	 * @param dg
	 * @param payr
	 * @param int
	 * @return
	 */
	public EasyuiDataGridJson datagridpos(EasyuiDataGrid dg, com.sumao.model.Paysub payr, int total);

	/**
	 * 查询出权限的父类栏以及子类栏
	 * 
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
	public boolean add(Paysub payr);

	/**
	 * 编辑保存信息
	 * 
	 * @param Paysub
	 *            payr
	 * @return
	 */
	public boolean edit(Paysub payr);

	/**
	 * 删除信息
	 * 
	 * @param ids
	 * @return
	 */
	public boolean del(String string);

	/**
	 * 根据Id查询出该权限信息
	 * 
	 * @param authid
	 * @return
	 */
	public Paysub findinfoByid(String authid);

	public EasyuiDataGridJson disbasicauthTotal(EasyuiDataGrid dg);
	/**
	 * 编辑保存基本授权信息
	 * 
	 * @param Paysub
	 *            payr
	 * @return
	 */
	public boolean editbasic(String sql);
}

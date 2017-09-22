package com.sumao.service;

import java.util.List;

import com.sumao.model.EasyuiDataGrid;
import com.sumao.model.EasyuiDataGridJson;
import com.sumao.model.Payrule;

/**
 * 缴费规则创建Service
 * 
 * @author heijj
 * 
 */
public interface PayService  {

	/**
	 * 用户注册
	 * 
	 * @param user
	 *            用户信息
	 * @return User
	 */
	public Payrule reg(Payrule user);

	/**
	 * 用户登录
	 * 
	 * @param user
	 *            用户信息
	 * @return User
	 */

	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg, Payrule payr);

	public List<Payrule> combobox(String q);

	public boolean add(Payrule payr);

	public boolean edit(Payrule payr);

	public boolean del(String ids);
	
	public boolean editaudit(String ids);

	public void editUsersRole(String payrIds, String roleId);

	public Payrule getUserInfo(Payrule payr);

	public Payrule editUserInfo(Payrule payr);

}

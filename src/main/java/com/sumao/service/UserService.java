package com.sumao.service;

import java.util.List;

import com.sumao.model.EasyuiDataGrid;
import com.sumao.model.EasyuiDataGridJson;
import com.sumao.model.Syuser;

/**
 * 用户Service
 * 
 * @author 陈小俊
 * 
 */
public interface UserService  {

	/**
	 * 用户注册
	 * 
	 * @param user
	 *            用户信息
	 * @return User
	 */
	public Syuser reg(Syuser user);

	/**
	 * 用户登录
	 * 
	 * @param user
	 *            用户信息
	 * @return User
	 */
	public Syuser login(Syuser user);

	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg, Syuser user);

	public List<Syuser> combobox(String q);

	public Syuser add(Syuser user);

	public Syuser edit(Syuser user);

	public void del(String ids);

	public void editUsersRole(String userIds, String roleId);

	public Syuser getUserInfo(Syuser user);

	public Syuser editUserInfo(Syuser user);

}

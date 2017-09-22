package com.sumao.dao;

import java.util.List;

import com.sumao.model.Syuser;

public interface SyuserMapper {
	public int deleteByPrimaryKey(String id);

	public int insert(Syuser record);

	public int insertSelective(Syuser record);

	public Syuser selectByPrimaryKey(String id);

	public int updateByPrimaryKeySelective(Syuser record);

	public int updateByPrimaryKey(Syuser record);

	/**
	 * 根据条件查询用户信息,不分页
	 * 
	 * @param user
	 * @return
	 */
	public List<Syuser> findUsers(Syuser user);
	
	
	
}
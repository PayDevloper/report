package com.sumao.dao;

import java.util.List;

import com.sumao.model.Payrule;

public interface PayruleMapper {
	public boolean deleteByPrimaryKey(String id);
	
	public boolean editaudit(String id);

	public boolean insert(Payrule payr);

	public int insertSelective(Payrule payr);

	public Payrule selectByPrimaryKey(String id);

	public int updateByPrimaryKeySelective(Payrule payr);

	public boolean updateByPrimaryKey(Payrule payr);

	/**
	 * 根据条件查询信息,不分页
	 * @author heijj
	 * @param payr
	 * @return  List<Payrule>
	 */
	public List<Payrule> findPayrList(Payrule payr);
	
	
	
}
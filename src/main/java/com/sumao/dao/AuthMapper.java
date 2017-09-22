package com.sumao.dao;

import com.sumao.model.ClientTop;

/**
 * 数据字典表的mapper auhtor：liutong version：1.0
 */
public interface AuthMapper {
	/** 执行sql语句，检查tokenin的有效性，并返回查询结果的个数 */
	public int checkValidity(String dicnum);
	/** 执行sql语句，根据code的唯一值得出url，并返回url */
	public ClientTop recieveUrl(String code);
}
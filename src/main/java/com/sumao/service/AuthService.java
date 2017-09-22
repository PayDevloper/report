package com.sumao.service;

import com.sumao.model.ClientTop;

public interface AuthService {
	/** 执行sql语句，查询出token是否有时效性，tokenin是传入的检验条件 */
	public int checkValidity(String tokenin);
	public ClientTop recieveUrl(String code);
}
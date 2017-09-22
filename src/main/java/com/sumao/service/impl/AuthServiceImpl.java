package com.sumao.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.sumao.dao.AuthMapper;
import com.sumao.model.ClientTop;
import com.sumao.service.AuthService;

/**
 * 数据字典的service实现类 auhtor：liutong version：1.0
 */
@Service("AuthService")
public class AuthServiceImpl implements AuthService {

	@Resource
	private AuthMapper authMapper;

	/** 执行sql语句，检查tokenin的有效性，并返回查询结果的个数 */

	@Override
	public int checkValidity(String tokenin) {
		int checknum=authMapper.checkValidity(tokenin);
		return checknum;
	}
	@Override
	public ClientTop recieveUrl(String code) {
		ClientTop url=authMapper.recieveUrl(code);
		return url;
	}
}
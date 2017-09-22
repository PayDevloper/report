package com.sumao.service;

import java.util.List;
import com.sumao.model.ComTable;

/**
 * Top10信息service
 * 
 * @author Liutong Version 1.0
 */
public interface PermisService {
	/**
	 * 获取产品销售top dateinfo,dateend传入的参数是“day”“week”“month”
	 */
	public  List<ComTable> findPermiss(String tokenin);
}
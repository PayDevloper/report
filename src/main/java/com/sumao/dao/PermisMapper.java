package com.sumao.dao;

import java.util.List;
import java.util.Map;
import com.sumao.model.ComTable;
/**
 * 明细表的mapper auhtor：liutong version：1.0
 */
public interface PermisMapper {

	/** 调用带游标的存储过程，显示订单明细表的数据总数 */

	public  List<ComTable> findPermiss(Map<String, Object> params);

}
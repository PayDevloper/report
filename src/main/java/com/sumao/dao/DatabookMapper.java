package com.sumao.dao;

import java.util.List;
import com.sumao.model.Paysub;

/**
 * 数据字典表的mapper auhtor：liutong version：1.0
 */
public interface DatabookMapper {

	int findposTotal(Paysub payr);
	List<Paysub> finddicList(Paysub payr);
	boolean insert(Paysub payr);
	boolean updateByPrimaryKey(Paysub payr);
	boolean deleteByPrimaryKey(String ids);
	
}
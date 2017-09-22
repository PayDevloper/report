package com.sumao.dao;

import java.util.List;
import java.util.Map;
import com.sumao.model.ComTable;
import com.sumao.model.Paysub;

/**
 * 数据字典表的mapper auhtor：liutong version：1.0
 */
public interface DatadicMapper {
	/** 执行sql语句，显示数据字典的数据总数，params是查询信息 */
	public ComTable DataDicNum(Map<String, Object> params);

	/** 执行sql语句，显示数据字典的数据分页，params是查询信息 */
	public List<ComTable> showDataDicByPage(Map<String, Object> map);

	/** 执行sql语句，通过id显示数据字典的数据，dicnum是id信息 */
	public ComTable getDicById(String dicnum);

	/** 执行sql语句，添加一条数据字典的数据，comtabe是字典的信息 */
	public void addWord(ComTable comtabe);

	/** 执行sql语句，更新一条数据字典的数据，comtabe是字典的信息 */
	public void updateWord(ComTable comtabe);
	
	/** 执行sql语句，删除某一条数据字典的数据，dicnum是id信息 */
	public boolean delWordById(String dicnum);
	public List<ComTable> showDataDicByPage(ComTable comtabe);

	public List<Paysub> finddicList(Paysub payr);
}
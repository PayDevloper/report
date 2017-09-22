package com.sumao.service;

import java.util.List;
import com.sumao.model.ComTable;
import com.sumao.model.EasyuiDataGrid;
import com.sumao.model.EasyuiDataGridJson;
import com.sumao.model.Paysub;

public interface DatadicService {
	public int DataDicNum(ComTable comtable);
	/** 执行sql语句，显示数据字典的数据分页，comtabe是字典的信息，startPos和pageSize是分页的参数 */
	public List<ComTable> showDataDicByPage(int startPos, int pageSize, ComTable comtable);

	/** 执行sql语句，通过id显示数据字典的数据，dicnum是id信息 */
	public ComTable getDicById(String dicnum);

	/** 执行sql语句，添加一条数据字典的数据，comtabe是字典的信息 */
	public void addWord(ComTable comtabe);

	/** 执行sql语句，更新一条数据字典的数据，comtabe是字典的信息 */
	public void updateWord(ComTable comtabe);

	/** 执行sql语句，删除某一条数据字典的数据，dicnum是id信息 */
	public boolean delWordById(String dicnum);
	
	/**=========================*/
	
	/** 执行sql语句，显示数据字典的数据总数，params是查询信息 */
	public int DataDicNum(Paysub payr);
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg, Paysub payr, int total);

}
package com.sumao.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.sumao.dao.DatadicMapper;
import com.sumao.interceptors.PageHelper;
import com.sumao.model.ComTable;
import com.sumao.model.EasyuiDataGrid;
import com.sumao.model.EasyuiDataGridJson;
import com.sumao.model.Paysub;
import com.sumao.service.DatadicService;

/**
 * 数据字典的service实现类 auhtor：liutong version：1.0
 */
@Service("DatadicService")
public class DatadicServiceImpl implements DatadicService {

	@Resource
	private DatadicMapper datadicMapper;

	/**
	 * 获取数据条数， comtable传入的参数是查询条件
	 */
	@Override
	public int DataDicNum(ComTable comtable) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("col1", comtable.getCol1());
		params.put("col5", comtable.getCol5().toString().trim());
		ComTable OrderNum = datadicMapper.DataDicNum(params);
		int num = Integer.parseInt(OrderNum.getCol1());
		return num;
	}

	/**
	 * 执行sql语句，显示数据字典的数据分页，
	 *  comtabe是字典的信息，
	 *   startPos和pageSize是分页的参数
	 */
	@Override
	public List<ComTable> showDataDicByPage(int startPos, int pageSize, ComTable comtable) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startPos", startPos);
		params.put("pageSize", pageSize + startPos);
		params.put("col1", comtable.getCol1());
		params.put("col5", comtable.getCol5().toString().trim());
		List<ComTable> list = datadicMapper.showDataDicByPage(params);
		return list;
	}

	/**
	 * 执行sql语句，通过id显示数据字典的数据， dicnum是id信息
	 */
	@Override
	public ComTable getDicById(String dicnum) {
		ComTable comTable = datadicMapper.getDicById(dicnum);
		return comTable;
	}

	/**
	 * 执行sql语句，添加一条数据字典的数据， comtabe是字典的信息
	 */
	@Override
	public void addWord(ComTable comtabe) {
		datadicMapper.addWord(comtabe);
	}

	/**
	 * 执行sql语句，更新一条数据字典的数据， comtabe是字典的信息
	 */
	@Override
	public void updateWord(ComTable comtabe) {
		datadicMapper.updateWord(comtabe);
	}

	/**
	 * 执行sql语句，删除某一条数据字典的数据， dicnum是id信息
	 */
	@Override
	public boolean delWordById(String dicnum) {
		return datadicMapper.delWordById(dicnum);
	}


	@Override
	public int DataDicNum(Paysub payr) {
		return 0;
	}

	@Override
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg, Paysub payr, int total) {
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		int pageNo = dg.getPage();
		int pageSize = dg.getRows();
		PageHelper.startPage(pageNo, pageSize);
		List<Paysub> list = datadicMapper.finddicList(payr);
		PageHelper.endPage();
		long listss=(long)total;
		j.setTotal(listss);
		j.setRows(list);
		
		return j;
	}
}
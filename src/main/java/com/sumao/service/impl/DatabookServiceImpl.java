package com.sumao.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.sumao.dao.DatabookMapper;
import com.sumao.interceptors.PageHelper;
import com.sumao.model.EasyuiDataGrid;
import com.sumao.model.EasyuiDataGridJson;
import com.sumao.model.Paysub;
import com.sumao.service.DatabookService;

/**
 * 数据字典的service实现类 auhtor：liutong version：1.0
 */
@Service("DatabookService")
public class DatabookServiceImpl implements DatabookService {

	@Resource
	private DatabookMapper databookMapper;

	@Override
	public int DataDicNum(Paysub payr) {
		int total=databookMapper.findposTotal(payr);
		return total;
	}

	@Override
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg, Paysub payr, int total) {
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		int pageNo = dg.getPage();
		int pageSize = dg.getRows();
		PageHelper.startPage(pageNo, pageSize);
		List<Paysub> list = databookMapper.finddicList(payr);
		PageHelper.endPage();
		long listss=(long)total;
		j.setTotal(listss);
		j.setRows(list);
		
		return j;
	}

	/**
	 * 新建保存信息
	 * @param Paysub payr
	 * @return
	 */
	@Override
	public boolean add(Paysub payr) {
		
		boolean stat=databookMapper.insert(payr);
		
		return stat;
	}

	/**
	 * 编辑保存信息
	 * @param Paysub payr
	 * @return
	 */
	@Override
	public boolean edit(Paysub payr) {
		boolean stat=databookMapper.updateByPrimaryKey(payr);
		return stat;
	}
	/**
	 * 删除信息
	 * @param ids
	 * @return
	 */
	@Override
	public boolean del(String ids) {
		
		boolean stat=databookMapper.deleteByPrimaryKey(ids);
		return stat;
	}
}
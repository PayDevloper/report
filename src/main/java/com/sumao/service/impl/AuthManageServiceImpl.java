package com.sumao.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.sumao.dao.AuthManageMapper;
import com.sumao.interceptors.PageHelper;
import com.sumao.model.EasyuiDataGrid;
import com.sumao.model.EasyuiDataGridJson;
import com.sumao.model.Paysub;
import com.sumao.service.AuthManageService;

/**
 * 岗位创建Service1.0
 * 
 * @author liutong
 * 
 */
@Service("AuthManageService")
public class AuthManageServiceImpl implements AuthManageService {
	@Resource
	private AuthManageMapper authManageDao;

	/**
	 * 岗位数据列表总数+列表
	 * 
	 * @author liutong
	 * @date 20170116
	 */
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg, Paysub payr, int total) {

		EasyuiDataGridJson j = new EasyuiDataGridJson();
		int pageNo = dg.getPage();
		int pageSize = dg.getRows();
		PageHelper.startPage(pageNo, pageSize);
		List<Paysub> list = authManageDao.findauthList(payr);
		PageHelper.endPage();
		long listss = (long) total;
		j.setTotal(listss);
		j.setRows(list);

		return j;
	}

	@Override
	public int findTotal(Paysub payr) {

		int total = authManageDao.findTotal(payr);
		return total;
	}

	/**
	 * 新建保存信息
	 * 
	 * @param Paysub
	 *            payr
	 * @return
	 */
	@Override
	public boolean add(Paysub payr) {

		boolean stat = authManageDao.insert(payr);

		return stat;
	}

	/**
	 * 编辑保存信息
	 * 
	 * @param Paysub
	 *            payr
	 * @return
	 */
	@Override
	public boolean edit(Paysub payr) {
		boolean stat = authManageDao.updateByPrimaryKey(payr);
		return stat;
	}

	/**
	 * 删除信息
	 * 
	 * @param ids
	 * @return
	 */
	@Override
	public boolean del(String ids) {

		boolean stat = authManageDao.deleteByPrimaryKey(ids);
		return stat;
	}

	@Override
	public List<Paysub> findposition(Paysub basein) {
		List<Paysub> listposition = authManageDao.findposition(basein);
		return listposition;
	}
}

package com.sumao.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sumao.dao.PaynewaccountingrulesMapper;
import com.sumao.interceptors.PageHelper;
import com.sumao.model.EasyuiDataGrid;
import com.sumao.model.EasyuiDataGridJson;
import com.sumao.model.Paynewaccountingrules;
import com.sumao.service.PaynewaccountingrulesService;

/**
 * 计费规则创建Service
 * @author heijj
 * 
 */
@Service("paynewaccountingrulesService")
public class PaynewaccountingrulesServiceImpl implements PaynewaccountingrulesService {

	@Resource
	private PaynewaccountingrulesMapper PaynewaccountingrulesDao;

	/**
	 * 新建保存信息
	 * @param Paynewaccountingrules payr
	 * @return
	 */
	@Override
	public boolean add(Paynewaccountingrules payr) {
		
		boolean stat=PaynewaccountingrulesDao.insert(payr);
		
		return stat;
	}

	/**
	 * 编辑保存信息
	 * @param Paynewaccountingrules payr
	 * @return
	 */
	@Override
	public boolean edit(Paynewaccountingrules payr) {
		boolean stat=PaynewaccountingrulesDao.updateByPrimaryKey(payr);
		return stat;
	}

	/**
	 * 删除信息
	 * @param ids
	 * @return
	 */
	@Override
	public boolean del(String ids) {
		
		boolean stat=PaynewaccountingrulesDao.deleteByPrimaryKey(ids);
		return stat;
	}
	
	/**
	 * 批量审核功能
	 * @param Paynewaccountingrules payr
	 * @return
	 */
	@Override
	public boolean editaudit(Paynewaccountingrules payr) {
		
		boolean stat=PaynewaccountingrulesDao.editaudit(payr);
		return stat;
	}

	/**
	 * 根据组织机构ID，更新信息“计费配置状态”状态为"已创建"
	 * @param Paynewaccountingrules payr
	 * @return
	 */
	@Override
	public boolean editNewAccountByID(Paynewaccountingrules payr){
		
		boolean stat=PaynewaccountingrulesDao.editNewAccountByID(payr);
		return stat;
	}

	/**
	 * 数据列表
	 * @author heijj
	 * @date  20161206
	 */
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg, Paynewaccountingrules payr,int total) {
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		int pageNo = dg.getPage();
		int pageSize = dg.getRows();
		PageHelper.startPage(pageNo, pageSize);
		List<Paynewaccountingrules> list = PaynewaccountingrulesDao.findPayrList(payr);
		PageHelper.endPage();
		long listss=(long)total;
		j.setTotal(listss);
		j.setRows(list);

		return j;
	}
	
	/**
	 * 翻页查询
	 * @param Paybond
	 * @return int 
	 */
	@Override
	public int findPayrTotal(Paynewaccountingrules payr) {
		
		int total=PaynewaccountingrulesDao.findPayrTotal(payr);
		return total;
	}
	
	/**
	 * 根据销售组织ID值，得到计费规则创建数据
	 * @param id
	 * @return Paynewaccountingrules
	 */
	@Override
	public Paynewaccountingrules selectByPrimaryKey(String id) {
		Paynewaccountingrules bond = PaynewaccountingrulesDao.selectByPrimaryKey(id);
		
		return bond;
	}
	
}
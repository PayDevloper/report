package com.sumao.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sumao.dao.PaysubMapper;
import com.sumao.interceptors.PageHelper;
import com.sumao.model.EasyuiDataGrid;
import com.sumao.model.EasyuiDataGridJson;
import com.sumao.model.Paysub;
import com.sumao.service.PaySubService;
import com.sumao.model.Pay_userorg;
import com.sumao.model.Payeditaccountingrules;

/**
 * 缴费规则创建Service
 * @author heijj
 * 
 */
@Service("paySubService")
public class PaySubServiceImpl implements PaySubService {

	@Resource
	private PaysubMapper payDao;


	/**
	 * 新建保存信息
	 * @param Paysub payr
	 * @return
	 */
	@Override
	public boolean add(Paysub payr) {
		
		boolean stat=payDao.insert(payr);
		
		return stat;
	}

	/**
	 * 编辑保存信息
	 * @param Paysub payr
	 * @return
	 */
	@Override
	public boolean edit(Paysub payr) {
		boolean stat=payDao.updateByPrimaryKey(payr);
		return stat;
	}

	/**
	 * 删除信息
	 * @param ids
	 * @return
	 */
	@Override
	public boolean del(String ids) {
		
		boolean stat=payDao.deleteByPrimaryKey(ids);
		return stat;
	}
	
	/**
	 * 删除保证金设定信息
	 * @param id
	 * @return
	 */
	@Override
	public boolean deBondByPrimaryKey(String id) {
		
		boolean stat=payDao.deBondByPrimaryKey(id);
		return stat;
	}
	
	/**
	 * 删除权利金设定信息
	 * @param id
	 * @return
	 */
	@Override
	public boolean delRightByPrimaryKey(String id) {
		
		boolean stat=payDao.delRightByPrimaryKey(id);
		return stat;
	}
	
	/**
	 * 批量审核功能
	 * @param ids
	 * @return
	 */
	@Override
	public boolean editaudit(String ids) {
		
		boolean stat=payDao.editaudit(ids);
		return stat;
	}

	/**
	 * 数据列表
	 * @author heijj
	 * @date  20161124
	 */
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg, Paysub payr,int total) {
		
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		int pageNo = dg.getPage();
		int pageSize = dg.getRows();
		PageHelper.startPage(pageNo, pageSize);
		List<Paysub> list = payDao.findPayrList(payr);
		PageHelper.endPage();
		long listss=(long)total;
		j.setTotal(listss);
		j.setRows(list);
		
		return j;
	}
	
	/**
	 * 数据列表
	 * @author heijj
	 * @date  20161124
	 */
	public EasyuiDataGridJson datagridsel(EasyuiDataGrid dg, Paysub payr,int total) {
		
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		int pageNo = dg.getPage();
		int pageSize = dg.getRows();
		PageHelper.startPage(pageNo, pageSize);
		List<Paysub> list = payDao.findPayselList(payr);
		PageHelper.endPage();
		long listss=(long)total;
		j.setTotal(listss);
		j.setRows(list);
		
		return j;
	}
	
	/**
	 * 数据列表
	 * @author heijj
	 * @date  20161124
	 */
	public EasyuiDataGridJson datagridselbond(EasyuiDataGrid dg, Paysub payr,int total) {
		
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		int pageNo = dg.getPage();
		int pageSize = dg.getRows();
		PageHelper.startPage(pageNo, pageSize);
		List<Paysub> list = payDao.findPayselbondList(payr);
		PageHelper.endPage();
		long listss=(long)total;
		j.setTotal(listss);
		j.setRows(list);

		return j;
	}
	
	/**
	 * 数据列表
	 * @author heijj
	 * @date  20161204
	 */
	public EasyuiDataGridJson datagriduserorg(EasyuiDataGrid dg, Pay_userorg payr,int total) {
		
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		int pageNo = dg.getPage();
		int pageSize = dg.getRows();
		PageHelper.startPage(pageNo, pageSize);
		List<Pay_userorg> list = payDao.findUserorgList(payr);
		PageHelper.endPage();
		long listss=(long)total;
		j.setTotal(listss);
		j.setRows(list);
		
		return j;
	}


	/**
	 * 翻页查询
	 * @param Pay_userorg payr
	 * @return int
	 */
	@Override
	public int findPayrTotal(Pay_userorg payr) {
		
		int total=payDao.findPayrTotal(payr);
		return total;
	}
	
	/**
	 * 翻页查询
	 * @param Paysub payr
	 * @return int
	 */
	@Override
	public int findSelTotal(Paysub payr) {
		
		int total=payDao.findSelTotal(payr);
		return total;
	}
	
	/**
	 * 翻页查询
	 * @param Paysub payr
	 * @return int
	 */
	@Override
	public int findSelbondTotal(Paysub payr) {
		
		int total=payDao.findSelbondTotal(payr);
		return total;
	}
	
	/**
	 * 翻页查询
	 * @param Paysub payr
	 * @return int
	 */
	@Override
	public int findTotal(Paysub payr) {
		
		int total=payDao.findTotal(payr);
		return total;
	}
	
	/**
	 * 根据销售组织id，查询是否已存在该计费规则
	 * @param mkid
	 * @return int 
	 */
	@Override
	public int getprovingmkid(String mkid) {
		
		int getprovingmkid=payDao.getprovingmkid(mkid);
		return getprovingmkid;
	}
	
	/**
	 * @param String
	 * 返回页面的json,返回值是卖方名称信息的json
	 * @return List<Pay_userorg>
	 */
	public List<Pay_userorg> showSeller() {
		
		return payDao.showSeller();
	}
	
	/**
	 * @param String
	 * 返回页面的json,返回值是销售组织信息的json
	 * @return List<Pay_userorg>
	 */
	public List<Pay_userorg> selSeller(String sellername) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("marketing", sellername);
		
		return payDao.selSeller(map);
	}
	
}

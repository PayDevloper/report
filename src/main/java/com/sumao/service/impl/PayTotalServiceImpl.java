package com.sumao.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sumao.dao.PaytotalMapper;
import com.sumao.interceptors.PageHelper;
import com.sumao.model.EasyuiDataGrid;
import com.sumao.model.EasyuiDataGridJson;
import com.sumao.model.Paysub;
import com.sumao.model.Paytotal;
import com.sumao.service.PayTotalService;

/**
 * 总项管理Service
 * @author heijj
 * 
 */
@Service("payTotalService")
public class PayTotalServiceImpl implements PayTotalService {

	@Resource
	private PaytotalMapper payDao;

	/**
	 * 新建保存信息
	 * @param Paytotal payr
	 * @return
	 */
	@Override
	public boolean add(Paytotal payr) {
		boolean stat=payDao.insert(payr);
		return stat;
	}

	/**
	 * 编辑保存信息
	 * @param Paytotal payr
	 * @return
	 */
	@Override
	public boolean edit(Paytotal payr) {
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
	 * 删除子项信息
	 * @param id
	 * @return
	 */
	@Override
	public boolean delSubByPrimaryKey(String id){
		boolean stat=payDao.delSubByPrimaryKey(id);
		return stat;
	}
	
	/**
	 * 删除保证金设定信息
	 * @param id
	 * @return
	 */
	@Override
	public boolean deBondByPrimaryKey(String id){
		boolean stat=payDao.deBondByPrimaryKey(id);
		return stat;
	}
	
	/**
	 * 删除权利金设定信息
	 * @param id
	 * @return
	 */
	@Override
	public boolean delRightByPrimaryKey(String id){
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
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg, Paytotal payr,int total) {
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		int pageNo = dg.getPage();
		int pageSize = dg.getRows();
		PageHelper.startPage(pageNo, pageSize);
		List<Paytotal> list = payDao.findPayrList(payr);
		PageHelper.endPage();
		long listss=(long)total;
		j.setTotal(listss);
		j.setRows(list);

		return j;
	}
	
	/**
	 * 翻页查询
	 * @param Paysub payr
	 * @return int
	 */
	@Override
	public int findTotal(Paytotal payr) {
		
		int total=payDao.findTotal(payr);
		return total;
	}
	
	/**
	 * 翻页查询
	 * @param Paysub payr
	 * @return int
	 */
	@Override
	public int findSelTotal(Paytotal payr) {
		
		int total=payDao.findSelTotal(payr);
		return total;
	}
	
}

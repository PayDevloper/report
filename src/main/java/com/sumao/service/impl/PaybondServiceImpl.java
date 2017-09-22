package com.sumao.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sumao.dao.PaybondMapper;
import com.sumao.interceptors.PageHelper;
import com.sumao.model.EasyuiDataGrid;
import com.sumao.model.EasyuiDataGridJson;
import com.sumao.model.Pay_rightgoldcheck;
import com.sumao.model.Paybond;
import com.sumao.service.PaybondService;

/**
 * 保证金设定Service
 * @author heijj
 * 
 */
@Service("paybondService")
public class PaybondServiceImpl implements PaybondService {

	@Resource
	private PaybondMapper paybondDao;

	/**
	 * 新建保存信息
	 * @param Paybond payr
	 * @return
	 */
	@Override
	public boolean add(Paybond payr) {
		
		boolean stat=paybondDao.insert(payr);
		
		return stat;
	}

	/**
	 * 编辑保存信息
	 * @param Paybond payr
	 * @return
	 */
	@Override
	public boolean edit(Paybond payr) {
		boolean stat=paybondDao.updateByPrimaryKey(payr);
		return stat;
	}

	/**
	 * 删除信息
	 * @param ids
	 * @return
	 */
	@Override
	public boolean del(String ids) {
		
		boolean stat=paybondDao.deleteByPrimaryKey(ids);
		return stat;
	}
	
	/**
	 * 批量审核功能
	 * @param Paybond payr
	 * @return
	 */
	@Override
	public boolean editaudit(Paybond payr) {
		
		boolean stat=paybondDao.editaudit(payr);
		return stat;
	}
	
	/**
	 *更新保证金设定中是否缴费及现有保证金金额
	 *@param Paybond
	 *@author heijj
	 */
	@Override
	public boolean editBondByID(Paybond payr) {
		
		boolean stat=paybondDao.editBondByID(payr);
		return stat;
	}

	/**
	 * 根据保证经设定数据的ID，查询设定信息，用于费用支付功能
	 * @param String id
	 * @author heijj
	 * @return Paybond 
	 */
	@Override
	public Paybond selectByPrimaryKey(String id) {
		Paybond bond = paybondDao.selectByPrimaryKey(id);
		
		return bond;
	}
	
	/**
	 * 根据保证金设定中的销售组织ID值，得到数据
	 * @param String Marketingid
	 * @return Paybond
	 */
	public Paybond selectByMarketingid(String id) {
		Paybond bond = paybondDao.selectByMarketingid(id);
		
		return bond;
	}

	/**
	 * 数据列表
	 * @author heijj
	 * @date  20161206
	 */
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg, Paybond payr,int total) {
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		int pageNo = dg.getPage();
		int pageSize = dg.getRows();
		PageHelper.startPage(pageNo, pageSize);
		List<Paybond> list = paybondDao.findPayrList(payr);
		PageHelper.endPage();
		long listss=(long)total;
		j.setTotal(listss);
		j.setRows(list);

		return j;
	}
	
	/**
	 * 历史记录数据列表
	 * @author heijj
	 * @date  20161206
	 */
	public EasyuiDataGridJson datagridHis(EasyuiDataGrid dg, Paybond payr,int total) {
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		int pageNo = dg.getPage();
		int pageSize = dg.getRows();
		PageHelper.startPage(pageNo, pageSize);
		List<Paybond> list = paybondDao.findPayrHisList(payr);
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
	public int findPayrTotal(Paybond payr) {
		
		int total=paybondDao.findPayrTotal(payr);
		return total;
	}
	
	/**
	 * 历史记录翻页查询
	 * @param Paybond
	 * @return int 
	 */
	@Override
	public int findPayrHisTotal(Paybond payr) {
		
		int total=paybondDao.findPayrHisTotal(payr);
		return total;
	}
	
	/**
	 * 卖家中心--费用明细列表展示
	 * @author heijj
	 * @param startPos
	 * @param pageSize
	 * @param payr
	 * @return
	 */
	public List<Paybond> showOrderByPage(int pageNum, int pageSize,Paybond payr) {
		
		PageHelper.startPage(pageNum, pageSize);
		List<Paybond> list = paybondDao.findPayrHisList(payr);
		PageHelper.endPage();
		
		return list;
	}
	
	
	/**
	 * 保证金设定编辑保存历史记录
	 * @param Paybond payr
	 * @return boolean
	 */
	public boolean editHistory(Paybond payr) {
		boolean stat=paybondDao.editHistory(payr);
		return stat;
	}
	
	/**
	 * 新建保存历史信息，用于卖家查看扣款明细
	 * @param Paybond payr
	 * @return boolean
	 */
	public boolean addHistory(Paybond payr) {
		boolean stat=paybondDao.insertHistory(payr);
		return stat;
	}
	
	/**
	 * 根据销售组织ID，得到保证金设定中的唯一编号，用于保证金设定历史记录的保存。
	 * @param Paybond payr
	 * @author heijj
	 * @return Paybond
	 */
	public Paybond findBondByMID(Paybond payr) {
		Paybond payb=paybondDao.findBondByMID(payr);
		return payb;
	}
	
}

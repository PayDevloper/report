package com.sumao.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.sumao.dao.Pay_rightgoldMapper;
import com.sumao.interceptors.PageHelper;
import com.sumao.model.EasyuiDataGrid;
import com.sumao.model.EasyuiDataGridJson;
import com.sumao.model.Pay_rightgold;
import com.sumao.model.Pay_rightgoldcheck;
import com.sumao.service.PayRightService;
import com.sumao.model.Pay_userorg;

/**
 * 权利金设定Service
 * @author heijj
 * 
 */
@Service("payRightService")
public class PayRightServiceImpl implements PayRightService {

	@Resource
	private Pay_rightgoldMapper payDao;

	/**
	 * 新建保存信息
	 * @param payr
	 * @return
	 */
	@Override
	public boolean add(Pay_rightgold payr) {
		
		boolean stat=payDao.insert(payr);
		
		return stat;
	}

	/**
	 * 编辑保存信息
	 * @param Pay_rightgold payr
	 * @return
	 */
	@Override
	public boolean edit(Pay_rightgold payr) {
		boolean stat=payDao.updateByPrimaryKey(payr);
		return stat;
	}

	/**
	 * 删除用户
	 * @param ids
	 * @return
	 */
	@Override
	public boolean del(String ids) {
		
		boolean stat=payDao.deleteByPrimaryKey(ids);
		return stat;
	}
	
	/**
	 * 批量审核功能
	 * @param Pay_rightgold
	 * @return
	 */
	@Override
	public boolean editaudit(Pay_rightgold payr) {
		
		boolean stat=payDao.editaudit(payr);
		return stat;
	}
	
	/**
	 *更新权利金设定中是否缴费
	 *@param Pay_rightgold
	 *@author heijj
	 */
	@Override
	public boolean editBondByID(Pay_rightgold payr) {
		
		boolean stat=payDao.editBondByID(payr);
		return stat;
	}
	
	/**
	 *根据销售组织ID得到卖方，销售组织信息
	 *@param String marketingid
	 *@return Pay_userorg
	 *@author heijj
	 */
	@Override
	public Pay_userorg getUserorgBy(String marketingid) {
		
		Pay_userorg uorg=payDao.getUserorgBy(marketingid);
		return uorg;
	}

	/**
	 * 根据权利金设定数据的ID，查询设定信息，用于费用支付功能
	 * @param String id
	 * @author heijj
	 * @return Pay_rightgold 
	 */
	@Override
	public Pay_rightgold selectByPrimaryKey(String id) {
		Pay_rightgold rgold=payDao.selectByPrimaryKey(id);
		
		return rgold;
	}
	
	/**
	 * 数据列表
	 * @author heijj
	 * @date  20161124
	 */
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg, Pay_rightgold payr,int total) {
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		int pageNo = dg.getPage();
		int pageSize = dg.getRows();
		PageHelper.startPage(pageNo, pageSize);
		List<Pay_rightgold> list = payDao.findPayrList(payr);
		PageHelper.endPage();
		long listss=(long)total;
		j.setTotal(listss);
		j.setRows(list);
		
		return j;
	}
	
	/**
	 * 翻页查询
	 * @param Pay_rightgold
	 * @return int 
	 */
	@Override
	public int findPayrTotal(Pay_rightgold payr) {
		
		int total=payDao.findPayrTotal(payr);
		return total;
	}
	
	/**
	 * 查询审核状态
	 * @param String
	 * @return String 
	 */
	@Override
	public List<Pay_rightgold> getproving(Pay_rightgold payr) {
		
		List<Pay_rightgold> enddate=payDao.getproving(payr);
		return enddate;
	}
	
	/**
	 * 翻页查询
	 * @param Pay_rightgoldcheck
	 * @return int 
	 */
	@Override
	public int findPayrchTotal(Pay_rightgoldcheck payr) {
		int total=payDao.findPayrchTotal(payr);
		return total;
	}
	
	/**
	 * 数据列表权利金查看
	 * @author heijj
	 * @date  20170110
	 */
	public EasyuiDataGridJson datagridch(EasyuiDataGrid dg, Pay_rightgoldcheck payr,int total) {
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		int pageNo = dg.getPage();
		int pageSize = dg.getRows();
		PageHelper.startPage(pageNo, pageSize);
		List<Pay_rightgoldcheck> list = payDao.findPayrchList(payr);
		PageHelper.endPage();
		long listss=(long)total;
		j.setTotal(listss);
		j.setRows(list);
		
		return j;
	}
	
	/**
	 * 卖家中心--权利金缴费查看列表展示
	 * @author heijj
	 * @param startPos
	 * @param pageSize
	 * @param payr
	 * @return
	 */
	public List<Pay_rightgoldcheck> showOrderByPage(int pageNum, int pageSize,Pay_rightgoldcheck payr) {
		
		PageHelper.startPage(pageNum, pageSize);
		List<Pay_rightgoldcheck> list = payDao.findPayrchList(payr);
		PageHelper.endPage();
		
		return list;
	}
	
	/**
	 * 得到权利金子项信息
	 * @return list
	*/ 
	@Override
	public List<Pay_rightgoldcheck> selectgoldsub() {
		
		List<Pay_rightgoldcheck> subitemname=payDao.selectgoldsub();
		return subitemname;
	}
	
	/**
	 * 翻页查询
	 * @param Pay_rightgoldcheck
	 * @return int 
	 */
	@Override
	public int findPayallchTotal(Pay_rightgoldcheck payr) {
		int total=payDao.findPayallchTotal(payr);
		return total;
	}
	
	/**
	 * 数据列表权利金全部查看
	 * @author heijj
	 * @date  20170110
	 */
	public EasyuiDataGridJson datagridallch(EasyuiDataGrid dg, Pay_rightgoldcheck payr,int total) {
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		int pageNo = dg.getPage();
		int pageSize = dg.getRows();
		PageHelper.startPage(pageNo, pageSize);
		List<Pay_rightgoldcheck> list = payDao.findPayallchList(payr);
		PageHelper.endPage();
		long listss=(long)total;
		j.setTotal(listss);
		j.setRows(list);
		
		return j;
	}
	
	
	//=============================================================================================================
	/**
	 * 卖方中心接口调用
	 * @param payr
	 * @return
	 */
	@Override
	public String getqueryQlj(Pay_rightgoldcheck payr) {
		List<Pay_rightgoldcheck> list = payDao.findPayrchList(payr);
		String json = new Gson().toJson(list);
		return json;
	}
	
}

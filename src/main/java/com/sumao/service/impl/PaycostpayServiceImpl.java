package com.sumao.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sumao.dao.PaycostpayMapper;
import com.sumao.interceptors.PageHelper;
import com.sumao.model.EasyuiDataGrid;
import com.sumao.model.EasyuiDataGridJson;
import com.sumao.model.Pay_rightgoldcheck;
import com.sumao.model.Paycostpay;
import com.sumao.service.PaycostpayService;

/**
 * 费用支付Service
 * @author heijj
 * 
 */
@Service("paycostpayService")
public class PaycostpayServiceImpl implements PaycostpayService {

	@Resource
	private PaycostpayMapper paycostpayDao;

	/**
	 * 新建保存信息
	 * @param Paycostpay payr
	 * @return
	 */
	@Override
	public boolean add(Paycostpay payr) {
		
		boolean stat=paycostpayDao.insert(payr);
		
		return stat;
	}

	/**
	 * 编辑保存信息
	 * @param Paycostpay payr
	 * @return
	 */
	@Override
	public boolean edit(Paycostpay payr) {
		boolean stat=paycostpayDao.updateByPrimaryKey(payr);
		return stat;
	}

	/**
	 * 删除信息
	 * @param ids
	 * @return
	 */
	@Override
	public boolean del(String ids) {
		
		boolean stat=paycostpayDao.deleteByPrimaryKey(ids);
		return stat;
	}
	
	/**
	 *数据批量审核
	 *@param String ids
	 */
	@Override
	public boolean editaudit(String ids) {
		
		boolean stat=paycostpayDao.editaudit(ids);
		return stat;
	}

	/**
	 * 根据费用支付数据的ID，得到相应信息
	 * @param String id
	 * @author heijj
	 * @return Paycostpay 
	 */
	@Override
	public Paycostpay selectByPrimaryKey(String id) {
		Paycostpay costpay = paycostpayDao.selectByPrimaryKey(id);
		
		return costpay;
	}

	/**
	 * 数据列表
	 * @author heijj
	 * @date  20161206
	 */
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg, Paycostpay payr,int total) {
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		int pageNo = dg.getPage();
		int pageSize = dg.getRows();
		PageHelper.startPage(pageNo, pageSize);
		List<Paycostpay> list = paycostpayDao.findPayrList(payr);
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
	public int findPayrTotal(Paycostpay payr) {
		
		int total=paycostpayDao.findPayrTotal(payr);
		return total;
	}
	
	/**
	 * 卖家中心--保证金缴费查看翻页查询
	 * @param Paycostpay
	 * @return int 
	 */
	@Override
	public int findPayrcostTotal(Paycostpay payr) {
		
		int total=paycostpayDao.findPayrcostTotal(payr);
		return total;
	}
	
	/**
	 * 卖家中心--保证金缴费查看列表展示
	 * @author heijj
	 * @param startPos
	 * @param pageSize
	 * @param payr
	 * @return
	 */
	public List<Paycostpay> showOrderByPage(int pageNum, int pageSize,Paycostpay payr){

		PageHelper.startPage(pageNum, pageSize);
		List<Paycostpay> list = paycostpayDao.findPayrcostList(payr);
		PageHelper.endPage();
		
		return list;
	}
	
	
	/**
	 * 卖家中心--保证金缴费查看数据列表
	 * @author heijj
	 * @date  20161206
	 */
	public EasyuiDataGridJson datagridcost(EasyuiDataGrid dg, Paycostpay payr,int total) {
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		int pageNo = dg.getPage();
		int pageSize = dg.getRows();
		PageHelper.startPage(pageNo, pageSize);
		List<Paycostpay> list = paycostpayDao.findPayrcostList(payr);
		PageHelper.endPage();
		long listss=(long)total;
		j.setTotal(listss);
		j.setRows(list);
		
		return j;
	}
	
	/**
	 * 翻页查询
	 * @param Paycostpay
	 * @return int 
	 */
	@Override
	public int findPayallchTotal(Paycostpay payr) {
		int total=paycostpayDao.findPayallchTotal(payr);
		return total;
	}
	
	/**
	 * 数据列表权利金全部查看
	 * @author heijj
	 * @date  20170110
	 */
	public EasyuiDataGridJson datagridallch(EasyuiDataGrid dg, Paycostpay payr,int total) {
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		int pageNo = dg.getPage();
		int pageSize = dg.getRows();
		PageHelper.startPage(pageNo, pageSize);
		List<Paycostpay> list = paycostpayDao.findPayallchList(payr);
		PageHelper.endPage();
		long listss=(long)total;
		j.setTotal(listss);
		j.setRows(list);
		
		return j;
	}
	
	/**
	 * 得到保证金子项信息
	 * @return list
	*/ 
	@Override
	public List<Paycostpay> selectgoldsub() {
		
		List<Paycostpay> subitemname=paycostpayDao.selectgoldsub();
		return subitemname;
	}
	
	/**
	 * 根据是否为权利金以及缴费编号，查询是否已创建
	 * @param Paycostpay
	 * @return int 
	 */
	@Override
	public int getprovingused(String paymentcode) {
		
		int pcode=paycostpayDao.getprovingused(paymentcode);
		return pcode;
	}
	
}
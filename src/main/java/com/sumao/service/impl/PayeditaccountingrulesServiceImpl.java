package com.sumao.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sumao.dao.PayeditaccountingrulesMapper;
import com.sumao.interceptors.PageHelper;
import com.sumao.model.EasyuiDataGrid;
import com.sumao.model.EasyuiDataGridJson;
import com.sumao.model.Pay_userorg;
import com.sumao.model.Payeditaccountingrules;
import com.sumao.model.Paysub;
import com.sumao.service.PayeditaccountingrulesService;

/**
 * 计费规则管理Service
 * @author heijj
 * 
 */
@Service("payeditaccountingrulesService")
public class PayeditaccountingrulesServiceImpl implements PayeditaccountingrulesService {

	@Resource
	private PayeditaccountingrulesMapper payeditaccountingrulesDao;

	/**
	 * 新建保存信息
	 * @param Paybond payr
	 * @return
	 */
	@Override
	public boolean add(Payeditaccountingrules payr) {
		
		boolean stat=payeditaccountingrulesDao.insert(payr);
		
		return stat;
	}

	/**
	 * 编辑保存信息
	 * @param Paybond payr
	 * @return
	 */
	@Override
	public boolean edit(Payeditaccountingrules payr) {
		boolean stat=payeditaccountingrulesDao.updateByPrimaryKey(payr);
		return stat;
	}
	

	/**
	 * 计费规则编辑保存历史记录
	 * @param Payeditaccountingrules payr
	 * @return boolean
	 */
	public boolean editHistory(Payeditaccountingrules payr) {
		boolean stat=payeditaccountingrulesDao.editHistory(payr);
		return stat;
	}

	/**
	 * 删除信息
	 * @param ids
	 * @return
	 */
	@Override
	public boolean del(String ids) {
		
		boolean stat=payeditaccountingrulesDao.deleteByPrimaryKey(ids);
		return stat;
	}
	
	/**
	 * 批量审核功能
	 * @param payr
	 * @return
	 */
	@Override
	public boolean editaudit(Payeditaccountingrules payr) {
		
		boolean stat=payeditaccountingrulesDao.editaudit(payr);
		return stat;
	}

	/**
	 * 数据列表
	 * @author heijj
	 * @date  20161208
	 */
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg, Payeditaccountingrules payr,int total) {
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		int pageNo = dg.getPage();
		int pageSize = dg.getRows();
		PageHelper.startPage(pageNo, pageSize);
		List<Payeditaccountingrules> list = payeditaccountingrulesDao.findPayrList(payr);
		PageHelper.endPage();
		long listss=(long)total;
		j.setTotal(listss);
		j.setRows(list);

		return j;
	}
	
	/**
	 * 翻页查询
	 * @param Payeditaccountingrules
	 * @return int 
	 */
	@Override
	public int findPayrTotal(Payeditaccountingrules payr) {
		
		int total=payeditaccountingrulesDao.findPayrTotal(payr);
		return total;
	}
	
	/**
	 * 根据计费规则管理的ID值，得到数据
	 * @param id
	 * @return Payeditaccountingrules
	 */
	@Override
	public Payeditaccountingrules selectByPrimaryKey(String id) {
		Payeditaccountingrules account = payeditaccountingrulesDao.selectByPrimaryKey(id);
		
		return account;
	}
	
	/**
	 * 查询符合输入参数的规则
	 * @param id
	 * @return List<Payeditaccountingrules>
	 */
	@Override
	public List<Payeditaccountingrules> findrule(String buyerordernum,String buytime) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("buyerordernum", buyerordernum);
		map.put("buytime", buytime);
		return payeditaccountingrulesDao.findrule(map);
	}

	/**
	 * 自动运行，查询符合输入参数的计费规则
	 * @param String tradingpatterns,String marketingid,String buytime
	 * @return List<Payeditaccountingrules>
	 */
	public List<Payeditaccountingrules> findsellerrule(String tradingpatterns,String marketingid,String buytime,String buyerordernum) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tradingpatterns", tradingpatterns);
		map.put("marketingid", marketingid);
		map.put("buytime", buytime);
		map.put("buyerordernum", buyerordernum);
		return payeditaccountingrulesDao.findsellerrule(map);
	}
	
	/**
	 * 自动运行，查询符合输入参数的退费规则
	 * @param String buytime
	 * @return List<Payeditaccountingrules>
	 */
	public List<Payeditaccountingrules> findRefundrule(String buytime) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("buytime", buytime);
		return payeditaccountingrulesDao.findRefundrule(map);
	}
	
	/**
	 * 根据销售组织以及配置，验证是否时间交叉
	 * @param paye
	 * @return List 
	 */
	@Override
	public int getprovingtime(Payeditaccountingrules paye) {
		
		int provingtime=payeditaccountingrulesDao.getprovingtime(paye);
		return provingtime;
	}
	
	/**
	 * 根据销售组织以及配置，验证是否阈值交叉
	 * @param paye
	 * @return List 
	 */
	@Override
	public int getprovingshold(Payeditaccountingrules paye) {
		
		int provingshold=payeditaccountingrulesDao.getprovingshold(paye);
		return provingshold;
	}
	
	/**
	 * 计费规则查询数据列表
	 * @author heijj
	 * @date  20161208
	 */
	public EasyuiDataGridJson datagridseach(EasyuiDataGrid dg, Payeditaccountingrules payr,int total) {
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		int pageNo = dg.getPage();
		int pageSize = dg.getRows();
		PageHelper.startPage(pageNo, pageSize);
		List<Payeditaccountingrules> list = payeditaccountingrulesDao.findPayseachList(payr);
		PageHelper.endPage();
		long listss=(long)total;
		j.setTotal(listss);
		j.setRows(list);

		return j;
	}
	
	/**
	 * 计费规则查询翻页查询
	 * @param Payeditaccountingrules
	 * @return int 
	 */
	@Override
	public int findPayseachTotal(Payeditaccountingrules payr) {
		
		int total=payeditaccountingrulesDao.findPayseachTotal(payr);
		return total;
	}
	
	/**
	 * 产品牌号数据列表
	 * @author heijj
	 * @date  20161124
	 */
	public EasyuiDataGridJson datagridbrand(EasyuiDataGrid dg, Payeditaccountingrules payr,int total) {
		
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		int pageNo = dg.getPage();
		int pageSize = dg.getRows();
		PageHelper.startPage(pageNo, pageSize);
		List<Payeditaccountingrules> list = payeditaccountingrulesDao.findPayselbrandList(payr);
		PageHelper.endPage();
		long listss=(long)total;
		j.setTotal(listss);
		j.setRows(list);
		
		return j;
	}
	
	/**
	 * 产品牌号翻页查询
	 * @param Payeditaccountingrules payr
	 * @return int
	 */
	@Override
	public int findPaybrandTotal(Payeditaccountingrules payr) {
		
		int total=payeditaccountingrulesDao.findPaybrandTotal(payr);
		return total;
	}
	
	/**
	 * 返回页面的json,返回值是牌号大类信息的json
	 * @return List<Payeditaccountingrules>
	 */
	public List<Payeditaccountingrules> findoneebs(String marketingid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("marketingid", marketingid);
		return payeditaccountingrulesDao.findoneebs(map);
	}
	
	/**
	 * 返回页面的json,返回值是牌号中类信息的json
	 * @param String ebsname
	 * @return List<Payeditaccountingrules>
	 */
	public List<Payeditaccountingrules> findtwoebs(String marketingid,String ebsname) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ebsname", ebsname);
		map.put("marketingid", marketingid);
		return payeditaccountingrulesDao.findtwoebs(map);
	}
	
	/**
	 * 返回页面的json,返回值是牌号小类信息的json
	 * @param String ebsname
	 * @return List<Payeditaccountingrules>
	 */
	public List<Payeditaccountingrules> findthreeebs(String marketingid,String ebsname) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ebsname", ebsname);
		map.put("marketingid", marketingid);
		return payeditaccountingrulesDao.findthreeebs(map);
	}
	
	/**
	 * 返回页面的json,返回值是牌号信息的json
	 * @param String ebsname
	 * @return List<Payeditaccountingrules>
	 */
	public List<Payeditaccountingrules> findgradeebs(String marketingid,String ebsname) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ebsname", ebsname);
		map.put("marketingid", marketingid);
		return payeditaccountingrulesDao.findgradeebs(map);
	}
}

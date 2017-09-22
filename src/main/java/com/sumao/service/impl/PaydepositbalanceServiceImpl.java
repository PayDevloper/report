package com.sumao.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sumao.dao.PaydepositbalanceMapper;
import com.sumao.interceptors.PageHelper;
import com.sumao.model.EasyuiDataGrid;
import com.sumao.model.EasyuiDataGridJson;
import com.sumao.model.Paybond;
import com.sumao.model.Paydepositbalance;
import com.sumao.service.PaydepositbalanceService;

/**
 * 保证金结算Service
 * @author heijj
 * 
 */
@Service("paydepositbalanceService")
public class PaydepositbalanceServiceImpl implements PaydepositbalanceService {

	@Resource
	private PaydepositbalanceMapper paydepositbalanceDao;

	/**
	 * 新建保存信息
	 * @param Paydepositbalance payr
	 * @return
	 */
	@Override
	public boolean add(Paydepositbalance payr) {
		boolean stat=paydepositbalanceDao.insert(payr);
		
		return stat;
	}

	/**
	 * 编辑保存信息
	 * @param Paydepositbalance payr
	 * @return
	 */
	@Override
	public boolean edit(Paydepositbalance payr) {
		boolean stat=paydepositbalanceDao.updateByPrimaryKey(payr);
		return stat;
	}

	/**
	 * 删除结算信息
	 * @param ids
	 * @return
	 */
	@Override
	public boolean del(String ids) {
		
		boolean stat=paydepositbalanceDao.deleteByPrimaryKey(ids);
		return stat;
	}
	
	/*
	 *数据批量审核
	
	@Override
	public boolean editaudit(String ids) {
		
		boolean stat=paydepositbalanceDao.editaudit(ids);
		return stat;
	}
 */

	/**
	 * 数据表格
	 * @param dg
	 * @param Paydepositbalance payr
	 * @param int total
	 * @return
	 */
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg, Paydepositbalance payr,int total) {
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		int pageNo = dg.getPage();
		int pageSize = dg.getRows();
		PageHelper.startPage(pageNo, pageSize);
		List<Paydepositbalance> list = paydepositbalanceDao.findPayrList(payr);
		PageHelper.endPage();
		long listss=(long)total;
		j.setTotal(listss);
		j.setRows(list);
		
		return j;
	}
	
	/**
	 * 翻页查询
	 * @param Paydepositbalance
	 * @return int 
	 */
	@Override
	public int findPayrTotal(Paydepositbalance payr) {
		
		int total=paydepositbalanceDao.findPayrTotal(payr);
		return total;
	}
	
	/**
	 * 根据订单号，在买方结算收费时，得到相应信息
	 * @param buyerordernum
	 * @author heijj
	 * @return Paydepositbalance
	 */
	@Override
	public Paydepositbalance findinfoma(String buyerordernum) {
		
		Paydepositbalance total=paydepositbalanceDao.findinfoma(buyerordernum);
		return total;
	}
	
	/**
	 * 订单中买方按吨的阶梯收费
	 * @param marketingid
	 * @param tradingpatterns
	 * @param buyerid
	 * @param effectivetime
	 * @param invalidtime
	 * @return
	 */
	@Override
	public int allweight(String marketingid,String tradingpatterns,String buyerid,String effectivetime,String invalidtime) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("marketingid", marketingid);
		map.put("tradingpatterns", tradingpatterns);
		map.put("buyerid", buyerid);
		map.put("effectivetime", effectivetime);
		map.put("invalidtime", invalidtime);

		return paydepositbalanceDao.allweight(map);
	}
	
	/**
	 * 订单中买方按单的阶梯收费
	 * @param marketingid
	 * @param tradingpatterns
	 * @param buyerid
	 * @param effectivetime
	 * @param invalidtime
	 * @return
	 */
	@Override
	public int allorder(String marketingid,String tradingpatterns,String buyerid,String effectivetime,String invalidtime) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("marketingid", marketingid);
		map.put("tradingpatterns", tradingpatterns);
		map.put("buyerid", buyerid);
		map.put("effectivetime", effectivetime);
		map.put("invalidtime", invalidtime);
		
		return paydepositbalanceDao.allorder(map);
	}
	
	/**
	 * 得到符合结算条件的订单信息
	 * @author heijj
	 * @param Paydepositbalance
	 * @return List<Paydepositbalance>
	 */
	@Override
	public List<Paydepositbalance> getOrderBalanceInfo(Paydepositbalance payr) {
		List<Paydepositbalance> list = paydepositbalanceDao.getOrderBalanceInfo(payr);
		
		return list;
	}
	
	/**
	 * 订单中卖方按吨的阶梯收费，生效日期之内结算信息中总吨数，用于判断退费区间及计算退费金额
	 * @param marketingid
	 * @param tradingpatterns
	 * @param effectivetime
	 * @param invalidtime
	 * @return int
	 */
	@Override
	public int sellerallweight(String marketingid,String tradingpatterns,String effectivetime,String invalidtime) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("marketingid", marketingid);
		map.put("tradingpatterns", tradingpatterns);
		map.put("effectivetime", effectivetime);
		map.put("invalidtime", invalidtime);

		return paydepositbalanceDao.sellerallweight(map);
	}
	
	/**
	 * 订单中卖方按单的阶梯收费，生效日期之内结算信息中总吨数，用于判断退费区间及计算退费金额
	 * @param marketingid
	 * @param tradingpatterns
	 * @param effectivetime
	 * @param invalidtime
	 * @return int
	 */
	@Override
	public int sellerallorder(String marketingid,String tradingpatterns,String effectivetime,String invalidtime) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("marketingid", marketingid);
		map.put("tradingpatterns", tradingpatterns);
		map.put("effectivetime", effectivetime);
		map.put("invalidtime", invalidtime);
		
		return paydepositbalanceDao.sellerallorder(map);
	}
	
	/**
	 * 结算计费、退费同时更新设定信息“现有保证金”内容
	 * @param Paybond payr
	 * @return
	 */
	public boolean UpdateBondByMID(Paybond payr) {
		
		boolean stat=paydepositbalanceDao.UpdateBondByMID(payr);
		return stat;
	}
	
	/**
	 * 更新保证金设定信息中“是否预警”字段的内容
	 * @return
	 */
	public boolean UpdateWarnBond() {
		
		boolean stat=paydepositbalanceDao.UpdateWarnBond();
		paydepositbalanceDao.UpdateWarnBondNO();  //更新保证金设定信息中“是否预警”字段的内容为“否”
		
		return stat;
	}
	
}

package com.sumao.service;

import java.util.List;

import com.sumao.model.EasyuiDataGrid;
import com.sumao.model.EasyuiDataGridJson;
import com.sumao.model.Paybond;
import com.sumao.model.Paydepositbalance;

/**
 * 保证金结算Service
 * 
 * @author heijj
 * 
 */
public interface PaydepositbalanceService  {
	/**
	 * 数据表格
	 * @param dg
	 * @param Paydepositbalance payr
	 * @param int total
	 * @return
	 */
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg, Paydepositbalance payr,int total);

	/**
	 * 新建保存信息
	 * @param Paydepositbalance payr
	 * @return
	 */
	public boolean add(Paydepositbalance payr);

	/**
	 * 编辑保存信息
	 * @param Paydepositbalance payr
	 * @return
	 */
	public boolean edit(Paydepositbalance payr);
	
	/**
	 * 删除结算信息
	 * @param ids
	 * @return
	 */
	public boolean del(String ids);
	
	//public boolean editaudit(String ids);

	/**
	 * 根据订单号，在买方结算收费时，得到相应信息
	 * @param buyerordernum
	 * @author heijj
	 * @return Paydepositbalance
	 */
	public Paydepositbalance findinfoma(String buyerordernum);
	/**
	 * 订单中买方按吨的阶梯收费
	 * @param marketingid
	 * @param tradingpatterns
	 * @param buyerid
	 * @param effectivetime
	 * @param invalidtime
	 * @return
	 */
	public int allweight(String marketingid,String tradingpatterns,String buyerid,String effectivetime,String invalidtime);
	
	/**
	 * 订单中买方按单的阶梯收费
	 * @param marketingid
	 * @param tradingpatterns
	 * @param buyerid
	 * @param effectivetime
	 * @param invalidtime
	 * @return
	 */
	public int allorder(String marketingid,String tradingpatterns,String buyerid,String effectivetime,String invalidtime);
	
	/**
	 * 得到符合结算条件的订单信息
	 * @author heijj
	 * @param Paydepositbalance
	 * @return List<Paydepositbalance>
	 */
	public List<Paydepositbalance> getOrderBalanceInfo(Paydepositbalance payr);
	
	/**
	 * 订单中卖方按吨的阶梯收费，生效日期之内结算信息中总吨数，用于判断退费区间及计算退费金额
	 * @param marketingid
	 * @param tradingpatterns
	 * @param effectivetime
	 * @param invalidtime
	 * @return int
	 */
	public int sellerallweight(String marketingid,String tradingpatterns,String effectivetime,String invalidtime);
	
	/**
	 * 订单中卖方按单的阶梯收费，生效日期之内结算信息中总吨数，用于判断退费区间及计算退费金额
	 * @param marketingid
	 * @param tradingpatterns
	 * @param effectivetime
	 * @param invalidtime
	 * @return int
	 */
	public int sellerallorder(String marketingid,String tradingpatterns,String effectivetime,String invalidtime);

	/**
	 * 结算计费、退费同时更新设定信息“现有保证金”内容
	 * @param Paybond payr
	 * @return
	 */
	public boolean UpdateBondByMID(Paybond payr);
	
	/**
	 * 数据表格翻页
	 * @param payr
	 * @return int
	 */
	public int findPayrTotal(Paydepositbalance payr);
	
	/**
	 * 更新保证金设定信息中“是否预警”字段的内容
	 * @return
	 */
	public boolean UpdateWarnBond();
	
}

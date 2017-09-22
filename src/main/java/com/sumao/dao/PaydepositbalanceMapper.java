package com.sumao.dao;

import java.util.List;
import java.util.Map;

import com.sumao.model.Paybond;
import com.sumao.model.Paydepositbalance;

public interface PaydepositbalanceMapper {
	/**
	 * 删除结算信息
	 * @param ids
	 * @return
	 */
	public boolean deleteByPrimaryKey(String id);
	
	//public boolean editaudit(String id);

	/**
	 * 新建保存信息
	 * @param Paydepositbalance payr
	 * @return
	 */
	public boolean insert(Paydepositbalance payr);

	/**
	 * 根据结算信息ID，得到结算信息数据
	 * @param id
	 * @return
	 */
	public Paydepositbalance selectByPrimaryKey(String id);

	/**
	 * 编辑保存信息
	 * @param Paydepositbalance payr
	 * @return
	 */
	public boolean updateByPrimaryKey(Paydepositbalance payr);

	/**
	 * 根据条件查询信息,不分页
	 * @author heijj
	 * @param payr
	 * @return  List<Paydepositbalance>
	 */
	public List<Paydepositbalance> findPayrList(Paydepositbalance payr);
	
	/**
	 * 分页查询
	 * @param Paydepositbalance payr
	 * @return int
	 */
	public int findPayrTotal(Paydepositbalance payr);
	
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
	public int allweight(Map<String, Object> map);
	
	/**
	 * 订单中买方按单的阶梯收费
	 * @param marketingid
	 * @param tradingpatterns
	 * @param buyerid
	 * @param effectivetime
	 * @param invalidtime
	 * @return
	 */
	public int allorder(Map<String, Object> map);
	
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
	public int sellerallweight(Map<String, Object> map);
	
	/**
	 * 订单中卖方按单的阶梯收费，生效日期之内结算信息中总吨数，用于判断退费区间及计算退费金额
	 * @param marketingid
	 * @param tradingpatterns
	 * @param effectivetime
	 * @param invalidtime
	 * @return int
	 */
	public int sellerallorder(Map<String, Object> map);
	
	/**
	 *结算计费、退费同时更新设定信息“现有保证金”内容
	 *@param Paybond
	 *@author heijj
	 */
	public boolean UpdateBondByMID(Paybond payr);
	
	/**
	 * 更新保证金设定信息中“是否预警”字段的内容为“是”
	 * @return
	 */
	public boolean UpdateWarnBond();
	
	/**
	 * 更新保证金设定信息中“是否预警”字段的内容为“否”
	 * @return
	 */
	public boolean UpdateWarnBondNO();
	
}
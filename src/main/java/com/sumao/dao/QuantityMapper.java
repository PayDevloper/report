package com.sumao.dao;

import java.util.List;
import java.util.Map;
import com.sumao.model.ClientTop;
import com.sumao.model.QuantityTop;
import com.sumao.report.summary.pojo.DailySalesSummary;
import com.sumao.report.summary.pojo.SummaryRequest;

public interface QuantityMapper {

	/**
	 * 调用带游标的查询产品信息 params包含“startdatetime”“startdatetime”键值对
	 */
	public List<QuantityTop> findTops(Map<String, Object> params);

	/**
	 * 调用带游标的存储过程客户信息 params包含“startdatetime”“startdatetime”键值对
	 */
	public List<ClientTop> findClientTops(Map<String, Object> params);

	/**
	 * 调用带游标的存储过程供应商信息 params包含“startdatetime”“startdatetime”键值对
	 */
	public List<ClientTop> findSuplierTops(Map<String, Object> params);

	/**
	 * 调用带游标的存储过程地区信息 params包含“startdatetime”“startdatetime”键值对
	 */
	public List<ClientTop> findAreaTops(Map<String, Object> params);

	/**
	 * 调用带游标的存储过程客户数量信息 params包含“startdatetime”“startdatetime”键值对
	 */
	public List<ClientTop> CountClient(Map<String, Object> params);

	public List<ClientTop> findNewClient(Map<String, Object> params);

	/**
	 * 调用带游标的存储过程订单状态信息 params包含“startdatetime”“startdatetime”键值对
	 */
	public List<ClientTop> CountTotal(Map<String, Object> params);

	public List<ClientTop> CountPayed(Map<String, Object> params);

	public List<ClientTop> CountPaying(Map<String, Object> params);

	public List<ClientTop> CountRemoved(Map<String, Object> params);

	/**
	 * 调用带游标的存储过程销售汇总信息 params包含“startdatetime”“startdatetime”键值对
	 */
	public List<ClientTop> SummaryFirst(Map<String, Object> params);

	public List<ClientTop> SummarySecond(Map<String, Object> params);

	public List<ClientTop> SummaryThird(Map<String, Object> params);

	public List<ClientTop> SummaryDeliver(Map<String, Object> params);

	public List<ClientTop> SummaryClients(Map<String, Object> params);

	public List<ClientTop> SummaryNewClients(Map<String, Object> params);

	public List<ClientTop> SummaryNewClient(Map<String, Object> params);
	/**查询图表的日销量，金额 */
	public List<DailySalesSummary> findChartData(SummaryRequest params);

}
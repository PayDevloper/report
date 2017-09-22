package com.sumao.service;

import java.util.List;
import com.sumao.model.ClientTop;
import com.sumao.model.CountNum;
import com.sumao.report.summary.pojo.DailySalesSummary;

/**
 * Top10信息service
 * 
 * @author Liutong Version 1.0
 */
public interface QuantityService {
	/**
	 * 获取产品销售top dateinfo,dateend传入的参数是“day”“week”“month”
	 */
	public List<ClientTop> findProducts(String dateinfo, String dateend, String orgnid);

	/**
	 * 获取客户top dateinfo,dateend传入的参数是“day”“week”“month”
	 */
	public List<ClientTop> findClients(String dateinfo, String dateend, String orgnid);

	/**
	 * 获取供应商top dateinfo,dateend传入的参数是“day”“week”“month”
	 */
	public List<ClientTop> findSuplier(String dateinfo, String dateend, String orgnid);

	/**
	 * 获取地区top dateinfo,dateend传入的参数是“day”“week”“month”
	 */
	public List<ClientTop> findArea(String dateinfo, String dateend, String orgnid);

	/**
	 * 客户数量 dateinfo,dateend传入的参数是“day”“week”“month”
	 */
	public CountNum CountClient(String dateinfo, String dateend, String orgnid);

	/**
	 * 汇总信息 dateinfo,dateend传入的参数是“day”“week”“month”
	 */
	public CountNum CountAll(String dateinfo, String dateend, String orgnid);

	/**
	 * 订单状态 dateinfo,dateend传入的参数是“day”“week”“month”
	 */
	public CountNum CountOrder(String dateinfo, String dateend, String orgnid);
	/**查询图表的日销量，金额 */
	public List<DailySalesSummary> findChartData(String dateinfo, String dateend, String orgnid);


}
package com.sumao.dao;

import java.util.List;
import java.util.Map;
import com.sumao.model.ComTable;
import com.sumao.report.salesummary.pojo.SaleSummeryElement;
import com.sumao.report.salesummary.pojo.SaleSummeryRequest;
/**
 * 明细表的mapper auhtor：liutong version：1.0
 */
public interface DetailMapper {

	/** 调用带游标的存储过程，显示订单明细表的数据总数 */
	public List<ComTable> getOrderCount(Map<String, Object> params);

	/** 调用带游标的存储过程，显示订单明细表 */
	public List<ComTable> showOrderByPage(Map<String, Object> map);

	/** 调用带游标的存储过程，显示供应商明细表的数据总数 */
	public List<ComTable> getSupplierCount(Map<String, Object> params);

	/** 调用带游标的存储过程，显示供应商明细表 */
	public List<ComTable> showSupplierByPage(Map<String, Object> params);

	/** 调用带游标的存储过程，显示地区明细表的数据总数 */
	public List<ComTable> getAreaCount(Map<String, Object> params);

	/** 调用带游标的存储过程，显示地区明细表 */
	public List<ComTable> showAreaByPage(Map<String, Object> params);

	/** 调用带游标的存储过程，显示销售明细表的数据总数 */
	public List<ComTable> getSaleCount(Map<String, Object> params);

	/** 调用带游标的存储过程，显示销售明细表 */
	public List<ComTable> showSaleByPage(Map<String, Object> params);

	/** 调用带游标的存储过程，显示客户明细表的数据总数 */
	public List<ComTable> getClientCount(Map<String, Object> params);

	/** 调用带游标的存储过程，显示客户明细表 */
	public List<ComTable> showClientByPage(Map<String, Object> params);

	public List<ComTable> selecttype(String typeIn);
	/** 显示销售概览的数据 */
	public List<SaleSummeryElement> findSummaryList(SaleSummeryRequest cts);
}
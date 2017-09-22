package com.sumao.service;

import java.util.List;
import com.sumao.model.ComTable;
import com.sumao.report.salesummary.pojo.SaleSummeryElement;
import com.sumao.report.salesummary.pojo.SaleSummeryRequest;

public interface DetailService {
	/**
	 * 调用带返回值的存储过程，查询订单明细表； comtable为查询条件
	 */
	public int findOrderNum(ComTable comtable);

	/**
	 * 调用带返回值的存储过程，查询供应商明细表； comtable为查询条件
	 */
	public int findSupplierNum(ComTable comtable);

	/**
	 * 调用带返回值的存储过程，查询地区明细表； comtable为查询条件
	 */
	public int findAreaNum(ComTable comtable);

	/**
	 * 调用带返回值的存储过程，查询销售明细表； comtable为查询条件
	 */
	public int findSaleNum(ComTable comtable);

	/**
	 * 调用带返回值的存储过程，查询客户明细表； comtable为查询条件
	 */
	public int findClientNum(ComTable comtable);
	/**
	 * 调用带返回值的存储过程，查询客户明细表； comtable为查询条件，pageSize为每页的大小，startPos为开始的数据条数
	 */
	public List<ComTable> showClientByPage(int startPos, int pageSize, ComTable comtable);

	/**
	 * 调用带返回值的存储过程，导出明细表； comtable为查询条件
	 */
	public List<ComTable> exportExcel(ComTable comtable, String tableinfo);

	/**
	 * 调用带返回值的存储过程，查询订单明细表； comtable为查询条件，pageSize为每页的大小，startPos为开始的数据条数
	 */
	public List<ComTable> showOrderByPage(int startPos, int pageSize, ComTable comtable);

	/**
	 * 调用带返回值的存储过程，查询供应商明细表； comtable为查询条件，pageSize为每页的大小，startPos为开始的数据条数
	 */
	public List<ComTable> showSupplierByPage(int startPos, int pageSize, ComTable comtable);

	/**
	 * 调用带返回值的存储过程，查询地区明细表； comtable为查询条件，pageSize为每页的大小，startPos为开始的数据条数
	 */
	public List<ComTable> showAreaByPage(int startPos, int pageSize, ComTable comtable);

	/**
	 * 调用带返回值的存储过程，查询销售明细表； comtable为查询条件，pageSize为每页的大小，startPos为开始的数据条数
	 */
	public List<ComTable> showSaleByPage(int startPos, int pageSize, ComTable comtable);

		/**
	 * 检索出订单类型的list
	 */
	public List<ComTable> selecttype(String typeIn);
	/**
	 * 查询销售概览； SaleSummeryRequest为查询条件
	 */
	public List<SaleSummeryElement> findSummaryList(SaleSummeryRequest cts);

}
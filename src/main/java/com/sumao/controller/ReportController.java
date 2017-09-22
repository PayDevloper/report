package com.sumao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sumao.model.pagination.QueryModel;
import com.sumao.model.report.Order;
import com.sumao.service.ReportService;

/**
 * 
 * @author Chenxiaojun 报表控制器
 */
//@Controller
//@RequestMapping("/reportController")
public class ReportController extends BaseController {
	private ReportService reportService;

	public ReportService getReportService() {
		return reportService;
	}

	@Autowired
	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

	/**
	 * 跳转到订单明细报表页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "orderDetail")
	public String orderDetail() {
		return "/report/orderDetail";
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "orderDetailGrid")
	@ResponseBody
	public List<Order> orderDetailGrid(QueryModel queryModel) {
		
//		List<Order> result= reportService.getOrderDetailList(null);
		List<Order> result= reportService.getOrderDetails(null);
		return result;
	}
}

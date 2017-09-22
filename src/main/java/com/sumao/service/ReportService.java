package com.sumao.service;

import java.util.List;
import java.util.Map;

import com.sumao.model.report.Order;

public interface ReportService {
	public List<Order> getOrderDetailList(Map<String, Object> params) ;
	
	public List<Order> getOrderDetails(Map<String, Object> params) ;
}

package com.sumao.report.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.sumao.controller.BaseController;
import com.sumao.model.EasyuiDataGrid;
import com.sumao.model.EasyuiDataGridJson;
import com.sumao.model.Pay_rightgoldcheck;
import com.sumao.report.pojo.PaginationResponse;
import com.sumao.report.qlj.pojo.QljElement;
import com.sumao.report.qlj.pojo.QljRequest;
import com.sumao.service.PayRightService;
import com.sumao.service.impl.PayRightServiceImpl;
/**
 * 接口开发示例
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/service/report")
public class QljController extends BaseController {
	

	private PayRightService payRightService;
	
	public PayRightService getPayRightService() {
		return payRightService;
	}

	@Autowired
	public void setPayRightService(PayRightService payRightService) {
		this.payRightService = payRightService;
	}
	
	//示例
	@RequestMapping(params="qlj", produces="application/json; charset=utf-8")
	@ResponseBody
	public String queryQlj(HttpServletRequest request, @RequestBody String qljQueryStr){
		
		QljRequest qljRequest = new Gson().fromJson(qljQueryStr, QljRequest.class);
		
		PaginationResponse<QljElement> response = new PaginationResponse<QljElement>();
		
		return new Gson().toJson(response);
	}
	
	
	@RequestMapping(params="Getqlj", produces="application/json; charset=utf-8")
	@ResponseBody
	public String getqueryQlj(Pay_rightgoldcheck payr){
		System.out.println("aaa==="+payr.toString());
		String getqueryQlj = payRightService.getqueryQlj(payr);
		
		return getqueryQlj;
	}
}

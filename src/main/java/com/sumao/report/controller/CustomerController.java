package com.sumao.report.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.sumao.model.ComTable;
import com.sumao.report.customersale.pojo.CustomerSaleRequest;
import com.sumao.service.DetailService;

/**
 * 
 * @author aaa 
 * 
 *
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Resource
	private DetailService detailService;

	public DetailService getDetailService() {
		return detailService;
	}

	@Autowired
	public void setDetailService(DetailService detailService) {
		this.detailService = detailService;
	}

	@RequestMapping(value = "cussale",produces="application/json; charset=utf-8")
	@ResponseBody
	public String getcusSale(@RequestBody String customerSaleRequest){
		
		CustomerSaleRequest cusSaleRequest = new Gson().fromJson(customerSaleRequest, CustomerSaleRequest.class);
		
		String startDate = cusSaleRequest.getStartDate();
		String endDate = cusSaleRequest.getEndDate();
		String customerCode = cusSaleRequest.getCustomerCode();
		String customerName = cusSaleRequest.getCustomerName();
		String businessType = cusSaleRequest.getBusinessType();
		
        ComTable comTable = new ComTable();
        
        comTable.setCol1(startDate);
        comTable.setCol2(endDate);
        comTable.setCol3(customerCode);
        comTable.setCol4(customerName);
        comTable.setCol5(businessType);
		
        List<ComTable> showClientByPage = this.detailService.showClientByPage(0, 5, comTable);
		
		String json = new Gson().toJson(showClientByPage);
        
		return json;
	}
	
}

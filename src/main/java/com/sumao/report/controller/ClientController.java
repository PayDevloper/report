package com.sumao.report.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;









































import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.sumao.model.ComTable;
import com.sumao.report.customersale.pojo.CustomerSaleElement;
import com.sumao.report.customersale.pojo.CustomerSaleRequest;
import com.sumao.report.manufacturer.pojo.ManufacturerElement;
import com.sumao.report.manufacturer.pojo.ManufacturerRequest;
import com.sumao.report.orderdetail.pojo.OrderDetailElement;
import com.sumao.report.orderdetail.pojo.OrderDetailRequest;
import com.sumao.report.pojo.PaginationResponse;
import com.sumao.report.salesummary.pojo.SaleSummeryElement;
import com.sumao.report.salesummary.pojo.SaleSummeryRequest;
import com.sumao.report.skudetail.pojo.SkuDetailElement;
import com.sumao.report.skudetail.pojo.SkuDetailRequest;
import com.sumao.report.skulocation.pojo.SkuLocationElement;
import com.sumao.report.skulocation.pojo.SkuLocationRequest;
import com.sumao.service.DetailService;

@Controller
@RequestMapping("/clientController")
public class ClientController {

	@Autowired
	private DetailService dsService;

	// 客户销售统计表报表
	@RequestMapping(value = "/showClientByPage", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String showClientByPage(@RequestBody String customerSaleRequest,
			HttpServletRequest request) throws Exception {
		
		String trim = customerSaleRequest.trim();

		PaginationResponse<CustomerSaleElement> response = new PaginationResponse<CustomerSaleElement>();
		if (StringUtils.isNotEmpty(customerSaleRequest)) {
            
			
			CustomerSaleRequest cts = new Gson().fromJson(trim,
					CustomerSaleRequest.class);
			if(cts.getPageNum().equals("")){
				cts.setPageNum("1");
			}
			if(cts.getPageSize().equals("")){
				cts.setPageSize("10");
			}
			int int1 = Integer.parseInt(cts.getPageNum());
			int int2 = Integer.parseInt(cts.getPageSize());
			
			
			ComTable comtable = new ComTable();
			if (StringUtils.isNotEmpty(cts.getSaleOrganizationIds())) {
				String trim2 = cts.getSaleOrganizationIds().trim();
				comtable.setCol10(trim2);
			}
			if (StringUtils.isNotEmpty(cts.getStartDate())) {
				String trim2 = cts.getStartDate().trim();
				comtable.setCol1(trim2);
			}
			if (StringUtils.isNotEmpty(cts.getEndDate())) {
				String trim2 = cts.getEndDate().trim();
				comtable.setCol2(trim2);
			}
			if (StringUtils.isNotEmpty(cts.getCustomerCode())) {
				String trim2 = cts.getCustomerCode().trim();
				comtable.setCol3(trim2);
			}
			if (StringUtils.isNotEmpty(cts.getCustomerName())) {
				String trim2 = cts.getCustomerName().trim();
				comtable.setCol4(trim2);
			}
			if (StringUtils.isNotEmpty(cts.getBusinessType())) {
				String trim2 = cts.getBusinessType().trim();
				comtable.setCol5(trim2);
			}
			int totalNum = dsService.findClientNum(comtable);
			List<ComTable> sourceList = null;
			List<CustomerSaleElement> targetList = null;

			 if (totalNum > 0) {
			sourceList = dsService.showClientByPage((int1-1)*int2, int2, comtable);
			if (CollectionUtils.isNotEmpty(sourceList)) {
				targetList = new ArrayList<CustomerSaleElement>(
						sourceList.size());
				int i = 0;
				for (ComTable sourceElement : sourceList) {
					CustomerSaleElement customerSaleElement = new CustomerSaleElement();
					i=++i;
					customerSaleElement.setId(i);
					customerSaleElement
							.setCustomerCode(sourceElement.getCol1());
					customerSaleElement
							.setCustomerName(sourceElement.getCol2());
					customerSaleElement
							.setBusinessType(sourceElement.getCol3());
					
					if(StringUtils.isNotEmpty(sourceElement.getCol4())){
						String string = sourceElement.getCol4().trim();
						customerSaleElement.setSales(string);
					}else{
						customerSaleElement.setSales("0.00");
					}
				
					if(StringUtils.isNotEmpty(sourceElement.getCol5())){
						String string = sourceElement.getCol5().trim();
						customerSaleElement.setAmount(string);
					}else {
						customerSaleElement.setAmount("0.00");
					}
					
					if(StringUtils.isNotEmpty(sourceElement.getCol6())){
						Integer valueOf = Integer.valueOf(sourceElement.getCol6());
						customerSaleElement.setOrdersNumber(valueOf);
						
					}else {
						customerSaleElement.setOrdersNumber(0);
					}
					targetList.add(customerSaleElement);
					
				}
				 }
			}
			 response.setPageNum(int1);
			 response.setPageSize(int2);
			response.setTotal(totalNum);
			response.setExecutionResult(true);
			response.setRows(targetList);
		} else {
			response.setExecutionResult(false);
			response.setMessage("参数不足");
		}

		return new Gson().toJson(response);
	}

	// 生产商统计报表
	@RequestMapping(value = "/toSupplierDetails", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String toSupplierDetails(@RequestBody String ManufacturerRequest,
			HttpServletRequest request) {
		PaginationResponse<ManufacturerElement> response = new PaginationResponse<ManufacturerElement>();
		if (StringUtils.isNotEmpty(ManufacturerRequest)) {
			ManufacturerRequest manufacturerRequest = new Gson().fromJson(
					ManufacturerRequest, ManufacturerRequest.class);
			if(manufacturerRequest.getPageNum().equals("")){
				manufacturerRequest.setPageNum("1");
			}
			if(manufacturerRequest.getPageSize().equals("")){
				manufacturerRequest.setPageSize("10");
			}
			int int1 = Integer.parseInt(manufacturerRequest.getPageNum());
			int int2 = Integer.parseInt(manufacturerRequest.getPageSize());
			ComTable comTable = new ComTable();
			if (StringUtils.isNotEmpty(manufacturerRequest
					.getSaleOrganizationIds())) {
				comTable.setCol10(manufacturerRequest.getSaleOrganizationIds());
			}
			if (StringUtils.isNotEmpty(manufacturerRequest.getStartDate())) {
				comTable.setCol2(manufacturerRequest.getStartDate());
			}
			if (StringUtils.isNotEmpty(manufacturerRequest.getEndDate())) {
				comTable.setCol3(manufacturerRequest.getEndDate());
			}
			if (StringUtils.isNotEmpty(manufacturerRequest.getProducer())) {
				comTable.setCol1(manufacturerRequest.getProducer());
			}

			int totalNum = dsService.findSupplierNum(comTable);
			List<ComTable> sourceList = null;
			List<ManufacturerElement> targetList = null;
			if (totalNum > 0) {
				sourceList = dsService.showSupplierByPage((int1-1)*int2, int2, comTable);
				if (CollectionUtils.isNotEmpty(sourceList)) {
					targetList = new ArrayList<ManufacturerElement>(
							sourceList.size());
					int i = 0;
					for (ComTable sourceElement : sourceList) {
						i=++i;
						ManufacturerElement mft = new ManufacturerElement();
						mft.setId(i);
						String trim2 = sourceElement.getCol3().trim();
						mft.setAmount(trim2);
						mft.setProducer(sourceElement.getCol1());
						String trim = sourceElement.getCol2().trim();
						mft.setSales(trim);
						targetList.add(mft);
					}
				}
			}
			response.setPageNum(int1);
			response.setPageSize(int2);
			response.setTotal(totalNum);
			response.setExecutionResult(true);
			response.setRows(targetList);
		} else {
			response.setExecutionResult(false);
			response.setMessage("参数不足");
		}
		return new Gson().toJson(response);
	}

	// 销售地区统计表报表
	@RequestMapping(value = "/toAreaDetails", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String toAreaDetails(@RequestBody String skuLocationRequest,
			HttpServletRequest request) {
		PaginationResponse<SkuLocationElement> response = new PaginationResponse<SkuLocationElement>();
		if (StringUtils.isNotEmpty(skuLocationRequest)) {
			SkuLocationRequest slr = new Gson().fromJson(skuLocationRequest,
					SkuLocationRequest.class);
			if(slr.getPageNum().equals("")){
				slr.setPageNum("1");
			}
			if(slr.getPageSize().equals("")){
				slr.setPageSize("10");
			}
			int int1 = Integer.parseInt(slr.getPageNum());
			int int2 = Integer.parseInt(slr.getPageSize());
			ComTable comTable = new ComTable();
			if (StringUtils.isNotEmpty(slr.getSaleOrganizationIds())) {
				comTable.setCol10(slr.getSaleOrganizationIds());
			}
			if (StringUtils.isNotEmpty(slr.getStartDate())) {
				comTable.setCol4(slr.getStartDate());
			}
			if (StringUtils.isNotEmpty(slr.getEndDate())) {
				comTable.setCol5(slr.getEndDate());
			}
			if (StringUtils.isNotEmpty(slr.getGrade())) {
				comTable.setCol3(slr.getGrade());
			}
			if (StringUtils.isNotEmpty(slr.getProvince())) {
				comTable.setCol1(slr.getProvince());
			}
			if (StringUtils.isNotEmpty(slr.getCity())) {
				comTable.setCol2(slr.getCity());
			}
			int totalNum = dsService.findAreaNum(comTable);
			List<ComTable> sourceList = null;
			List<SkuLocationElement> targetList = null;
			if (totalNum > 0) {
				sourceList = dsService.showAreaByPage((int1-1)*int2, int2, comTable);
				if (CollectionUtils.isNotEmpty(sourceList)) {
					targetList = new ArrayList<SkuLocationElement>(
							sourceList.size());
					int i = 0;
					for (ComTable sourceElement : sourceList) {
						i = ++i;
						SkuLocationElement locationElement = new SkuLocationElement();
						locationElement.setId(i);
						locationElement.setProvince(sourceElement.getCol1());
						locationElement.setCity(sourceElement.getCol2());
						locationElement.setProducer(sourceElement.getCol3());
						locationElement.setGrade(sourceElement.getCol4());
						locationElement.setOrigin(sourceElement.getCol5());
						String trim = sourceElement.getCol6().trim();
						locationElement.setSales(trim);
						String trim2 = sourceElement.getCol7().trim();
						locationElement.setAmount(trim2);
						String trim3 = sourceElement.getCol8().trim();
						locationElement
								.setAveragePrice(trim3);
						targetList.add(locationElement);
					}
				}
			}
			response.setPageNum(int1);
			response.setPageSize(int2);
			response.setTotal(totalNum);
			response.setExecutionResult(true);
			response.setRows(targetList);
		} else {
			response.setExecutionResult(false);
			response.setMessage("参数不足");
		}
		return new Gson().toJson(response);
	}

	// 销售汇总统计表
	@RequestMapping(value = "/toSaleDetails", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String toSaleDetails(@RequestBody String skuDetailRequest,
			HttpServletRequest request) {
		PaginationResponse<SkuDetailElement> response = new PaginationResponse<SkuDetailElement>();
		if (StringUtils.isNotEmpty(skuDetailRequest)) {
			SkuDetailRequest sdr = new Gson().fromJson(skuDetailRequest,
					SkuDetailRequest.class);
			
			if(sdr.getPageNum().equals("")){
				sdr.setPageNum("1");
			}
			if(sdr.getPageSize().equals("")){
				sdr.setPageSize("10");
			}
			int int1 = Integer.parseInt(sdr.getPageNum());
			int int2 = Integer.parseInt(sdr.getPageSize());
			
			
			ComTable comTable = new ComTable();
			if (StringUtils.isNotEmpty(sdr.getSaleOrganizationIds())) {
				String trim = sdr.getSaleOrganizationIds().trim();
				comTable.setCol10(trim);
			}
			if (StringUtils.isNotEmpty(sdr.getStartDate())) {
				String trim = sdr.getStartDate().trim();
				comTable.setCol1(trim);
			}
			if (StringUtils.isNotEmpty(sdr.getEndDate())) {
				String trim = sdr.getEndDate().trim();
				comTable.setCol2(trim);
			}
			if (StringUtils.isNotEmpty(sdr.getProducer())) {
				String trim = sdr.getProducer().trim();
				comTable.setCol4(trim);
			}
			if (StringUtils.isNotEmpty(sdr.getCategory())) {
				String trim = sdr.getCategory().trim();
				comTable.setCol5(trim);
			}
			if (StringUtils.isNotEmpty(sdr.getUse())) {
				String trim = sdr.getUse().trim();
				comTable.setCol6(trim);
			}
			int totalNum = dsService.findSaleNum(comTable);
			List<ComTable> sourceList = null;
			List<SkuDetailElement> targetList = null;
			if (totalNum > 0) {
				sourceList = dsService.showSaleByPage((int1-1)*int2, int2, comTable);
				if (CollectionUtils.isNotEmpty(sourceList)) {
					targetList = new ArrayList<SkuDetailElement>(
							sourceList.size());
					int i = 0;
					for (ComTable sourceElement : sourceList) {
						i= ++i;
						SkuDetailElement skuDetailElement = new SkuDetailElement();
						skuDetailElement.setId(i);
						skuDetailElement.setProducer(sourceElement.getCol1());
						skuDetailElement.setCategory(sourceElement.getCol2());
						skuDetailElement.setUse(sourceElement.getCol3());
						skuDetailElement.setGrade(sourceElement.getCol4());
						skuDetailElement.setOrigin(sourceElement.getCol5());
						String trim = sourceElement.getCol6().trim();
						skuDetailElement.setSales(trim);
						String trim2 = sourceElement.getCol7().trim();
						skuDetailElement.setAmount(trim2);
						String trim3 = sourceElement.getCol8().trim();
						skuDetailElement.setAveragePrice(trim3);
						targetList.add(skuDetailElement);
					}
				}
			}
			response.setPageNum(int1);
			response.setPageSize(int2);
			response.setTotal(totalNum);
			response.setExecutionResult(true);
			response.setRows(targetList);
		} else {
			response.setExecutionResult(false);
			response.setMessage("参数不足");
		}
		return new Gson().toJson(response);
	}

	// 订单明细统计表
	@RequestMapping(value = "/toOrderDetails", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String toOrderDetails(@RequestBody String orderDetailRequest,
			HttpServletRequest request) {
		PaginationResponse<OrderDetailElement> response = new PaginationResponse<OrderDetailElement>();
		if (StringUtils.isNotEmpty(orderDetailRequest)) {
			OrderDetailRequest odl = new Gson().fromJson(orderDetailRequest,
					OrderDetailRequest.class);
			if(odl.getPageNum().equals("")){
				odl.setPageNum("1");
			}
			if(odl.getPageSize().equals("")){
				odl.setPageSize("10");
			}
			int int1 = Integer.parseInt(odl.getPageNum());
			int int2 = Integer.parseInt(odl.getPageSize());
			
			ComTable comTable = new ComTable();
			if (StringUtils.isNotEmpty(odl.getSaleOrganizationIds())) {
				comTable.setCol10(odl.getSaleOrganizationIds());
			}
			if (StringUtils.isNotEmpty(odl.getStartDate())) {
				String trim7 = odl.getStartDate().trim();
				comTable.setCol7(trim7);
			}
			if (StringUtils.isNotEmpty(odl.getEndDate())) {
				String trim8 = odl.getEndDate();
				comTable.setCol8(trim8);
			}
			if (StringUtils.isNotEmpty(odl.getOrderNum())) {
				String trim5 = odl.getOrderNum().trim();
				comTable.setCol5(trim5);
			}
			if (StringUtils.isNotEmpty(odl.getOrderType())) {
				if(odl.getOrderType().equals("G")){
					odl.setOrderType("挂牌");
				}
				if(odl.getOrderType().equals("D")){
					odl.setOrderType("代客下单");
				}
				if(odl.getOrderType().equals("GK")){
					odl.setOrderType("公开竞拍");
				}
				if(odl.getOrderType().equals("M")){
					odl.setOrderType("密封竞拍");
				}
				if(odl.getOrderType().equals("Y")){
					odl.setOrderType("预售");
				}
				if(odl.getOrderType().equals("T")){
					odl.setOrderType("团购");
				}
				if(odl.getOrderType().equals("J")){
					odl.setOrderType("计划");
				}
				String trim1 = odl.getOrderType().trim();
				comTable.setCol1(trim1);
			}
			if(StringUtils.isNotEmpty(odl.getOrderstate())){
				if(odl.getOrderstate().equals("1")){
					odl.setOrderstate("待支付");
				}
				if(odl.getOrderstate().equals("2")){
					odl.setOrderstate("待业务经理审批");
				}
				if(odl.getOrderstate().equals("3")){
					odl.setOrderstate("业务经理审批已通过");
				}
				if(odl.getOrderstate().equals("4")){
					odl.setOrderstate("业务经理审批未通过");
				}
				if(odl.getOrderstate().equals("5")){
					odl.setOrderstate("已取消");
				}
				if(odl.getOrderstate().equals("6")){
					odl.setOrderstate("已支付");
				}
				String trim2 = odl.getOrderstate().trim();
				comTable.setCol2(trim2);
			}
				
			if (StringUtils.isNotEmpty(odl.getGrade())) {
				String trim6 = odl.getGrade().trim();
				comTable.setCol6(trim6);
			}
			if (StringUtils.isNotEmpty(odl.getWarehouse())) {
				String trim9 = odl.getWarehouse();
				comTable.setCol11(trim9);
			}
			if (StringUtils.isNotEmpty(odl.getBuyerName())) {
				String trim4 = odl.getBuyerName().trim();
				comTable.setCol4(trim4);
			}
			if (StringUtils.isNotEmpty(odl.getInvoiceState())) {
				String trim3 = odl.getInvoiceState().trim();
				comTable.setCol3(trim3);
			}
			int totalNum = dsService.findOrderNum(comTable);
			List<ComTable> sourceList = null;
			List<OrderDetailElement> targetList = null;
			if (totalNum > 0) {
				sourceList = dsService.showOrderByPage((int1-1)*int2, int2, comTable);
				if (CollectionUtils.isNotEmpty(sourceList)) {
					targetList = new ArrayList<OrderDetailElement>(
							sourceList.size());
					int i = 0 ; 
					for (ComTable sourceElement : sourceList) {
						i = ++i;
						OrderDetailElement detailElement = new OrderDetailElement();
						detailElement.setId(i);
						detailElement.setOrderNum(sourceElement.getCol1());
						detailElement.setOrderType(sourceElement.getCol2());
						detailElement.setOrderState(sourceElement.getCol3());
						double aa = Double.parseDouble(sourceElement.getCol4());
						Double double3 = formatDouble1(aa);
						detailElement.setOrderAmount(double3);
						if(sourceElement.getCol5()!=null){
							detailElement.setChangeDesc(sourceElement.getCol5());
						}else {
							detailElement.setChangeDesc("");
						}
						
						if(sourceElement.getCol6()==null){
							detailElement.setPaymentType(0);
						}else {
							Integer integer = Integer.valueOf(sourceElement.getCol6());
							detailElement.setPaymentType(integer);
						}
						if(sourceElement.getCol7()!=null){
							detailElement.setPaymentDesc(sourceElement.getCol7());
						}else {
							detailElement.setPaymentDesc("");
						}
						if(sourceElement.getCol9()!=null){
							detailElement.setInvoiceState(sourceElement.getCol8());
						}else {
							detailElement.setInvoiceState("未开票");
						}
						if(sourceElement.getCol8()!=null){
							detailElement.setBuyerNum(sourceElement.getCol9());
						}else{
							detailElement.setBuyerNum("");
						}
						if(sourceElement.getCol10()!=null){
							detailElement.setBuyerName(sourceElement.getCol10());
						}else{
							detailElement.setBuyerName("");
						}
						if(sourceElement.getCol11()!=null){
							detailElement.setSellerNum(sourceElement.getCol11());
						}else{
							detailElement.setSellerNum("");
						}
						if(sourceElement.getCol12()!=null){
							detailElement.setSellerName(sourceElement.getCol12());
						}else{
							detailElement.setSellerName("");
						}
						if(sourceElement.getCol13()!=null){
							detailElement.setCheckDate(sourceElement.getCol13());
						}else{
							detailElement.setCheckDate("");
						}
						
						if(sourceElement.getCol14()!=null){
							detailElement.setChecker(sourceElement.getCol14());
						}else{
							detailElement.setChecker("");
						}
						if(sourceElement.getCol15()!=null){
							detailElement.setSalesman(sourceElement.getCol15());
						}else{
							detailElement.setSalesman("");
						}
						if(sourceElement.getCol16()!=null){
							detailElement.setApprover(sourceElement.getCol16());
						}else {
							detailElement.setApprover("");
						}
						
						if(sourceElement.getCol17()!=null){
							detailElement.setApprovalOpin(sourceElement.getCol17());
						}else {
							detailElement.setApprovalOpin("");
						}
						
						if(sourceElement.getCol18()!=null){
							detailElement.setProductId(sourceElement.getCol18());
						}else {
							detailElement.setProductId("");
						}
						
						if(sourceElement.getCol19()!=null){
							detailElement.setCategory(sourceElement.getCol19());
						}else {
							detailElement.setCategory("");
						}
						if(sourceElement.getCol20()!=null){
							detailElement.setGrade(sourceElement.getCol20());
						}else {
							detailElement.setGrade("");
						}
						
						if(sourceElement.getCol21()!=null){
							detailElement.setWarehouse(sourceElement.getCol21());
						}else{
							detailElement.setWarehouse("");
						}
						
						if(sourceElement.getCol22()!=null){
							detailElement.setWarehouseNum(sourceElement.getCol22());
						}else{
							detailElement.setWarehouseNum("");
						}
						if(sourceElement.getCol23()!=null){
							double ab = Double.parseDouble(sourceElement.getCol23());
							double formatDouble5 = formatDouble1(ab);
							detailElement.setListPrice(formatDouble5);
						}else {
							detailElement.setListPrice(0.0);
						}
						if(sourceElement.getCol24()!=null){
							double bb = Double.parseDouble(sourceElement.getCol24());
							double formatDouble4 = formatDouble1(bb);
							detailElement.setSalePrice(formatDouble4);
						}else {
							detailElement.setSalePrice(0.0);
						}
						if(sourceElement.getCol25()!=null){
							double ee = Double.parseDouble(sourceElement.getCol25());
							double formatDouble1 = formatDouble1(ee);
							detailElement.setInventory(formatDouble1);
						}else {
							detailElement.setInventory(0.0);
						}
				        
						if(sourceElement.getCol26()!=null){
							double ff = Double.parseDouble(sourceElement.getCol26());
							double formatDouble3 = formatDouble1(ff);
							detailElement.setAmount(formatDouble3);
						}else {
							detailElement.setAmount(0.0);
						}
						if(sourceElement.getCol27()==null){
							detailElement.setFreight(0.0);
						}else {
							double gg = Double.parseDouble(sourceElement.getCol27());
							double formatDouble2 = formatDouble1(gg);
							detailElement.setFreight(formatDouble2);
						}
						targetList.add(detailElement);
					}
				}
			}
			response.setPageNum(int1);
			response.setPageSize(int2);
			response.setTotal(totalNum);
			response.setExecutionResult(true);
			response.setRows(targetList);
		} else {
			response.setExecutionResult(false);
			response.setMessage("参数不足");
		}
		return new Gson().toJson(response);
	}
	// 客户销售统计表报表
	@RequestMapping(value = "/showSaleSummaryByPage", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String showSaleSummaryByPage(@RequestBody String saleSummeryRequest,
			HttpServletRequest request) throws Exception {
		String trim = saleSummeryRequest.trim();

		PaginationResponse<SaleSummeryElement> response = new PaginationResponse<SaleSummeryElement>();
		if (StringUtils.isNotEmpty(saleSummeryRequest)) {
            
			
			SaleSummeryRequest cts = new Gson().fromJson(trim,
					SaleSummeryRequest.class);
			if(cts.getPageNum().equals("")){
				cts.setPageNum("1");
			}
			if(cts.getPageSize().equals("")){
				cts.setPageSize("10");
			}
			int int1 = Integer.parseInt(cts.getPageNum());
			int int2 = Integer.parseInt(cts.getPageSize());
			
			int totalNum = 1;
			List<SaleSummeryElement> targetList = dsService.findSummaryList(cts);

			 response.setPageNum(int1);
			 response.setPageSize(int2);
			response.setTotal(totalNum);
			response.setExecutionResult(true);
			response.setRows(targetList);
		} else {
			response.setExecutionResult(false);
			response.setMessage("参数不足");
		}
		return new Gson().toJson(response);
	}
	public static double formatDouble1(double d) {
        return (double)Math.round(d*100)/100;
    }
}

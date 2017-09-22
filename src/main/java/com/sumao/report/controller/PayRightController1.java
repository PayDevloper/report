package com.sumao.report.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.sumao.controller.BaseController;
import com.sumao.dao.Pay_rightgoldMapper;
import com.sumao.dao.PaybondMapper;
import com.sumao.dao.PaycostpayMapper;
import com.sumao.model.EasyuiDataGrid;
import com.sumao.model.EasyuiDataGridJson;
import com.sumao.model.Pay_rightgold;
import com.sumao.model.Pay_rightgoldcheck;
import com.sumao.model.Paybond;
import com.sumao.model.Paycostpay;
import com.sumao.report.depositfee.pojo.DepositFeeElement;
import com.sumao.report.depositfee.pojo.DepositFeeRequest;
import com.sumao.report.orderfee.pojo.OrderFeeElement;
import com.sumao.report.orderfee.pojo.OrderFeeRequest;
import com.sumao.report.pojo.PaginationResponse;
import com.sumao.report.qlj.pojo.QljElement;
import com.sumao.report.qlj.pojo.QljRequest;
import com.sumao.service.PayRightService;
import com.sumao.service.PaybondService;
import com.sumao.service.PaycostpayService;

/**
 * 会员费资质设定控制器
 * 
 * @author 黑洁净
 * 
 */
@Controller
@RequestMapping("/payRight")
public class PayRightController1 extends BaseController {

	private static final Logger logger = Logger
			.getLogger(PayRightController1.class);

	private PayRightService payRightService;
	// 权利金
	@Autowired
	private Pay_rightgoldMapper payDao;

	// 保证金
	@Autowired
	private PaycostpayMapper paycostpayDao;
	
	//费用明细
	@Autowired
	private PaybondService paybondService;
	
	@Autowired
	private PaycostpayService paycostpayservice;

	public PayRightService getPayRightService() {
		return payRightService;
	}

	@Autowired
	public void setPayRightService(PayRightService payRightService) {
		this.payRightService = payRightService;
	}

	// 权利金缴费查看
	@RequestMapping(value = "query", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public String queryPayRight(@RequestBody String query,
			HttpServletRequest request) {
		PaginationResponse<QljElement> response = new PaginationResponse<QljElement>();
		if (StringUtils.isNotEmpty(query)) {
			QljRequest qljRequest = new Gson()
					.fromJson(query, QljRequest.class);
			if(qljRequest.getPageNum().equals("")){
				qljRequest.setPageNum("1");
			}
			if(qljRequest.getPageSize().equals("")){
				qljRequest.setPageSize("10");
			}
			Pay_rightgoldcheck payQuery1 = new Pay_rightgoldcheck();

			// 把 qljRequest中的参数塞入到payQuery中
			if (StringUtils.isNotEmpty(qljRequest.getSaleOrganizationIds())) {
				String trim = qljRequest.getSaleOrganizationIds().trim();
				payQuery1.setMarketingid(trim);
			}
			if (StringUtils.isNotEmpty(qljRequest.getStartDate())) {
				payQuery1.setStartdate(qljRequest.getStartDate());
			}
			if(StringUtils.isEmpty(qljRequest.getStartDate())){
				payQuery1.setStartdate("1900-01-01");
			}
			if (StringUtils.isNotEmpty(qljRequest.getEndDate())) {
				payQuery1.setEnddate(qljRequest.getEndDate());
			}
			if (StringUtils.isEmpty(qljRequest.getEndDate())) {
				payQuery1.setEnddate("2900-12-31");
			}
			if (StringUtils.isNotEmpty(qljRequest.getPaymentState())) {
				payQuery1.setPayment(qljRequest.getPaymentState());
			}
			if(StringUtils.isNotEmpty(qljRequest.getQlj())){
				payQuery1.setSubitemname(qljRequest.getQlj());
			}

			int totalNum = payRightService.findPayrchTotal(payQuery1);
			
			List<Pay_rightgoldcheck> sourceList = null;
			List<QljElement> targetList = null;
			int int1 = Integer.parseInt(qljRequest.getPageNum());
			int int2 = Integer.parseInt(qljRequest.getPageSize());
			if (totalNum > 0) {
				EasyuiDataGrid dg = new EasyuiDataGrid();
				dg.setPage(int1);
				dg.setRows(int2);
				EasyuiDataGridJson datagridcost = payRightService.datagridch(dg, payQuery1, totalNum);
				sourceList = datagridcost.getRows();
				if (CollectionUtils.isNotEmpty(sourceList)) {
					targetList = new ArrayList<QljElement>(sourceList.size());
					int i = 0;
					for (Pay_rightgoldcheck sourceElement : sourceList) {
						i = ++i;
						QljElement targetElement = new QljElement();
						targetElement.setId(i);
						// 赛值
						targetElement.setPayNum(sourceElement.getId());
						targetElement.setQlj(sourceElement.getSubitemname());
						targetElement.setEffectStartDate(sourceElement.getStartdate());
						targetElement.setEffectEndDate(sourceElement.getEnddate());
							// TODO Auto-generated catch block
						targetElement.setCurrentState(sourceElement
								.getCurrentstate());
						double d = Double.parseDouble(sourceElement
								.getPayamount());
						targetElement.setPaymentAmount(d);
						targetElement.setPaymentState(sourceElement
								.getPayment());
						if(sourceElement.getShouldpay().equals("--")){
							targetElement.setAmountPayable(0.00);
						}else{
							double c = Double.parseDouble(sourceElement
									.getShouldpay());
							targetElement.setAmountPayable(c);
						}
						if(sourceElement.getActualpay().equals("--")){
							targetElement.setActualPayment(0.00);
						}else{
							double b = Double.parseDouble(sourceElement
									.getActualpay());
							targetElement.setActualPayment(b);
						}
						
						targetElement.setPaymentType(sourceElement
								.getPaymentmethod());
						targetElement
								.setPaymentDate(sourceElement.getPaytime());
						targetList.add(targetElement);
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

	// 保证金缴费查看
	@RequestMapping(value = "paybondcostpay", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String paybondcostpay(@RequestBody String depositFeeRequest,
			HttpServletRequest request) {
		PaginationResponse<DepositFeeElement> response = new PaginationResponse<DepositFeeElement>();
		if (StringUtils.isNotEmpty(depositFeeRequest)) {
			DepositFeeRequest dtf = new Gson().fromJson(depositFeeRequest,
					DepositFeeRequest.class);
			if(dtf.getPageNum().equals("")){
				dtf.setPageNum("1");
			}
			if(dtf.getPageSize().equals("")){
				dtf.setPageSize("10");
			}
			Paycostpay payr = new Paycostpay();
			if (StringUtils.isNotEmpty(dtf.getSaleOrganizationIds())) {
				String trim = dtf.getSaleOrganizationIds().trim();
				payr.setMarketingid(trim);
			}
			if (StringUtils.isNotEmpty(dtf.getPaymentState())) {
				payr.setFinanceacc(dtf.getPaymentState());
			}
			int totalNum = paycostpayservice.findPayrcostTotal(payr);
			List<Paycostpay> sourceList = null;
			List<DepositFeeElement> targetList = null;
			int int1 = Integer.parseInt(dtf.getPageNum());
			int int2 = Integer.parseInt(dtf.getPageSize());
			if (totalNum > 0) {
				EasyuiDataGrid dg = new EasyuiDataGrid();
				dg.setPage(int1);
				dg.setRows(int2);
				EasyuiDataGridJson datagridcost = paycostpayservice.datagridcost(dg, payr, totalNum);
				sourceList = datagridcost.getRows();
				if (CollectionUtils.isNotEmpty(sourceList)) {
					int i = 0;
					targetList = new ArrayList<DepositFeeElement>(sourceList.size());
					for (Paycostpay sourceElement : sourceList) {
						i = ++i;
						DepositFeeElement depositFeeElement = new DepositFeeElement();
                        depositFeeElement.setId(i);
						depositFeeElement.setPayNum(sourceElement.getId());
						depositFeeElement.setMinPaymentAmount(sourceElement
								.getSubitemname());
						depositFeeElement.setWarningValue(sourceElement
								.getTotalitemname());
						depositFeeElement.setPaymentState(sourceElement
								.getFinanceacc());
						depositFeeElement.setActualPayment(sourceElement
								.getActualpay());
						depositFeeElement.setPaymentType(sourceElement
								.getPaymentmethod());
						depositFeeElement
								.setPaymentDate(sourceElement.getPaytime());

						targetList.add(depositFeeElement);
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

	// 费用明细
	@RequestMapping(value = "paybondhis", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String paybondHis(@RequestBody String OrderFeeRequest,
			HttpServletRequest request) {
		PaginationResponse<OrderFeeElement> response = new PaginationResponse<OrderFeeElement>();
		if (StringUtils.isNotEmpty(OrderFeeRequest)) {
			OrderFeeRequest ofr = new Gson().fromJson(OrderFeeRequest,
					OrderFeeRequest.class);
			if(ofr.getPageNum().equals("")){
				ofr.setPageNum("1");
			}
			if(ofr.getPageSize().equals("")){
				ofr.setPageSize("10");
			}
			Paybond paybond = new Paybond();
			if (StringUtils.isNotEmpty(ofr.getSaleOrganizationIds())) {
				String trim = ofr.getSaleOrganizationIds().trim();
				paybond.setMarketingid(trim);
			}
			if (StringUtils.isNotEmpty(ofr.getStartDate())) {
				String trim = ofr.getStartDate().trim();
                paybond.setCreatetime(trim);
			}
			if(StringUtils.isEmpty(ofr.getStartDate())){
				 paybond.setCreatetime("1900-01-01");
			}
			if (StringUtils.isNotEmpty(ofr.getEndDate())) {
				String trim = ofr.getEndDate().trim();
                paybond.setRemark(trim);
			}
			if (StringUtils.isEmpty(ofr.getEndDate())) {
                paybond.setRemark("2900-12-31");
			}
			if (StringUtils.isNotEmpty(ofr.getHappenType())) {
				String trim = ofr.getHappenType().trim();
                paybond.setRemark2(trim);
			}
			if (StringUtils.isNotEmpty(ofr.getOrderId())) {
				String trim = ofr.getOrderId().trim();
                paybond.setCodeid(trim);
			}
			int totalNum = paybondService.findPayrHisTotal(paybond);
			List<Paybond> sourceList = null;
			List<OrderFeeElement> targetList = null;
			int int1 = Integer.parseInt(ofr.getPageNum());
			int int2 = Integer.parseInt(ofr.getPageSize());
			if(totalNum>0){
				EasyuiDataGrid dg = new EasyuiDataGrid();
				dg.setPage(int1);
				dg.setRows(int2);
				 EasyuiDataGridJson datagridcost = paybondService.datagridHis(dg, paybond, totalNum);
				 sourceList = datagridcost.getRows();
				 if (CollectionUtils.isNotEmpty(sourceList)) {
					 targetList = new ArrayList<OrderFeeElement>(sourceList.size());
					 int i = 0;
					 for (Paybond sourceElement : sourceList) {
						 i = ++i;
							OrderFeeElement orderFeeElement = new OrderFeeElement();
							orderFeeElement.setId(i);
								orderFeeElement.setHappenDate(sourceElement.getCreatetime());
							double d = Double.parseDouble(sourceElement.getRemark1());
							orderFeeElement.setHappenAmount(d);
							double e = Double.parseDouble(sourceElement.getHavedeposit());
							orderFeeElement.setCurrentAmount(e);
							orderFeeElement.setHappenType(sourceElement.getRemark2());
							orderFeeElement.setOrderId(sourceElement.getCodeid());
							orderFeeElement.setIsWarning(sourceElement.getWhetheralerted());
							targetList.add(orderFeeElement);
				 }
			}
			}
			response.setPageNum(int1);
			response.setPageSize(int2);
			response.setTotal(totalNum);
			response.setExecutionResult(true);
			response.setRows(targetList);
		}else {
			response.setExecutionResult(false);
			response.setMessage("参数不足");
		}

		return new Gson().toJson(response);
	}

}

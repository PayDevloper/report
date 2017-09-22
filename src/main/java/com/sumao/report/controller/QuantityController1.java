package com.sumao.report.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.sumao.model.ClientTop;
import com.sumao.model.CountNum;
import com.sumao.report.pojo.PaginationResponse;
import com.sumao.report.summary.pojo.CustomerQuantityInfo;
import com.sumao.report.summary.pojo.DailySalesSummary;
import com.sumao.report.summary.pojo.OrderStateInfo;
import com.sumao.report.summary.pojo.SalesRank;
import com.sumao.report.summary.pojo.SalesSummary;
import com.sumao.report.summary.pojo.SummaryRequest;
import com.sumao.report.summary.pojo.SummaryResponse;
import com.sumao.report.summary.pojo.TopInfo;
import com.sumao.service.QuantityService;

@Controller
@RequestMapping("/quantityTops")
public class QuantityController1 {

	@Autowired
	private QuantityService quantityService;

	// 首页信息查询
	@RequestMapping(value = "topCustom", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String topCustom(@RequestBody String summaryRequest, HttpServletRequest request) throws Exception {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateNowStr = sdf.format(d);
		// 最终返回的集合
		SummaryResponse summres = new SummaryResponse();
		PaginationResponse<SummaryResponse> response = new PaginationResponse<SummaryResponse>();
		if (StringUtils.isNotEmpty(summaryRequest)) {
			SummaryRequest sr = new Gson().fromJson(summaryRequest, SummaryRequest.class);
			if (sr.getStartDate().equals("")) {
				sr.setStartDate(dateNowStr);
			}
			if (sr.getEndDate().equals("")) {
				sr.setEndDate(dateNowStr);
			}

			String dateinfo = sr.getStartDate() + " " + "00:00:00";
			String dateend = sr.getEndDate() + " " + "23:59:59";
			String orgnid = sr.getSaleOrganizationIds();
			List<ClientTop> products = quantityService.findProducts(dateinfo, dateend, orgnid);
			List<ClientTop> clients = quantityService.findClients(dateinfo, dateend, orgnid);
			List<ClientTop> suplier = quantityService.findSuplier(dateinfo, dateend, orgnid);
			List<ClientTop> area = quantityService.findArea(dateinfo, dateend, orgnid);
			CountNum countnumClient = quantityService.CountClient(dateinfo, dateend, orgnid);
			CountNum countnumAll = quantityService.CountAll(dateinfo, dateend, orgnid);
			CountNum countnumOrder = quantityService.CountOrder(dateinfo, dateend, orgnid);
			// 最终返回

			// 设置销售汇总
			SalesSummary salesSummary = new SalesSummary();
			salesSummary.setSales(countnumAll.getCol11());
			salesSummary.setAmount(countnumAll.getCol10());
			salesSummary.setCustomerQuantity(countnumAll.getCol5());
			salesSummary.setOrderQuantity(countnumAll.getCol12());
			String valueOf = String.valueOf(countnumAll.getCol4());
			salesSummary.setOutStockQuantity(valueOf);
			String valueOf1 = String.valueOf(countnumAll.getCol6());
			salesSummary.setNewlyCustomerQuantity(valueOf1);
			summres.setSalesSummary(salesSummary);
			// ----------------------------------------------------

			// 设置销售排行
			SalesRank salesRank = new SalesRank();

			List<TopInfo> list = new ArrayList<TopInfo>(products.size());
			for (ClientTop sourceElement : products) {
				TopInfo topInfo = new TopInfo();
				if (StringUtils.isNotEmpty(sourceElement.getTotalPricec())) {
					String trim = sourceElement.getTotalPricec().trim();
					topInfo.setAmount(trim);
				} else {
					topInfo.setAmount("0.0");
				}
				if (StringUtils.isNotEmpty(sourceElement.getAveragec())) {
					String trim1 = sourceElement.getAveragec().trim();
					topInfo.setAveragePrice(trim1);
				} else {
					topInfo.setAveragePrice("0.0");
				}
				if (StringUtils.isNotEmpty(sourceElement.getTotalQuantityc())) {
					String trim2 = sourceElement.getTotalQuantityc().trim();
					topInfo.setSales(trim2);
				} else {
					topInfo.setSales("0.0");
				}
				topInfo.setObjectDesc(sourceElement.getSort());
				topInfo.setRanking(sourceElement.getSerialc());
				list.add(topInfo);
			}
			if (list.size() > 5) {
				List<TopInfo> subList = list.subList(0, 4);
				salesRank.setHotProductTop(subList);
			} else {
				salesRank.setHotProductTop(list);
			}

			List<TopInfo> list1 = new ArrayList<TopInfo>(clients.size());
			for (ClientTop sourceElement : clients) {
				TopInfo topInfo = new TopInfo();
				if (StringUtils.isNotEmpty(sourceElement.getTotalPricec())) {
					String trim = sourceElement.getTotalPricec().trim();
					topInfo.setAmount(trim);
				} else {
					topInfo.setAmount("0.0");
				}
				if (StringUtils.isNotEmpty(sourceElement.getAveragec())) {
					String trim1 = sourceElement.getAveragec().trim();
					topInfo.setAveragePrice(trim1);
				} else {
					topInfo.setAveragePrice("0.0");
				}
				if (StringUtils.isNotEmpty(sourceElement.getTotalQuantityc())) {
					String trim2 = sourceElement.getTotalQuantityc().trim();
					topInfo.setSales(trim2);
				} else {
					topInfo.setSales("0.0");
				}
				topInfo.setObjectDesc(sourceElement.getSort());
				topInfo.setRanking(sourceElement.getSerialc());
				list1.add(topInfo);
			}
			if (list1.size() > 5) {
				List<TopInfo> subList1 = list1.subList(0, 4);
				salesRank.setCustomerTop(subList1);
			} else {
				salesRank.setCustomerTop(list1);
			}

			List<TopInfo> list2 = new ArrayList<TopInfo>(suplier.size());
			for (ClientTop sourceElement : suplier) {
				TopInfo topInfo = new TopInfo();
				if (StringUtils.isNotEmpty(sourceElement.getTotalPricec())) {
					String trim = sourceElement.getTotalPricec().trim();
					topInfo.setAmount(trim);
				} else {
					topInfo.setAmount("0.0");
				}
				if (StringUtils.isNotEmpty(sourceElement.getAveragec())) {
					String trim1 = sourceElement.getAveragec().trim();
					topInfo.setAveragePrice(trim1);
				} else {
					topInfo.setAveragePrice("0.0");
				}
				if (StringUtils.isNotEmpty(sourceElement.getTotalQuantityc())) {
					String trim2 = sourceElement.getTotalQuantityc().trim();
					topInfo.setSales(trim2);
				} else {
					topInfo.setSales("0.0");
				}
				topInfo.setObjectDesc(sourceElement.getSort());
				topInfo.setRanking(sourceElement.getSerialc());
				list2.add(topInfo);
			}
			if (list2.size() > 5) {
				List<TopInfo> subList3 = list2.subList(0, 4);
				salesRank.setProducerTop(subList3);

			} else {
				salesRank.setProducerTop(list2);
			}

			List<TopInfo> list3 = new ArrayList<TopInfo>(area.size());
			for (ClientTop sourceElement : area) {
				TopInfo topInfo = new TopInfo();
				if (StringUtils.isNotEmpty(sourceElement.getTotalPricec())) {
					String trim = sourceElement.getTotalPricec().trim();
					topInfo.setAmount(trim);
				} else {
					topInfo.setAmount("0.0");
				}
				if (StringUtils.isNotEmpty(sourceElement.getAveragec())) {
					String trim1 = sourceElement.getAveragec().trim();
					topInfo.setAveragePrice(trim1);
				} else {
					topInfo.setAveragePrice("0.0");
				}
				if (StringUtils.isNotEmpty(sourceElement.getTotalQuantityc())) {
					String trim2 = sourceElement.getTotalQuantityc().trim();
					topInfo.setSales(trim2);
				} else {
					topInfo.setSales("0.0");
				}
				topInfo.setObjectDesc(sourceElement.getSort());
				topInfo.setRanking(sourceElement.getSerialc());
				list3.add(topInfo);
			}
			if (list3.size() > 5) {
				List<TopInfo> subList4 = list3.subList(0, 4);
				salesRank.setHotSellRegionTop(subList4);
			} else {
				salesRank.setHotSellRegionTop(list3);
			}

			// 设置订单状态概览
			OrderStateInfo stateInfo = new OrderStateInfo();
			//Integer valueOf2 = Integer.valueOf(countnumOrder.getCol10().replace(",", ""));
			stateInfo.setOrderQuantity(countnumOrder.getCol10());
			//Integer valueOf3 = Integer.valueOf(countnumOrder.getCol11().replace(",", ""));
			stateInfo.setPrepaidQuantity(countnumOrder.getCol11());
			//Integer valueOf4 = Integer.valueOf(countnumOrder.getCol12().replace(",", ""));
			stateInfo.setTobepaidQuantity(countnumOrder.getCol12());
			//Integer valueOf5 = Integer.valueOf(countnumOrder.getCol13().replace(",", ""));
			stateInfo.setCancelOrderQuantity(countnumOrder.getCol13());

			// 设置客户数量概览
			CustomerQuantityInfo customerQuantityInfo = new CustomerQuantityInfo();
			customerQuantityInfo.setCustomerquantity(countnumAll.getCol5());
			customerQuantityInfo.setNewlyCustomerQuantity(countnumAll.getCol6());

			// 设置每日销量概览

			List<DailySalesSummary> jihe = null;
			if (StringUtils.isNotEmpty(sr.getStartDate()) && StringUtils.isNotEmpty(sr.getEndDate())) {
				jihe = quantityService.findChartData(dateinfo, dateend, orgnid);
				if (jihe != null && jihe.size() > 0) {
				} else {
					jihe.clear();
					DailySalesSummary startdaysale = new DailySalesSummary();
					startdaysale.setSales("0.0");
					startdaysale.setAmount("0");
					startdaysale.setDay(sr.getStartDate());
					DailySalesSummary enddaysale = new DailySalesSummary();
					enddaysale.setSales("0.0");
					enddaysale.setAmount("0");
					enddaysale.setDay(sr.getEndDate());
					jihe.add(startdaysale);
					jihe.add(enddaysale);
				}
			}
			/**
			 * Date start =null; Date end =null; List<DailySalesSummary> jihe =
			 * null;
			 * if(StringUtils.isNotEmpty(sr.getStartDate())&&StringUtils.isNotEmpty(sr.getEndDate())){
			 * start = sdf.parse(sr.getStartDate()); end =
			 * sdf.parse(sr.getEndDate()); int daycount =
			 * daysBetween(sr.getStartDate(), sr.getEndDate()); daycount =
			 * daycount + 1; if(sr.getStartDate().equals(sr.getEndDate())){
			 * daycount = 1; }
			 * 
			 * jihe =new ArrayList<DailySalesSummary>(daycount); Calendar
			 * calendar = new GregorianCalendar(); calendar.setTime(start); for
			 * (int i = 0; i < daycount; i++) { DailySalesSummary daysale = new
			 * DailySalesSummary(); if(i!=0){
			 * calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动 } start =
			 * calendar.getTime();
			 * 
			 * String string = sdf.format(start);
			 * 
			 * daysale.setDay(string);
			 * 
			 * String aa = sdf.format(start)+""+"00:00:00"; String bb =
			 * sdf.format(start)+""+"23:59:59"; CountNum countnumAll1 =
			 * quantityService.CountAll(aa, bb, orgnid);
			 * if(StringUtils.isNotEmpty(countnumAll1.getCol11())){
			 * 
			 * String trim = countnumAll1.getCol11().trim();
			 * daysale.setSales(trim); }else { daysale.setSales("0.0"); }
			 * if(StringUtils.isNotEmpty(countnumAll1.getCol10())){ String trim1
			 * = countnumAll1.getCol10().trim(); daysale.setAmount(trim1); }else
			 * { daysale.setAmount("0"); }
			 * 
			 * jihe.add(daysale); } }
			 */

			summres.setCustomerQuantityInfo(customerQuantityInfo);
			summres.setSalesSummary(salesSummary);
			summres.setOrderStateInfo(stateInfo);
			summres.setSalesRank(salesRank);
			summres.setDailySalesSummary(jihe);
			List<SummaryResponse> summres1 = new ArrayList<SummaryResponse>();
			summres1.add(summres);
			response.setRows(summres1);
		} else {
			response.setExecutionResult(false);
			response.setMessage("参数不足");
		}
		return new Gson().toJson(summres);
	}

	public static int daysBetween(String smdate, String bdate) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(smdate));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(bdate));
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

}

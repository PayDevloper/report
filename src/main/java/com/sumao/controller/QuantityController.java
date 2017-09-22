package com.sumao.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sumao.model.ClientTop;
import com.sumao.model.ComTable;
import com.sumao.model.CountNum;
import com.sumao.service.PermisService;
import com.sumao.service.QuantityService;
/**
 * Top10信息控制层
 * 
 * @author Liutong Version 1.0
 */
@Controller
@RequestMapping("/quantityTops")
public class QuantityController {

	@Resource
	private QuantityService quantityService;
	@Resource
	private PermisService permisService;

	public PermisService getPermisService() {
		return permisService;
	}

	@Autowired
	public void setPermisService(PermisService permisService) {
		this.permisService = permisService;
	}

	public QuantityService getQuantityService() {
		return quantityService;
	}

	@Autowired
	public void setQuantityService(QuantityService quantityService) {
		this.quantityService = quantityService;
	}

	/**
	 * 显示销售汇总的top10信息（首页）
	 */
	@RequestMapping(params = "topToday")
	public String topToday(HttpServletRequest request) {
		String dateinfo = "day";
		String dateend = "day";
		String orgnid=request.getParameter("orgnid").toString().trim();
		List<ClientTop> products = quantityService.findProducts(dateinfo, dateend, orgnid);
		List<ClientTop> clients = quantityService.findClients(dateinfo, dateend, orgnid);
		List<ClientTop> suplier = quantityService.findSuplier(dateinfo, dateend, orgnid);
		List<ClientTop> area = quantityService.findArea(dateinfo, dateend, orgnid);
		CountNum countnumClient = quantityService.CountClient(dateinfo, dateend, orgnid);
		CountNum countnumAll = quantityService.CountAll(dateinfo, dateend, orgnid);
		CountNum countnumOrder = quantityService.CountOrder(dateinfo, dateend, orgnid);
		request.setAttribute("products", products);
		request.setAttribute("clients", clients);
		request.setAttribute("suplier", suplier);
		request.setAttribute("area", area);
		request.setAttribute("countnum", countnumClient);
		request.setAttribute("countnumAll", countnumAll);
		request.setAttribute("countnumOrder", countnumOrder);
		request.setAttribute("orgnid", orgnid);
		return "/admin/TopsList";
	}
	/**
	 * 显示销售汇总的top10信息（本周）
	 */
	@RequestMapping(params = "topWeek")
	public String topWeek(HttpServletRequest request,HttpServletResponse response) {
		String dateinfo = "week";
		String dateend = "week";
		String orgnid=request.getParameter("orgnid").toString().trim();
		List<ClientTop> products = quantityService.findProducts(dateinfo, dateend, orgnid);
		List<ClientTop> clients = quantityService.findClients(dateinfo, dateend, orgnid);
		List<ClientTop> suplier = quantityService.findSuplier(dateinfo, dateend, orgnid);
		List<ClientTop> area = quantityService.findArea(dateinfo, dateend, orgnid);
		CountNum countnumClient = quantityService.CountClient(dateinfo, dateend, orgnid);
		CountNum countnumAll = quantityService.CountAll(dateinfo, dateend, orgnid);
		CountNum countnumOrder = quantityService.CountOrder(dateinfo, dateend, orgnid);
		request.setAttribute("products", products);
		request.setAttribute("clients", clients);
		request.setAttribute("suplier", suplier);
		request.setAttribute("area", area);
		request.setAttribute("countnum", countnumClient);
		request.setAttribute("countnumAll", countnumAll);
		request.setAttribute("countnumOrder", countnumOrder);
		return "/admin/TopsWeek";
	}

	/**
	 * 显示销售汇总的top10信息（本月）
	 */
	@RequestMapping(params = "topMonth")
	public String topMonth(HttpServletRequest request) {
		String dateinfo = "month";
		String dateend = "month";
		String orgnid=request.getParameter("orgnid").toString().trim();
		List<ClientTop> products = quantityService.findProducts(dateinfo, dateend, orgnid);
		List<ClientTop> clients = quantityService.findClients(dateinfo, dateend, orgnid);
		List<ClientTop> suplier = quantityService.findSuplier(dateinfo, dateend, orgnid);
		List<ClientTop> area = quantityService.findArea(dateinfo, dateend, orgnid);
		CountNum countnumClient = quantityService.CountClient(dateinfo, dateend, orgnid);
		CountNum countnumAll = quantityService.CountAll(dateinfo, dateend, orgnid);
		CountNum countnumOrder = quantityService.CountOrder(dateinfo, dateend, orgnid);
		request.setAttribute("products", products);
		request.setAttribute("clients", clients);
		request.setAttribute("suplier", suplier);
		request.setAttribute("area", area);
		request.setAttribute("countnum", countnumClient);
		request.setAttribute("countnumAll", countnumAll);
		request.setAttribute("countnumOrder", countnumOrder);
		return "/admin/TopsMonth";
	}

	/**
	 * 显示销售汇总的top10信息（自定义）
	 */
	@RequestMapping(params = "topCustom")
	public String topCustom(HttpServletRequest request, ComTable comtable) {
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		String dateinfo = start + " " + "00:00:00";
		String dateend = end + " " + "23:59:59";
		String orgnid=request.getParameter("orgnid").toString().trim();
		List<ClientTop> products = quantityService.findProducts(dateinfo, dateend, orgnid);
		List<ClientTop> clients = quantityService.findClients(dateinfo, dateend, orgnid);
		List<ClientTop> suplier = quantityService.findSuplier(dateinfo, dateend, orgnid);
		List<ClientTop> area = quantityService.findArea(dateinfo, dateend, orgnid);
		CountNum countnumClient = quantityService.CountClient(dateinfo, dateend, orgnid);
		CountNum countnumAll = quantityService.CountAll(dateinfo, dateend, orgnid);
		CountNum countnumOrder = quantityService.CountOrder(dateinfo, dateend, orgnid);
		comtable.setCol1(start);
		comtable.setCol2(end);
		request.setAttribute("comtable", comtable);
		request.setAttribute("products", products);
		request.setAttribute("clients", clients);
		request.setAttribute("suplier", suplier);
		request.setAttribute("area", area);
		request.setAttribute("countnum", countnumClient);
		request.setAttribute("countnumAll", countnumAll);
		request.setAttribute("countnumOrder", countnumOrder);
		request.setAttribute("dateinfo", start);
		request.setAttribute("dateend", end);
		return "/admin/TopsCustom";
	}
}
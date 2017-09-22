package com.sumao.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sumao.model.ComTable;
import com.sumao.model.Page;
import com.sumao.service.AuthService;
import com.sumao.service.DetailService;
import com.sumao.service.PermisService;
import com.sumao.service.RegionService;

/**
 * 明细表的控制层 auhtor：liutong version：1.0
 */
@Controller
@RequestMapping("/orderDetail")
public class DetailController {

	@Resource
	private DetailService detailService;
	@Resource
	private RegionService regionService;
	@Resource
	private AuthService  authService;

	public DetailService getDetailService() {
		return detailService;
	}

	@Autowired
	public void setDetailService(DetailService detailService) {
		this.detailService = detailService;
	}

	public RegionService getRegionService() {
		return regionService;
	}

	@Autowired
	public void setRegionService(RegionService regionService) {
		this.regionService = regionService;
	}
	

	public AuthService getAuthService() {
		return authService;
	}
	@Autowired
	public void setAuthService(AuthService authService) {
		this.authService = authService;
	}

	/**
	 * 跳转到汇总信息明细表,comtable是传入的搜索框的值
	 */
	@RequestMapping(params = "toOrderDetail")
	public String toOrderDetail(HttpServletRequest request, ComTable comtable) {
		String derectpage="";
		String tokenin=request.getParameter("token").toString().trim();
		if(authService.checkValidity(tokenin)==0){
			derectpage="http://172.22.16.11:7003/seller/mycenter/#";
		}else{
			derectpage="/business/report/orderDetail";
		}
		Page page = null;
		String orgnid=request.getParameter("orgnid").toString().trim();
		comtable.setCol10(orgnid);
		List<ComTable> findSummary = new ArrayList<ComTable>();
		List<ComTable> findstates = detailService.selecttype("订单状态");
		List<ComTable> findtypes = detailService.selecttype("订单类型");
		int maxNum = detailService.findOrderNum(comtable);
		String pageNow = request.getParameter("pageNow");
		if (pageNow != null) {
			page = new Page(maxNum, Integer.parseInt(pageNow));
			findSummary = this.detailService.showOrderByPage(page.getStartPos(), page.getPageSize(), comtable);
		} else {
			page = new Page(maxNum, 1);
			findSummary = this.detailService.showOrderByPage(page.getStartPos(), page.getPageSize(), comtable);
		}
		request.setAttribute("findtypes", findtypes);
		request.setAttribute("findstates", findstates);
		request.setAttribute("details", findSummary);
		request.setAttribute("page", page);
		request.setAttribute("comtable", comtable);
		return derectpage;
	}
	/**
	 * 跳转到汇总信息明细表(带搜索条件),comtable是传入的搜索框的值
	 */
	@RequestMapping(params = "toOrderDetails")
	public String toOrderDetails(HttpServletRequest request, ComTable comtable) {
		Page page = null;
		String orgnid=request.getParameter("orgnid").toString().trim();
		String odtype = request.getParameter("odtype").toString().trim();
		String odstate = request.getParameter("odstate").toString().trim();	
		String odcheck = request.getParameter("odcheck").toString().trim();
		String enpname = request.getParameter("enpname").toString().trim();
		String orderid = request.getParameter("orderid").toString().trim();
		String prod = request.getParameter("prod").toString().trim();
		String start = request.getParameter("start").toString().trim();
		String end = request.getParameter("end").toString().trim();
		String warehouse = request.getParameter("warehouse").toString().trim();
		comtable.setCol1(odtype);
		comtable.setCol2(odstate);
		comtable.setCol3(odcheck);
		comtable.setCol4(enpname);
		comtable.setCol5(orderid);
		comtable.setCol6(prod);
		comtable.setCol7(start);
		comtable.setCol8(end);
		comtable.setCol10(orgnid);
		comtable.setCol11(warehouse);
		List<ComTable> findSummary = new ArrayList<ComTable>();
		List<ComTable> findstates = detailService.selecttype("订单状态");
		List<ComTable> findtypes = detailService.selecttype("订单类型");
		int maxNum = detailService.findOrderNum(comtable);
		String pageNow = request.getParameter("pageNow");
		if (pageNow != null) {
			page = new Page(maxNum, Integer.parseInt(pageNow));
			findSummary = this.detailService.showOrderByPage(page.getStartPos(), page.getPageSize(), comtable);
		} else {
			page = new Page(maxNum, 1);
			findSummary = this.detailService.showOrderByPage(page.getStartPos(), page.getPageSize(), comtable);
		}
		request.setAttribute("findtypes", findtypes);
		request.setAttribute("findstates", findstates);
		request.setAttribute("details", findSummary);
		request.setAttribute("page", page);
		request.setAttribute("comtable", comtable);
		return "/business/report/orderDetail";
	}

	/**
	 * 跳转到供应商明细表，comtable是传入的搜索框的值
	 */
	@RequestMapping(params = "toSupplierDetail")
	public String toSupplierDetail(HttpServletRequest request, ComTable comtable) {
		Page page = null;
		String orgnid=request.getParameter("orgnid").toString().trim();
		comtable.setCol10(orgnid);
		List<ComTable> findSummary = new ArrayList<ComTable>();
		int maxNum = detailService.findSupplierNum(comtable);
		String pageNow = request.getParameter("pageNow");
		if (pageNow != null) {
			page = new Page(maxNum, Integer.parseInt(pageNow));
			findSummary = this.detailService.showSupplierByPage(page.getStartPos(), page.getPageSize(), comtable);
		} else {
			page = new Page(maxNum, 1);
			findSummary = this.detailService.showSupplierByPage(page.getStartPos(), page.getPageSize(), comtable);
		}
		request.setAttribute("details", findSummary);
		request.setAttribute("page", page);
		request.setAttribute("comtable", comtable);
		return "/business/report/supplierDetail";
	}
	/**
	 * 跳转到供应商明细表(带搜索条件)，comtable是传入的搜索框的值
	 */
	@RequestMapping(params = "toSupplierDetails")
	public String toSupplierDetails(HttpServletRequest request, ComTable comtable) {
		Page page = null;
		String supname=request.getParameter("supname").toString().trim();
		String start=request.getParameter("start").toString().trim();
		String end=request.getParameter("end").toString().trim();
		String orgnid=request.getParameter("orgnid").toString().trim();
		comtable.setCol1(supname);
		comtable.setCol2(start);
		comtable.setCol3(end);
		comtable.setCol10(orgnid);
		List<ComTable> findSummary = new ArrayList<ComTable>();
		int maxNum = detailService.findSupplierNum(comtable);
		String pageNow = request.getParameter("pageNow");
		if (pageNow != null) {
			page = new Page(maxNum, Integer.parseInt(pageNow));
			findSummary = this.detailService.showSupplierByPage(page.getStartPos(), page.getPageSize(), comtable);
		} else {
			page = new Page(maxNum, 1);
			findSummary = this.detailService.showSupplierByPage(page.getStartPos(), page.getPageSize(), comtable);
		}
		request.setAttribute("details", findSummary);
		request.setAttribute("page", page);
		request.setAttribute("comtable", comtable);
		return "/business/report/supplierDetail";
	}

	/**
	 * 跳转到地区明细表，comtable是传入的搜索框的值
	 */
	@RequestMapping(params = "toAreaDetail")
	public String toAreaDetail(HttpServletRequest request, ComTable comtable) {
		Page page = null;
		String orgnid=request.getParameter("orgnid").toString().trim();
		comtable.setCol10(orgnid);
		List<ComTable> findSummary = new ArrayList<ComTable>();
		int maxNum = detailService.findAreaNum(comtable);
		String pageNow = request.getParameter("pageNow");
		if (pageNow != null) {
			page = new Page(maxNum, Integer.parseInt(pageNow));
			findSummary = this.detailService.showAreaByPage(page.getStartPos(), page.getPageSize(), comtable);
		} else {
			page = new Page(maxNum, 1);
			findSummary = this.detailService.showAreaByPage(page.getStartPos(), page.getPageSize(), comtable);
		}
		request.setAttribute("details", findSummary);
		request.setAttribute("page", page);
		request.setAttribute("comtable", comtable);
		return "/business/report/areaDetail";
	}
	/**
	 * 跳转到地区明细表(带查询条件)，comtable是传入的搜索框的值
	 */
	@RequestMapping(params = "toAreaDetails")
	public String toAreaDetails(HttpServletRequest request, ComTable comtable) {
		Page page = null;
		String prov=request.getParameter("prov").toString().trim();
		String city=request.getParameter("city").toString().trim();
		String prod=request.getParameter("prod").toString().trim();
		String start=request.getParameter("start").toString().trim();
		String end=request.getParameter("end").toString().trim();
		String orgnid=request.getParameter("orgnid").toString().trim();
		comtable.setCol1(prov);
		comtable.setCol2(city);
		comtable.setCol3(prod);
		comtable.setCol4(start);
		comtable.setCol5(end);
		comtable.setCol10(orgnid);
		List<ComTable> findSummary = new ArrayList<ComTable>();
		int maxNum = detailService.findAreaNum(comtable);
		String pageNow = request.getParameter("pageNow");
		if (pageNow != null) {
			page = new Page(maxNum, Integer.parseInt(pageNow));
			findSummary = this.detailService.showAreaByPage(page.getStartPos(), page.getPageSize(), comtable);
		} else {
			page = new Page(maxNum, 1);
			findSummary = this.detailService.showAreaByPage(page.getStartPos(), page.getPageSize(), comtable);
		}
		request.setAttribute("details", findSummary);
		request.setAttribute("page", page);
		request.setAttribute("comtable", comtable);
		return "/business/report/areaDetail";
	}
	
	/**
	 * 跳转到销售明细表，comtable是传入的搜索框的值
	 */
	@RequestMapping(params = "toSaleDetail")
	public String toSaleDetail(HttpServletRequest request, ComTable comtable) {
		Page page = null;
		String orgnid=request.getParameter("orgnid").toString().trim();
		comtable.setCol10(orgnid);
		List<ComTable> findSummary = new ArrayList<ComTable>();
		int maxNum = detailService.findSaleNum(comtable);
		String pageNow = request.getParameter("pageNow");
		if (pageNow != null) {
			page = new Page(maxNum, Integer.parseInt(pageNow));
			findSummary = this.detailService.showSaleByPage(page.getStartPos(), page.getPageSize(), comtable);
		} else {
			page = new Page(maxNum, 1);
			findSummary = this.detailService.showSaleByPage(page.getStartPos(), page.getPageSize(), comtable);
		}
		request.setAttribute("details", findSummary);
		request.setAttribute("page", page);
		request.setAttribute("comtable", comtable);
		return "/business/report/saleDetail";
	}
	/**
	 * 跳转到销售明细表(带搜索条件)，comtable是传入的搜索框的值
	 */
	@RequestMapping(params = "toSaleDetails")
	public String toSaleDetails(HttpServletRequest request, ComTable comtable) {
		Page page = null;
		String orgnid=request.getParameter("orgnid").toString().trim();
		String start=request.getParameter("start").toString().trim();
		String end=request.getParameter("end").toString().trim();
		String prod=request.getParameter("prod").toString().trim();
		String supplier=request.getParameter("supplier").toString().trim();
		String types=request.getParameter("types").toString().trim();
		String apply=request.getParameter("apply").toString().trim();
		comtable.setCol1(start);
		comtable.setCol2(end);
		comtable.setCol3(prod);
		comtable.setCol4(supplier);
		comtable.setCol5(types);
		comtable.setCol6(apply);
		comtable.setCol10(orgnid);
		List<ComTable> findSummary = new ArrayList<ComTable>();
		int maxNum = detailService.findSaleNum(comtable);
		String pageNow = request.getParameter("pageNow");
		if (pageNow != null) {
			page = new Page(maxNum, Integer.parseInt(pageNow));
			findSummary = this.detailService.showSaleByPage(page.getStartPos(), page.getPageSize(), comtable);
		} else {
			page = new Page(maxNum, 1);
			findSummary = this.detailService.showSaleByPage(page.getStartPos(), page.getPageSize(), comtable);
		}
		request.setAttribute("details", findSummary);
		request.setAttribute("page", page);
		request.setAttribute("comtable", comtable);
		return "/business/report/saleDetail";
	}
	/**
	 * 跳转到导出明细表，comtable是传入的搜索框的值
	 */
	@RequestMapping(params = "toClientDetail")
	public String toClientDetail(HttpServletRequest request, ComTable comtable) {
		Page page = null;
		String orgnid=request.getParameter("orgnid").toString().trim();
		comtable.setCol10(orgnid);
		List<ComTable> findSummary = new ArrayList<ComTable>();
		int maxNum = detailService.findClientNum(comtable);
		String pageNow = request.getParameter("pageNow");
		if (pageNow != null) {
			page = new Page(maxNum, Integer.parseInt(pageNow));
			findSummary = this.detailService.showClientByPage(page.getStartPos(), page.getPageSize(), comtable);
		} else {
			page = new Page(maxNum, 1);
			findSummary = this.detailService.showClientByPage(page.getStartPos(), page.getPageSize(), comtable);
		}
		request.setAttribute("details", findSummary);
		request.setAttribute("page", page);
		request.setAttribute("comtable", comtable);
		return "/business/report/clientDetail";
	}
	/**
	 * 跳转到导出明细表(带搜索条件)，comtable是传入的搜索框的值
	 */
	@RequestMapping(params = "toClientDetails")
	public String toClientDetails(HttpServletRequest request, ComTable comtable) {
		Page page = null;
		String start=request.getParameter("start").toString().trim();
		String end=request.getParameter("end").toString().trim();
		String clintid=request.getParameter("clintid").toString().trim();
		String clintname=request.getParameter("clintname").toString().trim();
		String enptype=request.getParameter("enptype").toString().trim();
		String orgnid=request.getParameter("orgnid").toString().trim();
		comtable.setCol1(start);
		comtable.setCol2(end);
		comtable.setCol3(clintid);
		comtable.setCol4(clintname);
		comtable.setCol5(enptype);
		comtable.setCol10(orgnid);
		List<ComTable> findSummary = new ArrayList<ComTable>();
		int maxNum = detailService.findClientNum(comtable);
		String pageNow = request.getParameter("pageNow");
		if (pageNow != null) {
			page = new Page(maxNum, Integer.parseInt(pageNow));
			findSummary = this.detailService.showClientByPage(page.getStartPos(), page.getPageSize(), comtable);
		} else {
			page = new Page(maxNum, 1);
			findSummary = this.detailService.showClientByPage(page.getStartPos(), page.getPageSize(), comtable);
		}
		request.setAttribute("details", findSummary);
		request.setAttribute("page", page);
		request.setAttribute("comtable", comtable);
		return "/business/report/clientDetail";
	}
}
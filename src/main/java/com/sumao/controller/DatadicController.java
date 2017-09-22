package com.sumao.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sumao.model.ComTable;
import com.sumao.model.Page;
import com.sumao.service.DatadicService;

/**
 * 数据字典表的控制层 auhtor：liutong version：1.0
 */
@Controller
@RequestMapping("/dataDictionary")
public class DatadicController {

	@Resource
	private DatadicService datadicService;

	public DatadicService getDatadicService() {
		return datadicService;
	}

	@Autowired
	public void setDatadicService(DatadicService datadicService) {
		this.datadicService = datadicService;
	}

	/**
	 * 跳转到数据字典的列表信息,comtable是传入的搜索框的值
	 */
	@RequestMapping(params = "toDataDicList")
	public String toOrderDetail(HttpServletRequest request, ComTable comtable) {
		Page page = null;
		String depmtype = null;
		List<ComTable> findSummary = new ArrayList<ComTable>();
		String params = request.getQueryString().toString().trim();
		HttpSession session = request.getSession();
		if (params.length() > 13) {
			depmtype = request.getParameter("depmtype").toString().trim();
			session.setAttribute("depmtype", depmtype);
		} else if (((String) session.getAttribute("depmtype")) != null
				&& !((String) session.getAttribute("depmtype")).equals("")) {
			depmtype = (String) session.getAttribute("depmtype").toString().trim();
		} else {
			depmtype = null;
		}
		comtable.setCol5(depmtype);
		int maxNum = datadicService.DataDicNum(comtable);
		String pageNow = request.getParameter("pageNow");
		if (pageNow != null) {
			page = new Page(maxNum, Integer.parseInt(pageNow));
			findSummary = this.datadicService.showDataDicByPage(page.getStartPos(), page.getPageSize(), comtable);
		} else {
			page = new Page(maxNum, 1);
			findSummary = this.datadicService.showDataDicByPage(page.getStartPos(), page.getPageSize(), comtable);
		}
		request.setAttribute("details", findSummary);
		request.setAttribute("page", page);
		request.setAttribute("comtable", comtable);
		return "/business/datadictionary/datadiclist";
	}

	/**
	 * 跳转到数据字典的新建信息
	 */
	@RequestMapping(params = "toNewword")
	public String toNewword(HttpServletRequest request) {
		return "/business/datadictionary/newword";
	}

	/**
	 * 跳转到数据字典的编辑信息
	 */
	@RequestMapping(params = "toEditword")
	public String toEditword(HttpServletRequest request) {
		String dicnum = request.getParameter("searchkey");
		ComTable comtabe = datadicService.getDicById(dicnum);
		request.setAttribute("details", comtabe);
		return "/business/datadictionary/editword";
	}

	/**
	 * 执行新建功能,comtable是传入的新建内容
	 */
	@RequestMapping(params = "addWord")
	public String addWord(HttpServletRequest request, ComTable comtabe) {
		Calendar c = Calendar.getInstance();
		HttpSession session = request.getSession();
		String depmtype = (String) session.getAttribute("depmtype").toString().trim();
		comtabe.setCol1(depmtype);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String CurDatetime = df.format(c.getTime());
		String DOCID = CurDatetime + String.valueOf(Math.random()).substring(2, 4);
		DOCID = DOCID.replace(":", "").replace(" ", "").replace("-", "");
		comtabe.setCol5(DOCID);
		datadicService.addWord(comtabe);
		return "redirect:/dataDictionary.do?toDataDicList" + "&depmtype=" + depmtype;
	}

	/**
	 * 执行编辑功能,comtable是传入的新建内容
	 */
	@RequestMapping(params = "editWord")
	public String editWord(HttpServletRequest request, ComTable comtabe) {
		datadicService.updateWord(comtabe);
		return "redirect:/dataDictionary.do?toDataDicList";
	}

	/**
	 * 跳转到数据字典的查看信息
	 */
	@RequestMapping(params = "toCheckword")
	public String toCheckword(HttpServletRequest request) {
		String dicnum = request.getParameter("searchkey");
		ComTable comtabe = datadicService.getDicById(dicnum);
		request.setAttribute("details", comtabe);
		return "/business/datadictionary/checkword";
	}

	/**
	 * 执行到数据字典的删除信息
	 */
	@RequestMapping(params = "delWord")
	public void delWord(HttpServletRequest request, HttpServletResponse response) {
		String dicnum = request.getParameter("wordid");
		String result = "{\"result\":\"error\"}";
		if (datadicService.delWordById(dicnum)) {
			result = "{\"result\":\"success\"}";
		}
		response.setContentType("application/json");
		try {
			PrintWriter out = response.getWriter();
			out.write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
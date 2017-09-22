package com.sumao.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.sumao.model.ComTable;
import com.sumao.model.Demo;
import com.sumao.model.Page;
import com.sumao.service.DemoService;
import com.sumao.service.DetailService;
import com.sumao.service.RegionService;

@Controller
@RequestMapping("/demo")
public class DemoController {

	@Resource
	private DemoService demoService;
	@Resource
	private DetailService detailService;
	@Resource
	private RegionService regionService;

	public DemoService getDemoService() {
		return demoService;
	}

	public void setDemoService(DemoService demoService) {
		this.demoService = demoService;
	}

	public DetailService getDetailService() {
		return detailService;
	}

	public void setDetailService(DetailService detailService) {
		this.detailService = detailService;
	}

	@RequestMapping("/show")
	public String showuser(HttpServletRequest request, Model model) {
		int userId = Integer.parseInt(request.getParameter("id"));
		Demo user = this.demoService.getUserById(userId);
		model.addAttribute("user", user);

		demoService.countUsers();
		return "/user";
	}

	@RequestMapping("/list")
	public String findUsers(HttpServletRequest request, Model model) {
		String name = request.getParameter("name");
		List<Demo> users = demoService.findUsers(name);
		model.addAttribute("users", users);
		return "/demo/userlist";
	}

	@RequestMapping(params = "findajax")
	public String findajax(HttpServletRequest request,HttpServletResponse response, ComTable comtable) {
		Page page = null;
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
		return "/demo/reportDetail";
	}
	@RequestMapping(params = "recvjson")
	public void recvjson(HttpServletRequest request,HttpServletResponse response, ComTable comtable) throws IOException {
		String json = request.getParameter("province");
		JSONObject  jsons=JSONObject.fromObject(json);
		List<ComTable> provinces = regionService.getCityByProvince(jsons.getString("省"));
		JSONArray jsoncity = JSONArray.fromObject(provinces);
		JSONArray jsonpro=new JSONArray();
		for(int i=1;i<=provinces.size();i++){
			JSONObject jsonrop=new JSONObject();
			jsonrop.put("col1",provinces.get(i-1).getCol1());
			jsonpro.add(jsonrop);
		}
		System.out.println("**"+jsonpro.toString());
		response.getWriter().print(jsoncity.toString());
		response.getWriter().flush();
		response.getWriter().close();
	}
}
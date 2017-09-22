package com.sumao.controller;

import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sumao.model.ComTable;
import com.sumao.service.RegionService;

import net.sf.json.JSONArray;

/**
 * 报表系统的省市联动的控制层 auhtor：liutong version：1.0
 */
@Controller
@RequestMapping("/proAndCity")
public class RegionController {

	@Resource
	private RegionService regionService;

	public RegionService getRegionService() {
		return regionService;
	}

	@Autowired
	public void setRegionService(RegionService regionService) {
		this.regionService = regionService;
	}

	/**
	 * 返回页面的json,返回值是省信息的json
	 */
	@RequestMapping(params = "findProvince")
	public void findProvince(HttpServletResponse response) throws IOException {
		List<ComTable> province = regionService.getProvince();
		JSONArray json = JSONArray.fromObject(province);
		response.getWriter().print(json.toString());
		response.getWriter().flush();
		response.getWriter().close();
	}

	/**
	 * 返回页面的json,返回值是市信息的json，province是传来的省名字
	 */
	@RequestMapping(params = "findCity")
	public void findCity(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String provincename = request.getParameter("province");
		List<ComTable> provinces = regionService.getCityByProvince(provincename);
		JSONArray json = JSONArray.fromObject(provinces);
		response.getWriter().print(json.toString());
		response.getWriter().flush();
		response.getWriter().close();
	}
}
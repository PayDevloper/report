package com.sumao.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sumao.model.EasyuiDataGrid;
import com.sumao.model.EasyuiDataGridJson;
import com.sumao.model.Json;
import com.sumao.model.SessionInfo;
import com.sumao.model.Paysub;
import com.sumao.service.AuthManageService;
import com.sumao.service.DetailService;
import com.sumao.service.NavigationService;
import com.sumao.util.ResourceUtil;

/**
 * 功能：导航的管理维护1.0
 * 
 * @author liutong
 * 
 */
@Controller
@RequestMapping("/NavigationController")
public class NavigationController extends BaseController {

	private static final Logger logger = Logger.getLogger(NavigationController.class);

	@Resource
	private DetailService detailService;
	@Resource
	private NavigationService navigationService;
	@Resource
	private AuthManageService authmanageService;

	public NavigationService getNavigationService() {
		return navigationService;
	}

	@Autowired
	public void setNavigationService(NavigationService navigationService) {
		this.navigationService = navigationService;
	}

	public AuthManageService getAuthmanageService() {
		return authmanageService;
	}

	@Autowired
	public void setAuthmanageService(AuthManageService authmanageService) {
		this.authmanageService = authmanageService;
	}

	public DetailService getDetailService() {
		return detailService;
	}

	@Autowired
	public void setDetailService(DetailService detailService) {
		this.detailService = detailService;
	}

	/**
	 * 跳转到系统探针页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "jspinfo")
	public String jspinfo() {
		return "/admin/tz/jspinfo";
	}

	/**
	 * 跳转到导航列表管理页面
	 * 
	 * @param HttpServletRequest
	 * @return
	 */
	@RequestMapping(params = "tolistnavigation")
	public String Paysub(HttpServletRequest request) {
		return "/authority/auth_navigation";
	}

	/**
	 * 岗位数据表格
	 * 
	 * @param dg
	 * @param payr
	 * @return
	 */
	@RequestMapping(params = "datagrid")
	@ResponseBody
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg, Paysub payr) {
		int total = navigationService.findnaviTotal(payr);
		return navigationService.datagrid(dg, payr, total);
	}

	/**
	 * 新建保存信息
	 * 
	 * @param Paysub
	 *            payr
	 * @param HttpSession
	 *            session
	 * @return
	 */
	@RequestMapping(params = "add")
	@ResponseBody
	public Json add(Paysub payr, HttpSession session) {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 可以方便地修改日期格式
		String operdate = dateFormat.format(now);
		String DOCID = operdate + String.valueOf(Math.random()).substring(2, 6);
		DOCID = DOCID.replace(":", "").replace(" ", "").replace("-", "");
		payr.setId(DOCID);
		payr.setSubname("0");
		boolean stat = navigationService.addnavi(payr);
		Json j = new Json();
		j.setSuccess(stat);
		return j;
	}

	/**
	 * 编辑保存信息
	 * 
	 * @param Paysub
	 *            payr
	 * @param HttpSession
	 *            session
	 * @return
	 */
	@RequestMapping(params = "edit")
	@ResponseBody
	public Json editnavi(Paysub payr, HttpSession session) {
		boolean stat = navigationService.editnavi(payr);

		Json j = new Json();
		j.setSuccess(stat);
		return j;
	}
	/**
	 * 删除信息
	 * 
	 * @param ids
	 * @author liutong
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public Json del(String ids) {
		Json j = new Json();
		boolean stat = true;
		String[] idall = ids.split(",");
		for (int i = 0; i < idall.length; i++) {
			boolean statmx = navigationService.delnavi(idall[i]);
			if (statmx == false) {
				stat = false; 
				logger.info("删除子项信息失败，唯一编号为：" + idall[i]);
			}
		}

		j.setSuccess(stat);
		return j;
	}

	/**
	 * 跳转到权限分配页面
	 * 
	 * @param HttpServletRequest
	 * @return
	 */
	@RequestMapping(params = "toauthdis")
	public String authDistribute(HttpServletRequest request) {
		return "/authority/auth_distribute";
	}
	
	/**
	 * 跳转到二级导航列表管理页面
	 * 
	 * @param HttpServletRequest
	 * @return
	 */
	@RequestMapping(params = "tolistsubnavi")
	public String Navisub(HttpServletRequest request) {
		return "/authority/auth_subnavigation";
	}

	/**
	 * 岗位数据表格
	 * 
	 * @param dg
	 * @param payr
	 * @return
	 */
	@RequestMapping(params = "navidatagrid")
	@ResponseBody
	public EasyuiDataGridJson navidatagrid(EasyuiDataGrid dg, Paysub payr) {
		int total = navigationService.findsubnaviTotal(payr);
		return navigationService.subnavidatagrid(dg, payr, total);
	}

	/**
	 * 新建保存信息
	 * 
	 * @param Paysub
	 *            payr
	 * @param HttpSession
	 *            session
	 * @return
	 */
	@RequestMapping(params = "addsubnavi")
	@ResponseBody
	public Json addsubnavi(Paysub payr, HttpSession session) {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String operdate = dateFormat.format(now);
		String DOCID = operdate + String.valueOf(Math.random()).substring(2, 6);
		DOCID = DOCID.replace(":", "").replace(" ", "").replace("-", "");
		payr.setId(DOCID);
		boolean stat = navigationService.addsubnavi(payr);
		Json j = new Json();
		j.setSuccess(stat);
		return j;
	}

	/**
	 * 编辑保存信息
	 * 
	 * @param Paysub
	 *            payr
	 * @param HttpSession
	 *            session
	 * @return
	 */
	@RequestMapping(params = "editsubnavi")
	@ResponseBody
	public Json editsubnavi(Paysub payr, HttpSession session) {
		boolean stat = navigationService.editsubnavi(payr);

		Json j = new Json();
		j.setSuccess(stat);
		return j;
	}

	/**
	 * 跳转到选择人员信息页面
	 * 
	 * @return "/Pay/pay_totalsel"
	 */
	@RequestMapping(params = "navisel")
	public String Paytotalsel() {
		return "/authority/auth_navisel";
	}

	/**
	 * 删除信息
	 * 
	 * @param ids
	 * @author liutong
	 * @return
	 */
	@RequestMapping(params = "delsubnavi")
	@ResponseBody
	public Json delsubnavi(String ids) {
		Json j = new Json();
		boolean stat = true;
		String[] idall = ids.split(",");
		for (int i = 0; i < idall.length; i++) {
			boolean statmx = navigationService.delsubnavi(idall[i]);
			if (statmx == false) {
				stat = false; 
				logger.info("删除子项信息失败，唯一编号为：" + idall[i]);
			}
		}

		j.setSuccess(stat);
		return j;
	}

}

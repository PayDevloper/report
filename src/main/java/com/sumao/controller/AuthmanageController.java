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
 * 功能：权限的管理维护1.0
 * 
 * @author liutong
 * 
 */
@Controller
@RequestMapping("/AuthmanageController")
public class AuthmanageController extends BaseController {

	private static final Logger logger = Logger.getLogger(AuthmanageController.class);

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
	 * 跳转到岗位人员信息管理页面
	 * 
	 * @param HttpServletRequest
	 * @return
	 */
	@RequestMapping(params = "tolistauth")
	public String Paysub(HttpServletRequest request) {
		/*
		 * String[] userids = null; HttpSession session=request.getSession();
		 * if(((String) session.getAttribute("userid"))!=null&&!((String)
		 * session.getAttribute("userid")).equals("")){ String userid=(String)
		 * session.getAttribute("userid"); if(userid.contains(",")){ userids =
		 * userid.split(","); }else{ userids=new String[1]; userids[0]=userid; }
		 * }else{ userids=new String[1]; userids[0]=null; } List<Paysub>
		 * findsubSource=this.navigationService.findSubauthort(userids);
		 * List<Paysub> findHigher=this.navigationService.findAuthsort();
		 * request.setAttribute("sub", findsubSource);
		 * request.setAttribute("high", findHigher);
		 */
		return "/authority/auth_newposition";
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
		int total = authmanageService.findTotal(payr);
		return authmanageService.datagrid(dg, payr, total);
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
		String username = "";
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
		if (sessionInfo != null && sessionInfo.getUser() != null) {
			username = sessionInfo.getUser().getName();
		} else {
			username = "admin";
		}

		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 可以方便地修改日期格式
		String operdate = dateFormat.format(now);
		String DOCID = operdate + String.valueOf(Math.random()).substring(2, 6);
		DOCID = DOCID.replace(":", "").replace(" ", "").replace("-", "");

		payr.setOperuser(username);
		payr.setOperdate(operdate);
		payr.setId(DOCID);

		boolean stat = authmanageService.add(payr);
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
	public Json edit(Paysub payr, HttpSession session) {
		String username = "";
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
		if (sessionInfo != null && sessionInfo.getUser() != null) {
			username = sessionInfo.getUser().getName();
		} else {
			username = "admin";
		}

		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 可以方便地修改日期格式
		String operdate = dateFormat.format(now);

		payr.setOperuser(username);
		payr.setOperdate(operdate);

		boolean stat = authmanageService.edit(payr);

		Json j = new Json();
		j.setSuccess(stat);
		return j;
	}

	/**
	 * 跳转到选择人员信息页面
	 * 
	 * @return "/Pay/pay_totalsel"
	 */
	@RequestMapping(params = "usersel")
	public String Paytotalsel() {
		return "/authority/auth_selusers";
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
			boolean statmx = authmanageService.del(idall[i]);
			if (statmx == false) {
				stat = false; // 如果有一条数据删除不成功，提示删除问题。
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
}

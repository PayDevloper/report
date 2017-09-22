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
import com.sumao.service.DatabookService;
import com.sumao.util.ResourceUtil;


/**
 * 功能：权限的管理维护
 * 
 * @author liutong
 * 
 */
@Controller
@RequestMapping("/DatadictionaryController")
public class DatadictionaryController extends BaseController {

	private static final Logger logger = Logger.getLogger(DatadictionaryController.class);

	@Resource
	private DatabookService databookService;

	public DatabookService getDatabookService() {
		return databookService;
	}
	@Autowired
	public void setDatabookService(DatabookService databookService) {
		this.databookService = databookService;
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
	 * 跳转到权限信息管理页面
	 * 
	 * @param HttpServletRequest
	 * @return
	 */
	@RequestMapping(params = "tolistdatadic")
	public String Paysub(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String depmtype = null;
		String params = request.getQueryString().toString().trim();
		if (params.length() > 13) {
			depmtype = request.getParameter("depmtype").toString().trim();
			session.setAttribute("depmtype", depmtype);
		} else if (((String) session.getAttribute("depmtype")) != null
				&& !((String) session.getAttribute("depmtype")).equals("")) {
			depmtype = (String) session.getAttribute("depmtype").toString().trim();
		} else {
			depmtype = "";
			session.setAttribute("depmtype", depmtype);
		}
		return "/business/datadictionary/dic_data";
	}

	/**
	 * 数据表格
	 * 
	 * @param dg
	 * @param payr
	 * @return
	 */
	@RequestMapping(params = "datagrid")
	@ResponseBody
	public EasyuiDataGridJson disauthdatagrid(EasyuiDataGrid dg, Paysub payr,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String depmtype = null;
		 if (((String) session.getAttribute("depmtype")) != null
				&& !((String) session.getAttribute("depmtype")).equals("")) {
			depmtype = (String) session.getAttribute("depmtype").toString().trim();
		} else {
			depmtype = null;
		}
		payr.setSubname(depmtype);
		int total = databookService.DataDicNum(payr);
		return databookService.datagrid(dg, payr, total);
	}
	/**
	 * 新建保存信息
	 * @param Paysub payr
	 * @param HttpSession session
	 * @return
	 */
	@RequestMapping(params = "add")
	@ResponseBody
	public Json add(Paysub payr,HttpSession session) {
		String username="";
		String depmtype = "";
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
		if (sessionInfo != null && sessionInfo.getUser() != null) {
			username=sessionInfo.getUser().getName();
		} else {
			username="admin";
		}
		if (((String) session.getAttribute("depmtype")) != null
				&& !((String) session.getAttribute("depmtype")).equals("")) {
			depmtype = (String) session.getAttribute("depmtype").toString().trim();
		} else {
			depmtype = null;
		}
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
		String operdate = dateFormat.format(now);
		String DOCID = operdate + String.valueOf(Math.random()).substring(2, 6);
		DOCID = DOCID.replace(":", "").replace(" ", "").replace("-", "");
		
		payr.setOperuser(username);
		payr.setOperdate(operdate);
		payr.setId(DOCID);
		payr.setRemark(depmtype);

		boolean stat=databookService.add(payr);
		Json j = new Json();
		j.setSuccess(stat);
		return j;
	}

	/**
	 * 编辑保存信息
	 * @param Paysub payr
	 * @param HttpSession session
	 * @return
	 */
	@RequestMapping(params = "edit")
	@ResponseBody
	public Json edit(Paysub payr,HttpSession session) {
		String username="";
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
		if (sessionInfo != null && sessionInfo.getUser() != null) {
			username=sessionInfo.getUser().getName();
		} else {
			username="admin";
		}
		
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
		String operdate = dateFormat.format(now);
		
		payr.setOperuser(username);
		payr.setOperdate(operdate);
		
		boolean stat=databookService.edit(payr);
		
		Json j = new Json();
		j.setSuccess(stat);
		return j;
	}
	/**
	 * 删除信息
	 * @param ids
	 * @author heijj
	 * @return 
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public Json del(String ids) {
		Json j = new Json();
		boolean stat=true;
		String[] idall=ids.split(",");
		for (int i=0;i<idall.length;i++)
		{
			boolean statmx=databookService.del(idall[i]);
			if (statmx==false)
			{
				stat=false;  //如果有一条数据删除不成功，提示删除问题。
				logger.info("删除子项信息失败，唯一编号为："+idall[i]);
			}
		}
		
		j.setSuccess(stat);
		return j;
	}
}

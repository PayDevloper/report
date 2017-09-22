package com.sumao.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sumao.model.EasyuiDataGrid;
import com.sumao.model.EasyuiDataGridJson;
import com.sumao.model.Json;
import com.sumao.model.Payeditaccountingrules;
import com.sumao.model.SessionInfo;
import com.sumao.model.Paynewaccountingrules;
import com.sumao.service.PaynewaccountingrulesService;
import com.sumao.util.ResourceUtil;

/**
 * 计费规则创建
 * @author heijj
 * 
 */
@Controller
@RequestMapping("/paynewaccountingrulesController")
public class PaynewaccountingrulesController extends BaseController {

	private static final Logger logger = Logger.getLogger(PaynewaccountingrulesController.class);

	private PaynewaccountingrulesService paynewaccountingrulesService;

	public PaynewaccountingrulesService getPaynewaccountingrulesService() {
		return paynewaccountingrulesService;
	}

	@Autowired
	public void setPaynewaccountingrulesService(PaynewaccountingrulesService paynewaccountingrulesService) {
		this.paynewaccountingrulesService = paynewaccountingrulesService;
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
	 * 跳转到信息管理页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "paynewaccountingrules")
	public String paynewaccountingrules() {
		return "/Pay/pay_newaccountingrules";
	}

	/**
	 * 数据表格
	 * @param dg
	 * @param Paynewaccountingrules payr
	 * @return
	 */
	@RequestMapping(params = "datagrid")
	@ResponseBody
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg, Paynewaccountingrules payr) {
		int total=paynewaccountingrulesService.findPayrTotal(payr);
		return paynewaccountingrulesService.datagrid(dg, payr,total);
	}

	/**
	 * 新建保存信息
	 * @param Paynewaccountingrules payr
	 * @param HttpSession session
	 * @return
	 */
	@RequestMapping(params = "add", produces = "text/htm;charset=UTF-8")
	@ResponseBody
	public Json add(Paynewaccountingrules payr,HttpSession session) {
		//audit_status,operuser, operdate,id
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
		String DOCID = operdate + String.valueOf(Math.random()).substring(2, 6);
		DOCID = DOCID.replace(":", "").replace(" ", "").replace("-", "");
				
		payr.setApprove("未审核");
		payr.setBillingconfiguration("未创建");
		payr.setCreateperson(username);
		payr.setCreatetime(operdate);
		payr.setId(DOCID);
		boolean stat=paynewaccountingrulesService.add(payr);
		
		Json j = new Json();
		j.setSuccess(stat);
		return j;
	}

	/**
	 * 编辑保存信息
	 * @param Paynewaccountingrules payr
	 * @param HttpSession session
	 * @return
	 */
	@RequestMapping(params = "edit", produces = "text/htm;charset=UTF-8")
	@ResponseBody
	public Json edit(Paynewaccountingrules payr,HttpSession session) {
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
		
		payr.setApprove("未审核");
		payr.setBillingconfiguration("未创建");
		payr.setCreateperson(username);
		payr.setCreatetime(operdate);
		
		boolean stat=paynewaccountingrulesService.edit(payr);
		
		Json j = new Json();
		j.setSuccess(stat);
		return j;
	}

	/**
	 * 删除信息
	 * @param ids
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
			boolean statmx=paynewaccountingrulesService.del(idall[i]);
			if (statmx==false)
			{
				stat=false;  //如果有一条数据删除不成功，提示删除问题。
				logger.info("删除计费规则创建信息失败，唯一编号为："+idall[i]);
			}
		}
		
		j.setSuccess(stat);
		return j;
	}
	
	/**
	 * 批量审核功能
	 * @param HttpSession session
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "editaudit")
	@ResponseBody
	public Json editaudit(String ids,HttpSession session) {
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
		
		Paynewaccountingrules payr=new Paynewaccountingrules();
		payr.setAuditperson(username);
		payr.setAudittime(operdate);
		payr.setApprove("已通过");
		
		Json j = new Json();
		boolean stat=true;
		String[] idall=ids.split(",");
		for (int i=0;i<idall.length;i++)
		{
			payr.setId(idall[i]);
			
			boolean statmx=paynewaccountingrulesService.editaudit(payr);
			if (statmx==false)
			{
				stat=false;  //如果有一条数据审核不成功，提示审核问题。
				logger.info("审核计费规则创建信息失败，唯一编号为："+idall[i]);
			}
		}
		
		j.setSuccess(stat);
		return j;
	}
	
	/**
	 * 审核不通过功能
	 * @param ids
	 * @param HttpSession session
	 * @return
	 */
	@RequestMapping(params = "editauditno")
	@ResponseBody
	public Json editauditno(String ids,HttpSession session) {
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
		
		Paynewaccountingrules payr=new Paynewaccountingrules();
		payr.setAuditperson(username);
		payr.setAudittime(operdate);
		payr.setApprove("未通过");
		
		Json j = new Json();
		boolean stat=true;
		String[] idall=ids.split(",");
		for (int i=0;i<idall.length;i++)
		{
			payr.setId(idall[i]);
			
			boolean statmx=paynewaccountingrulesService.editaudit(payr);
			if (statmx==false)
			{
				stat=false;  //如果有一条数据审核不成功，提示审核问题。
				logger.info("审核计费规则管理信息失败，唯一编号为："+idall[i]);
			}
		}
		
		j.setSuccess(stat);
		return j;
	}
	

}

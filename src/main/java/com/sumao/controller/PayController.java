package com.sumao.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import com.sumao.model.Payrule;
import com.sumao.service.PayService;
import com.sumao.util.ExceptionUtil;
import com.sumao.util.IpUtil;
import com.sumao.util.ResourceUtil;

/**
 * 缴费控制器
 * 
 * @author 黑洁净
 * 
 */
@Controller
@RequestMapping("/payController")
public class PayController extends BaseController {

	private static final Logger logger = Logger.getLogger(PayController.class);

	private PayService payService;

	public PayService getPayService() {
		return payService;
	}

	@Autowired
	public void setPayService(PayService payService) {
		this.payService = payService;
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
	 * 跳转到用户管理页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "payrule")
	public String payrule() {
		return "/Pay/pay_rulecreat";
	}

	/**
	 * 跳转到用户信息页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "userInfo")
	public String userInfo() {
		return "/admin/userInfo";
	}

	/**
	 * 编辑用户信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "editUserInfo")
	@ResponseBody
	public Json editUserInfo(Payrule user) {
		Json j = new Json();
		Payrule u = payService.editUserInfo(user);
		if (u != null) {
			j.setSuccess(true);
		}
		return j;
	}

	/**
	 * 用户注销
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(params = "logout")
	@ResponseBody
	public Json logout(HttpSession session) {
		Json j = new Json();
		if (session != null) {
			session.invalidate();
		}
		j.setSuccess(true);
		return j;
	}

	/**
	 * 用户注册
	 * 
	 * @param user
	 *            用户的信息
	 * @return json
	 */
	@RequestMapping(params = "reg")
	@ResponseBody
	public Json reg(Payrule user) {
		Json j = new Json();
		try {
			payService.reg(user);
			j.setSuccess(true);
			j.setMsg("注册成功！");
		} catch (Exception e) {
			j.setMsg("用户名已存在！");
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}
		return j;
	}

	
	/**
	 * 获得用户表格
	 * 
	 * @param dg
	 * @param user
	 * @return
	 */
	@RequestMapping(params = "loginDatagrid")
	@ResponseBody
	public EasyuiDataGridJson loginDatagrid(EasyuiDataGrid dg, Payrule user) {
		return payService.datagrid(dg, user);
	}

	/**
	 * 获得用户列表
	 * 
	 * @param q
	 * @return
	 */
	@RequestMapping(params = "loginCombobox")
	@ResponseBody
	public List<Payrule> loginCombobox(String q) {
		return payService.combobox(q);
	}

	/**
	 * 用户表格
	 * 
	 * @param dg
	 * @param payr
	 * @return
	 */
	@RequestMapping(params = "datagrid")
	@ResponseBody
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg, Payrule payr) {
		System.out.println("aaaaffffdddd");
		System.out.println("payr=="+payr.getSeller()+payr.getSales_org()+payr.getSales_orgID());
		return payService.datagrid(dg, payr);
	}

	/**
	 * 新建保存信息
	 * @param Payrule payr
	 * @return
	 */
	@RequestMapping(params = "add")
	@ResponseBody
	public Json add(Payrule payr) {
		//audit_status,operuser, operdate,id
		System.out.println("aaa185===fffff");
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
		String operdate = dateFormat.format(now);
		String DOCID = operdate + String.valueOf(Math.random()).substring(2, 6);
		DOCID = DOCID.replace(":", "").replace(" ", "").replace("-", "");
		System.out.println("DOCID==="+DOCID);
		
		payr.setAudit_status("未审核");
		payr.setOperuser("admin");
		payr.setOperdate(operdate);
		payr.setId(DOCID);
		boolean stat=payService.add(payr);
		
		Json j = new Json();
		j.setSuccess(stat);
		return j;
	}

	/**
	 * 编辑保存信息
	 * @param Payrule payr
	 * @return
	 */
	@RequestMapping(params = "edit")
	@ResponseBody
	public Json edit(Payrule payr) {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
		String operdate = dateFormat.format(now);
		
		payr.setAudit_status("未审核");
		payr.setOperuser("admin");
		payr.setOperdate(operdate);
		
		boolean stat=payService.edit(payr);
		
		Json j = new Json();
		j.setSuccess(stat);
		return j;
	}

	/**
	 * 批量编辑用户角色
	 * 
	 * @param userIds
	 *            用户ID
	 * @param roleId
	 *            角色ID
	 * @return
	 */
	@RequestMapping(params = "editUsersRole")
	@ResponseBody
	public Json editUsersRole(String userIds, String roleId) {
		Json j = new Json();
		payService.editUsersRole(userIds, roleId);
		j.setSuccess(true);
		return j;
	}

	/**
	 * 删除用户
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public Json del(String ids) {
		Json j = new Json();
		System.out.println("ids==="+ids);
		boolean stat=true;
		String[] idall=ids.split(",");
		for (int i=0;i<idall.length;i++)
		{
			boolean statmx=payService.del(idall[i]);
			if (statmx==false)
			{
				stat=false;  //如果有一条数据删除不成功，提示删除问题。
			}
		}
		
		j.setSuccess(stat);
		return j;
	}
	
	/**
	 * 批量审核功能
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "editaudit")
	@ResponseBody
	public Json editaudit(String ids) {
		Json j = new Json();
		System.out.println("ids==="+ids);
		boolean stat=true;
		String[] idall=ids.split(",");
		for (int i=0;i<idall.length;i++)
		{
			boolean statmx=payService.editaudit(idall[i]);
			if (statmx==false)
			{
				stat=false;  //如果有一条数据审核不成功，提示审核问题。
			}
		}
		
		j.setSuccess(stat);
		return j;
	}
	

}

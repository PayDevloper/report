package com.sumao.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.sumao.model.Page;
import com.sumao.model.Pay_rightgoldcheck;
import com.sumao.model.Pay_userorg;
import com.sumao.model.SessionInfo;
import com.sumao.model.Paybond;
import com.sumao.model.Paynewaccountingrules;
import com.sumao.service.PayRightService;
import com.sumao.service.PaybondService;
import com.sumao.util.ResourceUtil;

/**
 * 保证金设定
 * 
 * @author heijj
 * 
 */
@Controller
@RequestMapping("/paybondController")
public class PaybondController extends BaseController {

	private static final Logger logger = Logger.getLogger(PaybondController.class);

	private PaybondService paybondService;

	public PaybondService getPaybondService() {
		return paybondService;
	}

	@Autowired
	public void setPaybondService(PaybondService paybondService) {
		this.paybondService = paybondService;
	}

	private PayRightService payRightService;

	public PayRightService getPayRightService() {
		return payRightService;
	}

	@Autowired
	public void setPayRightService(PayRightService payRightService) {
		this.payRightService = payRightService;
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
	 * 跳转到保证金设定页面
	 * 
	 * @param HttpServletRequest
	 *            request
	 * @author heijj
	 * @return
	 */
	@RequestMapping(params = "paybond")
	public String Paybond(HttpServletRequest request) {
		String marketingid = request.getParameter("pmarketingid"); // 关键字查询条件
		if ((marketingid == null) || (marketingid.equals("null"))) {
			request.setAttribute("pmarketingid", "");
			request.setAttribute("seller", "");
			request.setAttribute("marketing", "");
		} else {
			Pay_userorg uorg = payRightService.getUserorgBy(marketingid);
			request.setAttribute("seller", uorg.getSeller());
			request.setAttribute("marketing", uorg.getMarketing());
			request.setAttribute("pmarketingid", marketingid);
		}
		return "/Pay/pay_bond";
	}
	
	/**
	 * 跳转到保证金设定页面
	 * 
	 * @param HttpServletRequest
	 *            request
	 * @author heijj
	 * @return
	 */
	@RequestMapping(params = "paybondHis")
	public String PaybondHis(HttpServletRequest request) {
		String marketingid = request.getParameter("orgnid"); // 关键字查询条件
		if ((marketingid == null) || (marketingid.equals("null"))) {
			request.setAttribute("pmarketingid", "");
			request.setAttribute("seller", "");
			request.setAttribute("marketing", "");
		} else {
			Pay_userorg uorg = payRightService.getUserorgBy(marketingid);
			request.setAttribute("seller", uorg.getSeller());
			request.setAttribute("marketing", uorg.getMarketing());
			request.setAttribute("pmarketingid", marketingid);
		}
		return "/Pay/pay_bondHistory";
	}
	


	/**
	 * 数据表格
	 * 
	 * @author heijj
	 * @param dg
	 * @param payr
	 * @param HttpServletRequest
	 *            request
	 * @return
	 */
	@RequestMapping(params = "datagrid")
	@ResponseBody
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg, Paybond payr, HttpServletRequest request) {

		String marketingid = request.getParameter("pmarketingid"); // 关键字查询条件
		if ((marketingid == null) || (marketingid.equals("null"))) {
			marketingid = payr.getMarketingid();
		}

		payr.setMarketingid(marketingid);

		int total = paybondService.findPayrTotal(payr);
		return paybondService.datagrid(dg, payr, total);
	}
	
	/**
	 * 卖家费用明细，即历史记录数据表格
	 * 
	 * @author heijj
	 * @param dg
	 * @param payr
	 * @param HttpServletRequest
	 *            request
	 * @return
	 */
	@RequestMapping(params = "datagridHis")
	@ResponseBody
	public EasyuiDataGridJson datagridHis(EasyuiDataGrid dg, Paybond payr, HttpServletRequest request) {

		String marketingid = request.getParameter("pmarketingid"); // 关键字查询条件
		if ((marketingid == null) || (marketingid.equals("null"))) {
			marketingid = payr.getMarketingid();
		}
		payr.setMarketingid(marketingid);

		String createtime=request.getParameter("createtime");  //关键字开始日期
		if ((createtime==null) || (createtime.equals("null")) || (createtime.equals("")))
		{
			createtime="1900-01-01";
			payr.setCreatetime(createtime);
		}
		
		String remark=request.getParameter("remark");  //关键字生效截止日期
		if ((remark==null) || (remark.equals("null")) || (remark.equals("")))
		{
			remark="2900-12-31";
			payr.setRemark(remark);
		}

		int total = paybondService.findPayrHisTotal(payr);
		return paybondService.datagridHis(dg, payr, total);
	}
	

	@RequestMapping(params = "costpaySeller")
	public String costpaySeller(HttpServletRequest request,Paybond payr) {
		Page page = null;
		String remark2=request.getParameter("remark2");   //发生类型
		if ((remark2==null) || (remark2.equals("null")))
		{
			remark2="";
		}
		payr.setRemark2(remark2);
		
		String marketingid=request.getParameter("orgnid");
		if ((marketingid==null) || (marketingid.equals("null")))
		{
			marketingid="";
		}
		payr.setMarketingid(marketingid);
		
		String createtime=request.getParameter("createtime");  //关键字开始日期
		if ((createtime==null) || (createtime.equals("null")) || (createtime.equals("")))
		{
			createtime="1900-01-01";
		}
		payr.setCreatetime(createtime);
		
		String remark=request.getParameter("remark");  //关键字生效截止日期
		if ((remark==null) || (remark.equals("null")) || (remark.equals("")))
		{
			remark="2900-12-31";
		}
		payr.setRemark(remark);

		List<Paybond> findSummary = new ArrayList<Paybond>();
		int maxNum = paybondService.findPayrHisTotal(payr);
	
		String pageNow = request.getParameter("pageNow");
		if (pageNow != null) {
			page = new Page(maxNum, Integer.parseInt(pageNow));
		} else {
			page = new Page(maxNum, 1);
		}
		
		findSummary = this.paybondService.showOrderByPage(page.getPageNow(), page.getPageSize(), payr);
		
		request.setAttribute("details", findSummary);
		request.setAttribute("page", page);
		request.setAttribute("payr", payr);
		
		return "/Pay/pay_costpayseller";
	}
	

	/**
	 * 新建保存信息
	 * 
	 * @param Paybond
	 *            payr
	 * @param HttpSession
	 *            session
	 * @return
	 */
	@RequestMapping(params = "add", produces = "text/htm;charset=UTF-8")
	@ResponseBody
	public Json add(Paybond payr, HttpSession session) {
		// audit_status,operuser, operdate,id
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

		payr.setCreateperson(username);
		payr.setCreatetime(operdate);
		payr.setId(DOCID);
		payr.setHavedeposit("0");
		payr.setApprove("未审核");
		payr.setPayment("未支付");

		boolean stat = paybondService.add(payr);

		Json j = new Json();
		j.setSuccess(stat);
		return j;
	}

	/**
	 * 编辑保存信息
	 * 
	 * @param Paybond
	 *            payr
	 * @param HttpSession
	 *            session
	 * @return
	 */
	@RequestMapping(params = "edit", produces = "text/htm;charset=UTF-8")
	@ResponseBody
	public Json edit(Paybond payr, HttpSession session) {
		String username = "";
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
		if (sessionInfo != null && sessionInfo.getUser() != null) {
			username = sessionInfo.getUser().getName();
		} else {
			username = "admin";
		}

		paybondService.editHistory(payr); // 保证金设定编辑保存历史记录

		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 可以方便地修改日期格式
		String operdate = dateFormat.format(now);

		payr.setCreateperson(username);
		payr.setCreatetime(operdate);
		payr.setApprove("未审核");

		boolean stat = paybondService.edit(payr);

		Json j = new Json();
		j.setSuccess(stat);
		return j;
	}

	/**
	 * 删除信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public Json del(String ids) {
		Json j = new Json();
		boolean stat = true;
		String[] idall = ids.split(",");
		for (int i = 0; i < idall.length; i++) {
			boolean statmx = paybondService.del(idall[i]);
			if (statmx == false) {
				stat = false; // 如果有一条数据删除不成功，提示删除问题。
				logger.info("删除保证金设定信息失败，唯一编号为：" + idall[i]);
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
		
		Paybond payr=new Paybond();
		payr.setAuditperson(username);
		payr.setAudittime(operdate);
		payr.setApprove("已通过");
		
		Json j = new Json();
		boolean stat = true;
		String[] idall = ids.split(",");
		for (int i = 0; i < idall.length; i++) {
			payr.setId(idall[i]);
			
			boolean statmx = paybondService.editaudit(payr);
			if (statmx == false) {
				stat = false; // 如果有一条数据审核不成功，提示审核问题。
				logger.info("审核保证金设定信息失败，唯一编号为：" + idall[i]);
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
		
		Paybond payr=new Paybond();
		payr.setAuditperson(username);
		payr.setAudittime(operdate);
		payr.setApprove("未通过");
		
		Json j = new Json();
		boolean stat=true;
		String[] idall=ids.split(",");
		for (int i=0;i<idall.length;i++)
		{
			payr.setId(idall[i]);
			
			boolean statmx=paybondService.editaudit(payr);
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
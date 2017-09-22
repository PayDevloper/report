package com.sumao.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sumao.model.ComTable;
import com.sumao.model.EasyuiDataGrid;
import com.sumao.model.EasyuiDataGridJson;
import com.sumao.model.Json;
import com.sumao.model.Page;
import com.sumao.model.SessionInfo;
import com.sumao.model.Pay_rightgold;
import com.sumao.model.Pay_rightgoldcheck;
import com.sumao.model.Pay_userorg;
import com.sumao.model.Paybond;
import com.sumao.service.PayRightService;
import com.sumao.util.ResourceUtil;

/**
 * 会员费资质设定控制器
 * @author 黑洁净
 * 
 */
@Controller
@RequestMapping("/payrightController")
public class PayRightController extends BaseController {

	private static final Logger logger = Logger.getLogger(PayRightController.class);

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
	 * 跳转到数据表格页面
	 * @author heijj
	 * @return
	 */
	@RequestMapping(params = "Pay_rightgold")
	public String Pay_rightgold(HttpServletRequest request) {
		String marketingid=request.getParameter("pmarketingid");  //关键字查询条件
		if ((marketingid==null) || (marketingid.equals("null")))
		{
			request.setAttribute("pmarketingid", "");
			request.setAttribute("seller", "");
			request.setAttribute("marketing", "");
		}
		else
		{
			Pay_userorg uorg = payRightService.getUserorgBy(marketingid);  //根据销售组织ID，得到销售组织，卖方等信息
			request.setAttribute("seller",uorg.getSeller());
			request.setAttribute("marketing",uorg.getMarketing());
			request.setAttribute("pmarketingid", marketingid);
		}
		
		return "/Pay/pay_rightgold";
	}
	
	/**
	 * 数据表格
	 * @author heijj
	 * @param dg
	 * @param payr
	 * @return
	 */
	@RequestMapping(params = "datagrid")
	@ResponseBody
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg, Pay_rightgold payr,HttpServletRequest request) {
		String marketingid=request.getParameter("pmarketingid");  //关键字查询条件
		if ((marketingid==null) || (marketingid.equals("null")))
		{
			marketingid=payr.getMarketingid();
		}
		else
		{
			//marketingid=marketingid;
		}
		
		payr.setMarketingid(marketingid);
		int total=payRightService.findPayrTotal(payr);
		return payRightService.datagrid(dg, payr,total);
	}

	/**
	 * 新建保存信息
	 * @param Pay_rightgold payr
	 * @param HttpSession session
	 * @return
	 */
	@RequestMapping(params = "add", produces = "text/htm;charset=UTF-8")
	@ResponseBody
	public Json add(Pay_rightgold payr,HttpSession session) {
		//createperson,createtime,approve,payment
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
		payr.setPayment("未支付");
		payr.setCreateperson(username);
		payr.setCreatetime(operdate);
		payr.setId(DOCID);
		boolean stat=payRightService.add(payr);
		
		Json j = new Json();
		j.setSuccess(stat);
		return j;
	}

	/**
	 * 编辑保存信息
	 * @param Pay_rightgold payr
	 * @param HttpSession session
	 * @return
	 */
	@RequestMapping(params = "edit", produces = "text/htm;charset=UTF-8")
	@ResponseBody
	public Json edit(Pay_rightgold payr,HttpSession session) {
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
		payr.setCreateperson(username);
		payr.setCreatetime(operdate);
		
		boolean stat=payRightService.edit(payr);
		
		Json j = new Json();
		j.setSuccess(stat);
		return j;
	}

	/**
	 * 删除用户
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
			boolean statmx=payRightService.del(idall[i]);
			if (statmx==false)
			{
				stat=false;  //如果有一条数据删除不成功，提示删除问题。
				logger.info("删除权利金设定信息失败，唯一编号为："+idall[i]);
			}
		}
		
		j.setSuccess(stat);
		return j;
	}
	
	/**
	 * 批量审核功能
	 * @param ids
	 * @param HttpSession session
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
		
		Pay_rightgold payr=new Pay_rightgold();
		payr.setAuditperson(username);
		payr.setAudittime(operdate);
		payr.setApprove("已通过");
		
		Json j = new Json();
		boolean stat=true;
		String[] idall=ids.split(",");
		for (int i=0;i<idall.length;i++)
		{
			
			payr.setId(idall[i]);
			
			boolean statmx=payRightService.editaudit(payr);
			if (statmx==false)
			{
				stat=false;  //如果有一条数据审核不成功，提示审核问题。
				logger.info("审核权利金设定信息失败，唯一编号为："+idall[i]);
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
		
		Pay_rightgold payr=new Pay_rightgold();
		payr.setAuditperson(username);
		payr.setAudittime(operdate);
		payr.setApprove("未通过");
		
		Json j = new Json();
		boolean stat=true;
		String[] idall=ids.split(",");
		for (int i=0;i<idall.length;i++)
		{
			payr.setId(idall[i]);
			
			boolean statmx=payRightService.editaudit(payr);
			if (statmx==false)
			{
				stat=false;  //如果有一条数据审核不成功，提示审核问题。
				logger.info("审核计费规则管理信息失败，唯一编号为："+idall[i]);
			}
		}
		
		j.setSuccess(stat);
		return j;
	}
	
	/**
	 * 根据销售组织以及子项配置，验证是否已通过验证
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "proving")
	@ResponseBody
	public void  proving(HttpServletRequest request,HttpServletResponse response,String mkid,String subid,Pay_rightgold payr,String start,String payid){
		String result = "{\"result\":\"success\"}";
		if(payid.equals("")||payid==""){
			payid="0";
		}
		payr.setMarketingid(mkid);
		payr.setSubitemcode(subid);
		payr.setId(payid);
		List<Pay_rightgold> enddatelist=payRightService.getproving(payr);

		if((start==null) || (start.equals("null"))||(start=="")|| (start.equals(""))){	
		}else if(enddatelist.equals("[]")){	
		}else{
			long AA=0;
			for(int i=0;i<enddatelist.size();i++){
				String enddate=enddatelist.get(i).getEnddate();
				if((enddate==null) || (enddate.equals("null"))||(enddate=="")|| (enddate.equals(""))){
				}else{
					enddate = enddate.replace(":", "").replace(" ", "").replace("-", "");
					long date=Long.parseLong(enddate);
					if(AA<date){
						AA=date;
					}
				}
			}
			String starttime=start.replace(":", "").replace(" ", "").replace("-", "");
			long newtime=Long.parseLong(starttime);

			if(newtime<AA){
				result = "{\"result\":\"error\"}";
			}
		}
		response.setContentType("application/json");
		
		try {
			PrintWriter out = response.getWriter();
			out.write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 跳转到管理查看页面
	 * @author heijj
	 * @return
	 */
	@RequestMapping(params = "Pay_rightgoldcheck")
	public String Pay_rightgoldcheck(HttpServletRequest request) {
		String marketingid=request.getParameter("orgnid");
		if ((marketingid==null) || (marketingid.equals("null")))
		{
			marketingid="";
		}
		//得到权利金信息
		List<Pay_rightgoldcheck> subitemname = payRightService.selectgoldsub();
		request.setAttribute("pmarketingid", marketingid);
		request.setAttribute("subitemname", subitemname);
		return "/Pay/pay_rightgoldcheck";
	}
	
	/**
	 * 数据表格
	 * @author heijj
	 * @param dg
	 * @param payr
	 * @return
	 */
	@RequestMapping(params = "datagridcheck")
	@ResponseBody
	public EasyuiDataGridJson datagridcheck(EasyuiDataGrid dg, Pay_rightgoldcheck payr,HttpServletRequest request) {

		String marketingid=request.getParameter("pmarketingid");
		if ((marketingid==null) || (marketingid.equals("null")))
		{
			marketingid="";
		}
		String startdate=request.getParameter("startdate");  //关键字生效开始日期
		if ((startdate==null) || (startdate.equals("null")) || (startdate.equals("")))
		{
			startdate="1900-01-01";
			payr.setStartdate(startdate);
		}
		
		String enddate=request.getParameter("enddate");  //关键字生效截止日期
		if ((enddate==null) || (enddate.equals("null")) || (enddate.equals("")))
		{
			enddate="2900-12-31";
			payr.setEnddate(enddate);
		}
		payr.setMarketingid(marketingid);
		int total=payRightService.findPayrchTotal(payr);
		return payRightService.datagridch(dg, payr,total);
	}
	
	
	@RequestMapping(params = "rightoldSeller")
	public String rightoldSeller(HttpServletRequest request,Pay_rightgoldcheck payr) {
		Page page = null;
		String subitemnamecont=request.getParameter("subitemname");
		if ((subitemnamecont==null) || (subitemnamecont.equals("null")))
		{
			subitemnamecont="";
		}
		payr.setSubitemname(subitemnamecont);
		
		String payment = request.getParameter("payment");
		if ((payment==null) || (payment.equals("null")))
		{
			payment="";
		}
		payr.setPayment(payment);
		
		String marketingid=request.getParameter("orgnid");
		if ((marketingid==null) || (marketingid.equals("null")))
		{
			marketingid="";
		}
		String startdate=request.getParameter("startdate");  //关键字生效开始日期
		if ((startdate==null) || (startdate.equals("null")) || (startdate.equals("")))
		{
			startdate="1900-01-01";
			payr.setStartdate(startdate);
		}
		
		String enddate=request.getParameter("enddate");  //关键字生效截止日期
		if ((enddate==null) || (enddate.equals("null")) || (enddate.equals("")))
		{
			enddate="2900-12-31";
			payr.setEnddate(enddate);
		}
		payr.setMarketingid(marketingid);
		//int total=payRightService.findPayrchTotal(payr);
		
		List<Pay_rightgoldcheck> findSummary = new ArrayList<Pay_rightgoldcheck>();
		int maxNum = payRightService.findPayrchTotal(payr);
	
		String pageNow = request.getParameter("pageNow");
		if (pageNow != null) {
			page = new Page(maxNum, Integer.parseInt(pageNow));
		} else {
			page = new Page(maxNum, 1);
		}
		
		findSummary = this.payRightService.showOrderByPage(page.getPageNow(), page.getPageSize(), payr);
		
		request.setAttribute("details", findSummary);
		request.setAttribute("page", page);
		request.setAttribute("payr", payr);
		
		List<Pay_rightgoldcheck> subitemname = payRightService.selectgoldsub();  //列表中“子项”的查询条件
		request.setAttribute("subitemname", subitemname);
		
		return "/Pay/pay_rightgoldseller";
	}
	
	/**
	 * 跳转到权利金全部查看数据表格页面
	 * @author heijj
	 * @return
	 */
	@RequestMapping(params = "Pay_rightgoldallcheck")
	public String Pay_rightgoldallcheck(HttpServletRequest request) {
		List<Pay_rightgoldcheck> subitemname = payRightService.selectgoldsub();
		request.setAttribute("subitemname", subitemname);
		return "/Pay/pay_rightgoldallcheck";
	}
	
	/**
	 * 数据表格
	 * @author heijj
	 * @param dg
	 * @param payr
	 * @return
	 */
	@RequestMapping(params = "datagridallcheck")
	@ResponseBody
	public EasyuiDataGridJson datagridallcheck(EasyuiDataGrid dg, Pay_rightgoldcheck payr,HttpServletRequest request) {

		int total=payRightService.findPayallchTotal(payr);
		return payRightService.datagridallch(dg, payr,total);
	}

}

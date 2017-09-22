package com.sumao.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sumao.model.EasyuiDataGrid;
import com.sumao.model.EasyuiDataGridJson;
import com.sumao.model.Json;
import com.sumao.model.Pay_userorg;
import com.sumao.model.SessionInfo;
import com.sumao.model.Payeditaccountingrules;
import com.sumao.model.Paynewaccountingrules;
import com.sumao.service.PayeditaccountingrulesService;
import com.sumao.service.PaynewaccountingrulesService;
import com.sumao.util.ResourceUtil;

/**
 * 计费规则管理
 * 
 * @author heijj
 * 
 */
@Controller
@RequestMapping("/payeditaccountingrulesController")
public class PayeditaccountingrulesController extends BaseController {

	private static final Logger logger = Logger.getLogger(PayeditaccountingrulesController.class);

	private PayeditaccountingrulesService payeditaccountingrulesService;

	public PayeditaccountingrulesService getPayeditaccountingrulesService() {
		return payeditaccountingrulesService;
	}

	@Autowired
	public void setPayeditaccountingrulesService(PayeditaccountingrulesService payeditaccountingrulesService) {
		this.payeditaccountingrulesService = payeditaccountingrulesService;
	}
	
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
	 * @param HttpServletRequest request
	 * @return
	 */
	@RequestMapping(params = "payeditaccountingrules")
	public String payeditaccountingrules(HttpServletRequest request) {
		String marketingid=request.getParameter("pmarketingid");  //销售组织ID
		if ((marketingid==null) || (marketingid.equals("null")))
		{
			request.setAttribute("pmarketingid", "");
		}
		else
		{
			Paynewaccountingrules newaccount = paynewaccountingrulesService.selectByPrimaryKey(marketingid);  //根据销售组织ID值，得到计费规则创建数据
			request.setAttribute("pmarketingid", marketingid);
			request.setAttribute("newaccount", newaccount);
		}
		return "/Pay/pay_editaccountingrules";
	}

	/**
	 * 数据表格
	 * @param dg
	 * @param Payeditaccountingrules payr
	 * @param HttpServletRequest request
	 * @return
	 */
	@RequestMapping(params = "datagrid")
	@ResponseBody
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg, Payeditaccountingrules payr,HttpServletRequest request) {

		String marketingid=request.getParameter("pmarketingid");  //关键字查询条件
		if ((marketingid==null) || (marketingid.equals("null")))
		{
			marketingid=payr.getMarketingid();
		}
		
		payr.setMarketingid(marketingid);
		
		String effectivetime=request.getParameter("effectivetime");  //关键字生效开始日期
		if ((effectivetime==null) || (effectivetime.equals("null")) || (effectivetime.equals("")))
		{
			effectivetime="1900-01-01";
			payr.setEffectivetime(effectivetime);
		}
		
		String invalidtime=request.getParameter("invalidtime");  //关键字生效截止日期
		if ((invalidtime==null) || (invalidtime.equals("null")) || (invalidtime.equals("")))
		{
			invalidtime="2900-12-31";
			payr.setInvalidtime(invalidtime);
		}
		
		int total=payeditaccountingrulesService.findPayrTotal(payr);
		return payeditaccountingrulesService.datagrid(dg, payr,total);
	}

	/**
	 * 新建保存信息
	 * @param Payeditaccountingrules payr
	 * @param HttpSession session
	 * @return
	 */
	@RequestMapping(params = "add", produces = "text/htm;charset=UTF-8")
	@ResponseBody
	public Json add(Payeditaccountingrules payr,HttpSession session,HttpServletRequest request) {
		//audit_status,operuser, operdate,id
		String username="";
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
		if (sessionInfo != null && sessionInfo.getUser() != null) {
			username=sessionInfo.getUser().getName();
		} else {
			username="admin";
		}
		String tradingpatterns=request.getParameter("tradingpatterns");
		if((tradingpatterns==null) || (tradingpatterns.equals("null"))){
			tradingpatterns=" ";
		}
		payr.setTradingpatterns(tradingpatterns);
		payr.setApprove("待审核");
		payr.setCreateperson(username);
		
		boolean stat=false;
		String maxnum = request.getParameter("maxnum");
		
		String chargingmode=payr.getChargingmode();  //计费模式chargingmode
		if (chargingmode.equals("阶梯收费"))
		{
			maxnum = request.getParameter("maxnumsf");
		}
		
		if(maxnum==""||maxnum.equals("")||maxnum.equals(" ")||maxnum==" "){
			maxnum="0";
		}
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
		String operdate = dateFormat.format(now);
		String DOCID = operdate + String.valueOf(Math.random()).substring(2, 6);
		DOCID = DOCID.replace(":", "").replace(" ", "").replace("-", "");
		payr.setId(DOCID);
		payr.setCreatetime(operdate);
		System.out.println("maxnum=="+maxnum);
		if (chargingmode.equals("线性收费")){
			stat=payeditaccountingrulesService.add(payr);
		}else if(chargingmode.equals("线性收费阶梯退费")&&maxnum.equals("0")){	//无子表时
			payr.setChargingdimensionality(payr.getChargingdimensionalitysf());
			payr.setDimensionparameter(payr.getDimensionparametersf());        //收费配置
			stat=payeditaccountingrulesService.add(payr);
		}else{	
			for(int i=0;i<Integer.parseInt(maxnum);i++){
			String aa=request.getParameter("startthreshold"+i);
			String bb=request.getParameter("startthresholdsf"+i);
			if(aa!=null||bb!=null){		
				//计费模式判断
				if (chargingmode.equals("线性收费阶梯退费"))
				{
					payr.setChargingdimensionality(payr.getChargingdimensionalitysf());
					payr.setDimensionparameter(payr.getDimensionparametersf());        //收费配置
					//子表信息保存
					payr.setStartthreshold(request.getParameter("startthreshold"+i));
					payr.setStartthresholdunit(request.getParameter("startthresholdunit"+i));
					payr.setEndthreshold(request.getParameter("endthreshold"+i));
					payr.setEndthresholdunit(request.getParameter("endthresholdunit"+i));
					payr.setStepmode(request.getParameter("stepmode"+i));
					payr.setStepmodenum(request.getParameter("stepmodenum"+i));
				}
				else if (chargingmode.equals("阶梯收费"))
				{
					//子表信息保存
					payr.setStartthreshold(request.getParameter("startthresholdsf"+i));
					payr.setStartthresholdunit(request.getParameter("startthresholdunitsf"+i));
					payr.setEndthreshold(request.getParameter("endthresholdsf"+i));
					payr.setEndthresholdunit(request.getParameter("endthresholdunitsf"+i));
					payr.setStepmode(request.getParameter("stepmodesf"+i));
					payr.setStepmodenum(request.getParameter("stepmodenumsf"+i));
				}
				
				stat=payeditaccountingrulesService.add(payr);
				}
			}
		}
		
		Paynewaccountingrules paynew=new Paynewaccountingrules();
		paynew.setMarketingid(payr.getMarketingid());
		boolean bstat=paynewaccountingrulesService.editNewAccountByID(paynew);
		if (bstat==false)
		{
			logger.info("根据组织机构ID，更新信息计费配置状态为已创建失败，唯一编号为："+payr.getMarketingid());
		}
		
		Json j = new Json();
		j.setSuccess(stat);
		return j;
	}
	
	/**
	 * 继承保存信息
	 * @param Payeditaccountingrules payr
	 * @param HttpSession session
	 * @return
	 */
	@RequestMapping(params = "inherit", produces = "text/htm;charset=UTF-8")
	@ResponseBody
	public Json inherit(Payeditaccountingrules payr,HttpSession session,HttpServletRequest request) {
		String username="";
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
		if (sessionInfo != null && sessionInfo.getUser() != null) {
			username=sessionInfo.getUser().getName();
		} else {
			username="admin";
		}
		String tradingpatterns=request.getParameter("tradingpatterns");
		if((tradingpatterns==null) || (tradingpatterns.equals("null"))){
			tradingpatterns=" ";
		}
		payr.setTradingpatterns(tradingpatterns);
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
		String operdate = dateFormat.format(now);
		String DOCID = operdate + String.valueOf(Math.random()).substring(2, 6);
		DOCID = DOCID.replace(":", "").replace(" ", "").replace("-", "");
		payr.setId(DOCID);
		
		payr.setChargingmode(request.getParameter("chargingmodes"));
		payr.setEffectivetime(request.getParameter("effectivetimes"));
		payr.setInvalidtime(request.getParameter("invalidtimes"));
		payr.setApprove("待审核");
		payr.setCreateperson(username);
		payr.setCreatetime(operdate);
		
		boolean stat=payeditaccountingrulesService.add(payr);
		
		Json j = new Json();
		j.setSuccess(stat);
		return j;
	}

	/**
	 * 编辑保存信息
	 * @param Payeditaccountingrules payr
	 * @param HttpSession session
	 * @return
	 */
	@RequestMapping(params = "edit", produces = "text/htm;charset=UTF-8")
	@ResponseBody
	public Json edit(Payeditaccountingrules payr,HttpSession session,HttpServletRequest request) {
		String username="";
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
		if (sessionInfo != null && sessionInfo.getUser() != null) {
			username=sessionInfo.getUser().getName();
		} else {
			username="admin";
		}
		String tradingpatterns=request.getParameter("tradingpatterns");
		if((tradingpatterns==null) || (tradingpatterns.equals("null"))){
			tradingpatterns=" ";
		}
		payr.setTradingpatterns(tradingpatterns);
		payeditaccountingrulesService.editHistory(payr); // 保证金设定编辑保存历史记录
		
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
		String operdate = dateFormat.format(now);
		
		payr.setChargingmode(request.getParameter("chargingmodes"));
		payr.setEffectivetime(request.getParameter("effectivetimes"));
		payr.setInvalidtime(request.getParameter("invalidtimes"));
		payr.setApprove("待审核");
		payr.setCreateperson(username);
		payr.setCreatetime(operdate);
		
		boolean stat=payeditaccountingrulesService.edit(payr);
		
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
		boolean stat=true;
		String[] idall=ids.split(",");
		for (int i=0;i<idall.length;i++)
		{
			boolean statmx=payeditaccountingrulesService.del(idall[i]);
			if (statmx==false)
			{
				stat=false;  //如果有一条数据删除不成功，提示删除问题。
				logger.info("删除计费规则管理信息失败，唯一编号为："+idall[i]);
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
		
		Payeditaccountingrules payr=new Payeditaccountingrules();
		payr.setAuditperson(username);
		payr.setAudittime(operdate);
		payr.setApprove("已通过");
		
		Json j = new Json();
		boolean stat=true;
		String[] idall=ids.split(",");
		for (int i=0;i<idall.length;i++)
		{
			payr.setId(idall[i]);
			
			boolean statmx=payeditaccountingrulesService.editaudit(payr);
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
		
		Payeditaccountingrules payr=new Payeditaccountingrules();
		payr.setAuditperson(username);
		payr.setAudittime(operdate);
		payr.setApprove("未通过");
		
		Json j = new Json();
		boolean stat=true;
		String[] idall=ids.split(",");
		for (int i=0;i<idall.length;i++)
		{
			payr.setId(idall[i]);
			
			boolean statmx=payeditaccountingrulesService.editaudit(payr);
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
	 * 根据销售组织以及相应配置，验证时间是否交叉
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "provingtime")
	@ResponseBody
	public void  provingtime(HttpServletRequest request,HttpServletResponse response,Payeditaccountingrules paye,String mkid,String charty,String start,String end,String trad,String charde,String payid){
		String result = "{\"result\":\"success\"}";
		if(payid.equals("")||payid==""){
			payid="0";
		}
		paye.setMarketingid(mkid);
		paye.setChargingparty(charty);
		paye.setTradingpatterns(trad);
		paye.setChargingmode(charde);
		paye.setEffectivetime(start);
		paye.setInvalidtime(end);
		paye.setId(payid);
		int provingtime=payeditaccountingrulesService.getprovingtime(paye);
		
		if(provingtime==0){	
		}else{
			result = "{\"result\":\"error\"}";
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
	 * 根据销售组织以及相应配置，验证阈值是否交叉
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "provingshold")
	@ResponseBody
	public void  provingshold(HttpServletRequest request,HttpServletResponse response,Payeditaccountingrules paye,String mkid,String charty,String start,String end,String startsh,String endsh,String trad,String charde,String payid){
		String result = "{\"result\":\"success\"}";
		if(payid.equals("")||payid==""){
			payid="0";
		}
		if(endsh.equals("#")||endsh=="#"){
			endsh="99999999";
		}
		paye.setMarketingid(mkid);
		paye.setChargingparty(charty);
		paye.setTradingpatterns(trad);
		paye.setChargingmode(charde);
		paye.setEffectivetime(start);
		paye.setInvalidtime(end);
		paye.setStartthreshold(startsh);
		paye.setEndthreshold(endsh);
		paye.setId(payid);
		int provingshold=payeditaccountingrulesService.getprovingshold(paye);
		
		if(provingshold==0){	
		}else{
			result = "{\"result\":\"error\"}";
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
	 * 跳转到计费规则管理查询页面
	 * @param HttpServletRequest request
	 * @return
	 */
	@RequestMapping(params = "payseachaccountingrules")
	public String payseachaccountingrules(HttpServletRequest request) {
		return "/Pay/pay_seachaccountingrules";
	}
	
	/**
	 * 计费配置查询数据表格
	 * @param dg
	 * @param Payeditaccountingrules payr
	 * @param HttpServletRequest request
	 * @return
	 */
	@RequestMapping(params = "datagridseach")
	@ResponseBody
	public EasyuiDataGridJson datagridseach(EasyuiDataGrid dg, Payeditaccountingrules payr,HttpServletRequest request) {
		
		String effectivetime=request.getParameter("effectivetime");  //关键字生效开始日期
		if ((effectivetime==null) || (effectivetime.equals("null")) || (effectivetime.equals("")))
		{
			effectivetime="1900-01-01";
			payr.setEffectivetime(effectivetime);
		}
		
		String invalidtime=request.getParameter("invalidtime");  //关键字生效截止日期
		if ((invalidtime==null) || (invalidtime.equals("null")) || (invalidtime.equals("")))
		{
			invalidtime="2900-12-31";
			payr.setInvalidtime(invalidtime);
		}
		
		int total=payeditaccountingrulesService.findPayseachTotal(payr);
		return payeditaccountingrulesService.datagridseach(dg, payr,total);
	}
	
	/**
	 * 跳转到选择信息页面
	 * @return "/Pay/pay_brandsel"
	 */
	@RequestMapping(params = "Paybrandsel")
	public String Paybrandsel() {
		return "/Pay/pay_brandsel";
	}
	
	/**
	 * 数据产品牌号
	 * 
	 * @param dg
	 * @param payr
	 * @return
	 */
	@RequestMapping(params = "datagridselbrand")
	@ResponseBody
	public EasyuiDataGridJson datagridselbrand(EasyuiDataGrid dg, Payeditaccountingrules payr) {
		int total=payeditaccountingrulesService.findPaybrandTotal(payr);
		
		return payeditaccountingrulesService.datagridbrand(dg, payr,total);
	}
	
	/**
	 * 返回页面的json,返回值是牌号大类信息的json
	 */
	@RequestMapping(params = "findoneebs")
	public void findoneebs(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String marketingid = request.getParameter("marketingid");
		List<Payeditaccountingrules> findoneebs = payeditaccountingrulesService.findoneebs(marketingid);
		JSONArray json = JSONArray.fromObject(findoneebs);
		response.getWriter().print(json.toString());
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	/**
	 * 返回页面的json,返回值是牌号中类信息的json
	 */
	@RequestMapping(params = "findtwoebs")
	public void findtwoebs(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String ebsname = request.getParameter("ebsname");
		String marketingid = request.getParameter("marketingid");
		List<Payeditaccountingrules> findtwoebs = payeditaccountingrulesService.findtwoebs(marketingid,ebsname);
		JSONArray json = JSONArray.fromObject(findtwoebs);
		response.getWriter().print(json.toString());
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	/**
	 * 返回页面的json,返回值是牌号小类信息的json
	 */
	@RequestMapping(params = "findthreeebs")
	public void findthreeebs(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String ebsname = request.getParameter("ebsname");
		String marketingid = request.getParameter("marketingid");
		List<Payeditaccountingrules> findthreeebs = payeditaccountingrulesService.findthreeebs(marketingid,ebsname);
		JSONArray json = JSONArray.fromObject(findthreeebs);
		response.getWriter().print(json.toString());
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	/**
	 * 返回页面的json,返回值是牌号信息的json
	 */
	@RequestMapping(params = "findgradeebs")
	public void findgradeebs(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String ebsname = request.getParameter("ebsname");
		String marketingid = request.getParameter("marketingid");
		List<Payeditaccountingrules> findgradeebs = payeditaccountingrulesService.findgradeebs(marketingid,ebsname);
		JSONArray json = JSONArray.fromObject(findgradeebs);
		response.getWriter().print(json.toString());
		response.getWriter().flush();
		response.getWriter().close();
	}

}
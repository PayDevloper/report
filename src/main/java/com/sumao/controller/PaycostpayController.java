package com.sumao.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.sumao.model.Paycostpay;
import com.sumao.model.Paybond;
import com.sumao.model.Pay_rightgold;
import com.sumao.model.Pay_rightgoldcheck;
import com.sumao.service.DetailService;
import com.sumao.service.PayRightService;
import com.sumao.service.PaybondService;
import com.sumao.service.PaycostpayService;
import com.sumao.util.ResourceUtil;

/**
 * 费用支付
 * @author heijj
 * 
 */
@Controller
@RequestMapping("/paycostpayController")
public class PaycostpayController extends BaseController {

	private static final Logger logger = Logger.getLogger(PaycostpayController.class);

	private PaycostpayService paycostpayService;
	

	public PaycostpayService getPaycostpayService() {
		return paycostpayService;
	}

	@Autowired
	public void setPaycostpayService(PaycostpayService paycostpayService) {
		this.paycostpayService = paycostpayService;
	}
	
	@Resource
	private DetailService detailService;
	
	public DetailService getDetailService() {
		return detailService;
	}

	@Autowired
	public void setDetailService(DetailService detailService) {
		this.detailService = detailService;
	}

	
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
	 * @param HttpServletRequest request
	 * @return
	 */
	@RequestMapping(params = "paycostpay")
	public String Paycostpay(HttpServletRequest request) {
		String ty=request.getParameter("ty");  //费用支付的类型，是权利金rgold还是保证金bond
		String marketingid=request.getParameter("pmarketingid");  //设定数据的ID值
		if ((ty==null) || (ty.equals("null")))
		{
			ty="bond";  //保证金bond
		}

		List<ComTable> paymentmethod = detailService.selecttype("zhifu");
		request.setAttribute("paymentmethod", paymentmethod);
		
		if ((marketingid==null) || (marketingid.equals("null")))
		{
			request.setAttribute("ty",ty);
			request.setAttribute("marketingid", "");
		}
		else
		{
			Paycostpay costp = new Paycostpay();
			if (ty.equals("bond"))
			{
				Paybond bond = paybondService.selectByPrimaryKey(marketingid);  //根据保证金设定的ID值，得到数据，用于自动带入“费用支付”新建页面内容
				costp.setSeller(bond.getSeller());
				costp.setMarketing(bond.getMarketing());
				costp.setMarketingid(bond.getMarketingid());
				costp.setPaymentcode(bond.getId());
				costp.setSubitemname(bond.getSubitemname());
				costp.setTotalitemname(bond.getTotalitemname());
				costp.setFinanceacc(bond.getFinanceacc());
				costp.setShouldpay(bond.getMinpayamount());
			}
			else
			{
				Pay_rightgold rgold = payRightService.selectByPrimaryKey(marketingid);  //根据权利金设定的ID值，得到数据，用于自动带入“费用支付”新建页面内容
				costp.setSeller(rgold.getSeller());
				costp.setMarketing(rgold.getMarketing());
				costp.setMarketingid(rgold.getMarketingid());
				costp.setPaymentcode(rgold.getId());
				costp.setSubitemname(rgold.getSubitemname());
				costp.setTotalitemname(rgold.getTotalitemname());
				costp.setFinanceacc(rgold.getFinanceacc());
				costp.setShouldpay(rgold.getPayamount());
			}
			request.setAttribute("ty",ty);
			request.setAttribute("pmarketingid", marketingid);
			request.setAttribute("costp",costp);  //费用支付页面中自动带入的内容
			
		}
		return "/Pay/pay_costpay";
	}
	
	/**
	 * 跳转到卖家中心--保证金缴费查看页面
	 * @param HttpServletRequest request
	 * @return
	 */
	@RequestMapping(params = "paybondcostpay")
	public String Paybondcostpay(HttpServletRequest request) {
		String ty="bond";  //费用支付的类型，是权利金rgold还是保证金bond
		String marketingid=request.getParameter("orgnid");  //设定数据的ID值
		
		if ((marketingid==null) || (marketingid.equals("null")))
		{
			request.setAttribute("ty",ty);
			request.setAttribute("marketingid", "");
		}
		else
		{
			Paycostpay costp = new Paycostpay();
			if (ty.equals("bond"))
			{
				Paybond bond = paybondService.selectByMarketingid(marketingid);  //根据保证金设定中的销售组织ID值，得到数据，用于自动带入卖家中心“保证经缴费查看”
				costp.setSeller(bond.getSeller());
				costp.setMarketing(bond.getMarketing());
				costp.setMarketingid(bond.getMarketingid());
				costp.setPaymentcode(bond.getId());
				costp.setSubitemname(bond.getSubitemname());
				costp.setTotalitemname(bond.getTotalitemname());
				costp.setFinanceacc(bond.getFinanceacc());
				costp.setShouldpay(bond.getMinpayamount());
				costp.setCodeid(bond.getWarningsum());  //预警值
				costp.setRemark(bond.getHavedeposit());  //现有保证金
			}
			
			request.setAttribute("ty",ty);
			request.setAttribute("pmarketingid", marketingid);
			request.setAttribute("costp",costp);  //费用支付页面中自动带入的内容
		}
		return "/Pay/pay_bondcostpay";
	}

	/**
	 * 数据表格
	 * @param dg
	 * @param Paycostpay payr
	 * @param HttpServletRequest request
	 * @return
	 */
	@RequestMapping(params = "datagrid")
	@ResponseBody
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg, Paycostpay payr,HttpServletRequest request) {
		String marketingid=request.getParameter("pmarketingid");  //关键字查询条件
		if ((marketingid==null) || (marketingid.equals("null")))
		{
			marketingid="";
		}
		
		payr.setPaymentcode(marketingid);
		int total=paycostpayService.findPayrTotal(payr);
		return paycostpayService.datagrid(dg, payr,total);
	}
	
	/**
	 * 卖家中心--保证金缴费查看数据表格
	 * @param dg
	 * @param Paycostpay payr
	 * @param HttpServletRequest request
	 * @return
	 */
	@RequestMapping(params = "datagridcost")
	@ResponseBody
	public EasyuiDataGridJson datagridcost(EasyuiDataGrid dg, Paycostpay payr,HttpServletRequest request) {
		String marketingid=request.getParameter("pmarketingid");  //关键字查询条件
		if ((marketingid==null) || (marketingid.equals("null")))
		{
			marketingid="";
		}
		
		payr.setMarketingid(marketingid);
		int total=paycostpayService.findPayrcostTotal(payr);
		return paycostpayService.datagridcost(dg, payr,total);
	}
	

	
	@RequestMapping(params = "bondSeller")
	public String bondSeller(HttpServletRequest request,Paycostpay payr) {
		Page page = null;
		String marketingid=request.getParameter("orgnid");  //关键字查询条件
		if ((marketingid==null) || (marketingid.equals("null")))
		{
			marketingid="";
		}
		payr.setMarketingid(marketingid);
		
		String financeacc=request.getParameter("financeacc");
		if ((financeacc==null) || (financeacc.equals("null")))
		{
			financeacc="";
		}
		payr.setFinanceacc(financeacc);
		
		List<Paycostpay> findSummary = new ArrayList<Paycostpay>();
		int maxNum = paycostpayService.findPayrcostTotal(payr);
	
		String pageNow = request.getParameter("pageNow");
		if (pageNow != null) {
			page = new Page(maxNum, Integer.parseInt(pageNow));
		} else {
			page = new Page(maxNum, 1);
		}
		
		findSummary = this.paycostpayService.showOrderByPage(page.getPageNow(), page.getPageSize(), payr);
		
		request.setAttribute("details", findSummary);
		request.setAttribute("page", page);
		request.setAttribute("payr", payr);
		
		return "/Pay/pay_bondseller";
	}
	

	/**
	 * 新建保存信息
	 * @param Paycostpay payr
	 * @param HttpSession session
	 * @return
	 */
	@RequestMapping(params = "add", produces = "text/htm;charset=UTF-8")
	@ResponseBody
	public Json add(Paycostpay payr,HttpSession session) {
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
		
		payr.setCreateperson(username);
		payr.setCreatetime(operdate);
		payr.setId(DOCID);
		boolean stat=paycostpayService.add(payr);
		//根据设定信息编号，更新设定信息“是否缴费”状态，如果是保证金支付，则增加“现有保证金”数量
		String codeid=payr.getCodeid();
		if (codeid.equals("bond"))
		{
			Paybond payb=new Paybond();
			payb.setHavedeposit(payr.getActualpay());
			payb.setId(payr.getPaymentcode());
			boolean bstat=paybondService.editBondByID(payb);
			if (bstat==false)
			{
				logger.info("保证金设定信息更新设定信息“是否缴费、现有保证金”内容失败，唯一编号为："+payb.getId());
			}
			else
			{
				Paybond paybondh=paybondService.selectByPrimaryKey(payr.getPaymentcode());
				paybondh.setCodeid("--");
				paybondh.setRemark1(payr.getActualpay());  //发生金额
				paybondh.setRemark2("支付保证金");       //发生类型
				paybondh.setCreatetime(operdate);    //发生日期
				paybondService.addHistory(paybondh); // 保证金设定编辑保存历史记录
			}
		}
		else
		{
			Pay_rightgold payrg=new Pay_rightgold();
			payrg.setId(payr.getPaymentcode());
			boolean rgstat=payRightService.editBondByID(payrg);
			if (rgstat==false)
			{
				logger.info("权利金设定信息更新设定信息“是否缴费”内容失败，唯一编号为："+payrg.getId());
			}
		}
		
		Json j = new Json();
		j.setSuccess(stat);
		return j;
	}

	/**
	 * 编辑保存信息
	 * @param Paycostpay payr
	 * @param HttpSession session
	 * @return
	 */
	@RequestMapping(params = "edit", produces = "text/htm;charset=UTF-8")
	@ResponseBody
	public Json edit(Paycostpay payr,HttpSession session) {
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
		
		payr.setCreateperson(username);
		payr.setCreatetime(operdate);
		
		boolean stat=paycostpayService.edit(payr);
		
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
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
		String operdate = dateFormat.format(now);
		
		String[] idall=ids.split(",");
		for (int i=0;i<idall.length;i++)
		{
			//处理保证金下费用支付的删除情况。
			Paycostpay payr=paycostpayService.selectByPrimaryKey(idall[i]);
			String codeid=payr.getCodeid();
			String actualpay=payr.getActualpay();
			String paymentcode=payr.getPaymentcode();
			
			boolean statmx=paycostpayService.del(idall[i]);
			if (statmx==false)
			{
				stat=false;  //如果有一条数据删除不成功，提示删除问题。
				logger.info("删除费用支付信息失败，唯一编号为："+idall[i]);
			}
			else
			{
				if (codeid.equals("bond"))
				{
					Paybond payb=new Paybond();
					payb.setHavedeposit("-"+actualpay);
					payb.setId(paymentcode);
					boolean bstat=paybondService.editBondByID(payb);
					if (bstat==false)
					{
						logger.info("删除费用支付信息后，保证金设定信息更新设定信息“是否缴费、现有保证金”内容失败，唯一编号为："+payb.getId());
					}
					else
					{
						//删除保证金交费信息，形成保证金设定中的历史信息，用于保存删除已交保证金记录。 
						Paybond paybondh=paybondService.selectByPrimaryKey(paymentcode);
						paybondh.setCodeid("--");
						paybondh.setRemark1("-"+actualpay);  //发生金额
						paybondh.setRemark2("支付保证金删除");    //发生类型
						paybondh.setCreatetime(operdate);    //发生日期
						paybondService.addHistory(paybondh); // 保证金设定编辑保存历史记录
					}
				}
			}
		}
		
		j.setSuccess(stat);
		return j;
	}
	
	/**
	 * 跳转到权利金全部查看数据表格页面
	 * @author heijj
	 * @return
	 */
	@RequestMapping(params = "Pay_bondallcheck")
	public String Pay_bondallcheck(HttpServletRequest request) {
		List<Paycostpay> subitemname = paycostpayService.selectgoldsub();
		request.setAttribute("subitemname", subitemname);
		return "/Pay/pay_bondallcheck";
	}
	
	/**
	 * 数据表格
	 * @author heijj
	 * @param dg
	 * @param payr
	 * @return
	 */
	@RequestMapping(params = "datagridallcost")
	@ResponseBody
	public EasyuiDataGridJson datagridallcost(EasyuiDataGrid dg, Paycostpay payr,HttpServletRequest request) {

		int total=paycostpayService.findPayallchTotal(payr);
		return paycostpayService.datagridallch(dg, payr,total);
	}
	
	/**
	 * 根据是否为权利金以及缴费编号，查询是否已创建
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "provingused")
	@ResponseBody
	public void  provingtime(HttpServletRequest request,HttpServletResponse response,String paymentcode,String ty){
		String result = "{\"result\":\"success\"}";
		if(ty.equals("bond")||ty=="bond"){
		}else{
		int provingused=paycostpayService.getprovingused(paymentcode);
		if(provingused==0){	
			}else{
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

}

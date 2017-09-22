package com.sumao.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sumao.model.EasyuiDataGrid;
import com.sumao.model.EasyuiDataGridJson;
import com.sumao.model.Json;
import com.sumao.model.Paydepositbalance;
import com.sumao.service.PaydepositbalanceService;

/**
 * 保证金结算
 * 
 * @author heijj
 * 
 */
@Controller
@RequestMapping("/paydepositbalanceController")
public class PaydepositbalanceController extends BaseController {

	//private static final Logger logger = Logger.getLogger(PaydepositbalanceController.class);

	private PaydepositbalanceService paydepositbalanceService;

	public PaydepositbalanceService getPaydepositbalanceService() {
		return paydepositbalanceService;
	}

	@Autowired
	public void setPaydepositbalanceService(PaydepositbalanceService paydepositbalanceService) {
		this.paydepositbalanceService = paydepositbalanceService;
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
	 * 跳转到管理页面
	 * @author heijj
	 * @return
	 */
	@RequestMapping(params = "paydepositbalance")
	public String paydepositbalance() {
		return "/Pay/pay_depositbalance";
	}

	/**
	 * 结算信息表格
	 * @author heijj
	 * @param dg
	 * @param payr
	 * @return
	 */
	@RequestMapping(params = "datagrid")
	@ResponseBody
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg, Paydepositbalance payr,HttpServletRequest request) {
		String marketingid=request.getParameter("marketingid");  //关键字查询条件
		if ((marketingid==null) || (marketingid.equals("null")))
		{
		}
		else
		{
			marketingid=payr.getMarketingid();
		}

		payr.setMarketingid(marketingid);
		
		
		int total=paydepositbalanceService.findPayrTotal(payr);
		return paydepositbalanceService.datagrid(dg, payr,total);
	}

	/**
	 * 新建保存信息
	 * @param Paydepositbalance payr
	 * @return
	 */
	@RequestMapping(params = "add", produces = "text/htm;charset=UTF-8")
	@ResponseBody
	public Json add(Paydepositbalance payr) {
		//audit_status,operuser, operdate,id
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
		String operdate = dateFormat.format(now);
		String DOCID = operdate + String.valueOf(Math.random()).substring(2, 6);
		DOCID = DOCID.replace(":", "").replace(" ", "").replace("-", "");
		
		payr.setCreateperson("admin");
		payr.setCreatetime(operdate);
		payr.setId(DOCID);
		boolean stat=paydepositbalanceService.add(payr);
		
		Json j = new Json();
		j.setSuccess(stat);
		return j;
	}

	/**
	 * 编辑保存信息
	 * @param Paydepositbalance payr
	 * @return
	 */
	@RequestMapping(params = "edit", produces = "text/htm;charset=UTF-8")
	@ResponseBody
	public Json edit(Paydepositbalance payr) {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
		String operdate = dateFormat.format(now);
		
		payr.setCreateperson("admin");
		payr.setCreatetime(operdate);
		
		boolean stat=paydepositbalanceService.edit(payr);
		
		Json j = new Json();
		j.setSuccess(stat);
		return j;
	}

	/**
	 * 删除结算信息
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
			boolean statmx=paydepositbalanceService.del(idall[i]);
			if (statmx==false)
			{
				stat=false;  //如果有一条数据删除不成功，提示删除问题。
			}
		}
		
		j.setSuccess(stat);
		return j;
	}
	
	

}
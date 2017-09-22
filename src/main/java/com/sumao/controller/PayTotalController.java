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
import com.sumao.model.SessionInfo;
import com.sumao.model.Paytotal;
import com.sumao.service.PayTotalService;
import com.sumao.util.ResourceUtil;

/**
 * 总项控制器
 * @author 黑洁净
 * 
 */
@Controller
@RequestMapping("/payTotalController")
public class PayTotalController extends BaseController {

	private static final Logger logger = Logger.getLogger(PayTotalController.class);

	private PayTotalService payTotalService;

	public PayTotalService getPayTotalService() {
		return payTotalService;
	}

	@Autowired
	public void setPayTotalService(PayTotalService payTotalService) {
		this.payTotalService = payTotalService;
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
	@RequestMapping(params = "Paytotal")
	public String Paytotal() {
		return "/Pay/pay_total";
	}
	
	/**
	 * 跳转到选择信息页面
	 * @return "/Pay/pay_totalsel"
	 */
	@RequestMapping(params = "Paytotalsel")
	public String Paytotalsel() {
		return "/Pay/pay_totalsel";
	}
	
	/**
	 * 总项表格
	 * @param dg
	 * @param payr
	 * @return
	 */
	@RequestMapping(params = "datagrid")
	@ResponseBody
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg, Paytotal payr) {
		int total=payTotalService.findTotal(payr);
		
		return payTotalService.datagrid(dg, payr,total);
	}
	
	/**
	 * 总项选择表格
	 * @param dg
	 * @param payr
	 * @return
	 */
	@RequestMapping(params = "datagridsel")
	@ResponseBody
	public EasyuiDataGridJson datagridsel(EasyuiDataGrid dg, Paytotal payr) {
		int total=payTotalService.findSelTotal(payr);
		
		return payTotalService.datagrid(dg, payr,total);
	}

	/**
	 * 新建保存信息
	 * @param Paytotal payr
	 * @param HttpSession session
	 * @return
	 */
	@RequestMapping(params = "add", produces = "text/htm;charset=UTF-8")
	@ResponseBody
	public Json add(Paytotal payr,HttpSession session) {
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
		
		payr.setOperuser(username);
		payr.setOperdate(operdate);
		payr.setId(DOCID);
		boolean stat=payTotalService.add(payr);
		
		Json j = new Json();
		j.setSuccess(stat);
		return j;
	}

	/**
	 * 编辑保存信息
	 * @param Paytotal payr
	 * @param HttpSession session
	 * @return
	 */
	@RequestMapping(params = "edit", produces = "text/htm;charset=UTF-8")  
	@ResponseBody
	public Json edit(Paytotal payr,HttpSession session) {
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
		
		boolean stat=payTotalService.edit(payr);
		
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
			payTotalService.deBondByPrimaryKey(idall[i]);  //删除保证金设定信息
			payTotalService.delRightByPrimaryKey(idall[i]);  //删除权利金设定信息
			payTotalService.delSubByPrimaryKey(idall[i]);  //删除子项信息
			
			boolean statmx=payTotalService.del(idall[i]);
			if (statmx==false)
			{
				stat=false;  //如果有一条数据删除不成功，提示删除问题。
				logger.info("删除总项信息失败，唯一编号为："+idall[i]);
			}
			else
			{
				
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
		boolean stat=true;
		String[] idall=ids.split(",");
		for (int i=0;i<idall.length;i++)
		{
			boolean statmx=payTotalService.editaudit(idall[i]);
			if (statmx==false)
			{
				stat=false;  //如果有一条数据审核不成功，提示审核问题。
				logger.info("审核总项信息失败，唯一编号为："+idall[i]);
			}
		}
		
		j.setSuccess(stat);
		return j;
	}
	

}

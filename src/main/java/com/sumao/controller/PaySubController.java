package com.sumao.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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
import com.sumao.model.SessionInfo;
import com.sumao.model.Paysub;
import com.sumao.model.Pay_userorg;
import com.sumao.model.Payeditaccountingrules;
import com.sumao.service.DetailService;
import com.sumao.service.PaySubService;
import com.sumao.util.ResourceUtil;

import net.sf.json.JSONArray;

/**
 * 子项管理\销售组织配置控制器
 * @author 黑洁净
 * 
 */
@Controller
@RequestMapping("/paySubController")
public class PaySubController extends BaseController {

	private static final Logger logger = Logger.getLogger(PaySubController.class);

	private PaySubService paySubService;
	@Resource
	private DetailService detailService;

	public PaySubService getPaySubService() {
		return paySubService;
	}

	@Autowired
	public void setPaySubService(PaySubService paySubService) {
		this.paySubService = paySubService;
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
	 * 跳转到信息管理页面
	 * @param HttpServletRequest
	 * @return
	 */
	@RequestMapping(params = "Paysub")
	public String Paysub(HttpServletRequest request) {
		//得到财务科姆数据字典信息
				List<ComTable> financeacc = detailService.selecttype("caiwu");
				request.setAttribute("financeacc", financeacc);
		return "/Pay/pay_sub";
	}

	/**
	 * 跳转到权利金信息选择页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "Paysubsel")
	public String Paysubsel() {
		return "/Pay/pay_subsel";
	}
	
	/**
	 * 跳转到保证金子项信息选择页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "Paysubselbond")
	public String Paysubselbond() {
		return "/Pay/pay_subselbond";
	}

	/**
	 * 跳转到销售组织配置页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "Payuserorg")
	public String Payuserorg() {
		return "/Pay/pay_userorg";
	}
	
	/**
	 * 跳转到销售组织选择页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "Payseluserorg")
	public String Payseluserorg() {
		return "/Pay/pay_seluserorg";
	}
	
	/**
	 * 数据表格
	 * @param dg
	 * @param payr
	 * @return
	 */
	@RequestMapping(params = "datagrid")
	@ResponseBody
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg, Paysub payr) {
		int total=paySubService.findTotal(payr);
		
		return paySubService.datagrid(dg, payr,total);
	}
	
	/**
	 * 数据选择权利金子项表格
	 * @param dg
	 * @param Paysub payr
	 * @author heijj
	 * @return
	 */
	@RequestMapping(params = "datagridsel")
	@ResponseBody
	public EasyuiDataGridJson datagridsel(EasyuiDataGrid dg, Paysub payr) {
		int total=paySubService.findSelTotal(payr);
		
		return paySubService.datagridsel(dg, payr,total);
	}
	
	/**
	 * 数据选择保证金子项表格
	 * @param dg
	 * @param Paysub payr
	 * @author heijj
	 * @return
	 */
	@RequestMapping(params = "datagridselbond")
	@ResponseBody
	public EasyuiDataGridJson datagridselbond(EasyuiDataGrid dg, Paysub payr) {
		int total=paySubService.findSelbondTotal(payr);
		
		return paySubService.datagridselbond(dg, payr,total);
	}
	
	/**
	 * 数据销售组织配置
	 * 
	 * @param dg
	 * @param payr
	 * @return
	 */
	@RequestMapping(params = "datagriduserorg")
	@ResponseBody
	public EasyuiDataGridJson datagriduserorg(EasyuiDataGrid dg, Pay_userorg payr) {
		int total=paySubService.findPayrTotal(payr);
		
		return paySubService.datagriduserorg(dg, payr,total);
	}
	
	/**
	 * 数据销售组织选择 
	 * 
	 * @param dg
	 * @param payr
	 * @return
	 */
	@RequestMapping(params = "datagridseluserorg")
	@ResponseBody
	public EasyuiDataGridJson datagridseluserorg(EasyuiDataGrid dg, Pay_userorg payr) {
		int total=paySubService.findPayrTotal(payr);
		
		return paySubService.datagriduserorg(dg, payr,total);
	}

	/**
	 * 新建保存信息
	 * @param Paysub payr
	 * @param HttpSession session
	 * @return
	 */
	@RequestMapping(params = "add", produces = "text/htm;charset=UTF-8")
	@ResponseBody
	public Json add(Paysub payr,HttpSession session) {
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
		boolean stat=paySubService.add(payr);
		
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
	@RequestMapping(params = "edit", produces = "text/htm;charset=UTF-8")
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
		
		boolean stat=paySubService.edit(payr);
		
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
			paySubService.deBondByPrimaryKey(idall[i]);  //删除保证金设定信息
			paySubService.delRightByPrimaryKey(idall[i]);  //删除权利金设定信息
			
			boolean statmx=paySubService.del(idall[i]);
			if (statmx==false)
			{
				stat=false;  //如果有一条数据删除不成功，提示删除问题。
				logger.info("删除子项信息失败，唯一编号为："+idall[i]);
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
			boolean statmx=paySubService.editaudit(idall[i]);
			if (statmx==false)
			{
				stat=false;  //如果有一条数据审核不成功，提示审核问题。
				logger.info("审核子项信息失败，唯一编号为："+idall[i]);
			}
		}
		
		j.setSuccess(stat);
		return j;
	}
	
	/**
	 * 根据销售组织id，查询是否已存在该计费规则
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "provingmkid")
	@ResponseBody
	public void  provingmkid(HttpServletRequest request,HttpServletResponse response,String mkid){
		String result = "{\"result\":\"success\"}";

		int provingmkid=paySubService.getprovingmkid(mkid);
		
		if(provingmkid==0){	
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
	 * 检索条件初始，得到“卖方名称”信息
	 * @param HttpServletRequest
	 * @return
	 */
	@RequestMapping(params = "showSeller")
	public void showSeller(HttpServletRequest request,HttpServletResponse response) throws IOException {
		//得到财务科姆数据字典信息
		List<Pay_userorg> showseller = paySubService.showSeller();
		
		JSONArray json = JSONArray.fromObject(showseller);
		response.getWriter().print(json.toString());
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	/**
	 * 检索条件初始，得到“卖方名称”信息
	 * @param HttpServletRequest
	 * @return
	 */
	@RequestMapping(params = "selSeller")
	public void selSeller(HttpServletRequest request,HttpServletResponse response) throws IOException {
		//得到财务科姆数据字典信息
		String sellername = request.getParameter("sellername");
		sellername=java.net.URLDecoder.decode(sellername , "UTF-8");
		List<Pay_userorg> selseller = paySubService.selSeller(sellername);
		
		JSONArray json = JSONArray.fromObject(selseller);
		response.getWriter().print(json.toString());
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	

}

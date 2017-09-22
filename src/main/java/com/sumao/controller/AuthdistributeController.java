package com.sumao.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
import com.sumao.model.Paysub;
import com.sumao.model.SessionInfo;
import com.sumao.service.AuthDistributeService;
import com.sumao.service.DetailService;
import com.sumao.util.ResourceUtil;

/**
 * 功能：权限的管理维护1.0
 * 
 * @author liutong
 * 
 */
@Controller
@RequestMapping("/AuthdistributeController")
public class AuthdistributeController extends BaseController {

	private static final Logger logger = Logger.getLogger(AuthdistributeController.class);

	@Resource
	private DetailService detailService;
	@Resource
	private AuthDistributeService authDistributeService;

	public AuthDistributeService getAuthDistributeService() {
		return authDistributeService;
	}

	@Autowired
	public void setAuthDistributeService(AuthDistributeService authDistributeService) {
		this.authDistributeService = authDistributeService;
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
	 * 跳转到权限分配页面
	 * 
	 * @param HttpServletRequest
	 * @return
	 */
	@RequestMapping(params = "toauthdis")
	public String authDistribute(HttpServletRequest request) {
		return "/authority/auth_distribute";
	}

	/**
	 * 显示权限分配的数据表格
	 * 
	 * @param dg
	 * @param payr
	 * @return
	 */
	@RequestMapping(params = "disauthdatagrid")
	@ResponseBody
	public EasyuiDataGridJson disauthdatagrid(EasyuiDataGrid dg, Paysub payr, HttpServletRequest request) {
		int total = authDistributeService.disauthTotal(payr);
		return authDistributeService.disauthdatagrid(dg, payr, total);
	}

	/**
	 * 岗位的数据表格
	 * 
	 * @param dg
	 * @param payr
	 * @return
	 */
	@RequestMapping(params = "datagridposition")
	@ResponseBody
	public EasyuiDataGridJson datagridposition(EasyuiDataGrid dg, Paysub payr) {
		int total = authDistributeService.findposTotal(payr);
		return authDistributeService.datagridpos(dg, payr, total);
	}

	/**
	 * 跳转到选择岗位信息页面
	 * 
	 * @return "/Pay/pay_totalsel"
	 */
	@RequestMapping(params = "positionsel")
	public String Paytotalsel() {
		return "/authority/auth_selposition";
	}

	/**
	 * 新建保存信息
	 * 
	 * @param Paysub
	 *            payr
	 * @param HttpSession
	 *            session，HttpServletRequest request
	 * @return
	 */
	@RequestMapping(params = "add")
	@ResponseBody
	public Json add(Paysub payr, HttpSession session, HttpServletRequest request) {
		if (request.getParameter("auth").toString().trim() != null
				&& !request.getParameter("auth").toString().trim().equals("")) {
			payr.setFinanceacc(request.getParameter("auth").toString().trim());
		} else {
			payr.setFinanceacc("");
		}
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

		String z = payr.getFinanceacc();
		String aa = "";
		String bb = "";
		if (z != null && !z.equals("")) {
			String[] zz = z.split(",");
			String AA = "";
			String BB = "";
			for (int i = 0; i < zz.length; i++) {
				String x[] = zz[i].split(";");
				AA = AA + x[0] + ",";
				BB = BB + x[1] + ",";
			}
			aa = AA.substring(0, AA.length() - 1);
			bb = BB.substring(0, BB.length() - 1);
		} else {
			aa = "";
			bb = "";
		}

		payr.setFinanceacc(aa);
		payr.setTotalid(bb);
		payr.setOperuser(username);
		payr.setId(DOCID);
		payr.setOperdate(operdate);

		boolean stat = authDistributeService.add(payr);
		Json j = new Json();
		j.setSuccess(stat);
		return j;
	}

	/**
	 * 编辑保存信息
	 * 
	 * @param Paysub
	 *            payr
	 * @param HttpSession
	 *            session
	 * @return
	 */
	@RequestMapping(params = "edit")
	@ResponseBody
	public Json edit(Paysub payr, HttpSession session, HttpServletRequest request) {
		if (request.getParameter("auth").toString().trim() != null
				&& !request.getParameter("auth").toString().trim().equals("")) {
			payr.setFinanceacc(request.getParameter("auth").toString().trim());
		} else {
			payr.setFinanceacc("");
		}
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

		String z = payr.getFinanceacc();
		String aa = "";
		String bb = "";
		if (z != null && !z.equals("")) {
			String[] zz = z.split(",");
			String AA = "";
			String BB = "";
			for (int i = 0; i < zz.length; i++) {
				String x[] = zz[i].split(";");
				AA = AA + x[0] + ",";
				BB = BB + x[1] + ",";
			}
			aa = AA.substring(0, AA.length() - 1);
			bb = BB.substring(0, BB.length() - 1);
		} else {
			aa = "";
			bb = "";
		}

		payr.setFinanceacc(aa);
		payr.setTotalid(bb);
		payr.setOperuser(username);
		payr.setOperdate(operdate);

		boolean stat = authDistributeService.edit(payr);

		Json j = new Json();
		j.setSuccess(stat);
		return j;
	}

	/**
	 * 跳转到编辑iframe页面
	 * 
	 * @param Paysub
	 *            payr
	 * @param HttpSession
	 *            session
	 * @return
	 */
	@RequestMapping(params = "toeditinfo")
	public String toeditinfo(HttpServletRequest request) {
		String authid = request.getParameter("id");
		List<Paysub> findSort = this.authDistributeService.findAuthsort();
		List<Paysub> findsubsort = this.authDistributeService.findSubauthort();
		Paysub findinfo = this.authDistributeService.findinfoByid(authid);
		request.setAttribute("findinfo", findinfo);
		request.setAttribute("sortList", findSort);
		request.setAttribute("zsortList", findsubsort);
		return "/authority/auth_editinfo";
	}

	/**
	 * 跳转到新建iframe页面
	 * 
	 * @param Paysub
	 *            payr
	 * @param HttpSession
	 *            session
	 * @return
	 */
	@RequestMapping(params = "toaddinfo")
	public String toaddinfo(HttpServletRequest request) {
		List<Paysub> findSort = this.authDistributeService.findAuthsort();
		List<Paysub> findsubsort = this.authDistributeService.findSubauthort();
		request.setAttribute("sortList", findSort);
		request.setAttribute("zsortList", findsubsort);
		return "/authority/auth_addinfo";
	}

	/**
	 * 删除信息
	 * 
	 * @param ids
	 * @author liutong
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public Json del(String ids) {
		Json j = new Json();
		boolean stat = true;
		String[] idall = ids.split(",");
		for (int i = 0; i < idall.length; i++) {
			boolean statmx = authDistributeService.del(idall[i]);
			if (statmx == false) {
				stat = false;
				logger.info("删除子项信息失败，唯一编号为：" + idall[i]);
			}
		}

		j.setSuccess(stat);
		return j;
	}
	/**
	 * 跳转到基本权限分配页面
	 * 
	 * @param HttpServletRequest
	 * @return
	 */
	@RequestMapping(params = "tobaseauthdis")
	public String tobaseauthdis(HttpServletRequest request) {
		return "/authority/auth_basicdistribute";
	}
	/**
	 * 显示基本权限分配的数据表格
	 * 
	 * @param dg
	 * @param payr
	 * @return
	 */
	@RequestMapping(params = "disbasicauthdatagrid")
	@ResponseBody
	public EasyuiDataGridJson disbasicauthdatagrid(EasyuiDataGrid dg, Paysub payr, HttpServletRequest request) {
		return authDistributeService.disbasicauthTotal(dg);
	}
	@RequestMapping(params = "toeditbaseinfo")
	public String toeditbaseinfo(HttpServletRequest request) {
		List<Paysub> findSort = this.authDistributeService.findAuthsort();
		List<Paysub> findsubsort = this.authDistributeService.findSubauthort();
		request.setAttribute("sortList", findSort);
		request.setAttribute("zsortList", findsubsort);
		return "/authority/auth_editbaseinfo";
	}
	/**
	 * 编辑保存信息
	 * 
	 * @param Paysub
	 *            payr
	 * @param HttpSession
	 *            session
	 * @return
	 */
	@RequestMapping(params = "editbase")
	@ResponseBody
	public Json editbase(Paysub payr, HttpSession session, HttpServletRequest request) {
		if (request.getParameter("auth").toString().trim() != null
				&& !request.getParameter("auth").toString().trim().equals("")) {
			payr.setFinanceacc(request.getParameter("auth").toString().trim());
		} else {
			payr.setFinanceacc("");
		}
		String sql="";
		String z = payr.getFinanceacc();
		if (z != null && !z.equals("")) {
			String[] zz = z.split(",");
			String AA = "";
			String BB = "";
			for (int i = 0; i < zz.length; i++) {
				String x[] = zz[i].split(";");
				AA = AA + x[0] + ",";
				BB = BB + x[1] + ",";
			}
			sql="update UAT_PROD0109.SYMENU set ISBASIC='basic' where id=";
			if(BB.split(",").length==1){	
				String[] authid=BB.split(",");
				sql=sql+"'"+authid[0]+"'";
			}else{
				String[] authid=BB.split(",");
				sql=sql+"'"+authid[0]+"'";
				for(int i=1;i<authid.length;i++){
				sql=sql+" "+"or id='"+authid[i]+"'";
				}
			}
		} else {
			sql="update UAT_PROD0109.SYMENU set ISBASIC='' where ISBASIC='basic'";
		}
		String sqla="update UAT_PROD0109.SYMENU set ISBASIC='' where ISBASIC='basic'";
		authDistributeService.editbasic(sqla);
		boolean end = authDistributeService.editbasic(sql);
		Json j = new Json();
		j.setSuccess(end);
		return j;
	}
}

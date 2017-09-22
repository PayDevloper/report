package com.sumao.controller;

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
import com.sumao.model.Syuser;
import com.sumao.service.AuthManageService;
import com.sumao.service.UserService;
import com.sumao.util.ExceptionUtil;
import com.sumao.util.IpUtil;
import com.sumao.util.ResourceUtil;

/**
 * 用户控制器
 * 
 * @author 陈小俊
 * 
 */
@Controller
@RequestMapping("/userController")
public class UserController extends BaseController {

	private static final Logger logger = Logger.getLogger(UserController.class);

	private UserService userService;
	@Resource
	private AuthManageService authmanageService;

	public AuthManageService getAuthmanageService() {
		return authmanageService;
	}

	@Autowired
	public void setAuthmanageService(AuthManageService authmanageService) {
		this.authmanageService = authmanageService;
	}

	public UserService getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
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
	@RequestMapping(params = "user")
	public String user() {
		return "/admin/user";
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
	 * 获得用户信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "getUserInfo")
	@ResponseBody
	public Json getUserInfo(HttpSession session) {
		Json j = new Json();
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
		if (sessionInfo != null && sessionInfo.getUser() != null) {
			Syuser u = userService.getUserInfo(sessionInfo.getUser());
			j.setObj(u);
			j.setSuccess(true);
		} else {
			j.setMsg("您没有登录或登录超时，请重新登录后重试！");
		}
		return j;
	}

	/**
	 * 编辑用户信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "editUserInfo")
	@ResponseBody
	public Json editUserInfo(Syuser user) {
		Json j = new Json();
		Syuser u = userService.editUserInfo(user);
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
	public Json reg(Syuser user) {
		Json j = new Json();
		try {
			userService.reg(user);
			j.setSuccess(true);
			j.setMsg("注册成功！");
		} catch (Exception e) {
			j.setMsg("用户名已存在！");
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}
		return j;
	}

	/**
	 * 用户登录
	 * 
	 * @param user
	 *            用户的信息
	 * @return json
	 */
	@RequestMapping(params = "login")
	@ResponseBody
	public Json login(Syuser user, HttpSession session, HttpServletRequest request) {
		Json j = new Json();
		Syuser u = userService.login(user);
		Paysub basein = new Paysub();
		if (u != null) {

			j.setSuccess(true);
			j.setMsg("登录成功!");

			u.setIp(IpUtil.getIpAddr(request));
			SessionInfo sessionInfo = new SessionInfo();
			sessionInfo.setUser(u);
			session.setAttribute(ResourceUtil.getSessionInfoName(), sessionInfo);

			basein.setSubname(u.getName());
			List<Paysub> findPosition = this.authmanageService.findposition(basein);
			session = request.getSession();
			if (findPosition.size() == 1) {
				session.setAttribute("userrole", findPosition.get(0).getSubname());
				session.setAttribute("userid", findPosition.get(0).getId());
			} else if (findPosition.size() > 1) {
				String userrole = findPosition.get(0).getSubname();
				String userid = findPosition.get(0).getId();
				for (int i = 1; i < findPosition.size(); i++) {
					userrole = userrole + "," + findPosition.get(i).getSubname();
					userid = userid + "," + findPosition.get(i).getId();
				}
				session.setAttribute("userrole", userrole);
				session.setAttribute("userid", userid);
			} else {
				session.setAttribute("userrole", "");
				session.setAttribute("userid", "");
			}
		} else {
			j.setMsg("用户名或密码错误!");
		}
		return j;
	}

	/**
	 * 用户新登录
	 * 
	 * @param user
	 *            用户的信息
	 * @return json
	 */
	@RequestMapping(params = "ulogin")
	public String ulogin(Syuser user, HttpSession session, HttpServletRequest request) {
		String Msg = "";
		Syuser u = userService.login(user);
		Paysub basein = new Paysub();
		if (u != null) {
			u.setIp(IpUtil.getIpAddr(request));
			SessionInfo sessionInfo = new SessionInfo();
			sessionInfo.setUser(u);
			session.setAttribute(ResourceUtil.getSessionInfoName(), sessionInfo);
			basein.setSubname(u.getName());
			List<Paysub> findPosition = this.authmanageService.findposition(basein);
			session = request.getSession();
			if (findPosition.size() == 1) {
				session.setAttribute("userrole", findPosition.get(0).getSubname());
				session.setAttribute("userid", findPosition.get(0).getId());
			} else if (findPosition.size() > 1) {
				String userrole = findPosition.get(0).getSubname();
				String userid = findPosition.get(0).getId();
				for (int i = 1; i < findPosition.size(); i++) {
					userrole = userrole + "," + findPosition.get(i).getSubname();
					userid = userid + "," + findPosition.get(i).getId();
				}
				session.setAttribute("userrole", userrole);
				session.setAttribute("userid", userid);
			} else {
				session.setAttribute("userrole", "");
				session.setAttribute("userid", "");
			}
			return "/admin/index";
		} else {
			Msg = "用户名或密码错误!";
			session.setAttribute("msg", Msg);

			return "/admin/loginForm";
		}

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
	public EasyuiDataGridJson loginDatagrid(EasyuiDataGrid dg, Syuser user) {
		return userService.datagrid(dg, user);
	}

	/**
	 * 获得用户列表
	 * 
	 * @param q
	 * @return
	 */
	@RequestMapping(params = "loginCombobox")
	@ResponseBody
	public List<Syuser> loginCombobox(String q) {
		return userService.combobox(q);
	}

	/**
	 * 用户表格
	 * 
	 * @param dg
	 * @param user
	 * @return
	 */
	@RequestMapping(params = "datagrid")
	@ResponseBody
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg, Syuser user) {
		return userService.datagrid(dg, user);
	}

	/**
	 * 添加用户
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(params = "add")
	@ResponseBody
	public Syuser add(Syuser user) {
		return userService.add(user);
	}

	/**
	 * 编辑用户
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(params = "edit")
	@ResponseBody
	public Syuser edit(Syuser user) {
		return userService.edit(user);
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
		userService.editUsersRole(userIds, roleId);
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
		userService.del(ids);
		j.setSuccess(true);
		return j;
	}

}

package com.sumao.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sumao.model.Paysub;
import com.sumao.service.NavigationService;

/**
 * 主页控制器
 * 
 * @author 陈小俊
 * 
 */
@Controller
@RequestMapping("/index")
public class IndexController extends BaseController{
	@Resource
	private NavigationService navigationService;
	
	public NavigationService getNavigationService() {
		return navigationService;
	}
	@Autowired
	public void setNavigationService(NavigationService navigationService) {
		this.navigationService = navigationService;
	}

	@RequestMapping(params = "north")
	public String north() {
		return "/system/layout/north";
	}

	@RequestMapping(params = "west")
	public String west(HttpServletRequest request) {
		String[] userids;   
		HttpSession session=request.getSession();
		if(((String) session.getAttribute("userid"))!=null&&!((String) session.getAttribute("userid")).equals("")){
			String userid=(String) session.getAttribute("userid");
			if(userid.contains(",")){
				userids = userid.split(",");
			}else{
				userids=new String[1];
				userids[0]=userid;
			}
		}else{
			userids=new String[1];
			userids[0]=" ";
		}		
		List<Paysub> findsubSource=this.navigationService.findSubauthort(userids);
		List<Paysub> findHigher=this.navigationService.findAuthsort();	
		request.setAttribute("sub", findsubSource);
		request.setAttribute("high", findHigher);

		return "/system/layout/newwest2";
	}

	@RequestMapping(params = "center")
	public String center() {
		return "/system/layout/center";
	}

	@RequestMapping(params = "south")
	public String south() {
		return "/system/layout/south";
	}

	/**
	 * 跳转到home页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "home")
	public String home() {
		return "/system/layout/home";
	}

}

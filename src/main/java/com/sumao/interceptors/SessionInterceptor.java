package com.sumao.interceptors;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

import org.springframework.web.servlet.ModelAndView; 
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/** 
 * 登陆拦截器 
 * 判断用户是否登陆 
 * 登陆，则不拦截，没登陆，则转到登陆界面； 
 */  
public class SessionInterceptor extends HandlerInterceptorAdapter  {
	
	  /**  
	   * 在业务处理器处理请求之前被调用  
	   * 如果返回false  
	   *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链 
       * 如果返回true  
	   *    执行下一个拦截器,直到所有的拦截器都执行完毕  
       *    再执行被拦截的Controller  
	   *    然后进入拦截器链,  
	   *    从最后一个拦截器往回执行所有的postHandle()  
	   *    接着再从最后一个拦截器往回执行所有的afterCompletion()  
	   */  	   
	   @Override    
	   public boolean preHandle(HttpServletRequest req,HttpServletResponse rsp,Object obj) throws Exception {
		   HttpSession session = req.getSession();
		   String ekaUser=(String)session.getAttribute("userrole");
		   if(ekaUser==null){
			   PrintWriter out = rsp.getWriter();  //以下可进行整体跳转   
		       out.println("<html>");      
		       out.println("<script>");      
		       out.println("window.open ('../platform','_top')");      
		       out.println("</script>");      
		       out.println("</html>");
			//   rsp.sendRedirect("../platform");     //只能iframe内跳转         
			   return false;     
			   }
		   return true;  
	   }
	   
	  /**  
	   * 在DispatcherServlet(前置控制器)完全处理完请求后被调用,可用于清理资源等   
	   *   
	   * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()  
	   */  
	   @Override   
	   public void afterCompletion(HttpServletRequest req,HttpServletResponse rsp, Object obj, Exception e)throws Exception{    
		   
	   }
	   
	   
	  /** 
	   * 在业务处理器处理请求执行完成后,生成视图之前执行的动作    
	   * 可在modelAndView中加入数据，比如当前时间 
	   */
	   @Override   
	   public void postHandle(HttpServletRequest req, HttpServletResponse rsp, Object obj, ModelAndView mode) throws Exception{    
		   
	   }
	   
	 
} 

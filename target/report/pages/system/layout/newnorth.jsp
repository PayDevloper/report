<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/public.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/sec.css" />
 	<div class="screen_top">
		<div class="wid1200 screen_topnav">
			<ul>
				<li><a href="">帮助</a></li>
				<li><a href="">关于塑贸</a></li>
				<li><a href="">卖家中心</a></li>
				<li><a href="">买家中心</a></li>
				<li><a href="">注册</a></li>
				<li><a href="">登录</a></li>
				<li>交易时间09:00-17:00</li>
				<li>你好，欢迎来到塑贸网！</li>
			</ul>
			<div class="screen_location f_l">
				<h3>
					卖家中心<a href="<%=basePath%>quantityTops.do?topToday">返回首页</a>
				</h3>
			</div>
		</div>
	</div>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.sumao.model.SessionInfo"%>
<%@page import="com.sumao.util.ResourceUtil"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String username="";
	SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
	if (sessionInfo != null && sessionInfo.getUser() != null) {
		username=sessionInfo.getUser().getName();
	} else {
		username="admin";
	}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>塑贸网</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/public.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/sec.css" />
<script src="<%=basePath%>/js/My97DatePicker/WdatePicker.js" charset="UTF-8" type="text/javascript"></script>
	<script type="text/javascript">
	function transDate() {
		var start = document.getElementById("starttime").value;
		var end = document.getElementById("endtime").value;
		if (start != "" && end != "") {
			window.location="<%=basePath%>quantityTops.do?topCustom"+"&start="+start+"&end="+end;
		} else {
			alert("日期不能为空");
		}
	}
</script>
</head>
<body>
	<!--top start-->
	<div class="screen_top">
		<div class="wid1200 screen_topnav">
			<ul>
				<li><a href="">帮助</a></li>
				<li><a href="">关于塑贸</a></li>
				<li><a href="">卖家中心</a></li>
				<li><a href="">买家中心</a></li>
				<li><a href="">注册</a></li>
				<li><a href="/platform/">登录</a></li>
				<li><%=username %> 你好，欢迎来到塑贸网！</li>
			</ul>
		</div>
	</div>
	<div class="wid1200" style="overflow: hidden">
		<jsp:include page="../system/layout/newwest.jsp"></jsp:include>
		<div class="f_r seller_right">
			<iframe id="iframe2" border="0" name="iframe2" src="/platform/paySubController.do?Payuserorg"
			 frameBorder="0" scrolling="auto" width="100%" height="500"></iframe>
		</div>
	</div>

	</br>
	</br>
	</br>
	<!--bottom start-->
	<jsp:include page="../system/layout/south.jsp"></jsp:include>
	<!--bottom end-->
</body>
</html>

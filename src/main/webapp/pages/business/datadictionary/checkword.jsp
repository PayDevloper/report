<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.sumao.model.Page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>塑贸网-最专业的石化交易及数据服务提供商</title>
<jsp:include page="../../../common/meta.jsp"></jsp:include>
<jsp:include page="../../../common/easyui.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/css/public.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/stye.css"></link>
<link rel=stylesheet type="text/css"
	href="<%=basePath%>/css/tech_table.css" />
<link rel=stylesheet type="text/css"
	href="<%=basePath%>/css/tech_tableright.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/sec.css" />
<script src="<%=basePath%>/js/My97DatePicker/WdatePicker.js"
	charset="UTF-8" type="text/javascript"></script>
<script type="text/javascript">
	<!--判断是查询还是导出and传入的参数是是按钮的value-->
	function expExcel(func){
		var tableinfo="销售地区";
		var form = document.forms[0];
		if(func.valueOf() == "查询"){
			var start = document.getElementById("col3").value;
		if (start != "") {
			form.action = "<%=basePath%>dataDictionary.do?toDataDicList"+"&searchkey="+start;
			form.method="post";
			form.submit();
		} else{
			alert("条件不能为空");
		}
		}else{
			form.action = "<%=basePath%>dataDictionary.do?toDataDicList"
			form.method = "post";
			form.submit();
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
	<div class="wid1200 seller_contain">
		<jsp:include page="../../system/layout/newwest.jsp"></jsp:include>
		<div class="f_r seller_right">
			<div class="second_contain">
				<img src="images/home_news.png" width="14" height="14" /> <a
					href="">首页</a><i>></i><a href="">卖家中心</a><i>></i><span>数据字典维护</span>
			</div>
			<div class="ysh mar_t20">
				<table cellpadding="0" cellspacing="0" border="0" class="ysh_stab"
					style="border-bottom: none">
					<tr>
						<td colspan="6">
							<div class="float_r" style="margin-right: 20px;">
								<input type="button" id="addnew" name="addnew" class="blubtn"
									value="返回" onclick="javascript:history.back(-1)" />
							</div>
						</td>
					</tr>
				</table>
			</div>
			<div class="second_contain_list"
				style="overflow: scroll; margin: 15px; height: 500px;">
				<form action="" id="Form" name="Form">
					<table cellpadding="0" cellspacing="0" border="0"
						class="xhdd2tab11">
						<tr>
							<th>类型</th>
							<td><input id="col1" class="hidden_input" type="text"
								value="${details.col2}" name="col1"
								onblur="checkNull('col1','btSpan')"> <span id="btSpan"
								class="alert_span"></span></td>
							<th>展示名称</th>
							<td><input id="col2" class="hidden_input" type="text"
								value="${details.col3}" name="col2"
								onblur="checkNull('col2','wzSpan')"> <span id="wzSpan"
								class="alert_span"></span></td>
						</tr>
						<tr>
							<th>字段编号</th>
							<td><input id="col3" class="hidden_input" type="text"
								value="${details.col1}" name="col3"
								onblur="checkNull('col3','fbrSpan')"> <span id="fbrSpan"
								class="alert_span"></span></td>
							<th>字段名称</th>
							<td><input id="col4" class="hidden_input" type="text"
								value="${details.col4}" name="col4"
								onblur="checkNull('col4','dzSpan')"> <span id="dzSpan"
								class="alert_span"></span></td>
						</tr>
						<tr>
							<th>编号</th>
							<td><input id="col5" class="hidden_input" type="text"
								value="${details.col5}" name="col5"
								onblur="checkNull('col5','fbrqSpan')" readonly="readonly">
								<span id="fbrqSpan" class="alert_span"></span></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>

	<br />
	<br />
	<!--bottom start-->
	<jsp:include page="../../system/layout/south.jsp"></jsp:include>
	<!--bottom end-->
</body>
</html>

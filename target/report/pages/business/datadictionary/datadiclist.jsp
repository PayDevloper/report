<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.sumao.model.Page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Page pagenew = (Page) request.getAttribute("page");
	String Page = Integer.toString(pagenew.getPageNow()); //当前页
	int cont = pagenew.getTotalPageCount();
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
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/sec.css" />
<script src="<%=basePath%>/js/My97DatePicker/WdatePicker.js"
	charset="UTF-8" type="text/javascript"></script>
<script type="text/javascript">
	<!--下一页的功能-->
	function next()
	{
		window.location="<%=basePath%>dataDictionary.do?toDataDicList"+"&pageNow="+<%=Integer.parseInt(Page) + 1%>;
	}
	<!--上一页的功能-->
	function pre()
	{
		window.location="<%=basePath%>dataDictionary.do?toDataDicList"+"&pageNow="+<%=Integer.parseInt(Page) - 1%>;
	}
	<!--点击数字的分页,参数p是输入的页数-->
	function fenye(p){
		window.location="<%=basePath%>dataDictionary.do?toDataDicList"+"&pageNow="+p;
	}
	<!--填写页数的分页-->
	function chang()
	{
		var p =document.getElementById("text_shr").value;
		var conter = "<%=cont%>";
	    var conters = conter/1;
	    var pager = p/1;
		if(pager>conters){
		  p=conters;
		}
		window.location="<%=basePath%>dataDictionary.do?toDataDicList"
				+ "&pageNow=" + p;
	}
	<!--验证填写的是否是数字-->
	function yanzheng() {
		var v = document.getElementById("text_shr").value;
		if (v.length != 0 && !isNaN(v)) {
			chang();
		}
	}
	function selectall(){
		  var all=document.getElementById("all");
		  var cbox=document.getElementsByName("strArray");
		  if(all.checked==true){
		    for(i=0;i<cbox.length;i++){
		    if(!cbox[i].checked==true)cbox[i].checked=true;
		    }
		    }else{
		       for(i=0;i<cbox.length;i++){
		       if(cbox[i].checked==true)cbox[i].checked=false;
		       }
		  }
		}
	 function selElem(){       
	        var cbox = document.getElementsByName("strArray");
	        if (cbox.length == 0) {
		alert('不可以进行空操作');
		return;
	}
	for (i = 0; i < cbox.length; i++) {
		if (cbox[i].checked == true) {
			break;
		}
		if (i == (cbox.length - 1)) {
			alert("至少选中一项才可进行该操作");
			return false;
		}
	}
	if (confirm('确实要作废选中的记录吗?')) {
		var themeid = "";
		for (i = 0; i < cbox.length; i++) {
			if (cbox[i].checked == true) {
				themeid = cbox[i].value;
          $.get("<%=basePath%>dataDictionary.do?delWord"+"&wordid="+themeid,
         		 function(data){
		    	if("success" == data.result){		
				window.location.reload();
			      }else{
			
			}
		});
			}
		}
}
	}
	<!--判断是查询还是导出and传入的参数是是按钮的value-->
	function expExcel(func){
		var tableinfo="销售地区";
		var form = document.forms[0];
		if(func.valueOf() == "查询"){
			var start = document.getElementById("col1").value;
		if (start != "") {
			form.action = "<%=basePath%>dataDictionary.do?toDataDicList"
			form.method="post";
			form.submit();
		} else{
			alert("条件不能为空");
		}
		}else{
			form.action = "<%=basePath%>dataDictionary.do?toNewword"
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
				<form action="" id="Form" name="Form">
					<table cellpadding="0" cellspacing="0" border="0" class="ysh_stab"
						style="border-bottom: none">
						<tr>
							<th>关键字：</th>
							<td><input type="text" class="in_text" id="col1" name="col1" /></td>
						</tr>
						<tr>
							<td colspan="6">
								<div class="float_r" style="margin-right: 20px;">
									<input type="button" id="search" name="search" class="blubtn"
										value="查询" onclick="expExcel(this.value)" /> <input
										type="button" id="addnew" name="addnew" class="grebtn"
										value="新建" onclick="expExcel(this.value)" />
								</div>
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div class="second_contain_list"
				style="overflow: scroll; margin: 15px; height: 500px;">
				<table cellpadding="0" cellspacing="0" border="0" class="xhdd2tab11">
					<tr>
						<th>类型</th>
						<th>展示名称</th>
						<th>数据库编号</th>
						<th>字段名称</th>
						<th>操作</th>
					</tr>
					<c:if test="${!empty details }">
						<c:forEach items="${details}" var="details">
							<tr>
								<td><input type="checkbox" value="${details.col5}"
									name="strArray" id="${details.col5}"> ${details.col2}</td>
								<td>${details.col3}</td>
								<td>${details.col1}</td>
								<td>${details.col4}</td>
								<td style="text-align: center"><a title="编辑" id="Editword"
									href="<%=basePath%>dataDictionary.do?toEditword&searchkey=${details.col5}">
										<img src="images/hh_per_c_06.gif">
								</a> <a title="查看"
									href="<%=basePath%>dataDictionary.do?toCheckword&searchkey=${details.col5}">
										<img src="images/hh_per_c_04.gif">
								</a></td>
							</tr>
						</c:forEach>
					</c:if>
					<tr>
						<td style="height: 30px;" colspan="8"><input id="all"
							type="checkbox" onclick="selectall()" name="all"
							style="border: 0px;">
							<div class="float_r" style="margin-right: 20px;">
								<input class="grebtn" type="button" onclick="selElem()"
									value="作废">
							</div></td>
					</tr>
				</table>
			</div>
			<!-- 分页功能 start -->
			<div id="fanye">
				<%
	if(Integer.parseInt(Page)<=1){%>
				<input type="button" class="fanye_input_btnnext" value="&lt;&lt;上一页" />
				<%}else{%>
				<input type="button" class="fanye_input_btnnext" onclick="pre()"
					value="&lt;&lt;上一页" />
				<%}
	int n = Integer.parseInt(Page)/6;
	int m=0;
	if(Integer.parseInt(Page)%6==0){
      m = (n-1)*6+1;
	}else{
	  m= n*6+1;
	}
	int k=0;
	if(cont-m>=6){
	    k=5;
	}else{
	    k=cont-m;
	}
	for(int p=m;p<=m+k;p++){
	   if(Integer.parseInt(Page)==p){
	%>
				<input type="button" class="fanye_input_btnnum fanye_input_mar"
					value="<%=p %>" onclick="fenye('<%=p %>')" />
				<%}else{ %>
				<input type="button" class="fanye_input_btnnum1 fanye_input_mar"
					value="<%=p %>" onclick="fenye('<%=p %>')" />
				<%} } %>
				<%if(Integer.parseInt(Page)>=cont){ %>
				<input type="button" class="fanye_input_btnnext" value="下一页&gt;&gt;" />
				<%}else{ %>
				<input type="button" class="fanye_input_btnnext" onclick="next()"
					value="下一页&gt;&gt;" />
				<%} %>
				共<%=cont %>页， 到第<input id="text_shr" name="text_shr"
					class="fanye_input_btntext" value="<%=Page %>" />页<input
					type="button" onclick="yanzheng()" class="fanye_input_btn"
					value="确定" />
			</div>

			<!-- 分页功能 End -->
		</div>
	</div>

	<br />
	<br />
	<!--bottom start-->
	<jsp:include page="../../system/layout/south.jsp"></jsp:include>
	<!--bottom end-->
</body>
</html>

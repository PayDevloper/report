<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.sumao.model.Page"%>
<%@ page import="com.sumao.model.Pay_rightgoldcheck"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Page pagenew = (Page) request.getAttribute("page");
	String Page = Integer.toString(pagenew.getPageNow()); //当前页
	int cont = pagenew.getTotalPageCount();
	Pay_rightgoldcheck payr= (Pay_rightgoldcheck) request.getAttribute("payr");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>权利金缴费查看</title>
<link rel="stylesheet" type="text/css" href="css/public.css" />
<link rel="stylesheet" type="text/css" href="css/sec.css" />
<script src="<%=basePath%>/js/My97DatePicker/WdatePicker.js"
	charset="UTF-8" type="text/javascript"></script>
<script type="text/javascript">
     //下一页的功能
	function next()
	{
		var pmarketingid = document.getElementById("pmarketingid").value;
		var subitemname = $("#subitemname").find("option:selected").text();
		if (subitemname=='全部')
		{
			subitemname='';
		}
		var payment = $("#payment").find("option:selected").text();
		if (payment=='全部')
		{
			payment='';
		}
		var startdate = document.getElementById("startdate").value;
		var enddate = document.getElementById("enddate").value;
		
		var url="<%=basePath%>payrightController.do?rightoldSeller&pageNow=<%=Integer.parseInt(Page) + 1%>&orgnid="
				+pmarketingid+"&subitemname="+subitemname+"&payment="+payment+"&startdate="+startdate+"&enddate="+enddate;
		
		changeReport(url)
	}
	//上一页的功能
	function pre()
	{
		var pmarketingid = document.getElementById("pmarketingid").value;
		var subitemname = $("#subitemname").find("option:selected").text();
		if (subitemname=='全部')
		{
			subitemname='';
		}
		var payment = $("#payment").find("option:selected").text();
		if (payment=='全部')
		{
			payment='';
		}
		var startdate = document.getElementById("startdate").value;
		var enddate = document.getElementById("enddate").value;
		
		var url="<%=basePath%>payrightController.do?rightoldSeller&pageNow=<%=Integer.parseInt(Page) - 1%>&orgnid="
				+pmarketingid+"&subitemname="+subitemname+"&payment="+payment+"&startdate="+startdate+"&enddate="+enddate;
		
		changeReport(url)
	}
	//点击数字的分页,参数p是输入的页数
	function fenye(p){
		var pmarketingid = document.getElementById("pmarketingid").value;
		var subitemname = $("#subitemname").find("option:selected").text();
		if (subitemname=='全部')
		{
			subitemname='';
		}
		var payment = $("#payment").find("option:selected").text();
		if (payment=='全部')
		{
			payment='';
		}
		var startdate = document.getElementById("startdate").value;
		var enddate = document.getElementById("enddate").value;
		
		var url="<%=basePath%>payrightController.do?rightoldSeller&pageNow="+p+"&orgnid="
				+pmarketingid+"&subitemname="+subitemname+"&payment="+payment+"&startdate="+startdate+"&enddate="+enddate;
			
		changeReport(url)
	}
	//填写页数的分页
	function chang()
	{
		var p =document.getElementById("text_shr").value;
		var conter = "<%=cont%>";
	    var conters = conter/1;
	    var pager = p/1;
		if(pager>conters){
		  p=conters;
		}
		var pmarketingid = document.getElementById("pmarketingid").value;
		var subitemname = $("#subitemname").find("option:selected").text();
		if (subitemname=='全部')
		{
			subitemname='';
		}
		var payment = $("#payment").find("option:selected").text();
		if (payment=='全部')
		{
			payment='';
		}
		var startdate = document.getElementById("startdate").value;
		var enddate = document.getElementById("enddate").value;
		
		var url="<%=basePath%>payrightController.do?rightoldSeller&pageNow="+p+"&orgnid="
				+pmarketingid+"&subitemname="+subitemname+"&payment="+payment+"&startdate="+startdate+"&enddate="+enddate;
		
		changeReport(url)
	}
	//验证填写的是否是数字
	function yanzheng(){
	   var v = document.getElementById("text_shr").value;
	   if(v.length!=0 && !isNaN(v)){
	      chang();
	   }
	}
	//判断是查询还是导出and传入的参数是是按钮的value
	function expExcel(func){
		var tableinfo="权利金缴费查看";
		var form = document.forms[0];
		if(func.valueOf() == "查询"){
			var pmarketingid = document.getElementById("pmarketingid").value;
			var subitemname = $("#subitemname").find("option:selected").text();
			if (subitemname=='全部')
			{
				subitemname='';
			}
			var payment = $("#payment").find("option:selected").text();
			if (payment=='全部')
			{
				payment='';
			}
			var startdate = document.getElementById("startdate").value;
			var enddate = document.getElementById("enddate").value;
			
			var url="<%=basePath%>payrightController.do?rightoldSeller&orgnid="
					+pmarketingid+"&subitemname="+subitemname+"&payment="+payment+"&startdate="+startdate+"&enddate="+enddate;
			
			//window.location=url;
			changeReport(url);
			
		}else{
			/*
			var pmarketingid = document.getElementById("pmarketingid").value;
			var subitemname = $("#subitemname").find("option:selected").text();	
			var payment = $("#payment").find("option:selected").text();
			var startdate = document.getElementById("startdate").value;
			var enddate = document.getElementById("enddate").value;
				
			form.action = "<%=basePath%>ExportTable.do?exportExcel"
					+ "&tableinfo=" + tableinfo+"&odtype="+odtype+"&odstate="+odstate+"&odcheck="+odcheck+"&enpname="+enpname
					+"&orderid="+orderid+"&prod="+prod+"&orgId="+orgId
					+"&end="+end+"&start="+start;
			form.method = "post";
			form.submit();
			*/
		}
	}
</script>
</head>

<body>
<div class="ysh mar_t20">
	<form  id="Form" name="Form" method="post" >
	  <div style="display:none">
	     <input id="pmarketingid" name="pmarketingid" value="<%=payr.getMarketingid()%>" />
	  </div>
      <table cellpadding="0" cellspacing="0" border="0" class="ysh_stab" style=" border-bottom:none">
        <tr>
						<th>权利金：</th>
						<td><select id="subitemname" name="subitemname" style="width:150px;">
						<option value="">全部</option>
									<c:if test="${!empty subitemname }">
									<c:forEach items="${subitemname}" var="subitemdetails">
									<option value="${subitemdetails.subitemname}">${subitemdetails.subitemname}</option>		
									</c:forEach></c:if>
						</select></td>
          <th>生效日期：</th><td><span class="display_inb"><input type="text" class="timet" id="startdate" name="startdate" value="" /><input type="button" class="timeb" value="" onClick="WdatePicker({el:'startdate',dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d %H:%m:%s'})" /></span><b>至</b><span class="display_inb"><input type="text" id="enddate" name="enddate" class="timet" value="" /><input type="button" class="timeb" value="" onClick="WdatePicker({el:'enddate',dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d %H:%m:%s'})" /></span></td>
         <th>支付状态：</th>
						<td><select id="payment" name="payment" style="width:150px;">
						<option value="">全部</option>
						<option value="已支付">已支付</option>
						<option value="未支付">未支付</option>
						</select>
						</td>
						<td>
            <div class="float_r" style="margin-right:20px;">
            <input type="button" class="blubtn" onclick="expExcel(this.value)" value="查询" /></div>								
          </td>
        </tr>
      </table>
    </form>
    </div>
    <div class="second_contain_list" style="overflow: scroll; margin: 15px; height: 500px;">
    <table cellpadding="0" cellspacing="0" border="0" class="xhdd2tab11">
					<tr>
						<th>缴费记录号</th>
						<th>权利金总项</th>
						<th>权利金子项</th>
						<th>生效开始日期</th>
						<th>生效截止日期</th>
						<th>当前状态</th>
						<th>缴费金额</th>
						<th>支付状态</th>
						<th>实交金额</th>
						<th>支付方式</th>
						<th>支付日期</th>
					</tr>
					<c:if test="${!empty details }">
						<c:forEach items="${details}" var="details">
							<tr>
								<td>${details.id}</td>
								<td>${details.totalname}</td>
								<td>${details.subitemname}</td>
								<td>${details.startdate}</td>
								<td>${details.enddate}</td>
								<td>${details.currentstate}</td>
								<td>${details.payamount}</td>
								<td>${details.payment}</td>
								<td>${details.actualpay}</td>
								<td>${details.paymentmethod}</td>
								<td>${details.paytime}</td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
				
				<!-- 分页功能 start -->
			<div class="pagenext"><a href="#" onclick="fenye('1')" class="up">&lt;&lt;首页</a>
				<%
					if (Integer.parseInt(Page) <= 1) {
				%>
				<a href="#" class="up mr10">上一页</a>
				<%
					} else {
				%>
				<a href="#" onclick="pre()" class="up mr10">上一页</a>
				<%
					}
					int n = Integer.parseInt(Page) / 6;
					int m = 0;
					if (Integer.parseInt(Page) % 6 == 0) {
						m = (n - 1) * 6 + 1;
					} else {
						m = n * 6 + 1;
					}
					int k = 0;
					if (cont - m >= 6) {
						k = 5;
					} else {
						k = cont - m;
					}
					for (int p = m; p <= m + k; p++) {
						if (Integer.parseInt(Page) == p) {
				%>
					<a href="#" onclick="fenye('<%=p%>')" class="pnum on"><%=p%></a>
				<%
					} else {
				%>
					<a href="#" onclick="fenye('<%=p%>')" class="pnum"><%=p%></a>
				<%
					}
					}
				%>
				<%
					if (Integer.parseInt(Page) >= cont) {
				%>
				<a href="#" class="down ml10">下一页</a>
				<%
					} else {
				%>
				<a href="#" onclick="next()" class="down ml10">下一页</a>
				<%
					}
				%>
				<a href="#" onclick="fenye('<%=cont%>')" class="down">尾页&gt;&gt;</a><span>共<%=cont%>页</span>
			</div>
			<!-- 分页功能 End -->
	</div>
</body>
</html>
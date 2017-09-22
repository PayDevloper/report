<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.sumao.model.Page"%>
<%@ page import="com.sumao.model.Paybond"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Page pagenew = (Page) request.getAttribute("page");
	String Page = Integer.toString(pagenew.getPageNow()); //当前页
	int cont = pagenew.getTotalPageCount();
	Paybond payr= (Paybond) request.getAttribute("payr");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>费用明细</title>
<link rel="stylesheet" type="text/css" href="css/public.css" />
<link rel="stylesheet" type="text/css" href="css/sec.css" />
<script src="<%=basePath%>/js/My97DatePicker/WdatePicker.js"
	charset="UTF-8" type="text/javascript"></script>
<script type="text/javascript">
     //下一页的功能
	function next()
	{
		var marketingid = document.getElementById("marketingid").value;  //销售组织ID
		var remark2 = $("#remark2").find("option:selected").text();    //发生类型
		if (remark2=='全部')
		{
			remark2='';
		}
		
		var createtime = document.getElementById("createtime").value;  //发生日期开始
		var remark = document.getElementById("remark").value;          //发生日期结束
		
		var url="<%=basePath%>paybondController.do?costpaySeller&pageNow=<%=Integer.parseInt(Page) + 1%>&orgnid="
				+marketingid+"&remark2="+remark2+"&createtime="+createtime+"&remark="+remark;
		
		changeReport(url)
	}
	//上一页的功能
	function pre()
	{
		var marketingid = document.getElementById("marketingid").value;  //销售组织ID
		var remark2 = $("#remark2").find("option:selected").text();    //发生类型
		if (remark2=='全部')
		{
			remark2='';
		}
		
		var createtime = document.getElementById("createtime").value;  //发生日期开始
		var remark = document.getElementById("remark").value;          //发生日期结束
		
		var url="<%=basePath%>paybondController.do?costpaySeller&pageNow=<%=Integer.parseInt(Page) - 1%>&orgnid="
				+marketingid+"&remark2="+remark2+"&createtime="+createtime+"&remark="+remark;
		
		changeReport(url)
	}
	//点击数字的分页,参数p是输入的页数
	function fenye(p){
		var marketingid = document.getElementById("marketingid").value;  //销售组织ID
		var remark2 = $("#remark2").find("option:selected").text();    //发生类型
		if (remark2=='全部')
		{
			remark2='';
		}
		
		var createtime = document.getElementById("createtime").value;  //发生日期开始
		var remark = document.getElementById("remark").value;          //发生日期结束
		
		var url="<%=basePath%>paybondController.do?costpaySeller&pageNow="+p+"&orgnid="
				+marketingid+"&remark2="+remark2+"&createtime="+createtime+"&remark="+remark;
		
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
		
		var marketingid = document.getElementById("marketingid").value;  //销售组织ID
		var remark2 = $("#remark2").find("option:selected").text();    //发生类型
		if (remark2=='全部')
		{
			remark2='';
		}
		
		var createtime = document.getElementById("createtime").value;  //发生日期开始
		var remark = document.getElementById("remark").value;          //发生日期结束
		
		var url="<%=basePath%>paybondController.do?costpaySeller&pageNow="+p+"&orgnid="
				+marketingid+"&remark2="+remark2+"&createtime="+createtime+"&remark="+remark;
		
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
			var marketingid = document.getElementById("marketingid").value;  //销售组织ID
			var remark2 = $("#remark2").find("option:selected").text();    //发生类型
			if (remark2=='全部')
			{
				remark2='';
			}
			
			var createtime = document.getElementById("createtime").value;  //发生日期开始
			var remark = document.getElementById("remark").value;          //发生日期结束
			
			var url="<%=basePath%>paybondController.do?costpaySeller&orgnid="
					+marketingid+"&remark2="+remark2+"&createtime="+createtime+"&remark="+remark;
			
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
	     <input id="marketingid" name="marketingid" value="<%=payr.getMarketingid()%>" />
	  </div>
      <table cellpadding="0" cellspacing="0" border="0" class="ysh_stab" style=" border-bottom:none">
        <tr>
						<th>发生类型：</th>
						<td><select id="remark2" name="remark2" style="width:150px;">
						<option value="">全部</option>
						<option value="扣减服务费">扣减服务费</option>
						<option value="支付保证金">支付保证金</option>	
						</select></td>
          <th>发生日期：</th><td><span class="display_inb"><input type="text" class="timet" id="createtime" name="createtime" value="" /><input type="button" class="timeb" value="" onClick="WdatePicker({el:'createtime',dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d %H:%m:%s'})" /></span><b>至</b><span class="display_inb"><input type="text" id="remark" name="remark" class="timet" value="" /><input type="button" class="timeb" value="" onClick="WdatePicker({el:'remark',dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d %H:%m:%s'})" /></span></td>
         <th>订单号：</th><td><input id="codeid" name="codeid" style="width: 150px;" /></td>
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
						<th>发生日期</th>
						<th>发生金额</th>
						<th>当前余额</th>
						<th>发生类型</th>
						<th>订单号</th>
						<th>是否预警</th>
					</tr>
				
					<c:if test="${!empty details }">
						<c:forEach items="${details}" var="details">
							<tr>
								<td>${details.createtime}</td>
								<td>${details.remark1}</td>
								<td>${details.havedeposit}</td>
								<td>${details.remark2}</td>
								<td>${details.codeid}</td>
								<td>${details.whetheralerted}</td>
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
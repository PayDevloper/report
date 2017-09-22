<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.sumao.model.Page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath ="http://172.22.16.14:7003/report/";
	Page pagenew = (Page) request.getAttribute("page");
	String Page = Integer.toString(pagenew.getPageNow()); //当前页
	int cont = pagenew.getTotalPageCount();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>塑贸网-最专业的石化交易及数据服务提供商</title>
<script src="<%=basePath%>/js/My97DatePicker/WdatePicker.js"
	charset="UTF-8" type="text/javascript"></script>
<script type="text/javascript">
$(function() {
	$("#orgName").unbind('change').change(function(){
		var orgId=$("#orgName").val();
		if(orgId.length == 0){
			var allgroupids = "";
			var obj=document.getElementById("orgName");
			for(var i = 0; i < obj.options.length; i++){
	            allgroupids =allgroupids+ obj.options[i].value+",";
	        }
			orgId = allgroupids
		}
		var odtype  = $("#col1").find("option:selected").text();
		var odstate =  $("#col2").find("option:selected").text();
		var odcheck = $("#col3").find("option:selected").text();
		var enpname = document.getElementById("col4").value;
		var orderid = document.getElementById("col5").value;	
		var prod = document.getElementById("col6").value;
		var start = document.getElementById("col7").value;
		var end = document.getElementById("col8").value;
		var warehouse = document.getElementById("col11").value;
		$.ajax({
			url:'http://172.22.16.14:7003/report/orderDetail.do?toOrderDetails&code=6c4eda51-9b2d-243d-2aa9-20245a7765e2&orgnid='+orgId
					+'&r='+Math.random()+"&odtype="+odtype+"&odstate="+odstate+"&odcheck="+odcheck+"&enpname="+enpname
					+"&orderid="+orderid+"&prod="+prod+"&warehouse="+warehouse
					+"&end="+end+"&start="+start,
			dataType:'html',
			success:function(html){
				$("#extDiv").html(html);
			}
		});
	});
});	

     //下一页的功能
	function next()
	{
		var orgId=$("#orgName").val();
		var allgroupids="";
		var obj=document.getElementById("orgName");
		for(var i = 0; i < obj.options.length; i++){
            allgroupids =allgroupids+ obj.options[i].value+",";
            }
		if(orgId.length == 0){
		var odtype  = $("#col1").find("option:selected").text();
		var odstate =  $("#col2").find("option:selected").text();
		var odcheck = $("#col3").find("option:selected").text();
		var enpname = document.getElementById("col4").value;
		var orderid = document.getElementById("col5").value;	
		var prod = document.getElementById("col6").value;
		var start = document.getElementById("col7").value;
		var end = document.getElementById("col8").value;	
		var warehouse = document.getElementById("col11").value;
		var url="<%=basePath%>orderDetail.do?toOrderDetails"+"&pageNow="+<%=Integer.parseInt(Page) + 1%>
		+"&odtype="+odtype+"&odstate="+odstate+"&odcheck="+odcheck+"&enpname="+enpname
		+"&orderid="+orderid+"&prod="+prod+"&orgnid="+allgroupids+"&warehouse="+warehouse
		+"&end="+end+"&start="+start;
		changeReport(url);
		}else{
			var odtype  = $("#col1").find("option:selected").text();
			var odstate =  $("#col2").find("option:selected").text();
			var odcheck = $("#col3").find("option:selected").text();
			var enpname = document.getElementById("col4").value;
			var orderid = document.getElementById("col5").value;	
			var prod = document.getElementById("col6").value;
			var start = document.getElementById("col7").value;
			var end = document.getElementById("col8").value;	
			var warehouse = document.getElementById("col11").value;
			var url="<%=basePath%>orderDetail.do?toOrderDetails"+"&pageNow="+<%=Integer.parseInt(Page) + 1%>
			+"&odtype="+odtype+"&odstate="+odstate+"&odcheck="+odcheck+"&enpname="+enpname
			+"&orderid="+orderid+"&prod="+prod+"&orgnid="+orgId+"&warehouse="+warehouse
			+"&end="+end+"&start="+start;	
		changeReport(url);
		}
	}
	//上一页的功能
	function pre()
	{
		var orgId=$("#orgName").val();
		var allgroupids="";
		var obj=document.getElementById("orgName");
		for(var i = 0; i < obj.options.length; i++){
            allgroupids =allgroupids+ obj.options[i].value+",";
            }
		if(orgId.length == 0){
		var odtype  = $("#col1").find("option:selected").text();
		var odstate =  $("#col2").find("option:selected").text();
		var odcheck = $("#col3").find("option:selected").text();
		var enpname = document.getElementById("col4").value;
		var orderid = document.getElementById("col5").value;	
		var prod = document.getElementById("col6").value;
		var start = document.getElementById("col7").value;
		var end = document.getElementById("col8").value;	
		var warehouse = document.getElementById("col11").value;
		var url="<%=basePath%>orderDetail.do?toOrderDetails"+"&pageNow="+<%=Integer.parseInt(Page) - 1%>
		+"&odtype="+odtype+"&odstate="+odstate+"&odcheck="+odcheck+"&enpname="+enpname
		+"&orderid="+orderid+"&prod="+prod+"&orgnid="+allgroupids+"&warehouse="+warehouse
		+"&end="+end+"&start="+start;
		changeReport(url);
		}else{
			var odtype  = $("#col1").find("option:selected").text();
			var odstate =  $("#col2").find("option:selected").text();
			var odcheck = $("#col3").find("option:selected").text();
			var enpname = document.getElementById("col4").value;
			var orderid = document.getElementById("col5").value;	
			var prod = document.getElementById("col6").value;
			var start = document.getElementById("col7").value;
			var end = document.getElementById("col8").value;	
			var warehouse = document.getElementById("col11").value;
			var url="<%=basePath%>orderDetail.do?toOrderDetails"+"&pageNow="+<%=Integer.parseInt(Page) - 1%>
			+"&odtype="+odtype+"&odstate="+odstate+"&odcheck="+odcheck+"&enpname="+enpname
			+"&orderid="+orderid+"&prod="+prod+"&orgnid="+orgId+"&warehouse="+warehouse
			+"&end="+end+"&start="+start;
		changeReport(url);
		}
	}
	//点击数字的分页,参数p是输入的页数
	function fenye(p){
		var orgId=$("#orgName").val();
		var allgroupids="";
		var obj=document.getElementById("orgName");
		for(var i = 0; i < obj.options.length; i++){
            allgroupids =allgroupids+ obj.options[i].value+",";
            }
		if(orgId.length == 0){
		var odtype  = $("#col1").find("option:selected").text();
		var odstate =  $("#col2").find("option:selected").text();
		var odcheck = $("#col3").find("option:selected").text();
		var enpname = document.getElementById("col4").value;
		var orderid = document.getElementById("col5").value;	
		var prod = document.getElementById("col6").value;
		var start = document.getElementById("col7").value;
		var end = document.getElementById("col8").value;	
		var warehouse = document.getElementById("col11").value;
		var url="<%=basePath%>orderDetail.do?toOrderDetails"+"&pageNow="+p
		+"&odtype="+odtype+"&odstate="+odstate+"&odcheck="+odcheck+"&enpname="+enpname
		+"&orderid="+orderid+"&prod="+prod+"&orgnid="+allgroupids+"&warehouse="+warehouse
		+"&end="+end+"&start="+start;
		changeReport(url);
		}else{
			var odtype  = $("#col1").find("option:selected").text();
			var odstate =  $("#col2").find("option:selected").text();
			var odcheck = $("#col3").find("option:selected").text();
			var enpname = document.getElementById("col4").value;
			var orderid = document.getElementById("col5").value;	
			var prod = document.getElementById("col6").value;
			var start = document.getElementById("col7").value;
			var end = document.getElementById("col8").value;	
			var warehouse = document.getElementById("col11").value;
			var url="<%=basePath%>orderDetail.do?toOrderDetails"+"&pageNow="+p
			+"&odtype="+odtype+"&odstate="+odstate+"&odcheck="+odcheck+"&enpname="+enpname
			+"&orderid="+orderid+"&prod="+prod+"&orgnid="+orgId+"&warehouse="+warehouse
			+"&end="+end+"&start="+start;
		changeReport(url);
		}
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
		var orgId=$("#orgName").val();
		var allgroupids="";
		var obj=document.getElementById("orgName");
		for(var i = 0; i < obj.options.length; i++){
            allgroupids =allgroupids+ obj.options[i].value+",";
            }
		if(orgId.length == 0){
		var odtype  = $("#col1").find("option:selected").text();
		var odstate =  $("#col2").find("option:selected").text();
		var odcheck = $("#col3").find("option:selected").text();
		var enpname = document.getElementById("col4").value;
		var orderid = document.getElementById("col5").value;	
		var prod = document.getElementById("col6").value;
		var start = document.getElementById("col7").value;
		var end = document.getElementById("col8").value;	
		var warehouse = document.getElementById("col11").value;
		var url="<%=basePath%>orderDetail.do?toOrderDetails"+"&pageNow="+p
		+"&odtype="+odtype+"&odstate="+odstate+"&odcheck="+odcheck+"&enpname="+enpname
		+"&orderid="+orderid+"&prod="+prod+"&orgnid="+allgroupids+"&warehouse="+warehouse
		+"&end="+end+"&start="+start;
		changeReport(url);
		}else{
			var odtype  = $("#col1").find("option:selected").text();
			var odstate =  $("#col2").find("option:selected").text();
			var odcheck = $("#col3").find("option:selected").text();
			var enpname = document.getElementById("col4").value;
			var orderid = document.getElementById("col5").value;	
			var prod = document.getElementById("col6").value;
			var start = document.getElementById("col7").value;
			var end = document.getElementById("col8").value;	
			var warehouse = document.getElementById("col11").value;
			var url="<%=basePath%>orderDetail.do?toOrderDetails"+"&pageNow="+p
			+"&odtype="+odtype+"&odstate="+odstate+"&odcheck="+odcheck+"&enpname="+enpname
			+"&orderid="+orderid+"&prod="+prod+"&orgnid="+orgId+"&warehouse="+warehouse
			+"&end="+end+"&start="+start;	
		changeReport(url);
		}
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
		var tableinfo="订单明细";
		var form = document.forms[0];
		if(func.valueOf() == "查询"){
			var orgId=$("#orgName").val();
			var allgroupids="";
			var obj=document.getElementById("orgName");
			for(var i = 0; i < obj.options.length; i++){
	            allgroupids =allgroupids+ obj.options[i].value+",";
	            }
			if(orgId.length == 0){
				orgId=allgroupids;
		    }else{
		    	orgId=$("#orgName").val();
		    }
			var odtype  = $("#col1").find("option:selected").text();
			var odstate =  $("#col2").find("option:selected").text();
			var odcheck = $("#col3").find("option:selected").text();
			var enpname = document.getElementById("col4").value;
			var orderid = document.getElementById("col5").value;	
			var prod = document.getElementById("col6").value;
			var start = document.getElementById("col7").value;
			var end = document.getElementById("col8").value;	
			var warehouse = document.getElementById("col11").value;
			var url="<%=basePath%>orderDetail.do?toOrderDetails"
			+"&odtype="+odtype+"&odstate="+odstate+"&odcheck="+odcheck+"&enpname="+enpname
			+"&orderid="+orderid+"&prod="+prod+"&warehouse="+warehouse+"&orgnid="+orgId
			+"&end="+end+"&start="+start;
			
			changeReport(url);
		}else if(func.valueOf() == "导出查询"){
			var orgId=$("#orgName").val();
			var orgName=$("#orgName").find("option:selected").text();
			var allgroupids="";
			var allgroupnames="";
			var obj=document.getElementById("orgName");
			for(var i = 0; i < obj.options.length; i++){
	            allgroupids =allgroupids+ obj.options[i].value+",";
	            allgroupnames=allgroupnames+obj.options[i].text+" ";
	            }
			if(orgId.length == 0){
				orgId=allgroupids;
				orgName=allgroupnames;
		    }else{
		    	orgId=$("#orgName").val();
		    	orgName=$("#orgName").find("option:selected").text();
		    }
			var odtype  = $("#col1").find("option:selected").text();
			var odstate =  $("#col2").find("option:selected").text();
			var odcheck = $("#col3").find("option:selected").text();
			var enpname = document.getElementById("col4").value;
			var orderid = document.getElementById("col5").value;	
			var prod = document.getElementById("col6").value;
			var start = document.getElementById("col7").value;
			var end = document.getElementById("col8").value;	
			var warehouse = document.getElementById("col11").value;
			form.action = "<%=basePath%>ExportTable.do?exportExcel"
					+ "&tableinfo=" + tableinfo+"&odtype="+odtype+"&odstate="+odstate+"&odcheck="+odcheck+"&enpname="+enpname
					+"&orderid="+orderid+"&prod="+prod+"&warehouse="+warehouse
					+"&orgnid="+orgId+"&orgName="+orgName
					+"&end="+end+"&start="+start;
			form.method = "post";
			form.submit();
		}else{
			var allgroupids="";
			var allgroupnames="";
			var obj=document.getElementById("orgName");
			for(var i = 0; i < obj.options.length; i++){
	            allgroupids =allgroupids+ obj.options[i].value+",";
	            allgroupnames=allgroupnames+obj.options[i].text+" ";
	            }
			form.action = "<%=basePath%>ExportTable.do?exportExcel"
					+ "&tableinfo="+tableinfo+"&odtype="+""+"&odstate="+""+"&odcheck="+""+"&enpname="+""
					+"&orderid="+""+"&prod="+""+"&warehouse="+""
					+"&orgnid="+allgroupids+"&orgName="+allgroupnames
					+"&end="+""+"&start="+"";
			form.method = "post";
			form.submit();
		}
	}
</script>
</head>
<body>
			<div class="ysh mar_t20">
				<form  id="Form" name="Form" method="post" >
					<table cellpadding="0" cellspacing="0" border="0" class="ysh_stab"
						style="border-bottom: none">
						<tr>
                       <th>订单编号：</th>
							<td><input id="col5" name="col5" type="text" class="in_text"
								value="${comtable.col5}" /></td>
							<th>订单类型：</th>
							<td><select id="col1" name="col1">
									<option selected="selected">${comtable.col1}</option>
									<c:if test="${!empty comtable.col1 }">
									<option value=""></option></c:if>
									<c:if test="${!empty findtypes }">
									<c:forEach items="${findtypes}" var="details">
									<option value="${details.col1}">${details.col1}</option>		
									</c:forEach></c:if>					
							</select></td>
							<th>订单状态：</th>
							<td><select id="col2" name="col2">
									<option selected="selected">${comtable.col2}</option>
									<c:if test="${!empty comtable.col2}">
									<option value=""></option></c:if>
									<c:if test="${!empty findstates }">
									<c:forEach items="${findstates}" var="details">
									<option value="${details.col1}">${details.col1}</option>		
									</c:forEach></c:if>
							</select></td>
						</tr>
						
						
						<tr>
						<th>牌号：</th>
							<td><input id="col6" name="col6" type="text" class="in_text"
								value="${comtable.col6}" /></td>
							<th>仓库：</th>
							<td><input id="col11" name="col11" type="text" class="in_text"
								value="${comtable.col11}" /></td>
							<th>买方企业名称：</th>
							<td><input id="col4" name="col4" type="text" class="in_text"
								value="${comtable.col4}" /></td>
							
						</tr>
						
						<tr>
						<th>开票状态：</th>
							<td><select id="col3" name="col3">
									<option selected="selected">${comtable.col3}</option>
									<c:if test="${!empty comtable.col3 }">
									<option value=""></option></c:if>
									<option value="已开票">已开票</option>							
									<option value="未开票">未开票</option>
							</select></td>
							<th>日期：</th>
							<td><span class="display_inb"><input type="text"
									class="timet" id="col7" name="col7" readonly="readonly"
									value="${comtable.col7}" /> <input type="button" class="timeb"
									value=""
									onClick="WdatePicker({el:'col7',dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'col8\')}'})" />
							</span><b>至</b><span class="display_inb"> <input type="text"
									id="col8" name="col8" class="timet" value="${comtable.col8}"
									readonly="readonly" /><input type="button" class="timeb"
									value=""
									onClick="WdatePicker({el:'col8',dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d %H:%m:%s',minDate:'#F{$dp.$D(\'col7\')}'})" /></span></td>
							
							</tr><tr>
							<td colspan="6">
								<div class="float_r">
									<input type="button" id="search" name="search" class="blubtn"
										value="查询" onclick="expExcel(this.value)" /> <input
										type="button" id="export" name="export" class="grebtn"
										value="导出全部" onclick="expExcel(this.value)" />
										<input
										type="button" id="exports" name="exports" class="orgbtn" 
										value="导出查询" onclick="expExcel(this.value)" />
								</div>
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div class="second_contain_list" style=" overflow-x:scroll; margin:15px;">
				<table cellpadding="0" cellspacing="0" border="0" class="xhdd2tab11">
					<tr>
						<th>订单编号</th>
						<th>订单类型</th>
						<th>订单状态</th>
						<th>订单总金额(元)</th>
						<th>订单改价原因</th>
						<th>支付方式</th>
						<th>支付备注</th>
						<th>开票状态</th>
						<th>买方企业编号</th>
						<th>买方企业名称</th>
						<th>卖方企业编号</th>
						<th>卖方企业名称</th>
						<th>下单时间</th>
						<th>下单人</th>
						<th>业务员</th>
						<th>审批人</th>
						<th>审批意见</th>
						<th>订单中产品ID</th>
						<th>产品分类</th>
						<th>牌号</th>
						<th>产品仓库</th>
						<th>仓库编码</th>
						<th>产品原单价(元/吨)</th>
						<th>产品新单价(元/吨)</th>
						<th>产品数量(吨)</th>
						<th>产品金额(元)</th>
						<th>运费(元/吨)</th>
					</tr>
					<c:if test="${!empty details }">
						<c:forEach items="${details}" var="details">
							<tr>
								<td>${details.col1}</td>
								<td>${details.col2}</td>
								<td>${details.col3}</td>
								<td>${details.col4}</td>
								<td>${details.col5}</td>
								<td>${details.col6}</td>
								<td>${details.col7}</td>
								<td>${details.col8}</td>
								<td>${details.col9}</td>
								<td>${details.col10}</td>
								<td>${details.col11}</td>
								<td>${details.col12}</td>
								<td>${details.col13}</td>
								<td>${details.col14}</td>
								<td>${details.col15}</td>
								<td>${details.col16}</td>
								<td>${details.col17}</td>
								<td>${details.col18}</td>
								<td>${details.col19}</td>
								<td>${details.col20}</td>
								<td>${details.col21}</td>
								<td>${details.col22}</td>
								<td>${details.col23}</td>
								<td>${details.col24}</td>
								<td>${details.col25}</td>
								<td>${details.col26}</td>
								<td>${details.col27}</td>
							</tr>
						</c:forEach>
					</c:if>
				</table>

			</div>
			<!-- 分页功能 start -->
			<div id="fanye" class="pagenext">
				<%
					if (Integer.parseInt(Page) <= 1) {
				%>
				<input type="button" class="up" value="&lt;&lt;上一页" />
				<%
					} else {
				%>
				<input type="button" class="up mr10" onclick="pre()"
					value="&lt;&lt;上一页" />
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
				<input type="button" class="pnum"
					value="<%=p%>" onclick="fenye('<%=p%>')" />
				<%
					} else {
				%>
				<input type="button" class="pnum"
					value="<%=p%>" onclick="fenye('<%=p%>')" />
				<%
					}
					}
				%>
				<%
					if (Integer.parseInt(Page) >= cont) {
				%>
				<input type="button" class="down ml10" value="下一页&gt;&gt;" />
				<%
					} else {
				%>
				<input type="button" class="down ml10" onclick="next()"
					value="下一页&gt;&gt;" />
				<%
					}
				%>
				<span>共<%=cont%>页</span>&nbsp;&nbsp; 到第<input id="text_shr" name="text_shr"
					class="inp_seller_center" value="<%=Page%>" />页<input
					type="button" onclick="yanzheng()" class="down ml10 btn_seller_center"
					value="确定" />
			</div>
			<!-- 分页功能 End -->
</body>
</html>

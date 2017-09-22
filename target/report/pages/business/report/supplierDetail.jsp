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
	var supname = document.getElementById("col1").value;
	var start = document.getElementById("col2").value;
	var end = document.getElementById("col3").value;
	$.ajax({
		url:'http://172.22.16.14:7003/report/orderDetail.do?toSupplierDetails&code=92a0001a-6d19-bc87-1056-d10dddd6bbaa&orgnid='+orgId+'&r='+Math.random()+"&supname="+supname+"&start="+start+"&end="+end,
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
		var supname = document.getElementById("col1").value;
		var start = document.getElementById("col2").value;
		var end = document.getElementById("col3").value;	
		var url="<%=basePath%>orderDetail.do?toSupplierDetails"+"&pageNow="+<%=Integer.parseInt(Page) + 1%>
		+"&orgnid="+allgroupids+"&supname="+supname+"&start="+start+"&end="+end;
		changeReport(url);
		}else{
			var supname = document.getElementById("col1").value;
			var start = document.getElementById("col2").value;
			var end = document.getElementById("col3").value;	
			var url="<%=basePath%>orderDetail.do?toSupplierDetails"+"&pageNow="+<%=Integer.parseInt(Page) + 1%>
			+"&orgnid="+orgId+"&supname="+supname+"&start="+start+"&end="+end;	
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
		var supname = document.getElementById("col1").value;
		var start = document.getElementById("col2").value;
		var end = document.getElementById("col3").value;	
		var url="<%=basePath%>orderDetail.do?toSupplierDetails"+"&pageNow="+<%=Integer.parseInt(Page) - 1%>
		+"&orgnid="+allgroupids+"&supname="+supname+"&start="+start+"&end="+end;
		changeReport(url);
		}else{
			var supname = document.getElementById("col1").value;
			var start = document.getElementById("col2").value;
			var end = document.getElementById("col3").value;	
			var url="<%=basePath%>orderDetail.do?toSupplierDetails"+"&pageNow="+<%=Integer.parseInt(Page) - 1%>
			+"&orgnid="+orgId+"&supname="+supname+"&start="+start+"&end="+end;
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
		var supname = document.getElementById("col1").value;
		var start = document.getElementById("col2").value;
		var end = document.getElementById("col3").value;	
		var url="<%=basePath%>orderDetail.do?toSupplierDetails"+"&pageNow="+p
		+"&orgnid="+allgroupids+"&supname="+supname+"&start="+start+"&end="+end;
		changeReport(url);
		}else{
			var supname = document.getElementById("col1").value;
			var start = document.getElementById("col2").value;
			var end = document.getElementById("col3").value;	
			var url="<%=basePath%>orderDetail.do?toSupplierDetails"+"&pageNow="+p
			+"&orgnid="+orgId+"&supname="+supname+"&start="+start+"&end="+end;
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
		var supname = document.getElementById("col1").value;
		var start = document.getElementById("col2").value;
		var end = document.getElementById("col3").value;	
		var url="<%=basePath%>orderDetail.do?toSupplierDetails"+"&pageNow="+p
		+"&orgnid="+allgroupids+"&supname="+supname+"&start="+start+"&end="+end;
		changeReport(url);
		}else{
			var supname = document.getElementById("col1").value;
			var start = document.getElementById("col2").value;
			var end = document.getElementById("col3").value;	
			var url="<%=basePath%>orderDetail.do?toSupplierDetails"+"&pageNow="+p
			+"&orgnid="+orgId+"&supname="+supname+"&start="+start+"&end="+end;	
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
		var tableinfo="生产厂商";
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
			var supname = document.getElementById("col1").value;
			var start = document.getElementById("col2").value;
			var end = document.getElementById("col3").value;	
			var url="<%=basePath%>orderDetail.do?toSupplierDetails"+"&orgnid="+orgId
				+"&supname="+supname+"&start="+start+"&end="+end;
			
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
			var supname = document.getElementById("col1").value;
			var start = document.getElementById("col2").value;
			var end = document.getElementById("col3").value;	
			form.action = "<%=basePath%>ExportTable.do?exportExcel"
					+ "&tableinfo=" + tableinfo+"&supname="+supname+"&orgnid="+orgId+"&orgName="+orgName
					+"&start="+start+"&end="+end;
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
					+"&supname="+""+ "&tableinfo=" + tableinfo
					+"&orgnid="+allgroupids+"&orgName="+allgroupnames
					+"&start="+""+"&end="+"";
			form.method = "post";
			form.submit();
		}
	}
</script>
</head>
<body>
			<div class="ysh mar_t20">
				<form action="" id="Form" name="Form">
					<table cellpadding="0" cellspacing="0" border="0" class="ysh_stab"
						style="border-bottom: none">

						<tr>
							<th>生产商名称：</th>
							<td><input type="text" class="in_text" id="col1" name="col1"
								value="${comtable.col1}" /></td>
							<th>日期：</th>
							<td><span class="display_inb"><input type="text"
									class="timet" readonly="readonly" id="col2" name="col2"
									value="${comtable.col2}" /><input type="button" class="timeb"
									value=""
									onClick="WdatePicker({el:'col2',dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'col3\')}'})" /></span><b>至</b><span
								class="display_inb"><input type="text"
									readonly="readonly" id="col3" name="col3" class="timet"
									value="${comtable.col3}" /><input type="button" class="timeb"
									value=""
									onClick="WdatePicker({el:'col3',dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d %H:%m:%s',minDate:'#F{$dp.$D(\'col2\')}'})" /></span></td>
							<td>
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
			<div class="second_contain_list" style="overflow: scroll; margin: 15px; height: 500px;">
				<table cellpadding="0" cellspacing="0" border="0" class="xhdd2tab11">
					<tr>
						<th>生产商</th>
						<th>销量（吨）</th>
						<th>金额（元）</th>
					</tr>
					<c:if test="${!empty details }">
						<c:forEach items="${details}" var="details">
							<tr>
								<td>${details.col1}</td>
								<td>${details.col2}</td>
								<td>${details.col3}</td>
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

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
		var prov = $("#col1").find("option:selected").text();
		var city =  $("#col2").find("option:selected").text();
		var prod = document.getElementById("col3").value;
		var start = document.getElementById("col4").value;
		var end = document.getElementById("col5").value;	
		$.ajax({
			url:'http://172.22.16.14:7003/report/orderDetail.do?toAreaDetails&code=0e60ddce-56f8-b59f-e184-f50cee34315d&orgnid='+orgId+'&r='+Math.random()+"&prov="+prov+"&city="+city+"&prod="+prod+"&end="+end+"&start="+start,
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
			var prov = $("#col1").find("option:selected").text();
			var city =  $("#col2").find("option:selected").text();
			var prod = document.getElementById("col3").value;
			var start = document.getElementById("col4").value;
			var end = document.getElementById("col5").value;	
			var url="<%=basePath%>orderDetail.do?toAreaDetails"+
					"&pageNow="+<%=Integer.parseInt(Page) + 1%>+"&prov="+prov+"&orgId="+allgroupids
					+"&city="+city+"&prod="+prod+"&end="+end+"&start="+start;
		changeReport(url);
		}else{
			var orgId=$("#orgName").val();
			var prov = $("#col1").find("option:selected").text();
			var city =  $("#col2").find("option:selected").text();
			var prod = document.getElementById("col3").value;
			var start = document.getElementById("col4").value;
			var end = document.getElementById("col5").value;	
			var url="<%=basePath%>orderDetail.do?toAreaDetails"+
					"&pageNow="+<%=Integer.parseInt(Page) + 1%>+"&prov="+prov+"&orgnid="+orgId
					+"&city="+city+"&prod="+prod+"&end="+end+"&start="+start;
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
		var prov = $("#col1").find("option:selected").text();
		var city =  $("#col2").find("option:selected").text();
		var prod = document.getElementById("col3").value;
		var start = document.getElementById("col4").value;
		var end = document.getElementById("col5").value;	
		var url="<%=basePath%>orderDetail.do?toAreaDetails"+
				"&pageNow="+<%=Integer.parseInt(Page) - 1%>+"&prov="+prov+"&orgnid="+allgroupids
				+"&city="+city+"&prod="+prod+"&end="+end+"&start="+start;
		changeReport(url);
		}else{
			var prov = $("#col1").find("option:selected").text();
			var city =  $("#col2").find("option:selected").text();
			var prod = document.getElementById("col3").value;
			var start = document.getElementById("col4").value;
			var end = document.getElementById("col5").value;	
			var url="<%=basePath%>orderDetail.do?toAreaDetails"+
					"&pageNow="+<%=Integer.parseInt(Page) - 1%>+"&prov="+prov+"&orgnid="+orgId
					+"&city="+city+"&prod="+prod+"&end="+end+"&start="+start;
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
		var prov = $("#col1").find("option:selected").text();
		var city =  $("#col2").find("option:selected").text();
		var prod = document.getElementById("col3").value;
		var start = document.getElementById("col4").value;
		var end = document.getElementById("col5").value;	
		var url="<%=basePath%>orderDetail.do?toAreaDetails"+"&pageNow="+p
		+"&prov="+prov+"&orgnid="+allgroupids
		+"&city="+city+"&prod="+prod+"&end="+end+"&start="+start;
		changeReport(url);
		}else{
			var prov = $("#col1").find("option:selected").text();
			var city =  $("#col2").find("option:selected").text();
			var prod = document.getElementById("col3").value;
			var start = document.getElementById("col4").value;
			var end = document.getElementById("col5").value;	
			var url="<%=basePath%>orderDetail.do?toAreaDetails"+"&pageNow="+p
			+"&prov="+prov+"&orgnid="+orgId
			+"&city="+city+"&prod="+prod+"&end="+end+"&start="+start;
		changeReport(url);
		}
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
		var orgId=$("#orgName").val();
		var allgroupids="";
		var obj=document.getElementById("orgName");
		for(var i = 0; i < obj.options.length; i++){
            allgroupids =allgroupids+ obj.options[i].value+",";
            }
		if(orgId.length == 0){
		var prov = $("#col1").find("option:selected").text();
		var city =  $("#col2").find("option:selected").text();
		var prod = document.getElementById("col3").value;
		var start = document.getElementById("col4").value;
		var end = document.getElementById("col5").value;	
		var url="<%=basePath%>orderDetail.do?toAreaDetails"
			+ "&pageNow=" + p+"&prov="+prov+"&orgnid="+allgroupids
			+"&city="+city+"&prod="+prod+"&end="+end+"&start="+start;
		changeReport(url);	
		}else{
			var prov = $("#col1").find("option:selected").text();
			var city =  $("#col2").find("option:selected").text();
			var prod = document.getElementById("col3").value;
			var start = document.getElementById("col4").value;
			var end = document.getElementById("col5").value;	
			var url="<%=basePath%>orderDetail.do?toAreaDetails"
				+ "&pageNow=" + p+"&prov="+prov+"&orgnid="+orgId
				+"&city="+city+"&prod="+prod+"&end="+end+"&start="+start;
		changeReport(url);
		}	
	}
	<!--验证填写的是否是数字-->
	function yanzheng() {
		var v = document.getElementById("text_shr").value;
		if (v.length != 0 && !isNaN(v)) {
			chang();
		}
	}
	//判断是查询还是导出and传入的参数是是按钮的value
	function expExcel(func){
		var tableinfo="销售地区";
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
			var prov = $("#col1").find("option:selected").text();
			var city =  $("#col2").find("option:selected").text();
			var prod = document.getElementById("col3").value;
			var start = document.getElementById("col4").value;
			var end = document.getElementById("col5").value;	
			var url="<%=basePath%>orderDetail.do?toAreaDetails"+"&prov="+prov
			+"&orgnid="+orgId
			+"&city="+city+"&prod="+prod+"&end="+end+"&start="+start;
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
			var prov = $("#col1").find("option:selected").text();
			var city =  $("#col2").find("option:selected").text();
			var prod = document.getElementById("col3").value;
			var start = document.getElementById("col4").value;
			var end = document.getElementById("col5").value;	
			form.action = "<%=basePath%>ExportTable.do?exportExcel"+"&tableinfo="+tableinfo
			+"&prov="+prov+"&orgnid="+orgId+"&orgName="+orgName
			+"&city="+city+"&prod="+prod+"&end="+end+"&start="+start;
			form.method="post";
			form.submit();
		}else{
			var allgroupids="";
			var allgroupnames="";
			var obj=document.getElementById("orgName");
			for(var i = 0; i < obj.options.length; i++){
	            allgroupids =allgroupids+ obj.options[i].value+",";
	            allgroupnames=allgroupnames+obj.options[i].text+" ";
	            }
			var prov = $("#col1").find("option:selected").text();
			var city =  $("#col2").find("option:selected").text();
			var prod = document.getElementById("col3").value;
			var start = document.getElementById("col4").value;
			var end = document.getElementById("col5").value;	
			form.action = "<%=basePath%>ExportTable.do?exportExcel"+"&tableinfo="+tableinfo
			+"&prov="+prov+"&orgnid="+allgroupids+"&orgName="+allgroupnames
			+"&city="+city+"&prod="+prod+"&end="+end+"&start="+start;
			form.method="post";
			form.submit();
		}
		
	}
	//省市两级联动
	
$(function() {
		$.ajax({
			type : "post",
			url : "<%=basePath%>proAndCity.do?findProvince",
			dataType : "json",
			error : function(err) {
				alert(err.code);
			},
			success : ajaxSendCallBack
		})
	});
	
	function ajaxSendCallBack(data) {
		for ( var i = 0; i < data.length; i++) {
			<!--创建一个指名名称元素-->
			var op = document.createElement("option");
			<!--设置op的实际值为当前的省份名称-->
			op.value = data[i].col1;
			<!--创建文本节点-->
			var textNode = document.createTextNode(data[i].col1);
			<!--把文本子节点添加到op元素指定其显示值-->
			op.appendChild(textNode);

			document.getElementById("col1").appendChild(op);
		}
	}
	function city() {
      var provincename = $("#col1").val();
		$.ajax({
			type : "post",
			url : "<%=basePath%>proAndCity.do?findCity" + "&province="
					+ provincename,
			dataType : "json",
			error : function(err) {
				alert(err.code);
			},
			success : ajaxSendCallBackcity
		})
	}
	function ajaxSendCallBackcity(data) {
		<!--把select中的所有option移除-->
		var citySelect = document.getElementById("col2");
		<!--获取其所有子元素-->
		var optionEleList = citySelect.getElementsByTagName("option");
		<!--循环遍历每个option元素然后在citySelect中移除-->
		while (optionEleList.length > 1) {
			citySelect.removeChild(optionEleList[1]);
		}

		for (var i = 0; i < data.length; i++) {
			<!--创建一个指名名称元素-->
			var op = document.createElement("option");
			<!--设置op的实际值为当前的省份名称-->
			op.value = data[i].col1;
			<!--创建文本节点-->
			var textNode = document.createTextNode(data[i].col1);
			<!--获取其所有子元素-->
			op.appendChild(textNode);
			document.getElementById("col2").appendChild(op);
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
							<td colspan="2" style="padding-left: 10px;">省：<select
								style="width: 130px;" id="col1" name="col1" onchange="city()">
								<c:if test="${!empty comtable.col1 }">
									<option value=""></option></c:if>
									<option selected="selected" value="">${comtable.col1}</option>
							</select> 市：<select style="width: 130px;" id="col2" name="col2">
							<c:if test="${!empty comtable.col2 }">
									<option value=""></option></c:if>
									<option selected="selected" value="">${comtable.col2}</option>
							</select></td>
							<th>产品名称：</th>
							<td><input type="text" class="in_text" id="col3" name="col3"
								value="${comtable.col3}" /></td>
							<th>日期：</th>
							<td><span class="display_inb"><input type="text"
									class="timet" readonly="readonly" id="col4" name="col4"
									value="${comtable.col4}" /> <input type="button" class="timeb"
									readonly="readonly" value=""
									onClick="WdatePicker({el:'col4',dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'col5\')}'})" /></span><b>至</b><span
								class="display_inb"><input type="text" id="col5"
									name="col5" readonly="readonly" class="timet"
									value="${comtable.col5}" /><input type="button" class="timeb"
									value=""
									onClick="WdatePicker({el:'col5',dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d %H:%m:%s',minDate:'#F{$dp.$D(\'col4\')}'})" /></span></td>
						</tr>
						<tr>
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
			<div class="second_contain_list"
				style="overflow: scroll; margin: 15px; height: 500px;">
				<table cellpadding="0" cellspacing="0" border="0" class="xhdd2tab11">
					<tr>
						<th>省</th>
						<th>市</th>
						<th>生产商</th>
						<th>产品牌号</th>
						<th>产地</th>
						<th>销量（吨）</th>
						<th>金额（元）</th>
						<th>平均单价（元/吨）</th>
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

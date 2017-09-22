<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html><head>
<base href="<%=basePath%>">
<title>塑贸网</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/public.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/sec.css" />
<script src="<%=basePath%>/js/My97DatePicker/WdatePicker.js"
	charset="UTF-8" type="text/javascript"></script>
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
	function js_method(func){
		if(func==1){	
			var url="<%=basePath%>quantityTops.do?topToday";
			changeReport(url)
		}else if(func==2){
			var url="<%=basePath%>quantityTops.do?topWeek";
			changeReport(url)
		}else{
			var url="<%=basePath%>quantityTops.do?topMonth";
			changeReport(url)
		}
	}
</script>
</head>
<div class="right_date">
	<ul>
		<li><a href="javascript:void(0);" onclick="js_method(1)" id="timetoday"  class="act">今天</a></li>
		<li><a href="javascript:void(0);" onclick="js_method(2)" id="timetoday">本周</a></li>
		<li><a href="javascript:void(0);" onclick="js_method(3)" id="timetoday">本月</a></li>
		<li class="f_r">
			<div class="ysh" style="width: 450px; height: 43px">
				<table cellpadding="0" cellspacing="0" border="0" class="ysh_stab"
					style="border-bottom: none">
					<tr>
						<th>日期：</th>
						<td><span class="display_inb"> <input type="text"
								class="timet" id="starttime" name="starttime" value=""
								readonly="readonly" /> <input type="button" class="timeb"
								value=""
								onClick="WdatePicker({el:'starttime',dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endtime\')}'})" />

						</span><b>至</b><span class="display_inb"><input type="text"
								id="endtime" name="endtime" class="timet" value=""
								readonly="readonly" /><input type="button" class="timeb"
								value=""
								onClick="WdatePicker({el:'endtime',dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d %H:%m:%s',minDate:'#F{$dp.$D(\'starttime\')}'})" />

						</span></td>
						<td><input type="button" class="blubtn" value="查询"
							onclick=" return transDate();"
							style="width: 80px; margin-right: 0" /></td>
					</tr>

				</table>
			</div>
		</li>
	</ul>
</div>
			<!--销售汇总 start-->
			<div class="sales_summary">
				<h1>销售汇总</h1>
				<div class="sales_summary_contain">
					<dl>
						<dt>销量（吨）</dt>
						<dd>${countnumAll.col11}</dd>
					</dl>
					<dl>
						<dt>金额（元）</dt>
						<dd>${countnumAll.col10 }</dd>
					</dl>
					<dl>
						<dt>订单数</dt>
						<dd>${countnumAll.col12 }</dd>
					</dl>
					<dl>
						<dt>已出库量</dt>
						<dd>${countnumAll.col13 }</dd>
					</dl>
					<dl>
						<dt>客户量</dt>
						<dd>${countnumAll.col5 }</dd>
					</dl>
					<dl>
						<dt>新增客户量</dt>
						<dd>${countnumAll.col6 }</dd>
					</dl>
				</div>
			</div>
			<!--销售汇总 end-->
			<div class="sales_summary">
				<h1>销售排行</h1>
				<h2>热门产品 Top5</h2>
				<table cellpadding="0" cellspacing="0">
					<tr>
						<th>排名</th>
						<th>产品名称</th>
						<th>总销量(吨)</th>
						<th>总金额(元)</th>
						<th>平均单价(元/吨)</th>
					</tr>
					<c:if test="${!empty products }">
						<c:forEach items="${products}" var="products">
							<tr>
								<td><label for="${products.serialc }">
										${products.serialc } </label></td>
								<td><label for="${products.serialc }">
										${products.sort } </label></td>
								<td><label for="${products.serialc }">
										${products.totalQuantityc } </label></td>
								<td><label for="${products.serialc }">
										${products.totalPricec } </label></td>
								<td><label for="${products.serialc }">
										${products.averagec } </label></td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
				<h2>客户 Top5</h2>
				<table cellpadding="0" cellspacing="0">
					<tr>
						<th>排名</th>
						<th>客户名称</th>
						<th>购买量(吨)</th>
						<th>金额(元)</th>
						<th>平均单价(元/吨)</th>
					</tr>
					<c:if test="${!empty clients }">
						<c:forEach items="${clients}" var="clients">
							<tr>
								<td><label for="${clients.serialc }">
										${clients.serialc } </label></td>
								<td><label for="${clients.serialc }">
										${clients.sort } </label></td>
								<td><label for="${clients.serialc }">
										${clients.totalQuantityc } </label></td>
								<td><label for="${clients.serialc }">
										${clients.totalPricec } </label></td>
								<td><label for="${clients.serialc }">
										${clients.averagec } </label></td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
				<h2>供应商 Top5</h2>
				<table cellpadding="0" cellspacing="0">
					<tr>
						<th>排名</th>
						<th>供应商</th>
						<th>销量(吨)</th>
						<th>金额(元)</th>
						<th>平均单价(元/吨)</th>
					</tr>
					<c:if test="${!empty suplier }">
						<c:forEach items="${suplier}" var="suplier">
							<tr>
								<td><label for="${suplier.serialc }">
										${suplier.serialc } </label></td>
								<td><label for="${suplier.serialc }">
										${suplier.sort } </label></td>
								<td><label for="${suplier.serialc }">
										${suplier.totalQuantityc } </label></td>
								<td><label for="${suplier.serialc }">
										${suplier.totalPricec } </label></td>
								<td><label for="${suplier.serialc }">
										${suplier.averagec } </label></td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
				<h2>热销地区 Top5</h2>
				<table cellpadding="0" cellspacing="0">
					<tr>
						<th>排名</th>
						<th>销售地区</th>
						<th>销量(吨)</th>
						<th>金额(元)</th>
						<th>平均单价(元/吨)</th>
					</tr>
					<c:if test="${!empty area }">
						<c:forEach items="${area}" var="clients">
							<tr>
								<td><label for="${clients.serialc }">
										${clients.serialc } </label></td>
								<td><label for="${clients.serialc }">
										${clients.sort } </label></td>
								<td><label for="${clients.serialc }">
										${clients.totalQuantityc } </label></td>
								<td><label for="${clients.serialc }">
										${clients.totalPricec } </label></td>
								<td><label for="${clients.serialc }">
										${clients.averagec } </label></td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
			</div>
			<div class="sales_summary" style="width: 650px;; margin-left: 10px">
				<h1>订单状态概览</h1>
				<div class="sales_summary_contain">
					<dl>
						<dt>订单数</dt>
						<dd>${countnumOrder.col10}</dd>
					</dl>
					<dl>
						<dt>已支付单数</dt>
						<dd>${countnumOrder.col11}</dd>
					</dl>
					<dl>
						<dt>待支付单数</dt>
						<dd>${countnumOrder.col12}</dd>
					</dl>
					<dl>
						<dt>已取消单数</dt>
						<dd>${countnumOrder.col13}</dd>
					</dl>
				</div>
			</div>
			<div class="sales_summary" style="width: 330px; margin-left: 20px">
				<h1>客户数量概览</h1>
				<div class="sales_summary_contain">
					<dl>
						<dt>客户数量</dt>
						<dd>${countnum.col1 }</dd>
					</dl>
					<dl>
						<dt>新增客户数量</dt>
						<dd>${countnum.col2 }</dd>
					</dl>
				</div>
			</div>
</html>

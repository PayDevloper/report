<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String pmarketingid=(String)request.getAttribute("pmarketingid");
%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../common/meta.jsp"></jsp:include>
<jsp:include page="../../common/easyui.jsp"></jsp:include>
<script type="text/javascript" charset="UTF-8">
	var datagrid;
	var userDialog;
	var userForm;
	$(function() {
		
		datagrid = $('#datagrid').datagrid({
			url : 'payrightController.do?datagridcheck&pmarketingid=<%=pmarketingid%>',
			toolbar : '#toolbar',
			remoteSort:false,
			title : '',
			iconCls : 'icon-save',
			pagination : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 ],
			fit : true,
			fitColumns : true,
			nowrap : false,
			border : false,
			idField : 'id',
			frozenColumns : [ [ {
				field : 'id',
				title : '缴费记录号',
				width : 150,
				sortable : true
			}, {
				field : 'subitemname',
				title : '权利金',
				width : 100,
				sortable : true
			} ] ],
			columns : [ [ {
				field : 'startdate',
				title : '生效开始日期',
				width : 100,
				sortable : true
			}, {
				field : 'enddate',
				title : '生效截止日期',
				width : 100,
				sortable : true
			},  {
				field : 'currentstate',
				title : '当前状态',
				width : 100,
				sortable : true
			},{
				field : 'payamount',
				title : '缴费金额',
				width : 100,
				sortable : true
			},{
				field : 'payment',
				title : '支付状态',
				width : 100,
				sortable : true
			},{
				field : 'shouldpay',
				title : '应付金额',
				width : 100,
				sortable : true
			},{
				field : 'actualpay',
				title : '实付金额',
				width : 100,
				sortable : true
			},{
				field : 'paymentmethod',
				title : '支付方式',
				width : 100,
				sortable : true
			},{
				field : 'paytime',
				title : '支付日期',
				width : 100,
				sortable : true
			} ] ],
		});


	});


	function searchFun() {
		datagrid.datagrid('load', {
			subitemname : $('#toolbar input[name=subitemname]').val(),
			startdate : $('#toolbar input[name=startdate]').val(),
			enddate : $('#toolbar input[name=enddate]').val(),
			payment : $('#toolbar input[name=payment]').val()
		});
	}
	function clearFun() {
		$('#toolbar input').val('');
		datagrid.datagrid('load', {});
	}
</script>
</head>
<body class="easyui-layout" fit="true">
	<div region="center" border="false" style="overflow: hidden;">
		<div id="toolbar" class="datagrid-toolbar" style="height: auto;">
			<fieldset>
				<legend>权利金缴费查看</legend>
				<table class="tableForm">
					<tr>
						<th>权利金</th>
						<td colspan="2"><select class="easyui-combobox" name="subitemname" style="width:150px;">
						<option value="">全部</option>
									<c:if test="${!empty subitemname }">
									<c:forEach items="${subitemname}" var="details">
									<option value="${details.subitemname}">${details.subitemname}</option>		
									</c:forEach></c:if>
						</select></td>
					</tr>
					<tr>
						<th>生效日期</th>
						<td colspan="6"><input name="startdate" class="easyui-datebox" style="width: 140px;" />
						至<input name="enddate" class="easyui-datebox" style="width: 140px;" /></td>
					</tr>
					<tr>
						<th>支付状态</th>
						<td><select class="easyui-combobox" name="payment" style="width:150px;">
						<option value="">全部</option>
						<option value="已支付">已支付</option>
						<option value="未支付">未支付</option>
						</select>
						</td>
						<td><a class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchFun();" href="javascript:void(0);">查找</a><a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="clearFun();" href="javascript:void(0);">清空</a></td>
					</tr>
				</table>
			</fieldset>
		</div>
		<table id="datagrid"></table>
	</div>

	<div id="userDialog" style="display: none;overflow: hidden;">
		<form id="userForm" method="post">
			<input name='id' style="display:none" value="" />
			<table class="tableForm">
				<tr>
					<th>权利金子项</th>
					<td>
					<input name="subitemname" class="easyui-validatebox" required="true" />
					</td>
				</tr>
				<tr>
					<th>应付金额</th>
					<td><input name="shouldpay" id="shouldpay" class="easyui-validatebox"  readonly="readonly" required="true" />
					</td>
				</tr>
				<tr>
					<th>实付金额</th>
					<td><input name="actualpay" id="actualpay" class="easyui-validatebox"  readonly="readonly" required="true" />
					</td>
				</tr>
				<tr>
					<th>支付方式</th>
					<td><input name="paymentmethod" id="paymentmethod" class="easyui-validatebox"  readonly="readonly" required="true" />
					</td>
				</tr>
				<tr>
					<th>支付日期</th>
					<td><input name="paytime" id="paytime" class="easyui-validatebox"  readonly="readonly" required="true" />
					</td>
				</tr>
				<tr>
					<th>备注</th>
					<td>
					<input class="easyui-textbox" name="remark" data-options="multiline:true" 
					value="" style="width:300px;height:100px">
					</td>
				</tr>
				
			</table>
		</form>
	</div>

</body>
</html>
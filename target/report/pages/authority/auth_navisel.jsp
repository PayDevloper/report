<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		userForm = $('#userForm').form();
		datagrid = $('#datagrid').datagrid({
			url : 'NavigationController.do?datagrid',
			toolbar : '#toolbar',
			remoteSort : false,
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
				title : 'id',
				field : 'id',
				width : 50,
				checkbox : true
			}, {
				field : 'financeacc',
				title : '一级导航',
				width : 250,
				sortable : true
			} ] ],
			columns : [ [ {
				field : 'totalid',
				title : '链接',
				width : 250,
				sortable : true
			} ] ],
			onRowContextMenu : function(e, rowIndex, rowData) {
				e.preventDefault();
				$(this).datagrid('unselectAll');
				$(this).datagrid('selectRow', rowIndex);
				$('#menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			}
		});});

		//选择确定总项
		function selpaytotal() {
			var rows = datagrid.datagrid('getSelections');
			if (rows.length == 0) {
				$.messager.show({
					msg : '至少选中一项才可进行该操作',
					title : '提示'
				});
			} else if (rows.length > 1) {
				$.messager.show({
					msg : '只能选中一项才可进行该操作',
					title : '提示'
				});
			} else {
				window.opener.document.getElementById("totalname").value = rows[0].financeacc;
				window.opener.document.getElementById("subname").value = rows[0].id;
				window.close();
			}
		}
	function searchFun() {
		datagrid.datagrid('load', {
			subname : $('#toolbar input[name=subname]').val(),
			totalname : $('#toolbar input[name=totalname]').val(),
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
				<legend>筛选</legend>
				<table class="tableForm">
					<tr>
						<th>一级导航</th>
						<td colspan="2"><input name="subname" style="width: 305px;" /><input
							name="id" id="id" type="hidden" /></td>
					</tr>
					<tr>
						<th>链接</th>
						<td><input name="totalname" style="width: 200px;" /></td>
						<td><a class="easyui-linkbutton" iconCls="icon-search"
							plain="true" onclick="searchFun();" href="javascript:void(0);">查找</a><a
							class="easyui-linkbutton" iconCls="icon-remove" plain="true"
							onclick="clearFun();" href="javascript:void(0);">清空</a></td>
					</tr>
				</table>
			</fieldset>
			<div>
				<a class="easyui-linkbutton" iconCls="icon-edit"
					onclick="selpaytotal();" plain="true" href="javascript:void(0);">确定</a>
				<a class="easyui-linkbutton" iconCls="icon-undo"
					onclick="datagrid.datagrid('unselectAll');" plain="true"
					href="javascript:void(0);">取消选中</a>
			</div>
		</div>
		<table id="datagrid"></table>
	</div>

	<div id="userDialog" style="display: none; overflow: hidden;">
		<form id="userForm" method="post">
			<input name='id' style="display: none" value="" />
			<table class="tableForm">
				<tr>
					<th>导航名称</th>
					<td><input name="financeacc" class="easyui-validatebox"
						required="true" /></td>
				</tr>
				<tr>
					<th>链接</th>
					<td><input name="totalid" id="totalid"
						class="easyui-validatebox" required="true" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
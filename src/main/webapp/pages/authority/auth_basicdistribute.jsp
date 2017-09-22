<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../common/meta.jsp"></jsp:include>
<jsp:include page="../../common/easyui.jsp"></jsp:include>
<script type="text/javascript" charset="UTF-8">
	var datagrid;
	var url;
	var userDialog;
	var userForm;
	$(function() {
		userForm = $('#userForm').form();
		datagrid = $('#datagrid').datagrid({
			url : 'AuthdistributeController.do?disbasicauthdatagrid',
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
				field : 'codeid',
				title : '授权名称',
				width : 150,
				sortable : true
			} ] ],
			columns : [ [ {
				field : 'subname',
				title : '岗位名称',
				width : 150,
				sortable : true
			}, {
				field : 'financeacc',
				title : '权限种类',
				width : 400,
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
		});
		
		userDialog = $('#userDialog').show().dialog({
			modal : true,
			title : '新建信息',
			buttons : [ {
				text : '确定',
				handler : function() {
						        	var authname=[]; 
						        	$.each($(document.getElementById('authframe').contentWindow.document).find('input[type="checkbox"]:checked'),
						        	       function(index, value){
						        		authname.push($(value).val());
						        	});
						userForm.form('submit', {
							url : 'AuthdistributeController.do?editbase&auth='+authname,
							success : function(data) {
								userDialog.dialog('close');
								$.messager.show({
									msg : '信息编辑成功！',
									title : '提示'
								});
								datagrid.datagrid('reload');
							}
						});
				}
			} ]
		}).dialog('close');
	});

	function edit() {
		var rows = datagrid.datagrid('getSelections');
		if (rows.length != 1 && rows.length != 0) {
			var names = [];
			for (var i = 0; i < rows.length; i++) {
				names.push(rows[i].name);
			}
			$.messager.show({
				msg : '只能选择一条数据进行编辑！您已经选择了' + rows.length + '个',
				title : '提示'
			});
		} else if (rows.length == 1) {
			userDialog.dialog('open');
			userForm.form('clear');
			 var id=rows[0].id;
			 url="<%=basePath%>AuthdistributeController.do?toeditbaseinfo";
			$('iframe').attr('src', url);
			userForm.form('load', {
				id : rows[0].id,
				codeid : rows[0].codeid,
				subname : rows[0].subname,
				totalname : rows[0].totalname,
				remark : rows[0].remark,
				financeacc : rows[0].financeacc,
			});
		}
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
			<div>
				<a class="easyui-linkbutton" iconCls="icon-edit" onclick="edit();"
					plain="true" href="javascript:void(0);">编辑</a> <a
					class="easyui-linkbutton" iconCls="icon-undo"
					onclick="datagrid.datagrid('unselectAll');" plain="true"
					href="javascript:void(0);">取消选中</a>
			</div>
		</div>
		<table id="datagrid"></table>
	</div>

	<div id="userDialog" style="display: none; overflow: hidden;">
		<form id="userForm" name="userForm" method="post">
			<input name='id' style="display: none" value="" />
			<table class="tableForm">
				<tr>
					<th>授权名称</th>
					<td><input name="codeid" class="easyui-validatebox"  readonly="readonly"
						required="true" /></td>
				</tr>
				<tr>
					<th>岗位名称</th>
					<td><input name="subname" id="subname"  readonly="readonly"
						class="easyui-validatebox" readonly="readonly" required="true" />
					   <input name="totalname" id="totalname"
						class="easyui-validatebox" readonly="readonly" type="hidden" /></td>
				</tr>
				<tr>
					<th></th>
					<td>所有用户将可以访问以下被选中的模块</td>
				</tr>
				<tr>
					<td colspan=3><iframe id="authframe" name="authframe"
							width="800" height="200" frameBorder=0> </iframe></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
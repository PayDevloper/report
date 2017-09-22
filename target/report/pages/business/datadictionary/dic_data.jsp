<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../../common/meta.jsp"></jsp:include>
<jsp:include page="../../../common/easyui.jsp"></jsp:include>
<script type="text/javascript" charset="UTF-8">
	var datagrid;
	var userDialog;
	var userForm;
	$(function() {
		userForm = $('#userForm').form();
		datagrid = $('#datagrid').datagrid({
			url : 'DatadictionaryController.do?datagrid',
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
				field : 'remark',
				title : '类型',
				width : 150,
				sortable : true
			} ] ],
			columns : [ [ {
				field : 'totalid',
				title : '展示名称',
				width : 150,
				sortable : true
			}, {
				field : 'operuser',
				title : '操作人',
				width : 150,
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
					if (userForm.find('[name=id]').val() != '') {
						userForm.form('submit', {
							url : 'DatadictionaryController.do?edit',
							success : function(data) {
								userDialog.dialog('close');
								$.messager.show({
									msg : '信息编辑成功！',
									title : '提示'
								});
								datagrid.datagrid('reload');
							}
						});
					} else {
						userForm.form('submit', {
							url : 'DatadictionaryController.do?add',
							success : function(data) {
								try {
									var d = $.parseJSON(data);
									if (d) {
										userDialog.dialog('close');
										$.messager.show({
											msg : '信息创建成功！',
											title : '提示'
										});
										datagrid.datagrid('reload');
									}
								} catch (e) {
									$.messager.show({
										msg : '信息已存在',
										title : '提示'
									});
								}
							}
						});
					}

				}
			} ]
		}).dialog('close');
	});

	function append() {
		userDialog.dialog('open');
		userForm.form('clear');
	}

	function selpositionitem() {
		window.open('/platform/AuthdistributeController.do?positionsel');
	}

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
			userForm.form('load', {
				id : rows[0].id,
				remark : rows[0].remark,
				totalid : rows[0].totalid,
				subname : rows[0].subname,
				totalname : rows[0].totalname,
				remark1 : rows[0].remark1
			});
		}
	}
	function searchFun() {
		datagrid.datagrid('load', {
			remark : $('#toolbar input[name=subname]').val(),
			totalid : $('#toolbar input[name=totalname]').val(),
		});
	}
	function clearFun() {
		$('#toolbar input').val('');
		datagrid.datagrid('load', {});
	}
	function removed() {
		var ids = [];
		var rows = datagrid.datagrid('getSelections');
		if (rows.length > 0) {
			$.messager.confirm('请确认', '您要删除当前所选项？', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].id);
					}
					$.ajax({
						url : 'DatadictionaryController.do?del',
						data : {
							ids : ids.join(',')
						},
						cache : false,
						dataType : "json",
						success : function(response) {
							datagrid.datagrid('unselectAll');
							datagrid.datagrid('reload');
							$.messager.show({
								title : '提示',
								msg : '删除成功！'
							});
						}
					});
				}
			});
		} else {
			$.messager.alert('提示', '请选择要删除的记录！', 'error');
		}
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
						<th>类型</th>
						<td colspan="2"><input name="subname" style="width: 305px;" /><input
							name="id" id="id" type="hidden" /></td>
					</tr>
					<tr>
						<th>展示名称</th>
						<td><input name="totalname" style="width: 200px;" /></td>
						<td><a class="easyui-linkbutton" iconCls="icon-search"
							plain="true" onclick="searchFun();" href="javascript:void(0);">查找</a><a
							class="easyui-linkbutton" iconCls="icon-remove" plain="true"
							onclick="clearFun();" href="javascript:void(0);">清空</a></td>
					</tr>
				</table>
			</fieldset>
			<div>
				<a class="easyui-linkbutton" iconCls="icon-add" onclick="append();"
					plain="true" href="javascript:void(0);">增加</a> <a
					class="easyui-linkbutton" iconCls="icon-remove"
					onclick="removed();" plain="true" href="javascript:void(0);">删除</a>
				<a class="easyui-linkbutton" iconCls="icon-edit" onclick="edit();"
					plain="true" href="javascript:void(0);">编辑</a> <a
					class="easyui-linkbutton" iconCls="icon-undo"
					onclick="datagrid.datagrid('unselectAll');" plain="true"
					href="javascript:void(0);">取消选中</a>
			</div>
		</div>
		<table id="datagrid"></table>
	</div>

	<div id="userDialog" style="display: none;overflow: hidden;">
		<form id="userForm" method="post">
			<input name='id' style="display:none" value="" />
			<table class="tableForm">
				<tr>
					<th>展示名称</th>
					<td><input name="totalid" id="totalid" class="easyui-validatebox"  required="true" />
					</td>
				</tr>
				<tr>
					<th>字段编号</th>
					<td><input name="subname" id="subname" class="easyui-validatebox"  required="true" />
					</td>
				</tr>
			<tr>
					<th>字段名称</th>
					<td><input name="totalname" id="totalname" class="easyui-validatebox"  required="true" />
					</td>
				</tr>
				<tr>
					<th>备注</th>
					<td>
					<input class="easyui-textbox" name="remark1" data-options="multiline:true" 
					value="" style="width:300px;height:100px">
					</td>
				</tr>	
			</table>
		</form>
	</div>
</body>
</html>
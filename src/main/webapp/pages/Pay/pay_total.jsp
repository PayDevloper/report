<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../common/meta.jsp"></jsp:include>
<jsp:include page="../../common/easyui.jsp"></jsp:include>
<script type="text/javascript" charset="UTF-8">
	var datagrid;
	var userDialog;
	var userForm;
	var passwordInput;
	var userRoleDialog;
	var userRoleForm;
	$(function() {
		userForm = $('#userForm').form();

		//缴费项创建列表 (卖家,销售组织,销售组织ID,会员费状态,保证金状态,大客户通道建设状态,是否审核)
		//seller, sales_org, sales_orgID, member_status, margin_status, customer_status, audit_status
		datagrid = $('#datagrid').datagrid({
			url : 'payTotalController.do?datagrid',
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
				title : 'id',
				field : 'id',
				width : 50,
				checkbox : true
			}, {
				field : 'totalname',
				title : '总项名称',
				width : 260,
				sortable : true
			},{
				field : 'totaltype',
				title : '总项类型',
				width : 260,
				sortable : true
			} ] ],
			columns : [ [ {
				field : 'remark',
				title : '备注',
				width : 100,
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
				id : 'subsave',
				handler : function() {
					var stat="true";
					var totalname=trim(userForm.find('[name=totalname]').val());
					if (totalname=='')
						{
						stat="false";
						$.messager.alert('提示', '总项名称不能为空格，请调整！', '提示');
						return false;
						}

					if (stat=="true")
					{
					if (userForm.find('[name=id]').val() != '') {
						
						userForm.form('submit', {
							url : 'payTotalController.do?edit',
							success : function(data) {
								userDialog.dialog('close');
								$.messager.show({
									msg : '信息编辑成功！',
									title : '提示'
								});
								datagrid.datagrid('reload');
								$("#datagrid").datagrid("uncheckAll"); 
							}
						});
					} else {
						
						userForm.form('submit', {
							url : 'payTotalController.do?add',
							success : function(data) {
							//	$.messager.alert('提示',data, 'error');
								
									//var d = $.parseJSON(data);
									//if (d) {
										userDialog.dialog('close');
										$.messager.show({
											msg : '信息创建成功！',
											title : '提示'
										});
										datagrid.datagrid('reload');
									//}
										
							}
						});
						
					}
				  }
					userForm.form('clear');
				}
			} ]
		}).dialog('close');


	});

	function trim(str) {
		return str.replace(/(^\s*)|(\s*$)/g, "");
		}
		
	function append() {
		userForm.form('clear');
		userDialog.dialog('open');
		//userForm.find('[name=seller]').removeAttr('readonly');
		//userForm.find('[name=sales_org]').removeAttr('readonly');
		//userForm.find('[name=sales_orgID]').removeAttr('readonly');
		
		
	}

	function edit() {
		var rows = datagrid.datagrid('getSelections');
		if (rows.length == 0) {
			$.messager.show({
				msg : '请选择一条数据进行编辑操作！',
				title : '提示'
			});
		} 
		else if (rows.length > 1) {
			var names = [];
			for ( var i = 0; i < rows.length; i++) {
				names.push(rows[i].name);
			}
			$.messager.show({
				msg : '只能选择一条数据进行编辑！您已经选择了' + rows.length + '个',
				title : '提示'
			});
		} else if (rows.length == 1) {
			//totalname, totaltype,operuser,operdate,remark
			//userForm.find('[name=sales_orgID]').attr('readonly', 'readonly');
			if ((rows[0].codeid=='byes'))
				{
				  $.messager.alert('提示', '已应用后总项不可以再进行编辑操作', '提示');
				}
			else
				{
			userDialog.dialog('open');
			userForm.form('clear');
			userForm.form('load', {
				id : rows[0].id,
				totalname : rows[0].totalname,
				totaltype : rows[0].totaltype,
				remark : rows[0].remark
			});
			}
		}
	}

	function editRole() {
		var ids = [];
		var rows = datagrid.datagrid('getSelections');
		if (rows.length > 0) {
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].id);
			}
			userRoleForm.find('input[name=userIds]').val(ids.join(','));
			userRoleDialog.dialog('open');
		} else {
			$.messager.alert('提示', '请选择要编辑的记录！', 'error');
		}
	}

	function funremove() {
		var ids = [];
		var rows = datagrid.datagrid('getSelections');
		if (rows.length > 0) {
			$.messager.confirm('请确认', '如果您删除总项，则总项下的子项和未审核和未审核通过的配置也将被删除？', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						if ((rows[i].codeid=='byes'))
				    	   {
				    	    $.messager.alert('提示', '已应用后总项不可以再进行删除操作', '提示');
				    	   }
				       else
				    	   {
				    	   ids.push(rows[i].id);
				    	   }
					}
					
				if (ids.length > 0) {
					$.ajax({
						url : 'payTotalController.do?del',
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
				}
			});
		} else {
			$.messager.alert('提示', '请选择要删除的记录！', 'error');
		}
	}
	
	//批量审核
	function editaudit() {
		var ids = [];
		var rows = datagrid.datagrid('getSelections');
		if (rows.length > 0) {
			$.messager.confirm('请确认', '您要审核当前所选项？', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].id);
					}
					$.ajax({
						url : 'payTotalController.do?editaudit',
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
								msg : '审核成功！'
							});
						}
					});
				}
			});
		} else {
			$.messager.alert('提示', '请选择要审核的记录！', 'error');
		}
	}
	
	function searchFun() {
		datagrid.datagrid('load', {
			totalname : $('#toolbar input[name=totalname]').val(),
			totaltype : $('#toolbar input[name=totaltype]').val(),
			remark : $('#toolbar input[name=remark]').val()
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
				<legend>缴费总项管理</legend>
				<table class="tableForm">
					<tr>
						<th>总项名称</th>
						<td colspan="2"><input name="totalname" style="width: 305px;" /><input name="id" id="id" type="hidden" /></td>
					</tr>
					<tr>
						<th>备注</th>
						<td colspan="2"><input name="remark" style="width: 305px;" /></td>
					</tr>
					<tr>
						<th>总项类型</th>
						<td><select class="easyui-combobox" name="totaltype" style="width:150px;" panelHeight="100">
									<option value="">全部</option>
									<option value="保证金">保证金</option>
									<option value="权利金">权利金</option>
							</select></td>
						<td><a class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchFun();" href="javascript:void(0);">查找</a><a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="clearFun();" href="javascript:void(0);">清空</a></td>
					</tr>
				</table>
			</fieldset>
			<div>
				<a class="easyui-linkbutton" iconCls="icon-add" onclick="append();" plain="true" href="javascript:void(0);">增加</a> 
				<a class="easyui-linkbutton" iconCls="icon-remove" onclick="funremove();" plain="true" href="javascript:void(0);">删除</a> 
				<a class="easyui-linkbutton" iconCls="icon-edit" onclick="edit();" plain="true" href="javascript:void(0);">编辑</a> 
			<!--  <a class="easyui-linkbutton" iconCls="icon-edit" onclick="editaudit();" plain="true" href="javascript:void(0);">批量审核</a> -->	
				<a class="easyui-linkbutton" iconCls="icon-undo" onclick="datagrid.datagrid('unselectAll');" plain="true" href="javascript:void(0);">取消选中</a>
			</div>
		</div>
		<table id="datagrid"></table>
	</div>

	<div id="userDialog" style="display: none;overflow: hidden;">
		<form id="userForm" method="post">
			<input name='id' style="display:none" value="" />
			<table class="tableForm">
				<tr>
					<th><font color="ff0000">*</font>总项名称</th>
					<td>
					<input name="totalname" class="easyui-validatebox" required="true" value="" />
					</td>
				</tr>
				<tr>
					<th>总项类型</th>
					<td>
					<select class="easyui-combobox" name="totaltype" style="width:200px;" required="true" panelHeight="100">
						<option value="权利金">权利金</option>
						<option value="保证金">保证金</option>
					</select>
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

	<div id="menu" class="easyui-menu" style="width:120px;display: none;">
		<div onclick="append();" iconCls="icon-add">增加</div>
		<div onclick="funremove();" iconCls="icon-remove">删除</div>
		<div onclick="edit();" iconCls="icon-edit">编辑</div>
	</div>
</body>
</html>
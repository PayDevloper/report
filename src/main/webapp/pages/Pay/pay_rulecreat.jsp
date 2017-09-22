<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../common/meta.jsp"></jsp:include>
<jsp:include page="../../common/easyui.jsp"></jsp:include>
<script type="text/javascript" charset="UTF-8">
var products = [
    		    {productid:'FI-SW-01',name:'Koi'},
    		    {productid:'K9-DL-01',name:'Dalmation'},
    		    {productid:'RP-SN-01',name:'Rattlesnake'},
    		    {productid:'RP-LI-02',name:'Iguana'},
    		    {productid:'FL-DSH-01',name:'Manx'},
    		    {productid:'FL-DLH-02',name:'Persian'},
    		    {productid:'AV-CB-01',name:'Amazon Parrot'}
    		];
    		function productFormatter(value){
    			for(var i=0; i<products.length; i++){
    				if (products[i].productid == value) return products[i].name;
    			}
    			return value;
    		}
    		
	var datagrid;
	var userDialog;
	var userForm;
	var passwordInput;
	var userRoleDialog;
	var userRoleForm;
	$(function() {
		userForm = $('#userForm').form();
		
		var lastIndex;
		//缴费项创建列表 (卖家,销售组织,销售组织ID,会员费状态,保证金状态,大客户通道建设状态,是否审核)
		//seller, sales_org, sales_orgID, member_status, margin_status, customer_status, audit_status
		datagrid = $('#datagrid').datagrid({
			url : 'payController.do?datagrid',
			toolbar :[{
				text:'append',
				iconCls:'icon-add',
				handler:function(){
					$('#datagrid').datagrid('endEdit', lastIndex);
					$('#datagrid').datagrid('appendRow',{
						seller:'',
						sales_org:'',
						sales_orgID:'',
						member_status:'',
						margin_status:'',
						customer_status:'',
						audit_status:'未审核'
					});
					lastIndex = $('#datagrid').datagrid('getRows').length-1;
					$('#datagrid').datagrid('selectRow', lastIndex);
					$('#datagrid').datagrid('beginEdit', lastIndex);
				}
			},'-',{
				text:'delete',
				iconCls:'icon-remove',
				handler:function(){
					var row = $('#datagrid').datagrid('getSelected');
					if (row){
						var index = $('#datagrid').datagrid('getRowIndex', row);
						$('#datagrid').datagrid('deleteRow', index);
					}
				}
			},'-',{
				text:'accept',
				iconCls:'icon-save',
				handler:function(){
					$('#datagrid').datagrid('acceptChanges');
				}
			}],
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
				field : 'seller',
				title : '卖家',
				width : 100,
				sortable : true
			} ] ],
			columns : [ [ {
				field : 'sales_org',
				title : '销售组织',
				width : 150,
				sortable : true
			}, {
				field : 'sales_orgID',
				title : '销售组织ID',
				width : 100,
				sortable : true
			}, {
				field : 'member_status',
				title : '会员费状态',
				width : 150,
				sortable : true
			}, {
				field : 'margin_status',
				title : '保证金状态',
				width : 150,
				sortable : true
			}, {
				field : 'customer_status',
				title : '大客户通道建设状态',
				width : 200,
				sortable : true
			}, {
				field : 'audit_status',
				title : '审核状态',
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
			},
			onClickRow:function(rowIndex){
				if (lastIndex != rowIndex){
					$('#datagrid').datagrid('endEdit', lastIndex);
					$('#datagrid').datagrid('beginEdit', rowIndex);
				}
				lastIndex = rowIndex;
			}
			
			
		});
		
		
		$('[name=roleId]').combotree({
			url : 'roleController.do?tree',
			animate : false,
			lines : !sy.isLessThanIe8(),
			checkbox : true,
			multiple : true,
			onLoadSuccess : function(node, data) {
				var t = $(this);
				if (data) {
					$(data).each(function(index, d) {
						if (this.state == 'closed') {
							t.tree('expandAll');
						}
					});
				}
			}
		});

		userRoleDialog = $('#userRoleDialog').show().dialog({
			modal : true,
			title : '批量编辑用户角色',
			buttons : [ {
				text : '编辑',
				handler : function() {
					userRoleForm.submit();
				}
			} ]
		}).dialog('close');

		userRoleForm = $('#userRoleForm').form({
			url : 'payController.do?editUsersRole',
			success : function(data) {
				var d = $.parseJSON(data);
				if (d) {
					userRoleDialog.dialog('close');
					$.messager.show({
						msg : '批量修改用户角色成功！',
						title : '提示'
					});
					datagrid.datagrid('reload');
				}
			}
		});

		userDialog = $('#userDialog').show().dialog({
			modal : true,
			title : '新建信息',
			buttons : [ {
				text : '确定',
				handler : function() {
					//alert(userForm.find("input[name='id']").val());

					if (userForm.find('[name=id]').val() != '') {
						userForm.form('submit', {
							url : 'payController.do?edit',
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
							url : 'payController.do?add',
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
		//userForm.find('[name=seller]').removeAttr('readonly');
		//userForm.find('[name=sales_org]').removeAttr('readonly');
		//userForm.find('[name=sales_orgID]').removeAttr('readonly');
		userForm.form('clear');
	}

	function edit() {
		var rows = datagrid.datagrid('getSelections');
		if (rows.length != 1 && rows.length != 0) {
			var names = [];
			for ( var i = 0; i < rows.length; i++) {
				names.push(rows[i].name);
			}
			$.messager.show({
				msg : '只能选择一条数据进行编辑！您已经选择了【' + names.join(',') + '】' + rows.length + '个',
				title : '提示'
			});
		} else if (rows.length == 1) {
			//seller, sales_org, sales_orgID, member_status, margin_status, customer_status, audit_status,operuser,operdate,remark
			userForm.find('[name=sales_orgID]').attr('readonly', 'readonly');
			userDialog.dialog('open');
			userForm.form('clear');
			userForm.form('load', {
				id : rows[0].id,
				seller : rows[0].seller,
				sales_org : rows[0].sales_org,
				sales_orgID : rows[0].sales_orgID,
				member_status : rows[0].member_status,
				margin_status : rows[0].margin_status,
				customer_status : rows[0].customer_status,
				audit_status : rows[0].audit_status,
				remark : rows[0].remark
			});
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

	function remove() {
		var ids = [];
		var rows = datagrid.datagrid('getSelections');
		if (rows.length > 0) {
			$.messager.confirm('请确认', '您要删除当前所选项？', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].id);
					}
					$.ajax({
						url : 'payController.do?del',
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
						url : 'payController.do?editaudit',
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
			seller : $('#toolbar input[name=seller]').val(),
			sales_org : $('#toolbar input[name=sales_org]').val(),
			sales_orgID : $('#toolbar input[name=sales_orgID]').val()
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
						<th>卖家</th>
						<td colspan="2"><input name="seller" style="width: 305px;" /><input name="id" id="id" type="hidden" /></td>
					</tr>
					<tr>
						<th>销售组织</th>
						<td colspan="2"><input name="sales_org"  style="width: 305px;" /></td>
					</tr>
					<tr>
						<th>销售组织ID</th>
						<td><input name="sales_orgID" style="width: 200px;" />
						</td>
						<td><a class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchFun();" href="javascript:void(0);">查找</a><a class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="clearFun();" href="javascript:void(0);">清空</a></td>
					</tr>
				</table>
			</fieldset>
			<div>
				<a class="easyui-linkbutton" iconCls="icon-add" onclick="append();" plain="true" href="javascript:void(0);">增加</a> 
				<a class="easyui-linkbutton" iconCls="icon-remove" onclick="remove();" plain="true" href="javascript:void(0);">删除</a> 
				<a class="easyui-linkbutton" iconCls="icon-edit" onclick="edit();" plain="true" href="javascript:void(0);">编辑</a> 
				<a class="easyui-linkbutton" iconCls="icon-edit" onclick="editaudit();" plain="true" href="javascript:void(0);">批量审核</a> 
				<a class="easyui-linkbutton" iconCls="icon-undo" onclick="datagrid.datagrid('unselectAll');" plain="true" href="javascript:void(0);">取消选中</a>
			</div>
		</div>
	 <table id="datagrid" style="width:700px;height:auto"
			title="Editable DataGrid" iconCls="icon-edit" singleSelect="true"
			idField="id">
	 <thead>
			<tr>
				<th field="seller" width="80" editor="text">卖家</th>
				<th field="sales_org" width="80" editor="{type:'validatebox',options:{required:true}}">销售组织</th>
				<th field="sales_orgID" width="80" editor="text">销售组织ID</th>
				<th field="member_status" width="100"  editor="text">总项</th>
				
				<th field="margin_status" width="80" align="right" editor="text">保证金状态</th>
				<th field="customer_status" width="250" editor="text">大客户通道建设状态</th>
				<th field="audit_status" width="60" align="center" editor="text">是否审核</th>
			</tr>
		</thead>
	 </table>
	<!--
	<table id="datagrid" style="width:700px;height:auto"
			title="Editable DataGrid" iconCls="icon-edit" singleSelect="true"
			idField="id" url="payController.do?datagrid">
		<thead>
			<tr>
				<th field="seller" width="80" editor="text">卖家</th>
				<th field="sales_org" width="80" editor="text">销售组织</th>
				<th field="sales_orgID" width="80" editor="text">销售组织ID</th>
				<th field="productid" width="100" formatter="productFormatter" editor="{type:'combobox',options:{valueField:'productid',textField:'name',data:member_status,required:true}}">总项</th>
				
				<th field="margin_status" width="80" align="right" editor="text">保证金状态</th>
				<th field="customer_status" width="250" editor="text">大客户通道建设状态</th>
				<th field="audit_status" width="60" align="center" editor="{type:'checkbox',options:{on:'P',off:''}}">是否审核</th>
			</tr>
		</thead>
	</table>
	  -->	
	
	</div>

	<div id="userDialog" style="display: none;overflow: hidden;">
		<form id="userForm" method="post">
			<input name='id' style="display:none" value="" />
			<table class="tableForm">
				<tr>
					<th>卖家</th>
					<td>
					<input name="seller" class="easyui-validatebox" required="true" /> <input type="button" name="selseller" onclick="sel1()" style="width:60px;" id="selseller" value="选择">
					</td>
				</tr>
				<tr>
					<th>销售组织</th>
					<td><input name="sales_org" class="easyui-validatebox" required="true" />
					</td>
				</tr>
				<tr>
					<th>销售组织ID</th>
					<td><input name="sales_orgID" class="easyui-validatebox" required="true" />
					</td>
				</tr>
				<tr>
					<th>会员费状态</th>
					<td>
		                <input type="radio" name="member_status" style="width:40px;" value="0">禁用</input>
		                <input type="radio" name="member_status" style="width:40px;" value="1" checked="checked">启用</input>
					</td>
				</tr>
				<tr>
					<th>保证金状态</th>
					<td>
		                <input type="radio" name="margin_status" style="width:40px;" value="0">禁用</input>
		                <input type="radio" name="margin_status" style="width:40px;" value="1" checked="checked">启用</input>
					</td>
				</tr>
				<tr>
					<th>大客户通道建设状态</th>
					<td>
		                <input type="radio" name="customer_status" style="width:40px;" value="0">禁用</input>
		                <input type="radio" name="customer_status" style="width:40px;" value="1" checked="checked">启用</input>
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

	<div id="userRoleDialog" style="display: none;overflow: hidden;">
		<form id="userRoleForm" method="post">
			<table class="tableForm">
				<input type="hidden" name="userIds" />
				<tr>
					<th>所属角色</th>
					<td><select name="roleId" style="width: 156px;"></select></td>
				</tr>
			</table>
		</form>
	</div>

	<div id="menu" class="easyui-menu" style="width:120px;display: none;">
		<div onclick="append();" iconCls="icon-add">增加</div>
		<div onclick="remove();" iconCls="icon-remove">删除</div>
		<div onclick="edit();" iconCls="icon-edit">编辑</div>
	</div>
</body>
</html>
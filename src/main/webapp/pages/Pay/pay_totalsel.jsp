<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../common/meta.jsp"></jsp:include>
<jsp:include page="../../common/easyui.jsp"></jsp:include>
<script type="text/javascript" charset="UTF-8">
	var datagrid;
	var userForm;
	$(function() {
		userForm = $('#userForm').form();
		//缴费项创建列表 (卖家,销售组织,销售组织ID,会员费状态,保证金状态,大客户通道建设状态,是否审核)
		//seller, sales_org, sales_orgID, member_status, margin_status, customer_status, audit_status
		datagrid = $('#datagrid').datagrid({
			url : 'payTotalController.do?datagridsel',
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
				width : 100,
				sortable : true
			} ] ],
			columns : [ [ {
				field : 'totaltype',
				title : '总项类型',
				width : 150,
				sortable : true
			}, {
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
		
	});

	//选择确定总项
	function selpaytotal() {
		var rows = datagrid.datagrid('getSelections');
		if (rows.length != 1 && rows.length != 0) {
			var names = [];
			for ( var i = 0; i < rows.length; i++) {
				names.push(rows[i].name);
			}
			$.messager.show({
				msg : '只能选择一条数据！您已经选择了【' + names.join(',') + '】' + rows.length + '个',
				title : '提示'
			});
		} else if (rows.length == 1) {
			window.opener.document.getElementById("totalname").value=rows[0].totalname;
			window.opener.document.getElementById("totalid").value=rows[0].id;
			window.opener.document.getElementById("remark1").value=rows[0].totaltype;
			window.close();
		}
	}
	
	function searchFun() {
		datagrid.datagrid('load', {
			totalname : $('#toolbar input[name=totalname]').val(),
			totaltype : $('#toolbar input[name=totaltype]').val()
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
						<th>总项名称</th>
						<td colspan="2"><input name="totalname" style="width: 305px;" /><input name="id" id="id" type="hidden" /></td>
					</tr>
					<tr>
						<th>总项类型</th>
						<td><input name="totaltype"  style="width: 305px;" /></td>
						<td><a class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchFun();" href="javascript:void(0);">查找</a><a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="clearFun();" href="javascript:void(0);">清空</a></td>
					</tr>
				</table>
			</fieldset>
			<div>
				<a class="easyui-linkbutton" iconCls="icon-edit" onclick="selpaytotal();" plain="true" href="javascript:void(0);">确定</a> 
				<a class="easyui-linkbutton" iconCls="icon-undo" onclick="datagrid.datagrid('unselectAll');" plain="true" href="javascript:void(0);">取消选中</a>
			</div>
		</div>
		<table id="datagrid"></table>
	</div>

</body>
</html>
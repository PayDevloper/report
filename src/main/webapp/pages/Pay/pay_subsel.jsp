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
		//子项管理列表 (子项名称,总项名称,总项编号,财务科目,是否审核)
		//subname, totalname, totalid, financeacc, audit_status
		datagrid = $('#datagrid').datagrid({
			url : 'paySubController.do?datagridsel',
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
				field : 'subname',
				title : '子项名称',
				width : 100,
				sortable : true
			} ] ],
			columns : [ [ {
				field : 'totalname',
				title : '总项名称',
				width : 150,
				sortable : true
			}, {
				field : 'totalid',
				title : '总项编号',
				width : 100,
				sortable : true
			}, {
				field : 'financeacc',
				title : '财务科目',
				width : 150,
				sortable : true
			},{
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
			//subitemcode,subitemname,totalitemname,financeacc
			window.opener.document.getElementById("subitemcode").value=rows[0].id;
			window.opener.document.getElementById("subitemname").value=rows[0].subname;
			window.opener.document.getElementById("totalitemname").value=rows[0].totalname;
			window.opener.document.getElementById("financeacc").value=rows[0].financeacc;
			window.close();
		}
	}
	
	function searchFun() {
		//子项管理列表 (子项名称,总项名称,总项编号,财务科目,是否审核)
		//subname, totalname, totalid, financeacc, audit_status
		datagrid.datagrid('load', {
			subname : $('#toolbar input[name=subname]').val(),
			totalname : $('#toolbar input[name=totalname]').val(),
			financeacc : $('#toolbar input[name=financeacc]').val()
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
						<th>子项名称</th>
						<td colspan="2"><input name="subname" style="width: 305px;" /><input name="id" id="id" type="hidden" /></td>
					</tr>
					<tr>
						<th>总项名称</th>
						<td colspan="2"><input name="totalname"  style="width: 305px;" /></td>
					</tr>
					<tr>
						<th>财务科目</th>
						<td><input name="financeacc" style="width: 200px;" />
						</td>
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
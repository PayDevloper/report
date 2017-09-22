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
		datagrid = $('#datagrid').datagrid({
			url : 'userController.do?datagrid',
			toolbar : '#toolbar',
			remoteSort : false,
			title : '',
			iconCls : 'icon-save',
			fit : true,
			fitColumns : true,
			nowrap : false,
			border : false,
			idField : 'id',
			frozenColumns : [ [ {
				title : '用户编号',
				field : 'id',
				width : 50,
				checkbox : true
			}, {
				field : 'name',
				title : '用户名称',
				width : 100,
				sortable : true
			} ] ],
			columns : [ [ {
				field : 'createdatetime',
				title : '创建时间',
				width : 150,
				sortable : true
			}, {
				field : 'modifydatetime',
				title : '最后修改时间',
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
		if (rows.length == 0) {
			$.messager.show({
				msg : '至少选中一项才可进行该操作',
				title : '提示'
			});
		} else if (rows.length > 1) {
			var names = [];
			var userids = [];
			for (var i = 0; i < rows.length; i++) {
				names.push(rows[i].name);
				userids.push(rows[i].id);
			}
			var arrnames = names.slice();
			var arrids = userids.slice();
			window.opener.document.getElementById("totalname").value = arrnames;
			window.opener.document.getElementById("totalid").value = arrids;
			window.close();
		} else {
			window.opener.document.getElementById("totalname").value = rows[0].name;
			window.opener.document.getElementById("totalid").value = rows[0].id;
			window.close();
		}
	}

	function searchFun() {
		datagrid.datagrid('load', {
			name : $('#toolbar input[name=name]').val(),
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
						<th>用户名称</th>
						<td><input name="name" style="width: 305px;" /></td>
						<td><a class="easyui-linkbutton" iconCls="icon-search"
							plain="true" onclick="searchFun();" href="javascript:void(0);">查找</a><a
							class="easyui-linkbutton" iconCls="icon-search" plain="true"
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

</body>
</html>
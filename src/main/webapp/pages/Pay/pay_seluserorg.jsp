<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>销售组织选择</title>
<jsp:include page="../../common/meta.jsp"></jsp:include>
<jsp:include page="../../common/easyui.jsp"></jsp:include>
<script type="text/javascript" charset="UTF-8">
	var datagrid;
	var userForm;
	$(function() {
		userForm = $('#userForm').form();
		//销售组织配置 (卖家,销售组织,销售组织ID,权利金配置,保证金配置)
		//seller,marketing,marketingid, rightgoldstat, bondstat
		datagrid = $('#datagrid').datagrid({
			url : 'paySubController.do?datagridseluserorg',
			toolbar : '#toolbar',
			remoteSort:false,
			singleSelect: true,
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
				width : 200,
				sortable : true
			} ] ],
			columns : [ [ {
				field : 'marketing',
				title : '销售组织',
				width : 150,
				sortable : true
			}, {
				field : 'marketingid',
				title : '销售组织ID',
				width : 100,
				sortable : true
			}
			] ],
			
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

	//选择销售组织
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
			var mkid=rows[0].marketingid
			$.get("paySubController.do?provingmkid&mkid="+mkid,function(dataa){							
			if(dataa.result=="success"){
			//seller,marketing,marketingid
			window.opener.document.getElementById("seller").value=rows[0].seller;
			window.opener.document.getElementById("marketing").value=rows[0].marketing;
			window.opener.document.getElementById("marketingid").value=rows[0].marketingid;
			window.close();
			}else{
				$.messager.alert('提示', '该销售组织计费规则已存在', '提示');
	    		return false;
     		}
   			})
		}
	}
	
	function searchFun() {
		//销售组织配置 (卖家,销售组织,销售组织ID,权利金配置,保证金配置)
		//seller,marketing,marketingid, rightgoldstat, bondstat
		datagrid.datagrid('load', {
			seller : $('#toolbar input[name=seller]').val(),
			marketing : $('#toolbar input[name=marketing]').val(),
			marketingid : $('#toolbar input[name=marketingid]').val()
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
						<td colspan="2"><input name="seller" style="width: 305px;" /></td>
					</tr>
					<tr>
						<th>销售组织</th>
						<td colspan="2"><input name="marketing"  style="width: 305px;" /></td>
					</tr>
					<tr>
						<th>销售组织ID</th>
						<td><input name="marketingid" style="width: 200px;" />
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
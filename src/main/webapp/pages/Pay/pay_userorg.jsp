<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		//销售组织配置 (卖家,销售组织,销售组织ID,权利金配置,保证金配置)
		//seller,marketing,marketingid, rightgoldstat, bondstat
		datagrid = $('#datagrid').datagrid({
			url : 'paySubController.do?datagriduserorg',
			toolbar : '#toolbar',
			remoteSort:false,
			title : '',
			singleSelect: true,
			iconCls : 'icon-save',
			pagination : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 ],
			fit : true,
			fitColumns : true,
			nowrap : false,
			border : false,
			idField : 'id',
			frozenColumns : [ [{
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
			},
			{field:'rightgoldstat',title:'权利金设定',width:50,align:'center',formatter:formatOper},
			{field:'bondstat',title:'保证金设定',width:50,align:'center',formatter:formatOperbond}
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
		
		
		//通过ajax得到卖方名称的下拉列表内容
		$.ajax({
			type :"post",
		    url: "paySubController.do?showSeller",
		    async: false,
		    dataType : "json",
		    success: function(data2){
		    	for (var i=0;i<data2.length;i++){
		    		//alert(data2[i].seller);
		    		$('#seller').append("<option value='"+data2[i].seller+"'>"+data2[i].seller+"</option>");
				}
		    }
		});
		
	});

	function formatOper(val,row,index){
	    return '<a href="/platform/payrightController.do?Pay_rightgold&pmarketingid='+row.marketingid+'" target="_blank">'+val+'</a>';
	} 
	
	function formatOperbond(val,row,index){
	    return '<a href="/platform/paybondController.do?paybond&pmarketingid='+row.marketingid+'" target="_blank">'+val+'</a>';  
	} 
	
	function funrightgoldstat(index)	//未使用
	{
		//window.open('/platform/payrightController.do?Pay_rightgold&pmarketingid='+id);
		$('#datagrid').datagrid('selectRow',index);// 关键在这里  
	    var row = $('#datagrid').datagrid('getSelected');  
	    if (row){  
	    	window.open('/platform/payrightController.do?Pay_rightgold&pmarketingid='+row.marketingid);  
	    }  
	}
	
	function funbondstat(index)	//未使用
	{
		$('#datagrid').datagrid('selectRow',index);// 关键在这里  
	    var row = $('#datagrid').datagrid('getSelected');  
	    if (row){  
	    	window.open('/platform/paybondController.do?paybond&pmarketingid='+row.marketingid);  
	    }  
	}
	
	function searchFun() {
		//销售组织配置 (卖家,销售组织,销售组织ID,权利金配置,保证金配置)
		//seller,marketing,marketingid, rightgoldstat, bondstat
		datagrid.datagrid('load', {
			seller : $('#seller').val(),
			marketing : $('#marketing').val(),
			marketingid : $('#toolbar input[name=marketingid]').val()
		});
	}
	
	function clearFun() {
		$('#toolbar input').val('');
		datagrid.datagrid('load', {});
	}
	
	function selSeller() {
		$("#marketing option[value!='']").remove();
		var sellername=$("#seller").val();//得到卖方名称所选内容
		
		//通过ajax得到销售组织的下拉列表内容
		$.ajax({
			type :"post",
		    url: "paySubController.do?selSeller&sellername="+encodeURI(encodeURI(sellername)),
		    async: false,
		    dataType : "json",
		    success: function(data2){
		    	for (var i=0;i<data2.length;i++){
		    		$('#marketing').append("<option value='"+data2[i].marketing+"'>"+data2[i].marketing+"</option>");
				}
		    }
		});
	}

</script>
</head>
<body class="easyui-layout" fit="true">
	<div region="center" border="false" style="overflow: hidden;">
		<div id="toolbar" class="datagrid-toolbar" style="height: auto;">
			<fieldset>
				<legend>缴费项及配置管理</legend>
				<table class="tableForm">
					<tr>
						<th>卖家</th>
						<td colspan="2">
						<select id="seller" name="seller" style="width:280px;" onchange="selSeller()">
						<option value="">空</option>
					</select></td>
					</tr>
					<tr>
						<th>销售组织</th>
						<td colspan="2">
						<select id="marketing" name="marketing" style="width:280px;">
						<option value="">空</option>
					</select></td>
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
			<!-- 
				<a class="easyui-linkbutton" iconCls="icon-edit" onclick="selpaytotal();" plain="true" href="javascript:void(0);">确定</a> 
				<a class="easyui-linkbutton" iconCls="icon-undo" onclick="datagrid.datagrid('unselectAll');" plain="true" href="javascript:void(0);">取消选中</a>
			 -->
			</div>
		</div>
		<table id="datagrid"></table>
	</div>

</body>
</html>
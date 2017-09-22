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

		//创建列表 
		//缴费记录号id，最低缴费金额subitemname，预警值totalitemname，支付状态financeacc，实际缴费actualpay，操作   支付方式paymentmethod，支付时间paytime，备注remark
		datagrid = $('#datagrid').datagrid({
			url : 'paycostpayController.do?datagridallcost',
		//	queryParams: form2Json("pmarketingid"),
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
				field : 'seller',
				title : '卖家',
				width : 80,
				sortable : true
			},{
				field : 'marketing',
				title : '销售组织',
				width : 80,
				sortable : true
			},{
				field : 'marketingid',
				title : '销售组织ID',
				width : 80,
				sortable : true
			},{
				field : 'id',
				title : '缴费记录号',
				width : 150,
				sortable : true
			}] ],
			columns : [ [ {
				field : 'shouldpay',
				title : '保证金',
				width : 80,
				sortable : true
			},{
				field : 'subitemname',
				title : '最低缴费金额',
				width : 80,
				sortable : true
			}, {
				field : 'totalitemname',
				title : '预警值',
				width : 50,
				sortable : true
			}, {
				field : 'financeacc',
				title : '支付状态',
				width : 80,
				sortable : true
			}, {
				field : 'codeid',
				title : '是否预警',
				width : 100,
				align:'center',formatter:formatOper
			}, {
				field : 'actualpay',
				title : '实际缴费',
				width : 100,
				sortable : true
			}, {
				field : 'paymentmethod',
				title : '支付方式',
				width : 100,
				sortable : true
			}, {
				field : 'paytime',
				title : '支付日期',
				width : 150,
				sortable : true
			} ] ],

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
	
	//保证金是否预警
	function formatOper(val,row,index){
		if (row.codeid=='是'){
			return '<img src=images/btn_red.png>';
    	}
		else{
			return '<img src=images/btn_green.png>';
    	}
	} 
	
	function searchFun() {
		datagrid.datagrid('load', {
			seller : $('#seller').val(),
			marketing : $('#marketing').val(),
			shouldpay : $('#toolbar input[name=shouldpay]').val(),
			financeacc : $('#toolbar input[name=financeacc]').val()
		});
	}
	function clearFun() {
		$('#toolbar input').val('');
		datagrid.datagrid('load', {
		});
	}
</script>
</head>
<body class="easyui-layout" fit="true">
	<div region="center" border="false" style="overflow: hidden;">
		<div id="toolbar" class="datagrid-toolbar" style="height: auto;">
			<fieldset>
				<legend>保证金支付记录管理</legend>
				<table class="tableForm">
					<tr>
						<th>卖家</th>
						<td><select id="seller" name="seller" style="width:200px;" onchange="selSeller()">
						<option value="">空</option>
					</select></td>
						<th>销售组织</th>
						<td><select id="marketing" name="marketing" style="width:200px;">
						<option value="">空</option>
					</select></td>
						<th>支付状态</th>
						<td><select class="easyui-combobox" name="financeacc" style="width:150px;" panelHeight="100">
						<option value="">全部</option>
						<option value="已支付">已支付</option>
						<option value="未支付">未支付</option>
						</select>
						</td>
					</tr>
					<tr>
						<th>子项名称</th>
						<td colspan="2">
							<select class="easyui-combobox" name="shouldpay" style="width:150px;">
								<option value="">全部</option>
								<c:if test="${!empty subitemname }">
									<c:forEach items="${subitemname}" var="details">
									<option value="${details.subitemname}">${details.subitemname}</option>		
									</c:forEach></c:if>
							</select>
						</td>	
						<td><a class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchFun();" href="javascript:void(0);">查找</a><a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="clearFun();" href="javascript:void(0);">清空</a></td>
					</tr>
				</table>
			</fieldset>
		</div>
		
		<table id="datagrid"></table>
	</div>

	<div id="userDialog" style="display: none;overflow: hidden;">
		<form id="userForm" method="post">
			<input name='id' style="display:none" value="" />
			<table class="tableForm">
				<tr>
					<th>缴费记录号</th>
					<td><input name="id" readonly="readonly" />
					</td>
				</tr>
				<tr>
					<th>最低缴费金额</th>
					<td><input name="subitemname" readonly="readonly" />
					</td>
				</tr>
				<tr>
					<th>实交金额</th>
					<td><input name="actualpay" readonly="readonly" />元
					</td>
				</tr>
				<tr>
					<th>支付方式</th>
					<td><input name="paymentmethod" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<th>支付时间</th>
					<td><input name="paytime" readonly="readonly"/>
					</td>
				</tr>
				
				<tr>
					<th>备注</th>
					<td>
					<input name="remark" readonly="readonly" data-options="multiline:true" 
					value="" style="width:300px;height:100px">
					</td>
				</tr>
				
			</table>
		</form>
	</div>
</body>
</html>
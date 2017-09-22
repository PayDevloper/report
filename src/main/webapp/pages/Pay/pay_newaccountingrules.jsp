<%@ page language="java" pageEncoding="UTF-8"%>
<%
String userrole=(String)session.getAttribute("userrole");  //登录用户岗位名称
%>
<!DOCTYPE html>
<html>
<head>
<title>计费规则创建</title>
<jsp:include page="../../common/meta.jsp"></jsp:include>
<jsp:include page="../../common/easyui.jsp"></jsp:include>
<script type="text/javascript" charset="UTF-8">
	var datagrid;
	var userDialog;
	var userForm;
	$(function() {
		userForm = $('#userForm').form();

		userDialog = $('#userDialog').show().dialog({
			modal : true,
			title : '新建信息',
			buttons : [ {
				text : '确定',
				handler : function() {

					if (userForm.find('[name=id]').val() != '') {
						userForm.form('submit', {
							url : 'paynewaccountingrulesController.do?edit',
							success : function(data) {
								userDialog.dialog('close');
								$.messager.show({
									msg : '信息编辑成功！',
									title : '提示'
								});
								userForm.form('clear');
								datagrid.datagrid('reload');
							}
						});
					} else {
						userForm.form('submit', {
							url : 'paynewaccountingrulesController.do?add',
							success : function(data) {
								
									//var d = $.parseJSON(data);
									//if (d) {
										userDialog.dialog('close');
										$.messager.show({
											msg : '信息创建成功！',
											title : '提示'
										});
										userForm.form('clear');
										datagrid.datagrid('reload');
									//}
								
							}
						});
					}
					
				}
			} ]
		}).dialog('close');

		//创建列表 
		/*
		, {
				field : 'measuredimension',
				title : '计量维度计费',
				width : 150,
				sortable : true
			}, {
				field : 'productcategory',
				title : '产品分类计费',
				width : 100,
				sortable : true
			}, {
				field : 'productapplication',
				title : '产品应用计费',
				width : 100,
				sortable : true
			}, {
				field : 'productbrand',
				title : '产品牌号计费',
				width : 100,
				sortable : true
			}, {
				field : 'collectionobject',
				title : '收取对象计费',
				width : 100,
				sortable : true
			}, {
				field : 'ordersourceobject',
				title : '订单来源计费',
				width : 100,
				sortable : true
			}
		*/
		//交易模式计费,计量维度计费,产品分类计费,产品应用计费,产品牌号计费,收取对象计费,订单来源
		datagrid = $('#datagrid').datagrid({
			url : 'paynewaccountingrulesController.do?datagrid',
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
				field : 'seller',
				title : '卖家',
				width : 150,
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
			}, {
				field : 'exchangemodel',
				title : '交易模式',
				width : 80,
				sortable : true
			}, {
				field : 'productbrand',
				title : '产品牌号',
				width : 80,
				sortable : true
			}, {
				field : 'approve',
				title : '审核状态',
				width : 100,
				sortable : true
			}, {
				field : 'billingconfiguration',
				title : '计费配置状态',
				width : 100,
				align:'center',formatter:formatOper
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


		//通过ajax得到卖方名称的下拉列表内容
		$.ajax({
			type :"post",
		    url: "paySubController.do?showSeller",
		    async: false,
		    dataType : "json",
		    success: function(data2){
		    	for (var i=0;i<data2.length;i++){
		    		//alert(data2[i].seller);
		    		$('#sellersel').append("<option value='"+data2[i].seller+"'>"+data2[i].seller+"</option>");
				}
		    }
		});
		
	});

	function selSeller() {
		$("#marketingsel option[value!='']").remove();
		var sellername=$("#sellersel").val();//得到卖方名称所选内容
		//通过ajax得到销售组织的下拉列表内容
		$.ajax({
			type :"post",
		    url: "paySubController.do?selSeller&sellername="+encodeURI(encodeURI(sellername)),
		    async: false,
		    dataType : "json",
		    success: function(data2){
		    	for (var i=0;i<data2.length;i++){
		    		$('#marketingsel').append("<option value='"+data2[i].marketing+"'>"+data2[i].marketing+"</option>");
				}
		    }
		});
	}
	

	function append() {
		userDialog.dialog('open');
		//userForm.form('clear');
	}
	
	function formatOper(val,row,index){
		if (row.approve!="已通过"){
			return '--';
    	}else{
	    return '<a href="/platform/payeditaccountingrulesController.do?payeditaccountingrules&pmarketingid='+row.marketingid+'" target="_blank">'+val+'</a>';
    	}
	} 
	
	function funbillingconf(index)	//未使用
	{
		//window.open('/platform/payrightController.do?Pay_rightgold&pmarketingid='+id);
		$('#datagrid').datagrid('selectRow',index);// 关键在这里  
	    var row = $('#datagrid').datagrid('getSelected');  
	    if (row){  
	    	window.open('/platform/payeditaccountingrulesController.do?payeditaccountingrules&pmarketingid='+row.marketingid);  
	    }
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
			userForm.find('[name=marketingid]').attr('readonly', 'readonly');
			userDialog.dialog('open');
			userForm.form('clear');
			userForm.form('load', {
				id : rows[0].id,
				seller : rows[0].seller,
				marketing : rows[0].marketing,
				marketingid : rows[0].marketingid,
				exchangemodel : rows[0].exchangemodel,
				measuredimension : rows[0].measuredimension,
				productcategory : rows[0].productcategory,
				productapplication : rows[0].productapplication,
				productbrand : rows[0].productbrand,
				collectionobject : rows[0].collectionobject,
				ordersourceobject :rows[0].ordersourceobject,
				approve : rows[0].approve,
				billingconfiguration : rows[0].billingconfiguration,
				remark : rows[0].remark
			});
		}
	}

	function funremove() {
		var ids = [];
		var rows = datagrid.datagrid('getSelections');
		if (rows.length > 0) {
			$.messager.confirm('请确认', '您要删除当前所选项？', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].id);
					}
					$.ajax({
						url : 'paynewaccountingrulesController.do?del',
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
			$.messager.confirm('请确认', '您要将当前所选项审核状态改为已通过吗？', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].id);
					}
					$.ajax({
						url : 'paynewaccountingrulesController.do?editaudit',
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
								msg : '审核通过成功！'
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
			seller : $('#sellersel').val(),
			marketing : $('#marketingsel').val(),
			marketingid : $('#toolbar input[name=marketingid]').val()
		});
	}
	function clearFun() {
		$('#toolbar input').val('');
		datagrid.datagrid('load', {});
	}
	
	//选择销售组织
	function seluserorg() {
		//userDialog.dialog('open');
		window.open('/platform/paySubController.do?Payseluserorg');
	}
	
	//审核不通过
	function editauditno() {
		var ids = [];
		var approved=0;
		var rows = datagrid.datagrid('getSelections');
		if (rows.length > 0) {
			$.messager.confirm('请确认', '您要将当前所选项审核状态改为未通过吗？', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						if(rows[i].approve!="已通过"){
						ids.push(rows[i].id);
						}else{
							approved=1;
						}
					}
					if(approved==1){
						$.messager.alert('提示', '已审核通过的无法进行不通过审核操作', '提示');
					}else{
					$.ajax({
						url : 'paynewaccountingrulesController.do?editauditno',
						data : {
							ids : ids.join(',')
						},
						cache : false,
						dataType : "json",
						success : function(response) {
							datagrid.datagrid('unselectAll');
							datagrid.datagrid('reload');
							/*
							$.messager.show({
								title : '提示',
								msg : '审核不通过成功！'
							});
							*/
						}
					});
					}
				}
			});
		} else {
			$.messager.alert('提示', '请选择要审核的记录！', 'error');
		}
	}
</script>
</head>
<body class="easyui-layout" fit="true">
	<div region="center" border="false" style="overflow: hidden;">
		<div id="toolbar" class="datagrid-toolbar" style="height: auto;">
			<fieldset>
				<legend>计费配置管理</legend>
				<table class="tableForm">
					<tr>
						<th>卖家</th>
						<td colspan="2"><select id="sellersel" name="sellersel" style="width:300px;" onchange="selSeller()">
						<option value="">空</option>
					</select><input name="id" id="id" type="hidden" /></td>
					</tr>
					<tr>
						<th>销售组织</th>
						<td colspan="2"><select id="marketingsel" name="marketingsel" style="width:300px;">
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
				<a class="easyui-linkbutton" iconCls="icon-add" onclick="append();" plain="true" href="javascript:void(0);">增加</a> 
				<a class="easyui-linkbutton" iconCls="icon-remove" onclick="funremove();" plain="true" href="javascript:void(0);">删除</a> 
				<a class="easyui-linkbutton" iconCls="icon-edit" onclick="edit();" plain="true" href="javascript:void(0);">编辑</a> 
				<%
				 if (userrole.contains("缴费审核"))
				 {
				%>
				<a class="easyui-linkbutton" iconCls="icon-edit" onclick="editaudit();" plain="true" href="javascript:void(0);">审核通过</a>
				<a class="easyui-linkbutton" style="width:120px" iconCls="icon-remove" onclick="editauditno();" plain="true" href="javascript:void(0);">审核不通过</a> 
				<%} %>
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
					<th>卖家</th>
					<td>
					<input name="seller" id="seller" class="easyui-validatebox" readonly="readonly" required="true" /> <input type="button" name="selseller" onclick="seluserorg()" style="width:60px;" id="selseller" value="选择">
					</td>
				</tr>
				<tr>
					<th>销售组织</th>
					<td><input name="marketing" id="marketing" class="easyui-validatebox" readonly="readonly" required="true" />
					</td>
				</tr>
				<tr>
					<th>销售组织ID</th>
					<td><input name="marketingid" id="marketingid" class="easyui-validatebox" readonly="readonly" required="true" />
					</td>
				</tr>
				<tr>
					<th>交易模式计费</th>
					<td>
		                <input type="radio" name="exchangemodel" style="width:40px;" value="0">禁用</input>
		                <input type="radio" name="exchangemodel" style="width:40px;" value="1" checked="checked">启用</input>
					</td>
				</tr>
				<tr>
					<th>产品牌号计费</th>
					<td>
		                <input type="radio" name="productbrand" style="width:40px;" value="0">禁用</input>
		                <input type="radio" name="productbrand" style="width:40px;" value="1" checked="checked">启用</input>
					</td>
				</tr>
				
				<!-- 
				<tr>
					<th>计量维度计费</th>
					<td>
		                <input type="radio" name="measuredimension" style="width:40px;" value="0">禁用</input>
		                <input type="radio" name="measuredimension" style="width:40px;" value="1" checked="checked">启用</input>
					</td>
				</tr>
				<tr>
					<th>产品分类计费</th>
					<td>
		                <input type="radio" name="productcategory" style="width:40px;" value="0">禁用</input>
		                <input type="radio" name="productcategory" style="width:40px;" value="1" checked="checked">启用</input>
					</td>
				</tr>
				<tr>
					<th>产品应用计费</th>
					<td>
		                <input type="radio" name="productapplication" style="width:40px;" value="0">禁用</input>
		                <input type="radio" name="productapplication" style="width:40px;" value="1" checked="checked">启用</input>
					</td>
				</tr>
				
				<tr>
					<th>收取对象计费</th>
					<td>
		                <input type="radio" name="collectionobject" style="width:40px;" value="0">禁用</input>
		                <input type="radio" name="collectionobject" style="width:40px;" value="1" checked="checked">启用</input>
					</td>
				</tr>
				<tr>
					<th>订单来源计费</th>
					<td>
		                <input type="radio" name="ordersourceobject" style="width:40px;" value="0">禁用</input>
		                <input type="radio" name="ordersourceobject" style="width:40px;" value="1" checked="checked">启用</input>
					</td>
				</tr>
				 -->
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
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
							url : 'paydepositbalanceController.do?edit',
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
							url : 'paydepositbalanceController.do?add',
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

		//创建列表 
		datagrid = $('#datagrid').datagrid({
			url : 'paydepositbalanceController.do?datagrid',
			toolbar : '#toolbar',
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
				field : 'ordernumber',
				title : '订单号',
				width : 100,
				sortable : true
			}, {
				field : 'exchangemodel',
				title : '交易模式',
				width : 100,
				sortable : true
			}, {
				field : 'remark1',
				title : '计量数量',
				width : 100,
				sortable : true
			}, {
				field : 'remark2',
				title : '订单支付时间',
				width : 100,
				sortable : true
			}, {
				field : 'deductingmoney',
				title : '扣除金额',
				width : 100,
				sortable : true
			}, {
				field : 'buyerid',
				title : '买方唯一编号',
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
	

	function append() {
		userDialog.dialog('open');
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
			userForm.find('[name=marketingid]').attr('readonly', 'readonly');
			userDialog.dialog('open');
			userForm.form('clear');
			userForm.form('load', {
				id : rows[0].id,
				seller : rows[0].seller,
				marketing : rows[0].marketing,
				marketingid : rows[0].marketingid,
				ordernumber : rows[0].ordernumber,
				productbrand : rows[0].productbrand,
				exchangemodel : rows[0].exchangemodel,
				measuredimension : rows[0].measuredimension,
				productcategory : rows[0].productcategory,
				productapplication : rows[0].productapplication,
				buyerid : rows[0].buyerid,
				deductingmoney : rows[0].deductingmoney,
				remark : rows[0].remark
			});
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
						url : 'paydepositbalanceController.do?del',
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


	function searchFun() {
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
</script>
</head>
<body class="easyui-layout" fit="true">
	<div region="center" border="false" style="overflow: hidden;">
		<div id="toolbar" class="datagrid-toolbar" style="height: auto;">
			<fieldset>
				<legend>计费明细查询</legend>
				<table class="tableForm">
					<tr>
						<th>卖家</th>
						<td colspan="2"><select id="seller" name="seller" style="width:300px;" onchange="selSeller()">
						<option value="">空</option>
					</select><input name="id" id="id" type="hidden" /></td>
					</tr>
					<tr>
						<th>销售组织</th>
						<td colspan="2"><select id="marketing" name="marketing" style="width:300px;">
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
		<!-- 	<div>
				<a class="easyui-linkbutton" iconCls="icon-add" onclick="append();" plain="true" href="javascript:void(0);">增加</a> 
				<a class="easyui-linkbutton" iconCls="icon-remove" onclick="remove();" plain="true" href="javascript:void(0);">删除</a> 
				<a class="easyui-linkbutton" iconCls="icon-edit" onclick="edit();" plain="true" href="javascript:void(0);">编辑</a> 
				<a class="easyui-linkbutton" iconCls="icon-undo" onclick="datagrid.datagrid('unselectAll');" plain="true" href="javascript:void(0);">取消选中</a>
			</div> -->
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
					<input name="seller" class="easyui-validatebox" required="true" /> <input type="button" name="selseller" onclick="sel1()" style="width:60px;" id="selseller" value="选择">
					</td>
				</tr>
				<tr>
					<th>销售组织</th>
					<td><input name="marketing" class="easyui-validatebox" required="true" />
					</td>
				</tr>
				<tr>
					<th>销售组织ID</th>
					<td><input name="marketingid" class="easyui-validatebox" required="true" />
					</td>
				</tr>
				<tr>
					<th>订单号</th>
					<td><input name="ordernumber" class="easyui-validatebox" required="true" />
					</td>
				</tr>
				<tr>
					<th>产品牌号</th>
					<td><input name="productbrand" class="easyui-validatebox" required="true" />
					</td>
				</tr>
				<tr>
					<th>交易模式</th>
					<td><input name="exchangemodel" class="easyui-validatebox" required="true" />
					</td>
				</tr>
				<tr>
					<th>计量维度</th>
					<td><input name="measuredimension" class="easyui-validatebox" required="true" />
					</td>
				</tr>
				<tr>
					<th>产品分类</th>
					<td><input name="productcategory" class="easyui-validatebox" required="true" />
					</td>
				</tr>
				<tr>
					<th>产品应用</th>
					<td><input name="productapplication" class="easyui-validatebox" required="true" />
					</td>
				</tr>
				<tr>
					<th>买方唯一编号</th>
					<td><input name="buyerid" class="easyui-validatebox" required="true" />
					</td>
				</tr>
				<tr>
					<th>扣除金额</th>
					<td><input name="deductingmoney" class="easyui-validatebox" required="true" />
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

</body>
</html>
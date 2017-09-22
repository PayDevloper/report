<%@ page language="java" pageEncoding="UTF-8"%>
<%
 String userrole=(String)session.getAttribute("userrole");  //登录用户岗位名称

 String pmarketingid=(String)request.getAttribute("pmarketingid");
 String seller=(String)request.getAttribute("seller");
 String marketing=(String)request.getAttribute("marketing");
%>
<!DOCTYPE html>
<html>
<head>
<title>保证金设定</title>
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
							url : 'paybondController.do?edit',
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
							url : 'paybondController.do?add',
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

		//创建列表 (发生日期，发生金额，当前余额，发生类型，订单号，是否预警)
		datagrid = $('#datagrid').datagrid({
			url : 'paybondController.do?datagridHis',
			queryParams: form2Json("pmarketingid"),
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
			frozenColumns : [ [{
				field : 'createtime',
				title : '发生日期',
				width : 150,
				sortable : true
			} ] ],
			columns : [ [ {
				field : 'remark1',
				title : '发生金额',
				width : 150,
				sortable : true
			}, {
				field : 'havedeposit',
				title : '当前余额',
				width : 100,
				sortable : true
			}, {
				field : 'remark2',
				title : '发生类型',
				width : 150,
				sortable : true
			}, {
				field : 'codeid',
				title : '订单号',
				width : 150,
				sortable : true
			}, {
				field : 'whetheralerted',
				title : '是否预警',
				width : 150,
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

	function append() {
		userDialog.dialog('open');
		userForm.form('clear');
		userForm.form('load', {
			seller : '<%=seller %>',
			marketing : '<%=marketing %>',
			marketingid : '<%=pmarketingid %>'
		});
	}
	
	function formatOper(val,row,index){
	    return '<a href="#" onclick="funcostpay('+index+')">'+val+'</a>';  
	}
	
	function funcostpay(index)
	{
		//保证金设定调用费用支付功能
		$('#datagrid').datagrid('selectRow',index);// 关键在这里  
	    var row = $('#datagrid').datagrid('getSelected');  
	    if (row){
	    	window.open('/platform/paycostpayController.do?paycostpay&ty=bond&pmarketingid='+row.id);  
	    }
	}

	//将表单数据转为json
    function form2Json(id) {

        var arr = $("#" + id).serializeArray()
        var jsonStr = "";

        jsonStr += '{';
        for (var i = 0; i < arr.length; i++) {
            jsonStr += '"' + arr[i].name + '":"' + arr[i].value + '",'
        }
        jsonStr = jsonStr.substring(0, (jsonStr.length - 1));
        jsonStr += '}'

        var json = JSON.parse(jsonStr)
        return json
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
				subitemcode : rows[0].subitemcode,
				subitemname : rows[0].subitemname,
				totalitemname : rows[0].totalitemname,
				financeacc : rows[0].financeacc,
				minpayamount : rows[0].minpayamount,
				warningsum : rows[0].warningsum,
				havedeposit : rows[0].havedeposit,
				whetheralerted : rows[0].whetheralerted,
				approve : rows[0].approve,
				payment : rows[0].payment,
				remark : rows[0].remark
			});
		}
	}
	
//$('#toolbar input[name=pmarketingid]').val()
	function searchFun() {
		datagrid.datagrid('load', {
			seller : $('#toolbar input[name=seller]').val(),
			marketing : $('#toolbar input[name=marketing]').val(),
			marketingid : $('#toolbar input[name=marketingid]').val(),
			createtime : $('#toolbar input[name=createtime]').val(),
			remark : $('#toolbar input[name=remark]').val(),
			codeid : $('#toolbar input[name=codeid]').val(),
			remark2 : $('#toolbar input[name=remark2]').val()
		});
	}
	function clearFun() {
		$('#toolbar input[name=createtime]').val('');
		$('#toolbar input[name=remark]').val('');
		$('#toolbar input[name=codeid]').val('');
		$('#toolbar input[name=remark2]').val('');
		datagrid.datagrid('load', {
			seller : $('#toolbar input[name=seller]').val(),
			marketing : $('#toolbar input[name=marketing]').val(),
			marketingid : $('#toolbar input[name=marketingid]').val(),
			createtime : $('#toolbar input[name=createtime]').val(),
			remark : $('#toolbar input[name=remark]').val(),
			codeid : $('#toolbar input[name=codeid]').val(),
			remark2 : $('#toolbar input[name=remark2]').val()
		});
	}
	
</script>
</head>
<body class="easyui-layout" fit="true">
	<div region="center" border="false" style="overflow: hidden;">
		<div id="toolbar" class="datagrid-toolbar" style="height: auto;">
			<fieldset>
				<legend>费用明细</legend>
				<table class="tableForm">
					<tr>
						<th>发生日期</th>
						<td colspan="3"><input name="createtime"  class="easyui-datebox" style="width: 140px;" /> 至 <input name="remark" class="easyui-datebox" style="width: 140px;" /> 
						 <input name="marketingid" type="hidden" value="<%=pmarketingid %>" style="width: 200px;" /><input name="pmarketingid" id="pmarketingid" type="hidden" value="<%=pmarketingid %>" style="width: 305px;" /></td>
					</tr>
					<tr>
						<th>发生类型</th>
						<td><select class="easyui-combobox" name="remark2" style="width:150px;">
						<option value="">全部</option>
						<option value="扣减服务费">扣减服务费</option>
						<option value="支付保证金">支付保证金</option>					
						</select></td>
						<th>订单号</th>
						<td><input name="codeid" style="width: 150px;" />
						<a class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchFun();" href="javascript:void(0);">查找</a><a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="clearFun();" href="javascript:void(0);">清空</a></td>
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
					<th>卖家</th>
					<td>
					<input name="seller" class="easyui-validatebox" readonly="readonly" required="true" /> 
					</td>
				</tr>
				<tr>
					<th>销售组织</th>
					<td><input name="marketing" class="easyui-validatebox" readonly="readonly" required="true" />
					</td>
				</tr>
				<tr>
					<th>销售组织ID</th>
					<td><input name="marketingid" class="easyui-validatebox" readonly="readonly" required="true" />
					</td>
				</tr>
				<tr>
					<th>子项配置编号</th>
					<td><input name="subitemcode" id="subitemcode" class="easyui-validatebox" readonly="readonly" required="true" /> <input type="button" name="selsub" onclick="selsubitem()" style="width:60px;" id="selsub" value="选择">
					</td>
				</tr>
				<tr>
					<th>子项名称</th>
					<td><input name="subitemname" id="subitemname" class="easyui-validatebox" readonly="readonly" required="true" />
					</td>
				</tr>
				<tr>
					<th>总项名称</th>
					<td><input name="totalitemname" id="totalitemname" class="easyui-validatebox" readonly="readonly" required="true" />
					</td>
				</tr>
				<tr>
					<th>财务科目</th>
					<td><input name="financeacc" id="financeacc" class="easyui-validatebox" readonly="readonly" required="true" />
					</td>
				</tr>
				<tr>
					<th>最低缴费金额</th>
					<td><input name="minpayamount"  class="easyui-numberbox" required="true" />
					</td>
				</tr>
				<tr>
					<th>警示金额</th>
					<td><input name="warningsum"  class="easyui-numberbox" required="true" />
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
		<div onclick="remove();" iconCls="icon-remove">删除</div>
		<div onclick="edit();" iconCls="icon-edit">编辑</div>
	</div>
</body>
</html>
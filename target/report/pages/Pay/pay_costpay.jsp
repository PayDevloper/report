<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.sumao.model.Paycostpay"%>
<%
String userrole=(String)session.getAttribute("userrole");  //登录用户岗位名称

 String pmarketingid=(String)request.getAttribute("pmarketingid");
 String ty=(String)request.getAttribute("ty");
 Paycostpay costp=(Paycostpay)request.getAttribute("costp");
%>
<!DOCTYPE html>
<html>
<head>
<title>费用支付</title>
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
					var shouldpay=userForm.find('[name=shouldpay]').val();
					var actualpay=userForm.find('[name=actualpay]').val();
					//应缴费用shouldpay,实际缴费actualpay
					if (actualpay/1>=shouldpay/1)
						{
						if (userForm.find('[name=id]').val() != '') {
							
								userForm.form('submit', {
									url : 'paycostpayController.do?edit',
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
								url : 'paycostpayController.do?add',
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
					else
						{
							$.messager.alert('提示', '实际缴费金额不能小于应缴费金额，请调整！', '提示');
							return false;
						}
				
				}
			} ]
		}).dialog('close');

		//创建列表 
		datagrid = $('#datagrid').datagrid({
			url : 'paycostpayController.do?datagrid',
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
				field : 'paymentcode',
				title : '缴费项编号',
				width : 150,
				sortable : true
			}, {
				field : 'subitemname',
				title : '子项名称',
				width : 150,
				sortable : true
			}, {
				field : 'totalitemname',
				title : '总项名称',
				width : 150,
				sortable : true
			}, {
				field : 'financeacc',
				title : '财务科目',
				width : 100,
				sortable : true
			}, {
				field : 'shouldpay',
				title : '应缴费金额',
				width : 100,
				sortable : true
			}, {
				field : 'actualpay',
				title : '实际缴费金额',
				width : 100,
				sortable : true
			}, {
				field : 'paymentmethod',
				title : '支付方式',
				width : 100,
				sortable : true
			},{
				field : 'payorderid',
				title : '支付凭证号',
				width : 100,
				sortable : true
			},{
				field : 'paytime',
				title : '支付时间',
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
		$.get('paycostpayController.do?provingused&paymentcode=<%=costp.getPaymentcode()%>&ty=<%=ty%>',function(data){
		if(data.result=="success"){
		//userForm.find('[name=actualpay]').removeAttr("readonly");
		$('#actualpay').numberbox("enable", true);
		userDialog.dialog('open');
		//userForm.form('clear');
		userForm.form('load', {
			seller : '<%=costp.getSeller() %>',
			marketing : '<%=costp.getMarketing() %>',
			marketingid : '<%=costp.getMarketingid() %>',
			paymentcode : '<%=costp.getPaymentcode() %>',
			subitemname : '<%=costp.getSubitemname() %>',
			totalitemname : '<%=costp.getTotalitemname() %>',
			financeacc : '<%=costp.getFinanceacc() %>',
			shouldpay : '<%=costp.getShouldpay() %>',
			codeid : '<%=ty %>'
		});
		}else{
			$.messager.alert('提示', '权利金支付记录同一个配置只可以录入一条信息', '提示');
 		  }
		})
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
			//userForm.find('[name=actualpay]').attr('readonly', true);
			$('#actualpay').numberbox("disable", true);
			userDialog.dialog('open');
			userForm.form('clear');
			userForm.form('load', {
				id : rows[0].id,
				seller : rows[0].seller,
				marketing : rows[0].marketing,
				marketingid : rows[0].marketingid,
				paymentcode : rows[0].paymentcode,
				subitemname : rows[0].subitemname,
				totalitemname : rows[0].totalitemname,
				financeacc : rows[0].financeacc,
				shouldpay : rows[0].shouldpay,
				actualpay : rows[0].actualpay,
				paymentmethod : rows[0].paymentmethod,
				payorderid : rows[0].payorderid,
				paytime : rows[0].paytime,
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
						url : 'paycostpayController.do?del',
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
			paytime : $('#toolbar input[name=paytime]').val(),
			payorderid : $('#toolbar input[name=payorderid]').val(),
			pmarketingid : $('#toolbar input[name=pmarketingid]').val()
		});
	}
	function clearFun() {
		$('#toolbar input[name=paytime]').val('');
		$('#toolbar input[name=payorderid]').val('');
		datagrid.datagrid('load', {
			paytime : $('#toolbar input[name=paytime]').val(),
			payorderid : $('#toolbar input[name=payorderid]').val(),
			pmarketingid : $('#toolbar input[name=pmarketingid]').val()
		});
	}
</script>
</head>
<body class="easyui-layout" fit="true">
	<div region="center" border="false" style="overflow: hidden;">
		<div id="toolbar" class="datagrid-toolbar" style="height: auto;">
			<fieldset>
				<legend>费用支付管理</legend>
				<table class="tableForm">
					<tr>
						<th>支付时间</th>
						<td colspan="2"><input name="paytime" class="easyui-datebox" style="width: 200px;" />
						<input name="pmarketingid" id="pmarketingid" type="hidden" value="<%=pmarketingid %>" style="width: 305px;" /></td>
					</tr>
					
					<tr>
						<th>支付凭证号</th>
						<td><input name="payorderid" style="width: 200px;" />
						</td>
						<td><a class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchFun();" href="javascript:void(0);">查找</a><a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="clearFun();" href="javascript:void(0);">清空</a></td>
					</tr>
				</table>
			</fieldset>
			<div>
				<a class="easyui-linkbutton" iconCls="icon-add" onclick="append();" plain="true" href="javascript:void(0);">增加</a> 
				<a class="easyui-linkbutton" iconCls="icon-remove" onclick="funremove();" plain="true" href="javascript:void(0);">删除</a> 
				<a class="easyui-linkbutton" iconCls="icon-edit" onclick="edit();" plain="true" href="javascript:void(0);">编辑</a> 
				<a class="easyui-linkbutton" iconCls="icon-undo" onclick="datagrid.datagrid('unselectAll');" plain="true" href="javascript:void(0);">取消选中</a>
				<a class="easyui-linkbutton" iconCls="icon-undo" onclick="javascript:history.back(-1)" plain="true" href="javascript:void(0);">返回页面</a>
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
					<th>缴费项编号</th>
					<td><input name="paymentcode" class="easyui-validatebox" readonly="readonly" required="true" />
					</td>
				</tr>
				<tr>
					<th>子项名称</th>
					<td><input name="subitemname" class="easyui-validatebox" readonly="readonly" required="true" />
					</td>
				</tr>
				<tr>
					<th>总项名称</th>
					<td><input name="totalitemname" class="easyui-validatebox" readonly="readonly" required="true" />
					</td>
				</tr>
				<tr>
					<th>财务科目</th>
					<td><input name="financeacc" class="easyui-validatebox" readonly="readonly" required="true" />
					</td>
				</tr>
				<tr>
					<th>应缴费金额</th>
					<td><input name="shouldpay" class="easyui-validatebox" readonly="readonly" required="true" />
					</td>
				</tr>
				<tr>
					<th><font color="ff0000">*</font>实际缴费金额</th>
					<td><input name="actualpay" id="actualpay" class="easyui-numberbox" required="true" />
					</td>
				</tr>
				<tr>
					<th><font color="ff0000">*</font>支付方式</th>
					<td>
					<select class="easyui-combobox" name="paymentmethod" style="width:150px;" required="true" panelHeight="160" >
									<c:if test="${!empty paymentmethod }">
									<c:forEach items="${paymentmethod}" var="details">
									<option value="${details.col2}">${details.col1}</option>		
									</c:forEach></c:if>
					</select>
					</td>
				</tr>
				<tr>
					<th><font color="ff0000">*</font>支付凭证号</th>
					<td><input name="payorderid" class="easyui-validatebox" required="true" />
					</td>
				</tr>
				<tr>
					<th>支付时间</th>
					<td><input name="paytime" id="paytime" class="easyui-datetimebox" style="width: 156px;" required="true"/>
					</td>
				</tr>
			
				
				<tr>
					<th>备注</th>
					<td>
					<input class="easyui-textbox" name="remark" data-options="multiline:true" 
					value="" style="width:300px;height:100px">
					<input name="codeid" type="hidden" value="<%=ty%>"/>
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
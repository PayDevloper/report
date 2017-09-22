<%@ page language="java" pageEncoding="UTF-8"%>
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
<title>保证金缴费</title>
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
										datagrid.datagrid('reload');
									}
								});
								
						} else {
							userForm.form('submit', {
								url : 'paycostpayController.do?add',
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
					else
						{
							$.messager.alert('提示', '实际缴费金额不能小于应缴费金额，请调整！', '提示');
							return false;
						}
				
				}
			} ]
		}).dialog('close');

		//创建列表 
		//缴费记录号id，最低缴费金额subitemname，预警值totalitemname，支付状态financeacc，实际缴费actualpay，操作   支付方式paymentmethod，支付时间paytime，备注remark
		datagrid = $('#datagrid').datagrid({
			url : 'paycostpayController.do?datagridcost',
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
				field : 'id',
				title : '缴费记录号',
				width : 150,
				sortable : true
			}, {
				field : 'subitemname',
				title : '最低缴费金额',
				width : 150,
				sortable : true
			}, {
				field : 'totalitemname',
				title : '预警值',
				width : 100,
				sortable : true
			}, {
				field : 'financeacc',
				title : '支付状态',
				width : 100,
				sortable : true
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
	

	function formatOper(val,row,index){
	    return '<a href="#" onclick="funbondcostpay('+index+')">'+val+'查看支付详情</a>';  
	} 
	

	function funbondcostpay(index)
	{
		//缴费记录号id，最低缴费金额subitemname，预警值totalitemname，支付状态financeacc，实际缴费actualpay，  支付方式paymentmethod，支付时间paytime，备注remark
		$('#datagrid').datagrid('selectRow',index);// 关键在这里  
	    var row = $('#datagrid').datagrid('getSelected');  
	    if (row){  

			userDialog.dialog('open');
			userForm.form('clear');
			userForm.form('load', {
				id : row.id,
				marketingid : row.marketingid,
				paymentcode : row.paymentcode,
				subitemname : row.subitemname,
				totalitemname : row.totalitemname,
				financeacc : row.financeacc,
				shouldpay : row.shouldpay,
				actualpay : row.actualpay,
				paymentmethod : row.paymentmethod,
				paytime : row.paytime,
				remark : row.remark
			});
			
	    	//window.open('/platform/payrightController.do?Pay_rightgold&pmarketingid='+row.marketingid);  
	    }  
	}

	function append() {
		//userForm.find('[name=actualpay]').removeAttr("readonly");
		$('#actualpay').numberbox("enable", true);
		userDialog.dialog('open');
		userForm.form('clear');
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
			pmarketingid : $('#toolbar input[name=pmarketingid]').val(),
			financeacc : $('#toolbar input[name=financeacc]').val()
		});
	}
	function clearFun() {
		$('#toolbar input[name=financeacc]').val('');
		datagrid.datagrid('load', {
			pmarketingid : $('#toolbar input[name=pmarketingid]').val(),
			financeacc : $('#toolbar input[name=financeacc]').val()
		});
	}
</script>
</head>
<body class="easyui-layout" fit="true">
	<div region="center" border="false" style="overflow: hidden;">
		<div id="toolbar" class="datagrid-toolbar" style="height: auto;">
			<fieldset>
				<legend>保证金缴费查看</legend>
				<table class="tableForm">
					<tr>
						<th>支付状态</th>
						<td><select class="easyui-combobox" name="financeacc" style="width:150px;">
						<option value="">全部</option>
						<option value="已支付">已支付</option>
						<option value="未支付">未支付</option>		
						</select>
						<input name="pmarketingid" id="pmarketingid" type="hidden" value="<%=pmarketingid %>" style="width: 105px;" /></td>	
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
					<input name="codeid" type="hidden" value="<%=ty%>"/>
					</td>
				</tr>
				
			</table>
		</form>
	</div>
</body>
</html>
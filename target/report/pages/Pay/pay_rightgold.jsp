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
<title>权利金设定</title>
<jsp:include page="../../common/meta.jsp"></jsp:include>
<jsp:include page="../../common/easyui.jsp"></jsp:include>
<script type="text/javascript" charset="UTF-8">
	var datagrid;
	var userDialog;
	var userForm;
	$(function() {
		userForm = $('#userForm').form();
		///platform/payrightController.do?Pay_rightgold&pmarketingid=33    权利金维护链接
		//卖家,销售组织,销售组织ID,子项配置编号,子项名称,总项名称,财务科目,缴费金额,生效开始日期,生效截止日期,创建人,创建日期,是否审核,是否缴费,备注
        //seller,marketing,marketingid,subitemcode,subitemname,totalitemname,financeacc,payamount,startdate,enddate,createperson,createtime,approve,payment,remark
		datagrid = $('#datagrid').datagrid({
			url : 'payrightController.do?datagrid',
			queryParams: form2Json("pmarketingid"),
			toolbar : '#toolbar',
			remoteSort:false,
		//	singleSelect: true,
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
				field : 'subitemcode',
				title : '子项配置编号',
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
				width : 200,
				sortable : true
			}, {
				field : 'payamount',
				title : '缴费金额',
				width : 200,
				sortable : true
			}, {
				field : 'startdate',
				title : '生效开始日期',
				width : 200,
				sortable : true
			}, {
				field : 'enddate',
				title : '生效截止日期',
				width : 200,
				sortable : true
			}, {
				field : 'approve',
				title : '是否审核',
				width : 100,
				sortable : true
			}, {
				field : 'remark1',
				title : '是否缴费',
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
		
		userDialog = $('#userDialog').show().dialog({
			modal : true,
			title : '新建信息',
			buttons : [ {
				text : '确定',
				handler : function() {
					//首先判断截止日期是否应晚于开始日期
					var start = $('#startdate').datetimebox('getValue');
					var end = $('#enddate').datetimebox('getValue');
					if (start<=end){
						//再判断该销售组织中的子项配置编号的配置是否已审核通过，如果通过，则生效开始时间需要大于原数据生效截止时间
						var mkid=userForm.find('[name=marketingid]').val();
						var subid=userForm.find('[name=subitemcode]').val();
						var payid=userForm.find('[name=id]').val();
						
						$.get("payrightController.do?proving&mkid="+mkid+"&subid="+subid+"&start="+start+"&payid="+payid,function(data){
							
					    	if(data.result=="success"){
								if (userForm.find('[name=id]').val() != '') {
									
									userForm.form('submit', {
										url : 'payrightController.do?edit',
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
										url : 'payrightController.do?add',
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
						}else{
							$.messager.alert('提示', '相同销售组织及子项配置时，新开始日期应晚于已有截止日期，请调整！', '提示');
				    		return false;
			     		  }
		       			})
					}else{
						
						$.messager.alert('提示', '截止日期应晚于开始日期，请调整！', '提示');
						return false;
					}
					//userForm.form('clear');
				}
			} ]
		}).dialog('close');


	});

	function append() {
		userDialog.dialog('open');
		//userForm.form('clear');
		userForm.form('load', {
			seller : '<%=seller %>',
			marketing : '<%=marketing %>',
			marketingid : '<%=pmarketingid %>'
		});
	}

	function formatOper(val,row,index){
		if (row.approve!="已通过"){
			return '--';
    	}else{
	    return '<a href="/platform/paycostpayController.do?paycostpay&ty=rightgold&pmarketingid='+row.id+'">'+val+'</a>';  
	    }
	}
	
	function funcostpay(index)	//未使用
	{
		//保证金设定调用费用支付功能
		$('#datagrid').datagrid('selectRow',index);// 关键在这里  
	    var row = $('#datagrid').datagrid('getSelected');  
	    if (row){
	    	if (row.approve!="已通过"){
	    		$.messager.alert('提示', '该缴费项目还未通过审核', 'error');
	    	}else{
	    		window.location='/platform/paycostpayController.do?paycostpay&ty=rightgold&pmarketingid='+row.id;
	    	}
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
			//卖家,销售组织,销售组织ID,子项配置编号,子项名称,总项名称,财务科目,缴费金额,生效开始日期,生效截止日期,创建人,创建日期,是否审核,是否缴费,备注
	        //seller,marketing,marketingid,subitemcode,subitemname,totalitemname,financeacc,payamount,startdate,enddate,createperson,createtime,approve,payment,remark
	        if (rows[0].approve=='已通过')
	    	   {
	    	    $.messager.alert('提示', '已生效后权利金设定不可以再进行修改操作', '提示');
	    	   }
	       else
	    	   {
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
					payamount : rows[0].payamount,
					startdate : rows[0].startdate,
					enddate : rows[0].enddate,
					approve : rows[0].approve,
					remark : rows[0].remark
				});
	    	   }
			
		}
	}

	function funremove() {
		var ids = [];
		var rows = datagrid.datagrid('getSelections');
		if (rows.length > 0) {
			$.messager.confirm('请确认', '您要删除当前所选项？', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						if (rows[i].approve=='已通过')
				    	   {
				    	    $.messager.alert('提示', '已生效后权利金设定不可以再进行删除操作', '提示');
				    	   }
				       else
				    	   {
							ids.push(rows[i].id);
				    	   }
					}
					
				if (ids.length > 0) {
					$.ajax({
						url : 'payrightController.do?del',
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
						url : 'payrightController.do?editaudit',
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
		//卖家,销售组织,销售组织ID,子项配置编号,子项名称,总项名称,财务科目,缴费金额,生效开始日期,生效截止日期,创建人,创建日期,是否审核,是否缴费,备注
        //seller,marketing,marketingid,subitemcode,subitemname,totalitemname,financeacc,payamount,startdate,enddate,createperson,createtime,approve,payment,remark
       
		datagrid.datagrid('load', {
			seller : $('#toolbar input[name=seller]').val(),
			marketing : $('#toolbar input[name=marketing]').val(),
			marketingid : $('#toolbar input[name=marketingid]').val(),
			subitemname : $('#toolbar input[name=subitemname]').val(),
			financeacc : $('#toolbar input[name=financeacc]').val()
		});
	}
	function clearFun() {
		$('#toolbar input[name=subitemname]').val('');
		$('#toolbar input[name=financeacc]').val('');
		datagrid.datagrid('load', {
			seller : $('#toolbar input[name=seller]').val(),
			marketing : $('#toolbar input[name=marketing]').val(),
			marketingid : $('#toolbar input[name=marketingid]').val(),
			subitemname : $('#toolbar input[name=subitemname]').val(),
			financeacc : $('#toolbar input[name=financeacc]').val()
		});
	}
	
	//选择子项
	function selsubitem() {
		//userDialog.dialog('open');
		window.open('/platform/paySubController.do?Paysubsel');
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
						url : 'payrightController.do?editauditno',
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
				<legend>权利金设定管理</legend>
				<table class="tableForm">
				<!-- 
					<tr>
						<th>卖家</th>
						<td colspan="2"><input name="seller" style="width: 305px;" /><input name="id" id="id" type="hidden" /></td>
					</tr>
					<tr>
						<th>销售组织</th>
						<td colspan="2"><input name="marketing"  style="width: 305px;" /></td>
					</tr>
					<tr>
						<th>销售组织ID</th>
						<td><input name="marketingid" style="width: 305px;" />
						</td>
					</tr>
				 -->
					<tr>
						<th>子项名称</th>
						<td colspan="2"><input name="subitemname"  value="" style="width: 305px;" /> <input name="marketingid" type="hidden" value="<%=pmarketingid %>" style="width: 200px;" /><input name="pmarketingid" id="pmarketingid" type="hidden" value="<%=pmarketingid %>" style="width: 305px;" /></td>
					</tr>
					<tr>
						<th>财务科目</th>
						<td><input name="financeacc" value="" style="width: 200px;" />
						</td>
						<td><a class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchFun();" href="javascript:void(0);">查找</a><a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="clearFun();" href="javascript:void(0);">清空</a></td>
					</tr>
				</table>
			</fieldset>
			<div>
				<a class="easyui-linkbutton" iconCls="icon-add" onclick="append();" plain="true" href="javascript:void(0);">增加</a> 
				<a class="easyui-linkbutton" iconCls="icon-remove" onclick="funremove();" plain="true" href="javascript:void(0);">删除</a>
				<%
				 if (userrole.contains("缴费审核"))
				 {
				%>
				<a class="easyui-linkbutton" iconCls="icon-edit" onclick="edit();" plain="true" href="javascript:void(0);">编辑</a>
				<a class="easyui-linkbutton" iconCls="icon-edit" onclick="editaudit();" plain="true" href="javascript:void(0);">审核通过</a>
				<a class="easyui-linkbutton" style="width:120px" iconCls="icon-remove" onclick="editauditno();" plain="true" href="javascript:void(0);">审核不通过</a> 
				<%} %>
				<a class="easyui-linkbutton" iconCls="icon-undo" onclick="datagrid.datagrid('unselectAll');" plain="true" href="javascript:void(0);">取消选中</a>
			</div>
		</div>
		<table id="datagrid" ></table>
	</div>

	<div id="userDialog" style="display: none;overflow: hidden;">
		<form id="userForm" name="userForm" method="post">
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
					<td><input name="marketing" class="easyui-validatebox" readonly="readonly"  required="true" />
					</td>
				</tr>
				<tr>
					<th>销售组织ID</th>
					<td><input name="marketingid" id="marketingid" class="easyui-validatebox" readonly="readonly"  required="true" />
					</td>
				</tr>
				<tr>
					<th><font color="ff0000">*</font>添加子项</th>
					<td>
		                <input name="subitemcode" id="subitemcode" readonly="readonly" class="easyui-validatebox" value=""  required="true"/> <input type="button" name="selsub" onclick="selsubitem()" style="width:60px;" id="selsub" value="选择">
					</td>
				</tr>
				<tr>
					<th><font color="ff0000">*</font>子项名称</th>
					<td>
		                <input name="subitemname" id="subitemname" readonly="readonly" class="easyui-validatebox" value="" required="true" />
					</td>
				</tr>
				<tr>
					<th><font color="ff0000">*</font>总项名称</th>
					<td><input name="totalitemname" id="totalitemname" readonly="readonly" class="easyui-validatebox" required="true" />
					</td>
				</tr>
				<tr>
					<th><font color="ff0000">*</font>财务科目</th>
					<td>
		                <input name="financeacc" id="financeacc" readonly="readonly" class="easyui-validatebox" value=""  required="true"/>
					</td>
				</tr>
				<tr>
					<th><font color="ff0000">*</font>缴费金额</th>
					<td>
		                <input name="payamount" class="easyui-numberbox" value="0" required="true" />
					</td>
				</tr>
				<tr>
					<th>生效开始日期</th>
					<td>
		               <input name="startdate" id="startdate" class="easyui-datetimebox" style="width: 156px;" />
					</td>
				</tr>
				<tr>
					<th>生效截止日期</th>
					<td>
		               <input name="enddate" id="enddate" class="easyui-datetimebox" style="width: 156px;" />
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
		<div onclick="funremove();" iconCls="icon-remove">删除</div>
		<div onclick="edit();" iconCls="icon-edit">编辑</div>
	</div>
</body>
</html>
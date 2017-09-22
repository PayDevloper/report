<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.sumao.model.Paynewaccountingrules"%>
<%
String userrole=(String)session.getAttribute("userrole");  //登录用户岗位名称
%>
<!DOCTYPE html>
<html>
<head>

<title>计费配置记录管理</title>
<jsp:include page="../../common/meta.jsp"></jsp:include>
<jsp:include page="../../common/easyui.jsp"></jsp:include>
<script type="text/javascript" charset="UTF-8">
	var datagrid;
	var userDialog;
	var userDialogedit;
	var userForm;
	var userFormedit;
	var i=0;
	var x=0;
	
	$(function() {
		userForm = $('#userForm').form();
		userFormedit = $('#userFormedit').form();

		userDialog = $('#userDialog').show().dialog({
			modal : true,
			title : '新建信息',
			buttons : [ {
				text : '确定',
				handler : function() {
					//首先判断截止日期是否应晚于开始日期
					var start = $('#effectivetime').datebox('getValue');
					var end = $('#invalidtime').datebox('getValue');
					var res=true;
					if (start<=end){
						var mkid=userForm.find('[name=marketingid]').val();
						var charty=userForm.find('[name=chargingparty]').val();
						var trad=userForm.find('[name=tradingpatterns]').val();
						var charde=userForm.find('[name=chargingmode]').val();
						var payid=userForm.find('[name=id]').val();		//为防止编辑时调用该方法，在数据库中比对时与自身进行比对，因编辑后的数据还未保存
						var charging = $('#chargingmode').combobox('getValue');
						var allstartthsh="";	//全部阈值建立数组
						var allendthsh="";
						if (charging=='线性收费阶梯退费'){
							var charg = $('#chargingdimensionalitysf').combobox('getValue');	//退费中收费配置
							for(j=0;j<i;j++){
								if(document.getElementById("startthresholdunit"+j)!=null){	//查看该行是否被删除
								var startth = document.getElementById("startthresholdunit"+j);
								var endth = document.getElementById("endthresholdunit"+j);
								var stepmo = document.getElementById("stepmode"+j);
								var startthsh = document.getElementById("startthreshold"+j);	//验证阈值大小
								var endthsh = document.getElementById("endthreshold"+j);
								var eth=endthsh.value;
								if(eth=="#"){
									eth="99999999";
								}
								var chargstartth="元/"+startth.value;	//为了与收费配置单位一致，从而进行比较
									if(Number(startthsh.value)<=Number(eth)&&charg==chargstartth&&startth.value==endth.value&&endth.value==stepmo.value&&startth.value==stepmo.value){	
									}else{	
										res=false;
									}
									if(res==true){
									//判断阈值交叉
						       		$.ajax({
									    url: 'payeditaccountingrulesController.do?provingshold&mkid='+mkid+'&charty='+charty+'&start='+start+'&end='+end+'&startsh='+startthsh.value+'&endsh='+eth+'&trad='+trad+'&charde='+charde+'&payid='+payid,
									    async: false,
									    success: function(datas){
									    	if(datas.result=="success"){
													}else{
														res=false;
													}
									    }
									});
						       		}
									allstartthsh=allstartthsh+","+startthsh.value;
									allendthsh=allendthsh+","+eth;
								}
							}
							var allstar=allstartthsh.substring(1,allstartthsh.length).split(",");
							var allend=allendthsh.substring(1,allendthsh.length).split(",");
							allstar=allstar.sort(sortNumber);
							allend=allend.sort(sortNumber);
							if(res==true&&allstar.length>=2){
							for(k=1;k<allstar.length;k++){
								if (allstar[k] <= allend[k-1]){
									res=false;
								}
							}}
						}else if(charging=='阶梯收费'){
							var yanzheng="";
							for(j=0;j<x;j++){
								if(document.getElementById("startthresholdunitsf"+j)!=null){	//查看该行是否被删除
								var startth = document.getElementById("startthresholdunitsf"+j);
								var endth = document.getElementById("endthresholdunitsf"+j);
								var stepmo = document.getElementById("stepmodesf"+j);
								var startthsh = document.getElementById("startthresholdsf"+j);	//验证阈值大小
								var endthsh = document.getElementById("endthresholdsf"+j);
								var eth=endthsh.value;
								if(eth=="#"){
									eth="99999999";
								}
								if(yanzheng==""){		//用来判断各行之间维度是否相同，当做一个记录
									yanzheng=startth.value;
								}
								if(Number(startthsh.value)<=Number(eth)&&yanzheng==startth.value&&startth.value==endth.value&&endth.value==stepmo.value&&startth.value==stepmo.value){	
								}else{	
									res=false;
								}
								yanzheng=startth.value;
								if(res==true){
								//判断阈值交叉
									$.ajax({
									    url: 'payeditaccountingrulesController.do?provingshold&mkid='+mkid+'&charty='+charty+'&start='+start+'&end='+end+'&startsh='+startthsh.value+'&endsh='+eth+'&trad='+trad+'&charde='+charde+'&payid='+payid,
									    async: false,
									    success: function(datas){
									    	if(datas.result=="success"){
													}else{
														res=false;
													}
									    }
									});
					       		}
								allstartthsh=allstartthsh+","+startthsh.value;
								allendthsh=allendthsh+","+eth;
							}
							}
							var allstar=allstartthsh.substring(1,allstartthsh.length).split(",");
							var allend=allendthsh.substring(1,allendthsh.length).split(",");
							allstar=allstar.sort(sortNumber);	//按大小排序
							allend=allend.sort(sortNumber);
							if(res==true&&allstar.length>=2){
							for(k=1;k<allstar.length;k++){
								if (allstar[k] <= allend[k-1]){
									res=false;
								}
							}}
						}
					if(res==true){
						//再判断该销售组织中相应规则是否已审核通过，如果通过，则生效时间段不可有交叉						
					$.get("payeditaccountingrulesController.do?provingtime&mkid="+mkid+"&charty="+charty+"&start="+start+"&end="+end+"&trad="+trad+"&charde="+charde+"&payid="+payid,function(data){							
					if(data.result=="success"){
					if (userForm.find('[name=id]').val() != '') {
						userForm.form('submit', {
							url : 'payeditaccountingrulesController.do?edit',
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
						document.getElementById("maxnumsf").value=x;
						document.getElementById("maxnum").value=i;
						userForm.form('submit', {
							url : 'payeditaccountingrulesController.do?add',
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
					    	}else{
								$.messager.alert('提示', '相同销售组织及其他配置时，生效时间段不应有交叉，请调整', '提示');
					    		return false;
				     		  }
			       			})
					}else{
						$.messager.alert('提示', '计量单位维度需一致并且阈值配置不可存在交叉和大小值颠倒', '提示');
						return false;
						}
				}else{
					$.messager.alert('提示', '截止日期应晚于开始日期，请调整', '提示');
					return false;
					}
				}
			} ]
		}).dialog('close');

//编辑、继承功能保存
		userDialogedit = $('#userDialogedit').show().dialog({
			modal : true,
			title : '编辑信息',
			buttons : [ {
				text : '确定',
				handler : function() {
					//首先判断截止日期是否应晚于开始日期
					var starts = $('#effectivetimes').datebox('getValue');
					var ends = $('#invalidtimes').datebox('getValue');
					if (starts<=ends){
						var startth = $('#startthresholdunit').combobox('getValue');
						var endth = $('#endthresholdunit').combobox('getValue');
						var stepmo = $('#stepmode').combobox('getValue');
						if (startth==endth&&startth==stepmo&&endth==stepmo){	//判断计量单位维度
							var startsh=userFormedit.find('[name=startthreshold]').val();
							var endsh=userFormedit.find('[name=endthreshold]').val();
							if(endsh=="#"){
								endsh="99999999";
							}
							if(Number(startsh)<=Number(endsh)){		//判断阈值大小值
							//再判断该销售组织中相应规则生效时间段不可有交叉
							var mkided=userFormedit.find('[name=marketingid]').val();
							var chartyed=userFormedit.find('[name=chargingparty]').val();
							var traded=userFormedit.find('[name=tradingpatterns]').val();
							var chardeed=userFormedit.find('[name=chargingmodes]').val();
							var payided=userFormedit.find('[name=id]').val();		//为防止编辑时调用该方法，在数据库中比对时与自身进行比对，因编辑后的数据还未保存	
						//判断生效时间交叉
						$.get("payeditaccountingrulesController.do?provingtime&mkid="+mkided+"&charty="+chartyed+"&start="+starts+"&end="+ends+"&trad="+traded+"&charde="+chardeed+"&payid="+payided,function(data){							
						if(data.result=="success"){						
							//判断阈值交叉
							$.get("payeditaccountingrulesController.do?provingshold&mkid="+mkided+"&charty="+chartyed+"&start="+starts+"&end="+ends+"&startsh="+startsh+"&endsh="+endsh+"&trad="+traded+"&charde="+chardeed+"&payid="+payided,function(dataq){							
								if(dataq.result=="success"){
						if (userFormedit.find('[name=id]').val() != '') {
							userFormedit.form('submit', {
							url : 'payeditaccountingrulesController.do?edit',
							success : function(data) {
								userDialogedit.dialog('close');
								$.messager.show({
									msg : '信息编辑成功！',
									title : '提示'
								});
								datagrid.datagrid('reload');
								}
							});
						} else {
							userFormedit.form('submit', {
							url : 'payeditaccountingrulesController.do?inherit',
							success : function(data) {
								try {
									var d = $.parseJSON(data);
									if (d) {
										userDialogedit.dialog('close');
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
							}else{
									$.messager.alert('提示', '相同销售组织及其他配置时，阈值不应有交叉，请调整', '提示');
						    		return false;
					     		}
				       		})
						}else{
							$.messager.alert('提示', '相同销售组织及其他配置时，生效时间段不应有交叉，请调整', '提示');
				    		return false;
			     		  }
		       			})
						}else {
							$.messager.alert('提示', '阈值最小值必须小于阈值最大值', '提示');
							return false;
						}
						}else {
							$.messager.alert('提示', '需保持计量单位维度一致性', '提示');
							return false;
						}
					}else{
					$.messager.alert('提示', '截止日期应晚于开始日期，请调整', '提示');
					return false;
					}
				}
			} ]
		}).dialog('close');
		//创建列表 
		/*
收费对象,交易模式,计量维度，维度单位，阶梯价格，时间周期开始，时间周期结束，阙值设置开始，阙值开始单位，阙值设置结束，阙值结束单位，阶梯方式,数量
chargingparty,tradingpatterns,dimensionparameter，chargingdimensionality,ladderprice，effectivetime，invalidtime，
startthreshold，startthresholdunit，endthreshold，endthresholdunit，stepmode,stepmodenum
			*/
		datagrid = $('#datagrid').datagrid({
			url : 'payeditaccountingrulesController.do?datagridseach',
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
				width : 100,
				sortable : true
			} ] ],
			columns : [ [ {
				field : 'marketing',
				title : '销售组织',
				width : 100,
				sortable : true
			}, {
				field : 'marketingid',
				title : '销售组织ID',
				width : 100,
				sortable : true
			}, {
				title : 'ID',
				field : 'id',
				width : 50,
				sortable : true
			}, {
				field : 'tradingpatterns',
				title : '交易模式',
				width : 100,
				sortable : true
			}, {
				field : 'dimensionparameter',
				title : '收费单价',
				width : 100,
				sortable : true
			}, {
				field : 'chargingdimensionality',
				title : '收费维度',
				width : 100,
				sortable : true
			}, {
				field : 'chargingmode',
				title : '计费模式',
				width : 100,
				sortable : true
			}, {
				field : 'effectivetime',
				title : '生效期开始',
				width : 100,
				sortable : true
			}, {
				field : 'invalidtime',
				title : '生效期结束',
				width : 100,
				sortable : true
			}, {
				field : 'startthreshold',
				title : '阈值设置开始',
				width : 50,
				sortable : true
			}, {
				field : 'startthresholdunit',
				title : '阈值开始单位',
				width : 50,
				sortable : true
			}, {
				field : 'endthreshold',
				title : '阈值设置结束',
				width : 50,
				sortable : true
			}, {
				field : 'endthresholdunit',
				title : '阈值结束单位',
				width : 50,
				sortable : true
			}, {
				field : 'stepmodenum',
				title : '费用单价',
				width : 50,
				sortable : true
			}, {
				field : 'stepmode',
				title : '费用维度',
				width : 50,
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
	
	//数据继承
    function inherit(){
		var rows = datagrid.datagrid('getSelections');
		if (rows.length != 1 && rows.length != 0) {
			var names = [];
			for ( var i = 0; i < rows.length; i++) {
				names.push(rows[i].name);
			}
			$.messager.show({
				msg : '只能选择一条数据进行继承！您已经选择了【' + names.join(',') + '】' + rows.length + '个',
				title : '提示'
			});
		} else if (rows.length == 1) {
			/*tradingpatterns,dimensionparameter,chargingdimensionality,chargingparty,chargingmode,effectivetime,
invalidtime,startthreshold,startthresholdunit,endthreshold,endthresholdunit,stepmodenum,stepmode
			*/
			userDialogedit.dialog('open');
			userFormedit.form('clear');
			userFormedit.form('load', {
				seller : rows[0].seller,
				marketing : rows[0].marketing,
				marketingid : rows[0].marketingid,
				tradingpatterns : rows[0].tradingpatterns,
				chargingdimensionality : rows[0].chargingdimensionality,
				dimensionparameter : rows[0].dimensionparameter,
				chargingparty : rows[0].chargingparty,
				chargingmodes : rows[0].chargingmode,
				effectivetimes : rows[0].effectivetime,
				invalidtimes : rows[0].invalidtime,
				startthreshold : rows[0].startthreshold,
				startthresholdunit : rows[0].startthresholdunit,
				endthreshold : rows[0].endthreshold,
				endthresholdunit : rows[0].endthresholdunit,
				stepmode : rows[0].stepmode,
				stepmodenum : rows[0].stepmodenum,
				approve : rows[0].approve,
				remark : rows[0].remark
			});
		}
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
			//if (rows[0].approve=='已通过')
	    	//   {
	    	  //  $.messager.alert('提示', '已审核后计费规则不可以再进行修改操作', '提示');
	    	//   }
	      // else
	    	 //  {
				userFormedit.find('[name=marketingid]').attr('readonly', 'readonly');
				userDialogedit.dialog('open');
				userFormedit.form('clear');
				/*
	tradingpatterns,dimensionparameter,chargingdimensionality,chargingparty,chargingmode,effectivetime,
	invalidtime,startthreshold,startthresholdunit,endthreshold,endthresholdunit,stepmodenum,stepmode
				*/
				userFormedit.form('load', {
					id : rows[0].id,
					seller : rows[0].seller,
					marketing : rows[0].marketing,
					marketingid : rows[0].marketingid,
					chargingparty : rows[0].chargingparty,
					tradingpatterns : rows[0].tradingpatterns,
					dimensionparameter : rows[0].dimensionparameter,
					chargingdimensionality : rows[0].chargingdimensionality,
					chargingmodes : rows[0].chargingmode,
					effectivetimes : rows[0].effectivetime,
					invalidtimes : rows[0].invalidtime,
					startthreshold : rows[0].startthreshold,
					startthresholdunit : rows[0].startthresholdunit,
					endthreshold : rows[0].endthreshold,
					endthresholdunit : rows[0].endthresholdunit,
					stepmode : rows[0].stepmode,
					stepmodenum : rows[0].stepmodenum,
					approve : rows[0].approve,
					remark : rows[0].remark
					
				});
			
			//}
		}

	}

	function remove() {
		var ids = [];
		var rows = datagrid.datagrid('getSelections');
		if (rows.length > 0) {
			$.messager.confirm('请确认', '您要删除当前所选项？', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						if (rows[i].approve=='已通过')
				    	   {
				    	    $.messager.alert('提示', '已审核后计费规则不可以再进行删除操作', '提示');
				    	   }
				       else
				    	   {
							ids.push(rows[i].id);
				    	   }
					}
					if (ids.length > 0) {
						$.ajax({
							url : 'payeditaccountingrulesController.do?del',
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
					else {
						$.messager.alert('提示', '请选择未审核的需要删除的记录！', 'error');
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
			$.messager.confirm('请确认', '您要审核当前所选项？', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].id);
					}
					$.ajax({
						url : 'payeditaccountingrulesController.do?editaudit',
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
								msg : '审核成功！'
							});
						}
					});
				}
			});
		} else {
			$.messager.alert('提示', '请选择要审核的记录！', 'error');
		}
	}

	//审核不通过
	function editauditno() {
		var ids = [];
		var rows = datagrid.datagrid('getSelections');
		if (rows.length > 0) {
			$.messager.confirm('请确认', '您要审核当前所选项？', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].id);
					}
					$.ajax({
						url : 'payeditaccountingrulesController.do?editauditno',
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
								msg : '审核不通过！'
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
		//effectivetime，invalidtime，tradingpatterns，approve，chargingmode
		datagrid.datagrid('load', {
			seller : $('#seller').val(),
			marketing : $('#marketing').val(),
			marketingid : $('#toolbar input[name=marketingid]').val(),
			effectivetime : $('#toolbar input[name=effectivetime]').val(),
			invalidtime : $('#toolbar input[name=invalidtime]').val(),
			tradingpatterns : $('#toolbar input[name=tradingpatterns]').val(),
			chargingmode : $('#toolbar input[name=chargingmode]').val()
		});
	}
	
	//判断是否计量维度一致JS
	function testClk()
	{
		/*
		单位：单、吨
		*/
		var startth = $('#startthresholdunit').combobox('getValue');
		var endth = $('#endthresholdunit').combobox('getValue');
		var stepmo = $('#stepmode').combobox('getValue');
		if (startth==endth&&startth==stepmo&&endth==stepmo){
		}else {
			$.messager.alert('提示', '需保持计量单位维度一致性', '提示');
		}
		
	}
	
	//计费模式改变JS
	function payedit()
	{
		/*
		线性收费，线性收费阶梯退费，阶梯收费
		*/
		var cont = $('#chargingmode').combobox('getValue');
		if (cont=='线性收费')
			{
			document.getElementById("tabpay1").style.display='block';
			document.getElementById("tabpay2").style.display='none';
			document.getElementById("tabpay3").style.display='none';
			}
		else if (cont=='线性收费阶梯退费')
			{
			document.getElementById("tabpay1").style.display='none';
			document.getElementById("tabpay2").style.display='block';
			document.getElementById("tabpay3").style.display='none';
			}
		else if (cont=='阶梯收费')
			{
			document.getElementById("tabpay1").style.display='none';
			document.getElementById("tabpay2").style.display='none';
			document.getElementById("tabpay3").style.display='block';
			}
	}
	
	function clearFun() {
		$('#toolbar input').val('');
		datagrid.datagrid('load', {});
	}
	
	function sortNumber(a,b)
	{
	return a - b
	}
</script>
</head>
<body class="easyui-layout" fit="true">
	<div region="center" border="false" style="overflow: hidden;">
		<div id="toolbar" class="datagrid-toolbar" style="height: auto;">
			<fieldset>
				<legend>计费配置记录管理</legend>
				<table class="tableForm">
					
					<tr>
						<tr>
						<th>卖家</th>
						<td><select id="seller" name="seller" style="width:200px;" onchange="selSeller()">
						<option value="">空</option>
					</select></td>
						<th>销售组织</th>
						<td><select id="marketing" name="marketing" style="width:200px;">
						<option value="">空</option>
					</select></td>
						<th>销售组织ID</th>
						<td><input name="marketingid" style="width: 140px;" />
						</td>
					</tr>
					<tr>
						<th>交易模式</th>
						<td><select class="easyui-combobox" name="tradingpatterns" style="width:150px;">
						<option value="">全部</option>
						<option value="现货交易">现货交易</option>
						<option value="客服交易">客服交易</option>
						<option value="公开竞拍">公开竞拍</option>
						<option value="密封竞拍">密封竞拍</option>
						<option value="团购交易">团购交易</option>
						<option value="预售交易">预售交易</option>
					</select></td>
					
						<th>计费模式</th>
						<td><select class="easyui-combobox" name="chargingmode" style="width:150px;">
						<option value="" selected="true">全部</option>
						<option value="线性收费">线性收费</option>
						<option value="线性收费阶梯退费">线性收费阶梯退费</option>
						<option value="阶梯收费">阶梯收费</option>
					</select>
						</td>
					</tr>
					<tr>
					<th>查看配置日期</th>
						<td colspan="4"><input name="effectivetime" class="easyui-datebox" style="width: 140px;" />
					至<input name="invalidtime" class="easyui-datebox" style="width: 140px;" /></td>
						<td colspan="3"><a class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchFun();" href="javascript:void(0);">查找</a><a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="clearFun();" href="javascript:void(0);">清空</a></td>
					</tr>
				</table>
			</fieldset>
		</div>
		<table id="datagrid"></table>
	</div>

	<div id="userDialog" style="display: none;overflow: hidden;">
		<form id="userForm" method="post">
			<input name='id' style="display:none" value="" />
			
			<table class="tableForm" style="width:700px;">
				<tr>
					<th>卖家</th>
					<td>
					<input name="seller" class="easyui-validatebox"  required="true" />
					</td>
					<th>销售组织</th>
					<td><input name="marketing" class="easyui-validatebox"  required="true" />
					</td>
				</tr>
				<tr>
					<th>销售组织ID</th>
					<td><input name="marketingid" class="easyui-validatebox"  required="true" />
					</td>
					<th>收费对象</th>
					<td>
					<select class="easyui-combobox" name="chargingparty" style="width:150px;" required="true">
						<option value="卖方">卖方</option>
						<option value="买方">买方</option>
						<option value="买卖双方">买卖双方</option>
					</select>
					</td>
				</tr>
				<tr>
					<th>配置有效期</th>
					<td colspan=3>
					<input name="effectivetime" id="effectivetime" class="easyui-datebox" style="width: 140px;" required="true"/>
					至<input name="invalidtime" id="invalidtime" class="easyui-datebox" style="width: 140px;" required="true"/>
					</td>
				</tr>
					<th>计费模式</th>
					<td>
					<select class="easyui-combobox" name="chargingmode" id="chargingmode" data-options="onSelect:payedit" style="width:150px;" required="true">
						<option value="线性收费">线性收费</option>
						<option value="线性收费阶梯退费">线性收费阶梯退费</option>
						<option value="阶梯收费">阶梯收费</option>
					</select>
					</td>
				</tr>
				</table>
				<!-- 线性收费子表内容 -->
				<table class="tableForm" style="width:700px;" id="tabpay1" name="tabpay1">
					<tr>
						<th>收费配置</th>
						<td><input name="dimensionparameter" class="easyui-numberbox" style="width:100px;" />
						<select class="easyui-combobox" name="chargingdimensionality" style="width:80px;">
							<option value="元/单">元/单</option>
							<option value="元/吨">元/吨</option>
							<option value="元/场次">元/场次</option>
						</select>
						</td>
					</tr>
				</table>
				<!-- 阶梯退费 以下为子表内容 -->
				<table class="tableForm" id="tabpay2" name="tabpay2" style="width:700px;display:none">
				<tr>
						<th>收费配置</th>
						<td><input name="dimensionparametersf" class="easyui-numberbox" style="width:100px;" />
						<select class="easyui-combobox" name="chargingdimensionalitysf" id="chargingdimensionalitysf" style="width:80px;">
							<option value="元/单">元/单</option>
							<option value="元/吨">元/吨</option>
							<option value="元/场次">元/场次</option>
						</select>
						</td>
					</tr>
				<tr>
					<td align=left>退费配置</td>
					<td align=right><a class="easyui-linkbutton" iconCls="icon-add" onclick="addtitle();" plain="true" href="javascript:void(0);">添加子表</a></td>
				</tr>
				<tr><td colspan=2><input name="maxnum" id="maxnum" style="display:none" >
			<table id="newtable" class='tableForm' style="width:700px"><tbody>
			 </tbody></table>
					<!-- 插入新子表位置 -->
				</td></tr>

				</table>
				
				<table class="tableForm" id="tabpay3" name="tabpay3" style="width:700px;display:none">
				<tr>
					<td align=left>收费配置</td>
					<td align=right><a class="easyui-linkbutton" iconCls="icon-add" onclick="addtitle3();" plain="true" href="javascript:void(0);">添加子表</a></td>
				</tr>
				<tr><td colspan=2><input name="maxnumsf" id="maxnumsf" style="display:none" >
				<table id="newsftable" class='tableForm' style="width:700px"><tbody>
			 </tbody></table>	<!-- 插入新子表位置 -->
				</td></tr>
				</table>

			<table class="tableForm"> 
				<tr>
					<th>备注</th>
					<td>
					<input class="easyui-textbox" name="remark" data-options="multiline:true" 
					value="" style="width:500px;height:50px">
					
					</td>
				</tr>
				
			</table>
			
		</form>
	</div>
	
	<!-- 编辑、继承FORM -->
	<div id="userDialogedit" style="display: none;overflow: hidden;">
		<form id="userFormedit" method="post">
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
					<th>收费对象</th>
					<td>
					<select class="easyui-combobox" name="chargingparty" style="width:150px;" required="true">
						<option value="卖方">卖方</option>
						<option value="买方">买方</option>
						<option value="买卖双方">买卖双方</option>
					</select>
					</td>
				</tr>
				<tr>
					<th>计费模式</th>
					<td>
					<select class="easyui-combobox" name="chargingmodes" style="width:150px;" required="true">
						<option value="线性收费">线性收费</option>
						<option value="线性收费阶梯退费">线性收费阶梯退费</option>
						<option value="阶梯收费">阶梯收费</option>
					</select>
					</td>
				</tr>
				<tr>
						<th>收费配置</th>
						<td><input name="dimensionparameter" class="easyui-validatebox" style="width:100px;" />
						<select class="easyui-combobox" name="chargingdimensionality" style="width:80px;">
							<option value="元/单">元/单</option>
							<option value="元/吨">元/吨</option>
							<option value="元/场次">元/场次</option>
						</select>
						</td>
				</tr>
				<tr>
					<th>配置有效期</th>
					<td><input name="effectivetimes" id="effectivetimes" class="easyui-datebox" style="width: 140px;" required="true"/>
					至<input name="invalidtimes" id="invalidtimes" class="easyui-datebox" style="width: 140px;" required="true"/>
					</td>
				</tr>
				<tr>
					<th>阙值设置</th>
					<td><input name="startthreshold" style="width: 60px;" />
					<select class="easyui-combobox" name="startthresholdunit" id="startthresholdunit" style="width:40px;" data-options="onSelect:testClk">
						<option value="单">单</option>
						<option value="吨">吨</option>
					</select>
					<=达成量<=<input name="endthreshold" style="width: 60px;" />
					<select class="easyui-combobox" name="endthresholdunit" id="endthresholdunit" style="width:40px;" data-options="onSelect:testClk"> 
						<option value="单">单</option>
						<option value="吨">吨</option>
					</select>
					</td>
				</tr>
				<tr>
					<th>费用单价</th>
					<td><input name="stepmodenum" id="stepmodenum" class="easyui-numberbox" style="width: 60px;" />
					<select class="easyui-combobox" name="stepmode" id="stepmode" style="width:40px;" data-options="onSelect:testClk">
						<option value="单">单</option>
						<option value="吨">吨</option>
					</select>
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
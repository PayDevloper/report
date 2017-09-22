<%@ page language="java" pageEncoding="UTF-8"%>
<%@	page import="com.sumao.model.Paynewaccountingrules"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String userrole=(String)session.getAttribute("userrole");  //登录用户岗位名称

 String pmarketingid=(String)request.getAttribute("pmarketingid");
 Paynewaccountingrules newaccount=(Paynewaccountingrules)request.getAttribute("newaccount");

%>
<!DOCTYPE html>
<html>
<head>

<title>计费规则管理</title>
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
						
						//var charging = $('#chargingmode').combobox('getValue');
						var charging =$("#chargingmode").val();
						
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
								userForm.form('clear');
								datagrid.datagrid('reload');
							}
						});
					} else {
						document.getElementById("maxnumsf").value=x;
						document.getElementById("maxnum").value=i;
						userForm.form('submit', {
							url : 'payeditaccountingrulesController.do?add',
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
			url : 'payeditaccountingrulesController.do?datagrid',
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
				width : 100,
				sortable : true
			}, {
				field : 'marketingid',
				title : '销售组织ID',
				width : 100,
				sortable : true
			}, {
				field : 'chargingparty',
				title : '收费对象',
				width : 100,
				sortable : true
			}, {
				field : 'tradingpatterns',
				title : '交易模式',
				width : 100,
				sortable : true
			}, {
				field : 'dimensionparameter',
				title : '收费配置',
				width : 100,
				sortable : true
			}, {
				field : 'chargingdimensionality',
				title : '配置维度',
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
				width : 100,
				sortable : true
			}, {
				field : 'startthresholdunit',
				title : '阈值开始单位',
				width : 100,
				sortable : true
			}, {
				field : 'endthreshold',
				title : '阈值设置结束',
				width : 100,
				sortable : true
			}, {
				field : 'endthresholdunit',
				title : '阈值结束单位',
				width : 100,
				sortable : true
			}, {
				field : 'stepmodenum',
				title : '费用单价',
				width : 100,
				sortable : true
			}, {
				field : 'stepmode',
				title : '费用维度',
				width : 100,
				sortable : true
			}, {
				field : 'approve',
				title : '审核状态',
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

	});

	function append() {
		userDialog.dialog('open');
		//userForm.form('clear');
		userForm.form('load', {
			seller : '<%=newaccount.getSeller() %>',
			marketing : '<%=newaccount.getMarketing() %>',
			marketingid : '<%=newaccount.getMarketingid() %>'
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
			var marketingid=$('#toolbar input[name=pmarketingid]').val();
			$("#eebscategory1 option[value!='']").remove();//删除四级下拉列表内容
			$("#eebscategory2 option[value!='']").remove();
			$("#eebscategory3 option[value!='']").remove();	
			$("#egradenumber option[value!='']").remove();
			if(rows[0].startebscategory1==1){
				$.ajax({
					type :"post",
				    url: "payeditaccountingrulesController.do?findoneebs&marketingid="+marketingid,
				    async: false,
				    dataType : "json",
				    success: function(data1){
				    	for (var i=0;i<data1.length;i++){
				    		$('#eebscategory1').append("<option value='"+data1[i].ebscategory1+"'>"+data1[i].remark1+"</option>");
						}
				    }
				});
				if(rows[0].startebscategory2==1){
					var ebsname1=rows[0].ebscategory1;
					$.ajax({
						type :"post",
					    url: "payeditaccountingrulesController.do?findtwoebs&ebsname="+ebsname1+"&marketingid="+marketingid,
					    async: false,
					    dataType : "json",
					    success: function(data2){
					    	for (var i=0;i<data2.length;i++){
					    		$('#eebscategory2').append("<option value='"+data2[i].ebscategory2+"'>"+data2[i].remark1+"</option>");
							}
					    }
					});
					if(rows[0].startebscategory3==1){
						var ebsname2=rows[0].ebscategory2;
						$.ajax({
							type :"post",
						    url: "payeditaccountingrulesController.do?findthreeebs&ebsname="+ebsname2+"&marketingid="+marketingid,
						    async: false,
						    dataType : "json",
						    success: function(data3){
						    	for (var i=0;i<data3.length;i++){
						    		$('#eebscategory3').append("<option value='"+data3[i].ebscategory3+"'>"+data3[i].remark1+"</option>");
								}
						    }
						});
						if(rows[0].startgradenumber==1){
							var ebsname3=rows[0].ebscategory3;
							$.ajax({
								type :"post",
							    url: "payeditaccountingrulesController.do?findgradeebs&ebsname="+ebsname3+"&marketingid="+marketingid,
							    async: false,
							    dataType : "json",
							    success: function(data4){
							    	for (var i=0;i<data4.length;i++){
							    		$('#egradenumber').append("<option value='"+data4[i].gradenumber+"'>"+data4[i].gradenumber+"</option>");
									}
							    }
							});
						}
					}
				}
			}
			userFormedit.form('load', {
				seller : rows[0].seller,
				marketing : rows[0].marketing,
				marketingid : rows[0].marketingid,
				tradingpatterns : rows[0].tradingpatterns,
				startebscategory1 : rows[0].startebscategory1,
				ebscategory1 : rows[0].ebscategory1,
				startebscategory2 : rows[0].startebscategory2,
				ebscategory2 : rows[0].ebscategory2,
				startebscategory3 : rows[0].startebscategory3,
				ebscategory3 : rows[0].ebscategory3,
				startgradenumber : rows[0].startgradenumber,
				gradenumber : rows[0].gradenumber,
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
				var marketingid=$('#toolbar input[name=pmarketingid]').val();
				$("#eebscategory1 option[value!='']").remove();//删除四级下拉列表内容
				$("#eebscategory2 option[value!='']").remove();
				$("#eebscategory3 option[value!='']").remove();	
				$("#egradenumber option[value!='']").remove();
				if(rows[0].startebscategory1==1){
					$.ajax({
						type :"post",
					    url: "payeditaccountingrulesController.do?findoneebs&marketingid="+marketingid,
					    async: false,
					    dataType : "json",
					    success: function(data1){
					    	for (var i=0;i<data1.length;i++){
					    		$('#eebscategory1').append("<option value='"+data1[i].ebscategory1+"'>"+data1[i].remark1+"</option>");
							}
					    }
					});
					if(rows[0].startebscategory2==1){
						var ebsname1=rows[0].ebscategory1;
						$.ajax({
							type :"post",
						    url: "payeditaccountingrulesController.do?findtwoebs&ebsname="+ebsname1+"&marketingid="+marketingid,
						    async: false,
						    dataType : "json",
						    success: function(data2){
						    	for (var i=0;i<data2.length;i++){
						    		$('#eebscategory2').append("<option value='"+data2[i].ebscategory2+"'>"+data2[i].remark1+"</option>");
								}
						    }
						});
						if(rows[0].startebscategory3==1){
							var ebsname2=rows[0].ebscategory2;
							$.ajax({
								type :"post",
							    url: "payeditaccountingrulesController.do?findthreeebs&ebsname="+ebsname2+"&marketingid="+marketingid,
							    async: false,
							    dataType : "json",
							    success: function(data3){
							    	for (var i=0;i<data3.length;i++){
							    		$('#eebscategory3').append("<option value='"+data3[i].ebscategory3+"'>"+data3[i].remark1+"</option>");
									}
							    }
							});
							if(rows[0].startgradenumber==1){
								var ebsname3=rows[0].ebscategory3;
								$.ajax({
									type :"post",
								    url: "payeditaccountingrulesController.do?findgradeebs&ebsname="+ebsname3+"&marketingid="+marketingid,
								    async: false,
								    dataType : "json",
								    success: function(data4){
								    	for (var i=0;i<data4.length;i++){
								    		$('#egradenumber').append("<option value='"+data4[i].gradenumber+"'>"+data4[i].gradenumber+"</option>");
										}
								    }
								});
							}
						}
					}
				}
				userFormedit.form('load', {
					id : rows[0].id,
					seller : rows[0].seller,
					marketing : rows[0].marketing,
					marketingid : rows[0].marketingid,
					chargingparty : rows[0].chargingparty,
					tradingpatterns : rows[0].tradingpatterns,
					startebscategory1 : rows[0].startebscategory1,
					ebscategory1 : rows[0].ebscategory1,
					startebscategory2 : rows[0].startebscategory2,
					ebscategory2 : rows[0].ebscategory2,
					startebscategory3 : rows[0].startebscategory3,
					ebscategory3 : rows[0].ebscategory3,
					startgradenumber : rows[0].startgradenumber,
					gradenumber : rows[0].gradenumber,
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

	function funremove() {
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
			$.messager.confirm('请确认', '您要将当前所选项审核状态改为已通过吗？', function(r) {
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
						url : 'payeditaccountingrulesController.do?editauditno',
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
	
	function searchFun() {
		//effectivetime，invalidtime，tradingpatterns，approve，chargingmode
		datagrid.datagrid('load', {
			pmarketingid : $('#toolbar input[name=pmarketingid]').val(),
			effectivetime : $('#toolbar input[name=effectivetime]').val(),
			invalidtime : $('#toolbar input[name=invalidtime]').val(),
			tradingpatterns : $('#toolbar input[name=tradingpatterns]').val(),
			approve : $('#toolbar input[name=approve]').val(),
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
		var cont =$("#chargingmode").val();   // $('#chargingmode').combobox('getValue');
		
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
		$('#toolbar input[name=effectivetime]').val('');
		$('#toolbar input[name=invalidtime]').val('');
		$('#toolbar input[name=tradingpatterns]').val('');
		$('#toolbar input[name=approve]').val('');
		$('#toolbar input[name=chargingmode]').val('');
		datagrid.datagrid('load', {
			pmarketingid : $('#toolbar input[name=pmarketingid]').val(),
			effectivetime : $('#toolbar input[name=effectivetime]').val(),
			invalidtime : $('#toolbar input[name=invalidtime]').val(),
			tradingpatterns : $('#toolbar input[name=tradingpatterns]').val(),
			approve : $('#toolbar input[name=approve]').val(),
			chargingmode : $('#toolbar input[name=chargingmode]').val()
		});
	}
	
	function sortNumber(a,b){
		return a - b
	}
	
	//启用牌号维度时JS，st值为1至4时是新建时，其他为编辑时
	function enablest(st){
		var se1=document.getElementsByName("startebscategory1");
		var se2=document.getElementsByName("startebscategory2");
		var se3=document.getElementsByName("startebscategory3");
		var se4=document.getElementsByName("startgradenumber");
		var marketingid=$('#toolbar input[name=pmarketingid]').val();
		if(st==1){
			$("#ebscategory1 option[value!='']").remove();	//防止重复点击启用出现重复数据
			//通过ajax得到启用维度相对应的下拉列表内容
			$.ajax({
				type :"post",
			    url: "payeditaccountingrulesController.do?findoneebs&marketingid="+marketingid,
			    async: false,
			    dataType : "json",
			    success: function(data1){
			    	for (var i=0;i<data1.length;i++){
			    		$('#ebscategory1').append("<option value='"+data1[i].ebscategory1+"'>"+data1[i].remark1+"</option>");
					}
			    }
			});
		}else if(st==2){
			//判断上一级牌号是否启用
			if(se1[1].checked!=true){
				$.messager.alert('提示', '需先启用大类维度', '提示');
				se2[0].checked=true;
			}else{
				$("#ebscategory2 option[value!='']").remove();	//防止重复点击启用出现重复数据
				var ebsname1=$("#ebscategory1").val();	//得到上一级维度下拉列表选择的信息
				//通过ajax得到启用维度相对应的下拉列表内容
				$.ajax({
					type :"post",
				    url: "payeditaccountingrulesController.do?findtwoebs&ebsname="+ebsname1+"&marketingid="+marketingid,
				    async: false,
				    dataType : "json",
				    success: function(data2){
				    	for (var i=0;i<data2.length;i++){
				    		$('#ebscategory2').append("<option value='"+data2[i].ebscategory2+"'>"+data2[i].remark1+"</option>");
						}
				    }
				});
			}
		}else if(st==3){
			//判断上两级牌号是否启用
			if(se1[1].checked!=true||se2[1].checked!=true){
				$.messager.alert('提示', '需先启用中类维度', '提示');
				se3[0].checked=true;
			}else{
				$("#ebscategory3 option[value!='']").remove();	//防止重复点击启用出现重复数据
				var ebsname2=$("#ebscategory2").val();	//得到上一级维度下拉列表选择的信息
				//通过ajax得到启用维度相对应的下拉列表内容
				$.ajax({
					type :"post",
				    url: "payeditaccountingrulesController.do?findthreeebs&ebsname="+ebsname2+"&marketingid="+marketingid,
				    async: false,
				    dataType : "json",
				    success: function(data3){
				    	for (var i=0;i<data3.length;i++){
				    		$('#ebscategory3').append("<option value='"+data3[i].ebscategory3+"'>"+data3[i].remark1+"</option>");
						}
				    }
				});
			}
		}else if(st==4){
			//判断上三级牌号是否启用
			if(se1[1].checked!=true||se2[1].checked!=true||se3[1].checked!=true){
				$.messager.alert('提示', '需先启用小类维度', '提示');
				se4[0].checked=true;
			}else{
				$("#gradenumber option[value!='']").remove();	//防止重复点击启用出现重复数据
				var ebsname3=$("#ebscategory3").val();	//得到上一级维度下拉列表选择的信息
				//通过ajax得到启用维度相对应的下拉列表内容
				$.ajax({
					type :"post",
				    url: "payeditaccountingrulesController.do?findgradeebs&ebsname="+ebsname3+"&marketingid="+marketingid,
				    async: false,
				    dataType : "json",
				    success: function(data4){
				    	for (var i=0;i<data4.length;i++){
				    		$('#gradenumber').append("<option value='"+data4[i].gradenumber+"'>"+data4[i].gradenumber+"</option>");
						}
				    }
				});
			}
		}else if(st==5){
			$("#eebscategory1 option[value!='']").remove();	//防止重复点击启用出现重复数据
			//通过ajax得到启用维度相对应的下拉列表内容
			$.ajax({
				type :"post",
			    url: "payeditaccountingrulesController.do?findoneebs&marketingid="+marketingid,
			    async: false,
			    dataType : "json",
			    success: function(data1){
			    	for (var i=0;i<data1.length;i++){
			    		$('#eebscategory1').append("<option value='"+data1[i].ebscategory1+"'>"+data1[i].remark1+"</option>");
					}
			    }
			});
		}else if(st==6){
			if(se1[3].checked!=true){
				$.messager.alert('提示', '需先启用大类维度', '提示');				
				se2[2].checked=true;
			}else{
				$("#eebscategory2 option[value!='']").remove();	//防止重复点击启用出现重复数据
				var ebsname1=$("#eebscategory1").val();	//得到上一级维度下拉列表选择的信息
				//通过ajax得到启用维度相对应的下拉列表内容
				$.ajax({
					type :"post",
				    url: "payeditaccountingrulesController.do?findtwoebs&ebsname="+ebsname1+"&marketingid="+marketingid,
				    async: false,
				    dataType : "json",
				    success: function(data2){
				    	for (var i=0;i<data2.length;i++){
				    		$('#eebscategory2').append("<option value='"+data2[i].ebscategory2+"'>"+data2[i].remark1+"</option>");
						}
				    }
				});
			}
		}else if(st==7){
			if(se1[3].checked!=true||se2[3].checked!=true){
				$.messager.alert('提示', '需先启用中类维度', '提示');
				se3[2].checked=true;
			}else{
				$("#eebscategory3 option[value!='']").remove();	//防止重复点击启用出现重复数据
				var ebsname2=$("#eebscategory2").val();	//得到上一级维度下拉列表选择的信息
				//通过ajax得到启用维度相对应的下拉列表内容
				$.ajax({
					type :"post",
				    url: "payeditaccountingrulesController.do?findthreeebs&ebsname="+ebsname2+"&marketingid="+marketingid,
				    async: false,
				    dataType : "json",
				    success: function(data3){
				    	for (var i=0;i<data3.length;i++){
				    		$('#eebscategory3').append("<option value='"+data3[i].ebscategory3+"'>"+data3[i].remark1+"</option>");
						}
				    }
				});
			}
		}else if(st==8){
			if(se1[3].checked!=true||se2[3].checked!=true||se3[3].checked!=true){
				$.messager.alert('提示', '需先启用小类维度', '提示');
				se4[2].checked=true;
			}else{
				$("#egradenumber option[value!='']").remove();	//防止重复点击启用出现重复数据
				var ebsname3=$("#eebscategory3").val();	//得到上一级维度下拉列表选择的信息
				//通过ajax得到启用维度相对应的下拉列表内容
				$.ajax({
					type :"post",
				    url: "payeditaccountingrulesController.do?findgradeebs&ebsname="+ebsname3+"&marketingid="+marketingid,
				    async: false,
				    dataType : "json",
				    success: function(data4){
				    	for (var i=0;i<data4.length;i++){
				    		$('#egradenumber').append("<option value='"+data4[i].gradenumber+"'>"+data4[i].gradenumber+"</option>");
						}
				    }
				});
			}
		}
	}

	
	//禁用牌号维度时JS，en值为1至4时是新建时，其他为编辑时
	function disablest(en){
		var se2=document.getElementsByName("startebscategory2");
		var se3=document.getElementsByName("startebscategory3");
		var sg=document.getElementsByName("startgradenumber");
		if(en==1){
			se2[0].checked=true;//将大类以下维度单选，变为禁用
			se3[0].checked=true;
			sg[0].checked=true;
			$("#ebscategory1 option[value!='']").remove();//删除所有下拉列表内容
			$("#ebscategory2 option[value!='']").remove();
			$("#ebscategory3 option[value!='']").remove();	
			$("#gradenumber option[value!='']").remove();	
		}else if(en==2){
			se3[0].checked=true;//将中类以下维度单选，变为禁用
			sg[0].checked=true;
			$("#ebscategory2 option[value!='']").remove();//删除大类以下下拉列表内容
			$("#ebscategory3 option[value!='']").remove();	
			$("#gradenumber option[value!='']").remove();
		}else if(en==3){
			sg[0].checked=true;	//将小类以下维度单选，变为禁用
			$("#ebscategory3 option[value!='']").remove();//删除中类下拉列表内容
			$("#gradenumber option[value!='']").remove();
		}else if(en==4){
			$("#gradenumber option[value!='']").remove();//删除小类以下下拉列表内容
		}else if(en==5){
			se2[2].checked=true;//将大类以下维度单选，变为禁用
			se3[2].checked=true;
			sg[2].checked=true;
			$("#eebscategory1 option[value!='']").remove();//删除所有下拉列表内容
			$("#eebscategory2 option[value!='']").remove();
			$("#eebscategory3 option[value!='']").remove();	
			$("#egradenumber option[value!='']").remove();
		}else if(en==6){
			se3[2].checked=true;//将中类以下维度单选，变为禁用
			sg[2].checked=true;
			$("#eebscategory2 option[value!='']").remove();//删除大类以下下拉列表内容	
			$("#eebscategory3 option[value!='']").remove();
			$("#egradenumber option[value!='']").remove();
		}else if(en==7){
			sg[2].checked=true;//将小类以下维度单选，变为禁用
			$("#eebscategory3 option[value!='']").remove();//删除中类以下下拉列表内容
			$("#egradenumber option[value!='']").remove();
		}else if(en==8){
			$("#egradenumber option[value!='']").remove();//删除小类以下下拉列表内容
		}
	}
	/**
	<select class="easyui-combobox" name="chargingmode" id="chargingmode" data-options="onSelect:payedit" style="width:150px;" required="true">
						<option value="线性收费">线性收费</option>
						<option value="线性收费阶梯退费">线性收费阶梯退费</option>
						<option value="阶梯收费">阶梯收费</option>
					</select>
	*/
	//计费模式改变方法
	function chargingpartychange()
	{
		$("#chargingmode option[value!='']").remove();//删除所有下拉列表内容
		var chargingpartycont=$("#chargingparty").val();//得到大类维度下拉列表所选内容
		
		if (chargingpartycont=='卖方')
			{
			$('#chargingmode').append("<option value='线性收费'>线性收费</option>");
			$('#chargingmode').append("<option value='阶梯收费'>阶梯收费</option>");
			$('#chargingmode').append("<option value='线性收费阶梯退费'>线性收费阶梯退费</option>");
			}
		else
			{
			$('#chargingmode').append("<option value='线性收费'>线性收费</option>");
			$('#chargingmode').append("<option value='阶梯收费'>阶梯收费</option>");
			}
		
	}
	//下拉列表改变选中时改变其他下拉列表内容js，ge值为1至4时是新建时，其他为编辑时
	function gradechange(ge){
		var se2=document.getElementsByName("startebscategory2");
		var se3=document.getElementsByName("startebscategory3");
		var sg=document.getElementsByName("startgradenumber");
		var marketingid=$('#toolbar input[name=pmarketingid]').val();
		if(ge==1){
			$("#ebscategory2 option[value!='']").remove();//删除大类以下下拉列表内容	
			$("#ebscategory3 option[value!='']").remove();
			$("#gradenumber option[value!='']").remove();
			if(se2[1].checked==true){
				var ebsname1=$("#ebscategory1").val();//得到大类维度下拉列表所选内容
				//通过ajax得到大类下一级维度的下拉列表内容
				$.ajax({
					type :"post",
				    url: "payeditaccountingrulesController.do?findtwoebs&ebsname="+ebsname1+"&marketingid="+marketingid,
				    async: false,
				    dataType : "json",
				    success: function(data2){
				    	for (var i=0;i<data2.length;i++){
				    		$('#ebscategory2').append("<option value='"+data2[i].ebscategory2+"'>"+data2[i].remark1+"</option>");
						}
				    }
				});
			}
		}else if(ge==2){
			$("#ebscategory3 option[value!='']").remove();//删除中类以下下拉列表内容	
			$("#gradenumber option[value!='']").remove();
			if(se3[1].checked==true){
				var ebsname2=$("#ebscategory2").val();//得到中类维度下拉列表所选内容
				//通过ajax得到中类下一级维度的下拉列表内容
				$.ajax({
					type :"post",
				    url: "payeditaccountingrulesController.do?findthreeebs&ebsname="+ebsname2+"&marketingid="+marketingid,
				    async: false,
				    dataType : "json",
				    success: function(data3){
				    	for (var i=0;i<data3.length;i++){
				    		$('#ebscategory3').append("<option value='"+data3[i].ebscategory3+"'>"+data3[i].remark1+"</option>");
						}
				    }
				});
			}
		}else if(ge==3){
			$("#gradenumber option[value!='']").remove();//删除小类以下下拉列表内容
			if(sg[1].checked==true){
				var ebsname3=$("#ebscategory3").val();//得到小类维度下拉列表所选内容
				//通过ajax得到小类下一级维度的下拉列表内容
				$.ajax({
					type :"post",
				    url: "payeditaccountingrulesController.do?findgradeebs&ebsname="+ebsname3+"&marketingid="+marketingid,
				    async: false,
				    dataType : "json",
				    success: function(data4){
				    	for (var i=0;i<data4.length;i++){
				    		$('#gradenumber').append("<option value='"+data4[i].gradenumber+"'>"+data4[i].gradenumber+"</option>");
						}
				    }
				});
			}
		}else if(ge==5){
			$("#eebscategory2 option[value!='']").remove();//删除大类以下下拉列表内容	
			$("#eebscategory3 option[value!='']").remove();
			$("#egradenumber option[value!='']").remove();
			if(se2[3].checked==true){
				var ebsname1=$("#eebscategory1").val();//得到大类维度下拉列表所选内容
				//通过ajax得到大类下一级维度的下拉列表内容
				$.ajax({
					type :"post",
				    url: "payeditaccountingrulesController.do?findtwoebs&ebsname="+ebsname1+"&marketingid="+marketingid,
				    async: false,
				    dataType : "json",
				    success: function(data2){
				    	for (var i=0;i<data2.length;i++){
				    		$('#eebscategory2').append("<option value='"+data2[i].ebscategory2+"'>"+data2[i].remark1+"</option>");
						}
				    }
				});
			}
		}else if(ge==6){
			$("#eebscategory3 option[value!='']").remove();//删除中类以下下拉列表内容	
			$("#egradenumber option[value!='']").remove();
			if(se3[3].checked==true){
				var ebsname2=$("#eebscategory2").val();//得到中类维度下拉列表所选内容
				//通过ajax得到中类下一级维度的下拉列表内容
				$.ajax({
					type :"post",
				    url: "payeditaccountingrulesController.do?findthreeebs&ebsname="+ebsname2+"&marketingid="+marketingid,
				    async: false,
				    dataType : "json",
				    success: function(data3){
				    	for (var i=0;i<data3.length;i++){
				    		$('#eebscategory3').append("<option value='"+data3[i].ebscategory3+"'>"+data3[i].remark1+"</option>");
						}
				    }
				});
			}
		}else if(ge==7){
			$("#egradenumber option[value!='']").remove();//删除小类以下下拉列表内容
			if(sg[3].checked==true){
				var ebsname3=$("#eebscategory3").val();//通过ajax得到小类下一级维度的下拉列表内容
				//通过ajax得到小类下一级维度的下拉列表内容
				$.ajax({
					type :"post",
				    url: "payeditaccountingrulesController.do?findgradeebs&ebsname="+ebsname3+"&marketingid="+marketingid,
				    async: false,
				    dataType : "json",
				    success: function(data4){
				    	for (var i=0;i<data4.length;i++){
				    		$('#egradenumber').append("<option value='"+data4[i].gradenumber+"'>"+data4[i].gradenumber+"</option>");
						}
				    }
				});
			}
		}
	}
	
</script>
</head>
<body class="easyui-layout" fit="true">
	<div region="center" border="false" style="overflow: hidden;">
		<div id="toolbar" class="datagrid-toolbar" style="height: auto;">
			<fieldset>
				<legend>筛选</legend>
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
						<td><input name="marketingid" style="width: 200px;" /><input name="pmarketingid" id="pmarketingid" type="hidden" value="<%=pmarketingid %>" style="width: 305px;" />
						</td>
						<td><a class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchFun();" href="javascript:void(0);">查找</a><a class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="clearFun();" href="javascript:void(0);">清空</a></td>
					</tr>
					
				 -->	
					<tr>
						<th>查看配置日期</th>
						<td colspan="6"><input name="effectivetime" class="easyui-datebox" style="width: 140px;" />
					至<input name="invalidtime" class="easyui-datebox" style="width: 140px;" /></td>
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
					</select> <input name="marketingid" type="hidden" value="<%=pmarketingid %>" style="width: 200px;" /><input name="pmarketingid" id="pmarketingid" type="hidden" value="<%=pmarketingid %>" style="width: 305px;" /></td>
					
						<th>审核状态</th>
						<td><select class="easyui-combobox" name="approve" style="width:150px;">
						<option value="" selected="true">全部</option>
						<option value="待审核">待审核</option>
						<option value="已通过">已通过</option>
						<option value="未通过">未通过</option>
						</select></td>
					
						<th>计费模式</th>
						<td><select class="easyui-combobox" name="chargingmode" style="width:150px;">
						<option value="" selected="true">全部</option>
						<option value="线性收费">线性收费</option>
						<option value="线性收费阶梯退费">线性收费阶梯退费</option>
						<option value="阶梯收费">阶梯收费</option>
					</select>
						</td>
						<td><a class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchFun();" href="javascript:void(0);">查找</a><a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="clearFun();" href="javascript:void(0);">清空</a></td>
					</tr>
				</table>
			</fieldset>
			<div>
				<a class="easyui-linkbutton" iconCls="icon-add" onclick="append();" plain="true" href="javascript:void(0);">增加</a> 
				<a class="easyui-linkbutton" iconCls="icon-remove" onclick="funremove();" plain="true" href="javascript:void(0);">删除</a> 
				<a class="easyui-linkbutton" iconCls="icon-edit" onclick="edit();" plain="true" href="javascript:void(0);">编辑</a> 
				<a class="easyui-linkbutton" iconCls="icon-add" onclick="inherit();" plain="true" href="javascript:void(0);">继承</a> 
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
					<select id="chargingparty" name="chargingparty" style="width:150px;" onchange="chargingpartychange()" required="true">
						<option value="卖方">卖方</option>
						<option value="买方">买方</option>
					</select>
					</td>
				</tr>
				<tr>
					<th><font color="ff0000">*</font>配置有效期</th>
					<td colspan=3>
					<input name="effectivetime" id="effectivetime" class="easyui-datebox" style="width: 140px;" required="true"/>
					至<input name="invalidtime" id="invalidtime" class="easyui-datebox" style="width: 140px;" required="true"/>
					</td>
				</tr>
				<%
				if (newaccount.getExchangemodel().equals("1"))
				{
				%>
				<tr>
					<th>交易模式</th>
					<td>
					<select class="easyui-combobox" name="tradingpatterns" style="width:150px;" required="true">
						<option value="现货交易">现货交易</option>
						<option value="客服交易">客服交易</option>
						<option value="公开竞拍">公开竞拍</option>
						<option value="密封竞拍">密封竞拍</option>
						<option value="团购交易">团购交易</option>
						<option value="预售交易">预售交易</option>
					</select>
					</td>
				</tr>
				<%
				}
				%>
				<%
				if (newaccount.getProductbrand().equals("1"))
				{
				%>
				<tr>
					<th>大类维度</th>
					<td colspan="3">
					<input type="radio" name="startebscategory1" style="width:30px;" value="0" onclick="disablest(1);" checked="checked"/>禁用
		            <input type="radio" name="startebscategory1" style="width:30px;" value="1" onclick="enablest(1);">启用
					<select id="ebscategory1" name="ebscategory1" style="width:150px;" onchange="gradechange(1)">
						<option value="">空</option>
					</select>
					</td>
				</tr>
				<tr>
					<th>中类维度</th>
					<td colspan="3">
					<input type="radio" name="startebscategory2" style="width:30px;" value="0" checked="checked" onclick="disablest(2);"/>禁用
		            <input type="radio" name="startebscategory2" style="width:30px;" value="1" onclick="enablest(2);"/>启用
		            <select id="ebscategory2" name="ebscategory2" style="width:150px;" onchange="gradechange(2)">
						<option value="">空</option>
					</select>
					</td>
				</tr>
				<tr>
					<th>小类维度</th>
					<td colspan="3">
					<input type="radio" name="startebscategory3" style="width:30px;" value="0" checked="checked" onclick="disablest(3);"/>禁用
		            <input type="radio" name="startebscategory3" style="width:30px;" value="1" onclick="enablest(3);"/>启用
		            <select id="ebscategory3" name="ebscategory3" style="width:150px;" onchange="gradechange(3)">
						<option value="">空</option>
					</select>
					</td>
				</tr>
				<tr>
					<th>牌号维度</th>
					<td colspan="3">
					<input type="radio" name="startgradenumber" style="width:30px;" value="0" checked="checked" onclick="disablest(4);"/>禁用
		            <input type="radio" name="startgradenumber" style="width:30px;" value="1" onclick="enablest(4);"/>启用
		            <select id="gradenumber" name="gradenumber" style="width:150px;" onchange="gradechange(4)">
						<option value="">空</option>
					</select>
					</td>
				</tr>
				<%
				}
				%>
				<tr>
					<th>计费模式</th>
					<td>
					<select name="chargingmode" id="chargingmode" onchange="payedit()" style="width:150px;">
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
				<!-- 
				<tr>
					<td width="60%">阈值设置：<input name="startthreshold" id="startthreshold" style="width: 50px;" />
					<select class="easyui-combobox" name="startthresholdunit" id="startthresholdunit" style="width:40px;">
						<option value="单">单</option>
						<option value="吨">吨</option>
					</select>
					<=达成量<=<input name="endthreshold" id="endthreshold" style="width: 50px;" />
					<select class="easyui-combobox" name="endthresholdunit" id="endthresholdunit" style="width:40px;">
						<option value="单">单</option>
						<option value="吨">吨</option>
					</select>
					</td>
					<td width="40%">退费：	<input name="stepmodenum" id="stepmodenum" class="easyui-validatebox" style="width: 60px;" />
					<select class="easyui-combobox" name="stepmode" id="stepmode" style="width:40px;">
						<option value="单">单</option>
						<option value="吨">吨</option>
					</select>
					</td>
				</tr>
				 -->
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
				<!-- 
				<tr>
					<td width="60%">阈值设置：<input name="startthreshold" id="startthreshold" style="width: 50px;" />
					<select class="easyui-combobox" name="startthresholdunit" id="startthresholdunit" style="width:40px;">
						<option value="单">单</option>
						<option value="吨">吨</option>
					</select>
					<=达成量<=<input name="endthreshold" id="endthreshold" style="width: 50px;" />
					<select class="easyui-combobox" name="endthresholdunit" id="endthresholdunit" style="width:40px;">
						<option value="单">单</option>
						<option value="吨">吨</option>
					</select>
					</td>
					<td width="40%">收费：	<input name="stepmodenum" id="stepmodenum" class="easyui-validatebox" style="width: 60px;" />
					<select class="easyui-combobox" name="stepmode" id="stepmode" style="width:40px;">
						<option value="单">单</option>
						<option value="吨">吨</option>
					</select>
					</td>
				</tr>
				 -->
				</table>
				<!-- 子表内容结束 -->
				
					
					
				<!-- 
				<tr>
					<th>订单来源</th>
					<td><input name="ordersource" class="easyui-validatebox" required="true" />
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
					<th>产品牌号</th>
					<td><input name="productbrand" class="easyui-validatebox" required="true" />
					</td>
				</tr>
				
				<tr>
					<th>是否有效</th>
					<td>
		                <input type="radio" name="effective" style="width:40px;" value="0">失效</input>
		                <input type="radio" name="effective" style="width:40px;" value="1" checked="checked">生效</input>
					</td>
				</tr>
				<tr>
					<th>优先级</th>
					<td><input name="priority" class="easyui-validatebox" required="true" />
					</td>
				</tr>
				 -->
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
				<%
				if (newaccount.getExchangemodel().equals("1"))
				{
				%>
				<tr>
					<th>交易模式</th>
					<td>
					<select class="easyui-combobox" name="tradingpatterns" style="width:150px;" required="true">
						<option value="现货交易">现货交易</option>
						<option value="客服交易">客服交易</option>
						<option value="公开竞拍">公开竞拍</option>
						<option value="密封竞拍">密封竞拍</option>
						<option value="团购交易">团购交易</option>
						<option value="预售交易">预售交易</option>
					</select>
					</td>
				</tr>
				<%
				}
				%>
				<%
				if (newaccount.getProductbrand().equals("1"))
				{
				%>
				<tr>
					<th>大类维度</th>
					<td colspan="3">
					<input type="radio" name="startebscategory1" style="width:30px;" value="0" checked="checked" onclick="disablest(5);"/>禁用
		            <input type="radio" name="startebscategory1" style="width:30px;" value="1" onclick="enablest(5);"/>启用
					<select id="eebscategory1" name="ebscategory1" style="width:150px;" onchange="gradechange(5)">
						<option value="">空</option>
					</select>
					</td>
				</tr>
				<tr>
					<th>中类维度</th>
					<td colspan="3">
					<input type="radio" name="startebscategory2" style="width:30px;" value="0" checked="checked" onclick="disablest(6);"/>禁用
		            <input type="radio" name="startebscategory2" style="width:30px;" value="1" onclick="enablest(6);"/>启用
		            <select id="eebscategory2" name="ebscategory2" style="width:150px;" onchange="gradechange(6)">
						<option value="">空</option>
					</select>
					</td>
				</tr>
				<tr>
					<th>小类维度</th>
					<td colspan="3">
					<input type="radio" name="startebscategory3" style="width:30px;" value="0" checked="checked" onclick="disablest(7);"/>禁用
		            <input type="radio" name="startebscategory3" style="width:30px;" value="1" onclick="enablest(7);"/>启用
		            <select id="eebscategory3" name="ebscategory3" style="width:150px;" onchange="gradechange(7)">
						<option value="">空</option>
					</select>
					</td>
				</tr>
				<tr>
					<th>牌号维度</th>
					<td colspan="3">
					<input type="radio" name="startgradenumber" style="width:30px;" value="0" checked="checked" onclick="disablest(8);"/>禁用
		            <input type="radio" name="startgradenumber" style="width:30px;" value="1" onclick="enablest(8);"/>启用
		            <select id="egradenumber" name="gradenumber" style="width:150px;" onchange="gradechange(8)">
						<option value="">空</option>
					</select>
					</td>
				</tr>
				<%
				}
				%>
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
				
				<!-- 
				<tr>
					<th>订单来源</th>
					<td><input name="ordersource" class="easyui-validatebox" required="true" />
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
					<th>产品牌号</th>
					<td><input name="productbrand" class="easyui-validatebox" required="true" />
					</td>
				</tr>
				
				<tr>
					<th>是否有效</th>
					<td>
		                <input type="radio" name="effective" style="width:40px;" value="0">失效</input>
		                <input type="radio" name="effective" style="width:40px;" value="1" checked="checked">生效</input>
					</td>
				</tr>
				<tr>
					<th>优先级</th>
					<td><input name="priority" class="easyui-validatebox" required="true" />
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
	
<script type="text/javascript">
				
				function addtitle(){ 
					//var maxnum = document.getElementById("maxnum");	//无法得到value值
					//$.messager.alert('提示', maxnum.value, 'error');
					//var newtable = document.getElementById("newtable"+i);
					//var x=(parseInt(maxnum.value)||0)+1;
					//maxnum.value=(parseInt(maxnum.value)||0)+1;//无法在指定位置输出HTML
					/*
					<tr><td width='60%'>阈值设置：<input name='startthreshold"+x+"' id='startthreshold"+x+"' style='width: 50px;' />
					<select class='easyui-combobox' name='startthresholdunit"+x+"' id='startthresholdunit"+x+"' style='width:40px;'>
						<option value='单'>单</option><option value='吨'>吨</option></select>
					<=达成量<=<input name='endthreshold"+x+"' id='endthreshold"+x+"' style='width: 50px;' />
					<select class='easyui-combobox' name='endthresholdunit"+x+"' id='endthresholdunit"+x+"' style='width:40px;'>
						<option value='单'>单</option><option value='吨'>吨</option></select></td>
					<td width='40%'>退费：	<input name='stepmodenum"+x+"' id='stepmodenum"+x+"' class='easyui-validatebox' style='width: 60px;' />
					<select class='easyui-combobox' name='stepmode"+x+"' id='stepmode"+x+"' style='width:40px;'>
						<option value='单'>单</option><option value='吨'>吨</option></select><input type='button' onclick='javascript:shanchu(this)'  value='删除子表' /></td></tr>
					*/
					var strFrm="<tr id='tr"+i+"'><td width='65%'>阈值设置：<input name='startthreshold"+i+"' id='startthreshold"+i+"' style='width: 60px;' />"
					+"<select class='easyui-combobox' name='startthresholdunit"+i+"' id='startthresholdunit"+i+"' style='width:40px;' data-options='onSelect:testClk0'>"
					+"<option value='单'>单</option><option value='吨'>吨</option></select><=达成量<=<input name='endthreshold"+i+"' id='endthreshold"+i+"' style='width: 60px;' />"
					+"<select class='easyui-combobox' name='endthresholdunit"+i+"' id='endthresholdunit"+i+"' style='width:40px;' data-options='onSelect:testClk0'>"
					+"<option value='单'>单</option><option value='吨'>吨</option></select></td>"
					+"<td width='35%'>退费：<input name='stepmodenum"+i+"' id='stepmodenum"+i+"' class='easyui-numberbox' style='width: 60px;' />"
					+"<select class='easyui-combobox' name='stepmode"+i+"' id='stepmode"+i+"' style='width:40px;' data-options='onSelect:testClk0'>"
					+"<option value='单'>单</option><option value='吨'>吨</option></select>&nbsp;&nbsp;<input type='button' onclick=shanchu('tr"+i+"')  value='删除' style='width: 50px;' /></td></tr>";

					$("#newtable tbody").append(strFrm);
					
					i++;
				}
				function shanchu(shancid){
					//var table=shancid.parentNode.parentNode.parentNode.parentNode.parentNode;
					//table.parentNode.removeChild(table);
					if(i>0)
					{
						$("#"+shancid).remove(); 
					}

				}
				//阶梯收费子表
				
				
				function addtitle3(){ 
					//var maxnum = document.getElementById("maxnumsf");	//无法得到value值
					//$.messager.alert('提示', maxnum.value, 'error');
					//var newtable = document.getElementById("newsftable"+x);
					//var x=(parseInt(maxnum.value)||0)+1;
					//maxnum.value=(parseInt(maxnum.value)||0)+1;//无法在指定位置输出HTML
					/*
					<tr><td width='60%'>阈值设置：<input name='startthreshold"+x+"' id='startthreshold"+x+"' style='width: 50px;' />
					<select class='easyui-combobox' name='startthresholdunit"+x+"' id='startthresholdunit"+x+"' style='width:40px;'>
						<option value='单'>单</option><option value='吨'>吨</option></select>
					<=达成量<=<input name='endthreshold"+x+"' id='endthreshold"+x+"' style='width: 50px;' />
					<select class='easyui-combobox' name='endthresholdunit"+x+"' id='endthresholdunit"+x+"' style='width:40px;'>
						<option value='单'>单</option><option value='吨'>吨</option></select></td>
					<td width='40%'>退费：	<input name='stepmodenum"+x+"' id='stepmodenum"+x+"' class='easyui-validatebox' style='width: 60px;' />
					<select class='easyui-combobox' name='stepmode"+x+"' id='stepmode"+x+"' style='width:40px;'>
						<option value='单'>单</option><option value='吨'>吨</option></select><input type='button' onclick='javascript:shanchu(this)'  value='删除子表' /></td></tr>
					*/
					var strFrm="<tr id='tr3"+x+"'><td width='65%'>阈值设置：<input name='startthresholdsf"+x+"' id='startthresholdsf"+x+"' style='width: 60px;' />"
					+"<select class='easyui-combobox' name='startthresholdunitsf"+x+"' id='startthresholdunitsf"+x+"' style='width:40px;' data-options='onSelect:testClk3'>"
					+"<option value='单'>单</option><option value='吨'>吨</option></select><=达成量<=<input name='endthresholdsf"+x+"' id='endthresholdsf"+x+"' style='width: 60px;' />"
					+"<select class='easyui-combobox' name='endthresholdunitsf"+x+"' id='endthresholdunitsf"+x+"' style='width:40px;' data-options='onSelect:testClk3'>"
					+"<option value='单'>单</option><option value='吨'>吨</option></select></td>"
					+"<td width='35%'>收费：<input name='stepmodenumsf"+x+"' id='stepmodenumsf"+x+"' class='easyui-numberbox' style='width: 60px;' />"
					+"<select class='easyui-combobox' name='stepmodesf"+x+"' id='stepmodesf"+x+"' style='width:40px;' data-options='onSelect:testClk3'>"
					+"<option value='单'>单</option><option value='吨'>吨</option></select>&nbsp;&nbsp;<input type='button' onclick=shanchu3('tr3"+x+"') value='删除' style='width: 60px;' /></td></tr>";
					
					$("#newsftable tbody").append(strFrm);
					x++;
				}
				
				function shanchu3(shancid){
					//var table=shancid.parentNode.parentNode.parentNode.parentNode.parentNode;
					//table.parentNode.removeChild(table);
					if(x>0)
					{
						$("#"+shancid).remove(); 
					}

				}
				
				function testClk0()		//无效JS
				{
					/*
					单位：单、吨
					*/
					$.messager.alert('提示', '123123', '提示');
					var charg = $('#chargingdimensionalitysf').combobox('getValue');	//退费中收费配置
					for(j=0;j<i;j++){
						if(document.getElementById("startthresholdunit"+j)!=null){	//查看该行是否被删除
						var startth = document.getElementById("startthresholdunit"+j);
						var endth = document.getElementById("endthresholdunit"+j);
						var stepmo = document.getElementById("stepmode"+j);
						var chargstartth="元/"+startth.value;	//为了与收费配置单位一致，从而进行比较
							if(charg==chargstartth&&startth.value==endth.value&&endth.value==stepmo.value&&startth.value==stepmo.value){	
							}else{	
								$.messager.alert('提示', '需保持计量单位维度一致性', '提示');
								break;
							}
						}
					}
				}
				
				function testClk3()		//无效JS
				{
					/*
					单位：单、吨
					*/
					$.messager.alert('提示', '123', '提示');
					var yanzheng="";
					for(j=0;j<x;j++){
						if(document.getElementById("startthresholdunitsf"+j)!=null){	//查看该行是否被删除
						var startth = document.getElementById("startthresholdunitsf"+j);
						var endth = document.getElementById("endthresholdunitsf"+j);
						var stepmo = document.getElementById("stepmodesf"+j);
						if(yanzheng==""){		//用来判断各行之间维度是否相同，当做一个记录
							yanzheng=startth.value;
						}
						if(yanzheng==startth.value&&startth.value==endth.value&&endth.value==stepmo.value&&startth.value==stepmo.value){	
						}else{	
							$.messager.alert('提示', '需保持计量单位维度一致性', '提示');
							break;
						}
						yanzheng=startth.value;
					}
					}
				}
				
				</script>
	<div id="menu" class="easyui-menu" style="width:120px;display: none;">
		<div onclick="append();" iconCls="icon-add">增加</div>
		<div onclick="funremove();" iconCls="icon-remove">删除</div>
		<div onclick="edit();" iconCls="icon-edit">编辑</div>
		<div onclick="inherit();" iconCls="icon-edit">继承</div>
	</div>
</body>
</html>
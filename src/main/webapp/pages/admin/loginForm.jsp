<%@ page language="java" pageEncoding="UTF-8"%>
<%
String msg=(String)session.getAttribute("msg");
if ((msg==null)||(msg.equals("null")))
{
	msg="";
}
%>
<script type="text/javascript" charset="UTF-8">
	var loginAndRegDialog;
	var regDialog;
	var loginTabs;
	var loginInputForm;
	var regForm;
	var loginDatagridName;
	var loginDatagridForm;
	var loginComboboxForm;
	var loginComboboxName;
	var msg;
	$(function() {
		regDialog = $('#regDialog').show().dialog({
			modal : true,
			title : '注册',
			closed : true,
			buttons : [ {
				text : '注册',
				handler : function() {
					regForm.submit();
				}
			} ],
			onOpen : function() {
				setTimeout(function() {
					regForm.find('input[name=name]').focus();
				}, 1);
			},
			onClose : function() {
				loginTabs.tabs('select', 0);
			}
		});


	});
	
</script>
<script type="text/javascript">
	function tologin(){
		
		var thisform=document.getElementById("loginInputForm");
		if(thisform.name.value=="") {	
			thisform.name.focus();
			return false;
			alert("请输入登录账号");
		} else if(thisform.password.value=="") {	
			thisform.password.focus();
			alert("请您输入密码");
			return false;
		}else
	    	thisform.action = "userController.do?ulogin";
		    thisform.method="post";
	        thisform.submit();
	}
	
</script>
<div id="regDialog" style="width:300px;display: none;padding: 5px;" align="center">
	<form id="regForm" method="post">
		<table class="tableForm">
			<tr>
				<th>登录名</th>
				<td><input name="name" class="easyui-validatebox" required="true" />
				</td>
			</tr>
			<tr>
				<th>密码</th>
				<td><input name="password" type="password" class="easyui-validatebox" required="true" />
				</td>
			</tr>
			<tr>
				<th>重复密码</th>
				<td><input name="rePassword" type="password" class="easyui-validatebox" required="true" validType="eqPassword['#regForm input[name=password]']" />
				</td>
			</tr>
		</table>
	</form>
</div>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>塑贸网</title>
<link rel="stylesheet" type="text/css" href="css/public.css" />
<link rel="stylesheet" type="text/css" href="css/sec.css" />
</head>

<body>
<div class="page">
  <div class="dlheader">
    <div class="float_l"><a href="#"><img src="images/dl_01.png" /></a><span><b>欢迎登录卖家中心</b>Welcome to SUMAO</span></div>
    <div class="float_r"><img src="images/dl_02.png" /><span>客户服务<br />400-821-1888</span></div>
    <br class="clear_b" />
  </div>
  <div class="dlmid_seller">
    <div class="dlcont">
      <img src="images/login_seller_pic.png" class="float_l" />
      <div class="dl_form">
        <div class="dl_formtop">
        <span>卖家登录</span>
        <span style="font-size:14px; font-weight:normal; color:#F00; padding-left:20px;"><img src="images/yj0726th.png" width="16" height="16" align="absmiddle"/>&nbsp;<%=msg %></span>
        </div>
        <form id="loginInputForm" method="post">
        <div class="dl_formmid">
          <ul>
            <li><span>账号</span><input type="text" name="name" class="dl_text" value="请输入登录账号" onFocus="this.value=''" /></li>
            <li><span>密码</span><input type="password" name="password" class="dl_pwd" value="请输入密码" onFocus="this.value=''" /></li>
            <li><span>验证码</span>
            <input type="text" class="yj0801dl_code" value="请输入右侧验证码" onFocus="this.value=''"/>
            <img src="images/yj0801code.png" /> </li>
            <li><b><input type="checkbox" class="dl_chk" />记住账号</b><a href="#">找回密码</a></li>
            <li><input type="button" class="dl_btn" onclick="javascript:tologin()" value="立即登录" /><input type="button" onclick="javascript:regDialog.dialog('open')" class="dl_btn2" value="免费注册" /></li>
          </ul>
        </div>
        </form>
      </div>
    </div>
  </div>
  <div class="dlcontect">
    <ul>
      <li><img src="images/dl_08.png" /><dl><dt>上游直供</dt></dl></li>
      <li><img src="images/dl_09.png" /><dl><dt>便捷交易</dt></dl></li>
      <li><img src="images/dl_10.png" /><dl><dt>安全支付</dt></dl></li>
      <li><img src="images/dl_11.png" /><dl><dt>极速物流</dt></dl></li>
      </ul>
    </div>
  <div class="footer">
    <div class="footerc">
      <a href="#">关于我们&nbsp;&nbsp;|</a><a href="#">法律声明&nbsp;&nbsp;|</a><a href="#">隐私条款&nbsp;&nbsp;|</a><a href="#">联系我们</a> <br />北京塑贸网电子商务有限公司&nbsp;&nbsp;版权所有@京备ICP14074250号
    </div>
  </div>
</div>
</body>
</html>

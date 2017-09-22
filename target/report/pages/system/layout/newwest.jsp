<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
 <div class="f_l seller_leftnav">
    <div class="guide_title">卖家中心</div>
    <dl>
      <dt><img src="images/seller_center_navarrow.png" />产品管理</dt>
      <dt><img src="images/seller_center_navarrow2.png" />业务管理</dt>
      
      <dt><img src="images/seller_center_navarrow3.png" />缴费管理</dt>
      <dd><a target="iframe2" href="<%=basePath%>payTotalController.do?Paytotal">缴费总项管理</a></dd>
      <dd><a target="iframe2" href="<%=basePath%>paySubController.do?Paysub">缴费子项管理</a></dd>
      <dd><a target="iframe2" href="<%=basePath%>paySubController.do?Payuserorg">缴费项及配置管理</a></dd>
      <dd><a target="iframe2" href="<%=basePath%>payrightController.do?Pay_rightgoldallcheck">权利金支付记录管理</a></dd>
      <dd><a target="iframe2" href="<%=basePath%>paycostpayController.do?Pay_bondallcheck">保证金支付记录管理</a></dd>
                
      <dt><img src="images/seller_center_navarrow3.png" />计费管理</dt>
      <dd><a target="iframe2" href="<%=basePath%>paynewaccountingrulesController.do?paynewaccountingrules">计费配置管理</a></dd>
      <dd><a target="iframe2" href="<%=basePath%>payeditaccountingrulesController.do?payseachaccountingrules">计费配置记录管理</a></dd>
      <dd><a target="iframe2" href="<%=basePath%>paydepositbalanceController.do?paydepositbalance">计费明细查询</a></dd>
       
      <dt><img src="images/seller_center_navarrow4.png" />卖家费用管理</dt>
      <dd><a target="iframe2" href="<%=basePath%>payrightController.do?rightoldSeller&orgnid=iorg20002">权利金缴费查看</a></dd>
      <dd><a target="iframe2" href="<%=basePath%>paycostpayController.do?bondSeller&orgnid=iorg20011">保证金缴费查看</a></dd>
      <dd><a target="iframe2" href="<%=basePath%>paybondController.do?costpaySeller&orgnid=iorg10004">费用明细</a></dd>

      <dt><img src="images/seller_center_navarrow4.png" />系统管理</dt>
      <dd><a target="iframe2" href="<%=basePath%>DatadictionaryController.do?tolistdatadic&depmtype=caiwu">财务科目管理</a></dd>
      <dd><a target="iframe2" href="<%=basePath%>DatadictionaryController.do?tolistdatadic&depmtype=zhifu">支付方式管理</a></dd>
      <dd><a target="iframe2" href="<%=basePath%>AuthmanageController.do?tolistauth">岗位管理</a></dd>
      <dd><a target="iframe2" href="<%=basePath%>AuthdistributeController.do?toauthdis">授权管理</a></dd>
    </dl>
  </div>
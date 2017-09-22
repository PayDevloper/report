<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<div class="f_l seller_leftnav">
	<div class="guide_title">卖家中心</div>
	<dl>
		<c:if test="${!empty high}">
			<c:forEach items="${high}" var="high" varStatus="s">
				<dt>
				<a target="iframe2" href="<%=basePath%>${high.totalid}">
				<c:choose>
                         <c:when test="${(s.index+1)%3==1}">
					<img src="images/seller_center_navarrow.png" />
					</c:when>
					<c:when test="${(s.index+1)%3==2}">
					<img src="images/seller_center_navarrow2.png" />
					</c:when>
					 <c:otherwise>
					 <img src="images/seller_center_navarrow3.png" />
					 </c:otherwise></c:choose>
					${high.financeacc}</dt>
				<c:if test="${!empty sub}">
					<c:forEach items="${sub}" var="sub">
						<c:if test="${sub.subname==high.id}">
							<dd>
								<a target="iframe2" href="<%=basePath%>${sub.totalid}">${sub.financeacc}</a>
							</dd>
						</c:if>
					</c:forEach>
				</c:if>
			</c:forEach>
		</c:if>
	</dl>
</div>
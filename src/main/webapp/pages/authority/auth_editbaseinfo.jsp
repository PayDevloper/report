<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div>
	<form>
		<input name='id' style="display: none" value="" />
		<table>
			<tbody>
				<tr>
					<th style="text-align: center; width: 20%">模块类别：</th>
					<th style="text-align: center; width: 80%">系统模块：</th>
				</tr>
				<c:if test="${!empty sortList}">
					<c:forEach items="${sortList}" var="sort">
						<tr id="ifrmetr">
							<td>${sort.financeacc}</td>
							<td style="text-align: center"><c:if
									test="${!empty zsortList}">
									<c:forEach items="${zsortList}" var="zsort" varStatus="s">
										<c:if test="${zsort.subname==sort.id}">
											<input id="cols${s.index+1} " name="financeacc"
												type="checkbox" value="${zsort.financeacc};${zsort.id}"
												<c:if test="${zsort.codeid eq 'basic'}">checked</c:if> />${zsort.financeacc}
                                </c:if>
									</c:forEach>
								</c:if></td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</form>
</div>
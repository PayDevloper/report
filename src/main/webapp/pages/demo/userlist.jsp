<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试</title>
</head>
<body>
	<table border="1">
       <tr><th>名字</th><th>密码</th><th>年龄</th></tr>
        <c:forEach items="${users}" var="item">
            <tr><td>${item.name}</td><td>${item.password}</td><td>${item.age}</td></tr>
        </c:forEach>
     </table>
</body>
</html>
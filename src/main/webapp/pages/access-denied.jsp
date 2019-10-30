<%--
  Created by IntelliJ IDEA.
  User: dmitr
  Date: 29.10.2019
  Time: 10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Spring 4 Security Example</title>
</head>
<body>
<h3>You are not authorized to access this profile.</h3>
<form action="<%=request.getContextPath()%>/logout" method="POST">
    <input type="submit" value="Logout"/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
</body>
</html>
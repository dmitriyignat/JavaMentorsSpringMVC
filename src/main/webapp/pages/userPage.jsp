<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dmitr
  Date: 29.10.2019
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User page</title>
</head>
<body>
    <h2>Welcome <c:out value="${user.name}"/>!</h2>

    <br/>

    Name: ${user.name} <br/>
    Login: ${user.login} <br/>

    <br/>
    <form action="<%=request.getContextPath()%>/logout" method="POST">
        <input type="submit" value="Logout"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</body>
</html>

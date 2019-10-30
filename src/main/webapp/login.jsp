<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: dmitr
  Date: 23.10.2019
  Time: 23:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>Please Login</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/login" method="post">
    Enter UserName: <input type="text" name="app_username"/><br/><br/>
    Enter Password: <input type="password" name="app_password"/><br/><br/>
    <input type="submit" value="Login"/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
</body>
</html>

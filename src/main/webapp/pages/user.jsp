
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User</title>
</head>
<body>
<form method="post">
    Login: <input type="text" name="login" value="<c:out value="${user.login}" />"/>
    Password: <input type="password" name="password"  value="<c:out value="${user.password}" />"/>
    Name: <input type="text" name="name"  value="<c:out value="${user.name}"/>"/>
    <c:if test="${user.id != null}">
        <input type="hidden" name="id" readonly value="<c:out value="${user.id}"/>" />
        <input formaction="updateUser" type="submit" value="${message}"/>
    </c:if>
    <c:if test="${user.id == null}">
        <input formaction="addUser" type="submit" value="${message}"/>
    </c:if>
</form>
</body>
</html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>User List</title>
</head>
<body>
<form method="post">
<table border="1" cellspacing="0" width="100%" >
    <tbody>
    <tr>
        <td width="5%"><b>ID </b></td>
        <td width="10%"><b>Name </b></td>
        <td width="10%"><b>Login </b></td>
        <td width="10%"><b>Password </b></td>
        <td width="10%"><b>Roles</b></td>
        <td width="10%" colspan="2"><b>Actions</b></td>

    </tr>
    <c:forEach var="user" items="${listUser}">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.login}</td>
            <td>${user.password}</td>
            <td><ul><c:forEach var="role" items="${user.roles}">
                <li><c:out value="${role.name}"/></li>
            </c:forEach></ul></td>
            <td><button formaction="update" type="submit" name="id" value="${user.id}">Update</button></td>
            <td><button formaction="delete" type="submit" name="id" value="${user.id}">Delete</button></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
<a href="<%=request.getContextPath()%>/admin/addUser" role="button" data-toggle="modal">Add new user</a>
<br/>
<br/>
<form action="<%=request.getContextPath()%>/logout" method="POST">
    <input type="submit" value="Logout"/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

</form>
</body>
</html>
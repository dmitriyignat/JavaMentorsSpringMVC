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
        <td width="10%" colspan="2"><b>Actions</b></td>

    </tr>
    <c:forEach var="user" items="${listUser}">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.login}</td>
            <td>${user.password}</td>

            <td><button formaction="update" type="submit" name="id" value="${user.id}">Update</button></td>
            <td><button formaction="delete" type="submit" name="id" value="${user.id}">Delete</button></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</form>
<a href="addUser" role="button" data-toggle="modal">Add new user</a>
</body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>List of all users</title>

</head>
<body>
<div align="center">
    <h2> List of all users</h2>
    <a href="/register.jsp"> Add new user</a> <br>
    <a href="/products">List of all items</a> <br>
    <form action="/logout" method="post">
        <button type="submit">Logout</button>
    </form>
    <table border="1">
        <tr>Email</tr>
        <tr> Password</tr>
        <tr> Role</tr>
        <c:forEach var="user" items="${allUsers}">
            <tr>
                <td>${user.getId()}</td>
                <td>${user.getEmail()}</td>
                <td>${user.getLogin()}</td>
                <td>${user.getPassword()}</td>
                <td>${user.getRole()}</td>
                <td>
                    <button><a href="/edit/users?id=${user.getId()}" name="edit"> Edit </a></button>
                </td>
                <td>
                    <form action="/admin/remove/users" method="post">
                        <input type="hidden" name="userId" value="${user.getId()}">
                        <button type="submit">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
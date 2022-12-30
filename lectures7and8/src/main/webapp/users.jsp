<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    table, tr, td {
        border: 1px solid black;
    }
    td {
        text-align: center;
    }
</style>
<body>
<h2>List of users</h2>
<table>
    <%@ page import="storage.Users, models.User" %>
    <tr>
        <th>Login</th>
        <th>Name</th>
    </tr>
    <% for(User user : Users.getUsers()){ %>
        <tr>
            <td><%= user.getLogin()%></td>
            <td><%= user.getName()%></td>
        </tr>
    <% } %>

</table>
</body>
</html>

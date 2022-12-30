<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Menu</title>
</head>
<body>
<h2>You are welcome!</h2>
<form action="${pageContext.request.contextPath}/users" method="GET">
    <input type="submit" value="See all users">
</form>
<form action="${pageContext.request.contextPath}/logout" method="GET">
    <input type="submit" value="logout">
</form>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main Page</title>
</head>
<body>
<h1>You are welcome!</h1>
<form action="${pageContext.request.contextPath}/login" method="post">
    <label for="login">Enter login: </label>
    <input type="text" id="login" name="login">
    <label for="password">Enter password: </label>
    <input type="password" id="password" name="password">
    <input type="submit" value="Login">
    <% if(request.getAttribute("unknown") != null && (boolean)request.getAttribute("unknown")){ %>
      <p>Invalid login or password. Try again.</p>
    <%} %>
</form>
</body>
</html>

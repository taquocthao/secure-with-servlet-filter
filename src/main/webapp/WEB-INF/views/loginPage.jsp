<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Login Page</h2>
	<p>${messageError }</p>
	<form action="login" method="post">
		<label>Username: </label><input type="text" name="username" /><br/>
		<label>Password: </label><input type="text" name="password" /><br/>
		<input type="submit" value="Login" />
	</form>
	<br/>
	<div>
		<label style="font-weight: bold;">User account: </label><span> user/123 </span><br/>
		<label style="font-weight: bold;">Admin account: </label><span> admin/123 </span>
	</div>
</body>
</html>
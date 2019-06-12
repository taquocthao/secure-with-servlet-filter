<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>

<a href="${pageContext.request.contextPath }/userTask">User Task</a> ||
<a href="${pageContext.request.contextPath }/adminTask">Admin Task</a> ||
<a href="${pageContext.request.contextPath }/userInfo">User Info</a> ||
<a href="${pageContext.request.contextPath }/login">Login</a> ||
<a href="${pageContext.request.contextPath }/logout">Logout</a>
&nbsp;
<span>Welcome, <span style="color: red">${loginedUser.username }</span></span>

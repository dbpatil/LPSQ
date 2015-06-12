<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
	<h1>Login</h1>
	<s:form action="autenticate" modelAttribute="user">
	Enter Email<s:input path="email"/><s:errors path="email" type="email"/><br/>
	Enter Password<s:input path="pass" type="password" name="pass"/>		<s:errors path="pass"/><br/>
	<input type="submit">
	</s:form>
	<br>
	<c:if test="${not empty requestScope.emsg}">
		<h3>Message : ${requestScope.emsg}</h3>
	</c:if>
</body>
</html>
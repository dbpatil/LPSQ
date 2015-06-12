<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Message Page</title>
</head>
<body>
<h1>Message</h1><br>
	<h2>
		<c:if test="${not empty requestScope.emsg}">
			<h3>Error Message : ${requestScope.emsg}</h3>
		</c:if>
	</h2>
	<br>
	<h2>
		<c:if test="${not empty requestScope.msg}">
			<h3>Success Message : ${requestScope.msg}</h3>
		</c:if>
	</h2>
<a href="welcome"> Click here for HomePage</a><br>
<a href="openRegisterView"> Click here for Register Page</a><br>
<a href="openLoginView"> Click here for Login Page</a><br>
</body>
</html>
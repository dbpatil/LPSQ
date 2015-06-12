<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Topic</title>
</head>
<body>
	<h1>Edit Topic Details</h1>
	<s:form modelAttribute="bean" method="post" action="updateTopic" name="myForm">
	Name<s:input path="topicName" name="topicName"/>		<s:errors path="topicName"/><br/>
	Description<s:input path="description" name="description" />		<s:errors path="description"/><br/>	
	<input type="submit" value="Update Responder">		
	</s:form>

</body>
</html>
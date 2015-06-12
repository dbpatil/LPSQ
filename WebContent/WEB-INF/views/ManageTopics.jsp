<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ManageTopics</title>
</head>
<body>
	<h1>ManageTopics</h1>
	<a href="openAddTopicView">Add Topic</a>
	<h1>List Of Topics</h1>
	<hr>
	<c:if test="${not empty list}">
		<table border:20 style="width: 100%">
			<tr>
				<td>SL No</td>
				<td>Topic Name</td>
				<td>Description</td>
				<td>Edit Topic</td>
				<td>Delete Topic</td>
			</tr>
			<%
				int uid = 0;
			%>
			<c:forEach items="${list}" var="topic">
				<tr>
					<td><%=uid++%></td>
					<td>${topic.topicName}</td>
					<td>${topic.description}</td>
					<td><a href="openEditTopicView?topic_sl_no=${topic.id}">
							Edit Topic</a></td>
					<td><a href="openDeleteTopicView?topic_sl_no=${topic.id}">
							Delete Topic</a></td>
				</tr>
			</c:forEach>
		</table>
		<hr>
		<%
			uid = 0;
		%>
	</c:if>
</body>
</html>
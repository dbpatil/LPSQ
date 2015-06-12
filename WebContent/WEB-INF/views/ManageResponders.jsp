<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">

function alertUserForDeleting() {
	var x;
    if (confirm("Are you Sure You Want to This Responder") == true) {
        return true;
    } else {
    	return false;
    }
}

</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ManageResponders</title>
</head>
<body>
<h1>ManageResponders</h1>
	<a href="openAddResponderView">Add Responder</a>
	<h1>List of Contacts</h1>
	<hr>
	<c:if test="${not empty list}">
		<table border:20 style="width: 100%">
			<tr>
				<td>SL No</td>
				<td>First Name</td>
				<td>Last Name</td>
				<td>DOB</td>
				<td>Topic</td>
				<td>Email</td>
				<td>Password</td>
				<td>Is Active</td>
				<td>Edit Responder</td>
				<td>Delete Responder</td>
			</tr>
			<%
				int uid = 0;
			%>
			<c:forEach items="${list}" var="resp">
				<tr>
					<td><%=uid++%></td>
					<td>${resp.fname}</td>
					<td>${resp.lname}</td>
					<td>${resp.dob}</td>
					<td>${resp.topic}</td>
					<td>${resp.email}</td>
					<td>${resp.pass}</td>
					<td>${resp.isActive}</td>
					<td><a href="openEditResponderView?resp_sl_no=${resp.id}">
							Edit Responder</a></td>
					<td><a href="openDeleteResponderView?resp_sl_no=${resp.id}" onclick="return alertUserForDeleting()">
							Delete Responder</a></td>
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
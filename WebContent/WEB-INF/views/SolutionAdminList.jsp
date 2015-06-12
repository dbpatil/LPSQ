<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div style = "text-align:right; float:right">
      <a href="Logout"> Logout </a>
   </div>
   <br>
   
   <br>
   
   <h1>List Of Users</h1><br>
<c:if test="${not empty beans}">  
<table  border:20 style="width:100%">
<tr><th>SL No  </th><th> EMAIL  </th><th>ACTIVE STATUS</th></tr>  
<% int uid=0; %>
<c:forEach items="${beans}" var="beans">
<tr><th><%= uid++ %></th><th>${beans.email}</th><th><a href="makeStatusChange?sl_no=${beans.id}">
<c:if test="${beans.activationStatus eq 'false'}">
		InActive
</c:if>
<c:if test="${beans.activationStatus eq 'true'}">
		Active
</c:if>
</a> </th><%-- <th><a href="deleteDynamicForm?sl_no=${beans.sl_no}"> Delete Form</a></th> --%><%--  <th><a href="openDynamicReportView?sl_no=${beans.sl_no}"> Report View</a></th> --%></tr>
</c:forEach>
</table>
<hr>
<% uid=0; %>
</c:if>



</body>
</html>
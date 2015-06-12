<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">

function validateForm() {
    var x = document.forms["myForm"]["dob"].value;
    if (x == null || x == "") {
        alert("Kindly Choose the Date");
        return false;
    }
    
    var x = document.forms["myForm"]["isActive"].value;
    if (x == null || x == "") {
        alert("Kindly Choose Active or DeActive");
        return false;
    }
}


</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Responder</title>
</head>
<body>
	<h1>Registering Responder</h1>
	<s:form modelAttribute="bean" method="post" action="addResponder" name="myForm" onsubmit="return validateForm()">
	First Name<s:input path="fname"/>		<s:errors path="fname"/><br/>
	Last Name<s:input path="lname"/>		<s:errors path="lname"/><br/>
	Enter Date of Birth<input type="date" name="dob" id="dob" path="dob" placeholder="dd/MM/yyyy"/>		<s:errors path="dob"/><br/>	
	Select Topic<s:select path="topic">	<s:options items="${topics}"/>	</s:select>		<s:errors path="topic"/><br/>
	Enter Email<s:input path="email" type="email"/>		<s:errors path="email"/><br/>
	Enter Password<s:input path="pass" name="pass" type="password"/>		<s:errors path="pass"/><br/>	
	Active Status : Active<input type="radio" path="isActive" name="isActive" value="Activated" /><s:errors path="isActive"/>
					De-Active<input type="radio" path="isActive" name="isActive" value="DeActivated" />	<s:errors path="isActive"/><br/>
		<input type="submit" value="Add Responder">		
	</s:form>
</body>
</html>
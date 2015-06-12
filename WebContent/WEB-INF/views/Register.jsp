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
    
    var a = document.forms["myForm"]["pass"].value;
    var b = document.forms["myForm"]["rpass"].value;
    if (a === b) 
    {
        
    }else
    {
    	alert("Password and Repeat Password is Incorrect");
        return false;
    }
}


</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register Page</title>
</head>
<body>
	<h1>Register</h1>
	<s:form modelAttribute="bean" method="post" action="register" name="myForm" onsubmit="return validateForm()">
	First Name<s:input path="fname"/>		<s:errors path="fname"/><br/>
	Last Name<s:input path="lname"/>		<s:errors path="lname"/><br/>
	Enter Date of Birth<input type="date" name="dob" id="dob" path="dob" placeholder="dd/MM/yyyy"/>		<s:errors path="dob"/><br/>
	Enter Address<s:input path="address"/>		<s:errors path="address"/><br/>
	Enter Company<s:input path="company"/>		<s:errors path="company"/><br/>
	Select Training<s:select path="training">
	<s:options items="${trailist}"/>
	</s:select>		<s:errors path="training"/><br/>
	Enter Email<s:input path="email"/>		<s:errors path="email"/><br/>
	Enter Password<s:input path="pass" name="pass"/>		<s:errors path="pass"/><br/>
	Enter Repeat-Password<s:input path="rpass" name="rpass"/>		<s:errors path="rpass"/><br/>		
		<input type="submit" value="Register">		
	</s:form>
</body>
</html>
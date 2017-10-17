<%@page contentType="text/html;charset=ISO-8859-1"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags"  prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<link rel="stylesheet" type="text/css" href="../resources/styles/hours.css">
<title><spring:message code="login.title"/></title>
</head>
<body>

	<div id="langsel">
		<a href="?lang=en">en</a> | <a href="?lang=fi">fi</a>
	</div>
	
	<div id="login_content">
	<h1><spring:message code="login.title"/></h1>

	<c:if test="${not empty loginerror}">
		<p class="Error"><spring:message code="login.failed"/></p>
	</c:if>

	<c:if test="${not empty loggedout}">
		<p class="Info"><spring:message code="logout.success"/></p>
	</c:if>
	
	<form action="j_spring_security_check" method="post">
	<fieldset>
		<table>
		<tr><td><spring:message code="login.username"/></td><td><input type='text' name='j_username' value=''></td></tr>
		<tr><td><spring:message code="login.password"/></td><td><input type='password' name='j_password' /></td></tr>
		<tr><td>&nbsp;</td><td><button type="submit"><spring:message code="login.button"/></button></td></tr>
		</table>
	</fieldset>
	</form>
	</div>
</body>
</html>
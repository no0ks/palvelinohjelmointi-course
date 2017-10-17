<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags"  prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../../resources/styles/hours.css">
<title><spring:message code="main.title"/></title>
</head>
<body>

	<div id="langsel">
		<a href="?lang=en">en</a> | <a href="?lang=fi">fi</a>
	</div>
	
	<p style="font-style:italic;color:#808080"><spring:message code="logged.in"/><sec:authentication property="principal.username"/></p>
	<h1><spring:message code="program.title"/></h1>
	<p><spring:message code="hour.info"/></p>
	
	<form:form modelAttribute="allWorkHours" method="get">
			<table>
				<thead>
					<tr>
						<td><spring:message code="workhours.username"/></td>
						<td><spring:message code="workhours.firstname"/></td>
						<td><spring:message code="workhours.lastname"/></td>
						<td><spring:message code="workhours.hours"/></td>
						<td><spring:message code="workhours.workdate"/></td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${allWorkHours}" var="workHours">
						<tr>
							<td><c:out value="${workHours.username}" /></td>
							<td><c:out value="${workHours.firstName}" /></td>
							<td><c:out value="${workHours.lastName}" /></td>
							<td><c:out value="${workHours.hours}" /></td>
							<td><fmt:formatDate value="${workHours.workDate.time}" type="date" dateStyle="short" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form:form>
		
		<h3><spring:message code="personhours.total"/></h3>
		
		<form:form modelAttribute="peopleHours" method="get">
			<table>
				<thead>
					<tr>
						<td><spring:message code="workhours.username"/></td>
						<td><spring:message code="workhours.firstname"/></td>
						<td><spring:message code="workhours.lastname"/></td>
						<td><spring:message code="workhours.hours"/></td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${peopleHours}" var="personHours">
						<tr>
							<td><c:out value="${personHours.username}" /></td>
							<td><c:out value="${personHours.firstName}" /></td>
							<td><c:out value="${personHours.lastName}" /></td>
							<td><c:out value="${personHours.hours}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form:form>
	
	<p><a href="../main"><spring:message code="main.title"/></a></p>
	<p><a href="../../j_spring_security_logout" ><spring:message code="logout.link"/></a></p>

</body>
</html>
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
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="../resources/styles/hours.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
  $(function() {
    $("#datepicker").datepicker();
  });
</script>
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
	
	<form:form modelAttribute="personHours" method="get">
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
				<tr>
					<td><c:out value="${personHours.username}" /></td>
					<td><c:out value="${personHours.firstName}" /></td>
					<td><c:out value="${personHours.lastName}" /></td>
					<td><c:out value="${personHours.hours}" /></td>
				</tr>
			</tbody>
		</table>
	</form:form>
	
	<form:form modelAttribute="workHours" method="post">
		<fieldset>		
			<legend><spring:message code="main.add.workhours"/></legend>
			
			<spring:hasBindErrors name="workHours">
					<p class="ErrorHeading"><spring:message code="main.add.errors" /></p>
			</spring:hasBindErrors>
			<table>
				<thead>
					<tr>
						<td><form:label	path="hours"><spring:message code="workhours.hours"/></form:label></td>
						<td><form:label	path="workDate"><spring:message code="workhours.workdate"/></form:label></td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><form:input path="hours" cssErrorClass="ErrorField"/></td>
						<td><form:input path="workDate" id="datepicker" cssErrorClass="ErrorField"/></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td><form:errors path="hours" cssClass="ErrorText"/></td>
						<td><form:errors path="workDate" cssClass="ErrorText"/></td>
					</tr>
				</tfoot>
			</table>
			<button type="submit"><spring:message code="submit.add"/></button>
		</fieldset>
	</form:form>

<sec:authorize access="hasRole('ROLE_ADMIN')">
	<p><a href="admin/adminpage"><spring:message code="main.admin.link"/></a></p>
</sec:authorize>
	<p><a href="../j_spring_security_logout"><spring:message code="logout.link"/></a></p>

</body>
</html>
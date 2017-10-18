<%@page contentType="text/html;charset=ISO-8859-1"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Painonhallinta</title>
<spring:url value="/resources/styles/paino.css" var="painoCss" />
<link href="${painoCss}" rel="stylesheet" />
</head>

<body>
	<h1>Painonhallinta</h1>
	<form:form modelAttribute="entries" method="get">
		<table>
			<thead>
				<tr><td>Paino (kg)</td>
				<td>Päivämäärä</td></tr>
			</thead>
			<tbody>
				<c:forEach items="${entries}" var="entry">
					<tr>
						<td><c:out value="${entry.kg}" /></td>
						<td><fmt:formatDate value="${entry.entryDate}" pattern="dd.MM.yyyy HH:mm"/></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</form:form>
	
	<form:form modelAttribute="weight" method="post">
		<fieldset>		
			<legend>Lisää tämänhetkinen paino</legend>
			<p>
				<form:label	path="kg">Paino (kg)</form:label><br/>
				<form:input path="kg"/>
			</p>
			<p>
				<button type="submit">Lisää</button>
			</p>
			<p><c:out value="${message}" /></p>
		</fieldset>
	</form:form>
		
</body>
</html>
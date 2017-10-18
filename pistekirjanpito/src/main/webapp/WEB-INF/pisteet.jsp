<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pisteet</title>
<link rel="stylesheet" type="text/css" href="styles/common.css">
<link rel="stylesheet" type="text/css" href="styles/table.css">
</head>
<body>
	<h2>Pisteet</h2>
	<form action="pisteet" method="get">
		<table>
			<thead><tr>
				<td>Oppilasnro</td>
				<td>Arvosana</td>
				<td></td></tr>
			</thead>
			<tbody>
				<c:forEach items="${pisteet}" var="piste">
					<tr>
						<td><c:out value="${piste.oppilasnro}" /></td>
						<td><c:out value="${piste.arvosana}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>
	
	<c:if test="${sessionScope.admin == 'Y'}">
		<h3>Lisää uusi suoritus:</h3>
		<form action="pisteet" method="post">
			<table>
				<thead><tr>
					<td>Oppilasnro</td>
					<td>Arvosana</td>
				</tr></thead>
				<tr><td><input type="text" name="oppilasnro" maxlength="7"/></td>
				<td><select name="arvosana">
					<option value="0">0</option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
				</select></td>
				<td><button type="submit" name="lisaa">Lisää</button></td></tr>
			</table>
		</form>
	</c:if>
	
	<div>
		<p><c:out value="${viesti}"/></p>
		<a href="logout">Kirjaudu ulos</a>
	</div>
	
</body>
</html>
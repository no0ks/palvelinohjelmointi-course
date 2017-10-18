<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pistekirjanpito</title>
<link rel="stylesheet" type="text/css" href="styles/common.css">
<link rel="stylesheet" type="text/css" href="styles/table.css">
</head>
<body>
	<h2>Pistekirjanpito</h2>
	<form action="login" method="post">
		<table>
		<tr><td>Käyttäjätunnus</td><td><input type="text" name="kayttajatunnus" value="<c:out value='${prev_login_username}'/>"/></td></tr>
		<tr><td>Salasana</td><td><input type="password" name="salasana"/></td></tr>
		<tr><td><button type="submit">Kirjaudu sisään</button></td></tr>
		</table>
	</form>
	<p><c:out value="${viesti}"/></p>
</body>
</html>

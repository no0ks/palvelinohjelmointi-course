<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="osoitteet.css">
<title>Osoitteet</title>
</head>
<body>
	<h1>Osoitteet</h1>
	<form action="osoitteet" method="post">
		<table>
			<thead><tr>
				<td>Etunimi</td>
				<td>Sukunimi</td>
				<td>Katuosoite</td>
				<td>Postinumero</td>
				<td>Postitoimipaikka</td>
				<td></td></tr>
			</thead>
			<tbody>
				<c:forEach items="${osoitteet}" var="osoite">
					<tr>
						<td><c:out value="${osoite.etunimi}" /></td>
						<td><c:out value="${osoite.sukunimi}" /></td>
						<td><c:out value="${osoite.katuosoite}" /></td>
						<td><c:out value="${osoite.postinro}" /></td>
						<td><c:out value="${osoite.postitmp}" /></td>
						<td><button type="submit" name="poista" value="${osoite.henkiloId}">Poista</button></td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
			<tr><td>Lis‰‰ uusi osoite:</td></tr>
			<tr><td><input type="text" name="etunimi"/></td>
			<td><input type="text" name="sukunimi"/></td>
			<td><input type="text" name="katuosoite"/></td>
			<td><input type="text" name="postinro"/></td>
			<td><input type="text" name="postitmp"/></td>
			<td><button type="submit" name="lisaa">Lis‰‰</button></td></tr>
			</tfoot>
		</table>
	</form>
	<p><c:out value="${viesti}"/></p>
</body>
</html>
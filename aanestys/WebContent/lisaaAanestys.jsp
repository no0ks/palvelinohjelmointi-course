<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lisää äänestys</title>
</head>
<body>
	<a href="index.jsp">Etusivu</a>
	<a href="lisaaAanestys.jsp">Lisää uusi äänestys</a>
	<a href="aanestystulokset.jsp">Äänestystulokset</a>
	<p>Lisää uusi äänestys</p>
	<form method="post" action="UusiAanestys">
		<table>
			<tr><td><input type="text" name="kysymys" maxlength="255"></input></td>
			<td><input type="submit" value="Lisää"></input></td></tr>
		</table>
	</form>
	<p></p>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bean.Aanestys"%>
<%@ page import="dao.AanestysDao"%>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Äänestys</title>
</head>
<body>
	<a href="index.jsp">Etusivu</a>
	<a href="lisaaAanestys.jsp">Lisää uusi äänestys</a>
	<a href="aanestystulokset.jsp">Äänestystulokset</a>
	
	<form method="post" action="PaivitaTulos">
		<table>
			<tr>
				<th>Kysymys</th>
				<th>Vastaus</th>
			</tr>
<%
ArrayList<Aanestys> aanestykset = new AanestysDao().listaaAanestykset();
	for(int i=0;i<aanestykset.size();i++) {
		out.print("<tr><td><input type='hidden' name='aanestysId' value='" + aanestykset.get(i).getAanestysId() + "'></input></td>");
		out.print("<tr><td>" + aanestykset.get(i).getKysymys() + "</td>");
		out.print("<td><select name='vastaus'><option value='0'></option><option value='1'>Kyllä</option><option value='2'>Ei</option></select></td></tr>");	
	}
%>
			<tr><td></td><td><input type="submit" value="Lähetä"></input></td></tr>
		</table>
	</form>
</body>
</html>
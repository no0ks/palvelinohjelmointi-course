<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bean.Aanestys"%>
<%@ page import="dao.AanestysDao"%>
<%@ page import="java.util.ArrayList"%>   
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Äänestystulokset</title>
</head>
<body>
	<a href="index.jsp">Etusivu</a>
	<a href="lisaaAanestys.jsp">Lisää uusi äänestys</a>
	<a href="aanestystulokset.jsp">Äänestystulokset</a>
	
	<table>
		<tr>
		<th>Kysymys</th>
		<th>Kyllä</th>
		<th>Ei</th>
		</tr>
<%
ArrayList<Aanestys> aanestykset = new AanestysDao().listaaAanestykset();
for(int i=0;i<aanestykset.size();i++) {
	out.print("<tr><td>" + aanestykset.get(i).getKysymys() + "</td>");
	out.print("<td>" + aanestykset.get(i).getVastausKylla() + "</td>");
	out.print("<td>" + aanestykset.get(i).getVastausEi() + "</td></tr>");
}
%>
	</table>
</body>
</html>
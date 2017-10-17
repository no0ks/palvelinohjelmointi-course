<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="bean.PaperinOstaja"%>
<%@ page import="dao.VessapaperiDao"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Vessapaperiostokset</title>
</head>
<body>
	<h1>Vessapaperiostokset</h1>
	<form action="VessapaperiServlet" method="post">
		<table>
			<tr><th>Ostaja</th>
			<th>Ostettu (pkt)</th>
			<th>Ostettava (pkt)</th>
			<th>Uusi ostos</th>
			<th>P‰ivitetty</th></tr>
<%
try {
	ArrayList<PaperinOstaja> ostajat = new VessapaperiDao().listaaOstajat();
	SimpleDateFormat fdate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	for(int i=0;i<ostajat.size();i++) {
		out.print("<tr><input type='hidden' name='ostajaId' value='" + ostajat.get(i).getOstajaId() + "'></input>");
		out.print("<td>" + ostajat.get(i).getOstajaNimi() + "</td>");
		out.print("<td>" + ostajat.get(i).getOstettuMaara() + "</td>");
		out.print("<td>" + ostajat.get(i).getOstettava() + "</td>");
		out.print("<td><input type='number' name='uusiOstos' min='0' max='100'></input></td>");
		out.print("<td>" + fdate.format(ostajat.get(i).getPaivitetty()) + "</td></tr>");
	}
	out.print("</table>");
	out.print("<button type='submit' name='lisaa'>Lis‰‰</button>");
	boolean nollattavissa = true;
	for(int i=0;i<ostajat.size();i++) {
		if(ostajat.get(i).getOstettava()!=0) {
			nollattavissa = false;
		}
	}
	if(nollattavissa) {
		out.print("<button type='submit' name='nollaa' value='nollaa'>Nollaa laskuri</button>");
	} else {
		out.print("<button type='submit' disabled name='nollaa'>Nollaa laskuri</button>");
	}
} catch (Exception e) {
	e.printStackTrace();
	String viesti = e.getMessage();
	out.print("</table>" + viesti);
}
String viesti = (String) request.getAttribute("viesti");
if(viesti!=null && !"".equals(viesti)) {
	out.print("</table>" + viesti);
}
%>
	</form>
</body>
</html>
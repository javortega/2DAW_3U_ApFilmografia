<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="filmografia.vista.*" %>
<%@ page import="java.util.*" %>
<%@ page import="filmografia.beans.Director" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>Estos son los directores consultados:</h3>
<%
String nombreDirector="";
Iterator<Director> iterator;
ListaDirectores listDirectores =(ListaDirectores) session.getAttribute("directores");
iterator=listDirectores.getListaDirectores();
while(iterator.hasNext()){
%>
 <p><%=iterator.next().getNombre() %></p>
<%} 
%>
<%

session.invalidate(); %>
<form action="validadirector" method="get">
<input type="submit" value="Finalizar">

</form>

</body>
</html>
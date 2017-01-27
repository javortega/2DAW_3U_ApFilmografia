<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="filmografia.vista.ListaDirectores" %>
<%@ page import="filmografia.beans.Director" %>
<%@ page import="java.util.Iterator" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String nombreDirector="";
ListaDirectores listaCompleta=null;
Iterator<Director> iterator=null;

if(request.getAttribute("listaCompletaDirectores")!=null){
listaCompleta=(ListaDirectores)request.getAttribute("listaCompletaDirectores");
iterator = listaCompleta.getListaDirectores();
session.setAttribute("listaCompletaDirectores", listaCompleta);

%>
<form method="post" action="validadirector">
		<label>Introduce el nombre del directorTrue</label><br>
		<select name="directores">
<%
while(iterator.hasNext()){ 
nombreDirector=iterator.next().getNombre();
%>
		<option value="<%=nombreDirector%>"><%=nombreDirector %></option>
<%
			}
%>		
		</select><br>
<input type="submit" value="Consultar">		
</form>	
<%
}else{
listaCompleta=(ListaDirectores)session.getAttribute("listaCompletaDirectores");
iterator = listaCompleta.getListaDirectores();
%>
<form method="post" action="validadirector">
		<label>Introduce el nombre del directorFalse</label><br>

		<select name="directores">
<%
while(iterator.hasNext()){
nombreDirector=iterator.next().getNombre();
%>		
		<option value="<%=nombreDirector%>"><%=nombreDirector %></option>
<%
				}
%>		
		</select><br>
<input type="submit" value="Consultar">		
</form>	
<%
	}
%>
</body>
</html>
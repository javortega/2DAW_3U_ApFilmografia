<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="filmografia.vista.ListaPeliculas" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="filmografia.beans.Director" %>        
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
<%
	
	ListaPeliculas lista=(ListaPeliculas)request.getAttribute("listaPeliculas");
	ArrayList<Director>directoresConsultados=null;
	//Pregunto por el atributo "directores" si es null creo el contenedor y añado el objeto Director
	if(session.getAttribute("directores")==null){
	directoresConsultados=new ArrayList<Director>();
	directoresConsultados.add(lista.getDirector());
	session.setAttribute("directores",directoresConsultados);
 }else{
 	
 	int contador = 0;
 	directoresConsultados=(ArrayList<Director>)session.getAttribute("directores");
 	for(Director director:directoresConsultados){
 	if(request.getParameter("nombre").equals(director.getNombre()))
 	contador++;
 }
 if(contador==0)
 directoresConsultados.add(new Director(request.getParameter("nombre")));
 }
 %>
 <p>Estas son las películas de este director==> <%=lista.getTituloPeliculas() %></p>
 <form action="index.html">
 <input type="submit" value="Consultar de nuevo">
 </form>
 <form action="directoresconsultados.jsp" method="post">
 <input type="submit" value="Finalizar">
 
 </form>
</body>
</html>
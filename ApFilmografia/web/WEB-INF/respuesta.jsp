<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="filmografia.vista.*" %>
<%@ page import="filmografia.utilidades.Contador" %>
<%@ page import="filmografia.beans.Director" %>        
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>

<jsp:useBean id="contador" class="filmografia.utilidades.Contador" scope="session"/>
<%
	//La lista de peliculas no debe ir en la sesion
	
	
	ListaPeliculas lista=(ListaPeliculas)request.getAttribute("listaPeliculas");
	ListaDirectores listaDir=null;
	
	if(contador.getNumAccesos()==0){
	
	 listaDir = new ListaDirectores();
	 listaDir.addDirectores(lista.getDirector());
	 session.setAttribute("directores", listaDir);
	 int acumulador=Integer.parseInt(application.getInitParameter("acumulador"));
%><jsp:setProperty property="numAccesos" name="contador" value="<%=acumulador %>"/>	
<%	
	}else{
	 listaDir =(ListaDirectores) session.getAttribute("directores");
	 listaDir.addDirectores(lista.getDirector());
	}
	if(lista.isVacio()){
%><p>No hay peliculas en la lista</p>	
<%	}else{
	
//Pregunto por el atributo "directores" si es null creo el contenedor y añado el objeto Director
%><p>Estas son las películas de este director==> <%=lista.getTituloPeliculas() %></p>
<% 
} 
%> 
 <form action="index.jsp">
 <input type="submit" value="Consultar de nuevo">
 </form>
 <form action="validadirector" method="post">
 <input type="submit" value="Finalizar">
 <input type="hidden" name="accion" value="directoresconsultados">
 </form>
</body>
</html>
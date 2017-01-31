<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="filmografia.vista.*" %>

<%@ page import="filmografia.beans.Director" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>       
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Respuesta</title>

</head>
<body>
<!--ListaPeliculas lista =(ListaPeliculas)request.getAttribute("listaPeliculas");
listaDirectores.setAddDirectores(lista.getDirector());  -->

<jsp:useBean id="listaDirectores" class="filmografia.vista.ListaDirectores" scope="session"/>
<c:set var="lista" value="${requestScope.listaPeliculas}" scope="page"/>

<%
ListaPeliculas listaPeliculas =(ListaPeliculas) request.getAttribute("listaPeliculas");
listaDirectores.addDirector(listaPeliculas.getDirector());
%>

<c:choose>
<c:when test="${lista.isVacio}">
<p>No hay peliculas en la lista</p>
<form action="validadirector">
 <input type="submit" value="Volver">
 </form>
</c:when>
<c:otherwise>
<p>Estas son las películas de este director==> <c:out value="${lista.tituloPeliculas }"/></p>
</c:otherwise>
</c:choose>
  <form action="validadirector">
 <input type="submit" value="Consultar de nuevo">
 <input type="hidden" name="accion" value="index">
 </form>
 <form action="validadirector" method="post">
 <input type="submit" value="Finalizar">
 <input type="hidden" name="accion" value="directoresconsultados">
 </form>
</body>
</html>
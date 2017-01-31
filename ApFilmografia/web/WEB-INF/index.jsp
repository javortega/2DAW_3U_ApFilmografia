<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="filmografia.vista.ListaDirectores" %>
<%@ page import="filmografia.beans.Director" %>
<%@ page import="java.util.Iterator" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:choose>
<c:when test="${requestScope.listaCompletaDirectores!=null}">
<c:set var="listaCompletaDirectores" value="${requestScope.listaCompletaDirectores}" scope="session"/>
<c:set var="iterator" value="${listaCompletaDirectores.listaDirectores}" scope="page"/>
<form method="post" action="validadirector">
		<label>Elige entre los directores disponibles:</label><br>
		<select name="directores">
		<c:forEach items="${iterator}" var="director">	
		<option value="${director.nombre}"><c:out value="${director.nombre}"/></option>
		</c:forEach>
		</select><br>
		<input type="submit" value="Consultar">		
</form>	
</c:when>
<c:otherwise>
<c:set var="iterator" value="${listaCompletaDirectores.listaDirectores}" scope="page"/>
<form method="post" action="validadirector">
		<label>Introduce el nombre del directorFalse</label><br>
		<select name="directores">
		<c:forEach items="${iterator}" var="director">	
		<option value="${director.nombre}"><c:out value="${director.nombre}"/></option>
		</c:forEach>
		</select><br>
		<input type="submit" value="Consultar">		
</form>
</c:otherwise>
</c:choose>
</body>
</html>
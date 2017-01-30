<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="filmografia.vista.*" %>
<%@ page import="java.util.*" %>
<%@ page import="filmografia.beans.Director" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>Estos son los directores consultados:</h3>

<c:set var="listaDirectoresConsultados" value="${sessionScope.listaDirectores}" scope="page"/>
<c:set var="iterator" value="${listaDirectoresConsultados.listaDirectores}" scope="page"/>
<c:forEach items="${iterator}" var="director">	
<c:out value="${director.nombre }"/><br> 
</c:forEach>
<%
session.invalidate(); 
%>
<form action="validadirector" method="post">
<input type="submit" value="Finalizar">

</form>

</body>
</html>
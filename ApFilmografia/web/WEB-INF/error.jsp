<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<%@ page import="filmografia.vista.excepciones.ExcepcionAp" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	ExcepcionAp err = (ExcepcionAp) request.getAttribute("error");
%> 
Se ha producido un error.<br>
El mensaje de la excepción es: <%=err.getMensajeError()%> <br>

<form action="validadirector" method="post">
<input type="hidden" value="index" name="accion">
<input type="submit" value="Inicio">
</form>
</body>
</html>
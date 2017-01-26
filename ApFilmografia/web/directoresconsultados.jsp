<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
String nombreDirector="";
if(session.isNew()){
%>
<jsp:forward page="index.html"/>

<%
}else{
if(session.getAttribute("directores")==null){
%>
<jsp:forward page="index.html"/>

<%}
ArrayList<Director>directores=(ArrayList<Director>)session.getAttribute("directores");

for(Director director:directores){
nombreDirector+=director.getNombre()+" ";
}
}
 %>
 <p>Aqui mostramos los directores que se han consultado--> <%=nombreDirector %></p>
<%session.invalidate(); %>
<form action="index.html" method="get">
<input type="submit" value="Finalizar">

</form>

</body>
</html>
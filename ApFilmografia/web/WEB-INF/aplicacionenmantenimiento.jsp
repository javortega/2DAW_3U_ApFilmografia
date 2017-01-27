<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">

p{
color:red;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Información al usuario</title>
</head>
<body>
<p><%=(String)request.getAttribute("error") %></p>
</body>
</html>
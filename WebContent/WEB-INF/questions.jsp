<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
	<%@page import="java.util.ArrayList" %>
	<%@page import="java.util.*" %>
<html>
<head>
<title>questions</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="http://testapp26.mybluemix.net/bootstrap/css/bootstrap.min.css"> 
</head>
<body>
<jsp:useBean id="dictionary" class="java.util.Hashtable" />
  <c:forEach var="bean" items="${list}">
    <a href="http://testapp26.mybluemix.net/detail?q=${bean.key}"<h2>${bean.value}</h2>>
  </c:forEach>
</body>
</html>
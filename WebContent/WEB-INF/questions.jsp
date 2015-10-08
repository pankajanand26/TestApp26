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
<div class="container">
<h2>Poll Questions</h2>
<ul class="list-group">
  <c:forEach var="bean" items="${list}">
    <li class="list-group-item"><h3><a href="http://testapp26.mybluemix.net/detail?q=${bean.key}">${bean.value}</a></h3></li>
  </c:forEach>
</ul>
</div>
</body>
</html>
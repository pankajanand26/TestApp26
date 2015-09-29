<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
	<%@page import="java.util.ArrayList" %>
<html>
<head>
<title>questions</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>Hello
  <c:forEach var="bean" items="${list}">
    <h3>${bean}</h3>
  </c:forEach>
</body>
</html>
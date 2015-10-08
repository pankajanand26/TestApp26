<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
	<%@page import="java.util.ArrayList" %>
	<%@page import="java.util.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Hello ${uname}
<div class="container">
<h2>Poll Questions</h2>
<ul class="list-group">
  <c:forEach var="bean" items="${list}">
    <li class="list-group-item">${bean}</li>
  </c:forEach>
</ul>
</div>

</body>
</html>
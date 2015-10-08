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
<link rel="stylesheet" type="text/css" href="http://testapp26.mybluemix.net/bootstrap/css/bootstrap.min.css"> 
</head>
<body>
Hello ${uname}
<div class="container">
<h2>${ques}</h2>
<ul class="list-group">
  <c:forEach var="bean" items="${list}">
    <li class="list-group-item">${bean}</li>
  </c:forEach>
</ul>


<form action="http://testapp26.mybluemix.net/result" method="get"> 

<div class="panel panel-primary">
<div class="panel-heading"><h1>${ques}</h1></div>
  <div class="panel-body">


<table>
  <div class="fieldWrapper"> 
  <c:forEach var="bean" items="${list}">

    <tr><td>
    <div class="radio">
    <label><input type="radio" value="${bean}" name="option" id="option" ></label>
  	</div>
  	</td>
  	</tr>
   </c:forEach>
 
  </div>  
  	<tr><td padding: 20px;>
<input type="submit" value="Submit" /></td></tr> 
</table>
</div>
</div>
</form>
</div>
</body>
</html>
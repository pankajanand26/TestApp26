<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@page import="java.util.ArrayList" %>
<html>
<head>
<title>questions</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
  <c:forEach var="bean" items="${list}">
    <h3>${bean.label}</h3>
  </c:forEach>
<%  
ArrayList<String> qlist = new ArrayList<String>();
// retrieve your list from the request, with casting 
qlist = (ArrayList<String>) request.getAttribute("list");

// print the information about every category of the list
for(String question : qlist) {
    out.println("<h2>"+question+"</h2><br/>");
}
%>

</body>
</html>
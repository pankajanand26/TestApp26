<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%! String error_message=""; %>
<html>
<head>
<title>index</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<div class="container">
<h1>Sign In</h1>
<% if(!(error_message.isEmpty())){ %><p><strong>${error_message}</strong></p><% } %>
<form action="/servlet2" method="get"> 

<table>
  <div class="fieldWrapper"> 
    <tr> 
      <td>Hello </td><td>World!</td><td> hello </td></tr> 
  </div>

<tr><td padding: 20px;><input type="submit" value="Submit" /></td><td></td></tr> 
</table>
</form>
</div>

</body>
</html>
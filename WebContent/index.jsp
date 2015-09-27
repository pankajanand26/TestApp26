<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%! String error_message=""; %>
<html>
<head>
<title>index</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="http://testapp26.mybluemix.net/bootstrap/css/bootstrap.min.css"> 

</head>
<body>

<div class="container">
<h1>Sign In</h1>
<% if(!(error_message.isEmpty())){ %><p><strong>${error_message}</strong></p><% } %>
<form action="http://localhost:9080/liberty-HelloWorld/servlet2" method="get"> 

<table>
  <div class="fieldWrapper"> 
    <tr><td>
    <div class="form-group">
    <label for="exampleInputEmail1">Username</label>
    <input type="uname" class="form-control" name="uname" id="uname" placeholder="Username">
  	</div>
  	<div class="form-group">
    <label for="exampleInputPassword1">Password</label>
    <input type="pass" class="form-control" name="pass" id="pass" placeholder="Password">
  	</div></td></tr>
  	</div>
	<tr><td padding: 20px;>
<input type="submit" value="Submit" /></td><td></td></tr> 
</table>
</form>
</div>
<h3>Hello,&nbsp;${name}</h3></h3><br/>
${login_info}
</body>
</html>
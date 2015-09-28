<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>register</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="http://testapp26.mybluemix.net/bootstrap/css/bootstrap.min.css"> 

</head>
<body>

<div class="container">
<h1>Register</h1>

<h4 style="color:red">${error}</h4>

<form action="http://testapp26.mybluemix.net/register" method="get"> 
	<table>
	<div class="form-group"> 
	<tr>
    	<td><label for="exampleInputEmail1">Username</label></td>
    	<td><input type="text" class="form-control" name="uname" id="uname" placeholder="Username"></td>
	</tr>
  	<tr>
    	<td><label for="exampleInputPassword1">Password</label></td>
    	<td><input type="password" class="form-control" name="pass" id="pass" placeholder="Password"></td>
	</tr>
  	<tr>
    	<td><label for="exampleInputPassword1">Confirm Password</label></td>
    	<td><input type="text" class="form-control" name="cpass" id="cpass" placeholder="Password"></td>
	</tr>
  	<tr>
    	<td><label for="exampleInputEmail">Email Id</label></td>
    	<td><input type="email" class="form-control" name="email" id="email" placeholder="abc@xyz.com"></td>
	</tr>
  	</div>
<tr><td></td><td padding:20px;> 
    <input type="submit" value="Submit"/></td></tr>
<tr><td></div>
<h4>Already registered <a href="http://testapp26.mybluemix.net/">Log in</a>. </h4>
</div>
</td></tr>
</table>
</form>

</body>
</html>
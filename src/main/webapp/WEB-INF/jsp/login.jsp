<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script language="JavaScript">
  javascript:window.history.forward(1);
</script>
<style>
h1
{
	color:  white ;
	font-size: 120%;
	font-family: Helvetica;
}

body
{
 background-color: lightgrey;

}

td
{
font-size: 12px; 
}
legend
{
color: purple;
font-family: fantasy;
}

#h
{

background-color:  #09529d ;
}


</style>
<script>
function fun()
{
	var uname=document.form.uname.value;
	var pass=document.form.pass.value;
	
if(uname=="" || uname==null)
		
		{
		alert("UserName is required field");  
		  return false;  
		}
else if	(pass=="" || pass==null)
{
	alert("password is required field");  
	  return false;  
	}
	
var form=new FormData();
form.append("name",uname);
form.append("pass",pass);


$ajax({
	
	  url: 'login',
	  data: form,
	  processData: false,
	  contentType: false,
	  type: 'POST',
	  success: function(data){
	    alert(data);
	  }
	
	
});


}
</script>
</head>
<body>
<div id="h">
<br>
<h1 align="center">LOGIN PAGE..!</h1>
<hr>
</div >
<br>
<form action="login" name="form" method="post" onsubmit="return fun()">
<table>
<tr>
<td>USER_NAME</td>
<td><input type="text" name="uname"></td>
</tr>

<tr>
<td>PASSWORD</td>
<td><input type="password" name="pass"></td>
</tr>

</table>
<br>
<input type="submit" value="LOGIN">
</form>
</body>
</html>
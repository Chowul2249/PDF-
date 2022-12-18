<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@page language="java" import="java.util.*" %>	
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"       href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
   <!--  <link rel="stylesheet" href="/resources/demos/style.css"> -->

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
</head>
<body>
<div id="fullDiv">
<div id="h">
<br>
<h1 align="center">Welcome ${message}..!</h1>
<hr>
</div >
<br>
<script language="JavaScript">
  javascript:window.history.forward(1);
</script>
<p align="right"><a href="logout" ><input type="submit" value="Logout" ></a></p>

<%-- <form method="post" action="pregi" onsubmit="return MyFunction();" enctype="multipart/form-data"> --%>

<fieldset>	
<legend>Please be register your personal details..</legend>
<div id="halfDiv">	
<table>
<tr>
<td>
<input type="hidden" name="id" id="id" value="${object.id}">
</td></tr>
<tr>
<td> ENTER YOUR FULL_Name:<sup style="color: red;">*</sup></td>
<td><input type="text" id="name" name="name" value="${object.name}"></td> 
<td id="Error1" style="color:red; display: none" >Please enter the name</td>
</tr>
<tr>
<td> ENTER YOUR FATHER_NAME:<sup style="color: red;">*</sup></td>
<td><input type="text" id="fname" name="fname" value="${object.fname }" ></td> 
<td id="Error2" style="color:red; display: none" >Please enter the Fathername</td>
</tr>
<tr>
<td> ENTER YOUR DOB:<sup style="color: red;">*</sup></td>
<td><input type="text" id="date" name="date" value="${object.date}"readonly="readonly" > </td>
<td id="Error3" style="color:red; display: none" >Please choose the Date</td>
</tr>

<tr>
<td> ENTER YOUR EMAIL_ID:<sup style="color: red;">*</sup></td>
<td><input type="email" id="email" name="email" value="${object.email}"  ></td>
<td id="Error4" style="color:red; display: none" >Please enter valid Email-ID</td>
</tr>

<tr>
<td> GENDER:<sup style="color: red;">*</sup></td>
<td><input type="radio" id="sex" name="sex" value="M" ${object.sex =="M"?'checked':''}>Male
<input type="radio" id="sex" name="sex" value="F" ${object.sex =="F"?'checked':''}>Female</td>
<td id="Error5" style="color:red; display: none" >Please choose the gender</td>
 
</tr>

<tr>
<td> ENTER YOUR PHONE NUMBER:<sup style="color: red;">*</sup></td>
<td><input type="text" id="phone" name="phone" maxlength=10 value="${object.phone}" ></td>
<td id="Error6" style="color:red; display: none" >Please enter the Phone number</td>
</tr>

<tr>
<td>UPLOAD YOUR PHOTO:<sup style="color: red;">*</sup></td>
<td><input type="file" id="file" name="file" value="${object.photo}"></td>
<td id="Error7" style="color:red; display: none" >Please choose the file</td>
<td id="Error8" style="color:red; display: none" >Please choose the image file</td>
</tr>
<tr></tr>
<tr></tr>
<tr></tr>
<tr></tr>
<tr></tr>
<tr></tr>
<tr></tr>
<tr></tr>
<tr>
<td>
<input type="submit" id="reg"value="Register" onclick="return MyFunction();"></td> <!-- onclick="MyFunction();" -->

<td><input type="submit" id="upd" value="update" onclick="up();" hidden></td>
</tr>
</table>
</div>  




</fieldset>	

<br>
	

	

<br>

<br>
<table border="1.5">
<tr>
<th>SI.NO</th>
<th>NAME</th>
<th>DATE OF BIRTH</th>
<th>PHONE</th>
<th>DELETE</th>
<th>UPDATE</th>
<th>DOWNLOAD</th>
</tr>
<c:forEach items="${list}" var="li" varStatus="serial">
<tr>
<td>${serial.index+1 }</td>
<td>${li.name }</td>
<td>${li.date}</td>
<td>${li.phone}</td>
<td>

<button onclick="deletee(${li.id})"> Delete</button>

 </td>

<td>
<button onclick="update(${li.id})"> Update</button>	 

</td>
 <td>
<a href="pdf/${li.id}/${li.name}.pdf" download><button><img src="resources/image/d.png" height="20px" width="30px" /></button></a>


</td>
</tr>	
</c:forEach>

</table>
<br>
<a href="excel"><input type="submit" value="EXCEL DOWNLOAD" ></a>
</div>

<script type="text/javascript">


function update(id)
{

$.ajax({
	
		type: 'POST',
	  url: 'updateCrud',
	  data: "id1="+id,
	  success: function(response){
		  
	  $("#fullDiv").html(jQuery(response).find("#halfDiv").html());
	  $("#reg").hide();
	  $("#upd").show();
	  	
	  }
	
});

}

$(function() {
    $( "#date" ).datepicker({
    	dateFormat: 'dd/mm/yy',//check change
    	  changeMonth: true,
    	  changeYear: true,
    	  yearRange: "1950:2050"
    	
    });
 });

function up()
{
	var id=$("#id").val();
	var name1=$("#name").val();
	 var fname1=$("#fname").val();
	 var date1=$("#date").val();
	 var email1=$("#email").val();
	 var gender1=$("input[name=sex]:checked").val();
	 var x6=$("#phone").val();
	 var files =$("#file")[0].files[0];

	 var x1=$.trim(name1);
	 var x2=$.trim(fname1);
	 var x3=$.trim(date1);
	 var x4=$.trim(email1);
	 var x5=$.trim(gender1);
	 var x7=$.trim(files);
	 
	 if(x1=="" || x2==""||x3=="" || x4==""||x5=="" || x6==""||x7=="")
		 {
		 	if(x1=="")
				{
					 $("#Error1").show()
			    }
		 	if(x2=="")
		 		{	
		 			$("#Error2").show()
		 
		 		}
		 	if(x3=="")
		 		{
		 			$("#Error3").show()	 
		 		}
		 	if(x4=="")
		 		{
		 			$("#Error4").show()
		 		}
		 	if(x5=="")
		 		{
		 			$("#Error5").show()
		 		}
		 	if(x6=="")
		 		{
		 			$("#Error6").show()
		 		}
		 	if(x7=="")
		 		{
			    	 $("#Error7").show()	 
		 		}
		 	 else
				 {
			 		var extension = $("#file").val().split('.').pop().toUpperCase();
			 		if (extension != "PNG" && extension != "JPG" && extension != "GIF" && extension != "JPEG") 
				 		{
				 			$("#Error8").show()
				 
				 		}
			 	 } 
		
		 }
	 else
		 {
		 
		 var form=new FormData();
		 
		 form.append("name",name1);	
		 form.append("fname",fname1);
		 form.append("date",date1);
		 form.append("sex",gender1);
		 form.append("phone",x6);
		 form.append("email",email1);
		 form.append("files", files); 
		 form.append("id",id);

	$.ajax({
		  url: 'up',
		  data: form,
		  enctype: 'multipart/form-data',
		  processData: false,
		  contentType: false,
		  type: 'POST',
		  success: function(response){
			
			  //$("#halfDiv").html(jQuery(response).find("#fullDiv").html());
			  
			$("#fullDiv").html(response);
			  alert("Sucessfully registered");
		    
		  }
		});
	
	return true;
	}
		

}

 
 
 
 
function  MyFunction()
{
 
 var name1=$("#name").val();
 var fname1=$("#fname").val();
 var date1=$("#date").val();
 var email1=$("#email").val();
 var gender1=$("input[name=sex]:checked").val();
 var x6=$("#phone").val();
 var files =$("#file")[0].files[0];

 var x1=$.trim(name1);
 var x2=$.trim(fname1);
 var x3=$.trim(date1);
 var x4=$.trim(email1);
 var x5=$.trim(gender1);
 var x7=$.trim(files);
 
 if(x1=="" || x2==""||x3=="" || x4==""||x5=="" || x6==""||x7=="")
	 {
	 	if(x1=="")
			{
				 $("#Error1").show()
		    }
	 	if(x2=="")
	 		{	
	 			$("#Error2").show()
	 
	 		}
	 	if(x3=="")
	 		{
	 			$("#Error3").show()	 
	 		}
	 	if(x4=="")
	 		{
	 			$("#Error4").show()
	 		}
	 	if(x5=="")
	 		{
	 			$("#Error5").show()
	 		}
	 	if(x6=="")
	 		{
	 			$("#Error6").show()
	 		}
	 	if(x7=="")
	 		{
		    	 $("#Error7").show()	 
	 		}
	 	 else
			 {
		 		var extension = $("#file").val().split('.').pop().toUpperCase();
		 		if (extension != "PNG" && extension != "JPG" && extension != "GIF" && extension != "JPEG") 
			 		{
			 			$("#Error8").show()
			 
			 		}
		 	 } 
	
	 }
 else
	 {
	 
	 var form=new FormData();
	 form.append("name",name1);	
	 form.append("fname",fname1);
	 form.append("date",date1);
	 form.append("sex",gender1);
	 form.append("phone",x6);
	 form.append("email",email1);
	 form.append("files", files); 


	 $.ajax({
	 	  url: 'pregi',
	 	  type: 'POST',
	 	  enctype: 'multipart/form-data',
	 	  processData: false,
	 	  contentType: false,
	 	  data: form,
	 	  success: function(response){
	 	    
	 		 $("#fullDiv").html(response);
	 		 // alert(data);
	 	    
	 	  }
	 	});


	 
	 
	 }
 
 
}
$(document).ready(function()
		{
		    $("#phone").keydown(function (e)
		    {
		       
		    	if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
		            
		            (e.keyCode === 65 && (e.ctrlKey === true || e.metaKey === true)) || 
		             
		            (e.keyCode >= 35 && e.keyCode <= 40))
		    	{
		                
		                 return;
		        }
		       
		        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105))
		     {
		            e.preventDefault();
		        }
		    });
		})
 

function deletee(id)
{
	
$.ajax({
	
	  url: 'deletee',
	  data: "id1="+id,
	  type: 'POST',
	  success: function(data){
		  $("#fullDiv").html(response);
	    
	  }
	
});

}

</script> 
<br>

</body>

</html>
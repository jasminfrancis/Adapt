<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Page Title</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<form>
	<table>

		<tr>
			<td>Username :</td>
			<td><input type="test" id="userName" name="userName" /></td>
		</tr>
		<tr>
			<td>Password :</td>
			<td><input type="password" id="password" maxlength="100"
				name="password" /></td>
		</tr>



	</table>
	<input type="button" value="login" id="loginButton" />
</form>
<br />
<div id="msg" style="color: red"></div>
<div id="messge1" style="color: red"></div>



<script type="text/javascript">


var user = {};

 $('#loginButton').click(function () {
	 
		if($('#password').val()){
			user.password = $('#password').val();
		  }else{
			  alert("password name  must be filled out");
			    return false;  
		  }	
	
	if($('#userName').val()){
		user.userName = $('#userName').val();
	}else{
		  alert("userName  must be filled out");
		    return false;  
	  }	
	
	$.ajax({
		
		url : 'login',
		method : 'POST',
		data : JSON.stringify({	
			"password":user.password,
			"userName":user.userName	
		}),
		
		//data: JSON.stringify(student),
		contentType : "application/json;charset=UTF-8",
		dataType : 'json',
		success : function(data) {
			if(data.status==200){
				console.log(data.message);
				console.log(data);
				if(data.response.role==1){
					window.location.href="/student";
					$('#userName').val('');
					$('#password').val('');
				}else{
					var firstName=data.response.firstName;
					window.location.href="/welcome/" + firstName;
				}
				
			}	
			else if(data.status==500){			
				$("#msg").html("<div ><b>"+data.message+"</b></div>");
				document.getElementById("messge1").style.display = "none";
				
			}else if(data.status==207){
				$("#messge1").html("<div ><b>"+data.message+"</b></div>");
			}
				
		},
		error : function(error) {
			
			alert(error);
		}
	})
 })
</script>
</body>
</html>
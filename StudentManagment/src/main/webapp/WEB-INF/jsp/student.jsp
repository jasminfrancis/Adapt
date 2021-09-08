<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>


<title>Page Title</title>
<a href="/">Logout</a>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<p style="color: red; font-size: 30px">Student Management System</p>

<form>
	<table>
		<tr>

			<td><input type="hidden" id="txtId" name="txtId" /></td>
		</tr>
		<tr>

			<td><input type="hidden" id="userName" name="userName" /></td>
		</tr>
		<tr>

			<td><input type="hidden" id="role" name="role" /></td>
		</tr>
		<tr>
			<td>First Name:</td>
			<td><input type="text" id="firstName" name="firstName" /></td>
		</tr>
		<tr>
			<td>Last Name :</td>
			<td><input type="text" id="lastName" maxlength="100"
				name="lastName" /></td>
		</tr>

		<tr>
			<td>Password :</td>
			<td><input type="password" id="password" maxlength="100"
				name="password" /></td>
		</tr>
		<tr>
			<td>Confirm Password :</td>
			<td><input type="password" id="confirmPassword" maxlength="100"
				name="confirmPassword" /></td>
		</tr>


	</table>
	<input type="button" value="Save" id="saveButton" />
</form>
<br />

<table id="tblStudent" border="1" style="border-collapse: collapse">
	<thead>

		<tr>
			<th>First Name</th>
			<th>Last Name</th>
			<th>User Name</th>
			<th>RoleName</th>
			<th>Actions</th>

		</tr>

	</thead>
	<tbody>

	</tbody>
</table>


<script type="text/javascript">
$(document).ready(function () {
getStudentList();
var student = {};

 $('#saveButton').click(function () {
	 
	
	if ($('#firstName').val()) {
				student.firstName = $('#firstName').val();
			} else {
				alert("FirstName No must be filled out");
				return false;
			}
			if ($('#lastName').val()) {
				student.lastName = $('#lastName').val();
			} else {
				alert("Last Name name  must be filled out");
				return false;
			}

			if ($('#password').val()) {
				student.password = $('#password').val();
			} else {
				alert("password  must be filled out");
				return false;
			}

			if ($('#confirmPassword').val()) {
				student.confirmPassword = $('#confirmPassword').val();
			} else {
				alert("confirmPassword  must be filled out");
				return false;
			}

			var password = student.password;
			var confirmPassword = student.confirmPassword;
			if (password != confirmPassword) {
				alert("Passwords do not match.");
				return false;
			}

			if ($('#txtId').val()) {
				student.id = $('#txtId').val();
			}

			if ($('#userName').val()) {
				student.userName = $('#userName').val();
			}
			if ($('#role').val()) {
				student.role = $('#role').val();
			}

			$.ajax({
				url : 'addStudent',
				method : 'POST',
				data : JSON.stringify({
					"firstName" : student.firstName,
					"lastName" : student.lastName,
					"id" : student.id,
					"password" : student.password,
					"userName" : student.userName,
					"role" : student.role

				}),
				//data: JSON.stringify(student),
				contentType : "application/json;charset=UTF-8",
				dataType : 'json',
				success : function(data) {
					alert(data.message);
					getStudentList();

				},
				error : function(error) {
					alert(error);
				}
			})
		})

	});

	/* Student List*/
	function getStudentList() {
		$.ajax({
					url : 'studentList',
					method : 'GET',
					dataType : 'json',
					success : function(data) {
						console.log(data);
						console.log(data.response);
						var tableBody = $('#tblStudent tbody');
						var listData = data.response;
						tableBody.empty();

						if (listData.length == 0) {
							tableBody
									.append('<tr><td colspan="12" style="text-align: center">'
											+ data.message + ' </td></tr>');
						} else {
							$(listData)
									.each(
											function(index, element) {
												tableBody
														.append('<tr><td>'
																+ element.firstName
																+ '</td><td>'
																+ element.lastName
																+ '</td><td>'
																+ element.userName
																+ '</td><td>'
																+ element.roleName
																+ '</td><td><button onclick = updateStudent('
																+ element.id
																+ ',"'
																+ element.firstName
																+ '","'
																+ element.lastName
																+ '","'
																+ element.role
																+ '","'
																+ element.userName
																+ '","'
																+ element.password
																+ '","'
																+ element.roleName
																+ '")>Update</button> | <button onclick = "deleteStudent('
																+ element.id
																+ ')">Delete</button></td></tr>');
											})
						}

					},
					error : function(error) {
						alert(error);
					}
				});
	}

	/* Delete Student */
	function deleteStudent(id) {
		$.ajax({
			url : 'deleteStudent/' + id,
			method : 'DELETE',
			success : function(data) {
				alert(data.message);
				getStudentList();
			},
			error : function(error) {
				alert(error);
			}
		})
	}

	/*This function set all available values in the curresponding fields and update the data*/
	function updateStudent(id, firstName, lastName, role, userName, password,
			roleName) {

		$('#firstName').val(firstName);
		$('#lastName').val(lastName);
		$('#txtId').val(id);
		$('#userName').val(userName);
		$('#role').val(role);
		$('#password').val(password);
		$('#confirmPassword').val(password);
	}
</script>
</body>
</html>
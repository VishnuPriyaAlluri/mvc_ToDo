<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	body{
		display: flex;
		justify-content: center;
		align-items: center;
		flex-direction: column;
	}
</style>
</head>
<body>
     <h1>ToDo Signup</h1>
<form action="signup" method="post">
	<fieldset>
		<legend>Signup here</legend>
		<table>
			<tr>
				<th>Name:</th>
			    <th><input type="text" name="name"></th>
			</tr>
			<tr>
				<th>Email:</th>
				<th><input type="email" name="email"></th>
				<th><h6 style="color:red">${email}</h6></th>
			</tr>
			<tr>
				<th>Password:</th>
				<th><input type="text" name="password"></th>
			</tr>
			<tr>
				<th>Mobile</th>
				<th><input type="tel" pattern="[0-9]{10}" name="mobile"></th>
			</tr>
			<tr>
				<th>Date Of Birth</th>
				<th><input type="date" name="date" ></th>
				<th><h6 style="color:red">${dob}</h6></th>
			</tr>
			<tr>
				<th>Gender</th>
				<th><input type="radio" name="gender" value="male">Male
				<input type="radio" name="gender" value="female">Female</th>
			</tr>
			<tr>
				<!-- Clicking this button will carry the values to action Location  -->
						<th><button>Signup</button></th>
						<th><button type="reset">Cancel</button></th>
				
			</tr>
			<tr>
				<!-- This is for Loading Login Page -->
						<th colspan="2"><a href="login"><button
									type="button">Click if Already have Account</button></a></th>
			</tr>
		</table>
	</fieldset>
	
</form>
     
</body>
</html>
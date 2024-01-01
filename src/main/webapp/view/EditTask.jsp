<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Task</title>
<style>
div {
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
}
</style>
</head>
<body>
    <div>

		<h1>Edit Task</h1>
		<!-- action is address where the values are carried to  -->
		<form action="update-task" method="post">
			<fieldset>
				<legend>Enter Task here,</legend>
				<input type="hidden" name="id" value="${task.id}">
				<table>
					<tr>
						<th>Task Name: </th>
						<!-- Name attribute is Complusory to carry value  -->
						<th><input type="text" value="${task.name}" name="name"></th>
					</tr>
					<tr>
						<th>Task Description: </th>
						<!-- Name attribute is Complusory to carry value  -->
						<th><input type="text" value="${task.description}" name="description"></th>
					</tr>
					<tr>
						<!-- Clicking this button will carry the values to action Location  -->
						<th><button>Update</button></th>
						<th><button type="reset">Cancel</button></th>
					</tr>
				</table>
			</fieldset>
		</form>
		<br>
		<br>
		<!-- This is for Loading Home Page Servlet -->					
		<a href="home"><button>Back</button></a>
	</div>
</body>
</html>
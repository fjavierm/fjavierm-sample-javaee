<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>MongoDB - Example</title>
	</head>
	<body>
		<form action="messageoper">
			Language: <input id="language" name="language" type="text" /><br />
			Message: <input id="message" name="message" type="text" /><br />
			Action: <select id="action" name="action">
				<option value="insert">Insert</option>
				<option value="find">Find</option>
				<option value="update">Update</option>
				<option value="delete">Delete</option>
			</select>
			<br />
			<input type="submit" value="Do" />
		</form>
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="/NibInk/sendEmail">
		<input name="recipient" type="text" placeholder="recipient">
		<input name="subject" type="text" placeholder="subject">
		<input name="message" type="text" placeholder="message">
		<input type="submit">
	
	</form>

</body>
</html>
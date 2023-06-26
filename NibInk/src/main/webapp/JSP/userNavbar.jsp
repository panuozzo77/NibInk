<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
	<link rel="stylesheet" href="/NibInk/CSS/userNavbar.css">
</head>
<body>
	<div class="navbar">
		<div class="lato_sinistro">
			<a>Contattaci</a>
		</div>
		<div class="lato_destro">
			<%if(session.getAttribute("name")!=null) { %>
			<a>Logout</a>
			<%} %>
			<%if(session.getAttribute("name")==null) { %>
			<a>Accedi</a>
			<%} %>
			<a id="account">Il Mio Account</a>
		</div>
	</div>
</body>
</html>
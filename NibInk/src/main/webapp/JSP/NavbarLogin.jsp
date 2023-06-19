<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="navbar">
		<div class="lato_destro">
			<a>Contattaci</a>
		</div>
		<div class="lato_sinistro">
			<a>Esci <%= session.getAttribute("name") %></a>
			<a id="carrello">Carrello</a>
		</div>
	</div>
</body>
</html>
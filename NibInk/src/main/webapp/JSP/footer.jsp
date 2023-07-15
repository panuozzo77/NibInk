<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="/NibInk/CSS/footer.css">
</head>
<body>


	<div class="footer">
		<div class="colonna">
			<h3>NibInk</h3>
			<h5>Contattaci subito:</h5>
			<a>+39 3476809732</a>
			<a>info@NibInk.com</a>
			<a>Termini e condizioni</a>
		</div>
		
		<div class="colonna">
			<h3>Categorie</h3>
			<a href="/NibInk/JSP/catalog.jsp?filter=pen">Penne Stilografiche</a>
			<a href="/NibInk/JSP/catalog.jsp?filter=notebook">Quaderni</a>
			<a href="/NibInk/JSP/catalog.jsp?filter=ink">Inchiostri</a>
		</div>
		<% if(!("unregistered".equals(session.getAttribute("userType")))) { %>
		<div class="colonna">
			<h3 onclick="location.href='http://localhost:8080/NibInk/JSP/myAccount.jsp'" style="cursor: pointer">Il mio account</h3>
			<a href="/NibInk/JSP/myOrders.jsp">I miei ordini</a>
			<a href="/NibInk/JSP/cart.jsp">Il mio carrello</a>
			<a href="/NibInk/JSP/messageCenter.jsp">Centro Messaggi</a>
			<%if("admin".equals(session.getAttribute("userType"))) { %>
				<a href="/NibInk/JSP/dashboard.jsp">Pannello di Controllo</a>
			<%} %>
		</div>
		<%} %>
		<div class="colonna">
			<h3>SEGUICI SUI SOCIAL</h3>
			<div style="margin-top: 10px">
				<img class="footerImg" alt="" src="/NibInk/images/instagram.png" style="cursor: pointer" onclick="location.href='https://www.instagram.com/'">
				<img class="footerImg" alt="" src="/NibInk/images/telegram.png" style="cursor: pointer" onclick="location.href='https://telegram.org/'">
			</div>
		</div>
	</div>


</body>
</html>
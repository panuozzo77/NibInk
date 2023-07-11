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
		</div>
		
		<div class="colonna">
			<h3>Categorie</h3>
			<a href="/NibInk/JSP/catalog.jsp?filter=pen">Penne Stilografiche</a>
			<a href="/NibInk/JSP/catalog.jsp?filter=notebook">Quaderni</a>
			<a href="/NibInk/JSP/catalog.jsp?filter=ink">Inchiostri</a>
		</div>
		
		<div class="colonna">
			<h3>Il mio account</h3>
			<a>I miei ordini</a>
			<a href="/NibInk/JSP/cart.jsp">Il mio carrello</a>
			<a>Termini e condizioni</a>
			<%if("admin".equals(session.getAttribute("userType"))) { %>
				<a href="/NibInk/JSP/admin.jsp">Pannello di Controllo</a>
			<%} %>
		</div>
		<div class="colonna">
			<h3>SEGUICI SUI SOCIAL</h3>
			<div>
				<img class="footerImg" alt="" src="/NibInk/images/instagram.png">
				<img class="footerImg" alt="" src="/NibInk/images/telegram.png">
			</div>
		</div>
	</div>


</body>
</html>
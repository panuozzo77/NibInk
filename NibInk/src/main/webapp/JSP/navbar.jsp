<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
<head>

	<meta charset="ISO-8859-1">
	<link href="/NibInk/CSS/navbar.css" rel="stylesheet" type="text/css"/>

</head>
<body>
<div class="page">
	<div class="menu">
		<a class="home" href="/NibInk/JSP/home.jsp">Home</a>
		<div class="prodotti">
  			<a href="/NibInk/JSP/catalog.jsp">Prodotti</a>
 			<div class="submenu">
    			<a href="/NibInk/JSP/catalog.jsp?filter=ink">Inchiostri</a>
    			<a href="/NibInk/JSP/catalog.jsp?filter=pen">Penne</a>
    			<a href="/NibInk/JSP/catalog.jsp?filter=notebook">Quaderni</a>
  			</div>
		</div>
		<img alt="" src="/NibInk/images/logo.png">
		<a href="/NibInk/JSP/cart.jsp">Carrello</a>
		<% if(session.getAttribute("userType").toString().equals("registered")||session.getAttribute("userType").toString().equals("admin")) { %>
		<a href="/NibInk/LoginServlet?logout=yes">Logout</a>
		<%} else { %>
		<a href="/NibInk/JSP/login.jsp">Login</a>
		<%} %>
	</div>
	
	<div class="menu2">
	<img alt="" src="/NibInk/images/logo.png" id="logo">
	<div class="link">
		<a class="home" href="/NibInk/JSP/home.jsp">Home</a>
  		<div class="prodotti">
  			<a href="/NibInk/JSP/catalog.jsp">Prodotti</a>
 			<div class="submenu">
    			<a href="/NibInk/JSP/catalog.jsp?filter=ink">Inchiostri</a>
    			<a href="/NibInk/JSP/catalog.jsp?filter=pen">Penne</a>
    			<a href="/NibInk/JSP/catalog.jsp?filter=notebook">Quaderni</a>
  			</div>
		</div>
  		<a href="/NibInk/JSP/cart.jsp">Carrello</a>
  		<% if(session.getAttribute("userType").toString().equals("registered")||session.getAttribute("userType").toString().equals("admin")) { %>
		<a href="/NibInk/LoginServlet?logout=yes">Logout</a>
		<%} else { %>
		<a href="/NibInk/JSP/login.jsp">Login</a>
		<%} %>
	</div>
		<img alt="" src="/NibInk/images/menu.png" class="scorrimento">
	</div>
</div>

</body>
<script>
	const menu=document.querySelector(".scorrimento");
	const links=document.querySelector(".link");
	
	menu.addEventListener('click',()=>
	{
		links.classList.toggle('scorrimento');
	})
</script>
</html>
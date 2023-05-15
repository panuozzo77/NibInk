<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="com.model.CartManager"%>
<%@ page import="com.model.Cart"%>
<%@ page import="java.util.Collections" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="../CSS/carrello.css">
</head>
<body>
<jsp:include page="Navbar.jsp" />

<div class="page">
	<h1>CARRELLO</h1>
	<% 
		HttpSession sessione = request.getSession();
		String cartId = (String) sessione.getAttribute("sessionId");
		CartManager cm = new CartManager();
		Cart carrello = cm.getCart(cartId);
		if (carrello!=null)
	{	
	%>
	<c:forEach items="<%= carrello.getCart()%>" var="item">
		<div class="riga">
			<div class="prodotto">
				<img alt="" src="/NibInk/images/${item.getItem().getTitle()}.jpg">
				<div class="info_prod">
					<h3>Nome: ${item.getItem().getTitle()}</h3>
					<h3>Numero pezzi: ${item.getQuantity()}</h3>
					<h3></h3>
				</div>
			</div>
			<div class="prezzo">
				<h3>Prezzo prodotto:</h3>
				<h4>${ item.getQuantity()*item.getItem().getPrice()}</h4>
			</div>
		</div>
	</c:forEach>
	<div class="prezzo_tot">
	<h2>Prezzo totale:</h2>
	<h2><%= carrello.getTotal()%></h2>
	<button>
		COMPRA
	</button>
	</div>
	<%
	}
	else
	{
	%>
	<h1>Non hai nessun prodotto nel tuo carrello</h1>
	<%
	}
	%>
</div>
</body>
</html>
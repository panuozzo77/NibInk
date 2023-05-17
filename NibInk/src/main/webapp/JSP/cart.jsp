<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="javax.servlet.http.HttpSession" %>
<%@ page import="com.model.CartManager"%>
<%@ page import="com.model.Cart"%>
<%@ page import="java.util.Collections" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Cart</title>
	<link rel="stylesheet" href="/NibInk/CSS/cart.css">
</head>
<body>
<jsp:include page="navbar.jsp" />
<div class="container">
	<h1 class="title">CARRELLO</h1>
		<% 
			HttpSession sessione = request.getSession();
			String cartId = (String) sessione.getAttribute("sessionId");
			CartManager cm = new CartManager();
			Cart carrello = cm.getCart(cartId);
			if (carrello!=null){	
		%>
		<div class="products">
			<c:forEach items="<%= carrello.getCart()%>" var="item">
				<div class="row">
					<div class="product">
						<img class="productImg" alt="" src="/NibInk/images/${item.getItem().getTitle()}.jpg">
						<div class="productInfo">
							<h3>${item.getItem().getTitle()}</h3>
							<h4>Quantità: ${item.getQuantity()}</h4>
							<h4>Taglia: ${item.getSize() }</h4><br>
							<button class="buttons">Rimuovi</button>
						</div>
					</div>
					<div class="price">
						<h4>${item.getQuantity()*item.getItem().getPrice()}</h4>
					</div>
				</div>
			</c:forEach>
			<div class="totalPrice">
				<h2>Prezzo totale: <%=carrello.getTotal()%></h2>
				<button class="buttons">COMPRA</button>
			</div>
		</div>
		<%}	else{%>
			<h1>Non hai nessun prodotto nel tuo carrello</h1>
		<%	}%>

</div>

</body>
</html>
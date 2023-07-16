<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="com.model.CartManager"%>
<%@ page import="com.model.Cart"%>
<%@ page import="java.util.Collections" %>

<!DOCTYPE html>
<html lang="it">
<head>
	<meta charset="ISO-8859-1">
	<title>NibInk - Carrello</title>
	<link rel="icon" type="image/x-icon" href="/NibInk/images/favicon.ico">
	<link rel="stylesheet" href="/NibInk/CSS/cart.css">
</head>
<body>
<script src="/NibInk/JavaScript/jquery.js"></script>

<jsp:include page="navbar.jsp" />
<div class="container">
	<h1 class="title">CARRELLO</h1>
		<% 
			HttpSession sessione = request.getSession();
			String cartId = (String) sessione.getAttribute("sessionId");
			CartManager cm = new CartManager();
			Cart carrello = cm.getCart(cartId);
			if (carrello!=null && !carrello.isEmpty()){	
		%>
		<div class="products">
			<c:forEach items="<%= carrello.getCart()%>" var="item" varStatus="loop">
			  <div class="row">
			    <div class="product">
			      <img class="productImg" alt="" src="/NibInk/images/${item.getItem().getCodenumber()}/thumbnail.png">
			      <div class="productInfo">
			        <h3>${item.getItem().getTitle()}</h3><br>
			        Taglia: ${item.getSize()}
			        <form id="cartForm" action="/NibInk/CartServlet" method="post">
			          Quantità:
			          <input type="number" name="quantityInput" id="quantityInput${loop.index}" min="1" max="15" value="${item.getQuantity()}">
			
			          <br><br>
			         
					  <input type="hidden" name="item" value="${item.getItem().getCodenumber()}">
					  <input type="hidden" name="quantity" value="${item.getQuantity()}">
					  <input type="hidden" name="size" value="${item.getSize()}">
			          <input type="submit" name="removeButton" id="removeButton" class="buttons" value="Rimuovi">
			          <input type="submit" name="updateButton" id="modifyButton${loop.index}" class="buttons" value="Modifica" style="display: none">
			        </form>
						
			        <script>
			          var quantityInput${loop.index} = document.getElementById("quantityInput${loop.index}");
			          var modifyButton${loop.index} = document.getElementById("modifyButton${loop.index}");
			          var previousQuantity${loop.index} = quantityInput${loop.index}.value;
			
			          quantityInput${loop.index}.addEventListener("input", function() {
			            if (quantityInput${loop.index}.value === previousQuantity${loop.index}) {
			              modifyButton${loop.index}.style.display = "none";
			            } else {
			              modifyButton${loop.index}.style.display = "inline";
			            }
			          });
			        </script>
			        </div>
			    </div>
			    <div class="price">
			      <h4><fmt:formatNumber value="${item.getQuantity() * item.getItem().getPrice()}" type="currency" currencySymbol="€"/></h4>
			    </div>
			  </div>
			</c:forEach>

			<div class="totalPrice">
				<h2>Prezzo totale: <fmt:formatNumber value="<%=carrello.getTotal()%>" type="currency" currencySymbol="€"/></h2>
				<button name="buyButton" class="buttons" onclick="submit()">Procedi all'acquisto</button>
			</div>
		</div>
		<%}	else{%>
		<div class="emptyContainer">
			<img src="/NibInk/images/emptyCart.svg" class="bgImg"><br>
			<h3 style="margin-top: 3%">Carrello vuoto!</h3><br>
			<button onclick="window.location.href='/NibInk/JSP/catalog.jsp'" class="buttons">Torna al catalogo</button>
		</div>
			
		<%	}%>

</div>
<script>
	function submit(){
		$("#cartForm").submit();
	}
</script>
</body>
</html>
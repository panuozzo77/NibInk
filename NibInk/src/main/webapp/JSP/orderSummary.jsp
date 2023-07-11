<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="com.model.CartManager"%>
<%@ page import="com.model.Cart"%>
<%@ page import="com.model.ItemInTheCart"%>
<%@ page import="java.util.ArrayList" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>NibInk - Riepilogo Ordine</title>
</head>
<body>
	<%
		HttpSession sessione = request.getSession();
		String cartId = (String) sessione.getAttribute("sessionId");
		CartManager cm = new CartManager();
		Cart carrello = cm.getCart(cartId);
		ArrayList<ItemInTheCart> items=carrello.getCart();
	%>

	<div class="Container">
		<h3>Riepilogo ordine</h3>
		<div class="itemsSummary">
			<%for(ItemInTheCart i : items){%>
				<div class="itemInfo"> 
					<div class="itemImg">
						<img class="image" src="/NibInk/images/<%=i.getItem().getTitle()%>.jpg">
					</div>
					<div class="itemTitle">
						<p><%= i.getItem().getTitle() %></p>
						<p>Taglia: <%=i.getSize()%></p>
					</div>
				</div>
				<div class="itemQnt">
					<%= i.getQuantity()%>
				</div>
				<div class="itemPrice">
					<%= i.getItem().getPrice()*i.getQuantity() %>
					<%if(i.getQuantity()>1){ %>
						<p> <%=i.getItem().getPrice() %> l'uno</p>
					<%} %>
				</div>
			<%} %>
		</div>
		<div class="shippingForm">
			<h3>Seleziona il metodo di spedizione:</h3>
		</div>
		<div class="costAndPayment">
			<h3>Riepilogo pagamento:</h3>
			
		</div>
	</div>
	

</body>
</html>

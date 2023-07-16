<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="com.model.CartManager"%>
<%@ page import="com.model.Cart"%>
<%@ page import="com.model.ShippingMethodManager"%>
<%@ page import="com.model.ShippingMethod"%>
<%@ page import="com.model.ItemInTheCart"%>
<%@ page import="com.model.DAOCustomer"%>
<%@ page import="com.model.Customer"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>


<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<title>NibInk - Riepilogo Ordine</title>
<link rel="icon" type="image/x-icon" href="/NibInk/images/favicon.ico">
<link rel="stylesheet" href="/NibInk/CSS/orderSummary.css">
</head>
<script src="/NibInk/JavaScript/jquery.js"></script>
<script src="/NibInk/JavaScript/orderSummary.js"> </script>
<body>
<div class=navbar>
<jsp:include page="navbar.jsp"/>
</div>
	<%
		HttpSession sessione = request.getSession();
		String cartId = (String) sessione.getAttribute("sessionId");
		CartManager cm = new CartManager();
		Cart carrello = cm.getCart(cartId);
		ArrayList<ItemInTheCart> items=carrello.getCart();
	%>
<div class="Container0">
	<div class="Container">
		<div class="firstHalf">
			<h3>Riepilogo ordine</h3>
			<div class="itemsSummary">
				<%for(ItemInTheCart i : items){%>
				<div class="itemContainer">
					<div class="itemInfo"> 
						<div class="itemImg">
							<img alt="Immagine del Prodotto" class="image" src="/NibInk/images/<%=i.getItem().getCodenumber()%>/thumbnail.png">
						</div>
						<div class="itemTitle">
							<p><%= i.getItem().getTitle() %></p>
							<p>Taglia: <%=i.getSize()%></p>	
						</div>
					</div>
					<div class="itemPriceQuantity">
						<div class="itemQnt">
							<p>Qnt. <%= i.getQuantity()%></p>
						</div>
						<div class="itemPrice">
							<fmt:formatNumber value="<%= i.getItem().getPrice()*i.getQuantity()%>" type="currency"/>
							
							<%if(i.getQuantity()>1){ %>
								<p class="singlePrice">(<fmt:formatNumber value="<%= i.getItem().getPrice()%>" type="currency"/> cad.)</p>
							<%} %>
						</div>
					</div>
				</div>
				<%} %>
					<div class="itemTotal">
						<p> Totale: <span id="itemTotalPrice"><fmt:formatNumber value="<%=carrello.getTotal()%>" type="currency"/> </span></p>
					</div>
			</div>
		</div>
		
		<div class="secondHalf">
			<div class="shippingForm">
				<h3>Seleziona il metodo di spedizione:</h3>
				<% List<ShippingMethod> shipping = new ShippingMethodManager().getAllShippingMethods();
					int i=0;
					for(ShippingMethod s : shipping){%>
					<div class="shippingContainer">
						<div class="shippingText">
							<div class="shippingInfo"> 
								<p class="sName"><%=s.getName()%></p>
								<p class="sCourier"><%=s.getCourier() %></p>
							</div>
							<div class="shippingPrice">
								<%if(session.getAttribute("paymentMethod").equals("card")){ %>
									<p id="sPrice<%=i%>"  class="sPrice">  <fmt:formatNumber value="<%=s.getAmount() %>" type="currency"/></p>
								<%}else{ %>
									<p id="sPrice<%=i%>"  class="sPrice"> <fmt:formatNumber value="<%= s.getAmount()+((carrello.getTotal()*s.getPercentage())/100) %>" type="currency"/></p>
									<p class="sPriceInfo">Calcolato come costo di spedizione sommato al <span class="sPercentage"><%=s.getPercentage()%></span>% del prezzo totale</p>
								<%} %>
							</div>
						</div>
						<div class="shippingBtns">
							<input name="shippingRadio" id="sr<%=i%>" type="radio" data-sPrice="<%=s.getAmount() %>" data-sMethod="<%=s.getName()%>" value="" onclick="addShipPrice()">
						</div>
					</div>
				<% i++;} %>
				<input type="hidden" id="shippingMethodsNumber" value="<%=i%>">
			</div>
			<div class="costAndPayment">
				<h3>Riepilogo pagamento:</h3>
					<%if(!(sessione.getAttribute("userType").equals("unregistered"))){
						int userId =  (int) sessione.getAttribute("id");
						Customer c = new DAOCustomer().getCustomerById(userId);
					%>
						<div class="emailDiv">
							<span>Email:</span><input type="email" id="emailCustomer" class="emailCustomer" value="<%=c.getEmail()%>">
						</div>
					<%}else{ %>
						<div class="emailDiv">
							<span>Email:</span><input type="email" id="emailCustomer" class="emailCustomer" value="" placeholder="Inserisci Email">
						</div>
					<%} %>
					<div class="addrDiv">
						<p>Indirizzo di spedizione: <span><%=session.getAttribute("addr") %></span></p>
						<%if(!(session.getAttribute("addrBA").equals("false"))){%>
						<p>Indirizzo di Fatturazione: <span><%=session.getAttribute("addrBA") %></span></p>
						<%} %>
					</div>
					<div class="paymenthContainer">
						<% if(session.getAttribute("paymentMethod").equals("card")){ %>
							<div class="cardInfo">
								<p>Hai selezionato carta di credito:</p>
								<p><%=session.getAttribute("cardName")%></p>
								<p><%=session.getAttribute("cardNumber")%></p>
							</div>
						<% }else{ %>
							<div class="cashOnDeliveryInfo">
								<p>Hai selezionato contrassegno</p>
							</div>
						<% } %>
					</div>
			</div>
			<div class="costSummary">
				<h3>Costo totale:</h3>
				<div class="costChart">
					<div class="itemCostGroup">
						<%for(ItemInTheCart it : items){%>
							<div class="costSingleton">
								<span><%=it.getItem().getTitle() %></span><span><fmt:formatNumber value="<%= it.getItem().getPrice()*it.getQuantity()%>" type="currency"/>  +</span>
							</div>
						<%} %>
					</div>
					<div class="shippingCostGroup">
						<div class="costSingleton" id="selectedShippingCost">
							<span>Spedizione</span><span></span>
						</div>
					</div>
					<div class="totalCostGroup">
						<div class="costSingleton" id="totalCost">
							<span>Costo totale:</span><span></span>
						</div>
					</div>
				</div>
				<div class="btnDiv">
					<input id="placeOrderBtn" type="button" class="Hidden" onclick="placeOrder()" value="Conferma Ordine">
				</div>
			</div>
		</div>
	</div>
</div>	
<input id="userType" type="hidden" value="<%=sessione.getAttribute("userType") %>">
</body>
</html>

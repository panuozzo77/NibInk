<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.model.DAOOrder" %>
<%@ page import="com.model.Order" %>
<%@ page import="com.model.Item" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.model.DAOItem" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="/NibInk/CSS/ordini.css" rel="stylesheet" type="text/css"/>
</head>
<body>

	<div class="page">
		<div class="title">
			<h2>I miei ordini</h2>
		</div>
		<div class="prodotto">
		<% 
		DAOOrder DaoOrder= new DAOOrder();
		DAOItem item = new DAOItem();
		String utente = (String)session.getAttribute("id"); 
		ArrayList<Order> orders = DaoOrder.loadAllOrder(Integer.parseInt(utente));
		for(Order ord : orders){
			Integer IdItem = ord.getProgessive_N();
			Item ItemPresent = item.getItemFromDB(IdItem.toString());
		%>
			<div class="informaizone_oridne">
				<div class="info">
					<h3>Data Ordine</h3>
					<p><%= ord.getOrderDate()%></p>
				</div>
				<div class="info">
					<h3>Totale Ordine</h3>
					<p><%= ItemPresent.getPrice()*ord.getAmount()%></p>
				</div>
				<div class="info">
					<h3>Ordine: <%= ord.getId()%></h3>
				</div>
			</div>
			<div class="informazione_consegna">
				<div class="prodotto">
					<h3>Consegnato il <%= ord.getOrderDate()%></h3>
					<div class="dettagli_ordine">
						<img alt="" src="">
						<h2><%= ItemPresent.getTitle()%></h2>
					</div>
				</div>
				
				<div class="function">
					<form action="/NibInk/AddToCart">
						<input type="hidden" name="product" value="<%= ord.getId()%>">
						<button type="submit" class="addToCart">Informazioni Prodotto</button>
					</form>
				</div>
			</div>
		<%} %>
		</div>
	</div>
</body>
</html>
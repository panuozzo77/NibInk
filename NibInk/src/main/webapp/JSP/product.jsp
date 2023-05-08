<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.model.Item" %>
<%@ page import="com.model.DAOItem" %>
<%@ page import="com.model.Catalog" %>

<!DOCTYPE html>
<html>
<head>

	<meta charset="UTF-8">
	<% DAOItem db = new DAOItem(); %>
	<% Item product = db.getItemFromDB(request.getParameter("id"));%>
	<title> <%= product.getTitle() %></title>
	<link rel="stylesheet" href="/NibInk/CSS/product.css">
	
</head>
<body>
	
	<div class="container">
  		<div class="rowUp">
    		<div class="columLeft">
    			<img class="product_img" src="/NibInk/images/<%=product.getTitle()%>.jpg" width=350px height=350px>
    		</div>
    		<div class="columRight">
    			<h2><%=product.getTitle()%></h2><br>		    	
		    	<p><fmt:formatNumber value="<%= product.getPrice()%>" type="currency"/></p>
		    	<p class="description"><%=product.getDescription()%></p>
		    	<div class="buttons">
		    		<button onclick="location.reload();" class="addToCart"> <img src="/NibInk/images/cart.png" width=20px height=20px>Aggiungi al Carrello</button>
		    	</div>
    		</div>
  		</div>
		<div class="rowDown">
			<h3>Scheda Tecnica</h3>
				<ul class="details">
					<li><b>&emsp;Codice Prodotto:</b>	<%= product.getCodenumber()%></li>
					<li><b>&emsp;Nome:</b>				<%= product.getTitle()%></li>
					<li><b>&emsp;Colore:</b>			<%= product.getColor()%></li>
					<li><b>&emsp;Peso:</b>				<%= product.getWeight()%></li>
					<li><b>&emsp;Dimensioni:</b>		<%= product.getDimensions()%></li>
				</ul>		
		</div>
		<div class="rowDown">
			<h4>Prodotti Correlati</h4>	
			<div class="moreProducts">
				<!-- Prodotti correlati es stessa marca + stesso prezzo -->
				<!-- 
				
				
				% Catalog rProducts = new Catalog() %>
				% rProducts.allItems = db.getAllItemsFromDB(null, "Price", String.valueOf(product.getPrice()));%>
				<c:forEach var="item" items="${Catalog.allItems}">
					<div class="column">
	    				<div class="card">
	    					<a href="/NibInk/JSP/product.jsp?id=${item.codenumber}">
	    						<img src="/NibInk/images/${item.title}.jpg" height=100px width=100px>
							</a>
							<br>
							${item.title}
							<br>
							<fmt:formatNumber value="${item.price}" type="currency"/>
						 </div>
	 		 		</div>
				</c:forEach>
				
				
				 -->
				
			</div>
		</div>
	</div>
	
</body>
</html>
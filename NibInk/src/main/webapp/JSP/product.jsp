<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.model.Item" %>
<%@ page import="com.model.DAOItem" %>
<%@ page import="com.model.Catalog" %>
<%@ page import="com.model.ItemVariant" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.model.ItemManager" %>
<%@ page import="java.util.ArrayList" %>


<!DOCTYPE html>
<html>
<head>

	<meta charset="UTF-8">
	<% 
		DAOItem db = new DAOItem();
		Item product = db.getItemFromDB(request.getParameter("id"));
		ItemVariant productVariants = new ItemVariant(product);
		productVariants.loadVariants();
	%>
	<title> <%= product.getTitle() %></title>
	<link rel="stylesheet" href="/NibInk/CSS/product.css">
	
</head>
<body>
<jsp:include page="navbar.jsp" />

	
	<div class="container">
  		<div class="rowUp">
    		<div class="columLeft">
    			<img class="product_img" src="/NibInk/images/<%=product.getTitle()%>.jpg">
    		</div>
    		<div class="columRight">
    			<h2><%=product.getTitle()%></h2><br>		    	
		    	<p><fmt:formatNumber value="<%= product.getPrice()%>" type="currency"/></p>
		    	<p class="description"><%=product.getDescription()%></p>
		    	<div class="buttons">
		    		<%
		    			if(!productVariants.isEmpty()){
		    		%>
		    				
		    			<form action="/NibInk/AddToCart" method="post">
		    			Taglia: &emsp;
		    			
		    			<select id="sizeSelect" name="size" onChange="updateQuantityInput()">
			    			<% for(Map.Entry<String, Integer> entry : productVariants.getVariants().entrySet()){ %>
			    				<%int quantityAvailable = entry.getValue(); %>
			    				<option value="<%=entry.getKey()%>" data-quantity="<%= quantityAvailable%>" > <%=entry.getKey()%>  </option>
			    			<% } %>
			    			  <option hidden="" value="" selected disabled >Scegli la taglia!</option>
		    			</select>	
		    			<br>
		    			Quantità:
		    			<input type="hidden" name="product" value=<%=product.getCodenumber() %>>
		    			<input type="number" name="quantity" id="quantityInput" min="1" max="10" value="1">
		    			<button disabled type="submit" id=button class="addToCart"><img src="/NibInk/images/cart.png" width="20px" height="20px">Scegli la taglia!</button>
		    			</form>
		    			
	    				<script>
	    					var sizeQuantityMap = {};
   							function updateQuantityInput() {
					        var sizeSelect = document.getElementById("sizeSelect");
					        var quantityInput = document.getElementById("quantityInput");
					
					        var selectedSize = sizeSelect.value;
					        var maxQuantity = parseInt(sizeSelect.options[sizeSelect.selectedIndex].getAttribute("data-quantity"));
					
					        if (maxQuantity > 0) {
					            quantityInput.max = maxQuantity.toString();
					            quantityInput.value = "1";
					            button.disabled = false;
					            button.innerHTML = '<img src="/NibInk/images/cart.png" width="20px" height="20px">Aggiungi al Carrello'
					            sizeQuantityMap[selectedSize]
					        } else {
					            quantityInput.max = "0";
					            quantityInput.value = "0";
					            button.disabled = true;
					            button.innerHTML = '<img src="/NibInk/images/cart.png" width=20px height=20px>Non disponibile'
					        	}
					    	}
						</script>
		    				
		    				
		    		<%		
		    			}else{
		    		%>
		    			
		    			<button class="addToCart" disabled><img src="/NibInk/images/cart.png" width=20px height=20px>Non disponibile</button>
		    				
		    		<%		
		    			}
		    		%>
		    			
		    			
		    		
		    		
		    	</div>
    		</div>
  		</div>
		<div class="rowDown">
			<h2>Scheda Tecnica</h2>
				<ul class="details">
					<li><b>&emsp;Codice Prodotto:</b>	<%= product.getCodenumber()%></li>
					<li><b>&emsp;Nome:</b>				<%= product.getTitle()%></li>
					<li><b>&emsp;Colore:</b>			<%= product.getColor()%></li>
					<li><b>&emsp;Peso:</b>				<%= product.getWeight()%></li>
					<li><b>&emsp;Dimensioni:</b>		<%= product.getDimensions()%></li>
				</ul>		
		</div>
		<jsp:include page="review.jsp" />
		<div class="rowDown">
			<h4>Prodotti Correlati</h4>	
			<div class="moreProducts">
				<!-- Prodotti correlati es stessa marca + stesso prezzo -->
			 
				 <%
				 	ItemManager im = new ItemManager();
					ArrayList<Item> itemsLoaded=im.loadRelatedItems(product.getPrice());
				 %>
				
				<div class="mpRow"> 
					<c:forEach items="<%= itemsLoaded %>" var="item"> 									
						<div class= "mpCardBorder">
							<div class="mpCardContent">
								<button class="mpProductCard" type="button" onclick="location.href='/NibInk/JSP/product.jsp?id=${item.getCodenumber()}';">
								<div>
								<img class="mpProductImg" src="/NibInk/images/${item.getTitle()}.jpg">
								</div>
									<br>${item.getTitle()}<br>
									<fmt:formatNumber value="${item.getPrice()}" type="currency"/>
								 </button>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
		
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>
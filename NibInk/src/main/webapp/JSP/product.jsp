<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.model.Item" %>
<%@ page import="com.model.DAOItem" %>
<%@ page import="com.model.Catalog" %>
<%@ page import="com.model.ItemVariant" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.model.ItemManager" %>
<%@ page import="com.model.FileImage" %>
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

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
        $(document).ready(function() {
        	var url = new URL(window.location.href);
            var id = url.searchParams.get('id'); 
            $.ajax({
                url: "/NibInk/getImages?id=" + id, 
                method: "GET",
                dataType: "json",
                success: function(response) {
                	
                    var imageUrls = response.images;
                    for (var i = 0; i < imageUrls.length; i++) {
                        var imageUrl = imageUrls[i];
                        var imgElement = $("<img>").attr("src", imageUrl).attr("alt", "Immagine").attr("class","slide").attr("id","img"+i);
                        $("#columLeft").append(imgElement);
                    }
                    var imgElement = $("<div>").attr("class", "radio-input");
                    $("#columLeft").append(imgElement);
                    if(imageUrls.length>=1)
                    {
                    	var imgElement = $("<input>").attr("type", "radio").attr("value","img0").attr("onClick","change('img0')").attr("class","input").attr("checked","").attr("name","radio");
                    	$(".radio-input").append(imgElement);
	                    for (var i = 1; i < imageUrls.length; i++) {
	                    	var imgElement = $("<input>").attr("type", "radio").attr("value","img"+i).attr("onClick","change('img"+i+"')").attr("class","input").attr("name","radio");
	                        $(".radio-input").append(imgElement);
	                    
	                    }
                    }
                    else
                    	{
                    	
                    	}
                },
                error: function() {
                    console.log("Errore durante la richiesta AJAX");
                }
            });
            //parte per la gesitone dei slide delle immagini
        });
        window.onload= function()
        {
        	var divElement = document.getElementById('columLeft');
        	var imgElements = divElement.getElementsByTagName('img');
        	for(var i=0; i<imgElements.length; i++)
        		{
        		if(i===0)
        			imgElements[i].style.display='flex';
        		else
        			imgElements[i].style.display='none';
        		}
        }
    </script>
</head>
<body>
<jsp:include page="navbar.jsp" />

	
	<div class="container">
  		<div class="rowUp">
    		<div id="columLeft">
    			
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
	<script type="text/javascript">
		function change(i)
		{
			var divElement = document.getElementById("columLeft");
			var imgElements = divElement.querySelectorAll('img[style="display: flex;"]');
			imgElements[0].style.display="none";
			var selectedImage = document.getElementById(i);
			selectedImage.style.display="flex";
		}
	</script>
</body>
</html>
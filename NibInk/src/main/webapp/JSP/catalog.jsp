<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.model.ItemManager" %>
<%@ page import="com.model.Item" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.model.DAOItem" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Catalog</title>
	<link href="/NibInk/CSS/catalog.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<script src="/NibInk/JavaScript/jquery.js"></script>
<script src="/NibInk/JavaScript/catalog.js"> </script>
<div class="navbar">
<jsp:include page="navbar.jsp"/>
</div>


	<%
		DAOItem db=new DAOItem();
		int items;
		int itemPerPage=10;
		boolean[] disponibility;
		int pageNumber=request.getParameter("pageNumber")== null ? 1 : Integer.parseInt(request.getParameter("pageNumber"));
		int startIndex=request.getParameter("startIndex")== null || request.getParameter("pageNumber").equals(String.valueOf(1)) ? 0 : Integer.parseInt(request.getParameter("startIndex"));
		String filter=request.getParameter("filter");
		ItemManager im = new ItemManager();
		ArrayList<Item> itemsLoaded;
		if(filter==null || filter.contains("null")){
			itemsLoaded=im.loadItems(startIndex, itemPerPage);
			items = db.getItemsNumber();
			disponibility = im.loadDisponibility(startIndex, itemPerPage);
		}
		else{
			itemsLoaded=im.loadFilteredItems(startIndex, itemPerPage, filter);
			items = db.getFilteredItemsNumber(filter);
			disponibility = im.loadDisponibility(startIndex, itemPerPage, filter);
		}
		
	%>
	<div class="container0">
	<div class="container">
		<div id="search-filters">
			<div id="search">
			<input id="searchbar" type="text" value="" placeholder="Cerca nel catalogo"/>
			<div id="searchResults"></div>
		</div>
		
		<div class="filters">
			<p class="filterText">Filtri:</p>
			
			<form class="filterForm" action="/NibInk/nextPage" method="get">
				<input type="checkbox" id="onlyPens" name="onlyPens" value="pen-">
				<label for="onlyPens"> Penne Stilografiche</label><br>
				<input type="checkbox" id="onlyInks" name="onlyInks" value="ink-">
				<label for="onlyInks"> Inchiostri</label><br>
				<input type="checkbox" id="onlyNotebooks" name="onlyNotebooks" value="notebook">
				<label for="onlyNotebooks"> Taccuini</label><br>
				<br>
				<input type="submit" class="buttons" id="submit" value="Filtra">
			</form>
				<% if(filter!=null){ %>
					<button class="buttons" onclick="location.href = '/NibInk/JSP/catalog.jsp';">Azzera</button>
				<%} %>
		</div>
		</div>
		
		
		
		
		<div class=container2>
			<h1 class="title">&emsp;&emsp;Catalog</h1>			
			
			<div class="itemInCatalog">		
				<% int i=0;
				   int p=0;
				%>			
				<c:forEach items="<%= itemsLoaded %>" var="item">
					<% if(i==0){ %>
    					<div class="row"> 				
    				<%} %>
					<div class= "cardBorder">
						<div class="cardContent">
							<button class="productCard" type="button" onclick="location.href='/NibInk/JSP/product.jsp?id=${item.getCodenumber()}';">
							<div>
							<img class="productImg" src="/NibInk/images/${item.getTitle()}.jpg">
							</div> 
								<br>${item.getTitle()}<br>
								<fmt:formatNumber value="${item.getPrice()}" type="currency"/>
							 </button>
							 <c:choose>
							 	<c:when test="<%=disponibility[p]%>">
								 	<form action="/NibInk/AddToCart" method="get" class="addToCartForm">
								 		<input type="hidden" name="product" value="${item.getCodenumber()}">
								 		<button type="submit" class="addToCart">Add to Cart</button>
									</form>
							 	</c:when>
							 	<c:otherwise>
								 		<button disabled class="addToCart">Non Disponibile</button>
							 	</c:otherwise>
							 </c:choose>
							 
						</div>
					 </div>
					 
					 <% i++; 
					 	p++;
					 	if (i==5){i=0; %>
					 
					 	</div>
					 <% } %>
					 
				</c:forEach>											
			</div>
			
			<div class="navigationKeys">
				<% 
					
					for(int k=0, j=1; k<items; k+=itemPerPage, j++)
					{
				%>
						<form action="/NibInk/nextPage" method="get">
							<input type="hidden" value="<%= filter %>" name="filters" class="invisibleButtons">
							<input type="hidden" value="<%=k%>" name="startIndex" class="invisibleButtons">
							<input type="hidden" value="<%=j%>" name="pageNumber" class="invisibleButtons">
							<input type="submit" value="Pagina <%=j%>" class="navButtons">
						</form>
				<%
					}
				%>
								
				
   			</div>
		</div>
	</div>
	</div>
	
	<div class="footer">
		<jsp:include page="footer.jsp"/>
	</div>
</body>
</html>
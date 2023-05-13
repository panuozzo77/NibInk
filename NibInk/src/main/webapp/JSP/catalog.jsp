<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.model.ItemManager" %>
<%@ page import="com.model.Item" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Catalog</title>
	<link href="/NibInk/CSS/catalog3.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<jsp:include page="Navbar.jsp" />


	<%
		int itemPerPage=15;
		int startIndex=request.getParameter("whereToStart")==null ? 0 : Integer.parseInt(request.getParameter("whereToStart"));
		int pageNumber=request.getParameter("pageNumber")==null ? 1 : Integer.parseInt(request.getParameter("pageNumber"));
		ItemManager im = new ItemManager();
		ArrayList<Item> itemsLoaded=im.loadItems(startIndex, itemPerPage);
	%>
	
	<div class="container">
		
		<div class="filters">
			<p class="filterText">Filtri:</p>
			
			<form class="filterForm" action="filterServlet" method="get">
				<input type="checkbox" id="productType1" name="onlyPens" value="">
				<label for="productType1"> Penne Stilografiche</label><br>
				<input type="checkbox" id="productType2" name="onlyInks" value="">
				<label for="productType2"> Inchiostri</label><br>
				<input type="checkbox" id="productType3" name="onlyNotebooks" value="">
				<label for="productType3"> Taccuini</label><br>
				<br>
				<input type="submit" class="buttons">
			</form>
		</div>
		
		
		
		<div class=container2>
		<h1 class="title">&emsp;&emsp;Catalog</h1> <br>
			<div class="itemInCatalog">		
				<% int i=0; %>			
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
							 <button class="addToCart">Add to Cart</button>
						</div>
					 </div>
					 
					 <% i++; 
					 	if (i==5){i=0; %>
					 
					 	</div>
					 <% } %>
					 
				</c:forEach>											
			</div>
			
			<div class="navigationKeys">
				<form action="page" method="get">
        			<input type="hidden" value="<%=startIndex + itemPerPage%>" name="startIndex">
					<input type="hidden" value="<%=pageNumber + 1%>" name="pageNumber">
            		<input class="buttons" type="submit" value="Pagina <%=pageNumber + 1%>">
        		</form>
   			</div>	
		</div>
	</div>
</body>
</html>
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
	<link href="/NibInk/CSS/catalog3.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="navbar">
	<jsp:include page="Navbar.jsp"/>
</div>


	<%
		int itemPerPage=10;
		int startIndex=request.getParameter("startIndex")==null || request.getParameter("pageNumber").equals(String.valueOf(1)) ? 0 : Integer.parseInt(request.getParameter("startIndex"));
		int pageNumber=request.getParameter("pageNumber")==null ? 1 : Integer.parseInt(request.getParameter("pageNumber"));
		String filter=request.getParameter("filter");
		ItemManager im = new ItemManager();
		ArrayList<Item> itemsLoaded;
		if(filter==null){
			itemsLoaded=im.loadItems(startIndex, itemPerPage);
		}
		else{
			itemsLoaded=im.loadFilteredItems(startIndex, itemPerPage, filter);
			
		}
		
	%>
	<div class="container0">
	<div class="container">
		
		<div class="filters">
			<p class="filterText">Filtri:</p>
			
			<form class="filterForm" action="/NibInk/FilterServlet" method="post">
				<input type="checkbox" id="onlyPens" name="onlyPens" value="Pens">
				<label for="onlyPens"> Penne Stilografiche</label><br>
				<input type="checkbox" id="onlyInks" name="onlyInks" value="Inks">
				<label for="onlyInks"> Inchiostri</label><br>
				<input type="checkbox" id="onlyNotebooks" name="onlyNotebooks" value="Notebooks">
				<label for="onlyNotebooks"> Taccuini</label><br>
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
							 <form action="/NibInk/SingleAddToCart" class="addToCartForm">
							 	<input type="hidden" name="product" value="${item.getCodenumber()}">
							 	<button type="submit" class="addToCart">Add to Cart</button>
							 </form>
						</div>
					 </div>
					 
					 <% i++; 
					 	if (i==5){i=0; %>
					 
					 	</div>
					 <% } %>
					 
				</c:forEach>											
			</div>
			
			<div class="navigationKeys">
				<% 
					DAOItem db=new DAOItem();
					for(int k=0, j=1; k<db.getItemsNumber();k+=itemPerPage, j++)
					{
				%>
						<form action="/NibInk/nextPage" method="get">
							<input type="hidden" value="<%=k+1%>" name="startIndex">
							<input type="hidden" value="<%=j%>" name="pageNumber">
							<input type="submit" value="Pagina <%=j%>">&emsp;
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
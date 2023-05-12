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

	<%
		int itemPerPage=24;
		int startIndex=request.getParameter("whereToStart")==null ? 0 : Integer.parseInt(request.getParameter("whereToStart"));
		int pageNumber=request.getParameter("pageNumber")==null ? 1 : Integer.parseInt(request.getParameter("pageNumber"));
		ItemManager im = new ItemManager();
		ArrayList<Item> itemsLoaded=im.loadItems(startIndex, itemPerPage);
	%>
	
	<div class="container">
		
		<div class="filters">
		<p class="filterText">Filtri:</p>
		
		<form action="filterServlet" method="get" class="filterForm">
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
		
		<h1>&emsp;Catalog</h1>
		
		<div class=container2>
		
			<div class="itemInCatalog">					
				<c:forEach items="<%= itemsLoaded %>" var="item">
					<button class="card" type="button" onclick="location.href='/NibInk/JSP/product.jsp?id=${item.getCodenumber()}';"> 
						<img src="/NibInk/images/${item.getTitle()}.jpg" height=100px width=100px>
						<br>
						${item.getTitle()}<br>
						<fmt:formatNumber value="${item.getPrice()}" type="currency"/>
					 </button>
				</c:forEach>
			</div>
			
			<div class="tasti_navigazionali">
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
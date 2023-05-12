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
	<link rel="stylesheet" href="/NibInk/CSS/catalog.css">
</head>
<body>
	<%
	int itemPerPage=5;
	int startIndex=request.getParameter("startIndex")==null ? 0 : Integer.parseInt(request.getParameter("startIndex"));
	int pageNumber=request.getParameter("pageNumber")==null ? 1 : Integer.parseInt(request.getParameter("pageNumber"));
	ItemManager im = new ItemManager();
	ArrayList<Item> itemsLoaded=im.loadItems(startIndex, itemPerPage);
	%>

	<h1>Catalog</h1>

		<c:forEach items="<%= itemsLoaded %>" var="item">
			<div class="column">
   				<div class="card">
   					<a href="/NibInk/JSP/product.jsp?id=${item.getCodenumber()}">
   						<img src="/NibInk/images/${item.getTitle()}.jpg" height=100px width=100px>
					</a>
					<br>
					${item.getTitle()}
					<br>
					<fmt:formatNumber value="${item.getPrice()}" type="currency"/>
				</div>
			</div>
		</c:forEach>
		
		 <div class="tasti_navigazionali">
		<% 
		DAOItem db=new DAOItem();
			for(int i=0, j=1; i<db.getItemsNumber();i+=itemPerPage, j++)
			{
		%>
				<form action="/NibInk/nextPage" method="get">
					<input type="hidden" value="<%=i+1%>" name="startIndex">
					<input type="hidden" value="<%=j%>" name="pageNumber">
					<input type="submit" value="Pagina <%=j%>">
				</form>
		<%
			}
		%>
		</div>
</body>
</html>
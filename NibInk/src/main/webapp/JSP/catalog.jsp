<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.model.Catalog" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Catalog</title>
	<link rel="stylesheet" href="/NibInk/CSS/catalog.css">
</head>
<body>

	<h1>Catalog</h1>
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
		
</body>
</html>
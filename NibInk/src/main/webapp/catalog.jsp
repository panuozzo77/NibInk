<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.model.Catalog" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Catalog</title>
</head>
<body>
	<h1>Catalog</h1>
	<table>
		<thead>
			<tr>
				<th>Code</th>
				<th>Title</th>
				<th>Color</th>
				<th>Price</th>
				<th>Weight</th>
				<th>Dimensions</th>
				<th>Description</th>
			</tr>
		</thead>
		<tbody>
		<% Catalog test = new Catalog();%>
			<c:forEach var="item" items="${Catalog.allItems}">
				<tr>
					<td>${item.codenumber}</td>
					<td>${item.title}</td>
					<td>${item.color}</td>
					<td><fmt:formatNumber value="${item.price}" type="currency"/></td>
					<td>${item.weight} grams</td>
					<td>${item.dimensions}</td>
					<td>${item.description}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<% int numItems = Catalog.allItems.size(); %>
	${numItems}
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
</head>
<body>
<%
String admin = (String) request.getSession().getAttribute("userType");
if(admin!=null)
	if(!admin.equals("admin"))
		response.sendError(HttpServletResponse.SC_FORBIDDEN);
%>
<ul class="menu">
    <li><a href="/NibInk/JSP/dashboard.jsp">Dashboard</a></li>
    <li><a href="/NibInk/JSP/products.jsp">Products</a></li>
    <li><a href="/NibInk/JSP/orders.jsp">Orders</a></li>
    <li><a href="#">Customers</a></li>
    <li><a href="/NibInk/JSP/itemEntry.jsp"> Crea nuovo Articolo</a>
  </ul>
</body>
</html>
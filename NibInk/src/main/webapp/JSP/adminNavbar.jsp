<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
<head>
	<meta charset="ISO-8859-1">
</head>
<body>
<%//Admin check
String admin = (String) request.getSession().getAttribute("userType");
if (admin != null && !admin.equals("admin")) {
	response.sendError(HttpServletResponse.SC_FORBIDDEN);
}
%>

<ul class="menu">
    <li><a href="/NibInk/JSP/dashboard.jsp">Dashboard</a></li>
    <li><a href="/NibInk/JSP/products.jsp">Articoli</a></li>
    <li><a href="/NibInk/JSP/orderManager.jsp">Ordini</a></li>
    <li><a href="/NibInk/JSP/customerManager.jsp">Utenti</a></li>
    <li><a href="/NibInk/JSP/shippingMethods.jsp">Metodi di Spedizione</a></li>
    <li><a href="/NibInk/JSP/itemEntry.jsp"> Crea nuovo Articolo</a>
    <li><a href="/NibInk/JSP/messageCenter.jsp"> Centro messaggi</a>
    <li><a href="/NibInk/JSP/home.jsp"> HomePage sito</a>
  </ul>
</body>
</html>
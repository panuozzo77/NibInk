<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@page import="com.model.DAOOrder" %>
<%@page import="com.model.Order" %>
<%@page import="java.text.SimpleDateFormat" %>

<%
    DAOOrder daoOrder = new DAOOrder();
    int itemsPerPage = 30;
    int pageNumber = request.getParameter("pageNumber") == null ? 1 : Integer.parseInt(request.getParameter("pageNumber"));
    int startIndex = request.getParameter("startIndex") == null || request.getParameter("pageNumber").equals(String.valueOf(1)) ? 0 : Integer.parseInt(request.getParameter("startIndex"));
    
    List<Order> orderList = daoOrder.loadOrdersByMostRecent(startIndex, itemsPerPage);
%>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/NibInk/CSS/admin.css">
<title>Ordini</title>
<body>
<div class="navbar">
	<jsp:include page="adminNavbar.jsp"/>
</div>
    <table>
        <tr>
            <th>ID</th>
            <th>User</th>
            <th>Email</th>
            <th>Shipping Address</th>
            <th>Invoice Address</th>
            <th>Payment Method</th>
            <th>Amount</th>
            <th>Status</th>
            <th>Shipping Method</th>
            <th>Order Date</th>
        </tr>

        <% SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (Order order : orderList) {
        %>
        <tr>
            <td><%= order.getId() %></td>
            <td><%= order.getUser() %></td>
            <td><%= order.getEmail() %></td>
            <td><%= order.getShippingAddress() %></td>
            <td><%= order.getInvoiceAddress() %></td>
            <td><%= order.getPaymentMethod() %></td>
            <td><%= order.getAmount() %></td>
            <td><%= order.getStatus() %></td>
            <td><%= order.getShippingMethod() %></td>
            <td><%= dateFormat.format(order.getOrderDate()) %></td>
        </tr>
        <% } %>
    </table>

    <div class="navigationKeys">
        <%
            int orderCount = daoOrder.getOrderCount();
            int pageCount = (int) Math.ceil((double) orderCount / itemsPerPage);
            for (int i = 1; i <= pageCount; i++) {
        %>
        <form action="/NibInk/JSP/order.jsp" method="get">
            <input type="hidden" value="<%= i %>" name="pageNumber" class="invisibleButtons">
            <input type="hidden" value="<%= (i - 1) * itemsPerPage %>" name="startIndex" class="invisibleButtons">
            <input type="submit" value="Page <%= i %>" class="navButtons">
        </form>
        <% } %>
    </div>
</body>
</html>
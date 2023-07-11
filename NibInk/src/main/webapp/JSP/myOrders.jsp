<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.model.DAOOrder" %>
<%@ page import="com.model.Order" %>
<%@ page import="com.model.OrderedItem" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.model.DAOItem" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>I Miei Ordini</title>
    <link href="/NibInk/CSS/myOrders.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <div class="page">
        <div class="title">
            <h2>I Miei ordini</h2>
        </div>
        <div class="prodotto">
            <% 
            DAOOrder db = new DAOOrder();
            DAOItem item = new DAOItem();
            String utente = String.valueOf(session.getAttribute("id")); 
            ArrayList<Order> orders = db.loadAllOrder(utente);
            for (Order ord : orders) {
            %>
            <div class="informazione_ordine">
                <div class="info">
                    <h3>Data Ordine</h3>
                    <p><%= ord.getOrderDate() %></p>
                </div>
                <div class="info">
                    <h3>Totale Ordine</h3>
                    <p><%= ord.getAmount() %></p>
                </div>
                <div class="info">
                    <h3>Numero Ordine: <%= ord.getId() %></h3>
                </div>
                <div class="informazione_consegna">
                    <h3>Consegnato il BOH</h3>
                </div>
                <div class="genera_fattura">
                    <form action="/NibInk/JSP/invoice.jsp" >
                        <input type="hidden" name="id" value="<%= ord.getId() %>">
                        <button type="submit" class="addToCart">Genera Fattura</button>
                    </form>
                </div>
            </div>
            <% 
            for (OrderedItem orderedItem : ord.getPurchasedItems(ord.getId())) { 
            	
            %>
            <div class="prodotto">
                <div class="dettagli_ordine">
                    <img alt="" src="<%= orderedItem.getItemId() %>">
                    <h2><%= orderedItem.getName() %></h2>
                </div>
                <div class="function">
                    <form action="/NibInk/JSP/product.jsp">
                        <input type="hidden" name="id" value="<%= orderedItem.getItemId() %>">
                        <button type="submit" class="addToCart">Informazioni Prodotto</button>
                    </form>
                </div>
            </div>
            <% 
            } 
            %>
            <% 
            } 
            %>
        </div>
    </div>
</body>
</html>
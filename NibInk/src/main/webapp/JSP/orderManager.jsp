<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.model.DAOCustomer" %>
<%@page import="com.model.Customer" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="/NibInk/JavaScript/orderManager.js"></script>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/NibInk/CSS/admin.css">
    <title>ADMIN - Ordini</title>
    <style>
        /* Styles for the popup */
        .popup-overlay {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            display: none;
        }

        .popup-container {
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background-color: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
            display: none;
        }

        .popup-container h2 {
            margin-top: 0;
        }

        .popup-container label {
            display: block;
            margin-bottom: 5px;
        }

        .popup-container input[type="text"] {
            width: 100%;
            padding: 5px;
            margin-bottom: 10px;
        }

        .popup-container input[type="submit"] {
            padding: 5px 10px;
        }
        
        .option {
        	display: inline-block;
        	margin-right:40px;
        }
    </style>
</head>
<body>
<%//Admin check
String admin = (String) request.getSession().getAttribute("userType");
if (admin != null && !admin.equals("admin")) {
	response.sendError(HttpServletResponse.SC_FORBIDDEN);
}
%>
    <div class="navbar">
        <jsp:include page="adminNavbar.jsp" />
    </div>
    <div class="actions" id="buttons">
    	<div class="option">
    		<button onclick="showAllOrders()">Mostra tutti gli Ordini</button>
    	</div>
    	<div class="option">
	    	<input type="number" max="1000" id="userResearch" placeholder="Ricerca per ID Utente">
	    	<button id="userButton" disabled onclick="showSingleUserOrders()">Avvia Ricerca</button>
    	</div>
    	<div class="option">
	    	<input type="date" id="startDate">
	    	<input type="date" id="endDate">
    		<button id="dateButton" disabled onclick="showOrderByDate()">Ricerca per Data</button>
    	</div>
    	<div class="option">
	    	<input type="number" max="1000" id="invoiceResearch" placeholder="ID ordine">
	    	<button id="invoiceButton" disabled onclick="showInvoice()">Mostra dettagli Ordine</button>
    	</div>
    </div>
    <div id=container></div>
</body>
</html>

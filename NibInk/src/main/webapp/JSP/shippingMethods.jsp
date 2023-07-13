<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.model.ShippingMethodManager" %>
<%@page import="com.model.ShippingMethod" %>
<%@page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/NibInk/CSS/admin.css">
    <title>Spedizioni</title>
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
    </style>
</head>
<body>
    <div class="navbar">
        <jsp:include page="adminNavbar.jsp" />
    </div>
    <%
    ShippingMethodManager db = new ShippingMethodManager();
    List<ShippingMethod> list = db.getAllShippingMethods();
    %>
    <table>
        <tr>
            <th>Nome Spedizione</th>
            <th>Spedizioniere</th>
            <th>Prezzo</th>
            <th>Azione</th>
        </tr>
        <c:forEach items="<%=list %>" var="method">
            <tr>
                <td>${method.getName()}</td>
                <td>${method.getCourier()}</td>
                <td>${method.getAmount()}</td>
                <td>
                    <form action="/NibInk/ShippingManagerServlet" method="post">
                        <input type="hidden" name="shippingName" value="${method.getName()}" />
                        <input type="hidden" name="mode" value="delete" />
                        <input type="submit" value="Elimina" />
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <button onclick="togglePopup()">Nuovo Metodo di spedizione</button>
    <div class="popup-overlay" id="popupOverlay"></div>
    <div class="popup-container" id="popupContainer">
        <h2>Aggiungi spedizione</h2>
        <form action="/NibInk/ShippingManagerServlet" method="post">
            <label for="name">Nome Spedizione:</label>
            <input type="text" id="name" name="shippingName" required>
            <label for="courier">Spedizioniere:</label>
            <input type="text" id="courier" name="courier" required>
            <label for="amount">Prezzo:</label>
            <input type="text" id="amount" name="amount" required>
            <input type="hidden" name="mode" value="add" />
            <input type="submit" value="Aggiungi">
        </form>
        <button onclick="togglePopup()">Indietro</button>
    </div>

    <script>
        function togglePopup() {
            var overlay = document.getElementById("popupOverlay");
            var container = document.getElementById("popupContainer");
            overlay.style.display = overlay.style.display === "block" ? "none" : "block";
            container.style.display = container.style.display === "block" ? "none" : "block";
        }
    </script>
</body>
</html>

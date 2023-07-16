<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.model.DAOCustomer" %>
<%@page import="com.model.Customer" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/NibInk/CSS/admin.css">
    <title>ADMIN - Utenti</title>
	<link rel="icon" type="image/x-icon" href="/NibInk/images/favicon.ico">
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
<%//Admin check
String admin = (String) request.getSession().getAttribute("userType");
if (admin != null && !admin.equals("admin")) {
	response.sendError(HttpServletResponse.SC_FORBIDDEN);
}
%>

    <div class="navbar">
        <jsp:include page="adminNavbar.jsp" />
    </div>
    <%
    DAOCustomer db = new DAOCustomer();
    ArrayList<Customer> list = db.getAllUsers();
    %>
    <table>
        <tr>
        	<th>ID</th>
            <th>Nome</th>
            <th>Cognome</th>
            <th>Email</th>
            <th>Tipo</th>
            <th>Azione1</th>
            <th>Azione2</th>
            <th>Azione3</th>
        </tr>
        <c:forEach items="<%=list %>" var="user">
            <tr>
                <td>${user.getID()}</td>
                <td>${user.getName()}</td>
                <td>${user.getSurname()}</td>
                <td>${user.getEmail()}</td>
                <td>${user.getType()}</td>
                <td>
                    <form action="/NibInk/adminChange" method="get">
                        <input type="hidden" name="userId" value="${user.getID()}" />
                        <input type="hidden" name="mode" value="delete" />
                        <input type="submit" value="Elimina" />
                    </form>
                </td>
                <td>
                	<button onclick="togglePopupPassword(2, ${user.getID()})">Cambia Password</button> <!-- funzionante -->
                </td>
                <td>
                	<button id="viewAddressesButton" onclick="viewAddresses(${user.getID()})">Visualizza Indirizzi</button>
                	<script>
                	  function viewAddresses(userId) {
                	    $.ajax({
                	      url: "/NibInk/adminChange",
                	      method: "GET",
                	      data: { userId: userId, mode: "showAddresses" },
                	      dataType: "json", 
                	      success: function (response) {
                	    	  var overlay = document.getElementById("popupOverlay");
                	    	  var container = document.getElementById("popupContainer3");

                	    	  // Clear any existing addresses
                	    	  container.innerHTML = "";

                	    	  // Create the HTML table structure
                	    	  var table = document.createElement("table");
                	    	  var thead = document.createElement("thead");
                	    	  var tbody = document.createElement("tbody");

                	    	  // Create the table header row
                	    	  var headerRow = document.createElement("tr");
                	    	  for (var key in response[0]) {
                	    	    if (response[0].hasOwnProperty(key)) {
                	    	      var th = document.createElement("th");
                	    	      th.textContent = key;
                	    	      headerRow.appendChild(th);
                	    	    }
                	    	  }
                	    	  thead.appendChild(headerRow);
                	    	  table.appendChild(thead);

                	    	  // Create the table body rows
                	    	  response.forEach(function (address) {
                	    	    var row = document.createElement("tr");
                	    	    for (var key in address) {
                	    	      if (address.hasOwnProperty(key)) {
                	    	        var cell = document.createElement("td");
                	    	        cell.textContent = address[key];
                	    	        row.appendChild(cell);
                	    	      }
                	    	    }
                	    	    tbody.appendChild(row);
                	    	  });
                	    	  table.appendChild(tbody);

                	    	  // Append the table to the container
                	    	  container.appendChild(table);
                	    	  var backButton = $("<button>").text("Indietro").click(function() {
                	    		  togglePopup(3); });
                	    	  backButton.appendTo(container);
                	    	  overlay.style.display = "block";
                	    	  container.style.display = "block";
                	    	},
                	      <!-- finita la funzione-->
                	      error: function (xhr, status, error) {
                	        console.log("AJAX Error:", error);
                	      }
                	    });
                	  }
                	</script>
                </td>
            </tr>
        </c:forEach>
    </table>
    <script>
        function togglePopupPassword(i, user) {
            var overlay = document.getElementById("popupOverlay");
            var container = document.getElementById("popupContainer"+i);
            var hiddenField = document.getElementById("hiddenValue");
            hiddenField.value = user;
            overlay.style.display = overlay.style.display === "block" ? "none" : "block";
            container.style.display = container.style.display === "block" ? "none" : "block";
        }
        function togglePopup(i) {
        	  var overlay = document.getElementById("popupOverlay");
        	  var container = document.getElementById("popupContainer" + i);

        	  overlay.style.display = "none";
        	  container.style.display = "none";
        	}
    </script>
    <!-- form per cambiare la password -->
    <div class="popup-overlay" id="popupOverlay"></div>
    
    <div class="popup-container" id="popupContainer2">
        <h2>Cambia password</h2>
        <form action="/NibInk/adminChange" method="post">
        <label for="name">Nuova password:</label>
         <input type="text" id="input1" name="newPassword" required>
         <input type="hidden" id="hiddenValue" name="id" value="" />
         <input type="submit" value="Modifica">
         </form>
        <button onclick="togglePopup(2)">Indietro</button> 
    </div>
    <!-- div per visualizzare gli indirizzi -->
    <div class="popup-container" id="popupContainer3">
  		<div id="addressTableContainer">
  		</div>
	</div>
</body>
</html>

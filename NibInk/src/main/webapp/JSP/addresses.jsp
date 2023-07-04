<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="com.model.AddressManager"%>
<%@ page import="com.model.Address"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title> NibInk - Indirizzi </title>
	<link href="/NibInk/CSS/addresses.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<script src="/NibInk/JavaScript/jquery.js"></script>
<script src="/NibInk/JavaScript/addresses.js"></script>
<jsp:include page="navbar.jsp"/>

<% 
	HttpSession sessione = request.getSession();
	String guest = (String) sessione.getAttribute("userType");
	if(guest.equals("unregistered")){
		System.out.println("Guest!");
%>
	
<script>
	guestOnly();
</script>
<%
	}else{
%>

<div class="container0">
		<div class="container1">
		<h3>Indirizzi di Spedizione</h3>
			<% 
				int userId = (int) sessione.getAttribute("id");
				AddressManager addrs = new AddressManager(userId);
				int i=0;
				for(Address a : addrs.getAddresses()){
			%>	
					<div>
						<%= a.getName() %>
						<%= a.getSurname() %>
						<%= a.getCity() %>
						<%= a.getStreet() %>
						<%= a.getNumber() %>
					</div>
				
			<%	
				}
			%>
				<div>
					<button id="newAddrBtn" class="buttonShow" onclick=showAddressInput()>Aggiungi un nuovo indirizzo</button>
				</div>
				<div id="formiDiv">
					<form id="addrFrom" action="">
						<div id="inputAddr" class="Hidden">
							<p class="required">Paese</p>
							<br>
							<select id="Country" onchange="toggleItaly()"></select>
							
							<p class="required">Nome e Cognome</p>
							<input id="NameSurname" type="text">
							
							<div class="addr">
								<div class="addrFirst">
									<p class="required">Via</p>
									<input id="Street"type="text">
								</div>
								<div class="addrSecond">
									<p class="required">N° Civico</p>
									<input id="Number"type="text">
								</div>
							</div>
							
							<p>Appartamento/Interno/Altro</p>
							<input id="MoreInfo" type="text">
							
							<p class="required italyOnly">Codice Postale</p>
							<input id="ZipCode" class="italyOnly" type="text">
							
							<p class="required">Città</p>
							<input id="City" type="text">
							
							<p class="required italyOnly">Provincia</p>
							<br>
							<select id="State" class="italyOnly"></select>
							
							<div class="containerCheckbox">
								<div class="checkboxDiv">
									<input id="isBA" type="checkbox"> 
								</div>
								<div class="pDiv">
									<p>Seleziona se è un indirizzo di fatturazione</p>
								</div>
								
							</div>
							
							<div class="containerCheckbox">
								<div class="checkboxDiv">
									<input id="isDefault" type="checkbox" checked>	
								</div>
								<div class="pDiv">
									<p>Seleziona se vuoi renderlo predefinito</p>
								</div>
								
							</div>
							
							<input  id="userId" type="hidden" value="<%= userId %>">
							<button id="addBtn" class="Hidden" onclick="checkAddr()">Aggiungi indirizzo</button>
						</div>
						
					</form>
				</div>
		</div>
	</div>


<%
	}
%>	
</body>
</html>
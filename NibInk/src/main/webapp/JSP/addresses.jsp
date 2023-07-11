<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="javax.servlet.http.*" %>
<%@page import="com.model.AddressManager"%>
<%@page import="com.model.Address"%>

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
	String fromUser=request.getParameter("fromUser")!=null ? request.getParameter("fromUser") : "";
	boolean hideSend=!(fromUser.equals("1")); 
%>


<div class="container0">
		<div class="container1">
		<h3>Indirizzi di Spedizione</h3>
		<div id="registeredAddresses" class="regAddrs">
			<%
					HttpSession sessione = request.getSession();
					sessione.setAttribute("addr", "");
					sessione.setAttribute("isBA", "");
					sessione.setAttribute("addrBA", "");
					String registered = (String) sessione.getAttribute("userType");
					int Guest=1;
					if(registered.equals("registered")){
						Guest=0;
					}
					if(Guest==0){
						int userId = (int) sessione.getAttribute("id");
						AddressManager addrs = new AddressManager(userId);
						int i=0;
						for(Address a : addrs.getAddresses()){
				%>	
						<div id="regA<%=i %>" class="regA">
							<%if(a.getDefault()){%>
								<div>
									<p class="default">Predefinito</p>
								</div>
			
							<%} %>
							<div class="addrContainer">
								<div class="addrInfo">
									<div class="addrText">
										<p id="nameSurname<%=i%>"><%= a.getName()%><%= a.getSurname()%></p>
										<p id="streetNumber<%=i%>"><span id="street<%=i%>"><%= a.getStreet()%></span> <span id="number<%=i%>"><%= a.getNumber()%></span> </p>
										<p id="zcs<%=i%>"><span id="zipCode<%=i%>"><%=a.getZipCode()%></span> <span id="city<%=i%>"><%=a.getCity()%></span> <span id="state<%=i%>"><%=a.getState()%></span></p>
										<p id="moreInfo<%=i%>"><%=a.getMoreInfo()%></p>
										<p id="country<%=i%>"><%=a.getCountry()%></p> 
									</div>
									<%if(a.getBA()){%>
										<div>
											<p class="ba">Fatturazione</p>
										</div>
					
									<%} %>
										<input id="isBa<%=i%>" value="<%=a.getBA() %>" type="hidden">
										<input id="isDe<%=i%>" value="<%=a.getDefault() %>" type="hidden">
								</div>
								<div class="addrButtons">
									<%if(hideSend){%>
										<div>
											<button id="sndBttnId<%=i%>" class="sndBttn" onclick="sendAddr(<%=i%>)">Spedisci qui</button>
										</div>
									<%}%>
										
									<div>
										<div>
											<button id="mdfBttnId<%=i%>" class="mdfBttn" onclick="modifyAddr(<%=i%>)">Modifica</button>
										</div>
										<div>
											<%if(!a.getDefault()){%>
												<button id="rmBttnId<%=i%>" class="rmBttn" onclick="removeAddr(<%=i%>)">Rimuovi</button>
											<%} %>
										</div>										
									</div>
								</div>
							</div>
						</div>
					
				<%	
					i++;}}
				%>
		</div>
				<div>
					<button id="newAddrBtn" class="buttonShow" onclick="addNewAddress()">Aggiungi un nuovo indirizzo</button>
				</div>
				<div id="formiDiv">
					<div id="addrFrom">
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
							<%	if(Guest==0){ 
								HttpSession s = request.getSession();
								int user = (int) s.getAttribute("id");
							%>
								
								<input  id="userId" type="hidden" value="<%=user%>">
							<%} %>
							
							<button id="addBtn" class="Hidden" onclick="addAddress()">Aggiungi indirizzo</button>
							<button id="modifyDb" class="Hidden" value="" onclick="modifyAddrDb()">Modifica Indirizzo</button>
							<%if(Guest==0){  %>
								<button id="goBack" class="Hidden" onclick="toggleAddressInput()">Annulla</button>
							<%} %>
						</div>
						
					</div>
				</div>
		</div>
	</div>
	<% if(Guest==1) {%>
		<script> guestOnly()</script>
		
	<%} %>
</body>
</html>
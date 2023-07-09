<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>NibInk - Pagamento</title>
<link href="/NibInk/CSS/payment.css" rel="stylesheet" type="text/css"/>

</head>
<body>
<script src="/NibInk/JavaScript/jquery.js"></script>
<script src="/NibInk/JavaScript/payment.js"> </script>

<jsp:include page="navbar.jsp"/>
	
	<div class="container0">
		<div class="container1">
		<h3>Pagamento ordine</h3>
			<form id="paymentForm" onkeydown="handleKeyPress(event)">
				<p class="required">Intestatario Carta</p>	
				<input type="text" id="cardName"><br>
				<p class="required">Numero della Carta</p>
				<input type="text" id="cardNumber"><br>
				<div  class="secondHalf">
					<div class="shContent">
						<p class="required">Data di Scadenza</p>
						<br>
						<select id="expMonth" class="selectColor" onclick="revertColor()"></select><select id="expYear" class="selectColor"></select>
					</div>
					<div class="shContent">
						<p class="required">Codice di Sicurezza</p>
						<input type="text" id="cardCode">	
					</div>
				</div>
				<div class="billingAddr">
				<input type="checkbox" onclick="toggleBillingAddr()" id="addrCheckbox" checked> 
					<div class="cbText">
						<div><p>Indirizzo di fatturazione e spedizione coincidono</p></div>
						<div><p id="userSavedAddr"> <%= session.getAttribute("addr") %><p></div>
					</div>
				</div>
				<div id="inputBAddr" class="inputBAddrHidden">
					<h3>Indirizzo di Fatturazione</h3>
							<p class="required">Paese</p>
							<br>
							<select id="country" onchange="toggleItaly()">
							</select>
							
							<p class="required">Nome e Cognome</p>
							<input id="baNameSurname" type="text">
							
							<div class="addr">
								<div class="addrFirst">
									<p class="required">Via</p>
									<input id="baStreet"type="text">
								</div>
								<div class="addrSecond">
									<p class="required">N° Civico</p>
									<input id="baNumber"type="text">
								</div>
							</div>
							
							<p>Appartamento/Interno/Altro</p>
							<input id="baMoreInfo" type="text">
							
							<p class="required italyOnly">Codice Postale</p>
							<input id="baZipCode" class="italyOnly" type="text">
							
							<p class="required">Città</p>
							<input id="baCity" type="text">
							
							<p class="required italyOnly">Provincia</p>
							<br>
							<select id="state" class="italyOnly"></select>
						</div>
					
				<div class="shButtons">	
					<button id="submit" onclick="checkAndSubmit(event)">Rivedi il tuo Ordine</button>
				</div>	
			</form>
		</div>
	</div>
	
</body>
</html>